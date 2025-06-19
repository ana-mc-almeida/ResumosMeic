# Componenet and Connector Views

Uma C&C view mostra elementos em runtime, por exemplo processos, clients, servers, e data stores. Estes elementos são chamados de components. E mostra connectors que são elementos que mostram interação entre componentes, por exemplo communication links, protocolos e acessos a shared storage.

Elements:

- Components: principal processing units and data stores. A component has a set of ports through which it interacts with other components (via connectors).
- Connectors: pathways of interaction between components. Connectors have a set of roles that indicate how components may use a connector in interactions.

Relations:

- Attachments: component ports are associated with connector roles to yield a graph of components and connectors.
- Interface delegation: in some situations component ports are associated with one or more ports in an "internal" subarchitecture. Similarly for the roles of a connector.

Constraints:

- Components can be attached only to connectors, not other components.
- Connectors can be attached only to components, not other connectors.
- Attachments can be made only between compatible ports and roles.
- Interface delegation can be defined only between two compatible ports (or two compatible roles).
- Connectors cannot appear in isolation; a connector must be attached to a component.

What C&C Views Are For:

- Showing how the system works.
- Guiding development by specifying the structure and behavior of runtime elements.
- Helping architects and others to reason about runtime system quality attributes, such as performance, reliability, and availability.
