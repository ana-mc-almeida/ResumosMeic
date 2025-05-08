# Exercício 1

| Boundary | On | Off |
| -------- | -- | --- |
| $a > 0 $ | $0$ | $1$ |
| $b > 2$ | $2$ | $3$ |
| $b \leq 200$ | $200$ | $201$ |
| $str != null$ | $null$ | "abcd" |
| $str.length \geq 2$ | "ab" | "1" |
| $vec.intValue() > 15$ | $15$ | $16$ |

Domain matrix:

| Variable | Boundary | Point | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 |
| -------- | -------- | ---- | - | - | - | - | - | - | - | - | - | -- | -- | -- |
| $a$ | $> 0$ | On | 0 |
| | |    

# Exercício 2

| Boundary | On | Off |
| -------- | -- | --- |
| $a > 0$ | $0$ | $1$ |
| $b \leq 1000$ | $1000$ | $1001$ |
| $a \leq b$ | $500$ with $a = 500$ | $499$ with $a = 500$ |
| $x \geq a$ | $500$ with $a = 500$ | $499$ with $a = 500$ |
| $x \leq b$ | $500$ with $b = 500$ | $501$ with $b = 500$ |

Domain Matrix:

<img src="Exercicio 2 domain matrix.png">

# Exercício 3

>Considere o método transfere(int quantia, Conta origem, Conta destino), que transfere uma quantia de dinheiro da conta origem para a conta destino. Este método funciona correctamente quando a quantia é maior do que 0, a conta origem está no estado abstracto aberto e a conta destino não está no estado abstracto inactivo.

> Utilizando a análise de domínio, desenvolva a matriz de domínio que representa os casos de teste que verificam a correcta concretização do domínio válido deste método. Considere ainda que os invariantes de estado da classe Conta são os seguintes:

> - estado abstracto aberto: saldo >= 0 && inactivo < 500;
> - estado abstracto descoberto: saldo < 0 && inactivo < 500; 
> - estado abstracto inactivo: inactivo >= 500. 

- quantia -> $a$
- origem -> $s$
- destino -> $d$

| Boundary | On | Off |
| -------- | -- | --- |
| $a > 0$ | $0$ | $1$ |
| $s$.isActive() | $(0, < 499)$ | $(-1, <499)$ |
| | $(>0, 499)$ | $(>0, 500)$ |
| $!d$.isInactive() | $(any, 500)$ | $(any, 499)$ |

<img src="Exercicio 3 domain matrix.png">