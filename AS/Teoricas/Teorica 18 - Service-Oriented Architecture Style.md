# Service-Oriented Architecture Style

### Overview

Service-oriented architectures consist of a collection of distributed components that provide and/or consume services. In SOA, service provider components and service consumer components can use different implementation languages and platforms. Services are largely standalone: service providers and service consumers are usually deployed independently, and often belong to different systems or even different organizations.

### Elements

- Service providers, which provide one or more services through published interfaces. Properties will vary with the implementation technology (such as EJB or ASP.NET) but may include performance, authorization constraints, availability, and cost. In some cases these properties are specified in a service-level agreement (SLA).
- Service consumers, which invoke services directly or through an intermediary.
- ESB, which is an intermediary element that can route and transform messages between service providers and consumers.
- Registry of services, which may be used by providers to register their services and by consumers to query and discover services at runtime.
- Orchestration server, which coordinates the interactions between service consumers and providers based on scripts that define business workflows.
- SOAP connector, which uses the SOAP protocol for synchronous communication between Web services, typically over HTTP. Ports of components that use SOAP are often described in WSDL.
- REST connector, which relies on the basic request/reply operations of the HTTP protocol.
- Messaging connector, which uses a messaging system to offer point-to-point or publish-subscribe asynchronous message exchanges.

### Relations

- Attachment of the different kinds of ports available to the respective connectors.

### Computational Model

Computation is achieved by a set of cooperating components that provide and/or consume services over a network. The computation is often described as a kind of workflow model.

### Constraints

- Service consumers are connected to service providers, but intermediary components (such as ESB, registry, or BPEL server) may be used.
- ESBs lead to a hub-and-spoke topology.
- Service providers may also be service consumers.
- Specific SOA patterns impose additional constraints.

### What It's For

- Allowing interoperability of distributed components running on different platforms or across the Internet.
- Integrating legacy systems.
- Allowing dynamic reconfiguration.
