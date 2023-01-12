##  Letancy

### Demonstrate letancy problem of random memory access

This simple project demonstrates RAM times for random access. 
It creates List of Strings and then get random character from it
First time it access only some a few Strings what can cause
processor to cache it. I observe that it enables to iterate 
1 billion items per second

Second time I access String randomly across whole list.
Then processor has to access objects from memory.
I observe only 70 million iterations per second.

###  Building

It is standard Maven project. You can build it with it.