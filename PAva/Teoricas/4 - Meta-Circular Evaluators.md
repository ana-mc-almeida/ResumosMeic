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

Adicionando funções:
```
(define (call? exp)
    (and (pair? exp)
        (eq? (car exp) 'call)
    )
)

(define (call-operator exp)
    (cadr exp)
)

(define (call-operands exp)
    (cddr exp)
)

(define (flet? exp)
    (and (pair? exp)
        (eq? (car exp) 'flet)
    )
)

(define (flet-names exp)
    (map car (cadr exp))
)

(define (flet-functions exp)
    (map 
        (lambda (f)
            (make-function (cadr f) (cddr f))
        )
        (cadr exp)
    )
)

(define (make-function parameters body)
    (cons 'function
        (cons parameters body)
    )
)

(define (flet-body exp)
    (caddr exp)
)

(define (eval-flet exp env)
    (let ((extended-environment
            (augment-environment (flet-names exp)
                (flet-functions exp)
                env
            )
        ))
        (eval (flet-body exp) extended-environment)
    )
)

(define (function? obj)
    (and (pair? obj)
        (eq? (car obj) 'function)
    )
)

(define (function-parameters func)
    (cadr func)
)

(define (function-body func)
    (caddr func)
)

(define (eval-call exp env)
    (let ((func (eval-name (call-operator exp) env))
            (args (eval-exprs (call-operands exp) env))
        )
        (let ((extended-environment
                (augment-environment (function-parameters func)
                    args
                    env
                )
            ))
            (eval (function-body func) extended-environment)
        )
    )
)
```

Adicionando as funções ao evaluator:
```
(define (eval exp env)
    (cond ((self-evaluating? exp) exp)
        ((name? exp)
            (eval-name exp env)
        )
        ((let? exp)
            (eval-let exp env)
        )
        ((addition? exp)
            (+ (eval (first-operand exp) env)
                (eval (second-operand exp) env)
            )
        )
        ((product? exp)
            (* (eval (first-operand exp) env)
                (eval (second-operand exp) env)
            )
        )
        ((flet? exp)
            (eval-flet exp env)
        )
        ((call? exp)
            (eval-call exp env)
        )
        (else
            (error "Unknown expression type -- EVAL" exp)
        )
    )
)
```

Neste momento se quisermos chamar uma função temos de usar `(call name args)`, mas seria melhor poder só usar `(name args)`. Podemos adicionar essa possibilidade:
```
(define (call? exp)
    (pair? exp)
)

(define (call-operator exp)
    (car exp)
)

(define (call-operands exp)
    (cdr exp)
)
```

## Rebinding names

Exemplo:
```
>>  (flet ((square (a) "I'm a square"))
        (square 10)
    )
"I'm a square"
>>  (flet ((+ (x y) "I'm an addition"))
        (+ 10 20)
    )
30
```

Porque é que conseguimos mudar a função `square`, mas não conseguimos mudar a função `+`? Nomes ligados a funções podem ser shadowed, mas nomes ligados a operadores não podem ser shadowed. Para podermos "shadar" operadores precisamos de tratar os operadores como se estivessem ligados a operadores primitivos.

```
(define (make-primitive f)
    (list 'primitive f)
)

(define (primitive? obj)
    (and (pair? obj)
        (eq? (car obj) 'primitive)
    )
)

(define (primitive-operation prim)
    (cadr prim)
)

(define initial-bindings
    (list (cons 'pi 3.14159)
        (cons 'e 2.71828)
        (cons 'square (make-function '(x) '((* x x))))
        (cons '+ (make-primitive +))
        (cons '* (make-primitive *))
    )
)

(define (apply-primitive-function prim args)
    (apply (primitive-operation prim) args)
)

(define (eval-call exp env)
    (let ((func (eval-name (call-operator exp) env))
            (args (eval-exprs (call-operands exp) env))
        )
        (if (primitive? func)
            (apply-primitive-function func args)
            (let ((extended-environment
                    (augment-environment (function-parameters func)
                        args
                        env
                    )
                ))
                (eval (function-body func) extended-environment)
            )
        )
    )
)
```

E assim podemos reduzir o tamanho do evaluator:
```
(define (eval exp env)
    (cond ((self-evaluating? exp) exp)
        ((name? exp)
            (eval-name exp env)
        )
        ((let? exp)
            (eval-let exp env)
        )
        ((flet? exp)
            (eval-flet exp env)
        )
        ((call? exp)
            (eval-call exp env)
        )
        (else
            (error "Unknown expression type -- EVAL" exp)
        )
    )
)
```

