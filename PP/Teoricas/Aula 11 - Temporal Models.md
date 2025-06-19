

<!-- toc -->

- [Temporal Models](#temporal-models)
  * [Representação](#representacao)
    + [Visualização time-oriented](#visualizacao-time-oriented)
    + [Consistency](#consistency)
    + [Security](#security)
    + [Union of Multiple Timelines](#union-of-multiple-timelines)
    + [Causal support](#causal-support)
      - [Adicionando persistent assertion](#adicionando-persistent-assertion)
      - [Adicionando constraints](#adicionando-constraints)
      - [Adicionando change assertion](#adicionando-change-assertion)
  * [Actions ou Tasks](#actions-ou-tasks)
    + [Methods](#methods)
    + [Chronicles](#chronicles)
  * [Temporal Planning](#temporal-planning)
    + [Flaw 1 - Assertion not causally supported](#flaw-1---assertion-not-causally-supported)
      - [Adicionar constraints](#adicionar-constraints)
      - [Adicionar persistent assertion](#adicionar-persistent-assertion)
      - [Adicionar uma action ou task](#adicionar-uma-action-ou-task)
    + [Flaw 2 - Non-refined task](#flaw-2---non-refined-task)
    + [Flaw 3 - Pair of possibly-conflicting assertions](#flaw-3---pair-of-possibly-conflicting-assertions)
    + [Algorithm](#algorithm)
    + [Heuristics](#heuristics)
  * [Constraint Management](#constraint-management)
    + [Time Constraints](#time-constraints)
      - [Consistent STN](#consistent-stn)
      - [Minimal STN](#minimal-stn)
      - [Composition](#composition)
    + [Path-Consistency](#path-consistency)

<!-- tocstop -->

# Temporal Models

- Ações têm duração
- Goals têm constraints temporais
- Maintenance actions
  - Por exemplo, manter uma porta aberta por x tempo.
- Concurent effects

## Representação

### Visualização time-oriented

- O tempo é o eixo x.
- Time stamps são inteiros.
- Cada variável tem a sua própria timeline.

<img src="Imagens/Aula11 Time Line Example.png">

### Consistency

Uma timeline é consistent se tiver pelo menos uma ground instance que seja consistent.<br>
Uma ground instance (T', C') é consistent se T' satisfaz C' e se nenhuma variável tem mais que um valor para cada altura.

Exemplo de consistent timeline:
<img src="Imagens/Aula11 Time Line Consistent Example.png">

Exemplo de uma timeline que não é consistent:
<img src="Imagens/Aula11 Time Line Non Consistent Example.png">

Para este exemplo, é impossível arranjar uma ground instance que ao mesmo tempo satisfaça as constraints e que não tenha dois valores para o mesmo timestamp. Logo, não é consistent.

<br>

### Security

Uma timeline é secure se é consistent e se todas as as ground instances que satisfazem as constraints são consistent.

É possível tornar uma timeline consistent em secure ao adicionar separation constraints:

- r != r1
- t2 < t3

Exemplo de uma timeline secure:
<img src="Imagens/Aula11 Time Line Consistent Example.png">

### Union of Multiple Timelines

A união de ($T_1, C_1$), ..., ($T_k, C_k$) é<br>
T = $T_1 \cup ... \cup T_k$<br>
C = $C_1 \cup ... \cup C_k$

Se todas as state variables ($T_i, C_i$) são secure e nenhuns dois pares de timelines tiver unground varibles em comum então a sua união também é secure.

### Causal support

Se todas as assertions de uma timeline têm causal support, então a timeline é causally supported.

Para uma variável ter causal support é preciso que exista informação que essa assertion é válida a priori ou que exista outra assertion que a torne possível. Formalmente:<br>

A assertion $[t_1, t_2] x = v_1$ ou $[t_1,t_2]x: (v_1, v_2)$ tem causal support se:

- Há informação a priori.
- Há outra assertion que produz $x = v_i$ no $t_1$
  - $[t_0, t_1]x = v_1$
  - $[t_0, t_1]x = (v_0, v_1)$

#### Adicionando persistent assertion

$T = \{ [t_1, t_2] loc(r1):(loc1,loc2), [t_3, t_4] loc(r1):(loc2,loc3)\}$<br>
$C = \{ t_1 < t_2 < t_3 < t_4 \}$

Adicionamos $[t_2, t_3] loc(r1) = loc2$

<img src="Imagens/Aula11 Causal Support Persistent Assertion.png">

#### Adicionando constraints

$T = \{ [t_1, t_2] loc(r1):(loc1,loc2), [t_3, t_4] loc(r) = l\}$<br>
$C = \{ t_1 < t_2, t_3 < t_4 \}$

Adicionamos as constraints $t_2 = t_3, r = r1, l = loc2$

<img src="Imagens/Aula11 Causal Support Constraints.png">

#### Adicionando change assertion

$T = \{ [t_1, t_2] loc(r1) = loc1, [t_3, t_4] loc(r1):(loc3,loc4)\}$<br>
$C = \{ t_1 < t_2 < t_3 < t_4 \}$

Adicionamos $[t_2, t_3] loc(r1) = (loc1, loc3)$

<img src="Imagens/Aula11 Causal Support Change Assertion.png">

## Actions ou Tasks

Uma action para temporal models é basicamente o mesmo que a action do modelo anterior. A maior diferença é que agora vamos ter um starting e ending time como parametros adicionais. Nas assertions podemos ver o endpoint inicial como sendo uma precondition e o final como senddo um effect.

Exemplo:
<img src="Imagens/Aula11 Action Example.png">

### Methods

A partir das actions é possível criar metodos que expandem um pouco a funcionalidade das actions, adicionando refinements.

Exemplo:

<img src="Imagens/Aula11 Method Example.png">

### Chronicles

Isto é basicamente a representação a sério e total destes problemas. Contém o conjunto de ações, e tasks permitidas, as assertions suportadas a priori, assertions e constraints.

<img src="Imagens/Aula11 Chronicles Example.png">

<br>

## Temporal Planning

O planning problem é um chronicle $\phi_o$ que tem algumas flaws, parecidas com as flaws em PSPs.
Para resolver as flaws precisamos de adicionar:

- Assertions
- Constraints
- Actions

<img src="Imagens/Aula11 Planning Problem.png">

### Flaw 1 - Assertion not causally supported

É basicamente o mesmo que um open goal em PSP. Há 3 resolvers possíveis.

#### Adicionar constraints

$l$ = loc3, $t_2 = t_3$

<img src="Imagens/Aula11 Planning Problem Flaw 1 Constraint.png">

#### Adicionar persistent assertion

$l$ = loc3, $[t_2, t_3]$ loc(r1) = loc3

<img src="Imagens/Aula11 Planning Problem Flaw 1 Assertion.png">

#### Adicionar uma action ou task

$[t_2, t_3]$ move(r1, loc3)

<img src="Imagens/Aula11 Planning Problem Flaw 1 Action.png">

### Flaw 2 - Non-refined task

A solução de adicionar uma action para resolver a flaw anterior causa este tipo de problema. É como se tivessemos uma função que não está definida.

Resolver: Adicionar a novas task ao chronicle

<img src="Imagens/Aula11 Planning Problem Flaw 2 Method.png">

E ficamos com o chronicle:

<img src="Imagens/Aula11 Planning Problem Flaw 2 Chronicle update.png">

### Flaw 3 - Pair of possibly-conflicting assertions

É basicamente o mesmo que uma threat em PSPs onde duas assertions podem causar conflitos uma à outra.

Por exemplo:

$[t_1, t_2]$ loc(r1) = loc1, $[t_3, t_4]$ loc($r$) : ($l, l'$)

<img src="Imagens/Aula11 Planning Problem Flaw 3 Example.png">

Se não tivermos cuidado podemos acabar com uma situação assim. O resolver é adicionar separation constraints:

- $r$ != r1
- $t_2$ < $t_3$
- $t_4$ < $t_1$
- $t_2$ = $t_3$, $r$ = r1, $l$ = loc1
- $t_1$ = $t_4$, $r$ = r1, $l'$ = loc1

### Algorithm

<img src="Imagens/Aula11 Planning Problem Algorithm.png">

### Heuristics

Há algumas heuristicas que poemos ter em conta ao escolher a flaw e o resolver:

- Escolher a flaw que tem menos resolvers.
- Escolher o resolver que remove menos resolvers das outras flaws.

<br>

## Constraint Management

Cada vez que um resolver modifica (T, C), existe o risco de (T, C) ficar inconsistente. O objetivo é dar prune a esta parte do search space.

C pode ter dois tipos de constraints:

- Object constraints
  - Por exemplo: loc($r$) = loc1, $r$ != r1, ...
- Temporal constraints
  - Por exemplo: $t_1$ < $t_3$, $t_2$ = $t_4$

Assumindo que os object constraints e os temporal constraints são independentes entre si, podemos separar o problema em dois problemas:

- Checkar consistency das object constraints.
- Checkar consistency das temporal constraints.
- C é consistente sse ambos forem consistentes.

### Time Constraints

Simple Temporal Networks (STNs) são redes de constraints de pontos do tempo.

<img src="Imagens/Aula11 STN.png">

#### Consistent STN

Um STN que tem solução. O primeiro STN é consistent porque tem pelo menos a solução $t_1$ = 3, $t_2$ = 5, $t_3$ = 8.

#### Minimal STN

Um STN que se nós diminuirmos algum dos intervalos, estamos a remover soluções.

Por exemplo:

<img src="Imagens/Aula11 Minimal STN.png">

#### Composition

<img src="Imagens/Aula11 STN Composition.png">

O resultado de uma composition é um intervalo em que o start time é a soma dos start times dos argumentos e o end time é a soma dos end times dos argumentos.

### Path-Consistency

<img src="Imagens/Aula11 STN Path Consistency.png">

- Itera sobre todos os trios.
- Reduz o intervalo e checka consistency
