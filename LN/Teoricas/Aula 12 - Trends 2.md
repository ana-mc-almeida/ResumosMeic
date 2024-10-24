# Large Language Models

Um Language Model calcula a probabilidade de cada sequência de tokens. Com LM podemos gerar uma linguagem ao adiciar um token de cada vez, tendo em conta os tokens gerados até agora (autoregressive language models).

A palavra "Large" serve para mostrar que este modelo é gigante, considerando:
- O tamanho do modelo (parametros)
- O tamanho do treino (tokens no dataset de treino)
- O tamanho da computação (necessária no treino)

## Training

1. São inicialmente treinados com unsupervised pre-training em corpora gigante para aprenderem a linguagem.

2. Depois usa-se supervised fine-tunning em tarefas especificas para melhorar a performance na tarefa principal.

`Nota: unsupervised quer dizer que o dataset não tem labels especificos, enquanto que supervised quer dizer que o dataset está labeled corretamente.`

3. Por fim é executado um Reinforcement Learning from Human Feedback (RLHF). O modelo tem as suas respostas refinadas com base nas preferências e feedback humano.

## Testing 

Inference -> Processo do modelo gerar um output com um dado input.

Decoding -> Sub-processo da Inference. Gera uma sequência de texto com base nas previsões de um modelo.

### Decoding

- Greedy Search: vê a probabilidade de cada palavra no vocabulário e escolhe a que tiver maior probabilidade.
- Beam Search: vai explorando multiplos caminhos, mantendo um registo dos melhores a cada passo. Nenhum dos caminhos é escolhido até ao fim do processo de decoding.
- Sampling: o modelo escolhe um subset de tokens (várias maneiras de escolher) e escolhe aleatoriamente um token desse subset.
- Temperature: ajusta a distribuição da probabilidade com escala.

#### Sampling 

- Random Sampling: O subset que vai criar vai ter em conta as probabilidades de cada um dos tokens. Depois escolhe-se aleatoriamente um token do subset. Tokens com maior probabilidade são mais provaveis de serem escolhidos, mas também há a chance de tokens com probabilidaes baixas serem escolhidos.
  - Por exemplo se as palavras possíveis forem "dormir", "comer", "correr" e "voar, o conjunto vai ser constituido por: dormir: 50%, comer: 30%, correr: 15%, voar: 5%.
- Top-k Sampling: Escolhe os k valores com maior probabilidade. E depois ajusta a distribuição de probabilidade, de modo a que a soma dê 1. Depois escolhe um aleatorio desse subset, tendo em conta as probabilidades.
- Top-p Sampling: Adiciona tokens até que a sua probabilidade chegue a p ou ultrapasse ligeiramente. Depois escolhe um aleatorio desse subset, tendo em conta as probabilidades.

#### Exemplo Temperature:
<img src="Imagens/Aula12 Temperature.png">

<br>

## Pros

- QA
- Translation
- Chit-chat
- Data science
- Image generation
- ...

## Cons

- Fallible
- Can be used to deceive us
- We will lose our capabilities
- Bias
- Environmental impact
- Intellectual property
- Plagiarism

<br>

# Prompting

Intruções ou queries dadas ao modelo.

Types:
- Single-sentence prompts: instruções básicas para se gerar uma resposta.
- Multi-turn prompts: uma conversa em que é preciso contexto para gerar um output interativo.

## Techniques

- Usar linguagem especifica e contexto para guiar o modelo.
- Experimentar com diferentes prompts.
- Persona pattern: Dizer ao modelo para atuar como a persona X e para fazer a task Y.
- Retrieval Augmented Generation (RAG): Antes de fazer a prompt ao LLM faz-se uma procura por relevantes documentos para a query e o prompt que se faz ao LLM tem em conta a query e esses documentos
- Chain of Though: Forçar o sistema a fazer uma sequência de passos intermédios antes da resposta final. Transforma problemas complexos em problemas menores.