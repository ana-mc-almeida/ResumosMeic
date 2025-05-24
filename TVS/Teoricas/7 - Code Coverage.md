# Code Coverage

A metric to check the amount of code that is being tested.

Coverage value = # Covered test requirements / # Total test requirements

Coverage reports can:
- Point out a grossly inadequate test suite.
- Suggest the presence of surprises (things that the code does that are not in the specification).
- Help to identify implementation constructs that may require implementation-based test design.

Coverage measures what is executed, not what is checked. Even if you could reach 100% statement coverage, it wouldn't means that your program is bug-free. Low coverage means code is not well tested, but high coverage does not mean code is well tested.

### Control flow graph (CFG)

- Segment: one or more lexically contiguous statements with no conditionally executed statements. Last statement must be the predicate of a conditional statement, a loop control, a break, a goto, or method exit().
- Branch: Conditional transfer of control

<img src="Imagens/7 - CFG example.png">

<img src="Imagens/7 - CFG Example 2.png">

# Method scope code coverage models

## Statement coverage model

Achieved when all statements in a method have been executed at least once. This means that all nodes in CGF are exercised at least once.

Example: [Pratica 6, Exercicio 1.2](../Praticas/Pratica%206/Pratica%206.md#2)


## Branch coverage model

Achieved when each branch in the code (edge in the CFG) is executed at least once. Improves on statement coverage by requiring that each branch is taken at least once.

100% branch coverage implies 100% statement coverage.

Short circuit evaluation is a blind spot for branch coverage, because some conditions may not be exercised.

Example: [Pratica 6, Exercicio 1.3](../Praticas/Pratica%206/Pratica%206.md#3)

## Path coverage model

Achieved when all entry-exit paths of a method have been exercised at least once.

May require an infinite number of paths for loops. Even if it doesn't have a loop the number of paths is exponential with the number of branches.

Example: [Pratica 6, Exercicio 1.4](../Praticas/Pratica%206/Pratica%206.md#4)

### Infeasible Path

- Contradictory or mutually exclusive conditions.
  - `if ((x > 2) || (x < 10)) { ... }` the false-false branch of this predicate can never be taken.
- Mutually exclusive, redundant predicates:
  - `if (x == 0) oof.perpetual(); else oof.free();`
  - `if (x != 0) oof.motion(); else oof.lunch()`
- Dead code.

## Basis-Path model coverage

Achieved when C independent entry-exit paths have been exercised. C is the cyclomatic complexity metric and $C = e - n + 2$ where $e$ are the amount of edges and $n$ is the amount of nodes.

Example: [Pratica 6, Exercicio 1.5](../Praticas/Pratica%206/Pratica%206.md#5)

## Condition coverage model

Achieved when all conditions are evaluated to true and false at least once.

## Branch/Condition coverage model

Condition coverage + each branch taken at least once.

## Multiple condition coverage

Requires that all true-false combinations of simple conditions be exercised at least once.

## Modified Condition/Decision coverage (MC/DC)

Every condition in a decision in the program has taken all possible outcomes at least once (Decision coverage).

Every decision in the program has taken all possible outcomes at least once (Branch coverage).

Each condition in a decision has been shown to independently affect that
decision's outcome.

Example: [Pratica 6, Exercicio 7.1](../Praticas/Pratica%206/Pratica%206.md#1-4)

## Simple loop coverage model

Exercise body of loop:
- 0 times
- once
- twice
- typical number of times
- max and max+1 times

Typically combined with other criteria, such as statement or branch coverage.

# Subsume relationship

<img src="Imagens/7 - subsume relationship.png">