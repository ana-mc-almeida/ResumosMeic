# Markov Decision Process (MDP)

A framework for sequential decision making of a single agent.
Let:

- $t = 0, 1, 2, \dots$ - be discrete time
- $s \in S$ - be a discrete set of states.
- $a \in A$ - be a discrete set of actions.
- $P(s'|s,a)$ - be a stochastic transition model
- $R: S \times A \rightarrow \mathbb{R}$ - be a reward function.

---

### state-value function definition

The state-value function of a state $s$ under a policy $\pi$ is the expected return the agent can receive when starting in state $s$ and then following policy $\pi$: $V^\pi(s)$

---

### action-value function definition

The action-value function (Q-values) of taking an action $a$ in state $s$ under a policy $\pi$ is the expected return the agent can receive when starting in state $s$, taking action $a$, and then following policy $\pi$ $Q^\pi(s,a)$

---

We denote all the optimal polices by $\pi^*$, note that an MDP might have more than one optimal policy.

These policies $\pi^*$ share the same state-value function, called optimal state-value function, $V^*(s)$ and the same action-value function, called optimal action-value function, $Q^*(s,a)$.

### Value iteration

Value iteration converges to the optimal $Q^*$ for any initialization, after computing the optimal $Q^*$, we can then extract the policy.

For it we need to initialize a state-value function and iteratively apply the Bellman equation turned into an assignment operation. The Bellman equation requires the stochastic transition model and the reward function.

## Reinforcement Learning

We use reinforcement learning when we don't know the stochastic transition model and reward function. Reinforcement learning is the technique where the agent interacts with the environment in order to learn.

The agent's objective is to compute the optimal policy of the MDP with the data points.

### Q-Learning

Q-Learning is an algorithm that can be used to perform reinforcement learning.

1. We start with some estimate $Q$.
2. Initialize current state $s$.
3. Loop for each step:
   1. Choose some action $a$.
   2. Take action $a$ and observe next state $s'$ and reward $r$.
   3. Update $Q$ estimate according to the reward.
   4. Update current state to $s'$.

Update formula: $Q(s,a) \leftarrow Q(s,a) + \alpha[r + \gamma\max\limits_{a'} Q(s', a') - Q(s,a)]$

Which is equivalent to: $Q(s,a) \leftarrow (1 - \alpha)Q(s,a) + \alpha[r + \gamma\max\limits_{a'} Q(s', a')]$

- $\alpha$ - learning rate
- $\gamma$ - discount factor = future reward importance
- $r$ - reward
