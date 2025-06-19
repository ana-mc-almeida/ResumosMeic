

<!-- toc -->

    + [Word Embedding Definition](#word-embedding-definition)
- [Dimentionality Reduction](#dimentionality-reduction)
  * [Latent Semantic Analysis (or Indexing)](#latent-semantic-analysis-or-indexing)
    + [Singular Value Decomposition (SVD)](#singular-value-decomposition-svd)
    + [Example term-document matrix](#example-term-document-matrix)
    + [Example word-word matrix](#example-word-word-matrix)
- [Neural Word Embedding](#neural-word-embedding)
  * [Word2Vec](#word2vec)
    + [Model architecture](#model-architecture)
    + [Training Data](#training-data)
    + [Conclusion](#conclusion)
  * [GloVe](#glove)
  * [fastText](#fasttext)
  * [BERT - Contextual model](#bert---contextual-model)
    + [Task1 - Masked Language Model](#task1---masked-language-model)
      - [Example](#example)
    + [Task2 - Next Sentence Prediction](#task2---next-sentence-prediction)
      - [Example](#example-1)
    + [Training](#training)
- [Neural Sentence Embeddings](#neural-sentence-embeddings)
- [Evaluating Word Embeddings](#evaluating-word-embeddings)

<!-- tocstop -->

### Word Embedding Definition

Um word embedding é a representação de uma palavra com um vetor. O objetivo é que palavras com significados semelhantes sejam (hopefully) representados por vetores que estão próximos no espaço vetorial.

<img src="Imagens/Aula7 Word Embedding.png">

<br>

# Dimentionality Reduction

## Latent Semantic Analysis (or Indexing)

Reduz um espaço vetorial de grande dimensão, reduzindo o tamanho da representação original. Para isto usa-se o Singular Value Decomposition method.

### Singular Value Decomposition (SVD)

Procedimento para encontrar as dimensões mais importantes de um data set, isto é, as dimensões onde a data varia mais. Sendo A a matriz do nosso data set, com este procedimento obtemos: A = UDV $^T$.

<img src="Imagens/Aula7 SVD.png">

<br>

### Example term-document matrix

d1: Shipment of gold damaged in a fire.<br>
d2: Delivery of silver arrived in a silver truck.<br>
d3: Shipment of gold arrived in a truck.<br>

Suppose that we use the term frequency as term weights and query weights. The following document indexing rules are also used:

- stop words were not ignored
- text was tokenized and lowercased
- no stemming was used
- terms were sorted alphabetically

**Problem**: Use Latent Semantic Indexing (LSI) to rank these documents for the query gold silver truck.

**Step 1**: Criar a term-document matriz A.

<img src="Imagens/Aula7 LSI example1.png">

<br>

**Step 2**: Decompor a matriz A para encontrar as matrizes U, S e V.

<img src="Imagens/Aula7 LSI example2.png">

<br>

E acabamos com a nova representação da palavra "delivery":

<img src="Imagens/Aula7 LSI example3.png">

<br>

### Example word-word matrix

Para os k termos individuais mais importantes:

<img src="Imagens/Aula7 LSI example word-word.png">

<br>

# Neural Word Embedding

## Word2Vec

Usa o modelo skip-gram e o modelo CBOW. Skip-gram baseia-se na palavra atual para prever as palavras à sua volta que se insiram no contexto. O CBOW prevê a palavra atual a partir do contexto das palavras à sua volta.

### Model architecture

<img src="Imagens/Aula7 skip-gram architecture.png">

### Training Data

<img src="Imagens/Aula7 skip-gram training.png">

Word embeddings are the Weights

<img src="Imagens/Aula7 skip-gram embeddings.png">

### Conclusion

Skip-gram e CBOW fazem fake tasks porque o seu objetivo principal quando são treinados não é exatamente ter um modelo funcional, mas sim gerar embeddings das palavras.

## GloVe

Nem a stora se lembrava do que é que o GloVe faz, acho que só é preciso saber que é um modelo de word embeddings independente do contexto, tal como o Word2Vec.

## fastText

Acho que também não é preciso saber grande coisa, só temos de saber que é um modelo de word embedding independente do contexto focado em subwords.

## BERT - Contextual model

Este modelo de word embedding já tem em conta o contexto. É treinado em duas tasks.

### Task1 - Masked Language Model

BERT altera 15% das palavras do input adicionando uma mascara. Esta mascara é composta por:

- 80% Token MASK - para ver se o BERT consegue perceber que palavra devia estar ali.
- 10% Palavra incorreta - para ver se o BERT consegue corrigir uma frase que não faz sentido.
- 10% Palavra correta - para garantir que o BERT não muda sempre a palavra e considera a hipotese de a palavra até fazer sentido no contexto.

Depois corre a sequência usando um bidirectional Transformer encoder e tenta prever as palavras mascaradas. Este bidirectional quer dizer que o modelo tem em conta as tanto as palavras que vêm antes como as que vêm depois da palavra que está a ser analisada.

#### Example

Input: the man went to the [MASK1]. He bought a [MASK2] of milk.<br>
Labels: [MASK1] = store; [MASK2] = gallon.

<br>

### Task2 - Next Sentence Prediction

Dadas duas frase A e B, o BERT tentar dizer se B é realmente a frase que vem a seguir a A ou se é só uma frase random.

#### Example

Sentence A: the man went to the store.<br>
Sentence B: he bought a gallon of milk.<br>
Label: IsNextSentence.

Sentence A: the man went to the store.<br>
Sentence B: Penguins are flightless.<br>
Label: NotNextSentence.

<br>

### Training

O processo de treino do BERT tem duas fases: pre-training e fine-tuning. O pre-training é bastante caro, mas é uma coisa que se faz apenas uma vez para cada lingua. Por outro lado, o fine-tuning não é nada caro.

# Neural Sentence Embeddings

Os metodos mais straigthforward para ter sentence embeddings são a soma e a média dos word embeddings das palavras da frase.

<br>

# Evaluating Word Embeddings

Podemos usar avaliações extrinsecas:

- Question Answer
- Spell Checking
- ...

Ou avaliações intrinsecas:

- Correlação entre o algoritmo e humanos usando word similarity ratings, por exemplo, o cosseno.
- TOEFL -> testes de vocabulário de escolha multipla.
