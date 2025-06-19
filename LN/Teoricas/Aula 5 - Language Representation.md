# Vectors

## Language as vectors (sparse)

The meaning of a word is given by the set of contexts in which it occurs.

tasty tnassiorc -> Food<br>
greasy tnassiorc -> Food<br>
tnassiorc with butter -> Food<br>
tnassiorc for breakfast -> Food

## Documents as vectors

Quando temos uma coleção de documents, é possível criar uma term-document matriz:<br>

- Cada linha representa um termo no vocabulário.
- Cada coluna representa um documento.
- Cada célula pode ser:
  - Um binário -> 1 se a palavra aparece no documento e 0 se não aparece.
  - Uma contagem -> o número de vezes que a palavra aparece no documento.
  - TF-IDF -> Relação entre o número de vezes que o termo aparece no documento e o número de vezes que aparece nos outros documentos.

### TF-IDF

TF-IDF(t, d, D) = TF(t, d) \* IDF(t, D)

t -> termo<br>
d -> documento<br>
D -> conjunto de documentos

- TF (Term Frequency)
  - TF(t, d) = frequência do termo t no documento d. Pode ser tanto a frequência absoluta ou relativa.
- IDF (Inverse Document Frequency)
  - IDF(t, D) = ln( | D | / | {d $\in$ D: t $\in$ d} | )

#### Example

<img src="Imagens/Aula5 TF-IDF.png">

## Words as vectors

Esta matriz tem:

- Cada coluna é uma palavra do vocaulário.
- Cada linha é uma palavra do vocabulário.
- As células são o número de vezes que as palavras da linha e coluna se encontram próximas, isto é, no mesmo contexto. Este "próximas" pode variar.

<img src="Imagens/Aula5 Words vectors.png">

<img src="Imagens/Aula5 Words vectors context.png">

### Problem

Words such as "the", "it", "they" are not discriminative the best weighting or measure of association between two words should tell us more than a chance of coocurrence.

A solução é usar similarity / distances metrics de novo.

### Cosine similarity

similarity(A,B) $= \dfrac{A \cdot B}{||A|| \times ||B||} = \dfrac{\sum\limits_{i=1}^n A_i \times B_i}{\sqrt{\sum\limits_{i=1}^n A_i^2} \times \sqrt{\sum\limits_{i=1}^n B_i^2}}$

#### Example

<img src="Imagens/Aula5 Cosine similarity.png">

## Setences as vectors

It is now possible to create a chat bot.

<img src="Imagens/Aula5 Chatbot.png">
