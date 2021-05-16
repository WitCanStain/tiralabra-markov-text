## Implementation ##

### Using a trie to implement Markov Chains ###

The goal of the program is to generate text sentences from any input text using Markov chains. The way that this will be done is by storing each k words long sequence into a trie structure. 

First, we split the input text into words or 'tokens' ('word' is a bit of a misnomer since punctuation marks are also counted as tokens). Then we run the createTrie() method of the Trie class to build a
trie which has as its nodes individual tokens of the text. For example, if the input text is "the quick brown fox jumped over the lazy dog.", then the list of tokens will be
["the", "quick", "brown", "fox", "jumped", "over", "the", "lazy", "dog", "."]. 

Suppose we have somewhere inside our input text two sentences, "he had become the monster" and  "he had lost himself". Our trie might in such a case look something like this (note that this is not the full trie):

![plain trie graph](https://i.imgur.com/Knm9D99.png)


The numbers next to the connecting arrows signify the frequency of occurrence of the node. For example, in the above example the word "become" appears after the words "he had" 3 times, and the word "lost"
appears once after "he had". When we generate sentences, we start from the root and randomly choose one of its child nodes - in the trie we're talking about, one of "he", "had", "become". 
When we get to the next level, we choose the next word based on its frequency. So, in this case, the word "had" would be chosen after "he" 4/10 times, and the word "would" would be chosen 6/10 times. We do the same when we go down again to the last node.

Once we have the first k words (k is the number that determines how many previous words we look at when deciding the next word) we have enough states to predict what the next state in the Markov chain should be.


![non-plain trie graph](https://i.imgur.com/PfjqOuW.png)

In the above graph, the red box contains one state, and the blue box contains another state. When we have gotten to the last node ("become"), we take it and the last k-1 words indicated by the node's location in the trie. This is our new previous state.
We then start again from the root and try to traverse that sequence of words down the trie. As long as in the original text this sequence of words is followed by at least one more word, we will find a match. We then again weigh the options and select the next node.
That word and the k-1 previous words will be our new state (blue box). Every time we reach a leaf node, we add it to the generated sentence. In this manner we can generate arbitrarily long sentences with Markov Chains.

In our example, we are left with the sentence "he had become the monster". 

### Structure of the program ###

The program's runtime cycle consists of essentially three components: in the first stage, if we are running the program through the command line, we resolve the values for the required parameters - filepath, length of words, number of sentences, number of words to use in the Markov Chain - or use default values if none are provided. In the first stage we also parse the given text file and separate its contents into a list of tokens. The first stage is accomplished through methods in the ParseInput class, and has time complexity O(n).

In the second stage, we create the trie. Here we use most of the data structures we have implemented - the Trie class itself, TrieNode, DynamicList, CustomHashMap, and HashNode. This is also the computationally most demanding stage of the program. Building the trie has time complexity O(nkc), where n is the number of tokens in the list, k is the number of tokens we base the next Markov chain stage on, and c is the number of child nodes when we are trying to insert a token which already exists at that position in the trie. This is because we iterate through the list of n tokens, and at each point we create a list of previous k tokens. When trying to insert a token which already exists at that position in the trie, we need to find its index among its sibling nodes, consuming an average of c/2 iterations where c is the number of its sibling nodes. Possible improvement: it may be possible to bring this down to O(n) through a more clever use of dynamic programming - storing each sequence of previous k tokens and pushing and popping from it as we proceed through the token list. Unfortunately I did not have time to properly experiment with this. 

In the third stage, we actually generate the Markov chain and get a sentence out. This is where the 'magic' happens. This stage is implemented mostly through methods in the MarkovProcess and Utility classes, utilising the data structures previously used. The time complexity for creating a sentence of length l, looking at the previous k tokens is O()

Sentence generation itself happens in two stages. In the first stage, we do not yet have enough tokens to draw on so we simply choose the first k tokens probabilistically. This stage has time complexity O(kc), where c is the greatest number of unique child nodes of a node in the sequence of k tokens. In the second stage we generate the rest of the sentence using the previous k tokens. This stage has time complexity O((l-k)(c+k)), where l is the length of the desired sentence. We get this result by considering that firstly we want l-k further tokens in our sentence, and secondly the weightedChoice() method has time complexity O(c) where c is as previously, and getNodeFromSequence() has time complexity O(k) since it needs to traverse down the trie to fetch the last node of the sequence. 

The trie will have a storage requirement of O(Nmk) where N is the number of distinct tokens in the corpus, and m is the number of different k-length sequences stored in the trie.


