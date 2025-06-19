

<!-- toc -->

- [Symbolic Language Representation](#symbolic-language-representation)
  * [Keywords](#keywords)
  * [Sentences](#sentences)
  * [Regular Expressions](#regular-expressions)
  * [First Order Logic](#first-order-logic)
  * [Frames](#frames)
  * [Graph](#graph)
- [Compositional Semantic Parsing](#compositional-semantic-parsing)
  * [Notação 1](#notacao-1)
    + [Example](#example)
  * [Notação 2](#notacao-2)
    + [Exemplo](#exemplo)
  * [Semantic Parsing as Sequence Prediction](#semantic-parsing-as-sequence-prediction)
    + [BIO Tagging](#bio-tagging)
      - [Exemplo](#exemplo-1)
- [Semantic Relations](#semantic-relations)
- [Semantic Resources](#semantic-resources)

<!-- tocstop -->

# Symbolic Language Representation

## Keywords

Apenas algumas palavras influenciam um robot. Por exemplo, um robot é suposto conseguir distinguir as palavras "left", "right" e "stop" e tomar uma ação conforme a palavra.

## Sentences

Um agente percebe frases completas. Por exemplo, "Quem construiu o palácio" e consegue responder.

## Regular Expressions

Um agente percebe regular expressions. Por exemplo, "Quem construiu .\* palácio" e consegue responder.

## First Order Logic

Definem-se consntantes e relações com simbolos de lógica. Por exemplo:

- O Pedro é um estudante: student(PEDRO)
- Todos os estudantes são grandes: $\forall x$ student($x$) $\implies$ great($x$)
- Há pelo menos um grande estuante: $\exists x$ student($x$) $\wedge$ great($x$)

## Frames

Existe um type e slots. Há vários tipos de ações de que o sistema pode tratar (intents). Os slots são os pedaços de informação relevantes para completar a ação.

Por exemplo, para o input: "How much is the cheapest flight from Boston to New York tomorrow morning?"

Type = Airfare

Slots:

- Custo: cheapest
- Cidade de partida: Boston
- Cidade de chegada: New York
- Data de partida: Tomorrow
- Hora de partida: Morning

Outro exemplo é a compra de bilhetes no cinema.

O Type é uma compra de bilhetes.

Os slots podem ser:

- Filme
- Número de tickets
- Lugares na sala
- Data
- Hora
- ...

Este tipo de representação não é o ideal sem uma pessoa a controlar no mundo real, porque as pessoas costumam mudar de ideias e não responder como deve de ser aos slots.

## Graph

A stora não aprofundou, mas convem saber que existe ig

<br>

# Compositional Semantic Parsing

## Notação 1

- x.sem = semanticas do X
- replace(A.sem N B.sem) = replace as semanticas de no Nth argumento das semanticas de B.

### Example

<img src="Imagens/Aula10 Compositional Semantic Parsing Example.png">

## Notação 2

Funções de lambda:

- $\lambda y.\lambda x.f(x, y)@a$ = $\lambda x.f(x,a)$

Funções de lambda básicas com prioridade da esquerda para a direita. O valor substituto vem depois do "@"

Regra especifica: Quando temos uma expressão do tipo $(\lambda F.F(x))@(\lambda y.G(y))$ ao fazer a substituição de F não nos podemos esquecer de adicionar um $@x$ à frente do substituto. Isto quer dizer que ficava: $(\lambda y.G(y))@x = G(x)$

### Exemplo

<img src="Imagens/Aula10 Compositional Semantic Parsing Example2.png">

Para começar o semantic parsing da frase "A dog likes Alex" precisamos de fazer a análise sintática primeiro:

<img src="Imagens/Aula10 Compositional Semantic Parsing Example3.png">

Semanticas:

- a -> $\lambda P,\lambda Q. \exists xP(x) \wedge Q(x)$
- dog -> DOG
- likes -> $\lambda P,\lambda x.P(\lambda y.LIKES(x,y))$
- Alex -> ALEX
- NP (DET NN) -> $(\lambda P,\lambda Q. \exists xP(x) \wedge Q(x))$@DOG = $\lambda Q. \exists x DOG(x) \wedge Q(x)$
- NP (NNP) -> $\lambda P.P(ALEX)$
- VP -> $(\lambda P,\lambda x.P(\lambda y.LIKES(x,y)))@(\lambda P.P(ALEX))$ = $(\lambda x.(\lambda P.P(ALEX)@(\lambda y.LIKES(x,y))))$ = $\lambda x.(\lambda y.LIKES(x,y)@(ALEX))$ = $\lambda x.(\lambda y.LIKES(x,y)@(ALEX))$ = $\lambda x.LIKES(x,ALEX)$
- S -> $(\lambda Q. \exists x DOG(x) \wedge Q(x))@(\lambda y.LIKES(y,ALEX))$ = $(\lambda Q. \exists x DOG(x) \wedge (\lambda y.LIKES(y,ALEX))@(x))$ = $\lambda Q. \exists x DOG(x) \wedge LIKES(x,ALEX)$

<br>

## Semantic Parsing as Sequence Prediction

- PoS Tagging
- BIO Tagging

### BIO Tagging

Serve para reconhecer um conjunto de chunks

- B é para a palavra que se encontra no beginning da chunk.
- I para palavras que se encontram inside da chunk.
- O para palavras outside da chunk.

#### Exemplo

Para a frase "how much is the cheapest flight from Boston to New York?" vamos tentar identificar chunks para criar uma representação sintática frame.

| Palavra  | BIO                   |
| -------- | --------------------- |
| How      | O                     |
| much     | O                     |
| is       | O                     |
| the      | O                     |
| cheapest | B - Custo             |
| flight   | O                     |
| from     | O                     |
| Boston   | B - Cidade de partida |
| to       | O                     |
| New      | B - Cidade de chegada |
| York?    | I - Cidade de chegada |

<br>

# Semantic Relations

- Sinónimos: Palavras com o mesmo significado, independentemente do contexto.
  - How large/big is that plane? <- large e big são sinónimos.
  - She is my large/big sister. <- large e big já não são sinónimos aqui.
- Antónimos: Palavras com significados opostos.
  - Baixo vs. alto.
- Hipónimo: Palavras que são subclasses de outras.
  - cão vs. animal (cão é um hipónimo de animal).
- Hiperónimo: Palavras que são superclasses de outras.
  - cão vs. animal (animal é um hiperónimo de cão).
- Merónimo: Corresponde a uma parte do significado de outra palavra.
  - Roda vs. carro (roda é um merónimo de carro)
- Holónimo: Corresponde ao significado completo de outra palavra.
  - Roda vs. carro (carro é um holónimo de roda)
- Homónimos: Palavras que se pronunciam e se escrevem da mesma maneira, mas que podem ter significados não relacionados.
  - Banco (para sentar) vs. banco (armazém de dinheiro)
- Polisémicos: Palavras que podem ter vários significados relacionados.
  - dar (dar um livro, dar uma festa)

<br>

# Semantic Resources

- WordNet
- PropBank
- FrameNet
- VerbNet
- BabelNet
