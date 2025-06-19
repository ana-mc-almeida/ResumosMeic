

<!-- toc -->

- [Normal-Form Games](#normal-form-games)
    + [Prisoner's dilemma](#prisoners-dilemma)
  * [Strictly dominated action](#strictly-dominated-action)
- [Solution Concepts](#solution-concepts)
  * [Iterated elimination of strictly dominated actions](#iterated-elimination-of-strictly-dominated-actions)
  * [Nash Equilibrium](#nash-equilibrium)
    + [Prisoner's Dilemma example:](#prisoners-dilemma-example)
    + [Cournot Model](#cournot-model)
  * [Mixed Strategy](#mixed-strategy)
    + [Example Matching Pennies](#example-matching-pennies)
    + [Example Battle of sexes](#example-battle-of-sexes)

<!-- tocstop -->

# Normal-Form Games

A (finite, n-person) normal-form game is a tuple $(N,A,u)$, where:

- $N$ is a finite set of $n$ players.
- $A = A_1 \times \dots \times A_n$, where $A_i$ is a finite set of actions available to player/agent $i$. Each vector $a = (a_1, \dots, a_n) \in A$ is called an action profile (or joint action).
- $u = (u_1, \dots, u_n)$, where $u_i : A \longmapsto \mathbb{R}$ is a real-valued utility (or payoff) function for player $i$.

In summary, each agent chooses a single action and then receives a payoff that depends on the joint action. The joint action is the outcome of the game. Although payoffs are common knowledge, an agent does not know the actions of the other agents, the best an agent can do is to predict the actions of others. A game's solution is a prediction of the outcome using the assumptions that all agents are rational and strategic.

### Prisoner's dilemma

- Two suspects are arrested and charged for a crime.
- The police lack sufficient evidence to convict the suspects, unless at least one confesses.
- The police hold the suspects in separate cells.
- If neither confesses then both will be convicted of a minor offense and sentenced to one month in jail.
- If both confess then both will be sentenced to jail for six months.
- If one confesses but the other does not, then the confessor will be released immediately but the other will be sentenced to nine months in jail.

Elements of this normal-form game:

- $n$ = {Prisoner 1, Prisoner 2}
- $A_1 = A_2 = $ {Confess, Not confess}
- $u = (u_1(a_1,a_2), u_2(a_1,a_2))$, where $u_i$ is the payoff function for each agent:

| Prisoner 1 \ Prisoner 2 | Not confess | Confess  |
| ----------------------- | ----------- | -------- |
| **Not confess**         | $-1, -1$    | $-9, 0$  |
| **Confess**             | $0, -9$     | $-6, -6$ |

Example:

- $u_1(a_1 = \text{confess}, a_2 = \text{not confess}) = 0$
- $u_2(a_1 = \text{confess}, a_2 = \text{not confess}) = -9$

## Strictly dominated action

A strictly dominiated action will always result in lower payoffs for the agent than some other action, no matter what the other agents do. Also known as subotimal action.

Formally: We will say that an action $a_i$ of agent $i$ is strictly dominated by another action $a_i'$ of agent $i$ if $u_i(a_i', a_{-i}) > u_i(a_i,a_{-i}), \forall a_i, \forall i$

For example in the prisoner's dilemma, the "not confess" is a strictly dominated action by the action "confess" for any prisoner:

- $u_1(a_1' = \text{confess}, a_2 = \text{not confess}) > u_1(a_1 = \text{not confess}, a_2 = \text{not confess})$
- $u_1(a_1' = \text{confess}, a_2 = \text{confess}) > u_1(a_1 = \text{not confess}, a_2 = \text{confess})$
- $u_2(a_2' = \text{confess}, a_1 = \text{not confess}) > u_2(a_2 = \text{not confess}, a_1 = \text{not confess})$
- $u_2(a_2' = \text{confess}, a_1 = \text{confess}) > u_2(a_2 = \text{not confess}, a_1 = \text{confess})$

For prisoner 1:
| Prisoner 1 \ Prisoner 2 | Not confess | Confess |
| - | ---------- | -------- |
| **Not confess** | $-1, -1$ | $-9, 0$ |
| **Confess** | $\underline{0}, -9$ | $\underline{-6}, -6$ |

For prisoner 2:
| Prisoner 1 \ Prisoner 2 | Not confess | Confess |
| - | ---------- | -------- |
| **Not confess** | $-1, -1$ | $-9, \underline{0}$ |
| **Confess** | $0, -9$ | $-6, \underline{-6}$ |

# Solution Concepts

## Iterated elimination of strictly dominated actions

This solution iteratively eliminates strictly dominated actions from all agents, until no more actions are strictly dominated. It assumes that:

- A rational agent would never take a strictly dominated action.
- It is common knowledge that all agents are rational.

For example:

| Agent 1 \ Agent 2 | Left  | Middle            | Right |
| ----------------- | ----- | ----------------- | ----- |
| **Up**            | $1,0$ | $1,\underline{2}$ | $0,1$ |
| **Down**          | $0,3$ | $0,\underline{1}$ | $2,0$ |

Here for agent 2 the middle option is always better than right option. So it will become like this:

| Agent 1 \ Agent 2 | Left              | Middle            |
| ----------------- | ----------------- | ----------------- |
| **Up**            | $\underline{1},0$ | $\underline{1},2$ |
| **Down**          | $0,3$             | $0,1$             |

Now, for agent 1 the action down is strictly dominated by action up.

| Agent 1 \ Agent 2 | Left  | Middle            |
| ----------------- | ----- | ----------------- |
| **Up**            | $1,0$ | $1,\underline{2}$ |

Thus, the solution (outcome) of this game is (Up, Middle)

Drawbacks:

- We need to assume that it is common knowledge that the agents are rational.
- And the technique does not produce a prediction whatsoever about this game, thus we are unsure about the outcome.

## Nash Equilibrium

A Nash equilibrium is a joint action $a^*$ with the property that the following holds for every agent $i$:

$$u_i(a_i^*,a_{-i}^*) \geq u_i(a_i,a_{-i}^*), \forall a_i \in A_i$$

In other words, a Nash equilibrium is a joint action from where no agent can unilaterally improve his payoff. Hence, no agent has any incentive to deviate.

### Prisoner's Dilemma example:

We can compute the best-response function for each agent:

- $B_1(a_2 = $ Not confess $) = $ Confess
- $B_1(a_2 = $ Confess $) = $ Confess
- $B_1(a_2 = $ Not confess $) = $ Confess
- $B_1(a_2 = $ Confess $) = $ Confess

|                 | Not confess | Confess  |
| --------------- | ----------- | -------- |
| **Not confess** | $-1, -1$    | $-9, 0$  |
| **Confess**     | $0, -9$     | $-6, -6$ |

In a Nash equilibrium, each agent's actions is an optimal response to the other agents' actions.

### Cournot Model

This is an application of a NE.

Features:

- There is more than one firm in the market. The number of firms is fixed and we will focus on the scenario with 2 firms (duopoly).
- All firms produce a homogeneous product, in other words, there is no product differentiation.
- Firms do not cooperate.
- Firms have market power.
- Firms choose quantities simultaneously.
- The firms are economically rational and act strategically. Seeking to maximize profit given their competitors' decisions.

Let $q_1$ and $q_2$ denote the quantities produced by firms 1 and 2, respectively.

Let $P(Q) = a - Q$ be the market-clearing price when the aggregate quantity on the market is $Q = q_1 + q_2$.

- $P(Q) = a - Q$, for $Q < a$
- $P(Q) = 0$, for $Q \geq a$

The firm's payoff is its profit: $\pi_i(q_i, q_j) = q_i[P(q_i + q_j) - c] = q_i[a - (q_i + q_j) - c]$

The quantity pair $(q_1^*, q_2^*)$ is a Nash equilibrium if, for each firm $i, q_i^*$ solves

$$\max\limits_{0 \leq q_i \leq \infin} \pi_i(q_i, q_j^*) = \max\limits_{0 \leq q_i \leq \infin} q_i[a - (q_i + q_j^*) - c]$$

Solving it for firm 1 we would get:

$$\dfrac{\partial}{\partial q_1} q_i[a - (q_i + q_2^*) - c] = 0 <=> q_1^* = \dfrac{1}{2}(a - q_2^* - c)$$

So for firm 2 we would have $q_2^* = \dfrac{1}{2}(a - q_1^* - c)$

Which yiels: $q_1^* = q_2^*=\dfrac{1}{3}(a-c)$

## Mixed Strategy

In any game in which each agent would like to outguess the other(s) there is no Nash equilibrium. This happens because the solution to such a game necessarily involves uncertainty about what the agents will do.

A mixed strategy for an agent $i$ is a probability distribution over his actions $a_i \in A_i$.

We will hereafter refer to the actions in $A_i$ as agents $i$'s pure strategies. $A_i$ consists of the two pure strategies: Heads and Tails.

Hence, a mixed strategy for agent $i$ is the probability distribution $(q, 1 - q)$, where $q$ is the probability of choosing Heads and $(1 - q)$ is the probability of choosing Tails.

- The mixed strategy (0,1) is simply the pure strategy Taisl. Likewise, the mixed strategy (1,0) is the pure strategy Heads.

NE guarantees that each agent's pure strategy is a best response to the other agents' pure strategies. To extend the previous definition to include mixed strategies we simply require that each agent's mixed strategy be a best response to the other agents' mixed strategies.

### Example Matching Pennies

In Matching Pennies what is the mixed strategy Nash equilibrium?

| Agent 1 \ Agent 2 | Heads  | Tails  |
| ----------------- | ------ | ------ |
| **Heads**         | $-1,1$ | $1,-1$ |
| **Tails**         | $1,-1$ | $-1,1$ |

Suppose that agent 1 believes that agent 2 will choose Heads with probability $q$ and Tails with probability $1 - q$. Consequently, agent 1 believes that agent 2 will play the mixed strategy $(q, 1 - q)$.

Given this belief, agent 1's expected payoff for choosing Heads given agent 2's mixed strategy $(q, 1 - q)$:

$$EU_1(Heads) = q * -1 + (1 - q) * 1 = 1 - 2 * q$$

And agent 1's expected payoff for choosing Tails given agent 2's mixed strategy $(q, 1 - q)$

$$EU_1(Tails) = q * 1 + (1 - q) * -1 = 2 * q - 1$$

Agent 1's best pure-strategy response is Heads if $q < \dfrac{1}{2}$ and Tails if $q > \dfrac{1}{2}$

Given this belief, agent 2's expected payoff for choosing Heads given agent 1's mixed strategy $(r, 1 - r)$:

$$EU_2(Heads) = r * 1 + (1 - r) * -1 = 2 * r - 1$$

And agent 2's expected payoff for choosing Tails given agent 1's mixed strategy (r, 1 - r):

$$EU_2(Tails) = r * -1 + (1 - r) * 1 = 1 - 2 * r$$

Agent 2's best pure-strategy response is Heads if $r > \dfrac{1}{2}$ and Tails if $r < \dfrac{1}{2}$

if $EU_1(Heads) = EU_1(Tails) <=> q = \dfrac{1}{2}$

if $EU_2(Heads) = EU_2(Tails) <=> r = \dfrac{1}{2}$

Thus, the mixed strategies $(\dfrac{1}{2}, \dfrac{1}{2}), (\dfrac{1}{2}, \dfrac{1}{2})$ are a Nash equilibrium

### Example Battle of sexes

$EU_M(B) = EU_M(F)$

$EU_M(B) = q * 1 + (1 - q) * 0 = q$

$EU_M(F) = 0 * q + 2 * (1 - q) = 2 - 2 * q$

$EU_M(B) = EU_M(F) <=> q = \dfrac{2}{3}$

$EU_W(B) = EU_W(F)$

$EU_W(B) = r * 2 + (1 - r) * 0 = 2 * r$

$EU_W(B) = r * 0 + (1 - r) * 1 = 1 - r$

$EU_W(B) = EU_W(F) <=> r = \dfrac{1}{3}$

$NE = \{(\dfrac{1}{3}, \dfrac{2}{3}), (\dfrac{2}{3}, \dfrac{1}{3})\}$
