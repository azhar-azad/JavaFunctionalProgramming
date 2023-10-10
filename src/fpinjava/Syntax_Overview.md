# Syntax Overview
This is a quick reference for syntax, 
using sample code selected from various 
parts of the book. 

## Defining a Functional Interface
```java
@FunctionalInterface
public interface TailCall<T> {
    
    TailCall<T> apply();
    
    default boolean isComplete() {
        return false;
    }
    //...
}
```

## Creating No-Parameter Lambda Expressions
```java
lazyEvaluator(() -> evaluate(1), () -> evaluate(2));
```

## Creating a Single-Parameter Lambda Expression
```java
friends.forEach((final String name) -> System.out.println(name));
```

## Inferring a Lambda Expression's Parameter Type
```java
friends.forEach((final String name) -> System.out.println(name));
```

## Dropping Parantheses for a Single-Parameter Inferred Type
```java
friends.forEach((final String name) -> System.out.println(name));
```
## Creating a Multi-Parameter Lambda expression
```java
friends.stream()
        .reduce((name1, name2) -> 
            name1.length() >= name2.length() ? name1 : name2);
```

## Calling a Method with Mixed Parameters
```java
friends.stream()
        .reduce("Steve", (name1, name2) -> 
            name1.length() >= name2.length() ? name1 : name2);
```

## Storing a Lambda Expression
```java
final Predicate<String> startsWithN = name -> name.startsWith("N");
```

## Creating a Multiline Lambda Expression
```java
FileWriterEAM.use("eam2.txt", writerEAM -> {
    writerEAM.writeStuff("how");
    writerEAM.writeStuff("sweet");
});
```

## Returning a Lambda Expression
```java
public static Predicate<String> checkIfStartsWith(final String letter) {
    return name -> name.startsWith(letter);
}
```

## Returning a Lambda Expression from a Lambda Expression
```java
final Function<String, Predicate<String>> startsWithLetter = 
    letter -> name -> name.startsWith(letter);
```

## Lexical Scoping in Closures
```java
public static Predicate<String> checkIfStartsWith(final String letter) {
    return name -> name.startsWith(letter);
}
```

## Passing a Method Reference of an Instance Method
```java
friends.stream().map(String::toUpperCase);
```

## Passing a Method Reference to a static Method
```java
str.chars().filter(Character::isDigit);
```

## Passing a Method Reference to a Method on Another Instance
```java
str.chars().forEach(System.out::println);
```

## Passing a Reference of a Method That Takes Parameters
```java
people.stream().sorted(Person::ageDifference);
```

## Using a Constructor Reference
```java
Supplier<Heavy> supplier = Heavy::new;
```

## Function Composition
```java
symbols.map(StockUtil::getPrice)
        .filter(StockUtil.isPriceLessThan(500))
        .reduce(StockUtil::pickHigh)
        .get();
```