# Functional Programming

Functional programming favours:
- functions over objects
- transformations (to create something new from something existing) over modifications
- immutable over mutable data

OOP is popular when:
- dealing with mutable data (objects with state)
- reading & writing to a relational database
- there is a lot of state and state changes

Functional Programming is popular when:
- the data must not change
- new info is to be extracted from existing info

Functional Terms:
- pure function: a function that produces output 'y' when given input 'x' (no state; no side-effects)
- higher order function: a function that accepts a function as input and/or returns a function
- immutability: defines an object that cannot be modified

NB: immutability is NOT the same as constancy

```java
final Vehicle vehicle = new Vehicle();
```

The variable cannot be reassigned BUT the object referenced can be modified (it is not immutable).