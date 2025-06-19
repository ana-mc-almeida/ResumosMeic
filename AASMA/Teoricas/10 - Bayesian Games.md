# Bayesian Games

Bayesian games (or games of incomplete information) allow us to represent agents' uncertainties about the game being played.

## First definition

A Bayesian game is a tuple $(N, G, P, I)$ where

- $N$ is a set of agents.
- $G$ is a set of games with $N$ agents each, such that the action sets for all the games are identical.
- $P \in \prod(G)$ is a common prior (probability of each game being the one that is being played) over games, where $\prod(G)$ is the set of all probability distributions over $G$.
- $I = (I_1, \dots, I_N)$ is a tuple of partitions of $G$, one for each agent.

The agents only know:

- The games.
- The common prior.
- The equivalence classes of all agents.

## Second definition

This definition has a different presentation, it is based on types - a way of defining uncertainty directly over a game's payoff function.

A Bayesian game is a tuple $(N, A, \Theta, p, u)$ where:

- $N$ is a set of agents.
- $A = A_1, \times \dots \times A_N$ where $A_i$ is the set of actions available to agent $i$.
- $\Theta = \Theta_1 \times \dots \times \Theta_N$ where $\Theta_i$ is the type space of agent $i$.
- $p : \Theta \rightarrow [0,1]$ is a common prior over types.
- $u = (u_1, \dots, u_N)$ where $u_i : A \times \Theta \rightarrow \mathbb{R}$ is the utility function for agent $i$.

A Bayesian Nash Equilibrium is a plan of action for each player as a function of types that maximize each type's utility expecting over the actions of the other players and over the types of the other players.

A pure strategy can be defined as $\alpha_i : \Theta_i \rightarrow A_i$, a mapping from every type to an action.

---

#### Timing of decisions

- ex-ante - The agent knows nothing about anyone's actual type, including his own.
- interim - An agent knows her own type but not the types of the other agents.
- ex-post - The agent knows all agents' types.

---

Given a Bayesian game, agent $i'\text{s}$ interim expected utility with respect to $\Theta_i$ and a pure strategy profile $\alpha$ is

$$EU_i(\alpha|\Theta_i) = \sum\limits_{\theta_i \in \Theta_i} p(\theta_i|\Theta_i) u_i(\alpha, \Theta_i, \theta_i)$$

A Bayesian Nash equilibrium is a pure strategy profile $a$ that satisfies

$$a_i \in \argmax\limits_{a_i'} EU_i(\alpha_i, \alpha_i' | \theta_i), \forall i, \forall \theta_i \in \Theta_i$$

The above is defined based on the interim stage.
