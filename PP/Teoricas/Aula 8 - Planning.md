# Deterministic Models

## Domain Models

### Representação

State: Estrutura de dados random

Action:
- head - Nome e lista de parametros.
- preconditions - Testes que dizem se a ação pode ser executada. 
- effects - alterações feitas ao estado atual
- cost - o custo da ação, pode ser omitido sendo que o default é que tudo tem custo 1

