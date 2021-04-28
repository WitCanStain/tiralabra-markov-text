## Implementation ##

The goal of the program is to generate text sentences from any input text using Markov chains. The way that this will be done is by storing each k words long sequence into a trie structure. 

First, we split the input text into words or 'tokens' ('word' is a bit of a misnomer since punctuation marks are also counted as tokens). Then we run the createTrie() method of the Trie class to build a
trie which has as its nodes individual tokens of the text. For example, if the input text is "the quick brown fox jumped over the lazy dog.", then the list of tokens will be
["the", "quick", "brown", "fox", "jumped", "over", "the", "lazy", "dog", "."]. 

Suppose we have somewhere inside our input text two sentences, "he had become the monster" and  "he had lost himself". Our trie might in such a case look something like this (note that this is not the full trie):

![plain trie graph](https://i.imgur.com/Knm9D99.png)


The numbers next to the connecting arrows signify the frequency of occurrence of the node. For example, in the above example the word "become" appears after the words "he had" 3 times, and the word "lost"
appears once after "he had". When we generate sentences, we start from the root and randomly choose one of its child nodes - in the trie we're talking about, one of "he", "had", "become". 
When we get to the next level, we choose the next word based on its frequency. So, in this case, the word "had" would be chosen after "he" 4/10 times, and the word "would" would be chosen 6/10 times. We do the same when we go down again to the last node.

Once we have the first k words (k is the number that determines how many previous words we look at when deciding the next word) we have what basically constitutes a single state in a Markov chain.
It is not the case that each word is a state of its own (unless k is 1). Rather, one state is a sequence of k words. In this case, k is 2, because we are looking at the previous 2 words when deciding what the next word should be.

![non-plain trie graph](https://i.imgur.com/PfjqOuW.png)

In the above graph, the red box contains one state, and the blue box contains another state. When we have gotten to the last node ("become"), we take it and the last k-1 words indicated by the node's location in the trie. This is our new previous state.
We then start again from the root and try to traverse that sequence of words down the trie. As long as in the original text this sequence of words is followed by at least one more word, we will find a match. We then again weigh the options and select the next node.
That word and the k-1 previous words will be our new state (blue box). Every time we reach a leaf node, we add it to the generated sentence. In this manner we can generate arbitrarily long sentences with Markov Chains.

In our example, we are left with the sentence "he had become the monster". 
