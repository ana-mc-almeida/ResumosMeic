# Domain Testing

A domain test suite is developed by domain analysis:
1. Identify constraints for all input variables
2. Select test values for each variable in each boundary
3. Select test values for variables not given in the boundary
4. Determine expected results for these input

## On, Off, In, Out points

With respect to a particular boundary
- On points lie on the boundary
- Off points lie off the boundary but as near as possible. If the on point satisfies the condition then the off point shouldn't satisfy and vice-versa.
  - For example, for the boundary $x <= 2$ the on point would be $2$ and it satisfies the condition so the off point should be $3$.
  - For example, for the boundary $x < 2$ the on point would be $2$ and it satisfies the condition so the off point should be $1$.
  - For example, for the boundary $x == 2$ the on point would be $2$ and it satisfies the condition so the off points should be $1$ and $3$.

With respect to all boundaries
- In points satisfy all boundary conditions & are not on a boundary. They shouldn't be neither on nor off points.
- Out points satisfy no boundary conditions & are not on a boundary. (Useless for testing)

## Abstract state on, off and in points

-  An abstract state on point is a state such that the smallest possible change in some attribute will produce a state change
- An abstract state off point is a valid state that is not the focus state and differs from the focus state by the smallest possible change in some attribute
- An abstract state in point is neither an on or an off point (if possible)

### Example: Stack class

Abstract states of Stack:
- empty -> Stack.size == 0
- loaded -> Stack.size > 0 && Stack.size < MAXSTACK
- full -> Stack.size == MAXSTACK

| Abstract state | Possible Transactions | In Point | On Point | Off Point |
| -------------- | --------------------- | -------- | -------- | --------- |
| empty | loaded | 0 | 0 | 1 |
| loaded | empty | 1 < x < MAXSTACK - 1 | 1 | 0 |
| | full | 1 < x < MAXSTACK - 1 | MAXSTACK - 1 | MAXSTACK |
| full | loaded | MAXSTACK | MAXSTACK | MAXSTACK - 1 |

### Example: Account class

```java
class Account {
    AccountNumber number;
    Money balance;
    Date lastUpdate;
}
```

Abstract states of Account:
- Open -> balance >= 0 && lastUpdate < 1825
- Overdrawn -> balance < 0 && lastUpdate < 1825
- Inactive -> lastUpdate >= 1825

| Abstract state | Possible Transactions | In Point | On Point | Off Point |
| -------------- | --------------------- | -------- | -------- | --------- |
| open | overdrawn | (x > 0, y < 1824) | (0, y < 1824) | (-0.01, y < 1824) |
| | inactive | (x > 0, y < 1824) | (x > 0, 1824) | (x > 0, 1825) |
| overdrawn | open | (x < 0, y < 1824) | (0, y < 1824) | (0, y < 1824) |
| | inactive | (x < 0, y < 1824) | (x < 0, 1824) | (x < 0, 1825) |
| inactive | open | (x, y > 1825) | (x > 0, 1825) | (x > 0, 1824) |
| | overdrawn | (x, y > 1825) | (x < 0, 1825) | (x < 0, 1824) |

## One by one selection criteria

- For relational conditions, e.g. x <= 10 -> One on point and one off point
- For strict equality conditions, e.g. x == 10 -> One on point and two off points
- For nonscalar types -> one on point and one off point
- For abstract state invariants -> one on point and at least one off point

## Boundaries with 2 variables

<img src="Imagens/boundary with 2 variables.png">

On and Off points for $y <= 14.0 - x$?
1. Fix a value for $x$
  - $x \in [4, 10]$ - Pick mean value: $x = 7$
2. Replace x and compute On and Off for $y$
  - On -> $7$
  - Off -> $7,000001$

## Domain matrix design

1. Determine the domain restrictions of the IUT
2. Define on and off points for each boundary condition
3. Add expected results and in points for other values

Notes:
- Each test case only has one off or on point
- Select in points for all other values in the test case
- Avoid to repeat in points.

For the image above:

<img src="Imagens/Domain matrix.png">

Errors in the figure:
- The value of x for test case 9 must be 7, so that y is an on point.
- The in point for y test cases 3, 4 and 12 is also wrong (all invalidate y <= 14 - x)