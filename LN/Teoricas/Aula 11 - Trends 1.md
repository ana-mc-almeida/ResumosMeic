# Pre-Trained Models

Modelos de machine learning que são treinados com datasets gigantes para serem usados no futuro. A ideia é treiná-los durante imenso tempo numa certa lingua até ficarem excelentes. No futuro, quando forem usados só é preciso fazer uns ajustes especificos à linguagem.

Há duas maneiras de os usar:

- Inference -> É o processo que estes modelos usam para gerar um output com base no input.
- Prompting -> É o processo de dar um input ao modelo para o guiar a dar o output que desejamos.

### Zero-shot learning

É uma tecnica que permite fazer prompts aos modelos sem nenhum exemplo. Assim força-se o modelo a usar o reasoning que adquiriu durante o treino.

### Few-shot learning

Pelo contrário, é uma técnica em que damos vários exemplos ao modelo para o ajudar a percerber a tarefa. Estes exemplos não são usados para ajustar os parametros do modelo, servem apenas como inference.

### Transfer learning

É uma técnica que permite usar um modelo que foi desenvolvido para uma task especifica noutra task como starting point. Podem haver dois tipos de transfer learning:

- Feature-Based -> As features aprendidades graças à taks inicial são usadas como satarting point para uma nova task.
- Fine-Tuning -> Os pesos do modelo são ajustados para serem mais adequados à nova tarefa.

### Feature-based transfer learning

Podemos o DistilBERT, que é um pre-trained model com a língua inglesa, mas não treinado para fazer sentence classification. Para isso vamos fazer a tokenization da frase, de maneira a que o DistilBert consiga interpretar o que está a ser dito. Treina-se então uma Logistic Regression para se conseguir transformar a interpretação do DistilBert numa classificação.

### Fine-Tuning

Pega-se num pre-trained model e continua-se o seu treino num dataset mais pequeno. Adicionam-se novas layers para se adaptar aos requisitos da nova tarefa. Podemos eventualmente freeze algumas layers para só se mudar partes especificas do modelo. No entanto, acaba sempre por ser bastante caro voltar a treinar estes modelos gigantes.

## Parameter-Efficient Adaptation Methos

Há imensos parameter-efficient adaptaion methods, mas só nos vamos focar em adapters e no LoRa

### Adaptares

Adapters são layers pequenas e faceis de treinar que são inseridas na arquitetura de um pre-trained model. São úteis para obter uma parte especifica da taks em mente enquanto mantêm a maior parte dos pesos do modelo frozen.

Os adaptares têm um número de parametros bastante pequeno relativamente ao modelo inteiro. E apenas os adapters são treinados com o dataset da nova task, reduzindo muito o custo computacional.

Durante a inference, são usados tanto os pesos do pre-trained model como os pesos das adapter layers para gerar o output.

### LoRA (Low-Rank Adaptation)

_Não percebi nada, ninguém percebeu ig, nem o chat me salvou :(_

LoRA vai arranjar uma matriz AB de low rank, para dar update mais facilmente aos pesos. Usar matrizes de low rank faz com que seja muito mais fácil. Depois juntam-se os pesos normais e os pesos das matrizes AB atualizados.

## Multi-taks learning

É um campo de machine learning em que se resolvem várias tarefas ao mesmo tempo de forma a que seja possível aproveitar conhecimento compartilhado de de cada uma das tasks para melhorar o desempenho global.

Cada task tem o seu conjunto de parametros que dão as caracteristicas únicas dessa taks. Mas também aprendem uma representação partilhada de entre todas as tasks que abrange features semelhantes. Essa representação compartilhada permite que o modelo generalize melhor, ou seja, que ele consiga reconhecer e aplicar esses padrões em várias tarefas diferentes, otimizando o desempenho ao lidar com informações variadas, mas que têm algo em comum.

Há hard sharing, soft sharing e muito mais abordagens:
<img src="Imagens/Aula11 Multi-task Soft vs Hard sharing.png">

## Compression Techiques

Metodos que tentam diminuir a complexidade ou tamanho dos modelos sem perdas significativas de performance:

- Pruning
- Quantization
- Teacher-Student model

### Pruning

Simplesmente a remoção de pesos, neurónios ou camadas desnecessárias da rede neuronal.

<img src="Imagens/Aula11 Pruning.png">

### Quantization

Reduz a precisão dos pesos e das funções de ativação da rede neuronal de forma a reduzir a memória e os requisitos computacionais.

<img src="Imagens/Aula11 Quantization.png">

### Teacher-Student

O Teacher model é o pre-trained model, que é super poderoso, mas computacionalmente caro.<br>
O Student model é um modelo mais simples, que queremos treinar para fazer o mesmo que o teacher. Tem menos parametros que o teacher e é tipicamente mais eficiente.

#### Knowledge Distillation

É o processo de passagem de informação do teacher para o student. Em vez de o student ser treinado com o mesmo dataset e o mesmo training que o teacher, aprende a imitar o comportamento do teacher. O teacher dá soft labels, que são as probabilidades de ser cada classe, em vez de serem hard labels, que é a label exata.

## Applications

- Question Answering (QA)
- Resumir
- Inferencia
  - Para duas frases dizer qual é a relação entre elas:
    - implicação, neutra, contradição
- Análise de sentimentos
- Reasoning para senso comum
- Deteção de erros
- Deteção de cyberbullying
