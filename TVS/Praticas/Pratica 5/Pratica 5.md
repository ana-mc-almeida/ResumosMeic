# 5.

> Considere a class IceCreamShop que representa o comportamento de uma gelataria que vende copos e cones, que podem ter vários sabores e toppings:
> ```
> public class IceCreamShop {
>    public void newCone() { ... }
>    public void newCup() { ... }
>    public void addFlavor() { ... }
>    public void cancelOrder() { ... }
>    public void addTopping() { ... }
>    public int getCost() { ... }
>    public void pay() { ... }
>}
>```

> A gelataria tem o seguinte comportamento:
> - Inicialmente, a loja está à espera de um pedido (newCone ou newCup);
> - Após um cliente ter feito a sua escolha, este pode adicionar sabores (addFlavor), cancelar o pedido se ainda não tiver sido adicionado nenhum sabor (cancelOrder), e adicionar um ou mais toppings se já tiver sido adicionado pelo menos um sabor (addTopping);
> - Após ter sido adicionado um topping, já não se podem adicionar mais sabores;
> - Quando se cancela ou se paga o pedido actual, fica-se à espera do próximo pedido;
> -Em qualquer altura, desde que já tenha sido pedido pelo menos um sabor, é possível efectuar o pagamento através do método pay.
> - Em qualquer altura, desde que o pedido não tenha sido cancelado, é possível perguntar o custo do pedido (getCost).

> Aplicando o padrão de testes mais apropriado, e descrevendo os vários passos da sua aplicação, desenhe os casos de teste que permitem verificar o comportamento desta classe. 

We will apply modal class test.

Develop state model:

<img src="5 - state model.png">

Full expansion of conditional transition variants:

| State | Event | Added? |
| ----- | ----- | ------ |
| Process | [#flavors = 0] cancel |
| Process | [#flavors > 0] cancel / error | yes |
| Process | [#flavors = 0] pay |
| Process | [#flavors >= 1] pay |
| Process | [#flavors = 0] pay / error | yes |
| Process | [#flavors >= 1] addTopping
| Process | [#flavors = 0] addTopping / error | yes |

Update state model:

<img src="5 - updated state model.png">

Generating the transition tree:

<img src="5 - transition tree.png">

Conformance test suite:

| TC | Level 1 | Level 2 | Level 3 | Level 4 | Expected state | expected exception |
| -- | ------- | ------- | ------- | ------- | -------------- | --------- |
| 1 | new | - | - | - | Waiting Order | - |
| 2 | new | newCup | - | - | Process | - |
| 3 | new | newCone | - | - | Process | - |
| 4 | new | newCup | [#flavors >= 1] pay | - | Waiting Order | - |
| 5 | new | newCup | [#flavors = 0] pay | - | Process | throw |
| 6 | new | newCup | addFlavor | - | Process | - |
| 7 | new | newCup | [#flavors = 0] addTopping | - | Process | throw |
| 8 | new | newCup | [#flavors >= 0] addTopping | - | AddTopping | - |
| 9 | new | newCup | getCost | - | Process | - |
| 10 | new | newCup | [#flavors = 0] cancel | - | Waiting Order | - |
| 11 | new | newCup | [#flavors >= 1] cancel | - | Process | throw |
| 12 | new | newCup | [#flavors >= 0] addTopping | addTopping | AddTopping | - |
| 13 | new | newCup | [#flavors >= 0] addTopping | getCost | AddTopping | - |
| 14 | new | newCup | [#flavors >= 0] addTopping | pay | Waiting Order | - |

Develop test data for each path using Invariant Boundaries:

| Cancel in Process | On | Off |
| ----------------- | -- | --- |
| [#flavors >= 1] | $\underline{1}$ | 0 |
| [#flavors = 0] | $\underline{0}$ | 1, ~~-1~~ |

| addTopping in Process | On | Off |
| ----------------- | -- | --- |
| [#flavors >= 1] | $\underline{1}$ | 0 |
| [#flavors = 0] | $\underline{0}$ | 1, ~~-1~~ |

| pay in Process | On | Off |
| ----------------- | -- | --- |
| [#flavors >= 1] | $\underline{0}$ | 1, ~~-1~~ |
| [#flavors = 0] | $\underline{1}$ | 0 |

-1 is impossible, and we will only have the underlined tests. The others are duplicates.

Develop Sneak Path Test Suite:

| State | newCup | newCone | pay | cancel | getCost | addFlavor | addTopping |
| ----- | ------ | ------- | --- | ------ | ------- | --------- | ---------- |
| Waiting Order | Valid | Valid | PSP | PSP | PSP | PSP | PSP |
| Process | PSP | PSP | Valid | Valid | Valid | Valid | Valid |
| AddTopping | PSP | PSP | Valid | PSP | Validd | PSP | Valid |

PSP = Possible Sneak Path

<img src="5 - updated transition tree.png">

PSP Test Suite:

| TC | Level 1 | Level 2 | Level 3 | Level 4 | Expected state | expected exception |
| -- | ------- | ------- | ------- | ------- | -------------- | -------------- |
| 15 | new | pay | - | - | - | Waiting Order | throw |
| 16 | new | cancel | - | - | - | Waiting Order | throw |
| 17 | new | getCost | - | - | - | Waiting Order | throw |
| 18 | new | addFlavor | - | - | - | Waiting Order | throw |
| 19 | new | addTopping | - | - | - | Waiting Order | throw |
| 20 | new | newCup | newCup | - | - | Process | throw |
| 21 | new | newCup | newCone | - | - | Process | throw |
| 22 | new | newCup | [#flavors >= 1] addTopping | newCup | AddTopping | throw |
| 23 | new | newCup | [#flavors >= 1] addTopping | newCone | AddTopping | throw |
| 24 | new | newCup | [#flavors >= 1] addTopping | cancel | AddTopping | throw |
| 25 | new | newCup | [#flavors >= 1] addTopping | addFlavor | AddTopping | throw |