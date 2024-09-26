## 1.1.1
a) Variaveis: cada um dos quadrados da grid.<br>
Dominio: Para cada uma delas o dominio é de 1 a n<br>
Constraints:
- todas as linhas precisam de que os seus elementos sejam diferentes.
- todas as colunas precisam de que os seus elementos sejam diferentes.

### Solução do stor:<br>
Variaveis de celula -> X = { x_{ij} | i <= i <= n, 1 <= j <= n }<br>
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
- $x_{ij} = -x_{ji}
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

| Edges | Domain Change | New edges |
| ----- | ------------- | --------- |
| $x_0, x_1$ | $x_0$ = {3,4,7} | Não atualiza por tar em processo |
| $x_1, x_0$ | $x_1$ = {2,4,6} | ($x_0, x_1$)
| $x_0, x_2$ | ---------- | --------- |
| $x_2, x_0$ | $x_2$ = {3,4,7} | ($x_0, x_1$) |
| $x_0, x_3$ | -------- | ------- |
| $x_3, x_0$ | -------- | ------- |
| $x_0, x_1$ | -------- | ------- |
| $x_0, x_2$ | -------- | ------- |

$D_{x_0}$ = {3,4,7}
$D_{x_1}$ = {2,4,6}
$D_{x_2}$ = {3,4,7}
$D_{x_3}$ = {6,7,8}

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
| A     | -       |
| B     | -       |
| C     | -       |

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
| E | $D_D$ = {g,b} |
| D | - |
| C | $D_B$ = {r,g} |
| B | - |
| A | - |

## b)

| Nodes | Domain Changes | Constraints | Edge |
| ----- | -------------- | ----------- | ---- |
| E | $D_D$ = {g,b} | - | - |
| D | - | $R_{BC}$ = {(r, b)} | - |
| C | $D_B$ = {r} | - | - |
| B | - | - | - |
| A | - | - | - |

<br>

# 1.2.9

| Nodes | Domain Changes |
| ----- | -------------- |
| $x_4$ | $D_3$ = {white, blue} |
| $x_3$ | $D_1$ = {white} |
| $x_2$ | --- |
| $x_1$ | --- |

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
- $X_2$ = 1 -> $D_3$ = {2}, $D_5$ = $D_6$ = {0, 1}
  - $X_3$ = 2 -> $D_4$ = {1}, $D_5$ = $D_6$ = {0}
    - $X_4$ = 1
      - $X_5$ = 0
        - $X_6$ = 0

## d)

a = {$(X_1, X_3), (X_3, X_1), (X_1, X_4), (X_4, X_1), (X_2, X_3), (X_3, X_2), (X_2, X_5), (X_5, X_2), ...$}

| Id | Edge | New Domain | Back to Queue |
| -- | ---- | ---------- | ------------- |
| 1  | $(X_1, X_3)$ | --- | --- |
| 2  | $(X_3, X_1)$ | --- | --- |
| 3  | $(X_1, X_4)$ | $D_1$ = {0, 1} | $(X_3, X_1)$ |
| 4  | $(X_4, X_1)$ | $D_4$ = {1, 2} | $(X_1, X_4)$ |
| 5  | $(X_2, X_3)$ | - | - |
| 6  | $(X_3, X_2)$ | - | - |
| .. | | |
| 10 | | |

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


<br>

# 1.5.4

## a)

Variables = {S, E, N, D, M, O, R, Y, $X_1$, $X_2$, $X_3$, $X_4$}<br>
Domains:
- $D_i | i \in $ {S, .. Y} = 0..9<br>
- $D_j | i \in $ {$X_1$, .., $X_4$}<br>

Constraints:
- AllDiff(S, ..., Y) <- Não percebo porque é que tem de se fazer isto mas yah.
- D + E = Y + 10 * $X_1$
- N + R + $X_1$ = E + 10 * $X_2$
- E + O + $X_2$ = N + 10 * $X_3$
- S + M + $X_3$ = O + 10 * $X_4$
- M = $X_4$

<br>

# Exercicio de exame anterior (2021-2022) II Inference in CSP

pairs = {$(x, z), (z, x), (z, y), (y, z)$}

| Id | Edge | New Domain | Back to Queue |
| -- | -- | -- | -- |
| 1  | $(x, z)$ | -- | -- |
| 2 | $(z, x)$ | -- | -- |
| 3 | $(z, y)$ | $D_z$ = {2} | $(x, z)$
| 4 | $(y, z)$ | -- | -- |
| 5 | $(x, z)$ | $D_x$ = {2} | $(z, y)$ |
| 6 | $(y, z)$ | -- | -- |

In this case we don't need to put $(z, x)$ back in queue in id 5. Because in this algorithm k != i and k != j. In the slides algorithm that is different, and we would need to put $(z, x)$ back in queue for id 5.