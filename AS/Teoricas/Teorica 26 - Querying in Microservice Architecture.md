# Querying Using the API Composition Pattern

Purpose: This pattern is used to implement queries that require retrieving data from multiple services.

Mechanism:

- A query operation (e.g., findOrder()) involves aggregating data from various services by invoking their APIs.
- An API composition module is responsible for orchestrating these API calls and assembling the results into a cohesive response for the client.

Design Issues:

- Performance: Increased latency from multiple network calls.
- Error Handling: Requires robust mechanisms for partial failures if one service is unavailable or responds slowly.
- Complexity: The composition logic can become intricate for queries involving many services.

Benefits and Drawbacks:

- Benefits: Simplifies services by isolating querying logic in the composition layer.
- Drawbacks: Can lead to significant overhead in network communication and may not scale well for highly complex queries.

# Querying Using the CQRS Pattern

Command Query Responsibility Segregation (CQRS): Separates the responsibility for modifying data (commands) and reading data (queries).

Motivations:

- Simplifies scalability and optimization for queries by using dedicated read models.
- Avoids the need to overload transactional systems with complex query requirements.

Mechanism:

- Commands update the state in the write model.
- Changes are propagated to a read model (using techniques like event sourcing) that is optimized for querying.

Benefits:

- Improved query performance and scalability due to tailored read models.
- Flexibility in using different database technologies for the read and write sides.

Drawbacks:

- Increased complexity in maintaining separate read and write models.
- Potential data consistency challenges due to asynchronous propagation of updates to the read model.
