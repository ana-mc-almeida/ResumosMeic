

<!-- toc -->

- [Coordination](#coordination)
  * [Games](#games)
    + [Cars example](#cars-example)
  * [Social conventions](#social-conventions)
  * [Roles](#roles)

<!-- tocstop -->

# Coordination

## Games

We can model a coordination problem as a coordination game.

Consider the stag hunt game with two hunters:

- If they both hunt hares, they each capture half of the hares in the range.
- If one hunts the stag and the other hunts hares, the stag hunter goes home empty-handed while the hare hunter captures all the hares.
- Finally, if both hunt the stag, then each of their shares of the stag is greater than the value of all the hares.

| Hunter 1 \ Hunter 2 | Stag  | Hare  |
| ------------------- | ----- | ----- |
| **Stag**            | $3,3$ | $0,2$ |
| **Hare**            | $2,0$ | $1,1$ |

With no coordination the action to hunt a Stag is a risk-taking strategy and the action to hunt a Hare is a conservative strategy.

With coordination both hunters enter into some kind of agreement in advance. One agreement could be to choose the joint action that is Pareto optimal (or strictly Pareto efficient).

A joint action $a$ Pareto dominates joint action $a'$ if $\forall i \in N : u_i(a) \geq u_i(a') \wedge \exists j \in N : u_j(a) > u_j(a')$

A joint action $a$ is Pareto optimal, or strictly Pareto efficient, if there does not exist another joint action $a' \in A$ that Pareto dominates $a$.

In the stag hunt game both (Stag, Stag) and (Hare, Hare) are Nash equilibria, but only (Stag, Stag) is Pareto optimal.

So coordination can be defined as the process in which a group of agents choose a single Pareto optimal Nash equilibrium in a game.

### Cars example

Each agent wants to cross first, but if they both cross they will crash.

| Car 1 \ Car 2 | Cross   | Stop  |
| ------------- | ------- | ----- |
| **Cross**     | $-1,-1$ | $1,0$ |
| **Stop**      | $0,1$   | $0,0$ |

Both (Cross, Stop) and (Stop, Cross) are Nash Equilibria and Pareto optimal. But we don't know which to choose.

## Social conventions

Boutilier (1996) proposed a convention that assumes a unique ordering scheme of joint actions that is common knowledge among agents. Each agent first computes all equilibria of the game, and then selects the first equilibrium according to this ordering scheme.

## Roles

Coordination by social conventions relies on the assumption that an agent can compute all equilibria in a game before choosing a single one. However, computing equilibria can be expensive when the agents' action sets are large.

A natural way to reduce the agents' action sets is to assign roles to the agents. Given a particular state, a role can be viewed as a masking operator on the agent's action set.

Suppose there is a fixed ordering $\{1,2, \dots , n\}$ of the roles. In other words, role 1 must be assigned first, followed by role 2, etc. For each role there is a function that assigns to each agent a "potential". The potential reflects how appropriate that agent is for the specific role, given the current state. So each role (starting from role 1) is assigned to the agent that has the highest potential for that role.

This works when there is no communication. If there is communication an agent only needs to compute its own potentials for the set of roles and then broadcast them to the rest of the agents.
