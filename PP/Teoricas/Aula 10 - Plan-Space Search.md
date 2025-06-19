# Plan-Space Search

Vamos usar tecnicas de CSPs para termos soluções mais flexiveis do que planos convencionais. Por exemplo, planos com uma ordem parcial das ações.

- Backward search desde o goal state
- Cada nó do search space é um plano parcial que contem falhas.

### Flaw: Open Goals

p é um open goal se não tiver nenhum causal link ligado a ele. Pode resolver-se com a criação de um causal link.

### Flaw: Threat

Uma ação $c$ que vem entre as ações $a$ e $b$ e pode afetar os valores da precondição de $b$.

Para resolver, podemos mudar a ordem, colocando $c$ antes de $a$ ou depois de $b$. Ou então adicionar inequality constraints para evitar que $c$ modifique as precondições de $b$

### Algoritmo

<img src="Imagens/Aula10 PSP Algorithm.png">

### Exemplo - dos slides

<img src="Imagens/Aula10 PSP Example1.png">
<img src="Imagens/Aula10 PSP Example2.png">
<img src="Imagens/Aula10 PSP Example3.png">
<img src="Imagens/Aula10 PSP Example4.png">
<img src="Imagens/Aula10 PSP Example5.png">
<img src="Imagens/Aula10 PSP Example8.png">
<img src="Imagens/Aula10 PSP Example7.png">
<img src="Imagens/Aula10 PSP Example9.png">
<img src="Imagens/Aula10 PSP Example10.png">
