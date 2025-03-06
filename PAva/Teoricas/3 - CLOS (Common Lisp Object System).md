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