

<!-- toc -->

- [1](#1)
- [2](#2)
  * [a)](#a)
  * [b)](#b)
- [3](#3)
  * [a)](#a-1)
  * [b)](#b-1)
  * [c)](#c)
  * [d)](#d)
- [4](#4)
- [Strange Days](#strange-days)
  * [Sequence "4 3 3 4 2"](#sequence-4-3-3-4-2)
  * [Sequence every red cut](#sequence-every-red-cut)

<!-- tocstop -->

# 1

Hyponym: Domestic cat<br>
Meronym: Whisker<br>
Hyperym: Animal<br>
Synonym: There aren't any<br>
None of the Above: leopard<br>

# 2

## a)

$friend(Dart, Dustin) \implies \forall x[friend(x, Dustin) \implies likes(x, Dart)]$

## b)

$\exists xy [friend(Dustin, x) \land \neg likes(x, Dart) \land friend(Dustin, y) \land \neg likes(y, Dart) \land different(x, y)]$

# 3

## a)

$\neg \exists x sister_of(Peter, x)$

## b)

$\exists x sister_of(Peter, x)$

## c)

$\forall x,y[(sister\_of(Peter, x) \land sister\_of(Peter, y)) \implies equal(x,y)]$

## d)

Opção 1:<br>

$b) \land c)$

Opção 2:

$\exists x[sister\_of(Peter, x) \land \forall y[\neg equal(x, y)] \implies \neg sister\_of(Peter, y)]$

# 4

Syntatic analysis:

- VP
  - V $_t$
    - likes
  - NP
    - DET
      - a
    - NN
      - dog

Semantic analysis:

$\lambda P.\lambda x.P(\lambda y.LIKES(x,y))@\lambda Q.\exists z[DOG(z) \land Q(z)]$ = <br>
$\lambda x. [\lambda Q.\exists z[DOG(z) \land Q(z)]@\lambda y.LIKES(x,y)]$ = <br>
$\lambda x.\exists z[DOG(z) \land \lambda y.LIKES(x,y)@z]$ = <br>
$\lambda x.\exists z[DOG(z) \land LIKES(x,z)]$

<br>

# Strange Days

## Sequence "4 3 3 4 2"

Syntatic analysis

- A
  - B
    - E
      - 4
    - F
      - 3
  - C
    - F
      - 3
    - E
      - 4
  - D
    - 2

Semantic analysiss

B = 4 \* 3 = 12<br>
C = 3 + 4 = 7<br>
A = 12 + 7 - 2 = 17<br>

## Sequence every red cut

Syntatic analysis:

- S
  - NP
    - DET
      - every
    - N
      - red
  - VP
    - Vi
      - cut

Semantic analysis:

every -> $\lambda P.\lambda Q.\forall x[P(x) \implies Q(x)]$<br>
red -> BLUE<br>
cut -> $\lambda x.CUT(x)$<br>

NP -> $\lambda P.\lambda Q.\forall x[P(x) \implies Q(x)]@BLUE$ =<br>
$\lambda Q.\forall x[BLUE(x) \implies Q(x)]$<br>

VP = cut

S -> $\lambda Q.\forall x[BLUE(x) \implies Q(x)]@\lambda y.CUT(y)$ =<br>
$\forall x[BLUE(x) \implies \lambda y.CUT(y)@x]$ =<br>
$\forall x[BLUE(x) \implies CUT(x)]$
