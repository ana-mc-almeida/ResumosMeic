# Deterministic Models

## Domain Models

### Rigid vs Varying Properties

Propriedades rigidas não são alteradas durante a procura. Normalmente são passadas no estado inicial. Por outro lado, varying properties mudam durante a execução.

### Representação

State: Estrutura de dados random

Action:
- head - Nome e lista de parametros.
- preconditions - Testes que dizem se a ação pode ser executada. 
- effects - alterações feitas ao estado atual
- cost - o custo da ação, pode ser omitido sendo que o default é que tudo tem custo 1

<br>

## Forward Search

### Algorithm

<img src="Imagens/Aula8 Fowrad Search.png">

A parte não-deterministica tem duas propriedades:
- Sound: Se um plano $\pi$ for traçado, é porque é uma solução.
- Complete: Se o problema tiver solução, pelo menos um plano vai ser traçado até à solução.