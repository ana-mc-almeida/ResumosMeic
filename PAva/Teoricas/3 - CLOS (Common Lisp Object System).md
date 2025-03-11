## Generics

Em CLOS é possível definir generic functions, por exemplo a função add:

### add example

```
(defgeneric add (x y))

(defmethod add ((x number) (y number))
    (+ x y)
)

(defmethod add ((x list) (y list))
    (mapcar #'add x y)
)

;; (add '(1 2) 3) adiciona o número 3 a cada elemento da lista:
(defmethod add ((x list) (y t))
    (add x (make-list (length x) :initial-element y)) 
)

(defmethod add ((x t) (y list))
    (add (make-list (length y) :initial-element x) y)
)
```

### factorial example

Para além de ser possível distinguir funções pelos tipos dos parametros podemos também distingui-las pela instancia do parametro, por exemplo para criar a função factorial:

```
(defgeneric fact (n))

(defmethod fact ((n integer))
    (* n (fact (1- n))))

(defmethod fact ((n (eql 0)))
    1)
```

E os metodos que são identificados com a instancia do parametro têm maior prioridade do que os que são identificados pelo tipo. 

### fibonacci example

O mesmo acontece para cadeias de heranças de tipos, os tipos mais especificos têm mais prioridade que os tipos mais gerais. Por exemplo, defininindo a função `fib` que executa o funcionamento da sequência de fibonacci.

```
(defgeneric fib (n))

(defmethod fib ((n (eql 0)))
    0
)

(defmethod fib ((n (eql 1)))
    1
)

(defmethod fib ((n number))
    (+ (fib (- n 1)) 
        (fib (- n 2))
    )
)

(let ((cached-results (make-hash-table)))
    (defmethod fib :around ((n number))
        (or (gethash n cached-results)
            (setf (gethash n cached-results)
                (call-next-method)
            )
        )
    )
)
```

A keyword `:arround` tem o mesmo funcionamento do `@Override` em java. A keyword `call-next-method` faz o mesmo que o `super` do java, neste caso seria `super.fib()`.

### explain example

Tendo em conta a hierarquia dos tipos dos numeros:

<img src="Images/hierarchy numbers.png">

```
(defgeneric explain (entity)
    (:method ((entity fixnum)) (format t "~S is a fixnum" entity))
    (:method ((entity rational)) (format t "~S is a rational" entity))
    (:method ((entity string)) (format t "~S is a string" entity))
)
```

Resultados de alguns testes:
```
> (explain 123)
123 is a fixnum
> (explain "Hi")
"Hi" is a string
> (explain 1/3)
1/3 is a rational
```

Para permitir a execução de mais de um metodo fazemos:

```
(defmethod explain :after ((entity integer))
    (format t " (in binary, is ~B)" entity)
)

(defmethod explain :before ((entity number))
    (format t "The number ")
)
```

Resultados de alguns testes:
```
> (explain 123)
The number 123 is a fixnum (in binary, is 1111011)
> (explain "Hi")
"Hi" is a string
> (explain 1/3)
The number 1/3 is a rational
```

## How it works

1. Computes the effective method.
2. If it exists, calls the effective method with the same arguments of the generic function call.
3. If it does not exist, calls no-applicable-method using, as arguments, the original generic function and the arguments of the original call.

Effective Method Computation:
1. Selects the applicable methods.
2. Sorts applicable methods by precedence order.
3. Combines applicable methods, producing the effective method.

Sorting the Applicable Methods:
- Sorts applicable methods by precedence order from most specific to least specific.
- Given two applicable methods:
  1. Their parameter specializers are examined in order (by default, from left to right).
  2. When two specializers differ, the highest precedence method is the one whose parameter specializer occurs first in the class precedence list of the corresponding argument. 
  3. When one specializer is an instance specializer `((eql object))`, the highest precedence method is the one whose parameter contains that specializer.
  4. When all specializers are identical, the two methods must have different qualifiers and either one can be selected to precede the other.

### Method Combination:

There are many pre-defined method combinations (known as method combination types):
- Simple - `append`, `nconc`, `list`, `progn`, `max`, `min`, `+`, `and`, `or`. Requires using the same method combination type in the generic function and all methods of the generic function.
- Standard - `standard`. Used by default when nothing is specified on the generic function. Implicitly used when the generic function is not specified.

### Standard Method Combination

- Primary methods define the main action of the effective method.
  - Only the most specific is (automatically) executed.
  - It can execute the next most specific method using call-next-method.
