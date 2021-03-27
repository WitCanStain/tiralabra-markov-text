## Week 1 report ##

This week I determined the topic for the project, created a repository for it, and got started on the documentation. So far the project is off to a good start and I have a clear idea of what to do.
After consultation with the course director, I learned of an efficient tree structure that could help me implement text generation with Markov Chains. After further research the trie seems to be a good candidate for ensuring constant efficiency for the actual generation of the resulting text. The downside is that the trie also requires quite a bit of storage space, which may limit the size of the training data and therefore the interestingness of the results. 
The next steps are to get started with implementing a rudimentary trie structure and a proof-of-concept demonstration for text generation with Markov Chains using the trie.

Questions:

What functions/algorithms must be implemented myself and which ones can be taken from the functionality of the standard Java libraries is still a little unclear. For example, are split() or join() allowed? Do I need to implement HashMap myself? How about iterating over lists? The FAQ hints that the range of allowed helper functions depends on the complexity of the project, and so it is difficult for me to determine the answer to these kinds of questions myself. 

