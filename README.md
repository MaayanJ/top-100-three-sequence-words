# top-100-most-common-three-words-sequence
This program reads txt files and returns 100 most common three words phrases.
The application can take file path input as an argument at run time or via std.in if ran
without arguments (see examples below)

##Running the program
This program is written with Java 11 and utilizes Gradle and it's wrapper to easily compile and 
ran the application, we well as tests.

The program can also be run with Docker.

###Build and ran the project
To clone the project, run from the command line: 
```bash
git clone https://github.com/MaayanJ/top-100-three-sequence-words.git
```

Then navigate to the root directory
```bash
cd top-100-three-sequence-words
```

To build and ran the application with Gradle with arguments:
```bash
./gradlew build
```
Then:
```bash
./gradlew run --args='src/main/resources/mobydick.txt'
```
*You can pass multiple file paths through the arguments by separating the files with a 
single white space like so;
```bash
./gradlew run --args='src/main/resources/mobydick.txt src/main/resources/anotherstory.txt'
```
*A valid file path starts from the src directory so all files should relative that directory.
The easiest way to read a file is to place it in the src/main/resources directory like
the above example (src/main/resources/mobydick.txt).

If you choose to ran the application without arguments ran:
```bash
./gradlew run
```

The application will then prompt you to enter a file paths (you can enter multiple files, again, with single white space between the paths)
The file path format should remain the same, but with no quotes:
```bash
src/main/resources/mobydick.txt
```

To build and ran the application with Docker with arguments:
```bash
 docker build -t top-100-three-sequence-words . --args='src/main/resources/mobydick.txt'
```
Then
```bash
docker run -it top-100-three-sequence-words
```
In order to run without arguments, ran:
```bash
 docker build -t top-100-three-sequence-words .
```
Then
```bash
docker run -it top-100-three-sequence-words
```
You will then be promted to enter the file path through the command line. 

*Multiple file paths can be passed in with a single white space between the paths.
The file path format should be:
```bash
src/main/resources/mobydick.txt
```

#Running Tests
The tests mainly cover the logic for reading the file, removing punctuation, building 3 words phrases and 
printing the top 100 most common phrases.

To run the Junit tests:

```bash
cd top-100-three-sequence-words
./gradlew test
```
*Tests report can be found in the generated build folder under tests/test/index.html

##Extra Credit
The program can run large files efficiently, can handle Unicode and can ran via Docker

##What you would do next, given more time (if anything)?
1. The unit tests cover the main methods that contain logic, in a behavioural, black box manner to reduce coupling, in case the internals of the method changes, however, if I had more time, I would have added more tests, for the different types of input (std vs arguments) as well as null checks
2. While I attempted to select the most optimized way to handle removal of punctuations, there is still one method that contains regular String concatenation ("word" + "another_word") but using the .append method is better for performance
3. For the sorting functionality, intead of using Streams to sort the Map according to it's values, I  would have used LinkedHashMap with access property set to true which would have acted as a Most-useed-values cache and would have naturally put the most accessed elements at the top of the map (regular maps don't maintain order) and this would have been a lot more efficient
4. For reading more than one file, I would have added multi-threading capability so that files can be read in parallel in different threads
5. Although I made every attempt to make the application organized and readable and have the methods as decoupled as possible, I would have added some interfaces for further decaoupling


##Decision Making
In this section, I just wanted to add that I did try thinking very carefully of the way files and methods are structred.
If you look at the FileProcessor class, you can see that each method has a very specific functionality, that can also very easily be tested in isolation.
Many times methods can become too large/too dependent on other methods which forces us to write unnecessarily large and as a result slow tests when we want to test simple behaviour.
In addition, from readability point of view, we should be generally able to look at a method's name and understand immediately what it does, without having to read it line be line.
These are just some of my considerations when I wrote the code, but hopefully it gives you an idea of how I approach code design.

Tested on https://www.gutenberg.org/files/2701/old/moby10b.txt modydick
