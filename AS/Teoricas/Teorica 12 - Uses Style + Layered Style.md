# Uses Style

Este style mostra os modulos usados por cada modulo. Define-se a relação *uses* entre dois modulos como especificação da relação *depends-on*. É um estile super parecido ao que se usa para AMS para a gestão de negócio.

Overview: The uses style shows how modules depend on each other; it is helpful for planning because it helps define subsets and increments of the system being developed.

Elements: Module

Relations: The *uses* relation, that is a form of *depends-on* relation.

Constraints: Não tem constraints, mas se estiver presente um loop ou uma longa cadeia de relações *uses* vai ser mais dificil criar a arquitetura em subsets incrementais.

What it's for:
- Planning incremental development and subsets
- Debugging and testing
- Evaluate the effect of changes

Notations: UML, Dependency Structure Matrix

Relations to other styles: Combina com o layered style usando a relação *allowed-to-use*.

# Layered Style

Serve para dividir o sistema em unidades, neste caso em layers. Cada layer tem um conjunto de modulos com um cohesive conjunto de serviços. As camadas são criadas para que cada layer só possa usar as camadas debaixo (em alguns casos apenas a camada imediatamente abaixo). As layers têm uma relação *allowed-to-use* para as layers abaixo.

Overview: The layered style puts together layers (groupings of modules that offer a cohesive set of services) in a unidirectional allowed-to-use relation with each other.

Elements: Layer. Que contém certos modulos.

Relations: *allowed-to-use*. Especialização da relação *depends-on*.

Constraints:
- Every piece of software is allocated to exactly one layer.
- There are at least two layers (typically three or more).
- The allowed-to-use relations should not be circular, that is, a lower layer cannot use a layer above.

What it's for:
- Promoting modifiability and portability
- Managing complexity and facilitating the communication of the code structure to developers
- Promoting reuse
- Achieving separation of concerns

Notations: Stack, Segmented Layers, Rings, Layers with sidecar, Size and Color, UML.

Relation to other styles:
- Module Decomposition
- Tiers
- *uses* Style