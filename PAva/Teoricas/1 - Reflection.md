

<!-- toc -->

    + [Computational System](#computational-system)
    + [Computational Meta-System](#computational-meta-system)
- [Reflection](#reflection)
    + [Reflective System](#reflective-system)
    + [Introspection](#introspection)
    + [Intercession](#intercession)
  * [Reification](#reification)
- [Emacs Lisp Intercession](#emacs-lisp-intercession)
  * [traced-lambda example](#traced-lambda-example)
  * [memoize example](#memoize-example)
- [Javassist Intercession](#javassist-intercession)
    + [Fibonacci example](#fibonacci-example)
  * [Intercession at Load Time](#intercession-at-load-time)
  * [Intercession at Run Time](#intercession-at-run-time)

<!-- tocstop -->

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

Nota: O `let` tem o seu primeiro argumento separador por dois parentenses porque esse argumento representa as bindings, é possível ter mais que uma binding<br>
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

O último result serve para retornar na mesma o valor de `fib`, para além de modificar o código.

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

## Intercession at Load Time

Podemos melhorar o programa para permitir que corra logo após modificar o bytecode sem termos de dar overwrite ao ficheiro .class em disco.

```
import javassist.*;
import java.io.*;
import java.lang.reflect.*;

public class MemoizeAndRun extends Memoize {
    public static void main(String[] args) throws ... {
        if (args.length < 2) {
            ...
        }
        else {
            ClassPool pool = ClassPool.getDefault();
            CtClass ctClass = pool.get(args[0]);
            memoize(ctClass, ctClass.getDeclaredMethod(args[1]));
            Class<?> rtClass = ctClass.toClass();
            Method main = rtClass.getMethod("main", args.getClass());
            String[] restArgs = new String[args.length - 2];
            System.arraycopy(args, 2, restArgs, 0, restArgs.length);
            main.invoke(null, new Object[] { restArgs });
        }
    }
}
```

O problema é que é dificil de usar o programa de memoization e é dificil memoizar vários metodos ao mesmo tempo. Para resolver isso, usamos annotations:

```
public class Fib {

    @Memoized
    public static Long fib (Long n) {
        if (n < 2) {
            return n;
        }
        else {
            return fib(n - 1) + fib(n - 2);
        }
    }
    ...
}
```

Annotation Definition, exemplo para multi value para os argumentos:

```
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Foo {
    String bar();
    long baz();
}
```

Use:

```
public class C1 {
@Foo(bar="Hello World", baz=100)
    public void m1(int a, long b) {
        ...
    }
}
```

As annotations usadas para definir novas annotations chamam-se meta-annotations:

Pre-defined annotations - Meta-annotations - @Target

- ElementType.TYPE
- ElementType.FIELD
- ElementType.METHOD
- ElementType.PARAMETER
- ElementType.CONSTRUCTOR
- ElementType.LOCAL_VARIABLE
- ElementType.ANNOTATION_TYPE

Pre-defined annotations - Meta-annotations - @Retention

- RetentionPolicy.SOURCE (Mantêm-se durante compilação)
- RetentionPolicy.CLASS (Mantêm-se durante load time)
- RetentionPolicy.RUNTIME (Mantêm-se durante runtime)

Usando annotated methods ficamos com:

```
import javassist.*;
import java.io.*;
import java.lang.reflect.*;

public class MemoizeAndRun extends Memoize {
    public static void main(String[] args) throws ... {
        if (args.length < 2) {
            ...
        }
        else {
            ClassPool pool = ClassPool.getDefault();
            CtClass ctClass = pool.get(args[0]);
            memoizeMethods(ctClass);
            Class<?> rtClass = ctClass.toClass();
            Method main = rtClass.getMethod("main", args.getClass());
            String[] restArgs = new String[args.length - 1];
            System.arraycopy(args, 1, restArgs, 0, restArgs.length);
            main.invoke(null, new Object[] { restArgs });
        }
    }

    static void memoizeMethods(CtClass ctClass) throws ... {
        for (CtMethod ctMethod : ctClass.getDeclaredMethods()) {
            Object[] annotations = ctMethod.getAnnotations();
            if ((annotations.length == 1) && (annotations[0] instanceof Memoized)) {
                memoize(ctClass, ctMethod);
            }
        }
    }
}
```

E, como precisamos de uma hashtable para cada metodo memoized, alteramos também o metodo `memoize` da class `Memoize`:

```
public class Memoize {
    static void memoize(CtClass ctClass, CtMethod ctMethod) throws NotFoundException, CannotCompileException {
        String name = ctMethod.getName();
        CtField ctField = CtField.make(
            "static java.util.Hashtable " + name + "Results = " +
            " new java.util.Hashtable();",
            ctClass);
        ctClass.addField(ctField);
        ctMethod.setName(name + "$original");
        ctMethod = CtNewMethod.copy(ctMethod, name, ctClass, null);
        ctMethod.setBody(
            "{" +
            "   Object result = " + name + "Results.get($1);" +
            "   if (result == null) {" +
            "       result = " + name + "$original($$);" +
            "       " + name + "Results.put($1, result);" +
            "   }" +
            "   return ($r)result;" +
            "}");
        ctClass.addMethod(ctMethod);
    }
}
```

Problemas:

- Só dá para memoizar uma classe.
- Temos de especificar qual é a classe.
- O memoizer não consegue memoizar classes automaticamente.

Para resolver isto temos de especializar o Class Loader. O Class Loader do Javassist funciona da seguinte forma:

```
import javassist.*;
import Foo;

public class Main {
    public static void main(String[] args) throws Throwable {
        ClassPool pool = ClassPool.getDefault();
        //Create Javassist class loader
        Loader classLoader = new Loader(pool);
        //Obtain the compile time class Foo
        CtClass ctFoo = pool.get("Foo");
        //Modify class Foo
        ...
        //Obtain the run time class Foo
        Class rtFoo = classLoader.loadClass("Foo");
        //Instantiate Foo
        Object foo = rtFoo.newInstance();
        ...
    }
}
```

É possível associar _Listeners_ ao Class Loader do Javassist. Os Listeners são notificados:

- quando são adicionados ao Class Loader (method `start`)
- quando a classe está prestes a ser loaded (method `onLoad`)

Listeners implement interface javassist.Translator:

```
public interface Translator {
    public void start(ClassPool pool) throws NotFoundException, CannotCompileException;
    public void onLoad(ClassPool pool, String classname) throws NotFoundException, CannotCompileException;
}
```

E ficamos com:

```
public class MyTranslator implements Translator {
    void start(ClassPool pool) throws NotFoundException, CannotCompileException {
        // Do nothing
    }

    void onLoad(ClassPool pool, String className) throws NotFoundException, CannotCompileException {
        // Obtain the compile time class
        CtClass ctClass = pool.get(className);
        try {
            memoizeMethods(ctClass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // That's all. The class will now be automatically
        // loaded from the modified byte code
    }

    void memoizeMethods(CtClass ctClass) throws NotFoundException, CannotCompileException, ClassNotFoundException {
        for (CtMethod ctMethod : ctClass.getDeclaredMethods()) {
            Object[] annotations = ctMethod.getAnnotations();
            if ((annotations.length == 1) && (annotations[0] instanceof Memoized)) {
                memoize(ctClass, ctMethod);
            }
        }
    }

}
```

```
public class MemoizeAndRun {
    public static void main(String[] args) throws ... {
        if (args.length < 1) {
            ...
        }
        else {
            Translator translator = new MemoizeTranslator();
            ClassPool pool = ClassPool.getDefault();
            Loader classLoader = new Loader();
            classLoader.addTranslator(pool, translator);
            String[] restArgs = new String[args.length - 1];
            System.arraycopy(args, 1, restArgs, 0, restArgs.length);
            classLoader.run(args[0], restArgs);
        }
    }
}
```

## Intercession at Run Time

O Javassist também nos permite criar novas classes em Run Time

```
ClassPool pool = ClassPool.getDefault();
CtClass ctFoo = pool.makeClass("Foo");
ctFoo.setSuperclass(...);
...
ctFoo.addField(...);
...
ctFoo.addMethod(...);
...
Class rtFoo = ctFoo.toClass();
```

Por exemplo, conseguimos criar um Evaluator

```
import javassist.*;
import java.lang.reflect.*;

public class Evaluator {
    public static void main (String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctEvaluator = pool.makeClass("Eval");
        String expression = args[0];
        String template =
            "public static double eval () { " +
            " return ("+ expression +");" +
            "},";
        CtMethod ctMethod = CtNewMethod.make(template, ctEvaluator);
        ctEvaluator.addMethod(ctMethod);
        Class evaluator = ctEvaluator.toClass();
        Method meth = evaluator.getDeclaredMethod("eval");
        System.out.println(meth.invoke(null));
    }
}
```

A maior preocupação que temos de ter é usar o mesmo nome quando criamos o metodo (`template`) e quando o obtemos de volta (`meth`).

Nesta versão temos o problema de que podemos apenas availar uma expressão de cada vez e as expressões não podem ter espaços, porque contariam como vários argumentos. Para resolver isso implementamos um REPL:

```
public static void main (String[] args) throws Exception {
    ClassPool pool = ClassPool.getDefault();
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    for(int i = 0; true; i++) {
        System.out.print("> ");
        String expression = input.readLine();
        CtClass ctEvaluator = pool.makeClass("Eval" + i);
        String template =
            "public static double eval () { " +
            " return ("+ expression +");" +
            "},";
        CtMethod ctMethod = CtNewMethod.make(template, ctEvaluator);
        ctEvaluator.addMethod(ctMethod);
        Class evaluator = ctEvaluator.toClass();
        Method meth = evaluator.getDeclaredMethod("eval");
        System.out.println(meth.invoke(null));
    }
}
```

Como não podemos modificar classes quando estamos na JVM, temos de criar uma nova classe, e para garantir que os nomes são diferentes criamos o nome com o index da execução com sufixo.
