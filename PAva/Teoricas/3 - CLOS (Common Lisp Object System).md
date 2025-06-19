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

- Flavors: Depth-first, from left to right, duplicates removed from the right (standard-object and t appended on the right).
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

### Meta Classes

Classes are represented by instances of other classes.<br>
A metaclass is a class whose instances are classes.<br>
The metaclass of an object is the class of the class of the object.<br>

- Determines the inheritance process that is used by the classes that are its instances.
- Determines the representation of the instances of the classes that are its instances.
- Determines the access to the slots of the instances of the classes that are its instances.

<img src="Images/Metaclass Hierarchy.png">

The t class does not have a superclass and is superclass of all classes except itself.<br>
The standard-object class is a direct subclass of class t, is an instance of class standard-class and is superclass of all classes that are instances of standard-class except itself.

Results of some tests:

Metaclass `standard-class`:

```
> (defclass foo () ())
#<STANDARD-CLASS FOO>
> (make-instance 'foo)
#<FOO @ #x717910a2>
> (class-of (make-instance 'foo))
#<STANDARD-CLASS FOO>
> (class-of (class-of (make-instance 'foo)))
#<STANDARD-CLASS STANDARD-CLASS>
> (class-of (class-of (class-of (make-instance 'foo))))
#<STANDARD-CLASS STANDARD-CLASS>
```

We get in a loop when we reach standard-class

Metaclass `built-in-class`:

```
> (class-of 1)
#<BUILT-IN-CLASS FIXNUM>
> (class-of (class-of 1))
#<STANDARD-CLASS BUILT-IN-CLASS>
> (class-of (class-of (class-of 1)))
#<STANDARD-CLASS STANDARD-CLASS>
```

Metaclass `forward-referenced-class`:

```
> (defclass bar (baz) ())
#<STANDARD-CLASS BAR>
> (setq bar-supers (class-direct-superclasses (find-class 'bar)))
(#<FORWARD-REFERENCED-CLASS BAZ>)
> (class-of (first bar-supers))
#<STANDARD-CLASS FORWARD-REFERENCED-CLASS>
> (defclass baz () ())
#<STANDARD-CLASS BAZ>
> bar-supers
(#<STANDARD-CLASS BAZ>)
```

BAZ class is considered an instance of FORWARD REFERENCED CLASS when it was not defined yet.

Function `change-class`:

```
> (setq foo-instance (make-instance 'foo))
#<FOO @ #x717a0562>
> (change-class foo-instance 'baz)
#<BAZ @ #x717a0562>
> foo-instance
#<BAZ @ #x717a0562>
```

#### Obtain class:

- From an object `foo`: `(class-of foo)`
- From the name of a type `'bar`: `(find-class 'bar)`

Example:

```
> (class-of "I am a string")
#<BUILT-IN-CLASS STRING>
> (find-class 'string)
#<BUILT-IN-CLASS STRING>
> (defclass foo () ())
#<STANDARD-CLASS FOO>
> (find-class 'foo)
#<STANDARD-CLASS FOO>
```

### `make-instance`

The generic function `make-instance`:

```
(defgeneric make-instance (class &rest initargs))
```

Method specialization for symbols:

```
(defmethod make-instance ((class symbol) &rest initargs)
    (apply #'make-instance (find-class class) initargs)
)
```

Method specialization for classes:

```
(defmethod make-instance ((class class) &rest initargs)
    (let ((instance (apply #'allocate-instance class initargs)))
        (apply #'initialize-instance instance initargs)
        instance
    )
)
```

Optimizer:

```
(define-compiler-macro make-instance (class-expr &rest init-exprs)
    (if (and (consp class-expr) (eq (first class-expr) 'quote))
        (make-instance->constructor-call (second class-expr) init-exprs)
        ...
    )
)
```

### Slots

The expression `(slot-value obj name)` returns the value of `name` in `obj`.

- If the slot does not exist, calls the generic function `slot-missing`: `(slot-missing (class-of obj) obj name 'slot-value)`
- If the slot exists but is unbound, calls the generic function `slot-unbound`: `(slot-unbound (class-of obj) obj name)`

The expression `(setf (slot-value obj name) new-value)` changes the value of slot `name` in `obj`.

- If the slot does not exist, calls the generic function `slot-missing`: `(slot-missing (class-of obj) obj name 'setf new-value)`

Resultados de alguns testes:

```
(defclass foo ()
    ((slot1))
)

> (setq foo1 (make-instance 'foo))
#<FOO @ #x716da0a2>
> (setf (slot-value foo1 'slot1) 1) ;Updating slot1
1
> (setf (slot-value foo1 'slot2) 2) ;Error: slot2 does not exist!
The slot SLOT2 is missing from the object #<FOO @ #x716da0a2> of class #<STANDARD-CLASS FOO> during operation SETF
    [Condition of type PROGRAM-ERROR]

Restarts:
0: [TRY-AGAIN] Try accessing the slot again
1: [USE-VALUE] Return a value
2: [RETRY] Retry SLIME interactive evaluation request.
3: [ABORT] Return to SLIME's top level.
4: [ABORT] Abort entirely from this (lisp) process.
```

