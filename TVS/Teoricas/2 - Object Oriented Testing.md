

<!-- toc -->

- [Encapsulation](#encapsulation)
  * [Example](#example)
- [Inheritance](#inheritance)
- [Polymorphism](#polymorphism)

<!-- tocstop -->

## Encapsulation

Because of the encapsulation as access control mechanism we may encounter obstacles when we need to check a certain variable inside an object.

### Example

The `add()` method from our `Set` class should throw an exception when adding an element that was already in the set.

Test case:

```
try {
    set.add(1);
    set.add(1);
    fail(Error. Same number added twice");
} catch (DuplicateException e) {
    // Empty
}
```

This test doesn't guarantee that the element was not added to the set. To test it we would need to think out of the box:

```
try {
    set.add(1);
    set.add(1);
    fail("Error. Same number added twice");
} catch (DuplicateException de) {
    set.remove(1);
    assertTrue(!set.contains(1));
}
```

## Inheritance

Frequent problems:

- How to test abstract classes if we can't initialize them?
- It is really easy to forget to execute initialization code of superclass.
- It is easy to forget obscure methods that should be overritten along side other methods. For example, Java's `equals()` and `hashcode()` methods. If one of the methods is overritten the other should be too.
- Multiple inheritance: a class inherits directly from 2 or more classes which may contain features with the same name.

## Polymorphism

Frequent problems:

- Overriding a method that is incompatible with the original one.
- Messages can be bound to the wrong class for untyped languages.
