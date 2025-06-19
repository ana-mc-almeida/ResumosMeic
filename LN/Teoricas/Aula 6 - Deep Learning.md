# Feed Forward Neural Networks

Redes neuronais convencionais não são suficientes para Lingua Natural:

- Os inputs são independentes uns dos outros, o que faz com que se perca o contexto das frases.
- Têm um tamanho de inputs fixos, o que raramente acontece quando se processa texto.

<br>

# Recurrent Neural Networks - RNN

<img src="Imagens/Aula6 RNN.png">

Com as RNN é possível ter em conta o contexto da frase. Cada elemento é processado, tendo em conta os elementos anteriores.

No entanto, pode ser preciso também olhar para as palavras seguintes para conseguir perceber o contexto. Para isso existem os Bidirectional RNNs:

<img src="Imagens/Aula6 Bidirectional RNNs.png">

### Exemplos

<img src="Imagens/Aula6 RNN examples.png">

One to many:

- Passar uma imagem e receber uma descrição.

Many to one:

- Receber um prompt e criar uma imagem.
- Receber script de um filme e atribuir-lhe uma categoria.

Many to many (primeiro):

- Tradução
- Sumarização

Many to may (segundo):

- Caracterizar as palavras de uma frase: verbo, nome, etc...

#### Character-based Example

<img src="Imagens/Aula6 Character-based example.png">

<br>

# Sequence to Sequence Models

Sequence to Sequence models têm uma arquitetura encoder-decoder. O encoder processa o input e transforma-o num vetor de contexto. Este vetor é suposto ser um bom resumo do input.

- Este vetor tem um tamanho fixo.

Depois o decoder pega no vetor de contexto e transforma-o no output.

### Teacher Forcing

Este mecanismo é usado para tarefas de sequence prediction. Em vez de o input de cada time step ser o valor gerado no time step anterior, usa-se o input original.

Vantagens:

- Acelera a aprendizagem do model, já que é guiado para a sequência certa.
- Evita acoumulação de erros.

Desvantagens:

- Se o modelo começar a confiar demasiado que vai ter auxilio do professor não vai conseguir recuperar dos seus próprios erros no futuro.

#### Example

<img src="Imagens/Aula6 Teacher Forcing.png">

### Melhorias

- Se o mecanismo Teacher Forcing for usado de maneira aleatória com uma probabilidade variável ao longo do tempo, o sistema já não vai poder depender do professor para corrigir os seus erros.

- À medida do tempo o uso de Teacher Forcing é diminuido. Com o progresso do treino o modelo aprende com as suas previsões.

<br>

# Generative Adversarial Networks

Uma rede neuronal (generator) cria novas instancias de data. O seu objetivo é criar amostras de data que sejam realistas ao ponto de serem indistinguiveis de data real.

Outra rede neuronal (discriminator) recebe inputs, tanto reais como gerados pelo generator e tenta dizer se é real ou fake.

Durante o treino, o generator é treinado para miniziar a accuracy do discriminator, gerando amostras realistas. O discriminator é treinado para maximizar a sua accuracy na classificação.

Uma das funcionalidades do discriminator pode ser verificação de autenticidade, verificando se um certo input é real ou inventado.

<br>

# Attention

Attention mechanisms são usados para align e tradução. No alignment escolhem-se as partes do input que são mais relevantes para cada palavra. A tradução usa a informação obtida no alignment para gerar o output.

Há vários tipos de attention mechanisms:

- Soft Attention: Associa importâncias diferentes a diferentes partes do input.
- Hard Attention: Escolhe partes especificas do input e ignora o resto.
- Self attention: Capture relações entre partes da frase.
- Multi-head attention: Extensão do self attention, onde há mais tipos de relações, por exemplo, dependencias sintaticas e semanticas.
- ...

Estes mecanismos funcionam bem sozinhos, combinados com redes neuronais, ou então formando encoders.

### Example

<img src="Imagens/Aula6 attention mechanisms example.png">

Cada palavra tem em conta também as outras palavras do input que são importantes quando se processa esta palavra.

<br>

# Transformers

Os transformers permitem que o processamento seja feito de forma paralela ao contrário das RNNs em que era preciso esperar pelo valor obtido na palavra anterior. Para isso usa-se self attention mechanisms.

<img src="Imagens/Aula6 transformers.png">

<img src="Imagens/Aula6 transformers 2.png">

<img src="Imagens/Aula6 transformers 3.png">

<br>

## Algorithm - Mega complexo espero que não seja preciso perceber

<img src="Imagens/Aula6 transformers algorithm1.png">

<img src="Imagens/Aula6 transformers algorithm2.png">

<img src="Imagens/Aula6 transformers algorithm3.png">

<img src="Imagens/Aula6 transformers algorithm4.png">

### Using multi-head attention mechanism

<img src="Imagens/Aula6 transformers algorithm5.png">

<img src="Imagens/Aula6 transformers algorithm6.png">

<br>

#### All together

<img src="Imagens/Aula6 transformers algorithm all together.png">
