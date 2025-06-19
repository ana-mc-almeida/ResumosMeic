

<!-- toc -->

- [7. Stateful vs stateless web tier](#7-stateful-vs-stateless-web-tier)
- [8. Data centers](#8-data-centers)
- [9. Message queue](#9-message-queue)
- [10. Logging, metrics, automation](#10-logging-metrics-automation)
- [11. Sharding](#11-sharding)

<!-- tocstop -->

Continuação da [Pratica 1](Pratica%201.md).

### 7. Stateful vs stateless web tier

Para escalarmos a web tier horizontally precisamos de mover o estado (user session) para fora o web tier. A melhor maneira de fazer isso é passando essas informações para uma persistent storage, ou seja uma database. Desta forma todos os web servers podem aceder ao estado dos utilizadores. Este mecanismo é o stateless web tier e é mais simple, robusto e scalable do que um stateful web tier.

Num stateful web tier, em vez de guardarmos as informações do user session para uma base de dados, cada servidor lembra-se do estado do cliente e tem isso guardado em memória. Para isto funcionar é preciso que o Load Balancer redirecione um cliente sempre para o mesmo serivodr. Isto causa vários problemas, como overload em servidores especificos se os utilizadores ficarem o dia todo logados. É também mais dificil de adicionar ou remover servidores.

### 8. Data centers

Quando os utilizadores de um serviço estão espalhados pelo globo inteiro é conveniente que existam data centers espalhados ao redor do globo para evitar esperas longas. Este é um conceito bastante simples em que o Load Balancer vai redirecionar o cliente para o servidor mais próximo geograficamente. No entanto, isto traz alguns problemas de sincronização já que cada data center tem o seu conjunto de databases.

### 9. Message queue

A message queue serve para podermos tratar todos os pedidos de forma assincrona, assim todos os pedidos são inicialmente guardados e tratados assim que possível. Também nos permite saber se é preciso mais workers (máquinas que tratam os pedidos na queue) e ajustar o número de workers adequadamente. O _Producer_ (quem adiciona mensagens à queue) e o _Consumer_ podem escalar de maneira independente.

A message queue tem de ser persistente e serve basicamente como um buffer de pedidos assincronos.

### 10. Logging, metrics, automation

Isto é basicamente só adicionar sistemas de monitorização do sistema.

### 11. Sharding

Para fazermos horizontal scalling da nossa database temos de ter considerações especiais para os _writes_. Para sabermos sempre onde está a informação podemos seguir a seguinte formula de hashing: `user_id % n_databases`. Desta forma sabemos sempre onde estão as informações relacionadas com cada user. Apesar de parecer uma boa solução há também vários problemas associados:

- Resharding data: No caso de uma das databases cair, ou ficar cheia, ou até mesmo se for adicionada uma nova database, temos de mover as informações das databases de um lado para o outro para garantir que tudo está no devido sitio. Isto é extremamente pesado, o que faz com que esta solução não seja scalable.
- Celebrity problem: Para aplicações sociais, há utilizadores que têm muito mais informações associadas ao seu perfil do que outros, por exemplo o Cristiano Ronaldo tem muito mais informações associadas ao seu perfil do que o eu. Isto pode causar problema de partição dos shards.
- Join and de-normalization: Como as informações estão guardadas em databases diferentes, é muito mais pesado fazer Joins. Temos então de recorrer a uma de-normalization para podermos fazer queries a uma única tabela.
