Para criar uma linguagem de programação não precisamos de criar um compilador diretamente. Podemos usar um evaluator. Enquanto a nossa linguagem não está definida precisamos de um evaluator de outra linguagem para correr o nosso evaluator. Vamos usar o evaluator do Scheme. 

# Evaluator

Abstract syntax: the evaluator will operate in terms of an abstract syntax: the syntax is recognized by predicates that abstract from the concrete syntax used.<br>
Example: Abstract Syntax for Assignments:
```
(define (set? expression)
    (eq? (car expression) 'set!)
)
```

Evaluator:
```
####### Identify what to evaluate #######
(define (eval exp)
    (cond ((self-evaluating? exp) exp)
        ((addition? exp)
            (+ (eval (first-operand exp))
                (eval (second-operand exp))
            )
        )
        ((product? exp)
            (* (eval (first-operand exp))
                (eval (second-operand exp))
            )
        )
        (else
            (error "Unknown expression type -- EVAL" exp)
        )
    )
)

####### Self evaluation #######
(define (self-evaluating? exp)
    (or (number? exp) (string? exp))
)

####### Adition evaluation #######
(define (addition? exp)
    (and (pair? exp)
        (eq? (car exp) 'plus)
    )
)

####### Product evaluation #######
(define (product? exp)
    (and (pair? exp)
        (eq? (car exp) 'times)
    )
)

####### Utils #######
(define (first-operand exp)
    (cadr exp)
)
(define (second-operand exp)
    (caddr exp)
)

####### REPL #######
(define (repl)
    (prompt-for-input)
    (let ((input (read)))
        (let ((output (eval input)))
            (print output)
        )
    )
    (repl)
)

(define (prompt-for-input)
    (display ">> ")
)

(define (print object)
    (write object)
    (newline)
)
```

We can change the name of the operations with this:
```
(define (addition? exp)
    (and (pair? exp)
        (eq? (car exp) '+)
    )
)

(define (product? exp)
    (and (pair? exp)
        (eq? (car exp) '*)
    )
)
```

Names are maintained in an environment: a collection of names. The environment is implemented as an association list: $((name_0 . value_0) (name_1 . value_1) ... (name_n . value_n))$

The improved evaluator looks like:

```
####### Identify what to evaluate #######
(define (eval exp env)                                          # Add env argument
    (cond ((self-evaluating? exp) exp)
        ((name? exp)                                            # Verify the value of the name in the environment
            (eval-name exp env)
        )
        ((let? exp)                                             # Support let operation
            (eval-let exp env)
        )
        ((addition? exp)
            (+ (eval (first-operand exp))
                (eval (second-operand exp))
            )
        )
        ((product? exp)
            (* (eval (first-operand exp))
                (eval (second-operand exp))
            )
        )
        (else
            (error "Unknown expression type -- EVAL" exp)
        )
    )
)

####### Self evaluation #######
(define (self-evaluating? exp)
    (or (number? exp) (string? exp))
)

####### Name evaluation #######
(define (eval-name name env)
    (cond (
        (null? env)
            (error "Unbound name -- EVAL-NAME" name)
        )
        ((eq? name (caar env))
            (cdar env)
        )
        (else
            (eval-name name (cdr env))
        )
    )
)

####### Let evaluation #######
(define (eval-let exp env)
    (let ((values (eval-exprs (let-inits exp) env)))
        (let ((extended-environment (augment-environment (let-names exp) values env)))
            (eval (let-body exp) extended-environment)
        )
    )
)

(define (eval-exprs exprs env)
    (if (null? exprs)
        (list)
        (cons (eval (car exprs) env)
            (eval-exprs (cdr exprs) env)
        )
    )
)

(define (augment-environment names values env)
    (if (null? names)
        env
        (cons (cons (car names) (car values))
            (augment-environment (cdr names) (cdr values) env)
        )
    )
)

(define empty-environment (list))

####### Adition evaluation #######
(define (addition? exp)
    (and (pair? exp)
        (eq? (car exp) 'plus)
    )
)

####### Product evaluation #######
(define (product? exp)
    (and (pair? exp)
        (eq? (car exp) 'times)
    )
)

####### Utils #######
(define (first-operand exp)
    (cadr exp)
)
(define (second-operand exp)
    (caddr exp)
)

####### REPL #######
(define (repl)
    (prompt-for-input)
    (let ((input (read)))
        (let ((output (eval input)))
            (print output)
        )
    )
    (repl)
)

(define (prompt-for-input)
    (display ">> ")
)

(define (print object)
    (write object)
    (newline)
)

####### Syntax #######
(define (addition? exp)
    (and (pair? exp)
        (eq? (car exp) '+)
    )
)

(define (product? exp)
    (and (pair? exp)
        (eq? (car exp) '*)
    )
)

(define (name? exp)
    (symbol? exp)
)

(define (let? exp)
    (and (pair? exp)
        (eq? (car exp) 'let)
    )
)

(define (let-names exp)
    (map car (cadr exp))
)

(define (let-inits exp)
    (map cadr (cadr exp))
)

(define (let-body exp)
    (caddr exp)
)
```

