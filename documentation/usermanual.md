### User Manual ###

The program can be compiled and packaged in the project directory with `mvn package`. It can the be run with `java -jar [jar_name] [options]`.

To run the performance tests, use the `-t` flag. 

To specify the file to use, use the `-f` flag

To specify the number of sentences to be generated, use the `-n` flag.

To specify the k-value, use the `-k` flag.

To specify the length of sentences, use the `-l` flag.

To use default values, provide no parameters.

Example:

`java -jar markovtext.jar -f test.txt -k 2 -n 10 -l 15`

This input will generate 10 sentences of length 15 tokens with k previous words as the Markov states from test.txt.


The test files are found in the main directory, and the program assumes that it is located in the same directory as the test files. If you wish to run the tests from NetBeans,
go up another folder by adding an additional `.` to the default file paths.
