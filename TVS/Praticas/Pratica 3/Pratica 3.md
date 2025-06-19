# 2.

> Utilizando o padrão de testes mais apropriado, desenhe os casos de teste para o método responsável pela renovação do seguro automóvel de uma companhia de seguros que tem as seguintes regras:
>
> - caso não tenha havido acidentes no ano anterior e a idade do segurado seja inferior ou igual a 25, então deve-se aumentar o prémio do seguro em 50 euros. Se a idade do segurado for superior a 25, então deve-se aumentar o prémio do seguro apenas em 25 euros;
> - se tiver havido um acidente no ano anterior e a idade do segurado for inferior ou igual a 25, então deve-se aumentar o prémio do seguro em 100 euros e deve-se enviar um aviso ao segurado. Se a idade do segurado for superior a 25, então deve-se apenas aumentar o prémio do seguro em 50 euros;
> - se tiver havido entre dois a quatro acidentes no ano anterior deve-se enviar um aviso ao segurado independentemente da idade deste. Se a idade do segurado for inferior ou igual a 25, então deve-se aumentar o prémio do seguro em 400 euros. Se a idade do segurado for superior a 25, então deve-se aumentar o prémio do seguro em 200 euros;
>   se tiver havido mais do que quatro acidentes no ano anterior então não se deve renovar o seguro.

Desicion tree

<img src="3 - Ex 2 Decision model.jpg">

- $V_0 : accidents = 0 \wedge age \leq 25 : +50$
- $V_1 : accidents = 0 \wedge age > 25 : +25$
- $V_2 : accidents = 1 \wedge age \leq 25 : +100, warn$
- $V_3 : accidents = 1 \wedge age > 25 : +50$
- $V_4 : accidents \geq 2 \wedge accidents \leq 4 \wedge age \leq 25 : +400, warn$
- $V_5 : accidents \geq 2 \wedge accidents \leq 4 \wedge age > 25 : +200, warn$
- $V_6 : accidents > 4 : cancel$

<img src="3 - Ex 2 Domain model.png">

# 4.

## `add()` method

We will apply category-partition.

Functions:

- inserts `obj` in list if possible.
- if `obj` is null or list is full throw exception.
- if `obj` is added update size

Inputs and Outputs:

| Function                                          | Input       | Output    |
| ------------------------------------------------- | ----------- | --------- |
| inserts `obj` in list if possible.                | `obj`, list | list      |
| if `obj` is null or list is full throw exception. | `obj`, list | exception |
| if `obj` is added update size                     | `obj`, list | size      |

Input: `obj`, list<br>
Output: list, size, exception

Categories:

| Parameter | Category            |
| --------- | ------------------- |
| obj       | invalid             |
|           | `obj` $\in$ list    |
|           | `obj` $\notin$ list |
| list      | empty               |
|           | full                |
|           | holding             |

Choices:

| Parameter | Category            | Choices                                                                      |
| --------- | ------------------- | ---------------------------------------------------------------------------- |
| obj       | invalid             | null                                                                         |
|           | `obj` $\in$ list    | $o_1$                                                                        |
|           | `obj` $\notin$ list | $o_\times$                                                                   |
| list      | empty               | {}                                                                           |
|           | full                | {$o_1, \dots, o_{max}$}                                                      |
|           | holding             | {$o_1$}, {$o_1, \dots, o_{max - 1}$}, {$o_1, \dots, o_m$}, $1 < m < max - 1$ |

Constraints:

We cannot have the choice $o_1$ from category `obj` $\in$ list with choice {} from category empty.

Test cases:

| TC  | obj input  | list input                  | list output                       | size output | exception output |
| --- | ---------- | --------------------------- | --------------------------------- | ----------- | ---------------- | --- |
| 1   | null       | {$o_1$}                     | same                              | 1           | throw            |
| 2   | $o_1$      | {$o_1, \dots, o_{max}$}     | same                              | max         | throw            |
| 3   | $o_1$      | {$o_1$}                     | {$o_1, o_2$}                      | 2           | -                |
| 4   | $o_1$      | {$o_1, \dots, o_{max - 1}$} | {$o_1, \dots, o_{max}$}           | max         |
| 5   | $o_1$      | {$o_1, \dots, o_4$}         | {$o_1, \dots, o_4, o_2$}          | 5           | -                | -   |
| 6   | $o_\times$ | {}                          | {$o_\times$}                      | 1           | -                |
| 7   | $o_\times$ | {$o_1$}                     | {$o_1, o_\times$}                 | 2           | -                |
| 8   | $o_\times$ | {$o_1, \dots, o_{max - 1}$} | {$o_1, \dots, o_{max}, o_\times$} | max         | -                |
| 9   | $o_\times$ | {$o_1, \dots, o_{max - 1}$} | same                              | max         | throw            |

## `contains()` method

Functions:
?

Input: `obj`, list<br>
Output: return value

Categories:

| Parameter | Category            |
| --------- | ------------------- |
| list      | empty               |
|           | full                |
|           | holding             |
| `obj`     | special case        |
|           | `obj` $\in$ list    |
|           | `obj` $\notin$ list |

Choices:

| Parameter | Category            | Choices                                                                     |
| --------- | ------------------- | --------------------------------------------------------------------------- |
| list      | empty               | {}                                                                          |
|           | full                | {$o_1, \dots, o_{max}$}                                                     |
|           | holding             | {$o_1$}, {$o_1, \dots, o_{max - 1}$}, {$o_1, \dots, o_m$} $1 < m < max - 1$ |
| `obj`     | special case        | null                                                                        |
|           | `obj` $\in$ list    | `obj` is first in list                                                      |
|           |                     | `obj` is in the middle of the list                                          |
|           |                     | `obj` is last in list                                                       |
|           | `obj` $\notin$ list | $o_\times$                                                                  |

Constraints:

- if `obj == null` the behavior of `contains()` is always the same.
- category empty from list is impossible with category `obj` $\in$ list from `obj`.
- for category holing, if there is only one element, the category `obj` $\in$ list has only one value.

Test cases:

The table would have 18 test cases.
