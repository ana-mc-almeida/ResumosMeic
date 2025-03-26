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

It is usual to consider that every function body, every let body, and every flet body has an implicit begin form. By wrapping the list of forms in a begin form we automatically transform the list into a single expression. This expression will be evaluated sequentially. To implement the implicit begin, we have to modify the selectors that return the body of a `let`, the body of a `flet` and the body of a `function`.

```
(define (let-body exp)
    (caddr exp)
)

(define (let-body exp)
    (caddr exp)
)

(define (function-body func)
    (caddr func)
)

(define (make-begin expressions)
    (cons 'begin expressions)
)
```

Namespaces:
- Java has namespaces for types, for constructors, for methods, for fields and variables, for labels, etc.
- C has a different namespace for structs, but a single namespace for functions and variables.
- Scheme has a single namespace: each name has just one meaning in each context.
- A Lisp-2 is a Lisp that has one namespace for functions and another namespace for variables.
- A Lisp-1 is a Lisp that has a single namespace where a name either represents a function or a variable but not both.
- Let's modify our evaluator to implement Lisp-2 semantics:
    - Create a different namespace for functions.
    - Each function definition is placed in the function namespace.
    - Each function call accesses the function namespace.
- Our namespace for functions will be implemented using hidden naming conventions, but we could use more sophisticated (and efficient) approaches.

```
(define (in-function-namespace name)
    (string->symbol
        (string-append
            "%function-"
            (symbol->string name)
        )
    )
)

# flet
(define (flet-names exp)
    (map car (cadr exp))
)

(define (flet-names exp)
    (map in-function-namespace
        (map car (cadr exp))
    )
)

(define (fdef-name exp)
    (cadr exp)
)

(define (fdef-name exp)
    (in-function-namespace (cadr exp))
)

# function call
(define (call-operator exp)
    (car exp)
)

(define (call-operator exp)
    (in-function-namespace (car exp))
)

# initial bindings
(define initial-bindings
    (list (cons 'pi 3.14159)
        ...
        (cons (in-function-namespace '+) (make-primitive +))
        (cons (in-function-namespace '*) (make-primitive *))
        (cons (in-function-namespace '-) (make-primitive -))
        (cons (in-function-namespace '/) (make-primitive /))
        (cons (in-function-namespace '=) (make-primitive =))
        (cons (in-function-namespace '<) (make-primitive <))
        (cons (in-function-namespace '>) (make-primitive >))
        (cons (in-function-namespace '<=) (make-primitive <=))
        (cons (in-function-namespace '>=) (make-primitive >=))
        ...
        (cons (in-function-namespace 'read) (make-primitive read))
    )
)
```

Lisp-2 and Higher-Order Functions example:
```
>>  (fdef reflexive-operation (f x)
        (f x x)
    )
(function (f x) (f x x))
>>  (reflexive-operation + 3)
error in "Unbound name -- EVAL-NAME": +
```

In a Lisp-2, a name that is not in function call position is always treated as not belonging to the function namespace. We need extra syntax to allow a name to be treated as belonging to the function namespace. We will use the form `(function +)`. If we allow changing the reader, we might even treat `#'foo` as an abbreviation for `(function foo)`.

```
(define (function-reference? exp)
    (and (pair? exp) 
        (eq? (car exp) 'function)
    )
)

(define (function-reference-name exp)
    (cadr exp)
)

(define (eval-function-reference exp env)
    (eval (in-function-namespace (function-reference-name exp))
        env
    )
)

(define (eval exp env)
    (cond ((self-evaluating? exp) exp)
        ((name? exp)
        (eval-name exp env))
        ((function-reference? exp)
        (eval-function-reference exp env))
        ...
        (else
        (error "Unknown expression type -- EVAL" exp))
    )
)
```

Now:
```
>>  (fdef reflexive-operation (f x)
        (f x x)
    )
(function (f x) (f x x))
>>  (reflexive-operation (function +) 3)
error in "Unbound name -- EVAL-NAME": %function-f
```

In a Lisp-2, a name that is in function call position is always treated as belonging to the function namespace. We need extra syntax to allow a name to be treated as not belonging to the function amespace. We will use the form `(funcall f ...)`

```
(define (funcall? exp)
    (and (pair? exp) 
        (eq? (car exp) 'funcall)
    )
)

(define (funcall-operator exp)
    (cadr exp)
)

(define (funcall-operands exp)
    (cddr exp)
)

(define (eval-funcall exp env)
    (let ((func (eval-name (funcall-operator exp) env))
            (args (eval-exprs (funcall-operands exp) env))
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

(define (eval exp env)
    (cond ((self-evaluating? exp) exp)
        ((name? exp)
            (eval-name exp env)
        )
        ((function-reference? exp)
            (eval-function-reference exp env)
        )
        ((funcall? exp)
            (eval-funcall exp env)                      # <- Aqui
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
        ...
        ((call? exp)
            (eval-call exp env)
        )
        (else
            (error "Unknown expression type -- EVAL" exp)
        )
    )
)
```

