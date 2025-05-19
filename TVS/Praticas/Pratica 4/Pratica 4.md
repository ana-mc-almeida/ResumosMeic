# 1.

> $-10 < x \leq 40 \wedge y \geq 40 \wedge z \leq 50$

## a.

> Desenhe os casos de teste para testar esta classe ao nível da classe.

Domain testing:

<img src="1 - a Domain testing.png">

## b.

We will use define-use. Since we are going to make one test case for each method, we need to be changing the define method that we are using.

```java
@Test
public class TestExample() {
    
    @Test(expecteExceptions = InvalidOperationException.class)
    public void testsInvalidInputForXThenThrowException() {
        new Example(-10, 41, -5);
    }

    // The same as before but with a different approach
    @Test
    public void testInvalidInputForXForLowerBoundary() {
        Example e = new Example(-10, 41, -5);
        assertThrows(InvalidOperationException.class, () -> e.set(-10));
        assertEquals(e.getX(), 5);
        assertEquals(e.getY(), 41);
        assertEquals(e.getZ(), -5);
        assertEquals(e.max(), 41);
    }

    @Test
    public void testInvalidInputForXForLowerBoundary() {
        Example e = new Example(-8, 50, -10); // Note that this define is different from the previous one.
        e.decX();
        assertEquals(e.max(), 50);
        assertEquals(e.getX(), -9);
        assertEquals(e.getY(), 50);
        assertEquals(e.getZ(), -10);
    }

    @Test
    public void testValidInputForXForGreaterBoundary() {
        Example e = new Example(39, 60, 0); // Note that this define is different from the previous one.
        e.incX();
        // The order of the use methods also changes
        assertEquals(e.getY(), 60);
        assertEquals(e.max(), 50);
        assertEquals(e.getZ(), 0);
        assertEquals(e.getX(), 40);
    }

    ...

}
```

# 2.

## Teste ao nível da classe

> A classe Prize descreve um prémio e tem como atributos uma descrição, um identificador, e um valor monetário. Do ponto de vista da classe PrizeDB, existem as seguintes restrições:

> - O número de prémios que são mantidos em qualquer momento é menor ou igual a 10;
> - A descrição de qualquer prémio tem que ser maior ou igual do que 10 caracteres e menor do que 100;
> - Não há dois prémios com o mesmo identificador

Primeiro temos de definir a classe invariant: $\#prizes \leq 10 \wedge \forall p \in prizes : 10 \leq \#p.description < 100 \wedge \forall p_1, p_2 \in prizes : p_1.id == p_2.id \Rightarrow p_1 == p_2$

Domain testing:

<img src="2 - Domain testing.png">

We could skip test 7 because we are alreay testing condition3 == True for all its in points.

```java
@Test
public class ExampleTest {
    
    public void testValidValueForPDescription() {
        Prize p = new Prize(1, "1234567890");
        PrizeDB pdb = new PrizeDB(p);

        assertTrue(pdb.hasId(1));
        assertEquals(pdb.find(1), p);
        assertEquals(pdb.getNumberOfPrizes(), 1);
    }
    
    public void testValidValueForCondition3() {
        Prize p1 = new Prize(1, "1234567890123");
        PrizeDB pdb = new PrizeDB(p1);
        Prize p2 = new Prize(2, "12345678901234567890");
        db.add(p2);

        assertEquals(pdb.find(1), p1);
        assertFails(pdb.hasId(3));
        assertEquals(pdb.getNumberOfPrizes(), 2);
    }

} 
```

## Teste ao nível do metodo `add()`

Functions for `add(p)`:
- add `p` if possible.
- throw exception if `p == null`, `p.id()` is not unique, PrizeDB is full, `p.description()` is valid.
- increase size if `p` is added.

In and Out values:

| Function | In | Out |
| -------- | -- | --- |
| add `p` if possible | `p`, set | set |
| throw exception if `p == null`, `p.id()` is not unique, PrizeDB is full | `p`, set, `p.description()` | exception |
| increase size if `p` is added | `p`, set | size |

Categories:

| | Caterogry |
| - | ------ |
| set | empty |
| | holding |
| | full |
| `p` | invalid
| | valid |

Choices:

| | Caterogry | Choices |
| - | ------ | ------ |
| set | empty | $\{\}$ |
| | holding | $\{p_1\}, \{p_1, \dots, p_9\}, \{p_1, \dots, p_n\}, n \in [1, 9]$ |
| | full | $\{p_1, \dots, p_{10}\}$
| `p` | invalid | null
| | not unique | $p_1$ |
| | unique | $p_\times$ |
| `p.description()` | invalid | $9, 100$ |
| | valid | $10, 50, 99$ |