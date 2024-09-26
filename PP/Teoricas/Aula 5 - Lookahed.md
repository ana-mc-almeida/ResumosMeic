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

VOU NO SLIDE 32