Example:
```
>>  (let ((pi 3.14159) (radius 5))
        (* 2 (* pi radius))
    )
=>  (eval (let ((pi 3.14159) (radius 5)) (* 2 (* pi radius))) ())
=>      (eval 3.14159 ())
<=      3.14159
=>      (eval 5 ())
<=      5
=>      (eval (* 2 (* pi radius)) ((pi . 3.14159) (radius . 5)))
=>          (eval 2 ((pi . 3.14159) (radius . 5)))
<=          2
=>          (eval (* pi radius) ((pi . 3.14159) (radius . 5)))
=>              (eval pi ((pi . 3.14159) (radius . 5)))
<=              3.14159
=>              (eval radius ((pi . 3.14159) (radius . 5)))
<=              5
<=          15.70795
<=      31.4159
<=  31.4159
```

Shadowing example
```
>>  (let ((a 1))
        (let ((b (+ a 2)))
            (let ((a (+ a b)))
                (* 2 a)
            )
        )
    )
=>  (eval (let ((a 1)) (let ((b (+ a 2))) (let ((a (+ a b))) (* 2 a)))) ())
=>      (eval 1 ()) 
<=      1
=>      (eval (let ((b (+ a 2))) (let ((a (+ a b))) (* 2 a))) ((a . 1)))
=>          (eval (+ a 2) ((a . 1)))
=>              (eval a ((a . 1)))
<=              1
=>              (eval 2 ((a . 1))) 
<=              2
<=          3
=>          (eval (let ((a (+ a b))) (* 2 a)) ((b . 3) (a . 1)))
=>              (eval (+ a b) ((b . 3) (a . 1)))
=>                  (eval a ((b . 3) (a . 1)))
<=                  1
=>                  (eval b ((b . 3) (a . 1)))
<=                  3
<=              4
=>              (eval (* 2 a) ((a . 4) (b . 3) (a . 1)))
=>                  (eval 2 ((a . 4) (b . 3) (a . 1)))
<=                  2
=>                  (eval a ((a . 4) (b . 3) (a . 1)))                          # The first name found is the one that is used, then the following ones are shadowed
<=                  4
<=              8
<=          8
<=      8
<=  8
```

Currently the REPL starts in a clean state, forcing us to introduce all needed names. We can provide some pre-defined names:
```
(define initial-bindings
    (list (cons 'pi 3.14159)
        (cons 'e 2.71828)
        ...
    )
)

(define initial-environment
    (augment-environment (map car initial-bindings)
        (map cdr initial-bindings)
        empty-environment
    )
)

(define (repl)
    (prompt-for-input)
    (let ((input (read)))
        (let ((output (eval input empty-environment)))
            (print output)
        )
    )
    (repl)
)
```