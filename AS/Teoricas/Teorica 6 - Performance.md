# Performance

### Performance General Scenario

| Portion of Scenario | Description | Possible Values |
| ------------------- | ----------- | --------------- |
| Source | The stimulus can come from a user (or multiple users), from an external system, or from some portion of the system under consideration. | External: User request, Request from external system, Data arriving from a sensor or other system. Internal: One component may make a request of another component, A timer may generate a notification. |
| Stimulus | The stimulus is the arrival of an event. The event can be a request for service or a notification of some state of either the system under consideration or an external system. | Arrival of a periodic, sporadic, or stochastic event: A periodic event arrives at a predictable interval. A stochastic event arrives according to some probability distribution. A sporadic event arrives according to a pattern that is neither periodic nor stochastic. |
| Artifact | The artifact stimulated may be the whole system or just a portion of the system. For example, a power-on event may stimulate the whole system. A user request may arrive at (stimulate) the user interface. | Whole system or Component within the system. |
| Environment | The state of the system or component when the stimulus arrives. Unusual modes-error mode, overloaded mode-will affect the response. For example, three unsuccessful login attempts are allowed before a device is locked out. | Runtime. The system or component can be operating in: Normal mode, Emergency mode, Error correction mode, Peak load, Overload mode, Degraded operation mode, Some other defined mode of the system. |
| Response | The system will process the stimulus. Processing the stimulus will take time. This time may be required for computation, or it may be required because processing is blocked by contention for shared resources. Requests can fail to be satisfied because the system is overloaded or because of a failure somewhere in the processing chain. | System returns a response, System returns an error, System generates no response, System ignores the request if overloaded, System changes the mode or level of service, System services a higher-priority event, System consumes resources |
| Response measure | Timing measures can include latency or throughput. Systems with timing deadlines can also measure jitter of response and ability to meet the deadlines. Measuring how many of the requests go unsatisfied is also a type of measure, as is how much of a computing resource (e.g., a CPU, memory, thread pool, buffer) is utilized. | The (maximum, minimum, mean, median) time the response takes (latency), The number or percentage of satisfied requests over some time interval (throughput) or set of events received, The number or percentage of requests that go unsatisfied, The variation in response time (jitter), Usage level of a computing resource. |

### Exemplo

<img src="Imagens/T6 Performance Scenario Example.png">

## Tactics

O objetivo das Performance tactics é gerar uma resposta a eventos com limitações de tempo e recursos.

### Control Resource Demand

Uma maneira de melhorar performance é gerindo como deve de ser a demanda por recursos.

- Manage work requests: Há duas maneiras de usar esta tactic:
  1. Manage event arrival: Uma maneira de limitar a quantidade de pedidos externos feitos ao sistema é através de um service level agreement (SLA) que especifica a taxa de event arrival que estamos dispostos a suportar.
  2. Manage sampling rate: Se o sistema não aguentar níveis de resposta adequados podemos reduzir a frequência dos estimulos. Por exemplo, diminuir os FPS de um vídeo que estamos a processar.
- Limit event response: Quando chegam demasiados eventos ao sistema, e não é possível processar todos, enviam-se os eventos para um queue ou então são descartados.
  - Se usarmos esta tactic e for inaceitável perder eventos, temos de garantir que a queue é grande suficiente para aguentar o pior caso.
- Prioritize events: Se os eventos tiverem prioridades diferentes, podemos acossiar prioridades aos eventos e se não tivermos recursos para processar todos, ignoramos os menos relevantes.
- Reduce computational overhead: Para os eventos que entram no sistema temos as seguintes tactics:
  1. Reduce indirection: Usamos intermediarios faz com que processemos os eventos de uma queue em paralelo, removê-los faz com que melhoremos a latency, já que todos os recursos são usados para o primeiro evento da queue. *Não tenho a certeza que seja mesmo isto '-'*
  2. Co-locate communicating resources: Colocamos componentes que se chamam muitas vezes no mesmo processador para evitar esperar o delay da comunicação entre uma network.
  3. Periodic cleaning: Fazemos um cleanup periodico de recursos que se tornaram ineficientes. Por exemplo, uma hashtable que já tem muita informação podem precisar de ser reinicializada.
- Bound execution times: Limita-se o tempo de execução que cada evento pode demorar. 
- Increase efficency of resource usage: Melhorar a eficiencia de algoritmos de áreas criticas. Customa ser a primary performance tactic.

### Manage Resources

- Increase resources: Utilização de processadores mais rápidos, processadores adicionais, memória adicional e networks mais rápidas.
- Introduce concurrency: Se os pedidos puderem ser tratados em paralelo, estamos a diminuir o blocked time.
- Maintain multiple copies of computations: Servidores replicados num microserviço ou servidores web replicados num conjunto de servidores permitem que os pedidos não sejam feitos todos por uma máquina.
- Maintain multiple copies of data: Usamos data replication e caching para evitarmos uma grande demora de vários acessos simultaneos.
- Bound queue sizes: Limitamos o máximo de de eventos que podem entrar e assim limitamos o uso de recursos. É preciso estabelecer politicas para quando as queues enchem e se é aceitável perder eventos.
- Schedule resources: Quando existe competição por um recurso, faz-se um scheduling desse recurso. Processadores, buffers e netwokrs podem ter schedules. 