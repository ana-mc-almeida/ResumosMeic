

<!-- toc -->

- [Linguistic Knowlege](#linguistic-knowlege)
    + [Phonetic Knowlege](#phonetic-knowlege)
    + [Morphological knowlege](#morphological-knowlege)
    + [Syntatic knowlege](#syntatic-knowlege)
    + [Semantic Knowlege](#semantic-knowlege)
    + [Pragmatic Knowlege](#pragmatic-knowlege)
    + [Discourse Knowlege](#discourse-knowlege)
    + [World Knowlege](#world-knowlege)
- [Part-Of-Speech Tagging](#part-of-speech-tagging)
  * [Rule-based](#rule-based)
    + [Example](#example)
  * [Stochastic](#stochastic)
    + [Hidden Markov Model - HMM](#hidden-markov-model---hmm)
    + [Viterbi](#viterbi)
      - [Algoritmo](#algoritmo)
      - [Exemplo](#exemplo)
  * [Deep-learning](#deep-learning)
- [Main Concepts](#main-concepts)
    + [Example](#example-1)
  * [Affixes](#affixes)
  * [Other word formations](#other-word-formations)

<!-- tocstop -->

# Linguistic Knowlege

### Phonetic Knowlege

Relaciona palavras com sons.

Por exemplo: "Eu almoço o almoço".

<br>

### Morphological knowlege

Estuda as palavras na frase.

Por exemplo: Quando se atribui a tag verbo a "almoço", sabemos que temos de a pronunciar de forma diferente.

<br>

### Syntatic knowlege

Determina como é que as palavras podem ser combinadas para formar uma frase.

<br>

### Semantic Knowlege

Responsável por atribuir significado às palavras e à frase.

<br>

### Pragmatic Knowlege

Tem em consideração o contexto da frase para a interpretar.

<br>

### Discourse Knowlege

É usado para conseguirmos interpretar as frases considerando o contexto das frases anteriores.

<br>

### World Knowlege

Senso comum.

Por exemplo: "O joão levou um tiro no olho e o cerebro saiu-lhe pelo ouvido." = Morreu :)

<br>

# Part-Of-Speech Tagging

O maior problema é que há palavras que podem ter mais do que uma tag. Por exemplo a palavra "book".

## Rule-based

**Step 1**: Each word receives a list of tags, using a dictionary.<br>
**Step 2**: The tag is assigned based on a set of rules designed by humans.

### Example

Input: He had a book

Step 1:

- he -> pronome
- had -> verbo
- a -> artigo
- book -> nome, verbo

Step 2:

- Rule 1: Se a tag anterior for um artigo, remove a tag verbo.

Com esta regra sabemos que book é um nome.

<br>

## Stochastic

Escolhe-se a melhor sequência de tags para uma dada frase (sequência de palavras). Para isso usam-se probabilidades.

### Hidden Markov Model - HMM

Descreve um sistema com estados não observaveis, no nosso caso, as tags, usando sequências observaveis, no nosso caso as palavras.

$$P(T | W) \approx \prod_{i=1}^n P(t_i | t_{i-1}) \times P(w_i | t_i)$$

São assumidas duas coisas:

- $P(t_i | t_{i-1})$ Assume que as palavras estão organizados em bigramas.
- $P(w_i | t_i)$ Assume que a probabilidade da palavra só depende da sua tag.

<br>

### Viterbi

#### Algoritmo

<img src="Imagens/Aula8 Viterbi Algorithm.png">

#### Exemplo

Descobrir qual é a sequencia PoS tags para a frase "will cook will". Seguindo as seguintes probabilidades:

<img src="Imagens/Aula8 Viterbi Example.png">

| SS   | will     | cook     | will     |
| ---- | -------- | -------- | -------- |
| Noun | _S(1,1)_ | _S(1,2)_ | _S(1,3)_ |
| Verb | _S(2,1)_ | _S(2,2)_ | _S(2,3)_ |

| BP   | will | cook      | will      |
| ---- | ---- | --------- | --------- |
| Noun | 0    | _BP(1,2)_ | _BP(1,3)_ |
| Verb | 0    | _BP(2,2)_ | _BP(2,3)_ |

Calculando cada uma das células:

_S(1,1)_ = P(will | Noun) _ P(Noun | \<s>) = 0.2 _ 0.7 = 0.14

_S(2,1)_ = P(will | Verb) _ P(Verb | \<s>) = 0.25 _ 0.3 = 0.075

_S(1,2)_ = max {<br>
&emsp;_S(1,1)_ _ P(cook | Noun) _ P(Noun | Noun) = 0.14 _ 0.2 _ 0.2 = 0.0056<br>
&emsp;_S(2,1)_ _ P(cook | Noun) _ P(Noun | Verb) = 0.075 _ 0.2 _ 0.4 = 0.006<br>
} = 0.006 -> que é o segundo argumento da função max por isso colocamos _BP(1,2)_ = 2

_S(2,2)_ = max {<br>
&emsp;_S(1,1)_ _ P(cook | Verb) _ P(Verb | Noun) = 0.14 _ 0.2 _ 0.4 = 0.0056<br>
&emsp;_S(2,1)_ _ P(cook | Verb) _ P(Verb | Verb) = 0.075 _ 0.1 _ 0.55 = 0.004125<br>
} = 0.0056 -> que é o primeiro argumento da função max por isso colocamos _BP(2,2)_ = 1

_S(1,3)_ = max {<br>
&emsp;_S(1,2)_ _ P(will | Noun) _ P(Noun | Noun) = 0.006 _ 0.2 _ 0.2 = 0.00024<br>
&emsp;_S(2,2)_ _ P(will | Noun) _ P(Noun | Verb) = 0.0056 _ 0.2 _ 0.4 = 0.00048<br>
} = 0.00048 -> que é o segundo argumento da função max por isso colocamos _BP(1,3)_ = 2

_S(2,3)_ = max {<br>
&emsp;_S(1,2)_ _ P(will | Verb) _ P(Verb | Noun) = 0.006 _ 0.25 _ 0.4 = 0.0006<br>
&emsp;_S(2,2)_ _ P(will | Verb) _ P(Verb | Verb) = 0.0056 _ 0.25 _ 0.55 = 0.00077<br>
} = 0.00077 -> que é o segundo argumento da função max por isso colocamos _BP(2,3)_ = 2

Preenchendo os valores na tabela ficamos com:

| SS   | will  | cook   | will    |
| ---- | ----- | ------ | ------- |
| Noun | 0.14  | 0.006  | 0.00048 |
| Verb | 0.075 | 0.0056 | 0.00077 |

| BP   | will | cook | will |
| ---- | ---- | ---- | ---- |
| Noun | 0    | 2    | 2    |
| Verb | 0    | 1    | 2    |

Olhando para a ultima coluna da tabela SS sabemos que o valor mais provavel é ser um verbo, depois usamos a tabela BP para saber a sequência inteira. da seguinte maneira:

<img src="Imagens/Aula8 Viterbi Example2.png">

E assim sabemos que a sequencia mais provável é Noun -> Verb -> Verb

## Deep-learning

Part-of-Speech é uma labelling task. RNN's, LSTM's ou outras arquiteturas podem ser usadas para treinar este tipo de modelos.

<br>

# Main Concepts

As palavras são constituidas por morphenes. As morphenes podem ser:

- Stems (lexical morphenes) - têm o significado principal da palavra.
- Affixes (grammatical morphenes) - muda o significado da stem.

### Example

Reusable:

- Use - Stem
- re- - quer dizer repetição
- -able - indica capacidade

## Affixes

- Prefixos: Antes da palavra. Ex: un- + happy = unhappy
- Sufixos: Depois da palavra. Ex: -ness + happy = happiness
- Infixos: No meio da palavra. Ex: chapéu-de-chuva no plural = chapéu**s**-de-chuva.
- Circumfixes: Inicio e fim da palavra ao mesmo tempo. Ex: entardecer -> Não existe "entarde", nem "tardecer", só faz sentido quando se adicionam os dois juntos.
- Clitics: Funcionam como palavra, mas nunca aparecem sozinhos. Ex: Eu contei-**os**.

## Other word formations

- Inflection: Não muda o label da palavra nem o significado. Ex: Conjugar verbos, mudar o genero da palavra. Gato -> Gata | Como -> Comes.
- Derivação: Cria uma palavra com um "significado diferente" ou mudança de label. Ex: fazer -> desfazer (significados opostos) | amigo -> amigável (passa de nome para adjetivo).
- Compounding: Combinação de stems. Ex: chapéu-de-chuva = chapéu + de + chuva.
- Cliticization: Palavras com clitics. Ex: Apagou-o.
