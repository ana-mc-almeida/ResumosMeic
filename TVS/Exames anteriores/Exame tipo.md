# Group I (5.0 points) - Code coverage, including coverage models based on control flow and data flow.

## a) 

>Draw the control flow graph for the following method, clearly identifying its several code segments and the several du-pairs and corresponding du-paths for the pow variable. It may be useful to show in each segment if the variable was defined and/or used. To simplify the analysis, you can consider that each segment should contain a single statement.

> ```
> int compute(int x, int y) {
>    int pow = y;
>    float z = 1.0;
>
>    if (x == 0 && y == 0)
>        z = 0.0;
>     else {
>        if (y < 0)
>            pow = -y;
>
>        while (pow != 0) {
>            z = z * x;
>            pow--;
>        }
>
>        if (y < 0)
>            z = 1 / z;
>    }
>    return z;
>}
>```

<img src="exame tipo I a.png">

| du-pair | dc-path |
| ------- | ------- |
| (2, <8, 9>) | <2, 3, 4, 6, 8, 9> |
| (2, <8, 11>) | <2, 3, 4, 6, 8, 11> |
| (7, <8, 9>) | <7, 8, 9> |
| (7, <8, 11>) | <7, 8, 11> |
| (10, <8, 9>) | <10, 8, 9> |
| (10, <8, 11>) | <10, 8, 11> |

## b)

> Show a minimal set of paths that achieves statement coverage for this method.

- <1, 2, 3, 4, 5, 13>
- <1, 2, 3, 4, 6, 7, 8, 9, 10, 8, 11, 12, 13>

## c)

> Show a minimal set of paths that achieves path coverage for this method.

- <1, 2, 3, 4, 5, 13>
- <1, 2, 3, 4, 6, 8, 11, 13>
- <1, 2, 3, 4, 6, 7, 8, 9, 10, 8, 11, 12, 13>

## d) 

> Show a minimal set of paths that achieves All-Defs coverage for this method, concerning just variable pow.

- <1, 2, 3. 4. 6. 8, 9, 10, 11, 13>
- <1, 2, 3, 4, 6, 7, 8, 11, 13>

## e) 

> Show a minimal set of paths that achieves All-Uses coverage for this method, concerning just variable pow.

- <1, 2, 3, 4, 6, 8, 11, 13>
- <1, 2, 3, 4, 6, 8, 9, 10, 8, 9, 10, 11, 13>
- <1, 2, 3, 4, 6, 7, 8, 9, 10, 8, 11, 13>
- <1, 2, 3, 4, 6, 7, 8, 11, 13>

# Grupo II (4,5 valores) Desenho de uma bateria de testes ao nível de método ou de classe

## 1.

> Considere a seguinte classe SpreadSheet

> ```
> public class SpreadSheet {
>   public SpreadSheet(int rows, int columns) throws  InvalidOperation { /*... */}
>   public void protect(int x, int y, boolean readOnly) throws  InvalidOperation { /* ... */}
>   public void changeNumberOfRows(int rows) throws  InvalidOperation { /* ... */}
>   public void set(int x, int y, Content c) throws InvalidOperation { /* ... */}
>   public Content get(int x, int y) throws InvalidOperation { /* ... */}
>   public int getNumberOfColumns() { /* ... */}
>   public int getNumberOfRows() { /* ... */}
> }
> ```

> que representa a funcionalidade de uma folha de cálculo desenvolvida para ser aplicada num dado contexto. Nesta folha de cálculo, o conteúdo de cada célula preenchida é representado por uma instância da classe Content. Existem diferentes tipos de Content, mas todos eles representam uma entidade que tem um dado valor numérico. Pode-se proteger uma dada célula da folha de cálculo, via método protect, por forma a que não se possa alterar o seu conteúdo, sendo então apenas possível ver o seu conteúdo. A correcta concretização desta classe deve assegurar que uma folha de cálculo tem pelo menos uma célula, não tem mais do que 100 linhas nem do que 100 colunas, o número de células protegidas é inferior a 10% do número total de células da folha de cálculo e todas as células preenchidas da folha têm um valor maior ou igual a 0.

> Desenhe a bateria de testes que testa esta classe utilizando o padrão de teste apropriado, descrevendo os vários passos do padrão aplicado. Concretize dois dos casos de teste desta bateria de teste utilizando a framework TestNG. Para a concretização dos testes TestNG, pode utilizar a classe IntValue, um dos tipos existentes de Content, que representa um número inteiro. Esta classe tem o construtor, que recebe o número inteiro a representar pela instância a criar, e o método getValue que devolve esse número inteiro. 

Class invariant: $\#\text{cells} \geq 1, \#\text{lines} \leq 100, \#\text{columns} \leq 100, \#\text{protected} < \#\text{cells} / 10, \text{Content} \geq 0$

<img src="Exame tipo II 1.png">

