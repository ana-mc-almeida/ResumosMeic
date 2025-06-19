

<!-- toc -->

- [Repeated Games](#repeated-games)
  * [Finitely-Repeated Games](#finitely-repeated-games)
    + [Prisoner's Dilemma in a two-stage game](#prisoners-dilemma-in-a-two-stage-game)
  * [Infinitely-Repeated Games](#infinitely-repeated-games)
    + [Folk Theorem](#folk-theorem)
      - [Formulas](#formulas)
    + [Example - Prisoner's Dilemma](#example---prisoners-dilemma)
    + [Example - Prisoner's Dilemma modified](#example---prisoners-dilemma-modified)
    + [Notes about hidden part of class](#notes-about-hidden-part-of-class)
      - [Stag-Hare example](#stag-hare-example)

<!-- tocstop -->

# Repeated Games

When you play the same normal-form game over and over.

## Finitely-Repeated Games

If the game has a finite number of repetitions we can model this game as an extensive-form game with imperfect information. At each round, players do not know what the other players have done, but afterward, they do. The overall payoff function is additive: the sum of payoffs in stage games.

Proposition: If the stage game $G$ has a unique Nash equilibrium then, for any finite $T$, the repeated game $G(T)$ has a unique outcome: the Nash equilibrium of $G$ is played in every subgame-perfect stage.

### Prisoner's Dilemma in a two-stage game

|               | Cooperate | Defect   |
| ------------- | --------- | -------- |
| **Cooperate** | $-1,-1$   | $-4, 0$  |
| **Deflect**   | $0, -4$   | $-3, -3$ |

Representing it in a tree:

<img src="Imagens/5 - Prisoners Dilemma example.png">

Pure Strategies:

- Agent 1: $\{(C_1, C_{1,1}), (C_1, D_{1,1}), (C_1, C_{1,2}), (C_1, D_{1,2}), (C_1, C_{1,3}), (C_1, D_{1,3}), (C_1, C_{1,4}), (C_1, D_{1,4}),$ $ (D*1, C*{1,1}), (D*1, D*{1,1}), (D*1, C*{1,2}), (D*1, D*{1,2}), (D*1, C*{1,3}), (D*1, D*{1,3}), (D*1, C*{1,4}), (D*1, D*{1,4})\}$
- Agent 2: $\{(C_1, C_{2,1}), (C_1, D_{2,1}), (C_1, C_{2,2}), (C_1, D_{2,2}), (C_1, C_{2,3}), (C_1, D_{2,3}), (C_1, C_{2,4}), (C_1, D_{2,4}),$ $ (D*1, C*{2,1}), (D*1, D*{2,1}), (D*1, C*{2,2}), (D*1, D*{2,2}), (D*1, C*{2,3}), (D*1, D*{2,3}), (D*1, C*{2,4}), (D*1, D*{2,4})\}$

In this example the strategy profile $(D_1, D_{1,4}), (D_1, D_{2,4})$ is a nash equilibrium.

## Infinitely-Repeated Games

If a game that is repeated an infinite number of times we would have to represent it with an infinite tree, and so the sum of the payoffs is infinity.

Definition: Given an infinite sequence of payoffs $r_1, r_2, \dots$ for agent $i$, the average reward of agent $i$ is $\lim\limits_{k\rightarrow \infin} \sum\limits^k_{j=1} \dfrac{r_j}{k}$

If we want to have a discount factor $\beta$ $(0 < \beta < 1)$, the future discounted reward for agent $i$ is: $\sum\limits^\infin_{j=1} \beta^jr_j$. This discount factor can be interpreted as how much more an agent cares about her well-being in the near term than in the long term.

### Folk Theorem

Folk theorem allows us to find a Nash equilibria in an infinitely repeated game.

Let action set $a$ be a Nash Equilibrium of stage game $G$. Let action set $a'$ be a set of actions where every agent benefits from selecting $a'$ instead of $a$. For example, in prisoner's Dilemma $a$ would be defect and $a'$ would be cooperate. Lets also assume that agents follow Trigger strategy (they will cooperate, but if anyone defects, defect forever).

Since this goes infinitely if you are the first one to defect, you will get a small advantage in relation with your opponents in the short term. However, in the long term it is worse for everyone, because everyone could have a greater score if they were cooperating.

#### Formulas

Maximum gain from deviating (over all agents) is $M = \max\limits_{i, a''} u_i(a_i'', a_i') - u_i(a')$

Minimum per-period loss from future punishment is $m = \min\limits_i u_i(a') - u_i(a)$

If an agent deviates, given the other agents' strategies, the maximum possible net gain is $M - m\dfrac{\beta_i}{1 - \beta_i}$

Deviation is not beneficial if $M - m\dfrac{\beta_i}{1 - \beta_i} \leq 0 <=> \beta_i \geq \dfrac{M}{M + m}, \forall i$

### Example - Prisoner's Dilemma

|       | C     | D     |
| ----- | ----- | ----- |
| **C** | $3,3$ | $0,5$ |
| **D** | $5,0$ | $1,1$ |

Is there a value of $\beta$ that could sustain cooperation?

Always cooperate: $3 + \beta 3 + \beta^2 3 + \dots = 3 + \beta \dfrac{3}{1 - \beta}$

Always defect (assuming agents follow Trigger strategy): $5 + \beta 1 + \beta^2 1 + \dots = 5 + \beta \dfrac{1}{1 - \beta}$

Always cooperate - Always defect = $-2 + \beta 2 + \beta^2 2 + \dots = -2 + \beta \dfrac{2}{1 - \beta}$

If we want the "always cooperate" to have a higher payoff we need that $-2 + \beta \dfrac{2}{1 - \beta} \geq 0 <=> \beta \geq \dfrac{1}{2}$

Interpretation: if we want to sustain cooperation, the agent needs to care about tomorrow at least as half as much as it cares about today!

### Example - Prisoner's Dilemma modified

|       | C      | D      |
| ----- | ------ | ------ |
| **C** | $3,3$  | $0,10$ |
| **D** | $10,0$ | $1,1$  |

Always cooperate: $3 + \beta 3 + \beta^2 3 + \dots = 3 + \beta \dfrac{3}{1 - \beta}$

Always defect (assuming agents follow Trigger strategy): $10 + \beta 1 + \beta^2 1 + \dots = 10 + \beta \dfrac{1}{1 - \beta}$

Always cooperate - Always defect = $-7 + \beta 2 + \beta^2 2 + \dots = -7 + \beta \dfrac{2}{1 - \beta}$

If we want the "always cooperate" to have a higher payoff we need that $-7 + \beta \dfrac{2}{1 - \beta} \geq 0 <=> \beta \geq \dfrac{7}{9}$

---

### Notes about hidden part of class

$u(A) = 0 * \theta + 1 * (1 - \theta) = 1 - \theta$

$u(B) = 1 * \theta + 0 * (1 - \theta) = \theta$

$u^* = \theta \times u(A) + (1 - \theta) \times u(B) = \theta \times (1 - \theta) + (1 - \theta) \times \theta = 2 \times \theta \times (1 - \theta)$

$u(A) - u* = (1 - \theta) - 2 \times \theta - 2 \times \theta^2$

$\dfrac{\partial}{\partial \theta}(u(A) - u*) = 0 <=> \theta = 0.5$, so $\theta$ will always converge to 0.5.

#### Stag-Hare example

$u(Stag) = 10 \times \theta + 1 \times (1 - \theta) = 9 \times \theta + 1$

$u(Hare) = 8 \times \theta + 5 \times (1 - 0) = 3 \times \theta + 5$

$u* = u(Stag) \times \theta + u(Hare) \times (1 - \theta) = 6 \times \theta^2 - \theta + 5$

$\dfrac{\partial}{\partial \theta}(u(Stag) - u\*) = 0 <=> \theta = 0 \vee \theta = \dfrac{2}{3} $
