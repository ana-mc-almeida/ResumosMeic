# Generalization Style

Este style serve para sistemas com herança. Ou seja, para quando há relação *is-a*.

Overview: The generalization style employs the is-a relation to support extension and evolution of architectures and individual elements. Modules in this style are defined in such a way that they capture commonalities and variations.

Elements: Module. A module can have the "abstract" property to indicate it does not contain a complete implementation.

Relations: Generalization, which is a specialization of the *is-a* relation. The relation can be further specialized to indicate, for example, if it is class inheritance, interface inheritance, or interface realization.

Constraints: 
- A module can have multiple parents, although multiple inheritance is often considered a dangerous design approach.
- Cycles in the generalization relation are not allowed; that is, a child module cannot be a generalization of one or more of its ancestor modules in a view.

What it's for:
- Expressing inheritance in object-oriented designs
- Incrementally describing evolution and extension
- Capturing commonalities, with variations as children
- Supporting reuse

Notations: UML

Relation to Other Styles: *uses* style and module decomposition.