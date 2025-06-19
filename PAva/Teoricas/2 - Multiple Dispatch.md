

<!-- toc -->

- [Multiple Dispatch](#multiple-dispatch)
  * [Java](#java)
    + [Double Dispatch](#double-dispatch)
    + [User defined method call mechanism](#user-defined-method-call-mechanism)
  * [C#](#c%23)

<!-- tocstop -->

# Multiple Dispatch

## Java

Em Java, quando temos o seguinte código

```
class Shape {}

class Line extends Shape {}

class Circle extends Shape {}

class Device {
    public void draw(Shape s) {
        System.err.println("draw what where?");
    }

    public void draw(Line l) {
        System.err.println("draw a line where?");
    }

    public void draw(Circle c) {
        System.err.println("draw a circle where?");
    }
}

class Screen extends Device {
    public void draw(Shape s) {
        System.err.println("draw what on screen?");
    }

    public void draw(Line l) {
        System.err.println("drawing a line on screen!");
    }

    public void draw(Circle c) {
        System.err.println("drawing a circle on screen!");
    }
}

class Printer extends Device {
    public void draw(Shape s) {
        System.err.println("draw what on printer?");
    }
    public void draw(Line l) {
        System.err.println("drawing a line on printer!");
    }
    public void draw(Circle c) {
        System.err.println("drawing a circle on printer!");
    }
}
```

ao correr

```
Device[] devices = new Device[] { new Screen(), new Printer() };
Shape[] shapes = new Shape[] { new Line(), new Circle() };
for (Device device : devices) {
    for (Shape shape : shapes) {
        device.draw(shape);
    }
}
```

obtemos:

```
draw what on screen?
draw what on screen?
draw what on printer?
draw what on printer?
```

Ou seja, o Java usa dynamic dispatch para o receiver, e static dispatch para os arguments. Se quisessemos ter Multiple Dispatch, teriamos duas opções: Double Dispatch ou definir um metodo que escolha o metodo que se adeque aos tipos mais "filhos" possíveis.

### Double Dispatch

```
abstract class Device {
    public abstract void draw(Shape s);
}

class Screen extends Device {
    public void draw(Shape s) {
        s.draw(this);
    }
}

class Printer extends Device {
    public void draw(Shape s) {
        s.draw(this);
    }
}

abstract class Shape {
    public abstract void draw(Screen s);
    public abstract void draw(Printer p);
}

class Line extends Shape {
    public void draw(Screen s) {
        System.err.println("drawing a line on screen!");
    }

    public void draw(Printer p) {
        System.err.println("drawing a line on printer!");
    }
}

class Circle extends Shape {
    public void draw(Screen s) {
        System.err.println("drawing a circle on screen!");
    }

    public void draw(Printer p) {
        System.err.println("drawing a circle on printer!");
    }
}
```

Devantagens:

- Mesmo que seja fácil adicionar novas classes que extendam `Shape`, para adicionar uma nova classe que extenda `Device` é preciso adicionar metodos a todos os filhos que extendem `Shape`.
- Se fosse preciso ter triple, quadruple dispatch, ou mais, o número de combinações de metodos aumenta de forma explosiva.

### User defined method call mechanism

```
class Device {
    public void draw(Shape s) {
        invoke(this, "draw", s);
    }

    static Object invoke(Object receiver, String name, Object arg) {
        try {
            Method method = bestMethod(receiver.getClass(), name, arg.getClass());
            return method.invoke(receiver, arg);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    static Method bestMethod(Class type, String name, Class argType) throws NoSuchMethodException {
        try {
            return type.getMethod(name, argType);
        } catch (NoSuchMethodException e) {
            if (argType == Object.class) {
                throw e;
            }
            else {
                return bestMethod(type, name, argType.getSuperclass());
            }
        }
    }

}
```

Desvantagens:

- `getMethod` can only access public methods.
- `bestMethod` can only access public methods with a single parameter.
- We are only dealing with double dispatch.
- We are "climbing" the class hierarchy but not the interface hierarchy.
- We are not dealing with boxing/unboxing (primitive types).
- ...

## C#

Em C# nem sequer há dynamic dispatch para o receiver por default. Se quisermos que o receiver tenha dynamic dispatch temos de adicionar o modifier `virtual` ao metodo da superclass e `override` aos metodos da subclasse.

```
class Device {
    public virtual void Draw(Shape s) {
        Console.WriteLine("draw what where?");
    }

    public virtual void Draw(Line l) {
        Console.WriteLine("draw a line where?");
    }

    public virtual void Draw(Circle c) {
        Console.WriteLine("draw a circle where?");
    }
}

class Screen : Device {
    public override void Draw(Shape s) {
        Console.WriteLine("draw what on screen?");
    }

    public override void Draw(Line l) {
        Console.WriteLine("drawing a line on screen!");
    }

    public override void Draw(Circle c) {
        Console.WriteLine("drawing a circle on screen!");
    }
}

class Printer : Device {
    public override void Draw(Shape s) {
        Console.WriteLine("draw what on printer?");
    }

    public override void Draw(Line l) {
        Console.WriteLine("drawing a line on printer!");
    }

    public override void Draw(Circle c) {
        Console.WriteLine("drawing a circle on printer!");
    }
}
```

Para termos dynamic dispatch nos parametros do metodo, temos de dar cast para `(dynamic)` à variável a que queremos dar dispatch.

```
Device[] devices = new Device[] { new Screen(), new Printer() };
Shape[] shapes = new Shape[] { new Line(), new Circle() };
foreach (Device device in devices) {
    foreach (Shape shape in shapes) {
        device.Draw((dynamic)shape);
    }
}
```

Desvantagem:

- dynamic cast is five times slower than cascaded ifs or Double Dispatch