- Auxiliary methods modify that behavior:
  - `:before` Methods called before primary methods.
  - `:after` Methods called after primary methods.
  - `:around` Method called instead of other applicable methods but that can call some of them by using `call-next-method`.

If there are no applicable :around methods:
1. All `:before` methods are called, from most specific to least specific, and their values are ignored.
2. The most specific primary method is called.
  - If that method calls `call-next-method`, the next most specific method is called and their values are returned to the caller.
  - The values returned by the most specific primary method become the values returned by the generic function call.
3. All `:after` methods are called, from least specific to most specific, and their values are ignored.

`call-next-method`:

- `call-next-method` might be called:
  - without arguments: it uses the same arguments that were used in the method call.
  - with arguments: it uses the provided arguments but these should produce the same ordered sequence of applicable methods that was produced by the arguments used in the method call.
- If there are no more applicable methods, `call-next-method` calls the generic function `no-next-method` using, as arguments:
  - The generic function that contains the method that called `call-next-method`.
  - The method that called `call-next-method`.
  - The arguments that were used for calling `call-next-method`.
- `next-method-p` can be used in a method to determine whether a next applicable method exists.

### Simple Method Combination

Primary Methods: methods qualified with the combination type (`append`, `nconc`, `list`, `progn`, `max`, `min`, `+`, `and`, `or`).<br>
Auxiliary Methods: methods qualified with `:around`.

If there are no applicable `:around` methods:
1. The effective method is the application of the combination type (the operator) to the results of calling all the applicable primary methods sorted by precedence order.

If there are applicable `:around` methods, the most specific one is called. If that method calls `call-next-method`:
1. If there are more applicable `:around` methods, the next most specific `:around` method is called.
2. If there are no more applicable `:around` methods:
  - 1. The effective method is the application of the combination type (the operator) to the results of calling all the applicable primary methods sorted by precedence order.

### User-Defined Method Combination

- Name of the method combination.
- Parameters of the method combination (e.g., sorting order for applicable methods).
- Local variable to contain methods whose qualifiers satisfy this pattern.
- Calls each applicable method in the effective method.

For example:
```
(define-method-combination list ()
    ((methods (list)))
    `(list ,@(mapcar (lambda (method)
        `(call-method ,method))
        methods)
    )
)
```

# Classes

```
(defclass foo (bar baz)
    ((slot1 :initform (fact 5)
            :reader foo-slot1
            :writer set-foo-slot1
        )
        (slot2 :type string
            :initarg :slot2
            :accessor foo-slot2
        )
        (slot3 :allocation :class)
    )
    (:default-initargs :slot2 "hi there")
)

(defmethod foo-slot1 ((obj foo))
    (slot-value obj 'slot1)
)

(defmethod set-foo-slot1 ((obj foo) new-value)
    (setf (slot-value obj 'slot1) new-value)
)

(defmethod foo-slot2 ((obj foo))
    (slot-value obj 'slot2)
)

(defmethod (setf foo-slot2) (new-value (obj foo))
    (setf (slot-value obj 'slot2) new-value)
)
```

- Podemos ou não definir o tipo dos atributos.
- Para dizermos que um atributo tem um getter usamos `:reader`.
- Para dizermos que um atributo tem um setter usamos `:writer`.
- Se quisermos dizer que tem um getter e setter podemos usar `:accessor`
- Para inicializar argumentos temos de usar o formato acima.
- Para instanciar um objeto da classe `foo` usamos `make-instance`. (Como se fosse o `new` em Java).

### Class Precedence List

- Flavors:  Depth-first, from left to right, duplicates removed from the right (standard-object and t appended on the right).
- Loops Identical but duplicates removed from the left. 
- CLOS Topological sort of the inheritance graph using the local ordering of superclasses.

#### Example

```
(defclass a () ())
(defclass b () ())
(defclass c () ())
(defclass d (a b) ())
(defclass e (a c) ())
(defclass f (d e) ())
```

<img src="Images/Class Inheritance Graph.png">

- Flavors (1980) f d a b e c
- Loops (1986) f d b e a c
- CLOS (1991) f d e a c b (as long as classes are on the same level in the graph their order doesn't matter)
- Dylan (1996) f d e a b c (same as before but with an algorithm to have an order)
- Python 2.1 (2001) f d a b e c
- Python 2.2 (2001) f d b e a c
- Python 2.3 (2003) f d e a b c

