

<!-- toc -->

- [Tactics for Availability](#tactics-for-availability)
  * [Detect Faults](#detect-faults)
  * [Recover From Faults](#recover-from-faults)
    + [Preparation and Repair](#preparation-and-repair)
    + [Reintrodutions](#reintrodutions)
  * [Prevent Faults](#prevent-faults)

<!-- tocstop -->

# Tactics for Availability

Availability tactics servem para prevenir ou aguentar faults para que o serviço continue a ser prestado como deve de ser. Estas tactics podem ser de três tipos: Detect Faults, Recover from Faults or Prevent Faults.

## Detect Faults

- Monitor: Monitoriza o estado de várias partes do systema. Deteta failures na network ou em shared resources. Usa outras tactics desta categoria para detetar componentes que não funcionam.
  - Exemplo: O monitorizador do sistema pode iniciar self-tests.
  - Se funcionar com um timer ou counter, então chama-se watchdog. O processo de um componente resetar o timer ou o counter como sinal que está a funcionar chama-se "petting the watchdog".
- Ping / Echo: Envia um par request/response que é usado para saber se é possível alcançar e o RTT entre dois nós da network. É preciso que exista um time threshold para o pinging component saiba quanto deve esperar.
- Heartbeat: Periodicamente envia uma mensagem do processo que está a ser monitorizado para o processo monitorizador.
  - A diferença entre o Ping/Echo e o Heartbeat é quem tem a responsabilidade de iniciar o _health check_.
- Timestamp: Adiciona um timestamp ou um sequence number às mensagens para garantir uma sequência de eventos correta.
- Condition monitoring: Verifica condições de um processo ou dispositivo, ou valida assunções feitas durante o design.
  - O monitor tem de ser simples para não trazer novos erros.
- Sanity checking: Verifica a validade e se operações ou outputs de um component são razoáveis.
- Voting: Compara valores de várias fontes que deviam ser iguais. Se não forem iguais, escolhe qual usar.
- Exception detection: Detetam as condições do sistema que alteram o fluxo normal da execução.
- Self-test: Componentes, ou subsistemas, correm procedimentos que os testam a eles próprios. Podem ser chamados pelo próprio componente ou invocados de vez em quando por um monitor.

## Recover From Faults

### Preparation and Repair

- Redundant spare: Existe uma configuração em que um componente duplicado passa a fazer o trabalho de um componente primário que falhou.
- Rollback: O sistema reverte as alterações até ao último _good state_.
- Exception handling: Quando uma exceção é detata o sistema pode tratar dela de forma a não cair. Por exemplo criando uma classe com a origem, nome e descrição da exceção.
- Software upgrade: Quando é preciso fazer uma atualização no software sem dar shutdown ao sistema. Por exemplo, quando queremos migrar uma base de dados para uma melhorada.
  - Se quisermos corrigir um erro numa rede de servidores, podemos ir dando shutdown e resolvendo o problema um a um. Assim só há um que está offline. Se passarmos para algo mais complexo como migrar uma database, já temos maiores problemas.
- Retry: Assume que a fault é temporária e que quando se voltar a tentar já não vai acontecer. Deve haver um limite de retries para se passar a considerar uma failure.
- Ignore fault behavior: Se sabemos que um componente tem uma falha ignoramos as mensagens enviadas por ele. Por exemplo, ignoramos as informações enviadas de um sensor defeituoso.
- Graceful degradation: Mantém os componentes mais importantes do sistema a funcionar enquanto sacrifica alguns menos importantes. Isto só funciona em sistemas com componenetes que apenas melhoram o sistema, mas não são essenciais para ele sobreviver.
- Reconfiguration: Reatribui responsabilidades para os componentes que ainda funcionam. Tenta perder o minimo de funcionalidade possível.

### Reintrodutions

- Shadow: Um componente recem reparado fica em shadow mode durante um certo periodo de tempo para que possa ser monitorizado.
- State resyncronization: Atua em conjunto com o Redundant spare, e serve para deixar as máquinas em questão sincronizadas. Sempre que há um write para uma delas, também se aplica o write nas outras.
- Escalating restarts: Usam-se vários níveis de restarts, 0-3, em que o 0 vai ter o mínimo de impacto nos serivços e o 3 vai reiniciar o sistema por completo.
  - Esta tactic é útil com o Graceful degradation, já que podemos controlar as partes do sistema que estamos a sacrificar.
- Nonstop forwarding: O sistema consiste na parte que planeia as ações de routing (control plane) e a parte que efetivamente executa as ações (data plane). O data plane segue as informações dadas pelo control plane, e se o control plane for abaixo continua a usar rotas que já conhece.

## Prevent Faults

- Removal from service: Basicamente um componente é colocado num estado fora de serviço para mitigar failures no sistema.
- Transactions: Em certos sistemas é preciso que as mensagens trocadas entre componentes sejam atomic, consistent, isolated e durable (ACID properties). Esta tactic evita que dois processos tentem atualizar a mesma informação ao mesmo tempo.
- Predictive model: Quando combinado com um monitor, estes predictive models conseguem prever faults. Podem usar dados como threshold crossing ou estatisticas sobre o estado do processo.
- Exception prevention: Utilização de Exception classes, error-correcting code, abstract data types, etc.
- Increase competence set: O competence set é o conjunto de estados em que o sistema consegue operar, ou seja, é competente. Por exemplo, o denominador a 0 está fora do competence set da maior parte dos programas. O objetivo é aumentar o competence set de um componente para que consiga tratar de mais casos, faults, como se fosse parte da sua normal operation.
  - Por exemplo, um componente vê que o acesso a um shared resource está bloqueado e lança uma exception. Outro componente em vez de lançar um exceção pode esperar pelo acesso ou retornar imediatamente com uma indicação que vai aceder ao shared resource quando tiver acesso. Neste caso o segundo componente tem um maior component set do que o primeiro.
