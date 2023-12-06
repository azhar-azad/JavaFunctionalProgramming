## Collection API Enhancements
- Java 9 supports collection factories, which let you create small
immutable lists, sets, and maps by using `List.of`, `Set.of`, `Map.of`, 
and `Map.ofEntries`. 
- The objects returned by those collection factories are immutable, which 
means that you can't change their state after creation. 
- The `List` interface supports the default methods `removeIf`, 
`replaceAll`, and `sort`. 
- The `Set` interface supports the default method `removeIf`. 
- The `Map` interface includes several new default methods for common 
patterns and reduces the scope for bugs. 
- `ConcurrentHashMap` supports the new default methods inherited from 
`Map` but provides thread-safe implementations for them. 