### Computational System

A system that reasons about and acts upon a given domain. The domain is represented by the internal structures of the system:
- Data representing entities and relations.
- Program prescribing data manipulation.

A program is not a computational system.<br>
A program describes (part of) a computational system.<br>
A running program is a computational system.<br>

### Computational Meta-System

A computational system that has as domain another computational system (called the Object System). A computational meta-system operates on data that represents the computational object-system.

Examples:
- A debugger is a computational meta-system.
- A profiler is a computational meta-system.
- A (classic) compiler is not a computational meta-system (its domain is a program and not a computational system)

# Reflection

Reflection is the process of reasoning about and/or acting upon oneself.

### Reflective System

A meta-system that has itself as object-system. A reflective system is a system that can represent and manipulate its own structure and behavior at run time.

### Introspection

Introspection is the ability of a program to examine its own structure and behavior.
- How many parameters has the function `foo`?

### Intercession

Intercession is the ability of a program to modify its own structure and behavior.
- Change the class of this instance to `Bar`?

## Reification

Structural Reification: the ability of a system to reify its own structure.
- Which are the instance variables of this class?

Behavioral (or computational) Reification: the ability of a system to reify its own execution.
- Which are the active error handlers at this moment?

# Emacs Lisp Intercession

## traced-lambda example 

O objetivo é criar uma função `traced-lambda` que recebe como argumentos o nome outra função `x` e a própria função `x` e retorna uma funcão nova que tem o mesmo comportamento, mas que mostra os argumentos e quando é chamada. Iriamos obter:

```
(defun traced-lambda (name lambda-form)
    (cons 'lambda 
        (cons (cdar lambda-form)
            (cons 
                (list 'princ 
                    (cons 'list
                        (cons (list 'quote name)
                            (cadr lambda-form)
                        )
                    )
                )
                (cons '(princ '->)
                    (cddr lambda-form)
                )
            )
        )
    )
)
```

