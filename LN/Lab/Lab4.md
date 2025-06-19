

<!-- toc -->

- [1](#1)
  * [a)](#a)
  * [b)](#b)
  * [c)](#c)
- [2](#2)
- [3](#3)
  * [a)](#a-1)
  * [b)](#b-1)
- [N-Grams](#n-grams)
- [1](#1-1)
  * [a)](#a-2)
  * [b)](#b-2)
  * [c)](#c-1)
- [New email challenge](#new-email-challenge)

<!-- tocstop -->

# 1

## a)

C

## b)

C

## c)

A

# 2

"C"

# 3

## a)

(hello|bye)\*(byebye)

## b)

(bye|hellobye|hellohellobyebye)\*(hello)?

<br>

# N-Grams

# 1

## a)

Punctuation removal and lowercasing

## b)

|              | \<s> | the | butter flies | fly | \</s> |
| ------------ | ---- | --- | ------------ | --- | ----- |
| \<s>         | 0    | 3   | 0            | 0   | 0     |
| the          | 0    | 0   | 4            | 0   | 0     |
| butter flies | 0    | 0   | 0            | 3   | 0     |
| fly          | 0    | 0   | 0            | 0   | 1     |
| \</s>        | 0    | 0   | 0            | 0   | 0     |

## c)

$P($ \<s> the butterflies fly $) = P(the|<s>) \times P(butterflies | the) \times P(butterflies | fly) \times P(fly | </s>) = \dfrac{3}{6} \times \dfrac{4}{6} \times \dfrac{3}{6} \times \dfrac{1}{6} = \dfrac{1}{36}$

<br>

# New email challenge

```
[a-zA-Z0-9\-_](.[a-zA-Z0-9\-_]|[a-zA-Z0-9\-_])+@[a-zA-Z0-9][a-zA-Z0-9\-]+[a-zA-Z0-9]\.[A-Za-z]{2,}
```
