

<!-- toc -->

- [Microservices](#microservices)
  * [Microservice architectural style](#microservice-architectural-style)

<!-- tocstop -->

# Microservices

## Microservice architectural style

Structures a software application as a collection of small, independently deployable services. Each service operates in its own process and communicates with others through lightweight mechanisms, often HTTP resource APIs. This approach emphasizes organizing services around business capabilities, enabling teams to develop, deploy, and scale services independently.

Key characteristics of microservices include:

- Componentization via Services: Unlike traditional monolithic architectures that use in-process function calls, microservices treat each service as a separate component accessed over a network. This separation allows for independent development and deployment.
- Organized Around Business Capabilities: Teams are structured cross-functionally, each responsible for a specific business capability, from user interface to data storage.
- Products Not Projects: Development teams own their services throughout the entire lifecycle, promoting a product mindset over a project-based approach.
- Smart Endpoints and Dumb Pipes: Microservices focus on delivering smart endpoints that process information and apply logic, while using simple messaging protocols for communication, avoiding complex integration platforms.
- Decentralized Governance and Data Management: Teams have the autonomy to choose appropriate tools and technologies, fostering innovation and flexibility. Data is decentralized, with each service managing its own database, enhancing modularity and scalability.
- Infrastructure Automation: Practices like continuous delivery and deployment automation are essential, enabling rapid and reliable releases.
- Design for Failure: Microservices are designed to handle failure gracefully, ensuring that the system remains resilient even when individual services fail.
- Evolutionary Design: The architecture supports incremental changes, allowing the system to evolve and adapt over time without significant rewrites.

Microservices are related with service-oriented architecture (SOA), but while they share similarities, microservices adopt a more decentralized and granular approach.

Although microservices offer numerous benefits, they also introduce complexities. Therefore, organizations should carefully assess their specific needs and capabilities before adopting this architectural style.
