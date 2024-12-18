# Managing Transactions with Saga

## Transaction Management in a Microservice Architecture
Transaction management is straightforward in monolithic architectures due to the use of ACID transactions. However, in microservices, each service has its database, making transactions span multiple services, which complicates maintaining data consistency. Traditional distributed transactions, such as those using the XA protocol and two-phase commit (2PC), are not viable because they are incompatible with many modern systems (e.g., NoSQL databases and message brokers) and reduce system availability.

To overcome these challenges, the Saga pattern is used. A saga is a sequence of local transactions, each confined to a single service and coordinated asynchronously. Sagas differ from ACID transactions as they lack isolation and require compensating transactions to rollback changes when errors occur.

## Coordinating Sagas

Sagas can be coordinated using two approaches:

- Choreography: This decentralized approach involves saga participants subscribing to and responding to each other's events. For instance, an "Order Created" event from the Order Service might trigger the Consumer Service to verify customer details.
- Orchestration: A centralized saga orchestrator explicitly coordinates the steps of the saga by sending commands to the participants.

Each approach has its advantages and disadvantages. Choreography simplifies participant logic but can lead to complex event chains, while orchestration centralizes control but risks overloading the orchestrator with business logic.

## Handling the Lack of Isolation

Unlike traditional ACID transactions, sagas lack isolation. Changes from a saga's local transactions become immediately visible to other sagas, potentially causing anomalies:
- Lost Updates: One saga overwrites changes made by another without acknowledging them.
- Dirty Reads: A saga reads uncommitted changes from another.
- Fuzzy/Nonrepeatable Reads: A saga reads inconsistent data because other sagas have made updates mid-execution.

These issues necessitate countermeasures to minimize business risks. One such countermeasure is the Semantic Lock, which involves marking records being modified by a saga to signal other sagas to proceed cautiously. For example, in the "Create Order Saga," the initial step sets the state of an order to `APPROVAL_PENDING`, signaling that it's in the process of being modified. Semantic locks emulate transaction isolation and help serialize sagas that update the same records, reducing anomalies and conflicts.