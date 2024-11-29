# Decomposition Style

O decomposition style pega nos modules e decompõem-nos em partes mais pequenas. A decomposição de um modulo pode ser para:
- Atingir Quality Attributes: Por exemplo, para suportar modifiability divide-se um modulo em duas partes, a implementação e interface.
- Build-versus-buy decisions: Algumas partes do modulo podem ser usadas de outras fontes, por exemplo de open-source software ou de projetos antigos.
- Product line implementation: Para acelerar a produção podemos distinguir os modulos comuns dos mais diferentes.
- Team allocation: Para acelerar o desenvolvimento equipas de desenvolvimento diferentes trabalham em modulos diferentes em paralelo.

### Resumo

Overview: The decomposition style is used for decomposing a system into units of implementation. A decomposition view describes the organization of the code as modules and submodules and shows how system responsibilities are partitioned across them.

Elements: Modules

Relations: Decomposition relation, which is a form of the is-part-of relation. The documentation should specify the criteria used to define the decomposition.

Constraints:
- No loops in the decomposition graph.
- A module can have only one parent.

What it's for:
- To support the learning process about a system.
- To provide input for work assignment
- To reason about localization of changes

It is possible to map between a decomposition view and one or more component-and-connector views.

# Data Model

São os gráficos que se usam para representar databases.

Podem ser usados por vários motivos:
- Conceptual: Só queremos representar conceitos e as relações entre eles.
- Logical: A evolução da representação conceptual é uma representação lógica
- Fisica: Preocupação com a implementação das data entities.

### Resumo

Overview: The data model describes the structure of the data entities and their relationships.

Elements: Data entity, which is an object that holds information that needs to be stored or somehow represented in the system. Properties include name, data attributes, primary key, and rules to grant users permission to access the entity.

Relations:
- One-to-one, one-to-many, many-to-one, many-to-many.
- Generalization / Specification.
- Aggregation -> Transforma uma relação numa aggregate entity.

Constraints: Functional dependencies should be avoided

What it's for:
- Describing the structure of the data used in the system
- Performing impact analysis of changes to the data model; extensibility analysis
- Enforcing data quality by avoiding redundancy and inconsistency
- Guiding implementation of modules that access the data

Propriedades das entidades:
- Nome
- Description
- Lista de atributos -> Um carro tem como atributos o ano, a marca, preço, etc
- Primary key
- Se é uma weak entity, isto é, precisa de outra entidade para viver. OrderItem precisa de PurchaseOrder para existir
- Constraints dos atributos, por exemplo a data de chegada não pode ser antes da data de partida 
- Permissões
- Numero de entidades esperado e taxa de crescimento

Notations: ERD (Entity-Relation-Diagram) ou UML

Relation to other styles: Um Data Model está ligado a uma module view porque os dados precisam de ser usados por alguém.