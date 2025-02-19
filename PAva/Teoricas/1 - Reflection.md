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
