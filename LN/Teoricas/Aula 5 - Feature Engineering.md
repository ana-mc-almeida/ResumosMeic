# Feature Engineering

### Definition

Feature Engineering is a machine learning approach in which domain knowledge is used to extract features from raw data. These features are used to improve the performance of machine learning algorithms. Some features make sense, others do not!

### Examples

- Classificar um nome próprio de acordo o seu genero, em português:
  - A última letra do nome pode ajudar a distinguir.
    - Maria, Luísa, Joaoa vs. Miguel, João, Pedro
  - O tamanho do nome não ajuda grande coisa.
    - Ana (3) vs Rui (3)
    - Inês (4) vs João (4)
  - De qualquer forma, vão haver sempre casos especificos onde se falha:
    - Luca, Garcia, Quaresma...

### Algorithms

Classification tasks:

- Decision trees
- Support Vector Machines
- Naive Bayes
- ...

Sequence prediction tasks:

- CRFs
- ...

## Naive Bayes

Se tivermos<br>
Categorias: $C = \{ c_1, ..., c_n \}$<br>
Evidencias ou Features: $x = \{ E_1, ..., E_m \}$

Então Naive Bayes diz que:<br>

$P(c_i | X) \approx P(E_1 | c_i) _ ... _ P(E_m | c_i) \* P(c_i) $

### Desvantagens

- Assumimos que as features são independentes umas das outras, mas nem sempre é assim.
- Frequência 0 são um problema, e para resolver é preciso usar tecnicas de Smoothing.

### Example

<img src="Imagens/Aula5 Naive Bayes.png">