## Accumulation

```
(fdef sum (f a b)
    (if (> a b)
        0
        (+ (funcall f a)
            (sum f (+ a 1) b)
        )
    )
)


(fdef product (f a b)
    (if (> a b)
        1
        (* (funcall f a)
            (product f (+ a 1) b)
        )
    )
)
```

Sums and products are so identical that it is possible to abstract them using additional (functional) parameters.

```
(fdef accumulate (combiner neutral f a b)
    (if (> a b)
        neutral
        (funcall combiner
            (funcall f a)
            (accumulate combiner neutral f (+ a 1) b)
        )
    )
)
```

Functional programming in a Lisp-2 tends to spread `funcall` and `function` all over the code. This makes the code hard to read.


Accumulation (in a Lisp-1):
```
(fdef accumulate (combiner neutral f a b)
    (if (> a b)
        neutral
        (combiner
            (f a)
            (accumulate combiner neutral f (+ a 1) b)
        )
    )
)
```

Functional programming in a Lisp-1 looks much better (at the price of loosing homonyms). Let's change our evaluator to become a Lisp-1.

Em Lisp-2 tinhamos:
```
(define (in-function-namespace name)
    (string->symbol
        (string-append
            "%function-"
            (symbol->string name)
        )
    )
)
```

Em Lisp-1:
```
(define (in-function-namespace name)
    name
)
```

Para simplificar também podemos:
- Drop `function` (and `function-reference?`, `function-reference-name`, `eval-function-reference`).
- Drop `funcall` (and `funcall?`, `funcall-operator`, `funcall-operands`, `eval-funcall`).
- Drop `in-function-namespace`.

Example:
```
>> (fdef next (i)
(if (< i 0)
(- i 1)
(+ i 1)))
(function (i) ...)
>>  (fdef next (i)
        ((if (< i 0) - +) i 1)
    )
(function (i) ...)
>>  (next 3)
error in "Unbound name -- EVAL-NAME": (if (< i 0) - +)
```

We were expecting to see a name in the operator position, but we now have an expression there. Let's generalize the treatment of the operator.

Antes:
```
(define (eval exp env)
    (cond ((self-evaluating? exp) exp)
        ((name? exp)
            (eval-name exp env)
        )
        ...
        ((call? exp)
            (eval-call exp env)
        )
        (else
            (error "Unknown expression type -- EVAL" exp)
        )
    )
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

Depois:
```
(define (eval exp env)
    (cond ((self-evaluating? exp) exp)
        ((name? exp)
            (eval-name exp env)
        )
        ...
        ((call? exp)
            (eval-call exp env)
        )
        (else
            (error "Unknown expression type -- EVAL" exp)
        )
    )
)

