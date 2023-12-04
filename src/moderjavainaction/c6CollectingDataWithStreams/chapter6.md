## Collecting Data With Streams
- `collect` is a terminal operation that takes as argument various 
recipes (called collectors) for accumulating the elements of a stream 
into a summary result. 
- Predefined collectors include reducing and summarizing stream elements
into a single value, such as calculating the minimum, maximum, or average.
- Predefined collectors let you group elements of a stream with `groupingBy`
and partition elements of a stream with `partitioningBy`. 
- Collectors compose effectively to create multilevel groupings, 
partitions, and reductions. 
- You can develop your own collectors by implementing the methods defined 
in the `Collector` interface. 