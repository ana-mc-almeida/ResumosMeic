# Multi-agent Learning

## Joint Action-Learning

Assume the others are doing their best. To find a stable equilibria we can use Nash Q-Learning, while to improve the worst case scenario we can use minimax-Q.

### Nash Q-Learning

For this algorithm, each agent will reproduce the calculations for the Q-table of the other agent, because you can only know the Nash Equilibria if you calculate both Q-tables.

For the prisioners Dillema case:

| Prisoner 1 \ Prisoner 2 | Colaborate | Defect |
| ----------------------- | ---------- | ------ |
| **Colaborate** | -1, -1 | -9, 0 |
| **Defect** | 0, -9 | -6, -6 |

Q1 = [0,0 ; 0,0]<br>
Q2 = [0,0 ; 0,0]

## Joint Action Learners with Agent Modelling

Are they considering my preferences. Try to predict others.

### Fictitius Play

Fictitious play is an instance of model-based learning. The learning agent explicitly maintains beliefs about the opponent's actions. In fictitious play, the learning agent is oblivious to the payoffs obtained by other agents.

Algorithm:
1. Initialize beliefs about the opponent's action.
2. Play a best response to the assessed action of the opponent.
3. Observe the opponent's actual play and update beliefs accordingly.
4. repeat from 2.

Let $A$ be the set of the opponent's actions and $w(a)$ be the number of times that the opponent has played action $a$, for every $a \in A$. Hence, the learning agent assesses the probability of $a$ in the opponent's mixed strategy as $P(a) = \dfrac{w(a)}{\sum\limits_{a'\in A} w(a')}$

For example, in a repeated Prisoner's Dilemma, if the opponent has selected the following actions in the first five games: Confess, Confess, Not Confess, Confess, Not Confess.

| Prisoner 1 \ Prisoner 2 | Colaborate | Defect |
| ----------------------- | ---------- | ------ |
| **Colaborate** | -1, -1 | -9, 0 |
| **Defect** | 0, -9 | -6, -6 |

Before the sixth game, the learning agent has $w(Confess) = 3$ and $w(Not Confess) = 2$ and thus the agent thus assumes that the opponent is playing the mixed strategy $(0.6,0.4)$