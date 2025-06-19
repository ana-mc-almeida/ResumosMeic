

<!-- toc -->

    + [RPG - Relaxed Planning Graph](#rpg---relaxed-planning-graph)
    + [Admissible Heuristic](#admissible-heuristic)
    + [Minimal Set of Actions](#minimal-set-of-actions)
    + [Relaxed Solution](#relaxed-solution)
- [Optimal Relaxed Solution Heuristic](#optimal-relaxed-solution-heuristic)
    + [Exemplo](#exemplo)
- [Fast-Forward Heuristic](#fast-forward-heuristic)
    + [Algoritmo (demasiado complexo para se perceber sem exemplo)](#algoritmo-demasiado-complexo-para-se-perceber-sem-exemplo)
    + [Exemplo](#exemplo-1)
      - [Exemplo para $h^{FF}(s_1)$](#exemplo-para-hffs_1)
      - [Exemplo para $h^{FF}(s_2)$](#exemplo-para-hffs_2)
  * [Landmark Heuristic](#landmark-heuristic)
    + [Algoritmo (Nem vale a pena tentar perceber sem exemplo '-')](#algoritmo-nem-vale-a-pena-tentar-perceber-sem-exemplo--)
    + [Exemplo](#exemplo-2)

<!-- tocstop -->

### RPG - Relaxed Planning Graph

São os gráficos que tamos a usar aqui, só para me lembrar desta sigla sus '-'

### Admissible Heuristic

O valor estimado é sempre igual ou superior ao valor real, nunca inferior.

### Minimal Set of Actions

É diferente do minimo set of actions para atingir um goal state. Minimal quer dizer que se alguma ação do set de ações for removida, deixa de conseguir atingir um goal state.

### Relaxed Solution

Uma Relaxed solution é uma solução que considera que um atributo pode assumir 2 valores ao mesmo tempo. Por exemplo:

<img src="Imagens/Aula10 Relaxed Solution Example.png">

<br>

# Optimal Relaxed Solution Heuristic

$h^+(s)$ = custo minimo de todas as relaxed solutions para um certo problema.

### Exemplo

Considerando a imagem acima, para o estado $s_0$ há duas ações possíveis:

- move(r1, d3, d1)
- move(r1, d3, d2)

A imagem já mostra que pela ação move(r1, d3, d1) vão ser precisas apenas mais 2 ações para se atingir um goal state. No entanto, se seguirmos a ação move(r1, d3, d2) ainda vão ser precisas 3 ações para se atingir um goal state. Nesse caso, a heuristica Optial Relxade Solution vai escolher a ação move(r1, d3, d1)

<br>

# Fast-Forward Heuristic

Não é admissible !!!

### Algoritmo (demasiado complexo para se perceber sem exemplo)

A primeira coisa a fazer, em cada iteração é verificar se o goal state é atingido. Se tiver sido, então retorna-se o minimal set de ações até se conseguir chegar a um goal state.<br>
Se o goal state não tiver sido atingido é preciso expandir o estado em que se está, sendo que o primeiro é o estado que a heuristica se refere. Esta expansão consiste em listar todas ações possíveis. Após listar as ações possíveis, listam-se os estados obtidos ao aplicar essas ações ao estado anterior. Não se removem os atomos do estado anterior, para esta heuristica consideram-se relaxed solutions.

### Exemplo

Vou considerar o seguinte exemplo:

<img src="Imagens/Aula10 Fast Forward Heuristic Example.png">

E vou calcular o $h^{FF}(s_1)$ e o $h^{FF}(s_2)$

#### Exemplo para $h^{FF}(s_1)$

Nota: $\hat{s}_0 = s_1$

Os Atoms que temos em $\hat{s}_0$ são:

- loc(r1) = d1
- loc1(c1) = d1
- cargo(r1) = nil

E tendo em conta estes atoms, temos as seguintes ações válidas:

- move(r1, d1, d2)
- move(r1, d1, d3)
- load(r1, c1, d1)

Ou seja, acabamos a iteração no estado $\hat{s}_1$ com os atoms:

- loc(r1) = d2
- loc(r1) = d3
- loc(c1) = r1
- cargo(r1) = c1
- loc(r1) = d1 -> que veio de $\hat{s}_0$
- loc1(c1) = d1 -> que veio de $\hat{s}_0$
- cargo(r1) = nil -> que veio de $\hat{s}_0$

Em $\hat{s}_1$ encontramos um subset que é o goal state:

- loc(r1) = d3
- loc(c1) = r1

Agora temos de extrair a solução. Para isso temos de encontrar um conjunto de ações minimal.

<img src="Imagens/Aula10 Fast-Forward Heuristic Example2.png">

Neste esquema, onde estão representadas as precondições e os efeitos com traços, podemos ver que para chegar desde o estado $\hat{s}_0$ até ao estado $\hat{s}_1$ precisamos de aplicar duas ações. Logo, $h^{FF}(s_1)$ = 2

#### Exemplo para $h^{FF}(s_2)$

Fazendo a expansão das variáveis, de uma maneira inteligente, obtemos:

<img src="Imagens/Aula10 Fast-Forward Heuristic Example3.png">

E extraindo o resultado conseguimos o esquema:

<img src="Imagens/Aula10 Fast-Forward Heuristic Example4.png">

Para conseguirmos chegar a loc(c1) = r1, precisamos das ações, load(r1, c1, d1) e move(r1, d2, d1). Para se chegar a loc(r1) = d3 só precisamos da ação move(r1, d2, d3), logo $h^{FF}(s_2)$ = 3

<br>

## Landmark Heuristic

Não é admissible !!!

### Algoritmo (Nem vale a pena tentar perceber sem exemplo '-')

<img src="Imagens/Aula10 Landmark Heuristic Algorithm.png">

### Exemplo

Considerando o exemplo da image, vamos tentar obter os landmarks.

<img src="Imagens/Aula10 Landmark Heuristic Example1.png">

Como a landmark loc(r1) = d3 já está present no estado inicial, não o adicionamos à queue. Então a queue fica:

- queue = { loc(c1) = r1 }

Começando o while loop ficamos com:

- queue = { }
- Landmarks = { loc(c1) = r1 }
- R = { take(r1, d1, c1), take(r1, d2, c1), take(r1, d3, c1) }

As precondições seriam, respetivamente:

- loc(r1) = d1, loc(c1) = d1, cargo(r1) = nil
- loc(r1) = d2, loc(c1) = d2, cargo(r1) = nil
- loc(r1) = d3, loc(c1) = d3, cargo(r1) = nil

Como o estado inicial não cumpre nenhuma das precondições, temos de continuar o algoritmo.

$A_1$ = A \ R = { move(r1, d3, d1), move(r1, d3, d2) }<br>
Fazendo o RPG obtemos:

<img src="Imagens/Aula10 Landmark Heuristic Example2.png">

Paramos em $\hat{s}_2$ porque é igual a $\hat{s}_1$ porque como não podemos usar nenhuma ação take, nem de put, entre r1 e c1, as ações disponiveis são apenas as de move, ou seja, os efeitos vão ser apenas localizações, que já estão todas em $\hat{s}_1$.

E sendo assim N = { take(r1, d1, c1) } porque é a única ação possível para as precondições obtidas ao fazer a RPG.

E assim temos Precondições = { loc(r1) = d1 }

$\Phi$ = { loc(r1) = d1 }

queue = { loc(r1) = d1 }

Na segunda iteração ficamos com:

Landmarks = { loc(c1) = r1, loc(r1) = d1 }

R = { move(r1, d2, d1), move(r1, d3, d1) }

As precondições de move(r1, d3, d1) são:

- loc(r1) = d3

E assim $s_0$ satisfaz pre(move(r1, d3, d1)), e retornam-se os Landmarks:

Landmarks = { loc(c1) = r1, loc(r1) = d1 }
