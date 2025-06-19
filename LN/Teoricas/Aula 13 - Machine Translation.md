### Collocations

Collocations são uma combinação de palavras de uma linguagem que aparecem com demasiada frequência para aparecerem por sorte. Usar uma combinação diferente soa estranho.

#### Exemplo

- "I missed the flight" vs. "perdi o avião" (não dizemos em inglês "I lost the flight).
- "The employee cleared the table" vs. "O empregado levantou a mesa".

<br>

# Machine Translation

Machine Translation é a utilização de computadores para automatizar processos de tradução de uma língua para outra. Também inclui speech to speech.

<img src="Imagens/Aula13 Speech to Speech MT.png">

## Approaches

### Classic

Tinham algumas regras de gramática e 250 lexical items (palavras).

<img src="Imagens/Aula13 Classic Approach MT.png">

### Statistical-based

Statistical Machine Translation (SMT) são modelos probabilisticos que têm em conta _faithfulness_ e _fluency_. Utilizam estas duas categorias para escolher a tradução mais provável.

T' = argmax $_T$ P(T, S) \* P(T), sendo S a source language.

| S = Estou cansado | Faithfulness | Fluency |
| ----------------- | ------------ | ------- |
| I'm exhausted     | 3            | 5       |
| Tired me          | 5            | 2       |
| I love cookies    | 0            | 5       |

Para este metodo são criadas várias hipóteses de tradução e associa-se uma probabilidade a cada hipótese. A mais provável é escolhida.

### Deep Learning-based

Modelos de linguagem multilingual são treinados em data de várias linguagens ao mesmo tempo. Desta forma conseguem captar as parecenças e diferenças entre as várias linguagens.

O modelo aprende um vocabulário partilhado e estratégia de tokenização entre as linguagens. Depois usa informação de texto monolingual de cada linguagem para criar sub-palavras ou tokens para conseguir tratar das diferenças entre as linguagens.

A monolingual data é usada principalmnete nas fases iniciais do treino para aprender as especificidades da linguagem. A parallel data é usada no fine-tunning.

## Shared Tasks

- General machine translation
- Biomedical translation
- Multimodal translation
- Sign language translation

## Datasets

- Datasets from WMT shared tasks
- Europarl
- Europarl-ST
- Open subtitles
- MLQA (Multilingual QA)

## Metrics

- BLUE
- METEOR
- COMET
