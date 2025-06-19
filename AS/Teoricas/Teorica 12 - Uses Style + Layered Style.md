

<!-- toc -->

- [Uses Style](#uses-style)
- [Layered Style](#layered-style)

<!-- tocstop -->

# Uses Style

Este style mostra os modulos usados por cada modulo. Define-se a relação _uses_ entre dois modulos como especificação da relação _depends-on_. É um estile super parecido ao que se usa para AMS para a gestão de negócio.

Overview: The uses style shows how modules depend on each other; it is helpful for planning because it helps define subsets and increments of the system being developed.

Elements: Module

Relations: The _uses_ relation, that is a form of _depends-on_ relation.

Constraints: Não tem constraints, mas se estiver presente um loop ou uma longa cadeia de relações _uses_ vai ser mais dificil criar a arquitetura em subsets incrementais.

What it's for:

- Planning incremental development and subsets
- Debugging and testing
- Evaluate the effect of changes

Notations: UML, Dependency Structure Matrix

Relations to other styles: Combina com o layered style usando a relação _allowed-to-use_.

Uma _call_ não é um _uses_, se a chamada for uma notificação, não está a usar nada do outro modulo. Um exemplo pode ser que o alarme toca, isso é como se fosse uma chamada para nós acordarmos, mas é irrelevante para o alarme se a pessoa acorda ou não.
O design pattern Observer é uma implementação do _uses_ style porque fica a observar o comportamento de um modulo, e recebe notificações desse modulo.

# Layered Style

Serve para dividir o sistema em unidades, neste caso em layers. Cada layer tem um conjunto de modulos com um cohesive conjunto de serviços. As camadas são criadas para que cada layer só possa usar as camadas debaixo (em alguns casos apenas a camada imediatamente abaixo). As layers têm uma relação _allowed-to-use_ para as layers abaixo.

Overview: The layered style puts together layers (groupings of modules that offer a cohesive set of services) in a unidirectional allowed-to-use relation with each other.

Elements: Layer. Que contém certos modulos.

Relations: _allowed-to-use_. Especialização da relação _depends-on_.

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
- _uses_ Style

Para enviar mensagens de baixo para cima usam-se callbacks. Uma layer recebe uma função e quando terminar de a processar avisa a camada de cima.
É possível ter várias presentation layers, uma para um web browser e outra para uma mobile app. Também é possível mover de uma database postgres para uma MySQL.

Cada layer determina uma Virtual Machine. Cada camada fornece tudo aquilo que a camada de cima precisa, assim não é preciso andar a descer até à camada mais funda.
