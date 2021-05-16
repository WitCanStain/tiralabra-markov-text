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

### Structure of the program and O-analysis ###

The program's runtime cycle consists of essentially three components: in the first stage, if we are running the program through the command line, we resolve the values for the required parameters - filepath, length of words, number of sentences, number of words to use in the Markov Chain - or use default values if none are provided. In the first stage we also parse the given text file and separate its contents into a list of tokens. The first stage is accomplished through methods in the ParseInput class, and has time complexity O(n).

In the second stage, we create the trie. Here we use most of the data structures we have implemented - the Trie class itself, TrieNode, DynamicList, CustomHashMap, and HashNode. This is also the computationally most demanding stage of the program. Building the trie has time complexity O(nkmc), where n is the number of tokens in the list, k is the number of tokens we base the next Markov chain stage on, and m is the number of child nodes when we are trying to insert a token which already exists at that position in the trie. This is because we iterate through the list of n tokens, and at each point we create a list of previous k tokens. When trying to insert a token which already exists at that position in the trie, we need to find its index among its sibling nodes to increase its occurrence counter, consuming an average of c/2 iterations where c is the number of its sibling nodes. Possible improvement: it may be possible to bring this down to O(nk) through utilising CustomHashMaps to a greater extend and bringing the time to for finding and increasing occurrences of an already-existing node to O(1). Unfortunately, I did not have enough time to experiment with this.

In the third stage, we actually generate the Markov chain and get a sentence out. This is where the 'magic' happens. This stage is implemented mostly through methods in the MarkovProcess and Utility classes, utilising the data structures previously used. 

Sentence generation itself happens in two stages. In the first stage, we do not yet have enough tokens to draw on so we simply choose the first k tokens probabilistically. This stage has time complexity O(kc), where c is the greatest number of unique child nodes of a node in the sequence of k tokens. In the second stage we generate the rest of the sentence using the previous k tokens. This stage has time complexity O((l-k)(c+k)), where l is the length of the desired sentence. We get this result by considering that firstly we want l-k further tokens in our sentence, and secondly the weightedChoice() method has time complexity O(c) where c is as previously, and getNodeFromSequence() has time complexity O(k) since it needs to traverse down the trie to fetch the last node of the sequence. 

The trie will have a storage requirement of O(Nmk) where N is the number of distinct tokens in the corpus, and m is the number of different k-length sequences stored in the trie.


### Performance ###

Overall, the program performs with an acceptable speed even when fed large bodies of text. When given the book 'Brothers Karamazov' by Fyodor Dostoyevsky, which contains 441313 words, with k=2 it builds the trie in an average of 5000 milliseconds, and generates sentences in about 0.17 milliseconds. With k=3, the program takes roughly 6500 milliseconds and generates sentence in roughly 0.21 milliseconds. We can see that sentence generation is roughly four orders of magnitude faster than trie creation. 

We can also try to assess the performance of the resulting text qualitatively. 

Here are five sentences generated from F. Scott Fitzgerald's book The Great Gatsby with k=1. 

1:   John D . I went over cataracts of a bootlegger 
2:   Tom ? ”  She goes around on a part 
3:   the wives .  “The Rosary , ”  “Of 
4:   and I hear you run a chair ; they say 
5:   it was Gatsby hesitated . ”  “And what ? 

We can see that at k=1 the sentences, though at points resembling coherence, lack any kind of grammatical or semantic continuity. 

1:   and expenses , including how to subscribe to our email newsletter to hear about a 
2:   themselves . I started away .  “I beg your pardon , ” she said 
3:   another world , a computer virus , or at least the same brown tint as 
4:   the city , where there are wanderers , confident girls who weave here and there 
5:   stout , but before he perceived the man —it was the hour , before my 

At k=2 and length=15 we see greater coherence, and the words string together into sentences that one might imagine coming from a human mouth. (if you are wondering why a 1925 book is talking about computer viruses - that part comes from Project Gutenberg's attached disclaimers). 

How about Dostoyevsky's Brothers Karamazov?

1:   and writhing with pain he went down the stairs wondering if he would have pulled 
2:   if I won ’t repeat the questions in order , and , having accomplished the 
3:   face .  “You won ’t make me blush , dear young lady , I 
4:   youth to hard and tedious study , if only for that night , for I 
5:   whom I should never have owned it to myself . And yet , in spite 

At k=3 we already have fairly sensible sentences that have some internal semantic sense. 

1:   beginning to confuse me and I had a bad moment there before I realized that 
2:   course I knew what they were referring to , but I wasn ’t even vaguely 
3:   ? ”  Silence for a moment . Then :  “However —I want to 
4:   whispered Jordan respectfully , and everyone laughed .  “Open another window , ” commanded 
5:   after attempting to laugh at the situation in a dignified and indifferent way , broke 

At k=6, for The Great Gatsby, we start copying text directly from the source. This is because in the original text the vast majority of 6-word sequences exist only once in the book, and so there is only one way for the sentence to be constructed - in the same manner as it originally appeared in the book.

Overall, the quality of resulting sentences seems to be partly dependent on the size of the training data, and partly dependent on the k-value we choose. The program outputs (in my opinion) interesting and coherent sentences when we increase k to a sufficiently high value, and there is a lot of room for experimentation with different source texts.

### Improvements ###

The program could be improved in several ways. Firstly, though the performance of the program is fast enough for on-the-fly text generation for all but the largest of texts, the time complexity could probably be improved by utilising CustomHashMap functionality more ubiquitously or by using clever dynamic programming to hasten the process of iterating over loops. Secondly, we could add a so-called alpha value which determines the likelihood that a completely random word is next chosen for the sentence - increasing novelty but perhaps decreasing coherence of the resulting sentences. It would also be interesting to add an in-built possibility to join multiple source texts together and construct the trie from the resulting token list. Unfortunately, I did not have time to implement these fun ideas.
