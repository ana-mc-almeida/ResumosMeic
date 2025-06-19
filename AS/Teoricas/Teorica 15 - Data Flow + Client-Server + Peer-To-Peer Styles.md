# Data Flow Style

Os componentes partilham informação entre eles e os connectors servem para fazer a transferencia de dados de um lado para o outro.

Daqui surge o Pipe-and-Filter style:

Elements:

- Filter, which is a component that transforms data read on its input ports to data written on its output ports. Filters typically execute concurrently and incrementally. Properties may specify processing rates, input/output data formats, and the transformation executed by the filter.
- Pipe, which is a connector that conveys data from a filter's output ports to another filter's input ports. A pipe has a single data-in and a single data-out role, preserves the sequence of data items, and does not alter the data passing through. Properties may specify buffer size, protocol of interaction, and data format that passes through a pipe.

Relations: The attachment relation associates filter output ports with data-in roles of a pipe, and filter input ports with data-out roles of pipes.

Computational Model: Data is transformed from a system's external inputs to its external outputs through a series of transformations performed by its filters.

Constraints

- Pipes connect filter output ports to filter input ports.
- Connected filters must agree on the type of data being passed along the connecting pipe.
- Specializations of the style may restrict the association of components to an acyclic graph or a linear sequence-sometimes called a pipeline.
- Other specializations may prescribe that components have certain named ports, such as the stdin, stdout, and stderr ports of UNIX filters.

What It's For:

- Improving reuse due to the independence of filters.
- Improving throughput with parallelization of data processing.
- Simplifying reasoning about overall behavior.

# Client-Server Style

Vários clientes comunicam com um servidor através de um connector request/reply.

Elements:

- Client, which is a component that invokes services of a server component.
- Server, which is a component that provides services to client components. Properties will vary according to concerns of the architect but typically include information about the nature of the server ports (such as how many clients can connect) and performance characteristics (such as maximum rates of service invocation).
- Request/reply connector, which is used by a client to invoke services on a server. Request/reply connectors have two roles: a request role and a reply role. Connector properties may include whether the calls are local or remote, and whether data is encrypted.

Relations: The attachment relation associates client service-request ports with the request role of the connector and server service-reply ports with the reply role of the connector.

Computational Model: Clients initiate interactions, invoking services as needed from servers and waiting for the results of those requests.

Constraints:

- Clients are connected to servers through request/reply connectors.
- Server components can be clients to other servers.
- Specializations may impose restrictions:
- Numbers of attachments to a given port
- Allowed relations among servers
- Components may be arranged in tiers.

What It's For:

- Promoting modifiability and reuse by factoring out common services.
- Improving scalability and availability in case server replication is in place.
- Analyzing dependability, security, and throughput.

# Peer-To-Peer Style

Dois computadores comunicam entre si sem a necessidade de um servidor para controlar a comunicação.

Elements:

- Peer component.
- Call-return connector, which is used to connect to the peer network, search for other peers, and invoke services from other peers.

Relations: The attachment relation associates peers with call-return connectors.

Computational Model: Computation is achieved by cooperating peers that request services of one another.

Properties: Same as other C&C views, with an emphasis on protocols of interaction and performance-oriented properties. Attachments may change at runtime.

Constraints:

- Restrictions may be placed on the number of allowable attachments to any given port, or role.
- Special peer components can provide routing, indexing, and peer search capability.
- Specializations may impose visibility restrictions on which components can know about other components.

What It's For:

- Providing enhanced availability.
- Providing enhanced scalability.
- Enabling highly distributed systems, such as file sharing, instant messaging, and desktop grid computing.
