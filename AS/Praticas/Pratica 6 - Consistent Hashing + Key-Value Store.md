

<!-- toc -->

- [Consistent Hashing](#consistent-hashing)
- [Key-Value Store](#key-value-store)

<!-- tocstop -->

# Consistent Hashing

Considerando um array circular de valores possíveis de hashing, distribuimos os servidores uniformemente pelo array. No entanto, isto traz problemas quando tentamos adicionamos ou removemos um servidor, como sobrecarregar um servidor mais do que os outros.

Para resolver isto, associamos a cada servidor, alguns servidores virtuais. E voltamos a distribuir pelo array circular. Como há mais servidores, a carga fica melhor distribuida. Ao adicionar ou remover servidores, também há muito menos mudanças a fazer.

Ainda há alguns problemas, por exemplo, quando um servidor morre é preciso garantir que há réplicas para não perdermos informação. Para além disso, quando um novo servidor é adicionado ele precisa de avisar os outros, e se a rede tiver imensos servidores isso não é assim tão simples. Usa-se o gossip algorithm para isso.

# Key-Value Store

CAP Theorem, Não é possível ter ao mesmo tempo:

- Consistency
- Availability
- Partition Tolerance

Mas é impossível sobreviver sem Partition Tolerance. Então temos de usar Partition Tolerance + Availabilty ou Partition Tolerance + Consistency.

Utilizamos então mecanismos de consistent hashing com virtual servers. E quando temos um valor para guardar, em vez de guardarmos só no primeiro server, guardamos também nos próximos N para manter algumas réplicas. Como estamos a replicar informação precisamos que os servidores sejam consistentes.

Para garantir consistencia entre as réplicas recorremos ao Quorum consensus. Sabendo que:

- N = Numero de réplicas
- W = Número de operações write completas até retornar.
- R = Número de operações read completas até retornar.

Podemos considerar os seguintes cenários:

- Se tivermos W + R > N, temos strong consistency.
- Se tivermos R = 1 e W = N temos strong consistency, escritas lentas e leituras rápidas.
- Se tivermos W = 1 e R = N temos strong consistency, escritas rápidas e leituras lentas.
- Se W + R <= N não temos strong consistency.

Para resolver problemas de inconsistencia usamos versões. Usamos vector clocks para representar as versões em cada servidor. Por exemplo D1[S1, v1] é a modificação D1 no servidor S1 que agora tem versão v1. O maior problema dos vector clocks é que adicionam complexidade ao cliente que precisa de resolver conflitos.

Quando um servidor morre é preciso avisar todos os servidores do acontecimento. E fazer um broadcast geral de todos para todos numa rede grande pode demorar. Para evitar isso usa-se o Gossip algorithm: cada node tem um conjunto de nodes amigos. Quando um dos amigos percebe que um node morreu, avisa os seus outros amigos e esses vão propagar a mensagem para os seus amigos e assim por diante.

Para conseguir comparar versões entre os nodes usa-se uma Merkle Tree que basicamente usa hashing para comparar dados em vez de os comparar carater a carater.
