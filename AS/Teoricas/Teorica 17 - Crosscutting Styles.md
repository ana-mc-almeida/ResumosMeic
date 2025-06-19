# Crosscutting Issues for C&C Styles

## Concurrency

- Systems may require components to run as concurrent threads or processes. This issue is common in most C&C styles and can be addressed by adding semantic details to the component types to indicate how they handle concurrency.
- Dynamic Reconfiguration: Indicates which components can be created or destroyed during runtime, introducing changes in system topology and requiring specific management.

## Communicating Processes:

- Distributed systems often require processes or threads to communicate. This is especially important in systems like client-server architectures.
- Styles that incorporate communicating processes need to document the synchronization and scheduling of processes, their execution precedence, and their handling of shared resources and deadlocks.
- Behavioral notations (e.g., activity diagrams) help in understanding the interactions among processes.

## Tiers

- Systems can be organized into logical groupings, termed "tiers," which are used to separate components that might run in different environments or require different execution contexts (e.g., client-server systems).
- iers impose constraints on which components can communicate with one another and can be graphically represented in C&C diagrams.

## Dynamic Creation and Destruction

- Many C&C styles allow the creation or destruction of components during runtime, which can affect the system's structure and behavior. This is especially important in systems with fluctuating component requirements, such as peer-to-peer systems.
