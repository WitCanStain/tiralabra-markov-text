## Week 3 report ##

This week I got to work on creating the necessary data structures to support
the Markov process, namely the Trie class and its associated TrieNode. So far
there is heavy reliance on pre-made datastructure and algorithms like HashMap
and ArrayList. These will gradually be phased out in favour of my own inferior
implementation. There are likely several inefficiencies in the process. In particular I could not find a more efficient method of calculating the probability
distribution and selecting a node child at random except for using two support ArrayLists. 
With large corpora I have a feeling that having each node be a Java object of its own may also cause some overhead, but I have not found a better method.

Currently, the program is at a state where it exhibits some desirable characteristics of Markov Chains but is severely lacking in other aspects.
For example, given the text excerpt from Dostoyevsky's Crime and Punishment located in testInputFile
I have noticed sentences being generated that are not simply contiguous chunks from the original. Given the short length of the example text using sequences of words more than 2 tokens long as comprising the previous state of the machine will yield results that are just copied from the input.

On the other hand, the existing methods, especially in MarkovProcess and Utility, are 'rough around the edges' to put it mildly
and larger texts may well cause slowness. There are likely also numerous edge cases that I have not accounted for.

Overall, however, I think that the current iteration demonstrates the basic principle of Markov chains and provides me with a good platform on which to improve.

Testing and checkstyle have not advanced this week because I've been too focused on getting the core functionality to work, apologies.

Time used: 11 hours

