

<!-- toc -->

- [Attribute-Driven Design (ADD)](#attribute-driven-design-add)
  * [Step 1: Review Inputs](#step-1-review-inputs)
  * [Step 2: Establish iteration goal by selecting drivers](#step-2-establish-iteration-goal-by-selecting-drivers)
  * [Step 3: Choose one or more elements of the system to refine](#step-3-choose-one-or-more-elements-of-the-system-to-refine)
  * [Step 4: Choose one or more design concepts that satisfy the selected drivers](#step-4-choose-one-or-more-design-concepts-that-satisfy-the-selected-drivers)
  * [Step 5: Instatiate architectural elements, allocate responsibilities, and define interfaces](#step-5-instatiate-architectural-elements-allocate-responsibilities-and-define-interfaces)
  * [Step 6: Sketch views and record design decisions](#step-6-sketch-views-and-record-design-decisions)
  * [Step 7: Perform analysis of current design and review iteration goal achievement of design purpose.](#step-7-perform-analysis-of-current-design-and-review-iteration-goal-achievement-of-design-purpose)
  * [Iterate if necessary](#iterate-if-necessary)

<!-- tocstop -->

# Attribute-Driven Design (ADD)

Attribute-Driven Design é feita em rondas, cada uma delas pode consistir num conjunto de iterações de design. Para cada iteração um conjunto de passos são feitos, ADD tem um guia dos passos que precisam de ser feitos em cada iteração.

1. Review Inputs
2. Establish iteration goal by selecting drivers
3. Choose one or more elements of the system to refine
4. Choose one or more design concepts that satisfy the selected drivers
5. Instatiate architectural elements, allocate responsibilities, and define interfaces
6. Sketch views and record design decisions
7. Perform analysis of current design and review iteration goal achievement of design purpose.

Os passos 1-7 constituem uma ronda. Dentro e cada ronda, os passos 2-7 constituem uma ou mais interações.

<img src="Imagens/T9 ADD.png">

## Step 1: Review Inputs

Antes de começar cada ronda de design precisamos de garantir que os drivers (inputs para o processo de design) estão disponíveis e corretos. Os drivers são:

- The purpose of the design round
- The primary functional requirements
- The primary quality attribute (QA) scenarios
- Any constraints
- Any concerns

## Step 2: Establish iteration goal by selecting drivers

Cada interação tem o foco de atingir um objetivo especifico. Neste passo especifica-se esse objetivo.

## Step 3: Choose one or more elements of the system to refine

Refinar pode ser a decomposição de elementos abrangentes em elementos mais simples (finer-grained elements) (top-down approach), ou pode ser a combinação de elementos simples em elementos mais abrangentes (coarser-grained elements) (bottom-up approach), ou pode ser também a melhoria de elementos.

## Step 4: Choose one or more design concepts that satisfy the selected drivers

Escolher o design concept é provavelmente a decisão mais dificil de todo o processo porque é preciso identificar vários conceitos de design que podem ser usados para atingir o objetivo da iteração. Há muitos tipos de design concepts disponíveis, como tactics, patterns, reference achitectures, e componentes desenvolvidos exteriormente. Para cada tipo existem várias opções, logo há imensas alternativas que têm de ser tidas em conta antes de se fazer a decisão final.

Mesmo quando os design concepts já estão mais claros é preciso identificar outras alternativas. Há várias maneiras para isso:

- Leverage existing best practices: Tem-se em conta soluções que já existem e que estão documentadas para o nosso problema.
  - A vantagen é que usa soluções que já foram experimentadas e que devem funcionar.
  - A desvantagem é que encontrar estas soluções e entende-las demora tempo.
- Leverage your own knowleddge and experience: Se for um sistema parecido a algum já desenhado anteriormente pela pessoa, então pode utilizar conhecimento previamente adquirido.
  - A vantagem é que pode ser aplicado rapidamente.
  - A desvantagem é que é podemos estar a usar ideias que não são as melhores para o nosso novo problema.
- Leverage the knowledge and experience of others: Fazer brainstorming com os colegas para todos contribuirem com os seus conhecimentos.

Depois de ter uma lista de alternativas temos de escolher a que mais se adequa ao nosso problema. Para isso recorre-se a uma tabela de pros e contras ou metodos como SWOT (strengths, weaknesses, opportunities, threats). É preciso ter em conta que as decisões tomadas em iterações anteriores podem restringir os design concepts que poemos escolher agora por causa de incompatibilidades.

Caso não consigamos escolher um design concept com as técnicas mencionadas acima, podemos usar protótipos para medir a sua performance. Quando se criam protótipos não nos preocupamos com maintanability ou com reuse. Como estes protótipos podem ser caros, só os consideramos quando temos a maioria destas questões presentes:

- O projeto incorpore tecnologias emersivas.
- A tecnologia é nova para a empresa.
- A satisfação de certos quality attributes está em risco com esta tecnologia.
- Há uma falta de certeza que a tecnologia ia satisfazer os drivers.
- Há configurações que precisam de ser testadas.
- Não é certo que vai ser fácil integrar a tecnologia.

## Step 5: Instatiate architectural elements, allocate responsibilities, and define interfaces

Depois de escolher os design concepts é preciso decidir como instanciar elementos dos design concepts que acabaram de ser escolhidos. Por exemplo, se escolhermos o layers pattern, temos de decidir quantas camadas vão haver e as relações permitidas entre elas.

Depois de instanciar os elementos é preciso dar responsabilidades a cada um deles. Por exemplo, as responsabilidades das camadas presentation, business e data são diferentes.

Para além disso é preciso definir o tipo de relações entre os elementos e a troca de informação deve ser feita por uma interface.

Tipos de instanciação:

- Reference architectures: Vai ser preciso adicionar ou remover elementos que fazem parte da estrutura da reference architecture à nossa arquitetura.
  - Por exemplo, se estivermos a desenhar uma aplicação que comunica com uma aplicação externa para tratar de pagamentos, vamos precisar de adicionar um componente de integração.
- Patterns: Os padrões são estruturas genéricas, ou seja, é preciso adaptar para o nosso problema. A sua instanciação consiste eem tornar um padrão genérico, em algo especifico.
- Tactics: Para instanciar uma tactic só é preciso adatar uma tactic existe a um novo tipo de design concept ou começar a usar um novo design concept que utiliza uma tactic já existente.
  - Por exemplo, usar a tática de authenticating actors para um processo de login. Ou adotar uma security tactic que inclua actor authentication.
- Externally developed components: Instanciar estes elementos pode implicar ou não a criação de novos elementos.
  - Por exemplo, se usarmos uma framework, provavelmente vamos precisar e criar classes que herdem das classes bases criadas pela framework. Um exemplo que não requer criação de elementos pode ser escolher opções de configuração, por exemplo o número e threads numa thread pool.

Quando criamos elementos e lhes associamos responsabilidades temos de ter em conta que os elementos devem ter high cohesion e low coupling.

## Step 6: Sketch views and record design decisions

Depois de ter tomado decisões e ter feito o design para a iteração, é preciso que a informação é apresentada de uma forma formal para que depois possa ser analisada. As decisões sobre tradeoffs importantes estão representadas nesta fase. O sketch obtido é uma forma de documentação.

Enquanto fazemos sketches da architetura e identificamos um elemento, devemos logo escrever qual é a sua responsabilidade.

É também importante registar as decisões feitas, em vez e só ter a representação dos elementos e relações entre eles, isto é, mostrar como chegamos à representação que temos.

## Step 7: Perform analysis of current design and review iteration goal achievement of design purpose.

Revisão geral daquilo que se passou durante a iteração. Pode ser feita até por pessoas diferentes para se ter uma perspetiva diferente. Esta diversidade ajuda a encontrar bugs tanto no código como na arquitetura. É nesta fase que se verifica se todos os drivers associados a esta ronda foram satisfeitos e se o objetivo foi atingido.

No fim de cada iteração devemos responder às seguintes perguntas:

- Quanto design é que ainda é preciso?
- Quanto design é que já foi feito?
- Acabamos?

Para ajudar nisto convem usarmos backlogs (to-do list) ou Kanban boars (to-do list com colunas: "Not Yet Addressed", "Partially Addressed", "Completely Addressed").

## Iterate if necessary

Devem ser feitos iterações adicionais e repetir os passos 2-7. O critério para availiar se são precisas mais iterações pode ser o risco. Pelo menos os drivers mais importantes devem estar satisfeitos, ou pelo menos o design deve ser bom suficiente para os satisfazer.
