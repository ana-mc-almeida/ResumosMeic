# 2.1

## a)

Med = 2

## b)

0 ("", "") -> 0 (B, B) -> 0 (I, I) -> 1 (O, F) -> 2 (O, E)

# 2.2

## a)

$C_1$ = $C_3$

## b)

p + 1

# 2.3

## a

s = { the, zombie, in, their, room }<br>
t = { the, flowers, in, ~~the~~, room} -> { the, flowers, in, room}

jaccard(s, t) = $\dfrac{| s \cap t |}{| s \cup t |} $ = $\dfrac{ | \{ the, in, room\} | }{ | \{ the, zombie, flowers, in, their, room \} |}$ = $\dfrac{3}{6} = 0.5$

## b

jaccard(s, t) = $\dfrac{| s \cap t |}{| s \cup t |} = \dfrac{8}{13}$

## c

s = {th, he, zo, om, mb, bi, ie, in, ei, ir, ro, oo}
t = {th, he , fl, lo, ow, we, er, rs, in, ro, oo, om}

jaccard(s, t) = $\dfrac{| s \cap t |}{| s \cup t |} $ = $\dfrac{6}{18} = 0.(3)$

# 2.4

|     | A   | B   |
| --- | --- | --- |
| TP  | 4   | 3   |
| TN  | -   | -   |
| FP  | 2   | 0   |
| FN  | 1   | 2   |

A: <br>
Precision = TP / (TP / FP) = 4 / (4 + 2) = 0.67<br>
Recall = TP / (TP + FN) = 4 / (4 + 1) = 0.8<br>
F1 = 2 _ Precision _ Recall / (Precision + Recall) = 2 _ 0.67 _ 0.8 / (0.67 + 0.8) = 0.72<br>

B:<br>
Precision = 3 / (3 + 0) = 1<br>
Recall = 3 / (3 + 2) = 0.6<br>
F1 = 2 _ 1 _ 0.6 / (1 + 0.6) = 0.75

<br>

# Escape room:

1. 1358
2. 440
3. 070
