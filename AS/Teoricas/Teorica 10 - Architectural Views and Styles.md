

<!-- toc -->

- [Architecture Views](#architecture-views)
- [Architecture Styles](#architecture-styles)
- [Module Representation](#module-representation)

<!-- tocstop -->

# Architecture Views

Uma view é provavelmente a forma de documentação mais importante para uma arquitetura de software.

Para documentar uma arquitetura é preciso documentar as views relevantes e depois adicionar documentação que se aplica a mais do que uma view. Este conceito de views mais relevantes depende do nosso objetivo.

Views diferentes representam diferentes quality attributes do sistema. Cada view foca-se em aspetos especificos do sistema enquanto ignora outros aspetos.

A documentação de uma view contém:

- Representação gráfica dos elementos e das relações da view.
- Uma descrição elementos e das suas propriedades.
- Especificação das interfaces e comportamentos dos elementos.
- Guia de como funcionam mecanismos dentro da arquitetura.
- Informação sobre o design.

A documentação que se aplica a todas as views inclui:

- Descrição de todas as packages.
- Informação de como as views se relacionam.
- Constraints sobre a arquitetura.
- Managements information para manter a package inteira.

# Architecture Styles

_Não percebi a diferença entre styles e views mas pronto_.

Há 3 tipos de estilos:

- Module Styles
- Componenet-and-connector Styles
- Allocation Styles

Style guide:

1. Overview: Explica porque é que o style é útil e para que tipo de sistemas serve.
2. Element types, relation types, properties: define os elementos que vão ser usados pelo style, como eles funcionam juntos e as suas propriedades.
3. Constraints: Regras para usar elementos e relações entre eles para formar uma instância do style válida.
4. What it's for: Explica o tipo de tarefas das views do style.
5. Notations: Informação extra ig.
6. Relation to other styles: Mostra como views deste style se podem relacionar com views de outros styles.
7. Exemplos.

# Module Representation

- Nome
- Resposabilidade
- Interface / Visibilidade
- Implementação / Especicação

Relações entre modules:

- Depends on
- Is part of
- Is a