(define (eval-call exp env)
    (let ((func (eval (call-operator exp) env))             # <- Aqui
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

## Anonymous functions

Named Functions
```
(fdef approx-pi (n)
    (flet ((term (i) (/ (expt -1.0 i) (+ (* 2 i) 1))))
        (* 4 
            (sum term 0 n)
        )
    )
)
```

Anonymous Functions
```
(fdef approx-pi (n)
    (* 4 
        (sum (lambda (i) (/ (expt -1.0 i) (+ (* 2 i) 1)))
            0 
            n
        )
    )
)
```

A lambda form evaluates to a function without requiring an explicit name for the function. The function is anonymous.

```
(define (lambda? exp)
    (and (pair? exp)
        (eq? (car exp) 'lambda)
    )
)

(define (lambda-parameters exp)
    (cadr exp)
)

(define (lambda-body exp)
    (cddr exp)
)

(define (eval-lambda exp env)
    (make-function (lambda-parameters exp)
        (lambda-body exp)
    )
)

(define (eval exp env)
    (cond ((self-evaluating? exp) exp)
        ((name? exp)
            (eval-name exp env)
        )
        ((lambda? exp)
            (eval-lambda exp env)
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
        ((set? exp)
            (eval-set exp env)
        )
        ((fdef? exp)
            (eval-fdef exp env)
        )
        ...
        (else
            (error "Unknown expression type -- EVAL" exp)
        )
    )
)
```

- A `def form` associates a name to the evaluation of a form, e.g., `(def foo (* 2 2))`.
- A `fdef form` associates a name to a function, e.g., `(fdef square (x) (* x x))`.
- A `lambda form` evaluates to a function, e.g., `(lambda (x) (* x x))`

Thus, we have the following equivalence: `(fdef square (x) (* x x)) = (def square (lambda (x) (* x x)))`. Or, in more abstract terms: $fdef = def + lambda$. The same equivalence occurs with $flet = let + lambda$. and with $let = lambda + function call$

Example $let = lambda + function call$:

From:
```
(let ((x (+ 1 2))
        (y (* 2 3))
    )
    (- x y)
)
```

To:
```
((lambda (x y)
        (- x y)
    )
    (+ 1 2)
    (* 2 3)
)
```

## Second order sum

$$\sum\limits^1_{x=-1} 10x^2 +5x = (10 - 5) + 0 + (10 + 5) = 20$$

```
>>  (fdef sum (f a b)
        (if (> a b)
            0
            (+ (f a)
                (sum f (+ a 1) b)
            )
        )
    )
(function (f a b) ...)

>>  (fdef second-order-sum (a b c i0 i1)
        (sum (lambda (x)
                (+ (* a x x) (* b x) c)
            )
            i0 i1
        )
    )
(function (a b c i0 i1) ...)

>>  (second-order-sum 10 5 0 -1 +1)
0                                   # What???
```

So far, we didn't think carefully about the scope of our binding constructs:
- A def form creates a binding in the current environment.
- A function call creates a set of bindings (for the parameters) that extends the current environment.
- At the end of the call, the extended environment is discarded.

A name is resolved by searching the current environment. This scoping discipline is called dynamic scope. In dynamic scope, the environment grows and shrinks in parallel with the call stack.

## Scopes

### Downwards Funarg Problem

Passing a function with free variables as argument causes the downwards funarg problem where the function is called in an environment where the free variables might be shadowed by other variables of the calling context.

Solution: Functions must know the correct environment for resolving the free variables.<br>

One potential solution might be to use name-mangling techniques to avoid name clashes. Isto é, usar nomes super esquisitos que mais ninguém vá usar. Mas isso traz problemas: 
- It makes the code hard to read.
- It doesn't really solve the problem, porque quando metemos funções com parametros com nomes esquisitos a chamar outras funções com parametros com nomes esquisitos vamos voltar a ter colisões.

The correct solution is to associate the downward function to the scope where the function was created. That scope is still active when the passed function is called unless it is possible to store the function somewhere and call it later. Some languages solve this last problem by restricting the language - Pascal forbids storing functions in variables. and C forbids inner functions.

Other languages (e.g., Scheme, Haskel, Common Lisp) implement environments with indefinite extent.

# Upwards Funarg Problem

```
>>  (fdef compose (f g)
        (lambda (x)
            (f (g x))
        )
    )
(function (f g) ...)

>> (fdef foo (x) (+ x 5))
(function (x) (+ x 5))

>> (fdef bar (x) (* x 3))
(function (x) (* x 3))

>> (def foobar (compose foo bar))
(function (x) (f (g x)))

>> (foobar 2)
error in "Unbound name -- EVAL-NAME": f
```

Returning a function with free variables as result causes the upwards funarg problem where the function is called in an environment where the free variables are not longer bound (or are bound to different values). 

Some languages avoid the upwards funarg problem by restricting the language - Pascal forbids functional returns, C forbids inner functions, Java forbids non-final free variables in anonymous inner classes and other languages (e.g., Scheme, Haskel, Common Lisp) implement environments with indefinite extent.

```
(define (make-function parameters body env)     # <- Added evn
    (cons 'function
        (cons parameters
            (cons env                           # <- Added evn
                body
            )
        )
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
    (make-begin (cdddr func))
)

(define (function-environment func)             # <- Novo
    (caddr func)
)

(define (eval-lambda exp env)                   # <- Added evn
    (make-function (lambda-parameters exp)
        (lambda-body exp)
        env                                     # <- Added evn
    )
)

(define (eval-call exp env)
    (let ((func (eval (call-operator exp) env))
            (args (eval-exprs (call-operands exp) env))
        )
        (if (primitive? func)
            (apply-primitive-function func args)
            (let ((extended-environment
                    (augment-environment (function-parameters func)
                        args
                        (function-environment func)                     # Aqui vamos buscar o env da func em vez de usar o global env
                    )
                ))
                (eval (function-body func) extended-environment)
            )
        )
    )
)
```

## Recursive functions

```
>>  (flet ((fact (n)
            (if (= n 0)
                1
                (* n (fact (- n 1)))
            ))
        )
        (fact 3)
    )
error in "Unbound name -- EVAL-NAME": fact
```

Antes funcionanva, mas agora `flet` creates a function and establishes a scope where a name is bound to that function. But the function is created outside that scope. As a result, the function cannot refer itself.

Até ao slide 576