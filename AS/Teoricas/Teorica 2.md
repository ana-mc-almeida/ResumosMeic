# Perspetivas

Há duas perspetivas a ter quando se olha para software: O código e o serviço prestado.

## Modules

Modulos fazem parte dos software elements, e servem para encapsular interfaces. Desta forma podemos modificar a implementação sem modificar a interface, sem propagar as mudanças.

Uma das qualidades relacionadas com os Modules é a Modifiability.

Os Modules podem relacionar-se uns com os outros através de invocações ou dependências. Se um Modulo mudar a sua interface, um outro Modulo que o utiliza precisa de mudar a sua implementação.

Para projetos grandes onde há uma pessoa ou equipa a trabalhar em cada Module, então aloca-se esse Modulo para essa pessoa, ou equipa, se focar nele, dessa forma conseguimos estimar o tempo que vai demorar a estar pronto. Outro tipo de allocation é a file allocation que nos ajuda a saber exatamente em que ficheiros está o Module que estamos à procura.

## Components and Connectors

Components fazem parte do runtime de um programa e têm um life cycle porque podem ser criados e podem morrer mais tarde. Os connectors são protocolos de comunicação entre os components. Exemplos de components podem ser um cliente e o servidor. Um exemplo de connector pode ser o protocolo entre o servidor e cliente, por exemplo UDP.

Algumas das qualidades relacionadas com os Components and Connectors são Reliability (usando TCP), Availability, Performance (por exemplo, usando UDP), Security (por exemplo, usando HTTPS).

Tal como os Modules são alocados em certos ficheiros, os Components and Connectors são alocados para o hardware. Dessa forma conseguimos saber o custo do sistema.
