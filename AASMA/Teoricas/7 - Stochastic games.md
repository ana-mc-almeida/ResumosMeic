# Stochastic games

A stochastic game (also known as a Markov game) is a tuple $(Q, N, A, P, r)$, where:
- $Q$ is a finite set of games (states);
- $N$ is a finite set of n agents;
- $A = A_1 \times \dots \times A_n$, where $A_i$ is a finite set of actions available to agent $i$;
- $P:Q \times A \times Q \rightarrow [0,1]$ is the transition probability function; $P(q, a, \hat{q}$) is the probability of transitioning from state $q$ to state $\hat{q}$ after action profile $a$;
- $R = r_1, \dots, r_n$, where $r_i : Q \times A \rightarrow \mathbb{R}$ is a real-valued payoff function for agent $i$.

A stochastic game is generalization of a repeated game, where $Q$ has only one game (one state) and of markov decision process, where $N$ has only one agent.