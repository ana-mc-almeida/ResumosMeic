# Integration Testing

Software systems are built with components that must interoperate, integration testing is useful to reveal component interoperability faults.

Harder to locate bugs because each test case may involve several components, it is hard to locate the faulty component and understand the cause of failure.

### Requirements

- Each component was tested isolated.
- The communication among components and with the SUT was tested.

### Object-oriented development

Integration in object-oriented development takes place at all scopes:
- Within a class.
- Within a class hierarchy.
- Between a client and its server.

### Integration faults

- Configuration/version control problems.
- Missing, overlapping, or conflicting functions.
- The client send a message that violates the server's precondition or sequential constraints.
- Wrong object bound to message (polymorphic target).
- Wrong parameters, or incorrect parameter values.
- Incorrect usage of virtual machine, ORB, or OS services.

### Integration strategies

- Big Bang Integration: Attempt to demonstrate system stability by testing all components at the same time.
- Bottom-up Integration: Interleave component and integration testing by following usage dependencies.
- Top-down Integration: Interleave component and integration testing by following application control hierarchy.
- Sandwich Integration: Use Top-down and Bottom-up.
- Collaboration Integration: Choose an order of integration according to collaborations and their dependencies.
- Layer Integration: Incrementally exercises interfaces and components in a layered architecture.
- Backbone Integration: Combine Top-down Integration, Bottom-up Integration and Big Bang Integration to reach a stable system that will support iterative development.
- Client/Server Integration: Exercise a loosely coupled network of components, all of which use a common server component.
- Distributed Services Integration: Exercise a loosely coupled network of peer-to-peer components.
- High-frequency Integration: Develop and rerun an integration test suite test hourly, daily or weekly.

---

### Drive definition

A program that calls the interface procedures of the module being tested and verifies the results.
- A driver simulates a client that calls the SUT.
- Corresponds to the execution of a test suite.

---

## Big Bang Integration

The intent is to demonstrate stability by attempting to exercise an entire system with a few test runs.

### Strategy

- Dispenses incremental integration testing.
- The entire system is built, and a test suite is applied to demonstrate minimal operability at system scope.
- Test suit may be developed at system scope by using an appropriate responsibility-based test design pattern.

### Consequences

- Debugging can be difficult because you receive fewer clues about fault locations.
- Even if SUT passes, many interface faults can hide and waylay subsequent system scope testing.
- Integration testing cannot be started until all modules are completely created and tested.
- Under favorable circumstances it can result in quick completion of integration testing.
- Few (if any) integration drivers or stubs are developed.

## Incremental integration 

Incremental integration is the most effective technique. Add components a few at a time and then test their interoperability.

A sequence of components should be identified using careful analysis of component dependencies. Integration testing must be planned and managed to follow these dependencies.

## Bottom-up Integration

The intent is to demonstrates stability by adding components to the SUT in uses-dependency order, beginning with components having the fewest dependencies.

### Strategy

We need to have the dependency tree and the responsability for each component.

1. for (n = leaf level; n < root level; n++)
    1. Code all components of level n
    2. Develop responsibility test suite for each component in level n
    3. Exercise test suites of level n

### Consequences

Disadvantages:
- Driver development is the most significant cost.
- The driver does not directly exercise intercomponent interfaces.
- Postpones checking critical control interfaces and collaborations until the end of the development cycle.

Advantages:
- May begin as soon as any leaf-level component is ready.
- Work may proceed in parallel.
- Although this pattern reduces stubbing, stubs may still be needed to break a cycle or simulate exceptions.
- It is suitable to responsibility-based design.

## Top-down integration

The intent is to demonstrate stability by adding components to the SUT in control hierarchy order, beginning with the top-level control objects.

Control objects typically implement essential and nontrivial control strategies and therefore present relatively high risk. Top-down integration focuses on control components first, making the demonstration of system scope end-to-end operability a high priority.

It solves the disadvantage "Postpones checking critical control interfaces and collaborations until the end of the development cycle." from Bottom-up integration.

### Strategy

We need a diagram to be able to have the hierarchy control.

1. Model the control hierarchy as a dependency tree.
2. Develop a staged plan for implementation and testing
3. Design a responsibility-based test suite at system scope.
    1. Develop and test the component(s) at the highest level of control first.
    2. Continue in breadth-first swath at each level, replacing the server stubs with a full implementation.

### Consequences

Disadvantages:
- Setting up a test requires that many stubs be coded to provide the desired response.
- An unforeseen requirement in a lower-level component may necessitate last-minute changes to many top components, breaking part of the test suite, or the design may not be optimal. (The exactly oposite of the problem we were trying to solve from Bottom-up)
- The stubs are necessarily implementation-specific and likely to be brittle.
- It may not be easy to exercise the lower-level components sufficiently.
- Can have a small bang when integrating the components of a layer.

Advantages:
- Control components are developed and integrated early.
- Testing and integration may begin early.
- The cost of driver development is reduced.
- Components may be developed in parallel with testing.

## Sandwich Integration Testing

The intent is to demonstrate stability by adding components incrementally using both Top-down and Bottom-up approaches.

By combining both we can avoid their disadvantages.

### Strategy

1. Select a layer in the dependency tree designated as the target layer that is usually near the middle.
2. Integrate the components incrementally. The layers above the target are integrated using the top-down approach. The layers bellow the target are integrated using the bottom-up approach. Testing converges at the target layer.


### Consequences

- Integration can be faster.
- Top and bottom layers can be tested in parallel.
- Can reduce the need for stubs and drivers.
- It can be more complex to plan.

## Layer Integration

The intent is to use an incremental approach to verify stability in a layered architecture.

### Strategy

1. Develop each layer in isolation.
    - Since each layer can have multiple components, we may also need to use an integration technique to test them.
2. Do Layer integration.
    - Top-down or bottom-up.

### Consequences

Advantages and disadvantages are the same as for Top-down Integration (for the top-down variant) or Bottom-up Integration (for the bottom-up variant).

## Client/Server Integration

The intent is to demonstrate stability of client/server interaction. Begin by testing clients and servers in isolation and then use controlled increases in scope until all interfaces have been exercised.

### Strategy

We need to identify clients and servers and to exercise all combinations of clients and servers.

1. Each client is tested with a stub for the server.
2. The server is tested with stubs for all client types.
3. Test actual pairs of clients and servers while the server may retain stubs for other clients.
4. Finally, all stubs are removed, and the individual use cases are replayed.

### Consequences

Disadvantages:
- Cost of driver and stub development for clients and servers-
- Cannot exercise end-to-end use cases until midway or late in the testing cycle.
- The number of potential clients can be huge. In this case we need to consider group of clients as representative client.

Advantages:
- Avoids big bang integration problems.
- Order can be sequenced according to priority or risk.
- Client and server integration order has few constraints.
- Approach supports controllable and repeatable testing.
