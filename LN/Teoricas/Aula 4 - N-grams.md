# N-grams

### Definition

N-gram is a sequence of N tokens.

- N = 1 -> unigrams
- N = 2 -> bigrams
- N = 3 -> trigrams
- ...

### Examples

#### Example 1 - Complete sentences

- Once upon a \_\_\_
- Spoiler \_\_\_
- Stranger \_\_\_

Solution:

- Once upon a **time**
- Spoiler **alert**
- Stranger **things**

<br>

#### Example 2 - Automatic Speech Recognizer

- olá edgar
- ou lá apagar
- ó lá edgar
- ...

Which is the most likely?

<br>

#### Example 3 - Machine Translation System

Input: It is raining cats and dogs.<br>
Possible translations:

- Chovem cães e gatos
- Chove a potes
- Chovem potes

Which is the most likely?

<br>

## Word Prediction

Input: H -> history H = $W_1..W_{N-1}$<br>
Output: Probability of W being the next word.

P($W$ | H) = count(HW) / $\Sigma_k$ count(HK) = count(HW) / count(H);

#### Example

H = Once upon a<br>
$W$ = time<br>
P(time | Once upon a) = count(once upon a time) / count(once upon a)

#### Disadvantages

- Some sequences were never seen, so you might not have all these values.
- It is quite expensive to use a long H.

### Markov Assumption

It is possible to calculate the probability of a future event without having to look to the entire history.

To calculate P($W$ | H) = P($W_N$ | $W_1...W_{N-1}$):

- Use bigrams -> P($W$ | H) $\approx$ P($W_N$ | $W_{N-1}$)
- Use trigrams -> P($W$ | H) $\approx$ P($W_N$ | $W_{N-2}W_{N-1}$)

#### Example

<img src="Imagens/Aula4 Markov example.png">
<img src="Imagens/Aula4 Markov example bigrams.png">
<img src="Imagens/Aula4 Markov example trigrams.png">

<br>

## Sentence Probability

Chain rule of probability:<br>
$P(w_1^N) = P(w_1 | <s>) \times P(w_2 | <s> w_1) \times ... \times P(w_N | w_1^{N-1}) = \Pi_{k=1}^NP(w_k | w_1^{k-1})$

We will have the same problem as we did for Word Prediction so we will use Markov assumption again:

Using bigrams -> $P(w_1^N) \approx \prod\limits_{k=1}^NP(w_k | w_{k-1})$<br>
Using trigrams -> $P(w_1^N) \approx \prod\limits_{k=1}^NP(w_k | w_{k-2}w_{k-1})$

### Example

<img src="Imagens/Aula4 Markov example sentence.png">

$P($ I eat chinese food $) = P($ I $|$ \<s>$) \times P($ eat $|$ I $) \times P($ Chinese $|$ eat $) \times P($ food $|$ Chinese $) \times P($ \<s> $|$ food $)$

Como não sabemos $P($ I $|$ \<s>$)$ nem $P($ \<s> $|$ food $)$ vamos assumir que o seu valor é 1 para não interferir nas contas.

Logo:<br>
count(I eat) / count(I) _ count(eat Chinese) / count(eat) _ count(Chinese food) / count(Chinese) = <br>
= 13 / 3437 _ 19/938 _ 120/213 = ...

<br>

## Evaluation of N-Grams

### Perplexity

$PP(W) = P(w_1w_2...w_N)^{-\dfrac{1}{N}} = \sqrt[N]{\dfrac{1}{P(w_1w_2...w_N)}}$

For each model:

$PP(W) = P(w_1w_2...w_N)^{-\dfrac{1}{N}} = \sqrt[N]{\prod\limits_{i = 1}^{N} \dfrac{1}{P(w_1w_2...w_N)}}$

<br>

## Smoothing

Allow to deal with the fact that some sequences have zero or few occurrences.

### Laplace (or Add-one)

Add 1 to all the counts and recalculate.

With bigrams:<br>
$P$ Laplace $(W_N | W_{N-1})$ = $($ count $(W_{N-1}W_N) + 1) / ($ count $(w_{N-1}) + |V|)$

- |V| is the the number of words in the vocabulary V

#### Example

<img src="Imagens/Aula4 Smoothing Laplace example.png">
