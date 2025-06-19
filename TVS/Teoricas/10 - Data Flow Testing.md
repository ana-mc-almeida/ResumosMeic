# Data Flow Testing

Instead of focus on control flow of a program. Focus on data flow of a program.

Data Flow Testing focuses on the points at which variables change value and the points at which variables are read. This is done because the bad value computation is revealed only when it is used, but the problem could be in the assignment.

### Data operations categories

- **d** - Defined / Created / Initialized.
- **k** - Killed / Undefined / Released.
- **u** - Used.
  - **p** - used in predicate.
  - **c** - used in a computation.

A parameter x passed to a function

- call-by-value, is considered as a use (c-use) of x.
- call-by-reference, is considered as a definition and use (c-use) of x.

Variables can be used and re-defined in the same statement on both sides of an assignment or as a call by reference parameter in function call.

- x = x + 5;
- x \*= 5;
- increment(&y);

#### Example

- `x = y + z` : defines variable x and uses (c-use) variables y and z.
- `scanf ("%d %d", &x, &y)` : defines variables x and y
- `printf ("Output: %d \n", x + y)` : uses variables (c-use) x and y
- `if (x > 0)`: uses variable (p-use) x

| i   | Code Line           | Def  | C-use      | P-use |
| --- | ------------------- | ---- | ---------- | ----- |
| 1   | read (x, y);        | x, y |
| 2   | z = x + 2;          | z    | x          |
| 3   | if (z < y)          |      |            | z, y  |
| 4   | w = x + 1;          | w    | x          |
| -   | else                | -    | -          | -     |
| 5   | y = y + 1;          | y    | y          |
| 6   | print (x, y, w, z); |      | x, y, w, z |

## Static Data Flow Testing

Identify potential defects, commonly known as data flow anomaly.

### Data flow anomaly

Three types of abnormal situations with using variable.

- Defined and then defined again - action sequence **dd**.
- Undefined but referenced - action sequence **-u**.
- Defined but not referenced - action sequence **dk**.

## Dynamic Data Flow Testing

Select paths by analyzing the program's data flow in order to explore sequences of events related to the status of data objects.

### Strategy

1. Draw a data flow graph from program $P$.
2. For each variable, classify each node as defining or usage node.
3. Select one or more data flow testing criteria (All-uses, all-defs).
4. Identify paths in the data flow graph satisfying the testing criteria
5. Compute input values for each path

### Definition-clear path

A path is called a definition clear path (def-clear path) with respect to variable $v$ if $v$ has
been neither defined nor undefined in nodes of that path, except the first and last nodes.

### Definiton-use pair (du-pair)

A definition-use pair with respect to a variable $v$ is a pair $(d,u)$ such that $d$ is a node in the program's flow graph at which $v$ is defined, $u$ is a node or edge at which $v$ is used and there is at least one def-clear path with respect to $v$ from $d$ to $u$. In other words, there is at least one path $(d, \dots, u)$ such that the value that is assigned to $v$ at $d$ is used at $u$.

### Example

```
input(A,B)
if (B > 1)
  A = A + 7
if (A > 10)
  B = A + B
output(A, B)
```

<img src="Imagens/10 - du-pair example.png">

#### Variable A

Nodes defining A = {1, 2}<br>
Nodes using A = {2, 4, 5, <3,4>, <3,5>}

du-pair:

- (1,2)
- (1,4)
- (1,5)
- (1,<3,4>)
- (1,<3,5>)
- (2,4)
- (2,5)
- (2,<3,4>)
- (2,<3,5>)

| du-pair   | dc-path   |
| --------- | --------- |
| (1,2)     | <1,2>     |
| (1,4)     | <1,3,4>   |
| (1,5)     | <1,3,4,5> |
|           | <1,3,5>   |
| (1,<3,4>) | <1,3,4>   |
| (1,<3,5>) | <1,3,5>   |
| (2,4)     | <2,3,4>   |
| (2,5)     | <2,3,4,5> |
|           | <2,3,5>   |
| (2,<3,4>) | <2,3,4>   |
| (2,<3,5>) | <2,3,5>   |

#### Variable B

Nodes defining B = {1, 4}<br>
Nodes using B = {4, 5, <1,2>, <1,3>}

| du-pair   | dc-path   |
| --------- | --------- |
| (1,4)     | <1,2,3,4> |
|           | <1,3,4>   |
| (1,5)     | <1,2,3,5> |
|           | <1,3,5>   |
| (1,<1,2>) | <1,2>     |
| (1,<1,3>) | <1,3>     |
| (4,5)     | <4,5>     |

### Definition Complete Path

A complete path is a path from the entry node to the exit node.

### Simple path

A simple path is a path in which all nodes, except possibly the first and the last, are distinct.

### Data Flow Testing Criteria

- All-Defs - for every program variable $v$, at least one def-clear path from every definition of $v$ to at least one c-use or one p-use of $v$ must be covered.
- All-Uses - for every program variable $v$, at least one def-clear path from every definition of $v$ to every c-use and every p-use of v must be covered.
- All-P-Uses - for every variable $v$, at least one dc-path from every definition of $v$ to
  every P-use of v must be covered.
- All-C-Uses - for every variable $v$, at least one dc-path from every definition of $v$ to
  every C-use of v must be covered.
- All-P-Uses/Some-C-Uses - for every variable $v$, at least one dc-path from every definition of $v$ to every P-use of v must be covered. If a definition of $v$ has no P-uses, at least one dc-path from the definition of $v$ to a C-use of $v$ must be covered.
- All-C-Uses/Some-P-Uses - for every variable $v$, at least one dc-path from every definition of $v$ to every C-use of $v$ must be covered. If a definition of $v$ has no C-uses, at least one dc-path from the definition of $v$ to a P-use of $v$ must be covered.
- All DU-Paths - for every program variable $v$, every du-path from every definition of $v$ to every c-use and every p-use of $v$ must be covered.

## Relationship between strategies

<img src="Imagens/10 - relationship between strategies.png">
