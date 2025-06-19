### Vertical vs. Horizontal Scalability

Antes de começar há dois conceitos importantes:

- Vertical scalability refere-se a melhorar o hardware dos servidores existentes, adicionando mais CPU, por exemplo.
- Horizontal scalability refere-se a aumentar o número de servidores.

# Scale From Zero To Millions Of Users

Para criar um sistema capaz de suportar milhões de utilizadores é preciso começar pelo sistema mais simples e ir melhorando-o.

### 1. Single Server

Esta é a arquitetura mais simples e consiste apenas num servidor que pode ser acedido por vários utilizadores. A web app, database, cache, etc corre tudo no mesmo servidor.

### 2. Database

O próximo passo é separar o servidor da database. Estas duas arquiteturas já têm alguma scalability porque se podem ligar vários clientes ao servidor.

Ter a database num servidor à parte permite que sejam divididas as tarefas entre os servidores e diminuir a carga. Desta forma a web app trata dos pedidos enquanto que a database trata do armazenamento de dados.

### 3. Load Balancer

Um dos maiores problemas neste momento é a availability. Se um servidor cair o serviço fica indisponível. Para resolver este problema usa-se um Load Balancer. Quando os clientes fazem um pedido para o serviço resolver, o Load Balancer recebe o pedido e redireciona o pedido para um dos servidores disponíveis.

A maior vantagem é que agora o nosso sistema tem availability, já que mesmo que um servidor da wep app caia, o Load Balancer pode redirecionar os pedidos para o outro servidor. Para além disso também ganhamos alguma segurança, os clientes já não se ligam diretamente aos servidores, mas sim ao Load Balancer, que depois comunica com os servidores por ips privados. Por fim, o Load Balancer também nos oferece uma boa scalability porque é muito fácil adicionar novos servidores à rede, promovendo horizontal scalability.

### 4. Data Replication

Já resolvemos o problema de falhas para a web layer. Mas o mesmo problema ainda existe na data layer. Como só temos uma database, se ela cair, o sistema também fica inoperacional. Assim, o bottleneck passa a ser na data layer.

Para resolver este problema da maneira mais fácil possível podemos usar um esquema Master / Slave para a database. A maior parte das aplicações têm muito mais _reads_ do que _writes_, cerca de 95%-5%. Este esquema consiste na replicação de databases em que uma é o Master e apenas suporta _writes_. Todas as outras databases são slaves e apenas permitem _reads_. Assim resolvemos o problema, mas precisamos de garantir que os Slaves estão sincronizados com o Master. No caso de um dos Slaves cair, não há qualquer problema porque há mais Slaves que o podem substituir. Se for o último Slave a cair, então o Master passa a suportar operações de _read_ até que o Slave seja reposto. Se o Master cair, é preciso eleger um dos Slaves, que esteja sincronizado com o Master, para ser o novo Master.

### 5. Cache

Fazer leituras e escritas à database pode ser demorado. Para mitigar esta demora instalam-se Caches entre a web tier e a database tier que permitem que leituras frequentes não sejam sempre direcionadas à database. Também dá jeito que escritas não sejam muito frequentes.

Há um vasto conjunto de preocupações a ter ao instalar a Cache, por exemplo, qual _Expiration policy_ usar. Convém não usar um _expiration time_ muito curto porque isso força o sistema a reler a informação da datbase com frequência. No entanto, também não convém ser muito longo porque pode o conteúdo pode ser modificado entretanto.

Temos de ter também em conta a _Eviction Policy_, que pode ser Least-Recently-Used (LRU), Least-Frequenly-Used (LFU) ou First In First Out (FIFO).

### 6. Content Delivery Network (CDN)

Vários servidores são dispersados geograficamente para diminuir a espera da resposta entre o servidor e cliente. CDN's são Caches que guardam informações estáticas, por exemplo, imagens, vídeos, bibliotecas de JavaScript, etc. Quando um utilizador faz um pedido ao servidor, o CDN mais próximo geograficamente vai ficar responsável por entregar o conteúdo estático.

Para além de todos os problemas referios para as Caches, CDN's são geridos por terceiros, e cada transferência para os CDN's ou dos CDN's para um cliente são cobradas. Por isso não convém guardar informações que são usadas com pouca frequência.