Exemplo:
```
>>  (+ 3 4)
7
>>  (flet ((+ (x y) "I'm an addition"))
        (+ 10 20)
    )
"I'm an addition"
>>  (+ 3 (flet ((+ (x y) (* x y))) (+ 4 5)))
23
>>  (+ 1 2 3 4 5 6)
21
>>  (let ((* 10))
        (+ * *)
    )
20
>>  (let ((+ *)
            (* +)
        )
        (+ (* 1 2) 3)
    )
9                           # O resultado é 9 em vez de 6 porque as bindings são associadas em paralelo dentro do let. 
>>  (flet ((+ (x y) (* x y))
            (* (x y) (+ x y))
        )
        (+ (* 1 2) 3)
    )
...infinite loop...         # Aqui as bindings ficam a ser redefinidas infinitamente
```

Introduz-se também a `if` clause usando o `if` do Scheme e valores booleanos.

...

O environment que temos até agora é funcional: é criado, usado e descartado, mas era bom que tivesse um incremental scope para os nomes, isto é, se tivesse memória das binding anteriores.

```
(define (def? exp)
    (and (pair? exp) 
        (eq? (car exp) 'def)
    )
)

(define (def-name exp)
    (cadr exp)
)

(define (def-init exp)
    (caddr exp)
)

(define (eval-def exp env)
    (let ((value (eval (def-init exp) env)))
        (define-name! (def-name exp) value env)
        value
    )
)

(define (define-name! name value env)
    (let ((binding (cons name value))
            (new-pair (cons (car env) (cdr env)))
        )
        (set-car! env binding)
        (set-cdr! env new-pair)
    )
)

(define (fdef? exp)
    (and (pair? exp) 
        (eq? (car exp) 'fdef)
    )
)

(define (fdef-name exp)
    (cadr exp)
)

(define (fdef-parameters exp)
    (caddr exp)
)

(define (fdef-body exp)
    (cdddr exp)
)

(define (eval-fdef exp env)
    (let ((value
            (make-function (fdef-parameters exp)
                (fdef-body exp)
            )
        ))
        (define-name! (fdef-name exp) value env)
        value
    )
)
```

Adicionando ao evaluator:
```
(define (eval exp env)
    (cond ((self-evaluating? exp) exp)
        ((name? exp)
            (eval-name exp env)
        )
        ((if? exp)
            (eval-if exp env)
        )
        ((let? exp)
            (eval-let exp env)
        )
        ((flet? exp)
            (eval-flet exp env)
        )
        ((def? exp)
            (eval-def exp env)
        )
        ((fdef? exp)
            (eval-fdef exp env)
        )
        ((call? exp)
            (eval-call exp env)
        )
        (else
            (error "Unknown expression type -- EVAL" exp)
        )
    )
)
```

Example:
```
>>  (def foo 1)
1
>>  (+ foo (def foo 2))
3                               # But it could have been 4
>>  (def bar 1)
1
>>  (+ (def bar 2) bar)
4                               # But it could have been 3
```

The evaluation of a definition modifies the current environment. The modification occurs as a side-effect. Understanding side-effects requires an evaluation order.

Example with left-to-right evaluation order:
```
>>  (def baz 3)
3
>>  (+ (let ((x 0))
            (def baz 5)
        )
        baz 
    )
8
>>  (+ (let ()
        (def baz 6))
        baz
    )
12                              # Huuh? Shouldn't it be 9?
```

Este comportamento de dar 12 em vez de 9 é porque quando saimos de um scope tudo é descartado, mas isso não acontece quando o scope é vazio. No exemplo de cima, obtivemos 8 porque o baz depois do let volta a ser 3, como tinha sido definido anteriormente. O mesmo não se verifica para o caso de baixo. Isto não devia acontecer!

In our current implementation, the environment is implemented as a continuous sequence of bindings without separation between different scopes. To add such separation, we need to partition that sequence into frames. Each time we augment the environment (i.e., for every `let`, `flet` and function call), we create a new frame. Each frame contains an association list for the bindings. This change require us to redefine:
- the process of finding bindinds: `eval-name`
- the process of augmenting the environment: `augment-environment`
- the process of updating the environment: `define-name!`

```
(define (augment-environment names values env)
    (cons (map cons names values) env)
)

(define (eval-name name env)
    (define (lookup-in-frame frame)
        (cond ((null? frame)
                (eval-name name (cdr env))
            )
            ((eq? name (caar frame))
                (cdar frame)
            )
            (else
                (lookup-in-frame (cdr frame))
            )
        )
    )
    (if (null? env)
        (error "Unbound name -- EVAL-NAME" name)
        (lookup-in-frame (car env))
    )
)

(define (define-name! name value env)
    (let ((binding (cons name value)))
        (set-car! env (cons binding (car env)))
    )
)
```

Example:
```
>>  (def baz 3)
3
>>  (+ (let ((x 0))
            (def baz 5)
        )
        baz
    )
8
>>  (+ (let ()
        (def baz 6))
        baz
    )
9                                           # Good, it's fine now!
>>  (def counter 0)
0
>>  (fdef incr ()
        (def counter (+ counter 1))
    )
    (function () 
        (def counter (+ counter 1))
    )
>>  (incr)
1
>>  (incr)
1                                           # What?
```

