## Working with Streams
- The Streams API lets you express complex data processing queries. 
- You can filter and slice a stream using the `filter`, `distinct`, 
`takeWhile` (Java 9), `dropWhile` (Java 9), `skip`, and `limit` methods. 
- The methods `takeWhile` and `dropWhile` are more efficient than a filter
when you know that the source is sorted. 
- You can extract or transform elements of a stream using the `map` and 
`flatMap` methods. 
- You can find elements in a stream using the `findFirst` and `findAny` 
methods. You can match a given predicate in a stream using the `allMatch`,
`noneMatch`, and `anyMatch` methods. 
- These methods make use of short-circuiting: a computation stops as soon 
as a result is found; there's no need to process the whole stream. 
- You can combine all elements of a stream iteratively to produce a result
using the `reduce` method, for example, to calculate the sum or find the 
maximum of a stream. 
- Some operations such as `filter` and `map` are stateless: they don't 
store any state. Some operations such as `reduce` store state to calculate
a value. Some operations such as `sorted` and `distinct` also store state
because they need to buffer all the elements of a stream before returning 
a new stream. Such operations are called *stateful operations*. 
- There are three primitive specializations of streams: `IntStream`, 
`DoubleStream`, and `LongStream`. Their operations are also specialized 
accordingly. 
- Streams can be created not only from a collection but also from values, 
arrays, files, and specific methods such as `iterate` and `generate`. 
- An infinite stream has an infinite number of elements (for example all 
possible strings). This is possible because the elements of a stream are 
only produced *on demand*. You can get a finite stream using methods such 
as `limit`. 