

<!-- toc -->

  * [1.1.1](#111)
    + [Solução do stor:](#solucao-do-stor)
    + [Solução do stor:](#solucao-do-stor)
  * [1.1.2](#112)
    + [Solução do stor:](#solucao-do-stor-1)
- [1.1.3](#113)
    + [Solução do stor:](#solucao-do-stor-2)
- [1.2.1](#121)
- [1.2.2](#122)
- [1.2.3](#123)
- [1.2.5](#125)
  * [a)](#a)
  * [b)](#b)
- [1.2.6](#126)
  * [a)](#a-1)
  * [b)](#b-1)
- [1.2.7](#127)
  * [a)](#a-2)
  * [b)](#b-2)
  * [c)](#c)
  * [d)](#d)
  * [e)](#e)
  * [f)](#f)
  * [g)](#g)
- [1.2.8](#128)
  * [a)](#a-3)
  * [b)](#b-3)
- [1.2.9](#129)
- [1.2.10](#1210)
  * [a)](#a-4)
  * [b) e c)](#b-e-c)
- [1.3.1](#131)
  * [a)](#a-5)
  * [b)](#b-4)
  * [c)](#c-1)
  * [d)](#d-1)
  * [e)](#e-1)
  * [e)](#e-2)
  * [f)](#f-1)
  * [g)](#g-1)
  * [h)](#h)
  * [i)](#i)
  * [j)](#j)
  * [k)](#k)
  * [l)](#l)
  * [m)](#m)
  * [n)](#n)
  * [o)](#o)
  * [p)](#p)
  * [q)](#q)
  * [r)](#r)
  * [s)](#s)
  * [t)](#t)
  * [u)](#u)
  * [v)](#v)
- [1.3.3](#133)
  * [a)](#a-6)
  * [b)](#b-5)
- [1.3.4](#134)
- [1.4.1](#141)
  * [a)](#a-7)
  * [b)](#b-6)
- [1.4.2](#142)
  * [a)](#a-8)
  * [b)](#b-7)
- [1.4.3](#143)
- [1.5.2](#152)
  * [a)](#a-9)
  * [b)](#b-8)
  * [c)](#c-2)
  * [d)](#d-2)
  * [e)](#e-3)
  * [f)](#f-2)
  * [g)](#g-2)
  * [h)](#h-1)
- [1.5.4](#154)
  * [a)](#a-10)
- [1.5.5](#155)
  * [f)](#f-3)
  * [g)](#g-3)
- [h)](#h-2)
  * [i)](#i-1)
- [Exercicio de exame anterior (2021-2022) II Inference in CSP](#exercicio-de-exame-anterior-2021-2022-ii-inference-in-csp)
- [Exercicio 2) de exame (2023-2024)](#exercicio-2-de-exame-2023-2024)
- [2.2.1](#221)
  * [1)](#1)
  * [2)](#2)
- [2.2.2](#222)
  * [a)](#a-11)
  * [b)](#b-9)
  * [c)](#c-3)
  * [d)](#d-3)
- [2.2.3](#223)
  * [a)](#a-12)
  * [b)](#b-10)
  * [c)](#c-4)
- [2.2.5](#225)
  * [a)](#a-13)
  * [b)](#b-11)
  * [c) + d)](#c--d)
  * [e)](#e-4)
  * [f)](#f-4)
- [2.2.6](#226)
  * [a)](#a-14)
  * [b)](#b-12)
- [2.2.7](#227)
  * [2.](#2)
  * [3.](#3)
- [Exercicio 1 Grupo 5 Exame 2023/2024](#exercicio-1-grupo-5-exame-20232024)
  * [a)](#a-15)
  * [b)](#b-13)
- [2.3.1](#231)
- [2.3.2](#232)
- [2.3.5](#235)

<!-- tocstop -->

## 1.1.1

a) Variaveis: cada um dos quadrados da grid.<br>
Dominio: Para cada uma delas o dominio é de 1 a n<br>
Constraints:

- todas as linhas precisam de que os seus elementos sejam diferentes.
- todas as colunas precisam de que os seus elementos sejam diferentes.

### Solução do stor:<br>

Variaveis de celula -> X = { x\_{ij} | i <= i <= n, 1 <= j <= n }<br>
Dominio: D_x = {k | 1 <= k <= n }<br>

Constraints:

- $V_i$ alldiff($x_{i1}$, $x_{i2}$, ..., $x_{in}$)
- $V_j$ alldiff($x_{j1}$, $x_{j2}$, ..., $x_{jn}$)

b)
Variaveis: cada um dos quadrados em cada um dos squares.<br>
Dominio: 1 a n<br>
Constraints: <br>

- Separadamente os Latin squares têm de cumprir as constraints de a).
- Todos os pares presentes no orthogonal square precisam de ser diferentes.

### Solução do stor:

Variaveis:<br>
A = { $a_{ij}$ | 1 <= i <= n; 1 <= j <= n }<br>
B = { $b_{ij}$ | 1 <= i <= n; 1 <= j <= n }<br>

Domain:<br>
$D_A$ = $D_B$ = { k | 1 <= k <= n }<br>
$D_X$ = { (l, m) | 1 <= l <= n, 1 <= m <= n }<br>

Constraints:<br>

- alldiff(X)

## 1.1.2

Variaveis: Valores da matriz<br>
Dominio: -9 a 9<br>
Constraints:

- (x,y) = 0 && x == y
- (x,y) = -(y,x) && x != y

### Solução do stor:

Variaveis: A = { $x_{ij}$ | 1 <= i <= 3 , 1 <= j <= 3 }<br>

Domain: $D_A$ = [-9, 9]<br>

Constraints:

- $x*{ij} = -x*{ji}
- D($x_{11}$) = D($x_{22}$) = D($x_{33}$) = {0}

# 1.1.3

Variaveis: cada uma das cells<br>
Dominio: 1..(3n^2 - 3n + 1)<br>
Constraints:

- alldifferent para todas a variaveis
- a soma de todas as linhas é igual a M
- a soma de todas as diagonais é igual a M

### Solução do stor:

Variaveis: [a, s]<br>

Domain: { K | 1 <= k <= 19 }

Constraints:

- alldiff(X)
- a + b + c = d + e + f + g = ... = 38
- a + d + h = b + c + i + m = ... = 38
- c + g + l = b + f + k + p = ... = 38

# 1.2.1

| Edges      | Domain Change   | New edges                        |
| ---------- | --------------- | -------------------------------- |
| $x_0, x_1$ | $x_0$ = {3,4,7} | Não atualiza por tar em processo |
| $x_1, x_0$ | $x_1$ = {2,4,6} | ($x_0, x_1$)                     |
| $x_0, x_2$ | ----------      | ---------                        |
| $x_2, x_0$ | $x_2$ = {3,4,7} | ($x_0, x_1$)                     |
| $x_0, x_3$ | --------        | -------                          |
| $x_3, x_0$ | --------        | -------                          |
| $x_0, x_1$ | --------        | -------                          |
| $x_0, x_2$ | --------        | -------                          |

$D_{x_0}$ = {3,4,7}<br>
$D_{x_1}$ = {2,4,6}<br>
$D_{x_2}$ = {3,4,7}<br>
$D_{x_3}$ = {6,7,8}<br>

<br>

# 1.2.2

| Edges | Domain Change | New edges      | Explanation                                                                                                                                                                                                          |
| ----- | ------------- | -------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| $x,z$ | -----         | -----          | Há sempre um valor de $z$ que satisfaz $R_{zx}$ para qualquer valor de $x$.                                                                                                                                          |
| $z,x$ | -----         | -----          | O mesmo que em cima.                                                                                                                                                                                                 |
| $y,z$ | -----         | -----          | Para qualquer valor de $y$, há sempre um valor de $z$ que satisfaz $R_{zy}$, esse valor é o 2.                                                                                                                       |
| $z,y$ | $D_z$ = {2}   | $(x,z), (y,z)$ | O valor 5 da variável $z$ não tem suporte de nenhum valor de $y$. Logo 5 pode ser removido do domínio de $y$ porque não satisfaz $R_{zy}$. Então temos de adicionar os edges que têm uma constraint com $z$ à queue. |
| $x,z$ | $D_x$ = {2}   | $(z,x)$        | Como agora o $D_z$ = {2}, já não há nenhum valor de $z$ que satisfaça o valor 5 de $x$ para a constraint $R_{zx}$. Então reduzimos o dominio.                                                                        |
| $y,z$ | -----         | -----          | Continua a haver um valor de $z$ que satisfaz todos os valores de $y$                                                                                                                                                |
| $z,x$ | -----         | -----          | $R_{zx}$ é satisfeita para todos os valores de $z$.                                                                                                                                                                  |

$D_x$ = {2}<br>
$D_y$ = {2,4}<br>
$D_z$ = {2}<br>

Pontos chave:

- Quando analisamos um edge $(a,b)$ só vamos alterar o domínio da variável $a$.
- Se houverem alterações ao dominío os edges que vamos adicionar à queue vão ter sempre o $a$ em segundo lugar.
  - Por exemplo $(b,a)$ ou $(c,a)$
- Só adicionamos à queue edges que não lá estejam ainda.

<br>

# 1.2.3

Primeiro aplica-se arc-consistency:

| Edges     | Domain Changes  | New Edges   |
| --------- | --------------- | ----------- |
| $X_1,X_3$ | $D_1$ = {3,4,7} | ---         |
| $X_3,X_1$ | $D_3$ = {3,4,7} | $(X_1,X_3)$ |
| $X_1,X_4$ | ----            | ----        |
| $X_4,X_1$ | ----            | ----        |
| $X_2,X_4$ | $D_2$ = {2,4,6} | ----        |
| $X_4,X_2$ | ----            | ----        |
| $X_1,X_3$ | ----            | ----        |

$D_1$ = {3,4,7}<br>
$D_2$ = {2,4,6}<br>
$D_3$ = {3,4,7}<br>
$D_4$ = {6,7,8}<br>

| Trios         | Constraint Change     | New Trios                                                 |
| ------------- | --------------------- | --------------------------------------------------------- |
| $X_1,X_3,X_2$ | ----                  | ----                                                      |
| $X_1,X_4,X_2$ | ----                  | ----                                                      |
| $X_1,X_2,X_3$ | ----                  | ----                                                      |
| $X_1,X_4,X_3$ | ----                  | ----                                                      |
| $X_1,X_2,X_4$ | ----                  | ----                                                      |
| $X_1,X_3,X_4$ | ----                  | ----                                                      |
| $X_2,X_1,X_3$ | ----                  | ----                                                      |
| $X_2,X_4,X_3$ | ----                  | ----                                                      |
| $X_2,X_1,X_4$ | ----                  | ----                                                      |
| $X_2,X_3,X_4$ | ----                  | ----                                                      |
| $X_3,X_1,X_4$ | $R_{3,4} = X_4 > X_3$ | $(X_1,X_3,X_4),(X_2,X_3,X_4),(X_1,X_4,X_3),(X_2,X_4,X_3)$ |
| $X_3,X_2,X_4$ | ----                  | ----                                                      |
| $X_1,X_3,X_4$ | ----                  | ----                                                      |
| $X_2,X_3,X_4$ | ----                  | ----                                                      |
| $X_1,X_4,X_3$ | ----                  | ----                                                      |
| $X_2,X_4,X_3$ | ----                  | ----                                                      |

Uma nova constraint é adicionada: $R_{3,4} = X_4 > X_3$

<br>
 
# 1.2.5

## a)

<img src="Imagens/1.2.5.png">

w(A) = 0<br>
w(B) = 1<br>
w(C) = 1<br>
w(D) = 1<br>
w(E) = 1<br>
w(F) = 2<br>
w(G) = 3<br>
width graph = 3

## b)

<img src="Imagens/1.2.5b.png">

w(G) = 0<br>
w(F) = 1<br>
w(E) = 1<br>
w(D) = 2<br>
w(C) = 1<br>
w(B) = 2<br>
w(A) = 2<br>
width graph = 2

<br>

# 1.2.6

## a)

<img src="Imagens/1.2.6a.png">

w(D) = 0<br>
w(C) = 1<br>
w(B) = 2<br>
w(A) = 2<br>
width graph = 2

## b)

<img src="Imagens/1.2.6b.png">

w(D) = 0<br>
w(B) = 1<br>
w(C) = 1<br>
w(A) = 2<br>
width graph = 2

# 1.2.7

## a)

<img src="Imagens/1.2.7a.png">

w(B) = 0<br>
w(C) = 0<br>
w(A) = 2<br>
width graph = 2

## b)

<img src="Imagens/1.2.7b.png">

w(A) = 0<br>
w(B) = 1<br>
w(C) = 1<br>
width graph = 1

## c)

<img src="Imagens/1.2.7c.png">

w(B) = 0<br>
w(A) = 1<br>
w(C) = 1<br>
width graph = 1

## d)

a), b), c) :
| Nodes | Changes |
| ----- | ------- |
| A | - |
| B | - |
| C | - |

Nada vai mudar para nenhum dos casos

## e)

a)
<img src="Imagens/1.2.7da.png">

b)
<img src="Imagens/1.2.7db.png">

c)
<img src="Imagens/1.2.7dc.png">

Quando se deixa o nó com mais conexões para o fim, as procuras são mais fundas.

## f)

Não mudam nada em relação ao width normal

## g)

Path consistency vai criar uma nova constraint: B = C.
Isto faz com que a arvore da ordem a), vá também ter cortes mais cedo.

# 1.2.8

## a)

| Nodes | Domian Changes |
| ----- | -------------- |
| E     | $D_D$ = {g,b}  |
| D     | -              |
| C     | $D_B$ = {r,g}  |
| B     | -              |
| A     | -              |

## b)

| Nodes | Domain Changes | Constraints         | Edge |
| ----- | -------------- | ------------------- | ---- |
| E     | $D_D$ = {g,b}  | -                   | -    |
| D     | -              | $R_{BC}$ = {(r, b)} | -    |
| C     | $D_B$ = {r}    | -                   | -    |
| B     | -              | -                   | -    |
| A     | -              | -                   | -    |

<br>

# 1.2.9

| Nodes | Domain Changes        |
| ----- | --------------------- |
| $x_4$ | $D_3$ = {white, blue} |
| $x_3$ | $D_1$ = {white}       |
| $x_2$ | ---                   |
| $x_1$ | ---                   |

It is backtrack-free because whichever value selected for $x_1$ will allow us to reach a decision without backtracking.<br>
It is not full arc consistent because, for example, three are values in $x_2$ that don't respect $R_{1,2}$, for example, green.

<br>

# 1.2.10

## a)

É path-consistent porque independentemente do valor escolhido para $x$ é possível escolher um valor para $y$ que satisfaz $x < y$. E para esse mesmo valor de $y$ é possível escolher um valor de $z$ que satisfaz $y < z$. E independentement do valor escolhido para $z$, a constraint $x < z$ vai estar sempre satisfeita.<br>

Demonstração:<br>
$0 <= x <= 4$ Logo,<br>
$x < y <= 5$, para todos os valores de que $x$ pode assumir, há sempre um valor de $y$ que cumpre $x < y$<br>
$y < z <= 10$, o valor mais alto que y pode ter é 5, ou seja há sempre algum valor de $z$ que cumpre $y < z$<br>
Como $x \in [0, 4]$ e $z \in [5, 10]$, por isso a constraint $x < z$ também está sempre satisfeita.

<br>

## b) e c)

A melhor maneira de mostrar que não é directional path-consistent é com um contra-exemplo por isso vou misturar o b) e o c).

Ao escolher $x$ = 4 e $z$ = 5 cumpria o constraint $x < z$ e ao escolher $y$ = 1 também cumpro o constraint $y < z$, seguindo a ordem dada. No entanto, a constraint $x < y$ não é cumprida para estes valores de $x, y, z$. Logo a network não é path consistent para a ordem $x, z, y$.

<br>

# 1.3.1

Backtracking:

$X_A$ = r

- $X_B$ = g
  - $X_C$ = b
    - $X_D$ = r
      - $X_E$ = g
        - $X_F$ = b
          - $X_G$ = ~~r~~, ~~b~~
        - $X_F$ = g
          - $X_G$ = ~~r~~, ~~b~~
      - $X_E$ = y

## a)

Node G

## b)

Node F

## c)

Node E

## d)

E = y True

## e)

Gashnig's Backjumping

A = r latest = {}

- B = g latest = {}
  - C = b latest = {}
    - D = r latest = {}
      - E = g latest = {}
        - F = b latest = {}
          - G = ~~r~~, ~~b~~ latest = C porque corre de cima para baixo e C elimina o blue do dominio, ficando assim vazio. Antes o A removeu o red.
  - C = {} backtrack
- B = y latest = {}
  - C = b latest = {}
    - D = r latest = {}
      - E = g latest = {}
        - F = b latest = {}
          - G = ~~r~~, ~~b~~ latest = C
  - C = {} backtrack
- B = {} backtrack

A = b latest = {}

- B = g latest = {}
  - C = b latest = {}
    - D = r latest = {}
      - E = g latest = {}
        - F = b latest = {}
          - G = ~~r~~, ~~b~~ latest = D
    - D = g latest = {}
      - E = g latest = {}
        - F = b latest = {}
          - G = r COMPLETED

---

Graph-based backjumping

Primeiro aplica-se os induced parents.

A = r Induced = {}

- B = g Induced = {}
  - C = b Induced = {}
    - D = r Induced = {}
      - E = g Induced = {}
        - F = b Induced = {}
          - G = ~~r~~, ~~b~~ Induced = {A, C, D, E} JUMP TO E
      - E = y Induced = {}
        - F = Induced = {}
          - G = ~~r~~, ~~b~~ Induced = {A, C, D, E} JUMP TO E
      - E não tem valores por isso salta para o próximo induced: Induced = {A, C, D}, ou seja, para D
    - D = g Induced = {}
      - E = g Induced = {}
        - F = b Induced = {}
          - G ~~r~~, ~~b~~ Induced = {A,C,D,E}
      - E = y Induced = {}
        - F = b Induced = {}
          - G ~~r~~, ~~b~~ Induced = {A,C,D,E}
      - E não tem valores por isso salta para o próximo induced: Induced = {A, C, D}, ou seja para D
    - D Também não tem valores por isso vai para o próximo, ou seja C
  - C Que também não tem e vai para A

A = b Induced = {}

- B = g Induced = {}
  - C = b Induced = {}
    - D = r Induced = {}
      - E = g Induced = {}
        - F = b Induced = {}
          - G = ~~r~~, ~~b~~ Induced = {A,C,D,E}
      - E = y Induced = {}
        - F = b Induced = {}
          - G = ~~r~~, ~~b~~ Induced = {A,C,D,E}
      - E não tem valores então vai para o próximo, Induced = {A,C,D}
    - D = g
      - E = g
        - F = b
          - G = r

---

Conflict-based

A = r JUMP = {}

- B = g JUMP = {}
  - C = b JUMP = {}
    - D = r JUMP = {}
      - E = g JUMP = {}
        - F = b JUMP = {}
          - G Nao tem valores possíveis JUMP = {A, C}
  - C Não tem valores possíveis JUMP = {A}

A = b JUMP = {}

- B = g JUMP = {}
  - C = b JUMP = {}
    - D = r JUMP = {}
      - E = g JUMP = {}
        - F = b JUMP = {}
          - G Não tem valores JUMP = {C, D}
    - D = g JUMP = {}
      - E = g JUMP = {}
        - F = g JUMP = {}
          - G = r JUMP = {}

## e)

G

## f)

C

## g)

B

## h)

True

## i)

G

## j)

E

## k)

F

## l)

True

## m)

G

## n)

False

## o)

E

## p)

False

## q)

D

## r)

True

## s)

G

## t)

C

## u)

A

## v)

False

<br>

# 1.3.3

## a)

O par $x_1$ e $x_4$ é um no-good. Nesse caso podemos fazer a constraint `not (x1 == 1 /\ x4 == 2)`

## b)

It would fail at $x_4$ and wouldn't keep going.

# 1.3.4

$x_1$ = 1

- $x_2$ = 1
  - $x_3$ = ~~1~~, ~~2~~ -> No-good: ($x_1$ = 1, $x_2$ = 1)
- $x_2$ = 2
  - $x_3$ = ~~1~~, ~~2~~ -> No-good: ($x_1$ = 1, $x_2$ = 2)
- $x_2$ = 3
  - $x_3$ = ~~1~~, ~~2~~ -> No-good: ($x_1$ = 1, $x_2$ = 3)

$x_1$ = 2

- $x_2$ = 1
  - $x_3$ = ~~1~~, ~~2~~ -> No-good: ($x_1$ = 2, $x_2$ = 1)
- $x_2$ = 2
  - $x_3$ = 1
    - $x_4$ = ~~1~~, 2
      - $x_5$ = ~~1~~, ~~2~~ -> Leaf dead end -> No-good: ($x_3$ = 1, $x_4$ = 2)
    - $x_4$ não tem mais valores, então temos internal dead end -> No-good: ($x_3$ = 1)
- $x_2$ = 3
  - $x_3$ = 1 -> Não continua porque temos o no-good $x_3$ = 1, ou seja adicionamos ($x_1$ = 2, $x_2$ = 3), aos no-goods
  - $x_3$ = 2 -> Não continua porque temos o no-good ($x_1$ = 2, $x_2$ = 3)

$x_1$ = 3

- Iriamos repetir a coisa toda // TODO TENTA FAZER ESTA BOMBA EM CASA

<br>

# 1.4.1

## a)

Se metermos $x_1$ = True a probabilidade de F ser satisfeito é 100%, se metermos $x_1$ = False a probabilidade de F ser satisfeito é $\dfrac{1}{2^{99}}$

Então P(F ser satisfeito) = P($x_1$ = T) + P($x_1$ = F /\ resto = T) = $\dfrac{1}{2} + \dfrac{1}{2} \times \dfrac{1}{2^{99}}$

## b)

1 flip -> Não confirmado pelo stor, mas deve bué ser lol

<br>

# 1.4.2

## a)

É possível sim, eventualmente todas as hipoteses vão ser consideradas e vai acabar por resolver o problema.

## b)

(!P V !Q), (P V Q)

Se começarmos com P = False e Q = False, ao fazermos flip simultaneamente vai haver sempre uma clausua a falhar.

<br>

# 1.4.3

A = False<br>
B = False<br>
C = True<br>
D = False<br>

Choose: C = False<br>
Choose: A = True

<br>

# 1.5.2

## a)

Variables = {$X_1,X_2,X_3,X_4,X_5,X_6$}<br>
Domain $X_i | i \in \{1, ..., 6\}$ = {0,1,2}<br>
Constraints:

- $X_1 != X_4$
- $X_1 != X_3$
- $X_2 != X_3$
- $X_2 != X_4$
- $X_2 != X_5$
- $X_2 != X_6$
- ...
- $X_1$ < $X_4$
- $X_4$ < $X_3$

## b)

$X_1$ = 0

- $X_2$ = 0
  - $X_3$ = ~~0~~, 1
    - $X_4$ = ~~0~~, ~~1~~, ~~2~~
  - $X_3$ = 2
    - $X_4$ = ~~0~~, 1
      - $X_5$ = ~~0~~, ~~1~~, ~~2~~
    - $X_4$ = ~~2~~
  - $X_3$ = ~~0~~
- $X_2$ = 1

## c)

$X_1$ = 0 -> $D_3$ = {1,2}, $D_4$ = {1,2}

- $X_2$ = 0 -> $D_3$ = {1,2}, $D_5$ = {1,2}, $D_6$ = {1,2}
  - $X_3$ = 1 -> $D_4$ = {~~0~~}, it becomes empty because of the other constraint that $X_4$ < $X_3$
  - $X_3$ = 2 -> $D_4$ = {1}, $D_5$ = {1}, $D_6$ = {1}
    - $X_4$ = {~~0~~}
- $X_2$ = 1 -> $D_3$ = {2}, $D_5$ = $D_6$ = {0, 2}
  - $X_3$ = 2 -> $D_4$ = {1}, $D_5$ = $D_6$ = {0}
    - $X_4$ = 1
      - $X_5$ = 0
        - $X_6$ = 0

## d)

a = {$(X_1, X_3), (X_3, X_1), (X_1, X_4), (X_4, X_1), (X_2, X_3), (X_3, X_2), (X_2, X_5), (X_5, X_2), ...$}

| Id  | Edge         | New Domain     | Back to Queue |
| --- | ------------ | -------------- | ------------- |
| 1   | $(X_1, X_3)$ | ---            | ---           |
| 2   | $(X_3, X_1)$ | ---            | ---           |
| 3   | $(X_1, X_4)$ | $D_1$ = {0, 1} | $(X_3, X_1)$  |
| 4   | $(X_4, X_1)$ | $D_4$ = {1, 2} | $(X_1, X_4)$  |
| 5   | $(X_2, X_3)$ | -              | -             |
| 6   | $(X_3, X_2)$ | -              | -             |
| ..  |              |                |
| 10  |              |                |

Final:<br>
$D_1$ = {0, 1}<br>
$D_4$ = {1, 2}

Dica do stor: Os valores que são adicionados à queue, vão ser um par em que o valor da direita é o valor que estava à esquerda no edge. Como mostra a tabela do exercicio.

## e)

$D_1$ = {0, 1}<br>
$D_2$ = {0, 1, 2}<br>
$D_3$ = {2}<br>
$D_4$ = {1}<br>
$D_5$ = {0, 1, 2}<br>
$D_6$ = {0, 1, 2}<br>

$X_1$ = 0

- $X_2$ = 0
  - $X_3$ = 2
    - $X_4$ = 1
      - $X_5$ = ~~0~~, ~~1~~, ~~2~~
- $X_2$ = 1
  - $X_3$ = 2
    - $X_4$ = 1
      - $X_5$ = 0
        - $X_6$ = 0

## f)

- $X_1$ = 0
  - $X_2$ = 0
    - $X_3$ = ~~0~~, 1
      - $X_4$ = ~~0~~, ~~1~~, ~~2~~ latest = $X_3$ porque remove os valores 1 e 2 do domínio de $X_4$ por causa da constraint $X_4$ < $X_3$. O valor 0 já tinha sido removido por $X_1$.
    - $X_3$ = 2
      - $X_4$ = ~~0~~, 1
        - $X_5$ = ~~0~~, ~~1~~, ~~2~~ latest = $X_4$ porque remove o valor 1 que era o último que restava depois de $X_2$ remover 0 e $X_3$ remover 2.
      - $X_4$ = ~~2~~ latest = $X_3$
    - $X_3$ = {} backtrack
  - $X_2$ = 1
    - $X_3$ = ~~0~~, ~~1~~, 2
      - $X_4$ = ~~0~~, 1
        - $X_5$ = 0
          - $X_6$ = 0

## g)

Primeiro temos de fazer o induced graph para sabermos os induced acestors de cada node:
<img src="Imagens/1.5.2g.png">

- $X_1$ = 0
  - $X_2$ = 0
    - $X_3$ = ~~0~~, 1
      - $X_4$ = ~~0~~, ~~1~~, ~~2~~ $I$ = {$X_1$, $X_2$, $X_3$}
    - $X_3$ = 2
      - $X_4$ = ~~0~~, 1
        - $X_5$ = ~~0~~, ~~1~~, ~~2~~ $I$ = {$X_2$, $X_3$, $X_4$}
      - $X_4$ = ~~2~~ Não tem valores por isso saltamos para o próximo ancestor em $I$.
    - $X_3$ = {} Não tem valores por isso saltamos para o próximo ancestor em $I$.
  - $X_2$ = 1
    - $X_3$ = ~~0~~, ~~1~~, 2
      - $X_4$ = ~~0~~, 1
        - $X_5$ = 0
          - $X_6$ = 0

## h)

Também tem de se fazer o induced graph, mas é igual ao de cima.

- $X_1$ = 0
  - $X_2$ = 0
    - $X_3$ = ~~0~~, 1
      - $X_4$ = ~~0~~, ~~1~~, ~~2~~ JUMP = {$X_1$, $X_3$}
    - $X_3$ = 2
      - $X_4$ = ~~0~~, 1
        - $X_5$ = ~~0~~, ~~1~~, ~~2~~ JUMP = {$X_2$, $X_3$, $X_4$}
      - $X_4$ = ~~2~~ Não tem valores seguintes por isso saltamos para o próximo ancestor em JUMP.
    - $X_3$ = {} Não tem valores seguintes por isso saltamos para o próximo ancestor em JUMP.
  - $X_2$ = 1
    - $X_3$ = ~~0~~, ~~1~~, 2
      - $X_4$ = ~~0~~, 1
        - $X_5$ = 0
          - $X_6$ = 0

<br>

# 1.5.4

## a)

Variables = {S, E, N, D, M, O, R, Y, $X_1$, $X_2$, $X_3$, $X_4$}<br>
Domains:

- $D_i | i \in $ {S, .. Y} = 0..9<br>
- $D_j | i \in $ {$X_1$, .., $X_4$}<br>

Constraints:

- AllDiff(S, ..., Y) <- Não percebo porque é que tem de se fazer isto mas yah.
- D + E = Y + 10 \* $X_1$
- N + R + $X_1$ = E + 10 \* $X_2$
- E + O + $X_2$ = N + 10 \* $X_3$
- S + M + $X_3$ = O + 10 \* $X_4$
- M = $X_4$

<br>

# 1.5.5

## f)

$X_A$ = 1 $latest_A$ = {}

- $X_B$ = ~~1~~, .., ~~8~~ $latest_B$ = A -> JUMP

$X_A$ = 2 $latest_A$ = {}

- $X_B$ = 1 $latest_B$ = {}
  - $X_C$ = ~~1~~,..,~~8~~ $latest_C$ = B -> JUMP
- $X_B$ = ~~2~~,..,~~8~~ backtrack

$X_A$ = 3 $latest_A$ = {}

- $X_B$ = 1 $latest_B$ = {}
  - $X_C$ = ~~1~~,..,~~8~~ $latest_C$ = B -> JUMP
- $X_B$ = ~~2~~,..,~~8~~ backtrack

$X_A$ = 4 $latest_A$ = {}

- $X_B$ = ~~1~~, 2 $latest_B$ = {}
  - $X_C$ = 1 COMPLETED

## g)

A = 1 Induced = {}

- B= ~~1~~, .., ~~8~~ Induced = {A}

A = 2 Induced = {}

- B = 1 Induced = {}
  - C = ~~1~~, ..~~8~~ Induced = {B}
- B= ~~2~~, .. ~~8~~ Induced = {A}

A = 3 Induced = {}

- B = 1 Induced = {}
  - C = ~~1~~, .., ~~8~~ Induced = {B}
- B = ~~2~~, .., ~~8~~ Induced = {}

A = 4 Induced = {}

- B = ~~1~~, 2 Induced = {}
  - C = 1 Completed

# h)

A = 1 Jump = {}

- B = ~~1~~, .., ~~8~~ Jump = {A}

A = 2 Jump = {}

- B = 1 Jump = {}
  - C = ~~1~~, .., ~~8~~ Jump = {B}
- B = ~~2~~, .., ~~8~~ Jump = {A}

A = 3 Jump = {}

- B = 1 Jump = {}
  - C = ~~1~~, .., ~~8~~ Jump = {B}
- B = ~~2~~, .., ~~8~~ Jump = {A}

A = 4 Jump = {}

- B = ~~1~~, 2 Jump = {}
  - C = 1

## i)

Na maioria dos casos o conflict based permite saltar para mais longe na arvore.

<br>

# Exercicio de exame anterior (2021-2022) II Inference in CSP

pairs = {$(x, z), (z, x), (z, y), (y, z)$}

| Id  | Edge     | New Domain  | Back to Queue |
| --- | -------- | ----------- | ------------- |
| 1   | $(x, z)$ | --          | --            |
| 2   | $(z, x)$ | --          | --            |
| 3   | $(z, y)$ | $D_z$ = {2} | $(x, z)$      |
| 4   | $(y, z)$ | --          | --            |
| 5   | $(x, z)$ | $D_x$ = {2} | $(z, y)$      |
| 6   | $(y, z)$ | --          | --            |

In this case we don't need to put $(z, x)$ back in queue in id 5. Because in this algorithm k != i and k != j. In the slides algorithm that is different, and we would need to put $(z, x)$ back in queue for id 5.

# Exercicio 2) de exame (2023-2024)

(P V Q V !R), (!P), (!Q V R)

P = True<br>
Q = True<br>
R = False

Não satisfaz: (!P), (!Q V R)

Choose: P = False -> Não satisfaz: (!Q V R)

Choose: R = True -> Satisfaz todas

<br>

# 2.2.1

## 1)

incr-01-to-10(d2, d1):

- pre-requisitos: { d1 = 1, d2 = 0 }
- effects: { d1 = 0, d2 = 1 }

## 2)

{ incr-x0-to-x1, incr-01-to-10, incr-x0-to-x1 }

<br>

# 2.2.2

## a)

O caminho mais rápido tem 4 iterações

| Step | Action               | State Change                    |
| ---- | -------------------- | ------------------------------- |
| 1    | take(r1, loc1, c1)   | pos(c1) = r1, cargo(r1) = c1    |
| 2    | move(r1, loc1, loc2) | pos(r1) = loc2                  |
| 3    | put(r1, loc2, c1)    | pos(c1) = loc2, cargo(r1) = nil |
| 4    | VERIFICA SE CONSEGUE |                                 |

NO EXAME é preciso escrever o estado todo ao escolher cada ação e não apenas o que mudou, para além disso é preciso mostrar todas as ações possíveis e não apenas aquela que se vai escolher quando se faz o algoritmo Forward-Search. Por fim é preciso mostrar também $\pi$, o plano que se escolheu.

## b)

g = { pos(c1) = loc2, pos(c2) = loc2 }

$A^{-1}$

- put(r1, loc2, c1) g1
- put(r1, loc2, c2) g2
- put(r2, loc2, c1) g3
- put(r2, loc2, c2) g4

g1 = { pos(c1) = r1, pos(r1) = loc2, pos(r2) = loc2, cargo(r1) = c1 }

$A^{-2}$

- put(r2, loc2, c2) g5
- move(r1, loc1, loc2) g6
- take(r1, loc2, c1) g7

g6 = { pos(c1) = r1, pos(r1) = loc1, pos(r2) = loc2, cargo(r1) = c1 }

$A^{-3}$

- put(r2, loc2, c2) g8
- take(r1, loc1, c1) g9

g9 = { loc(r1) = loc1, pos(c1) = loc1, cargo(r1) = nil, pos(c2) = loc2 }

$S_0$ contains g9 por isso tá completo

## c)

Atoms in $\hat{s}_0$ = $s_0$

- loc(r1) = loc1
- loc(r2) = loc2
- cargo(r1) = nil
- cargo(r2) = nil
- pos(c1) = loc1
- pos(c2) = loc2

A1

- move(r1, loc1, loc2)
- move(r2, lo1, loc2)
- take(r1, loc1, c1)
- take(r2, loc2, r2)

Atoms in $\hat{s}_1$

- loc(r1) = loc2
- loc(r2) = loc1
- cargo(r1) = c1
- cargo(r2) = c2
- pos(c1) = r1
- pos(c2) = r2
- $\hat{s}_0$

A2

- move(r1, loc2, loc1)
- move(r2, loc2, loc1)
- put(r1, loc2, c1)
- put(r2, loc1, c2)
- put(r1, loc1, c1)
- put(r2, loc2, c2)
- take(r1, loc2, c2)
- take(r2, loc1, c1)
- A1

Atoms in $\hat{s}_2$

- pos(c1) = loc2
- pos(c2) = loc1
- cargo(r1) = c2
- cargo(r2) = c1
- pos(c1) = r2
- pos(c2) = r1
- $\hat{s}_1$

<img src="Imagens/2.2.2.c.png">

_Comecei a fazer apenas as precondições e efeitos relevantes a partir de $\hat{s}_1$, para o desenho ser legivel._

Para voltar atrás só precisamos de ver por quantas ações passamos até atingirmos o nosso goal desde $\hat{s}_0$. E assim chegamos a que $h^{FF}(s_0) = 3$

## d)

queue <- { pos(c1) = loc2 } // só adicionamos isto à queue porque a outra landmark do goal já é cumprida por $s_0$.

queue = { ~~pos(c1) = loc2~~ }, Landmarks = { pos(c1) = loc2 }

R = { put(r1, loc2, c1), put(r2, loc2, c1) } // $s_0$ não satisfaz as precondições de nenhuma ação presente em R, por isso continuamos.

N = ?

$\hat{s}_0$

- loc(r1) = loc1
- loc(r2) = loc2
- cargo(r1) = nil
- cargo(r2) = nil
- pos(c1) = loc1
- pos(c2) = loc2

$A_1$ \ R

- move(r1, loc1, loc2)
- move(r2, loc2, loc1)
- take(r1, loc1, c1)
- take(r2, loc2, c2)

$\hat{s}_1$

- loc(r1) = loc2
- loc(r2) = loc1
- cargo(r1) = c1
- cargo(r2) = c2
- pos(c1) = r1
- pos(c2) = r2
- $\hat{s}_0$

$A_2$ \ R

- move(r1, loc2, loc1)
- move(r2, loc2, loc1)
- put(r2, loc1, c2)
- put(r1, loc1, c1)
- put(r2, loc2, c2)
- take(r1, loc2, c2)
- take(r2, loc1, c1)
- $A_1$ \ R

$\hat{s}_2$

- pos(c1) = loc2
- pos(c2) = loc1
- cargo(r1) = c2
- cargo(r2) = c1
- pos(c1) = r2
- pos(c2) = r1
- $\hat{s}_1$

$A_3$ \ R

- $A_2$ \ R

$\hat{s}_3$

- $\hat{s}_2$

Como $\hat{s}_2$ = $\hat{s}_3$, podemos parar e N = R = { put(r1, loc2, c1), put(r2, loc2, c1) }

Preconditions = { loc(r1) = loc2 /\ pos(c1) = r1 }, { loc1(r2) = loc2 /\ pos(c1) = r2 }

Q = Preconditions

L = { pos(c1) = loc2, loc(r1) = loc2 /\ pos(c1) = r1 }

R: { move(r1, loc1, loc2), take(r1, loc1, c1), take(r1, loc2, c1) }

R is satisfied by $s_0$ -> take(r1, loc1, c1)

$h^{sl}(s_0)$ = len(L) = 2

<br>

# 2.2.3

## a)

Precisamos de 4 ações, porque com apenas 2 não seria possível considerar o caso em que estamos a colocar e tirar os blocos da mesa.

## b)

Sim, porque sem essa variável seria impossível de garantir que só se pode pegar em um bloco de cada vez. Para além disso não saberiamos que bloco é que estamos a colocar sem essa variável.

## c)

Há 2 estados, um deles é o normal, chegando à solução pela maneira normal. O outro seria

s1 = { top(a) = b, top(b) = a, top(c) = nil, loc(a) = b, loc(b) = hand, loc(c) = table, holding = b }

Este estado é impossível de obter, mas acho que se consideram estados impossíveis.

<br>

# 2.2.5

## a)

A thread é que se $a_2$ vier antes de $a_1$ isso iria mudar o value(bar) para 1 e assim a precondition de $a_1$ não vai ser satisfeita. O resolver é remover a seta que vai do start até ao $a_2$ e criar uma seta desde $a_1$ até $a_2$.

## b)

Sim, uma execução possível seria:

- Criar $a_1$ para satisfazer o open goal value(foo) = 5, em finish.
- Criar $a_2$ para satisfazer o open goal value(bar) = 1, em finish.
- Criar um causal link com o efeito value(bar) = 5, em start, para o open goal value(bar) = 5, em $a_1$.

## c) + d)

Em teoria dá para ir infinitamente se ficarmos a stackar ações de forma a termos sempre um open goal.

## e)

Only one, the smaller.

## f)

<img src="Imagens/2.2.5.f.png">

Seguindo a ordem: Vermelho, Laranja, Rosa, Azul

<br>

# 2.2.6

## a)

As duas flaws são os open goals `loc(r1) = d2` e `loc(r2) = d1`.

## b)

<img src="Imagens/2.2.6 b.png">

<br>

# 2.2.7

## 2.

São os open goals `at(home)`, `have(bread)` e `have(flowers)`.

## 3.

<img src="Imagens/2.2.7 3.png">

1. Vermelho escuro -> Resolve o open goal `at(home)` de $a_g$.
2. Laranja -> Resolve o open goal `have(bread)` de $a_g$.
3. Verde -> Resolve o open goal `at(y)` de buy(y, bread). Faz com que y = bakery.
4. Azul turquesa -> Resolve a threat de o go(z, bakery) acontecer depois de go(x, home) e modificar o effect e go(x, home).
5. Azul escuro -> Resolve o open goal `at(x)` de go(x, home). Faz com que x = bakery.
6. Roxo -> Resolve o open goal `have(flowers)` de $a_g$.
7. Castanho -> Resolve o open goal `at(w)` de buy(w, flowers). Faz com que w = florist.
8. Rosa -> Resolve a threat de o go(k, bakery) acontecer entre go(z, bakery) e buy(bakery, bread). Força a que aconteça antes.
9. Vere lima -> Resolve o open goal `at(z)` em go(z, bakery). Faz com que z = florist.
10. Cinzento -> Resolve o open goal `at(k)` em go(k, florist). Faz com que k = home.
11. Vermelho claro -> Resolve o open goal `sells(florist, flowers)` em buy(florist, flowers).
12. Vermelho claro (fiquei sem cores) -> Resolve o open goal `sells(bakery, bread)` em buy(bakery, bread).

<br>

# Exercicio 1 Grupo 5 Exame 2023/2024

## a)

É consistent porque os assingments das variáveis:

$t_1$ = 1<br>
$t_2$ = 2<br>
$r$ = r1<br>
$t_3$ = 3<br>
$t_4$ = 4<br>
$l$ = loc1<br>

Não é seguro porque

$t_1$ = 1<br>
$t_2$ = 3<br>
$r$ = r1<br>
$t_3$ = 2<br>
$t_4$ = 4<br>
$l$ = loc2<br>

## b)

$r$ != r1<br>
$t_2$ < $t_3$<br>
$l$ = loc1

<br>

# 2.3.1

Exemplo consistent:

Se tivermos (**$T, C$**) = ([$t_1$,$t_2$] loc(r1) = loc1 e [$t_3$, $t_4$] loc(r1) = (loc1, loc2) , {$t_1$ < $t_2$ < $t_3$, $t_4$})

($T,C$) vai ser sempre consistent porque os intervalos [$t_1$,$t_2$] e [$t_3$,$t_4$] nunca se intersetam.

Exemplo não consistente:

Se tivermos (**$T, C$**) = ([$t_1$,$t_2$] loc(r1) = loc1 e [$t_3$, $t_4$] loc(r1) = loc2 , {$t_1$ < $t_3$ < $t_4$ < $t_2$})

Neste caso $C$ é consistent, mas ($T,C$) não é porque há dois valores para loc(r1) no intervalo [$t_3$, $t_4$] para quaisquer que sejam os t's.

# 2.3.2

| k   | i   | j   | $r_{ij}$ | $r_{ik}$ | $r_{kj}$            | $r_{ik} \cdot r_{kj}$ | $r_{ij} \cap [r_{ik} \cdot r_{kj}]$ |
| --- | --- | --- | -------- | -------- | ------------------- | --------------------- | ----------------------------------- |
| 1   | 2   | 3   | [4, 7]   | [-5, -1] | [$-\infin, \infin]$ | [$-\infin, \infin]$   | [4, 7]                              |
| 1   | 2   | 4   | [5, 8]   | [-5, -1] | [2, 6]              | [-3, 5]               | [5, 5]                              |
| 1   | 3   | 4   | ...      |          |                     |
| ... |
| 2   | 3   | 4   | [4, 6]   | [-7, -4] | [5, 5]              | [-2, 1]               | {}                                  |

Como encontramos uma inconsistencia (interseção vazia) o algoritmo acaba e sabemos que a STN é inconsistente.

<br>

# 2.3.5

| k   | i   | j   | $r_{ij}$           | $r_{ik}$           | $r_{kj}$           | $r_{ik} \cdot r_{kj}$ | $r_{ij} \cap [r_{ik} \cdot r_{kj}]$ |
| --- | --- | --- | ------------------ | ------------------ | ------------------ | --------------------- | ----------------------------------- |
| 1   | 2   | 3   | [$-\infin,\infin$] | [-2,-1]            | [$-\infin,\infin$] | [$-\infin,\infin$]    | [$-\infin,\infin$]                  |
| 1   | 2   | 4   | [3,4]              | [-2,-1]            | [$-\infin,\infin$] | [$-\infin,\infin$]    | [3,4]                               |
| 1   | 2   | 5   | [$-\infin,\infin$] | [-2,-1]            | [6,7]              | [4,6]                 | [4,6]                               |
| 1   | 3   | 4   | [1,2]              | [$-\infin,\infin$] | [$-\infin,\infin$] | [$-\infin,\infin$]    | [1,2]                               |
| 1   | 3   | 5   | [4,5]              | [$-\infin,\infin$] | [6,7]              | [$-\infin,\infin$]    | [4,5]                               |
| 1   | 4   | 5   | [$-\infin,\infin$] | [$-\infin,\infin$] | [6,7]              | [$-\infin,\infin$]    | [$-\infin,\infin$]                  |
| 2   | 1   | 3   | [$-\infin,\infin$] | [1,2]              | [$-\infin,\infin$] | [$-\infin,\infin$]    | [$-\infin,\infin$]                  |
| 2   | 1   | 4   | [$-\infin,\infin$] | [1,2]              | [3,4]              | [4,6]                 | [4,6]                               |
| 2   | 1   | 5   | [6,7]              | [1,2]              | [4,6]              | [5,7]                 | [6,7]                               |
| 2   | 3   | 4   | [1,2]              | [$-\infin,\infin$] | [3,4]              | [$-\infin,\infin$]    | [$-\infin,\infin$]                  |
| 2   | 3   | 5   | [4,5]              | [$-\infin,\infin$] | [4,6]              | [$-\infin,\infin$]    | [4,5]                               |
| 2   | 4   | 5   | [$-\infin,\infin$] | [-4,-3]            | [4,6]              | [0,3]                 | [0,3]                               |
| 3   | 1   | 2   | [1,2]              | [$-\infin,\infin$] | [$-\infin,\infin$] | [$-\infin,\infin$]    | [1,2]                               |
| 3   | 1   | 4   | [4,6]              | [$-\infin,\infin$] | [1,2]              | [$-\infin,\infin$]    | [4,6]                               |
| 3   | 1   | 5   | [6,7]              | [$-\infin,\infin$] | [4,5]              | [$-\infin,\infin$]    | [6,7]                               |
| 3   | 2   | 4   | [3,4]              | [$-\infin,\infin$] | [1,2]              | [$-\infin,\infin$]    | [3,4]                               |
| 3   | 2   | 5   | [4,6]              | [$-\infin,\infin$] | [4,5]              | [$-\infin,\infin$]    | [4,6]                               |
| 3   | 4   | 5   | [0,3]              | [-2,-1]            | [4,5]              | [2,4]                 | [2,3]                               |
| 4   | 1   | 2   | [1,2]              | [4,6]              | [-4,-3]            | [0,3]                 | [1,2]                               |
| 4   | 1   | 3   | [$-\infin,\infin$] | [4,6]              | [-2,-1]            | [2,5]                 | [2,5]                               |
| 4   | 1   | 5   | [6,7]              | [4,6]              | [2,3]              | [6,9]                 | [6,7]                               |
| 4   | 2   | 3   | [$-\infin,\infin$] | [3,4]              | [-2,-1]            | [1,3]                 |
| 4   | 2   | 5   | [4,6]              | [3,4]              | [2,3]              | [5,7]                 | [5,6]                               |
| 4   | 3   | 5   | [4,5]              | [1,2]              | [2,3]              | [3,5]                 | [4,5]                               |
| 5   | 1   | 2   | [1,2]              | [6,7]              | [-6,-5]            | [0,2]                 | [1,2]                               |
| 5   | 1   | 3   | [2,5]              | [6,7]              | [-5,-4]            | [1,3]                 | [2,3]                               |
| 5   | 1   | 4   | [4,6]              | [6,7]              | [-3,-2]            | [3,5]                 | [4,5]                               |
| 5   | 2   | 3   | [1,3]              | [5,6]              | [-5,-4]            | [0,2]                 | [1,2]                               |
| 5   | 2   | 4   | [3,4]              | [5,6]              | [-3,-2]            | [2,4]                 | [3,4]                               |
| 5   | 3   | 4   | [1,2]              | [4,5]              | [-3,-2]            | [1,3]                 | [1,2]                               |
| k   | i   | j   | $r_{ij}$           | $r_{ik}$           | $r_{kj}$           | $r_{ik} \cdot r_{kj}$ | $r_{ij} \cap [r_{ik} \cdot r_{kj}]$ |

1 -> 2 [1,2]<br>
1 -> 3 [2,3]<br>
1 -> 4 [4,5]<br>
1 -> 5 [6,7]<br>
2 -> 3 [1,2]<br>
2 -> 4 [3,4]<br>
2 -> 5 [5,6]<br>
3 -> 4 [1,2]<br>
3 -> 5 [4,5]<br>
4 -> 5 [2,3]
