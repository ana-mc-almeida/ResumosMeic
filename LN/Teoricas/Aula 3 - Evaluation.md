# Comparing Strings

## Edit-based

Edit-based metrics allow to quantify how different two strings are. It counts the amount of operations to transform one string into the other.

### Levenshtein distance

No inicio cria-se uma tabela com um espaço branco seguido da palavra, tanto para a linha para a coluna.

<img src="Imagens/Aula3 Levenshtein.png">

Para preencher as outras celulas é preciso verificar se a letra na linha e coluna são iguais. 
- Se forem iguais o resultado é copiado da célula diretamente diagonal em cima e à esquerda. 
- Se não forem iguais o resultado da célula vai ser o minimo das celulas acima, à esquerda e à diagonal superior esquerda + 1.

<img src="Imagens/Aula3 Levenshtein Resolvido.png">

### Longest Common Subsequence (LCS) distance

Allows only insertion and deletion, not substitution.

### Hamming distance

Allows only substitution. Only works with strings of the same length.

### Damerau-Levenshtein distance

Allows insertion, deletion, substitution and transposition (swapping) of two adjacent characters.

### Jaro distance

Allows only transposition.

## Similarity-Metrics (Token-based)

For similarity metrics the highest the value, the more similar the strings are. Jaccard and Dice use sets and not bags. There are no repeated elements in a set.

### Jaccard

| $ s \cap t $ | / | $ s \cup t $ |

### Dice

$2 \times | s \cap t | / (|s| + |t|)$

## Comparing Strings at the sound level

### Soundex

Algorithm:
1. Retain the first letter of the name.
2. Remove all vowels, y, h, w (but not the ones in the first position).
3. Replace the other letters by digits (after the first letter):
    - b, f, p, v -> 1
    - c, g, j, k, q, s, x, z -> 2
    - d, t -> 3
    - l -> 4
    - m, n -> 5
    - r -> 6
4. Adjacent numbers are coded as a single number (ex: 55 -> 5).
5. At the end if you have more than 3 digits drop them after the third one (ex: L2345 -> L234). If you have less than 3 digits add zeros (ex: L2 -> L200).

## Conclusion

This mechanisms can be used to also compare sentences, but only at the lexical level, no semantics.

<img src="Imagens/Aula3 Comparing Strings sentences.png">

In this example, if we were to use Jaccard or Dice, the system would think that these sentences are similar. However, they differ a lot semanticaly.

<br>

# Automatic Evaluation

### Base line

A target for your system to beat. You should always compare your system with others (even if with a random baseline)

### Metrics

True Positives (TP)
True Negatives (TN)
False Positives (FP)
False Negatives (FN)

- Precision -> TP / (TP + FP)
- Recall -> TP / (TP + FN)
- Accuracy -> (TP + TN) / (TP + TN + FP + FN)
- F-measure
- F1-measure = 2 * P * R / (P + R)

### Other Metrics

- Bleu - Machine Translation
- Meteor - Machine Translation
- Rouge - initially summarization
- Perplefixty - to evaluate Language Models
- ...
- Comet - trained measure (deep learning model)

These metrics are used in generative tasks such as dialogue systems.

<br>

# Human Evaluation

### Advantages

- Nuance understanding: Humans can understand context, irony, humor and cultural references.
- Quality assessment: Humans can judge subjective qualities like readability, coherency and engagement of the text.
- Error identification: Humans can provide detailed insights into the nature of the error.
- Ground truth benchmarking: human judgment often serves as the gold standard.

### Types

- Direct assessment - 1/10 scale or 1/100 scale.
- Ranked-Based - outputs are ranked in order
- Paired Comparison - choose between two outputs which is better.

## Automatic vs. Human Evaluation

We use human evaluation to evaluate automatic evaluation. Pearson, Spearman, and Kendall are used to find the correlation between human scores and metric scores. The higher the correlation with human scores, the better the metric is.

<img src="Imagens/Aula3 Pearson, Spearman, Kendall.png">

When comparing two metrics to two systems, it is more important that the metrics get the same tendency as humans rather than getting the same results as the human did.

<img src="Imagens/Aula3 Rank order.png">

<br>

## Extrinsic vs. Intrinsic Evaluation

Intrinsic: evaluate your system alone.<br>
Extrinsic: evaluate your system as a component of a more complex system.

Example:<br>
Question Classification (QC) vs. Question/Answer (QA)

- A QC system can be evaluated "per se" and have a score X (intrinsic evaluation)
- The same system might replace an existing QC system in a QA system, an improve (or not) the QA system (extrinsic evaluation of the QC system)