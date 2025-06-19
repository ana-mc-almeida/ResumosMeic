# Generalization Style

Este style serve para sistemas com herança. Ou seja, para quando há relação _is-a_.

Overview: The generalization style employs the is-a relation to support extension and evolution of architectures and individual elements. Modules in this style are defined in such a way that they capture commonalities and variations.

Elements: Module. A module can have the "abstract" property to indicate it does not contain a complete implementation.

Relations: Generalization, which is a specialization of the _is-a_ relation. The relation can be further specialized to indicate, for example, if it is class inheritance, interface inheritance, or interface realization.

Constraints:

- A module can have multiple parents, although multiple inheritance is often considered a dangerous design approach.
- Cycles in the generalization relation are not allowed; that is, a child module cannot be a generalization of one or more of its ancestor modules in a view.

What it's for:

- Expressing inheritance in object-oriented designs
- Incrementally describing evolution and extension
- Capturing commonalities, with variations as children
- Supporting reuse

Notations: UML

Relation to Other Styles: _uses_ style and module decomposition.

# Aspects Style

Serve para identificar os modulos responsáveis por crosscutting concerns. Estes modulos chamam-se aspects. Num sistema de um banco há o modulo Account que para além de ter as suas responsabilidades básicas, por exemplo retirar ou depositar dinheiro, também escreve logs da sua atividade. O modulo relacionado ao logging seria um aspect.

Overview: The aspects style shows aspect modules that implement crosscutting concerns and how they are bound to other modules in the system.

Elements: Aspect, which is a specialized module that contains the implementation of a crosscutting concern.

Relations: Crosscuts, which binds an aspect module to a module that will be affected by the crosscutting logic of that aspect.

Constraints:

- An aspect can crosscut one or more regular modules as well as aspect modules.
- An aspect that crosscuts itself may cause infinite recursion, depending on the implementation.

What It's For:

- Modeling crosscutting concerns in object-oriented designs
- Enhancing modifiability

Notations: UML

Relation to other styles: Generalization Style
