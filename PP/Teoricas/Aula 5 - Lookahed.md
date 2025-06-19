

<!-- toc -->

- [Backtracking](#backtracking)
    + [Example](#example)
- [Look-ahead schemes](#look-ahead-schemes)
- [Forward Checking](#forward-checking)
    + [Example](#example-1)
- [Arc-consistency look-ahead](#arc-consistency-look-ahead)
- [Full lookahead](#full-lookahead)
- [Partial lookahead](#partial-lookahead)
    + [Example](#example-2)
- [Dynamic lookahead value orderings](#dynamic-lookahead-value-orderings)
- [Lookahead variable orderings](#lookahead-variable-orderings)
    + [Example](#example-3)

<!-- tocstop -->

# Backtracking

Percorre a árvore depth-first.

### Example

<img src="Imagens/Aula5 Backtracking example.png">

<img src="Imagens/Aula5 Backtracking example d1.png">

<img src="Imagens/Aula5 Backtracking example d2.png">

<br>

# Look-ahead schemes

- Atribuição de variável: Escolhe-se a variável que está em mais constraints.
- Atribuição de valor: Escolhe-se o valor com mais suporte dessa variável.

<br>

# Forward Checking

Propaga o efeito de atribuir um valor a uma variável, para as variáveis que estão numa constraint, separadamente. Se uma variável ficar com o seu dominio vazio, a procura volta atrás.

### Example

Quando se escolhe $x_1$ = red:
<img src="Imagens/Aula5 Forward checking example.png">

<br>

# Arc-consistency look-ahead

Para cada atribuição de um valor a uma variável, força-se que a network seja full arc-consistency.

- É mais forte do que forward checking, mas existe um loop que faz o AC-1 e torna o processo mais demorado.

<img src="Imagens/Aula5 arc-consistency lookahed.png">

<br>

# Full lookahead

Neste algoritmo faz-se apenas um full lookahead arc-consistency por todas as próximas variáveis.

# Partial lookahead

Aplica-se um directional arc-consistency para as futuras variáveis.

### Example

<img src="Imagens/Aula5 partial lookahead example.png">

<br>

# Dynamic lookahead value orderings

- Min-conflicts heuristic -> Escolhe o valor da variável que remove o menor número de valores dos dominios das variáveis futuras.

- Max-domain-size heuristic -> Escolhe o valor que vai fazer originar o maior minimo, nos dominios das variáveis futuras.

<br>

# Lookahead variable orderings

- Fail-first -> Escolhe-se a variável que é mais provável falhar primeiro, para diminuir o search space.

- Dynamic variable ordering (DVO) with forward checking (DVFC) -> Escolhe a variável com menor dominio.

### Example

<img src="Imagens/Aula5 lookahead variable ordering.png">
