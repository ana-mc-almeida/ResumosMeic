# Stochastic Greedy Local Search

## Greedy Local Search

### Algorithm

1. Arranja random assignments para as variáveis.
2. Se o assignment escolhido for consistent, então retornamos essa solução.
3. Se não for consistent, flipa-se a variável que nos dá mais clausulas certas quando flipada.
4. Volta ao passo 2

#### Example

( !C ), ( !A V !B V C ), ( !A V D V E ), ( !B V ! C )

Escolhe-se o primeiro assignment tendo todas as variáveis o valor 1. A primeira a última clausula são violadas.

- Se flipassemos A, D, E não iriamos melhorar nada a nossa situação.
- Se flipassemos C iriamos satisfazer a primeira e última clausula, mas estariamos a violar a segunda.
- Se flipassemos D iriamos satisfazer a última clausula, mas a primeira continuava a ser violada.

Como C e D ambos fazem com que fiquemos com uma clausula violada, escolhemos um qualquer. Vamos assumir que flipamos C, por exemplo.

Olhando para as opções de novo, considerando que flipamos C:

- Se flipassemos C, D, E não iriamos conseguir satisfazer a segunda clausula.
- Se fliparmos A conseguiriamos satisfazer todas as clausulas.
- Se fliparmos B também conseguimos satisfazer todas as clausulas.

Por isso escolher flipar tanto A como B resolvem o problema.

<br>

### Heuristics

- Plateau Search: Quando todos os caminhos da procura melhoram a mesma coisa, o algoritmo escolhe qualquer um dos caminhos.
- Tabu Search: Armazena-se uma lista com últimos valores assigned às variáveis. Depois fica proibido voltar a escolher os mesmos valores.
- Tie-breaking rules com base no historico.
- Constraint weighting: Sempre que numa iteração uma constraint não é satisfeita, o seu peso aumenta em 1. Assim evitam-se empates na escolha de variáveis.

#### Constraint Weighting Example

( !C ), ( !A V !B V C ), ( !A V D V E ), ( !B V ! C )

Primeiro todas as constraints têm peso 1.

Escolhe-se o primeiro assign para todas as variáveis terem valor 1. E nesse caso, a primeira e última clausula são violadas. Por isso os seus pesos aumentam em 1 unidade. E nesta altura temos:

- $w_1$ = $w_4$ = 2
- $w_2$ = $w_3$ = 1

O próximo assignment pode ser:

- Flipar A, D, E, mas não nos ajuda a satisfazer as clausulas violadas.
- Flipar B que faz com que tenhamos um custo de 2 porque a primeira clausula não fica satisfeita.
- Flipar C que faz com que tenhamos um custo de 1 porque satisfaz a primeira e ultima clausula, mas viola a segunda.

Ou seja flipamos C sem duvida. E passamos a ter os pesos:

- $w_1$ = $w_2$ = $w_4$ = 2
- $w_3$ = 1

_Até podia continuar, mas nem daria para mostrar o constraint weighting a funcionar por isso fico por aqui._

## Random walk Strategies

### WalkSAT

1. Escolhe uma constraint não satisfeita aleatoriamente.
2. Escolhe com probabilidade p uma variável para se flipar ou com probabilidade 1 - p escolhe uma uma variável para flipar que minimiza o número de clausulas que ficam violadas.

#### Example

( !C ), ( !A V !B V C ), ( !A V D V E ), ( !B V ! C )

Atribui-se o valor 1 a todas as variaveis e a primeira e última clausula são violadas.

Escolhe-se aleatoriamente a clausula ( !B V !C )

- Para se minimizar o número de constraints violadas muda-se B para 0.
- Se flipassemos C, iriamos estar a violar a segunda clausula.

Só falta satisfazer a clausula ( !C ) por isso flipa-se C.
