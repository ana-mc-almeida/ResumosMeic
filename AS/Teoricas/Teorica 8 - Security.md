# Security

Os três focos da security são (CIA):

- Confidenciality: É a proteção de informações ou serviços de um acesso sem autorização.
  - Por exemplo, um hacker tentar aceder às informações de pessoas por um computador do governo.
- Integrity: Garante que serviços e data não são modificados por pessoas não autorizadas.
  - Por exemplo, a nota obtidda num teste não ser mudada desde o momento em que o professor a atribui.
- Availability: Garante que o sistema está disponível quando tem de estar disponível.
  - Por exemplo, um denial-of-service attack não vai impedir o sistema de fornecer serviço.

Uma maneira de representar os ataques é com uma _attack tree_. O root da árvore é um ataque com sucesso, e os nodes são as causas do ataque. Os nós filhos dividem essas causas em causas mais pequenas e assim por diante. Um ataque é uma tentativa de comprometer a CIA. As _attack trees_ são o estimulu do ataque.

## Security General Scenario

| Portion of Scenario | Description                                                                                                                                                                                                                                              | Possible Values                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| ------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| Source              | The attack may be from outside the organization or from inside the organization. The source of the attack may be either a human or another system. It may have been previously identified (either correctly or incorrectly) or may be currently unknown. | Human, Another system inside the organization, outside the organization, previously identified or unknown.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| Stimulus            | The stimulus is an attack.                                                                                                                                                                                                                               | An unauthorized attempt to: Display data, Capture data, Change or delete data, Access system services, Change the system's behavior or Reduce availability                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| Artifact            | What is the target of the attack?                                                                                                                                                                                                                        | System services, Data within the system, A component or resources of the system, Data produced or consumed by the system                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| Environment         | What is the state of the system when the attack occurs?                                                                                                                                                                                                  | The system is: Online or offline, Connected to or disconnected from a network, Behind a firewall or open to a network, Fully operational, Partially operational, Not operational                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| Response            | The system ensures that confidentiality, integrity, and availability are maintained.                                                                                                                                                                     | Transactions are carried out in a fashion such that: Data or services are protected from unauthorized access, Data or services are not being manipulated without authorization, Parties to a transaction are identified with assurance, The parties to the transaction cannot repudiate their involvements, The data, resources, and system services will be available for legitimate use. The system tracks activities within it by: Recording access or modification, Recording attempts to access data, resources, or services and Notifying appropriate entities (people or systems) when an apparent attack is occurring. |
| Response measure    | Measures of a system's response are related to the frequency of successful attacks, the time and cost to resist and repair attacks, and the consequential damage of those attacks.                                                                       | One or more of the following: How much of a resource is compromised or ensured, Accuracy of attack detection, How much time passed before an attack was detected, How many attacks were resisted, How long it takes to recover from a successful attack, How much data is vulnerable to a particular attack.                                                                                                                                                                                                                                                                                                                   |

### Exemplo

<img src="Imagens/T8 Security Scenario Example.png">

## Tactics

### Detect Attacks

- Detect intrusion: Compara os padrões de um pedido e do tráfico da rede com padrões maliciosos conhecidos.
- Detect service denial: Compara o padrões de uma assinatura e o tráfico da rede com o histórico de perfis conhecidos por denial-of-service attacks.
- Verify message integrity: Utiliza técnicas como checksums ou hash functions para verificar a integridade das mensagens e de ficheiros.
- Detect message delivery anomalies: Deteta man-in-the-middle-attack verificando se o tempo de entrega da mensagem está estável e números de connections e disconnections.

### Resist Attacks

- Identify actors: Identifica os atores (users ou remove computers) para saber a origem de um input externo.
- Authenticate actors: Garante que os atores são quem dizem ser.
- Authorize actors: Garante que um utilizador autenticado tem permissão para aceder ou modificar data ou serviços.
- Limit access: Limita os acessos aos recursos de um computador. Esta limitação pode ser feita restringindo os pontos de acesso aos recursos ou restringir o tipo de tráfico que pode usar os access points.
- Limit exposure: Minimiza os danos de um ataque. Reduz a quantidade de data ou serviços a que se pode aceder por cada access point. Assim mesmo que um dos pontos de acesso seja comprometido num ataque, o ataque causar grandes danos.
- Encrypt data: É impossível ter controlo de autorizações em canais de comunicação públicos, então encriptação é a única proteção para os dados.
- Separate entities: Limita o scope de um ataque. Separação dentro do sistema pode ser feita por separação física, onde se ligam servidores diferentes a redes diferentes, usando virtual machines ou evitando conexões eletronicas entre partes diferentes do sistema.
- Validate input: Previne SQL injection, cross-site scripting.
- Change credential settings: Obrigar os utilizadores de um sistema a mudar a password de tempos em tempos. Quando se recebe um sistema convém mudar as definições de segurança para não serem iguais às default e não serem ultrapassadas facilmente.
  - Por exemplo, quando se usa um novo database server que vem com o user admin, com todas as permissões, é importante mudar a password deste user.

### React to Attack

- Revoke access: Se o sistema ou um administrador achar que um ataque vai acontecer pode limitar os acessos a recursos sensiveis, mesmo que o acesso não seja um ataque.
  - Por exemplo, se existir um virus num computador, o acesso a certos recursos deve ser bloqueado até o virus ser removido.
- Restrict login: Falhas sucessivas para fazer login podem indicar um ataque. Uma solução possível é bloquear o acesso de um computador especifico temporariamente.
- Inform actors: Notifica-se system operators quando o sistema deteta que está sob ataque.

### Recover from Attacks

- _Availability tactis for recovery_
- Audit: Mantém-se registo do utilizador, das ações do sistema e os seus efeitos. Isto ajuda a descobrir as ações e a identificar um hacker. Servem também para criar melhores defesas no futuro.
- Nonrepudiation: Garante que alguém que enviou uma mensagem não pode negar que a enviou, e que alguém que recebeu uma mensagem não pode negar que a recebeu.
