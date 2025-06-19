Nota: Em inglês "Phrase" refere-se a uma parte da frase e não à frase inteira.

## Classificação de partes da frase

- Grupo nominal (NP)
- Grupo verbal (VP)
- Grupo preposicioal (PP)
- Sujeito (SUBJ)
- Complemento direto (DO)
- Complemento indireto (IO)
- Interjeição (INTJ)
- ...

## TreeBank

Corpus onde todas as frases estão anotadas sintaticamente.

<br>

# Syntatic Parsing

### Constituents

Gupos de palavras que se comportam como unidade ou como frases.

## Context-Free Grammars - CFGs

CFGs capturam os constituents das frases e a sua ordem.

### Parsing Example

Para a frase "O Zé ama a Ana" e usando o seguinte conjunto de regras e obtemos:

<img src="Imagens/Aula9 Syntatic Parsing Example.png">

## Probabilistic CFG

Considera-se um conjunto de regras com uma probabilidade associada e escolhe-se o conjunto de regras mais provável.

### Example

Considerando a gramática:

<img src="Imagens/Aula9 Probabilistic CFG Example1.png">

Vamos calcular as duas árvores sintaticas para a frase "astronomers saw stars with ears" para decidir qual é a mais provável.

t1:
<img src="Imagens/Aula9 Probabilistic CFG Example2.png">

t2:
<img src="Imagens/Aula9 Probabilistic CFG Example3.png">

<img src="Imagens/Aula9 Probabilistic CFG Example4.png">

## Dependency Grammars

Já não se usam contituents e começam a usar-se ligações entre elementos da frase relacionados.

Há apenas uma root que não tem conexões até ela. Todos os outros elementos da árvore têm exatamente uma ligação para eles, isto faz com que exista um caminho único desde a root até qualquer outro elemento.

<img src="Imagens/Aula9 Dependency Grammar Example.png">

Este tipo de gramática é muito usada para traduzir português europeu para língua gestual portuguesa e vice-versa.
