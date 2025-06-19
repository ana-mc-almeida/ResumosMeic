# 1.

> https://fenix.tecnico.ulisboa.pt/disciplinas/TVS36/2024-2025/2-semestre/exercicio-1

## `public void addBook(Book b) throws CannotProcessRequestException`

Using category partition

In: book, service

| Parameter | Category    | Choice |
| --------- | ----------- | ------ |
| service   | valid       | aBook  |
|           | invalid     | null   |
| service   | available   | avai   |
|           | unavailable | unavai |

Concretizei alguns testes na class [BookstoreTest.java](BookstoreTest.java)

## `List<Book> search(String author, String title, BigDecimal price) throws CannotProcessRequestException`

Using category partition

In: author, title, price

| Parameter | Category   | Choice |
| --------- | ---------- | ------ |
| author    | valid      |        |
| title     | valid      |        |
| price     | null       |        |
|           | greater    |        |
|           | smaller    |        |
|           | in between |        |
| books     | empty      |        |
|           | holding    |        |

It having an empty books list will give the same result for any category of price

Concretizei alguns testes na class [BookstoreTest.java](BookstoreTest.java)
