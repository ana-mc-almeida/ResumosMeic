# Back-Of-The-Envelope Estimation

Fazem-se estimativas das capacidades do sistemas considerando valores tabelados e ocurrências frequentes do sistema. Pode ser medido, o espaço ocupado, tempo que se demora ou até a availability do serviço.

Para medir a availability usam-se percentagens. Um sistema com 100% de availability não tem downtime.

### Exemplo

Estimativa das Querys Per Second (QPS) do Twitter:

Assumptions:
- 150 milhões de utilizadores usam o Twitter diariamente.
- Utilizadores postam 2 tweets por dia em média.
- 10% dos tweets contêm media (videos, images).
- Os tweets são guardados por 5 anos.
- Average tweet size:
  - tweet_id = 64 bytes
  - text = 140 bytes
  - media = 1 MB

Estimativa de QPS:
- Tweets QPS = 150 milhões * 2 tweets / 24 horas / 3600 segundos = ~3500

Armazenamento de media:
- Armazenamento de Media: 150 milhões * 2 * 10% * 1 MB = 30 TB por dia.
- Armazenamento de Media em 5 anos: 30 TB * 365 * 5 = ~55 PB

<br>

# Availability

Availability é a capacidade de um sistema oferecer o serviço quando é necessário. Está diretamente relacionada com security, performance e com safety.

### Failure

Uma failure acontece quando é possível observar o sistema a fazer algo diferente da sua especificação. Se não for observável, então não é uma failure.

### Fault

Uma fault é a causa da failure. No entanto, as faults podem ser tratadas para que não sejam observáveis para não se tornarem failures.

### Availability Calulation

```
MTBF / (MTBF + MTTR)

MTBF = mean time between failures
MTTR = mean time to repair
```

### System Availability Requirements

| Availability | Downtime/90 Days | Downtime/Year |
| ------------ | ---------------- | ------------- |
| 99.0% | 21 hr, 36 min | 3 days, 15.6 hr |
| 99.9% | 2 hr, 10 min | 8 hr, 0 min, 46 sec |
| 99.99% | 12 min, 58 sec | 52 min, 34 sec |
| 99.999% | 1 min, 18 sec | 5 min, 15 sec |
| 99.9999% | 8 sec | 32 sec |

### Availability General Scenario

| Portion of Scenario | Description | Possible Values | 
| ------------------- | ----------- | --------------- |
| Source | This specifies where the fault comes from. | Internal/external: people, hardware, software, physical infrastructure, physical environment |
| Stimulus | The stimulus to an availability scenario is a fault. | Fault: omission, crash, incorrect timing, incorrect response |
| Artifact | This specifies which portions of the system are responsible for and affected by the fault. | Processors, communication channels, storage, processes, affected artifacts in the system's environment |
| Environment | We may be interested in not only how a system behaves in its "normal" environment, but also how it behaves in situations such as when it is already recovering from a fault. | Normal operation, startup, shutdown, repair mode, degraded operation, overloaded operation |
| Response | The most commonly desired response is to prevent the fault from becoming a failure, but other responses may also be important, such as notifying people or logging the fault for later analysis. This section specifies the desired system response. | Prevent the fault from becoming a failure, Log the fault, Notify the appropriate entities (people or systems), Recover from the fault, Disable the source of events causing the fault, Be temporarily unavailable while a repair is being effected, Fix or mask the fault/failure or contain the damage it causes, Operate in a degraded mode while a repair is being effected |
| Response measure | We may focus on a number of measures of availability, depending on the criticality of the service being provided. | Time or time interval when the system must be available, Availability percentage (e.g., 99.999 percent), Time to detect the fault, Time to repair the fault, Time or time interval in which system can be in degraded mode, Proportion (e.g., 99 percent) or rate (e.g., up to 100 per second) of a certain class of faults that the system prevents, or handles without failing |

### Example

<img src="Imagens/T4 Availability Scenario Example.png">