Para não termos de usar expressões tão confusas, existem os operadores:
- `_expr_ -> returns _expr_ unevaluated except for subexpressions preceded by comma.
- ,_subexpr_ -> evaluates _subexpr_ and inserts the value in the containing expression.
- ,@_subexpr_ -> evaluates _subexpr_ and splices the value (a list) in the containing expression.

Em Julia:

- :(expr) -> returns expr unevaluated except for subexpressions preceded by $.
- $(subexpr) -> evaluates subexpr and inserts the value in the containing expression.
- $(subexpr...) -> evaluates subexpr and splices the value (a list) in the containing expression.

Então fazendo o código acima, mas usando estas novas expressões:

```
(defun traced-lambda (name lambda-form)
    `(lambda ,(cadr lambda-form)
        (princ (list ',name ,@(cadr lambda-form)))
        (princ '->)
        ,@(cddr lambda-form)
    )
)
```

Regras relativas a nexted backquotes:
- \`\`_expr_ -> returns \`_expr_ unevaluated except for subexpressions preceded by comma.
- ,_subexpr_ -> evaluates _subexpr_ when the inner backquote is evaluated and inserts the value in the containing expression.
,,_subexpr_ -> evaluates _subexpr_ twice and inserts the value in the containing expression.
,',_subexpr_ -> evaluates _subexpr_ when the outer backquote is evaluated and inserts the value in the containing expression.

## memoize example

TODO: Checkar wtf é aquele último `result` no código em "Depois de aplicar memoization"

Por exemplo, a sequência de fibonacci, se for implementada recursivamente, tem time complexity O($2^n$). 

```
(defun fib (n)
    (if (< n 2)
        n
        (+ (fib (- n 1))
            (fib (- n 2))
        )        
    )
)
```

Para resolver isso usa-se memoization. Implementando essa tecnica em Emacs Lisp temos:

```
(defun memoize (lambda-form)
    (setcar (cddr lambda-form)
        `(let ((result ,(caddr lambda-form)))
            (setcar (cddr ',lambda-form)
                `(if (eql ,',(caadr lambda-form)
                        ',,(caadr lambda-form))
                    ',result
                    ,(caddr ',lambda-form)
                result
                )
            )
        )
    )
)
```

Depois de aplicar memoization:

```
(lambda (n)
    (let ((result
        (if (< n 2)
            n
            (+ (fib (- n 1))
                (fib (- n 2))
            )
        )
        ))
        (setcar (cddr '(lambda (n) ...))
            (list 'if
                (list 'eql 'n (list 'quote n))
                (list 'quote result)
                (caddr '(lambda (n) ...))
            )
        )
        result
    )
)
```

Depois de `(fib 0)`:

```
(lambda (n)
    (if (eql n '0)
        '0
        (let 
            ((result
                (if (< n 2)
                    n
                    (+ (fib (- n 1))
                        (fib (- n 2))
                    )
                )
            ))
            (setcar (cddr '(lambda (n) ...))
                (list 'if
                    (list 'eql 'n (list 'quote n))
                    (list 'quote result)
                    (caddr '(lambda (n) ...))
                )
            )
            result
        )
    )
)
```

Depois de `(fib 1)`:

```
(lambda (n)
    (if (eql n '1)
        '1
        (if (eql n '0)
            '0
            (let 
                ((result
                    (if (< n 2)
                        n
                        (+ (fib (- n 1))
                            (fib (- n 2))
                        )
                    )
                ))
                (setcar (cddr '(lambda (n) ...))
                    (list 'if
                        (list 'eql 'n (list 'quote n))
                        (list 'quote result)
                        (caddr '(lambda (n) ...))
                    )
                )
                result
            )
        )
    )
)
```

Depois de `(fib 40)`:

```
(lambda (n)
    (if (eql n '40)
        '102334155
        (if (eql n '39)
            '63245986
                ...
                    (if (eql n '1)
                        '1
                        (if (eql n '0)
                            '0
                            (let 
                                ((result
                                    (if (< n 2)
                                        n
                                        (+ (fib (- n 1))
                                            (fib (- n 2))
                                        )
                                    )
                                ))
                                (setcar (cddr '(lambda (n) ...))
                                    (list 'if
                                        (list 'eql 'n (list 'quote n))
                                        (list 'quote result)
                                        (caddr '(lambda (n) ...))
                                    )
                                )
                                result
                            )
                        )
                    )
                ...
        )
    )
)
```

It is impossible to debug or compile a program that can be self-modified and doesn't have a stable form. In order to be able to do stuff like this we lose in terms of performance.

# Javassist Intercession

Java from file to JVM:

<img src="Images/Java from file to JVM.png">

Java não nos permite ter intercession diretamente porque as entidades dentro da JVM são imutáveis. O melhor que podemos fazer é ter intercession na fase de tradução do código Java para bytecode. Para fazer isso precisariamos de saber bytecode, ou então usar a biblioteca Javassist, que nos facilita essa tarefa. No entanto, o código que injetamos não é exatamente código java.

- Injected code is described using a template (in a String).
- The template represents either a statement (when it ends with ;) or a block (when it is contained within {}).
- $0 is the receiver (nonexistent for static methods).
- $1,$2,$3, etc, are the method parameters.
- \$$ represent all the parameters of the method.
- $r is the method return type (useful for casts).
- $w is the wrapper type (useful for casts of primitive types).

Disadvantages:
- Templates based on String concatenation are error prone.
- Javassist's compiler is fragile and does not deal with all features of Java (e.g., inner classes, anonymous classes, enums, and generics).
- It is possible to violate Java semantics (wrong return type, lack of type casts, wrong dispatch, etc).
- Manual recompilation of class files is not practical.

### Fibonacci example

Fibonacci with recursion:

```
public class Fib {
    public static Long fib(Long n) {
        if (n < 2) {
            return n;
        }
        else {
            return fib(n - 1) + fib(n - 2);
        }
    }
    
    public static void main(String[] args) {
        System.out.println(fib(Long.parseLong(args[0])));
    }
}
```

Fibonacci with memoization:

```
public class Fib {
    public static Long fib$original(Long n) {
        if (n < 2) {
            return n;
        } 
        else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    static Hashtable cachedResults = new Hashtable();

    public static Long fib(Long n) {
        Object result = cachedResults.get(n);
        if (result == null) {
            result = fib$original(n);
            cachedResults.put(n, result);
        }

        return (Long)result;
    }
}
```

Implementing memoization with Javassist:

```
import javassist.*;
import java.io.*;

public class Memoize {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException {
        if (args.length != 2) {
            System.err.println("Usage: java Memoize <class> <method>");
            System.exit(1);
        } 
        else {
            ClassPool pool = ClassPool.getDefault();
            CtClass ctClass = pool.get(args[0]);
            memoize(ctClass, ctClass.getDeclaredMethod(args[1]));
            ctClass.writeFile();
        }
    }

    static void memoize(CtClass ctClass, CtMethod ctMethod)
        throws NotFoundException, CannotCompileException {
        CtField ctField = CtField.make("static java.util.Hashtable cachedResults = " +
                                       " new java.util.Hashtable();",
                                       ctClass);
        ctClass.addField(ctField);

        String name = ctMethod.getName();
        ctMethod.setName(name + "$original");
        ctMethod = CtNewMethod.copy(ctMethod, name, ctClass, null);
        ctMethod.setBody("{" +
                         " Object result = cachedResults.get($1);" +
                         " if (result == null) {" +
                         "     result = " + name + "$original($$);" +
                         "     cachedResults.put($1, result);" +
                         " }" +
                         " return ($r)result;" +
                         "}");
        ctClass.addMethod(ctMethod);
    }
}
```