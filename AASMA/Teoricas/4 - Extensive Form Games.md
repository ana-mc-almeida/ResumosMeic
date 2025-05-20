# Extensive Form Games

Turned-based game. Player 2 will know what player 1 has played. One player takes a decision and the other accepts or rejects that decision.

### Example - Sharing game

A brother and sister have to decide how to share two indivisible and identical presents from their parents in the following way:
- First the brother (player 1) suggests a split, which can be one of three: he keeps both, she keeps both, or they each keep one.
- Then the sister (player 2) chooses whether to accept or reject the split.

<img src="Imagens/4 - Sharing game.svg">

## Strategies and Equilibria

A pure strategy for an agent in a perfect-information game is a complete specification of which deterministic action to take at every node belonging to that agent.

For the sharing games the pure strategies would be:
- $S_1 =$ { 2-0, 1-1, 0-2 }
- $S_2 = $ { (yes,yes,yes), (yes,yes,no), (yes,no,yes), (yes,no,no), (no,yes,yes), (no, yes, no), (no, no, yes), (no, no, no) }

### Theorem

Every (finite) perfect-information game in extensive form has a pure-strategy Nash equilibrium.

Since agents take turns, and everyone gets to see everything that happened thus far before making a move, it is never necessary to introduce randomness into action selection in order to find an equilibrium.

### Example

<img src="Imagens/4 - second game.png">

Here the pure strategies are:
- $S_1 =$ { (A,G), (A,H), (B,G), (B,H) }
- $S_2 =$ { (C,E), (C,F), (D,E), (D,F) }

To calculate the nash equilibria we convert a perfect-information game to an equivalent normalform game:

| | (C,E) | (C,F) | (D,E) | (D,F) |
|-|----- | ----- | ----- | ----- |
| **(A,G)** | 3, 8 | 3, 8 | 8, 3 | 8, 3 |
| **(A,H)** | 3, 8 | 3, 8 | 8, 3 | 8, 3 |
| **(B,G)** | 5, 5 | 2, 10 | 5, 5 | 2, 10 |
| **(B,H)** | 5, 5 | 1, 0 | 5, 5 | 1, 0 |

<img src="Imagens/4 - nash equilibria filling.png">

We fill each cell with the result of the combination of actions that reach a leaf node.

| | (C,E) | (C,F) | (D,E) | (D,F) |
|-|----- | ----- | ----- | ----- |
| **(A,G)** | 3, <u>8</u> | <u>3</u>, <u>8</u> | <u>8</u>, 3 | <u>8</u>, 3 |
| **(A,H)** | 3, <u>8</u> | <u>3</u>, <u>8</u> | <u>8</u>, 3 | <u>8</u>, 3 |
| **(B,G)** | <u>5</u>, 5 | 2, <u>10</u> | 5, 5 | 2, <u>10</u> |
| **(B,H)** | <u>5</u>, <u>5</u> | 1, 0 | 5, <u>5</u> | 1, 0 |

Nash equilibria in this game: { (A, G), (C, F) }, { (A, H), (C, F) } and { (B, H), (C, E) }

## Subgame-perfect equilibria

The nash equilibria (A, G), (C, F) makes sense because agent 2 doesn't have an incentive to change from C to D and because agent 1 doesn't have an incentive ot change from A to B.

<img src="Imagens/4 - agent 2 from D to C.png">

<img src="Imagens/4 - agent 1 from A to B.png">

On the other hand, if we look at nash equilibria (B, H), (C, E).

<img src="Imagens/4 - NE (B,H), (C,E).png">

Agent 2 chooses action E because he knows that agent 1 would choose H afterwards. What if agent 2 does not consider agent 1's threat to be credible? If agent 2 played F, agent 1 would have an incentive to change from H to G.

The nash equilibrium (B,H), (C,E) is intuitively wrong. 

### Definition (Subgame of $G$ rooted at $h$) 

Given a perfect-information extensive-form game $G$, the subgame of $G$ rooted at node $h$ is the restriction of $G$ to the descendants of $h$.

### Definition (Subgames of $G$)

The set of subgames of $G$ consists of all of subgames of $G$ rooted at some node in $G$.

### Definition (Subgame-perfect equilibrium)

The subgame-perfect equilibria (SPE) of a game $G$ are all strategy profiles $s$ such that for any subgame $G'$ of $G$, the restriction of $s$ to $G'$ is a Nash equilibrium of $G'$.

### Example (A,G), (C,F)

Is the nash equilibria (A,G), (C,F) from the previous example a subgame-perfect equilibrium?

<img src="Imagens/4 - NE (A,G), (C,F) part 1.png">

This subgame is a NE.

<img src="Imagens/4 - NE (A,G), (C,F) part 2.png">

This subgame is a NE.

<img src="Imagens/4 - NE (A,G), (C,F) part 3.png">

This subgame is a NE.

<img src="Imagens/4 - NE (A,G), (C,F) part 4.png">

So (A,G),(C,F) is a subgame-perfect equilibrium.

### Example (B,H), (C,E)

<img src="Imagens/4 - NE (B,H), (C,E) subgame.png">

This subgame is not a NE, so (B,H), (C,E) is not a subgame-perfect equilibrium.

## Backward Induction

The general idea is to search the tree using DFS. At each node, the returned value is the best for the agent that plays in that node.

### Example

<img src="Imagens/4 - Backward Induction example part 1.png">
<img src="Imagens/4 - Backward Induction example part 2.png">
<img src="Imagens/4 - Backward Induction example part 3.png">
<img src="Imagens/4 - Backward Induction example part 4.png">
<img src="Imagens/4 - Backward Induction example part 5.png">
<img src="Imagens/4 - Backward Induction example part 6.png">
<img src="Imagens/4 - Backward Induction example part 7.png">