# Conflict sets

### Dead-end Definition

Um dead-end ocorre quando numa árvore se encontra um estado em que todos os valores de uma variável entram em conflito com a solução parcial encontrada até lá. Essa variável é também considerada uma dead-end variable.

<br>

# Backjumping styles

## Gashnig's backjumping

À medida que se percorre a árvore, também se verifica onde é que podem haver inconsistencias. Quando aparece um dead-end salta-se até onde se detetou que havia uma inconsistencia.

<img src="Imagens/Aula6 Gashnig1.png">

<img src="Imagens/Aula6 Gashnig2.png">

### Example

Neste exemplo, ao percorrer a árvore pelo caminho assinalado, encontra-se uma inconsistencia logo em $x_3$ - se $x_1$ = red e $x_3$ = blue, $x_7$ fica sem valores possíveis no seu dominio. Como não se encontra mais nenhuma incoerência até se ir processar $x_7$ o backjumo é então feito para $x_3$.

<img src="Imagens/Aula6 Gashnig example1.png">

<img src="Imagens/Aula6 Gashnig example2.png">

#### Exemplo completo

<img src="Imagens/Aula6 gashnig full example.png">

<br>

## Graph-based backjumping

!!! Não me lembro de ter visto os outros backjumps por isso vou ver onde a stora começa na próxima aula e depois do update ig !!!