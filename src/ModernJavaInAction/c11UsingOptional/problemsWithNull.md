## Problems with null
- *It's a source of error*. `NullPointerException` is by far the most 
common exception in Java. 
- *It bloats your code*. It worsens readability by making it necessary to 
fill your code with `null` checks that are often deeply nested. 
- *It's meaningless*. It doesn't have any semantic meaning, and in 
particular, it represents the wrong way to model the absence of a value 
in a statically typed language. 
- *It breaks Java philosophy*. Java always hides pointers from developers
except in one case: the `null` pointer. 
- *It creates a hole in the type system*. `null` carries no type or other
information, so it can be assigned to any reference type. This situation
is a problem because when `null` is propagated to another part of the 
system, you have no idea what that `null` was initially supposed to be. 