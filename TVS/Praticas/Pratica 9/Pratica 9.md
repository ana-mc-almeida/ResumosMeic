

<!-- toc -->

- [1.](#1)
      - [Variable x](#variable-x)
      - [Variable y](#variable-y)
  * [Extra](#extra)

<!-- tocstop -->

# 1.

> Considere o seguinte pedaço de código Java e desenhe o respectivo grafo de controlo de fluxo, identificando claramente os vários segmentos do código e os vários du-pairs e respectivos dc-paths para as variáveis x e y. Pode ser útil indicar em cada segmento quais as variáveis definidas e utilizadas em cada segmento.

> ```
> public float doSomething(int z) {
>   float x = 0;
>   float y = 0;
>
>   if (z >= 0) {
>     x = Math.sqrt(z) - 4;
>
>     if (x >= 0 && x <= 5)
>       y = f(x);
>     else
>       x = h(z);
>   }
>
>   if (y < 5)
>     y = g(x, z);
>
>   return x + y + z;
> }
> ```

<img src="1 - general division.png">

<img src="1 - general CFG.png">

#### Variable x

<img src="1 - x CFG.png">

Def(x) = { 2, 5, 8, }<br>
Use(x) = { <6, 7>, <6, 8>, 7, 10, 11 }

(We consider only the valid du-pairs, for example the only valid du-pairs that contain definition node 2, are (2, 10) and (2, 11))

| du-pair    | dc-path              |
| ---------- | -------------------- |
| (2, 10)    | <2, 3, 4, 9, 10>     |
| (2, 11)    | <2, 3, 4, 9, 11>     |
|            | <2, 3, 4, 9, 10, 11> |
| (5, <6,7>) | <5, 6, 7>            |
| (5, <6,8>) | <5, 6, 8>            |
| (5, 7)     | <5, 6, 7>            |
| (5, 10)    | <5, 6, 7, 9, 10>     |
| (5, 11)    | <5, 6, 7, 9, 10, 11> |
|            | <5, 6, 7, 9, 11>     |
| (8, 10)    | <8, 9, 10>           |
| (8, 11)    | <8, 9, 10, 11>       |

To achieve All-Defs for x variable:

- <1, 2, 3, 4, 9, 10>
- <1, 2, 3, 4, 5, 6, 8, 9, 10>

To achieve All-Uses for x variable: (basically all du-pairs must be exercised)

- <1, 2, 3, 4, 9, 10, 11> (covers def 2)
- <1, 2, 3, 4, 5, 6, 7, 9, 10, 11> (covers part of def 5)
- <1, 2, 3, 4, 5, 6, 8, 9, 10, 11> (covers part of def 5 and def 8)

To achieve All-dupaths for x variable: (basically every dc-path must be exercised)

- paths from All-uses
- <1, 2, 3, 4, 9, 11>
- <1, 2, 3, 4, 5, 6, 8, 9, 11>
- <1, 2, 3, 4, 5, 6, 7, 9, 11>

#### Variable y

<img src="1 - y CFG.png">

Def(y) = { 3, 7, 10 }<br>
Use(y) = { <9, 10> , <9, 11>, 11 }

| du-pair      | dc-path                    |
| ------------ | -------------------------- |
| (3, <9, 10>) | <3, 4, 9, 10>              |
|              | <3, 4, 5, 6, 8, 9, 10>     |
| (3, <9, 11>) | <3, 4, 9, 11>              |
|              | <3, 4, 5, 6, 8, 9, 11>     |
| (3, 11)      | <3, 4, 5, 6, 8, 9, 10, 11> |
|              | <3, 4, 9, 11>              |
| (7, <9, 10>) | <7, 9, 10>                 |
| (7, <9, 11>) | <7, 9, 11>                 |
| (7, 11)      | <7, 9, 11>                 |
| (10, 11)     | <10, 11>                   |

To achieve All-Defs for y variable:

- <1, 2, 3, 4, 9, 11>
- <1, 2, 3, 4, 5, 6, 7, 9, 10, 11>

To achieve All-Uses for y variable: (basically exercise all du-pairs)

- <1, 2, 3, 4, 9, 10, 11> (covers part of def 3 and defnition 3)
- <1, 2, 3, 4, 9, 11> (covers part of def 3)
- <1, 2, 3, 4, 5, 6, 7, 9, 11> (covers part of def 7)
- <1, 2, 3, 4, 5, 6, 7, 9, 10, 11> (covers part of def 7)

To achieve All-dupaths for y variable:

- <1, 2, 3, 4, 9, 10, 11> (from all-uses)
- <1, 2, 3, 4, 9, 11> (from all-uses)
- <1, 2, 3, 4, 5, 6, 7, 9, 11> (from all-uses)
- <1, 2, 3, 4, 5, 6, 7, 9, 10, 11> (from all-uses)
- <1, 2, 3, 4, 5, 6, 8, 9, 10, 11>
- <1, 2, 3, 4, 5, 6, 8, 9, 11>

## Extra

> Modifying code line `x = h(z);` to `z = h(z);` make the analysis for z variable.

<img src="1 - z CFG.png">

Def(z) = { 1, 8 }<br>
Use(z) = { <4, 5>, <4, 9>, 5, 8, 10, 11 }

(in this case (8,8) du-path is not possible becasue use comes before definition)

| du-pair    | dc-path                          |
| ---------- | -------------------------------- |
| (1, <4,5>) | <1, 2, 3, 4, 5>                  |
| (1, <4,9>) | <1, 2, 3, 4, 9>                  |
| (1, 5)     | <1, 2, 3, 4, 5>                  |
| (1, 8)     | <1, 2 3, 4, 5, 6, 8>             |
| (1, 10)    | <1, 2, 3, 4, 5, 6, 7, 9, 10>     |
|            | <1, 2, 3, 4, 9, 10>              |
| (1, 11)    | <1, 2, 3, 4, 5, 6, 7, 9, 10, 11> |
|            | <1, 2, 3, 4, 5, 6, 7, 9, 11>     |
|            | <1, 2, 3, 4, 9, 10, 11>          |
|            | <1, 2, 3, 4, 9, 11>              |
| (8, 10)    | <8, 9, 10>                       |
| (8, 11)    | <8, 9, 11>                       |
|            | <8, 9, 10, 11>                   |

To achieve All-Defs for z variable:

- <1, 2, 3, 4, 5, 6, 8, 9, 10, 11>

To achieve All-Uses for z variable:

- <1, 2, 3, 4, 5, 6, 8, 9, 10, 11>
- <1, 2, 3, 4, 9, 10, 11>

To achieve All-dupaths for z variable:

- <1, 2, 3, 4, 5, 6, 8, 9, 10, 11>
- <1, 2, 3, 4, 9, 10, 11>
- <1, 2, 3, 4, 9, 11>
- <1, 2, 3, 4, 5, 6, 7, 9, 10, 11>
- <1, 2, 3, 4, 5, 6, 7, 9, 11>
- <1, 2, 3, 4, 5, 6, 8, 9, 11>
