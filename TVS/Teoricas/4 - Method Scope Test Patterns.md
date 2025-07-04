

<!-- toc -->

- [Class Test](#class-test)
  * [Class Scope Integration](#class-scope-integration)
    + [Small Pop](#small-pop)
      - [Testing strategy:](#testing-strategy)
      - [Testing strategy:](#testing-strategy-1)
- [Method test design patterns](#method-test-design-patterns)
    + [Concept - Functional Cohesion](#concept---functional-cohesion)
  * [Category-Partition](#category-partition)
    + [Fault Model](#fault-model)
    + [Strategy Procedure](#strategy-procedure)
    + [Example](#example)
      - [1. Identify all functions of the MUT](#1-identify-all-functions-of-the-mut)
      - [2. Identify input and output parameters of MUT](#2-identify-input-and-output-parameters-of-mut)
      - [3. Identify categories for each input parameter](#3-identify-categories-for-each-input-parameter)
      - [4. Partition each category into choices](#4-partition-each-category-into-choices)
      - [5. Identify constraints on choices](#5-identify-constraints-on-choices)
      - [6. Generate test cases by enumerating all choices](#6-generate-test-cases-by-enumerating-all-choices)
      - [7. Develop expected values for each test case](#7-develop-expected-values-for-each-test-case)
    + [Entry and Exit Criteria](#entry-and-exit-criteria)
    + [Consequences](#consequences)
    + [Known uses](#known-uses)
  * [Combinational Functional Test](#combinational-functional-test)
    + [Fault Model](#fault-model-1)
    + [Strategy Procedure Example:](#strategy-procedure-example)
    + [Entry and Exit Criteria](#entry-and-exit-criteria-1)
    + [Consequences](#consequences-1)
    + [Known Uses](#known-uses)
  * [Recursive Function Test](#recursive-function-test)
    + [Fault Model](#fault-model-2)
    + [Strategy: Test procedure](#strategy-test-procedure)
    + [Entry and exit criteria](#entry-and-exit-criteria)
    + [Consequences](#consequences-2)
    + [Known Uses](#known-uses-1)
  * [Polymorphic Message Test](#polymorphic-message-test)
    + [Fault Model](#fault-model-3)
    + [Strategy: Test Procedure](#strategy-test-procedure)
    + [Entry and Exit Criteria](#entry-and-exit-criteria-2)
    + [Consequences](#consequences-3)

<!-- tocstop -->

# Class Test

Class scope testing corresponds with the classical definition of unit testing.

Testing a class requires exercising all methods, but is this enough? No. We need to consider semantics of object oriented programming languages.

1. Although we test one method at a time, methods cannot exist apart from a class. We need to test the intraclass interactions.
2. A class is a composite of features it defines and inherits. Superclass features must be exercised in the context of the subclass. We need to also test the superclass/subclass interactions.

Class scope testing is typically the responsibility of the class developer.

## Class Scope Integration

The purpose of class scope integration is to demonstrate that the CUT is ready to test. Two approaches for reaching the operability threshold:

- Small Pop
- Alpha-Omega Cycle

### Small Pop

It is effective when responsibility-based testing and debugging are unlikely to be impeded by simple coding problems. "Popping" an object out of a class to test it independently.

- The class under test (CUT) is small and simple.
- All or most of the servers of the class under test are known to be stable by testing or usage.
- All or most of the inherited features are known to be stable by testing or usage.
- Few intraclass dependencies exist.

#### Testing strategy:

1. Develop the entire class, without any testing.
2. Write a test suite using any appropriate test pattern.
3. Run the test suite.
4. Debug the class as needed.

Alpha and omega are the two placeholder states that bracket the life cycle of an object. The alpha-omega cycle takes the OUT from the alpha state to omega state by sending a message to every method at least once. Passing this cycle means that the CUT is ready for extensive testing. No attempt is made to achieve any coverage.

Really useful for incremental development.

#### Testing strategy:

The test driver sends one message to each of the following kinds of methods in the specified order:

1. New or constructor method
2. Accessor (get) method
3. Boolean (predicate) method
4. Modifier (set) method
5. Iterator method
6. Delete or destructor method
7. Order within each step: private, protected and public

# Method test design patterns

### Concept - Functional Cohesion

A method implements a fragment of the class contract. The responsibility of a method is called its function. Functional cohesion: the unity of purpose (no method feels out of place) among two or more functions.

## Category-Partition

Category-partition is a general purpose test design pattern.

The intent is to design method scope test suites based on I/O analysis.

Appropriate for any method that implements one or more functions. Systematic technique to identify functions and exercise each I/O relationship.

### Fault Model

Reveal faults such that combinations of message parameters and instance variables result in missing or incorrect method output.

Doesn't cover neither faults that depend on certain message sequences nor faults that result in the corruption of object state, because it is hidden by the MUT's interface.

### Strategy Procedure

Test model: Black box testing

1. Identify all functions of the MUT
2. Identify input and output parameters of MUT (may also include class attributes and global variables)
3. Identify categories for each input parameter (category = subset of parameter values such that determines a particular behavior or output. The values from one category cannot be included in another category)
4. Partition each category into choices (A choice is a specific test value for a given category. Choices are made by intuition, experience or by applying domain testing)
5. Identify constraints on choices - Some choices may be mutually exclusive or inclusive
6. Generate test cases by enumerating all choices - Test cases are generated by the cross product of all choices.
7. Develop expected values for each test case

### Example

`getNextElement()` of class `List`

- Returns successive elements of the list
- Position is established by other methods, otherwise a `NoPosition` exception is thrown
- An `EmptyList` exception is thrown if the list is empty.

#### 1. Identify all functions of the MUT

- Primary function: return next element in the list.
- Secondary functions:
  - Keep track of the last position and wrap it from last to first.
  - Throw the NoPosition and EmptyList exceptions if appropriate.

#### 2. Identify input and output parameters of MUT

Input:

- position of the last referenced object
- the list itself

Output:

- the returned element
- the incremented position cursor
- Can also consider the exception thrown as an output

#### 3. Identify categories for each input parameter

| Parameter                               | Caterogry     |
| --------------------------------------- | ------------- |
| Position of the last referenced element | nth element   |
|                                         | Special cases |
| State of the list                       | m-elements    |
|                                         | Special cases |

#### 4. Partition each category into choices

| Parameter                               | Caterogry                         | Choices                                              |
| --------------------------------------- | --------------------------------- | ---------------------------------------------------- |
| Position of the last referenced element | nth element, $n \in [2, Max - 1]$ | $n = 2, n = Max - 1, n = \forall x \in ]2, Max - 1[$ |
|                                         | Special cases                     | Undefined, first, last                               |
| State of the list                       | m-elements                        | $m \forall x \in [2, Max[$                           |
|                                         | Special cases                     | Empty, singleton, full ($m = Max$)                   |

#### 5. Identify constraints on choices

- An undefined position precludes a response for all states of a list.
- If list is singleton, last and first refer the same position.

#### 6. Generate test cases by enumerating all choices

| Test Case | Cursor Position | Content     |
| --------- | --------------- | ----------- |
| 1         | Undefined       | m = rand(x) |
| 2         | first           | empty       |
| 3         | first           | singleton   |
| 4         | first           | m = rand(x) |
| 5         | first           | full        |
| 6         | n = 2           | empty       |
| 7         | n = 2           | singleton   |
| 8         | n = 2           | m = rand(x) |
| 9         | n = 2           | full        |
| 10        | n = rand(x)     | empty       |
| 11        | n = rand(x)     | singleton   |
| 12        | n = rand(x)     | m = rand(x) |
| 13        | n = rand(x)     | full        |
| 14        | n = Max -1      | empty       |
| 15        | n = Max -1      | singleton   |
| 16        | n = Max -1      | m = rand(x) |
| 17        | n = Max -1      | full        |
| 18        | last            | empty       |
| 19        | last            | m = rand(x) |
| 20        | last            | full        |

#### 7. Develop expected values for each test case

<img src="Imagens/4 - Caterogy partition step 7.png">

### Entry and Exit Criteria

Entry criteria: Small Pop or Alpha-Omega cycle

Exit Criteria:

- Every combination of choices is tested once
- Each exception must be thrown at least once
- Branch coverage must be satisfied

### Consequences

- Identification of categories and choices is a subjective process.
- The size of the test suite may become quite large.
- The generated test suite can also be used for subclasses.

### Known uses

- Elements of the Category-Partition approach appear in nearly all black-box test design strategies.

## Combinational Functional Test

Combinational Functional Test is appropriate for methods that implement complex selection algorithms (case-based logic). Refers to a unit test that validates the functionality of a single method by exploring combinations of its input values, usually without considering any external state or side effects.

The intent is to design test suite for behaviors selected according to combinations of state and/or message parameters.

### Fault Model

Reveals:

- Incorrect value assigned to a decision variable
- Incorrect or missing operator/variable in a predicate
- Incorrect structure in a predicate (dandling else, a misplaced semicolon, ...)
- Incorrect or missing default case
- Incorrect action
- Extra action(s)
- Structural errors in a decision table implementation: falling through (missing break in switch) or computing an incorrect value for an action selector

### Strategy Procedure Example:

[Pratica 3, Exercicio 2](../Praticas/Pratica%203/Pratica%203.md)

### Entry and Exit Criteria

Entry criteria: Small Pop

Exit Criteria:

- Produce every action at least once.
- Force each exception due to incorrect input (if any) at least once.
- Branch coverage for MUT.
- If polymorphic binding is used instead of a case statement to select an action, be sure that each possible binding is executed at least once.

### Consequences

- Design of the decision table often reveals design errors and omissions.
- Detects faults that are incorrect response actions to test messages.
- Faults resulting from the order of messages to other methods or faults corrupting object variables hidden by the MUT interface may not be shown.

### Known Uses

- This technique is embedded in several commercial testing tools.

## Recursive Function Test

The intent of Recursive Function Test is to test recursive methods. Recursion bugs can easily hide from low-coverage testing.

```java
int factorial(int n) {
  assert(n >= 0); // pre-condition
  if (n == 0) {
    return 1; // base case
  }
  else {
    int fact = n * factorial(n-1); // recursive case
    assert (fact > 0); // post-condition
    return fact;
  }
}
```

### Fault Model

- Execution continues when the precondition is violated for some illegal starting point or during the descendent phase.
- Base case is omitted or is miscoded.
- `>=` instead of `==`.
- Value returned by base case is incorrect.
- Recursive case expression implements an incorrect algorithm.
- Incorrect arguments appear in the recursive case message.
- Execution continues when the postcondition is violated during the ascendant phase.
- Fails to handle exceptions.
- Recursion to traverse data structures.
  - Incorrectly initializes or corrupts the data structure.
  - Does not correctly traverse the data structure when it is instantiated with a boundary case.
- Space and time faults.
  - Allows a recursion depth that exceeds available runtime memory.
  - Has a non-linear runtime, causing real-time deadline to be missed.

### Strategy: Test procedure

The test model should define:

- Base case
- Recursive case
- Pre-conditions for the initial call
- Post-condition
- All descent-phase calls
- All ascent-phase bindings

Test suite should contain:

- Attempt to violate the precondition in the initial call and at least once in the descent phase.
- Attempt to violate the postcondition at least once in the ascent phase.
- Test boundary cases on depth: zero, one and maximum.
- Attempt to force all exceptions in the server objects or the environment on which the method depends.
- Use domain analysis to choose arguments for methods with multiple arguments (can also use Category Partition).
- Use data structure states for recursive methods that traverse complex data structures.
  - Apply Category-partition to determine the interesting states.

### Entry and exit criteria

Entry criteria: Small Pop

Exit criteria:

- Null, Singleton and Maximal cases.
- Attempted violation of pre-condition in initial call and during the descent-phase.
- Attempted violation of post-condition during the ascendent-phase.
- Invariant Boundaries defined for values of multiple arguments and/or the states of data structures traversed.
- Worst-case runtime given system load and maximum depth.

### Consequences

- Requires no more than two dozen test cases
- Reveals faults that result in incorrect method evaluation for a given test message and state.
- Doesn't reveal faults that occur only under certain sequence of messages to other methods or that corrupt instance variables hidden by the MUT's interface.

### Known Uses

- Some of these strategies are part of the oral tradition of LISP programming.

## Polymorphic Message Test

The intent is to design a test suite for a client of a polymorphic server that exercise all client bindings to the server. A polymorphic method can be binded to several implementations

### Fault Model

- Client fails to meet all preconditions for all possible bindings.
  - Class MoneyMarketAccount imposes a minimum withdrawal of $500. However, superclass Account allows any positive value. A buggy client of Account would attempt to send a withdrawal message for less than $500 to a MoneyMarketAccount object.
- The implementation of a server class is changed or extended.
- To find faults must exercise all possible bindings.
- Focus on bugs from the point of view of the client.

### Strategy: Test Procedure

Test model: Develop extended flow graph of MUT.

<img src="Imagens/4 - Polymorphic Message Test, Test Model.png">

Test procedure:

1. Determine the number of candidate bindings for each message sent to a polymorphic server object.
2. Expand segment with multiway branch sub-graph for each segment that has a polymorphic message.

- Add two nodes for each binding: a branch node and sequential node.
- Add a final, catch-all node to represent runtime binding error.

3. Draw the test cases based on this model that exercise all branches.

<img src="Imagens/4 - Polymorphic Message Test, Test Procedure.png">

### Entry and Exit Criteria

Entry criteria

- Small Pop
- Server class should be stable

Exit criteria: Achieve branch coverage of extended message flow graph.

### Consequences

- Breaks the black-box strategy.
- Reveals many bugs in the client usage of the server class.
- Should be used together with a responsibility-based pattern.
- Requires analysis of server class hierarchy to determine all possible bindings.
