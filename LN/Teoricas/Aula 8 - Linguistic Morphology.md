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

// TODO Adicionar uma explicação decente quando fizer nos labs, porque os slides tão uma merda. Nem o daniel percebeu e teve ali a perguntar bué '-'


## Deep-learning

Part-of-Speech é uma labelling task. RNN's, LSTM's ou outras arquiteturas podem ser usadas para treinar este tipo de modelos.

<br>

# Main Concepts

As palavras são consistuidas por morphenes. As morphenes podem ser:
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