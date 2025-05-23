# 1.

> Considere o seguinte método:

> ```java
> void compute(int x, int y) {
>    if (y == 0)
>        System.out.println ("y is zero");
>    else if (x == 0)
>        System.out.println ("x is zero");
>    else {
>        for (int i = 1; i <= x; i++) {
>            if (i % y == 0)
>                System.out.println (i);
>        }
>    }
>}
>```
## 1.

> Desenho o grafo de controlo de fluxo do método compute.

<img src="1 - CFG.png">

## 2.

> Identifique o conjunto mínimo de caminhos que atinge 100% de cobertura de instrução.

3 Caminhos:
- ABJ
- ACDJ
- ACEFGHIFJ

## 3.

> Identifique o conjunto mínimo de caminhos que atinge 100% de cobertura de ramo.

3 Caminhos:
- ABJ
- ACDJ
- ACEFGHIFGIFJ

## 4.

> Identifique o conjunto mínimo de caminhos que atinge 100% de cobertura de caminho.

É impossível testar todos os caminhos. Como temos um loop o número de caminhos seria infinito.

## 5.

> Identifique o conjunto mínimo de caminhos que atinge 100% de cobertura de basis-path.

Queremos encontrar os $C$ caminhos independentes. $C = e - n + 2$, onde $e$ são os edges e $n$ os nodes.

Neste caso vamos ter $C = 13 - 10 + 2 = 5$ caminhos:
- ABJ
- ACDJ
- ACEFJ
- ACEFGHIFJ
- ACEFGIFJ

# 5.

> Considere o seguinte método em Java:

> ```
> void doSomething(int x, double vec[]) {
>    int y = 0;
>    if (x > 0) y = 1;
>    if (x > 100 && y != 0)
>        System.out.println("less than 100");
>    else
>        y = vec[0];
>    switch (y) {
>        case 1:
>            f(vec);
>            break;
>        case 2:
>            g(vec);
>            break;
>    }
>    ff(vec);
> }
> ```

## 1.

> Desenhe o grafo de controlo de fluxo do método doSomething.

<img src="5 - CFG.png">

Nenhuma das coberturas que vamos usar faz distinção entre predicados compostos e simples por isso consideramos `x > 100 && y != 0` como uma única instrução.

## 2.

> Identifique o conjunto mínimo de caminhos que atinge 100% de cobertura de instrução.

2 Caminhos:
- ABCEFHI
- ABCDFGI

## 3.

> Identifique o conjunto mínimo de caminhos que atinge 100% de cobertura de ramo.

3 Caminhos:
- ABCEFHI
- ABCDFGI
- ACEFI

## 4.

> Identifique o conjunto mínimo de caminhos que atinge 100% de cobertura de caminho.

12 Caminhos:
- ACEFI
- ACEFGI
- ACEFHI
- ACDFI
- ACDFGI
- ACDFHI
- ABCEFI
- ABCEFGI
- ABCEFHI
- ABCDFI
- ABCDFGI
- ABCDFHI

## 5. (Extra)

> Identifique o conjunto mínimo de caminhos que atinge 100% de cobertura de caminho base.

$C = 12 - 9 + 2 = 5$ caminhos:
- ACEFI
- ABCEFI
- ACDFI
- ACEFHI
- ACEFGI

# 6.

> Considere o seguinte método:

```java
void contains(int x, int a, int b) {
    if(a <= b) {
        if(a <= x && x <= b)
            System.outprint.ln("x is an interior point");
        if(x <= a || x >= b)
            System.outprint.ln("x is outside the interval");
    } else
        System.outprint.ln("the interval is empty");
}
```

## 1.

> Desenho o grafo de controlo de fluxo do método `contains`.

<img src="6 - Segments.png">

<img src="6 - CFG.png">

### Extra

Se quissemos fazer distinção entre predicados simples e compostos ficariamos com:

<img src="6 - extra segments.png">

<img src="6 - extra CFG.png">

Para voltarmos à cobertura que não distingue entre predicados simples e compostos podemos simplesmente juntar os nodes que estão agrupados com o tracejado azul.

## 2.

> Identifique o conjunto mínimo de caminhos que atinge 100% de cobertura de instrução.

2 Caminhos:
- AFG
- ABCDEG

## 3.

> Identifique o conjunto mínimo de caminhos que atinge 100% de cobertura de ramo;

3 Caminhos:
- AFG
- ABCDEG
- ABDG

## 4.

> Identifique o conjunto mínimo de caminhos que atinge 100% de cobertura de caminho.

5 Caminhos:

- AFG
- ABCDEG
- ABCDG
- ABDEG
- ABDG

# 7.

> Determine a bateria de testes mínima para atingir 100% MC/DC nos seguintes casos:

## 1.

> if ((A || B) && C) ...

| | | 0 | | | 1 | |
| - | - | - | - | - | - | - |
| | A | B | C | A | B | C |
| **focus A** | 0 | 0 | 1 | 1 | 0 | 1 |
| **focus B** | 0 | 0 | 1 | 0 | 1 | 1 |
| **focus C** | 1 | 0 | 0 | 1 | 0 | 1 |

Test cases: { (0,0,1), (1,0,1), (0,1,1), (1,0,0) }

No **focus C** escolhemos A = 1 e B = 0, porque era uma combinação que já existia, e assim não temos de repetir casos de teste. Se escolhessemos A = 1 e B = 1, iriamos ter 5 casos de teste em vez de apenas 4.