### Dynamic Objects

```
(defclass dynamic-object ()
    ((extra-slots
        :reader extra-slots
        :initform (make-hash-table :test #'eq)
        )
    )
)

(defmethod slot-missing ((class t)
        (object dynamic-object)
        slot-name
        operation
        &optional new-value
    )
    (case operation
        (slot-value
            (gethash slot-name (extra-slots object))
        )
        (setf
            (setf (gethash slot-name (extra-slots object))
                new-value
            )
        )
        (t (call-next-method))
    )
)
```

Results:

```
(defclass foo (dynamic-object)          ;Redefining class foo...
    ((slot1))
)

> (slot-value foo1 'slot1)              ;...but keeping old instances alive
1
> (setf (slot-value foo1 'slot2) 2)     ;Now, the slot exists...
2
> (slot-value foo1 'slot2)              ;...and it keeps its value
2
> (slot-value foo1 'slot3)
NIL                                     ;Humm, we should improve this
```

We should not return a value for "missing" slots that are not present in the set of extra slots.

Improving it we get:

```
(defmethod slot-missing ((class t)
        (object dynamic-object)
        slot-name
        operation
        &optional new-value
    )
    (case operation
        (slot-value
            (multiple-value-bind (value found?)
                (gethash slot-name (extra-slots object))
                (if found?
                    value
                    (slot-unbound class object slot-name)
                )
            )
        )
        (setf
            (setf (gethash slot-name (extra-slots object))
                new-value
            )
        )
        (slot-boundp
            (nth-value 1 (gethash slot-name (extra-slots object)))
        )
        (slot-makunbound
            (remhash slot-name (extra-slots object))
        )
    )
)
```

Results:

```
> (slot-value foo1 'slot1)          ;A 'normal' slot
1
> (slot-value foo1 'slot2)          ;An 'added' slot
2
> (slot-value foo1 'slot3)          ;A slot without an assigned value
The slot SLOT3 is unbound in the object #<FOO @ #x714a1f0a> of class #<STANDARD-CLASS FOO>.
    [Condition of type UNBOUND-SLOT]

Restarts:
0: [TRY-AGAIN] Try accessing the slot again
1: [USE-VALUE] Return a value
2: [STORE-VALUE] Store a value and return it
3: [RETRY] Retry SLIME interactive evaluation request.
4: [ABORT] Return to SLIME's top level.
5: [ABORT] Abort entirely from this (lisp) process.

> (setq foo2 (make-instance 'foo))  ;A new instance
#<FOO @ #x71648d6a>
> (* (slot-value foo2 'slot1) 2)    ;Error: slot1 is unbound in foo2
The slot SLOT1 is unbound in the object #<FOO @ #x7161dfaa> of class #<STANDARD-CLASS FOO>.
    [Condition of type UNBOUND-SLOT]

Restarts:
0: [TRY-AGAIN] Try accessing the slot again
1: [USE-VALUE] Return a value
2: [STORE-VALUE] Store a value and return it
3: [RETRY] Retry SLIME interactive evaluation request.
4: [ABORT] Return to SLIME's top level.
5: [ABORT] Abort entirely from this (lisp) process.

:C 2                                ;Choose option 2: [STORE-VALUE]
enter expression which will evaluate to a value to use: 25
50
CL-USER> (slot-value foo2 'slot1)
25
```

`slot-value` and `(setf slot-value)` are functions, but not generic functions.<br>
In all implementations that include the MOP (all of them, in practice), they call the generic functions `slot-value-using-class` and `(setf slot-value-using-class)`

The (non-generic) function `slot-value`:

```
(defun slot-value (object slot-name)
    (let* ((class (class-of object))
        (slot-definition (find-slot-definition class slot-name)))
        (if (null slot-definition)
            (slot-missing class object slot-name 'slot-value)
            (slot-value-using-class class object slot-definition)
        )
    )
)
```

The Generic Function `slot-value-using-class`:

```
(defmethod slot-value-using-class ((class standard-class)
                                    (object standard-object)
                                    (slotdef standard-effective-slot-definition)
                                  )
    (if ...
        (slot-unbound class object (slot-definition-name slotdef))
        ...
    )
)
```

The Generic Function `slot-unbound`:

```
(defmethod slot-unbound ((class t) instance slot-name)
    (restart-case
        (error 'unbound-slot :name slot-name :instance instance)
        (use-value (value)
            ...
        )
        (store-value (new-value)
            ...
        )
    )
)
```
