# Directional consistency

## Backtrack-free search

### Definition

Uma constraint network é backtrack-free quando todos os caminhos têm solução e não é preciso voltar atrás numa decisão para a mudar porque não há valores consistentes no dominio das variaveis seguintes.

## Ordered graph

$d = (v_1,..., v_n)$ ordering of the nodes.

### Definitions

- The nodes are added from bottom to top.
- Parents of a node $v$ are the nodes adjacent to $v$ that precede $v$ in $d$.
- The width of a node is the number of parents.
- The width of an ordering is the maximum width over all nodes.
- The width of a graph is the minimum width over all the orderning of the graph.

### Example

<img src="Imagens/Aula 4 Ordering example.png">

## Induced width and induced graph

The induced graph of (G, d) is an ordererd graph (G*, d). G* is created by:

1. Processing nodes of G from last to first (top to bottom)
2. Connecting all of the parents of a node after processing.

Induced width of (G, d), w*(d), is the width of (G*, d)
Induced width of a graph, w\*, is still the minimmum induced width over all of its orderings.

### Example

<img src="Imagens/Aula 4 Induced graph example.png">
Coincidentemente o resultado é igual ao do gráfico G, mas podia não ser.

### Observations

- A width-1 graph cannot have a cycle.
- Given an ordering with width 1, the graph has induced width 1.
- A graph is a tree iff it has induced witdh of 1.

## Algorithms to find minimum width

### Min-Width

<img src="Imagens/Aula 4 Min Width.png">

#### Explanation

The nodes with most neighbours stay in the first position of the ordering, so they will have no parents. In the other hand, nodes with few neighbours are in the last positions of the ordering because they are not that problematic.

### Min-Induced-Width (greedy)

<img src="Imagens/Aula 4 Min-Induced-Width.png">

Is the same as the above one, but with the part where parents are connected.

<br>

## Directional arc-consistency

### Definition

Uma constraint network é directional arc-consistent para uma certa ordem $d$ sse cada variavel $x_i$ é arc-consistent em relação a $x_j$ sendo que $i < j$

### Algorithm

<img src="Imagens/Aula 4 DAC.png">

#### Explanation

Percorre a network seguindo a ordem reversa de $d$. Dentro desse loop percorre também todos os elementos que estão acima na lista aplicando revise entre estes elementos. Seria algo como:

```
for (int i = n - 1; i >= 0; i--) {
    for (int j = i - 1; j >= 0; j--) {
        Revise(x_i, x_j);
    }
}
```

#### Example

<img src="Imagens/Aula 4 DAC example.png">

## Directional path-consistency

### Definition

Uma constraint network é directional path-consistent para uma certa ordem $d$ sse cada par de variaveis {$x_i,x_j$} é path-consistent em relação a $x_k$ sendo que $k > i, j$.

### Algorithm

<img src="Imagens/Aula 4 DPC.png">

#### Explanation

Aplica-se o mesmo que no arc-consistency, e para além disso, enquanto se percorre o loop de fora também se aplica um o Revise-3 entre a variavel do loop de fora e todos os pares de variaveis que estejam numa posição menor em $d$.

### Example

<img src="Imagens/Aula 4 DPC example.png">

<br>

!!! Depois do slide 28 parece meio irrelevante !!!