A scope protects names: all internally defined names are invisible to the outside. But sometimes we want to change a name defined in an outer scope. This requires the concept of assignment. Instead of defining a new binding, an assignment updates an existing binding. Concrete syntax: `(set! name expression)`

```
(define (set? exp)
    (and (pair? exp) (eq? (car exp) 'set!))
)

(define (assignment-name exp)
    (cadr exp)
)

(define (assignment-expression exp)
    (caddr exp)
)

(define (eval-set exp env)
    (let ((value (eval (assignment-expression exp) env)))
        (update-name! (assignment-name exp) value env)
        value
    )
)
```

To update a name we must locate its binding but that's what the evaluation of the name does already.

```
(define (eval-name name env)
    (define (lookup-in-frame frame)
        (cond ((null? frame)
                (eval-name name (cdr env))
            )
            ((eq? name (caar frame))
                (cdar frame)
            )
            (else
                (lookup-in-frame (cdr frame))
            )
        )
    )
    (if (null? env)
        (error "Unbound name -- EVAL-NAME" name)
        (lookup-in-frame (car env))
    )
)
```

Adicionando a expressão de assignment ao evaluator:
```
(define (eval exp env)
    (cond ((self-evaluating? exp) exp)
        ((name? exp)
            (eval-name exp env)
        )
        ((if? exp)
            (eval-if exp env)
        )
        ((let? exp)
            (eval-let exp env)
        )
        ((flet? exp)
            (eval-flet exp env)
        )
        ((def? exp)
            (eval-def exp env)
        )
        ((set? exp)                                 # <- Aqui
            (eval-set exp env)
        )
        ((fdef? exp)
            (eval-fdef exp env)
        )
        ((call? exp)
            (eval-call exp env)
        )
        (else
            (error "Unknown expression type -- EVAL" exp)
        )
    )
)
```

Besides assignments, there is another important source of side-effects: input/output. We need to include input-output operations in our evaluator. They must be implemented using operating system primitives.

```
(define initial-bindings
    (list (cons 'pi 3.14159)
        ...
        (cons '<= (make-primitive <=))
        (cons '>= (make-primitive >=))
        (cons 'display (make-primitive display))
        (cons 'newline (make-primitive newline))
        (cons 'read (make-primitive read))
    )
)

(fdef print-value (text value)
    (evaluate-all-and-return-the-last-one
        (display text)
        (display " = ")
        (display value)
        (newline)
        value
    )
)
```

Example:
```
>> (print-value "fact(5)" (fact 5))
= 120 fact(5)                               # Huh?
```

Maybe using:
```
(fdef evaluate-all-and-return-the-last-one (v1 v2 v3 v4 v5)
    v5
)

(fdef print-value (text value)
    (evaluate-all-and-return-the-last-one
        (display text)
        (display " = ")
        (display value)
        (newline)
        value
    )
)
```

Nop! Nothing guarantees that the arguments to the function evaluate-all-and-return-the-last-one are evaluated from left to right. We need a specific control structure to ensure left-to-right evaluation. Following Scheme tradition, we will use `begin`.

```
(define (begin? exp)
    (and (pair? exp) 
        (eq? (car exp) 'begin)
    )
)
(define (begin-expressions exp)
    (cdr exp)
)

(define (eval-begin exp env)
    (define (eval-sequence expressions env)
        (if (null? (cdr expressions))
            (eval (car expressions) env)
            (begin
                (eval (car expressions) env)
                (eval-sequence (cdr expressions) env)
            )
        )
    )
    (eval-sequence (begin-expressions exp) env)
)

(define (eval exp env)
    (cond ((self-evaluating? exp) exp)
        ((name? exp)
            (eval-name exp env)
        )
        ((if? exp)
            (eval-if exp env)
        )
        ...
        ((def? exp)
            (eval-def exp env)
        )
        ((set? exp)
            (eval-set exp env)
        )
        ((fdef? exp)
            (eval-fdef exp env)
        )
        ((begin? exp)                       # <- Aqui
            (eval-begin exp env)
        )
        ((call? exp)
            (eval-call exp env)
        )
        (else
            (error "Unknown expression type -- EVAL" exp)
        )
    )
)
```

Example:
```
(fdef print-value (text value)
    (begin
        (display text)
        (display " = ")
        (display value)
        (newline)
        value
    )
)

>>  (print-value "fact(5)" (fact 5))
fact(5) = 120
120
>>  (print-value "35^3" (* 35 35 35))
35^3 = 42875
42875
```

It is usual to consider that every function body, every let body, and every flet body has an implicit begin form. By wrapping the list of forms in a begin form we automatically transform the list into a single expression. This expression will be evaluated sequentially. To implement the implicit begin, we have to modify the selectors that return the body of a let, the body of a flet and the body of a function.

Até ao slide 345