```java
@Test
public class SpeadSheetTest {

    public void givenValidAmountOfCellsAccept() {
        SpreadSheet ss = new SpreadSheet(1, 1);
        ss.set(0, 0, new IntValue(1));
        
        assertEquals(ss.getNumberOfColumns(), 1);
        assertEquals(ss.getNumberOfRows(), 1);
        assertEquals(ss.get(0, 0), new IntValue(1));
    }

    public void givenInvalidAmountOfCellsReject() {
        assertThrows(InvalidOperation.class, () -> new SpreadSheet(0, 0));
    }

}
```

## 2.

> Considere o seguinte método

> ```
> public void set(int x, int y, Content c) throws InvalidOperation
> ```

> que altera o conteúdo da célula desprotegida presente na linha x e na coluna y da folha de cálculo e respeita as condições indicadas na pergunta anterior.

> Utilizando o padrão de testes mais apropriado, e descrevendo os vários passos do padrão aplicado, desenhe a bateria de testes que verifica o comportamento correcto deste método.

> Caso aplique o padrão Combinational Function Test ou o padrão Category Partition Test nesta questão, então no último passo de cada um dos padrões apenas é necessário desenhar as matrizes de domínio para as duas primeiras variantes, no caso do Combinational Function Test, ou indicar os primeiros 10 casos de teste resultantes da aplicação do padrão Category Partition Test.

Using Category Partition:

Functions: changes the content from row $x$ and column $y$ to Content.

Input: row, col, Content<br>
Output: new content, exception

| Parameter | Category | Choice |
| --------- | -------- | ------ |
| row | Valid | $m \in [0, 99]$
| | Invalid | $m \in ]-\infin, 0[ \cup ] 99, \infin[$
| column | Valid | $m \in [0, 99]$
| | Invalid | $m \in ]-\infin, 0[ \cup ] 99, \infin[$
| Content | Valid | $m >= 0$
| | Invalid | $m < 0$, "hello"

Constraints: None

| Test case | Row | Col | Content | Exception |
| --------- | --- | --- | ------- | --------- |
| 1 | 10 | 15 | 3 | - |
| 2 | 10 | 15 | -1 | yes |
| 3 | 10 | 15 | "hi" | yes |
| 4 | 10 | -1 | 3 | yes |
| 5 | 10 | -1 | -1 | yes |
| 6 | 10 | -1 | "hi" | yes |
| 7 | -1 | 15 | 3 | yes |
| 8 | -1 | 15 | -1 | yes |
| 9 | -1 | 15 | "hi" | yes |
| 10 | -1 | -1 | 3 | yes |

# Grupo IV (6 valores) Aplicação de TDD + Mocking

> Pretende-se desenvolver a funcionalidade representada pela seguinte user story:

> Como utilizador, quero ser capaz de criar uma conta bancária. Uma conta bancária deve conter um identificador (número inteiro), estado da conta (activo/inactivo) e um saldo.Por omissão, quando é criado uma conta, o seu identificador é 0, está activa e o saldo é 0.0. Deve ser possível criar contas com um dado identificador e saldo.
> É possível congelar a conta, tornando-a inactiva. Posteriormente, é possível torná-la activa outra vez.
> Todas as operações realizadas sobre uma conta devem guardadas num ficheiro de log por forma a que as operações realizadas sobre as contas bancárias possam ser auditadas. A entidade Log será responsável por escrever no ficheiro de log. A instância de Log a utilizar por cada conta bancária é indicada no construtor. Esta entidade terá apenas um método: void writeTo(int accountId, OperationType type, float value, boolean sucess). Considere que OperationType é um enumerado com os seguintes valores: ADD, WITHDRAWAL, CREATION, FREEZE, ACTIVATE.
> Implement three test cases that exercise the Bank class. In the implementation of these test cases, the class Bank must be exercised in isolation from the class Log and you can use the TestNG and mockito frameworks. You can consider that all the exception classes and the OperationType enum are already implemented.

```java
@Test
public class BankTest {

    private Log log;

    @BeforeMethod
    public void setUp() {
        log = mock(Log.class);
    }
    
    public void whenConstructThenAccoutnIsActiveAndHasId0AndBalance0() {
        Account acc = new Account(log);
        
        assertTrue(acc.isActive());
        assertEquals(acc.getId(), 0);
        assertEquals(acc.getBalance(), 0);
    }

    public void givenValidValuesThenLogIsUpdated() {
        Account acc = new Account(log);

        assertEquals(acc.getBalance(), 0.0);
        assertEquals(acc.getId(), 0);
        verify(log).writeTo(0, OperationType.CREATION, 0, true);
    }

    public void givenAnIdAndBalanceWhenCreateAccountThenLogIsUpdated() {
        Account acc = new Account(1.0, 5, log);

        assertEquals(acc.getBalance(), 1.0);
        assertEquals(acc.getId(), 5);
        verify(log).writeTo(5, OperationType.CREATION, 1.0, true);
    }

}
```