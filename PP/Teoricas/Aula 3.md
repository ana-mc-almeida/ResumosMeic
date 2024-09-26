## Constraint Propagation

Advantages:
- Narrows search space
- Constraints can become explicit enough to go directly to solution

Example:<br>
R = { x = y, y = z }<br>
Infer x = z, resulting R' = { x = y, y = z, x = z }<br>
R and R' have the same solutions, but R' is more explicit

## Arc-consistency

- Infer constraints on pairs of variables.
- Makes sure that every value in the domain of a variable has a leagal match in the domain of any related variable. 
- Decreases the size of the variables' domains.

### Definition

A variable $x_i$ is arc-consistent relative to $x_j$ iff for every value $a_i$ belonging to $D_i$ there exists a value $a_j$ belonging to $D_j$ such that $(a_i, a_j)$ belongs to $R_{ij}$.<br>
The subnetwork defined by {$x_i, x_j$} is arc-consistent iff $x_i$ is arc-consistent relative to $x_j$ and $x_j$ is arc-consistent relative to $x_i$.<br>
A network of constraints is called arc-consistent iff all of its arcs are arc-consistent.

### Example
- Variables x, y
- Domains { 1, 2, 3 }
- $R_{xy}$ = { x < y }
- $R_{xy}$ is not arc-consistent to x = 3 because there is no consistent matching in $D_y$.
- The same goes to y = 1. There is no consistent matching in $D_x$.

<img src="Imagens/Aula3 Arc-consistency.png">

### Revise Procedure

<img src="Imagens/Aula3 Revise Procedure.png">

#### Explanation:
This procedure just checks for each value of the domain of a variable $x$ if there any $y$ such that $(x, y)$ belong to $R_{xy}$. If there is no value that satisfies this condition, then the value is removed from the variable domain.

#### Disadvantages
The problem with this approach is that it will just run each variable once. This can be a problem if a variable domain changes later on because of another variable in the network.

### AC-1 - Brute force algorithm

<img src="Imagens/Aula3 AC-1.png">

#### Explanation:
This algorithm just loops through every pair of variables that participates in a constraint applying revise procedure. It will keep looping until no domain is changed.

#### Disadvantages
The problem with AC-1 is that it is really slow. There is no reason to (re)process ALL the constraints.

### AC-3 - An efficient algorithm

<img src="Imagens/Aula3 AC-3.png">

#### Explanation:
In this case, instead of looping arround every pair of variables that participate in a constraint like AC-1 does, we just follow a queue. The queue starts with every pair of variables that participate in a constraint, but then only the ones that have their domain changed, by applying revise procedure, are again added to the queue.

#### Advantages
In this case we can have the same results, but it is not necessary to loop through arcs that are not being changed anymore.

### AC-4

<img src="Imagens/Aula3 AC-4.png">

#### Explanation:
Basicamente em vez de guardar as coisas numa queue, tem duas listas, uma $S_{(x_i,a_i)}$ que contem todas as conexões. E uma outra $counter(i, a_i, j)$ que é o número de ligações entre o valor $a_i$ da variavel $x_i$ e todos os valores da variavel $x_j$.<br>
Primeiro para cada counter checka-se se $counter(x_i, a_i, x_j) == 0$. E se for quer dizer que não há conexões entre o valor $a_i$ e a variavel $x_j$. E então vai ser adicionado a uma lista. Depois de todos os counters serem analisados, vai percorrer-se a lista e decrementar os counters dos valores que estavam ligados àquela variável. No caso de esse counter chegar a zero, esse par variavel-valor são adicionados à lista.  

#### Coisas suspeitas para perguntar à prof:
- Wtf porque é que isto diminui o espaço???
- Para que é que serve a lista M? Parece só tar lá a guardar cenas que não vão ser usadas para nada no futuro lol.


## Path-consistency

- Infer constraints based on trios of variables.
- Ensure that any consistent solution to a 2-variable subnetwork is extendible to any 3rd variable.
- Creates or removes new constraints.

<br>

### Definition

A two-variable set {$x_i,x_j$} is path-consistent relative to variable $x_k$ iff for every consistent assignment ({$x_i, a_i$}, {$x_j, a_j$}) there is a value $a_k \in D_k$ such that the assignment ({$x_i, a_i$}, {$x_k, a_k$}) is consistent and ({$x_j, a_j$}, {$x_k, a_k$}) is consistent.

<br>

### Example

<img src="Imagens/Aula3 PC Example.png">

<br>

### Revise-3

<img src="Imagens/Aula3 Revise-3.png">

Basically the same as the Revise procedure from arc-consistency, but for 3 variables instead of 2.

<br>

### PC-1

<img src="Imagens/Aula3 PC-1.png"><br>
Similar to AC-1, but using Revise-3 instead of Revise from AC.

<br>

### PC-2

<img src="Imagens/Aula3 PC-2.png"><br>
Similar to AC-3.

<br>

!!! A PARTIR DO SLIDE 35 NãO ANDEI PARA AQUI A METER PORQUE NEM PERCEBO SE É SUPOSTO LOL !!!