## Introducing Streams
- A stream is a sequence of elements from a source that supports
data-processing operations.
- Streams make use of internal iteration: the iteration is abstracted 
away through operations such as `filter`, `map`, and `sorted`. 
- There are two types of stream operations: intermediate and terminal 
operations. 
- Intermediate operations such as `filter` and `map` return a stream and 
can be chained together. They're used to set up a pipeline of operations 
but don't produce any result. 
- Terminal operations such as `forEach` and `count` return a non-stream 
value and process a stream pipeline to return a result. 
- The elements of a stream are computed on demand ("lazily"). 