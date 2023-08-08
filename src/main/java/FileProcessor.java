import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;


public class FileProcessor {

    /**
     * This method orchestrates the entire process of reading, validating, filtering and sorting a file
     * and well as printing out the results.
     * The method iterate through a list of filePaths and passes each down to the appropriate methods
     * @param filePaths
     */
    public void processFiles(Set<String> filePaths) {
        boolean fileFound = false;
        for(String path:filePaths) {
            String content ="";
            try {
                content = readFiles(path);
                fileFound = true;
            }
            catch (IOException e) {
                System.out.println("file with path: " + path + "was not found. If you entered more than one path, those will continue to process");
            }
            if(fileFound) {
                Map<String, Integer> mostCommonSequences = findCommonThreeWordsSequences((validateContent(content)));
                printMostCommonSequences(sortSequencesByValue(mostCommonSequences), path);
            }
        }
    }


    protected String readFiles(String filePath) throws IOException {
        String line = "";
        String content = "";
        BufferedReader reader = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8);
        while((line = reader.readLine()) != null) {
            if(!line.isEmpty()) {
                content += line + " ";
            }
        }
        //prevent memory leaks.
        reader.close();
        return content;
    }
    /**
     * Since we wish to ignore punctuation and letter case,
     * we need to first process the content and make the appropriate modifications (i.e making all letters lower case etc.)
     * @param fileContent
     */
    protected String validateContent(String fileContent) {
        String str= fileContent;
        str = (str.
                replaceAll("[^a-zA-Z']"," ")
                .replaceAll("'", "")
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("\\p{C}", " ")
                .replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "")
                .replaceAll(" \\p{Sc}", " ")
                .replaceAll("\\s+", " ")).toLowerCase();
        return str;
    }

    /**
     * This method counts the occurrences of each unique phrase
     * Since we are parsing large files, I implemented a four pointer algorithm, that in once iteration, reads 3 words
     * from both ends of the string, in an attempt to cut in half the number of iterations needed.
     * In each iteration, 3 words sequence is taken from the beginning of the string (using pointers 1 and 2)
     * and from the end of the string (using pointers 3 and 4)
     * At the end of each iteration, all pointers are incremented/decremented accordingly (1 and 2 will increment and 3 and 4 will decrement)
     * so all pointers work in an outside in approach until they meet, then the loop is terminated.
     * @Return a sorted map with reduced top 100 values
     * @param content
     */
    protected Map<String, Integer> findCommonThreeWordsSequences(String content) {
        Map<String, Integer> commonSequences = new HashMap<>();

        //4 pointers solution with sliding windows for every 3 words from both ends of the array.
        //to cut in half the number of iterations
        ArrayList<String> listOfWords = new ArrayList<>(Arrays.asList(content.split(" ")));

        //creating 3 words sliding window at the beginning of the array
        int firstPointer = 0;
        int secondPointer = 2;

        //creating 3 words sliding window at the end of the array
        int thirdPointer = listOfWords.size()-3;
        int forthPointer = listOfWords.size()-1;

        while(firstPointer < thirdPointer) {
            StringBuffer phrase1 = new StringBuffer();
            StringBuffer phrase2 = new StringBuffer();

            //Append is more efficient than regular string concatenation
            String phrase1String = (phrase1.append(listOfWords.get(firstPointer)).append(" ").append(listOfWords.get(firstPointer+1)).append(" ").append(listOfWords.get(secondPointer))).toString();
            String phrase2String = (phrase2.append(listOfWords.get(thirdPointer)).append(" ").append(listOfWords.get(thirdPointer+1)).append(" ").append(listOfWords.get(forthPointer))).toString();

            Integer countPhrase1 = commonSequences.containsKey(phrase1String) ? commonSequences.get(phrase1String) : 0;
            commonSequences.put(phrase1String, countPhrase1 + 1);

            Integer countPhrase2 = commonSequences.containsKey(phrase2String) ? commonSequences.get(phrase2String) : 0;
            commonSequences.put(phrase2String, countPhrase2 + 1);

            firstPointer++;
            secondPointer++;
            thirdPointer--;
            forthPointer--;

        }
        return commonSequences;
    }

    /**
     * Sort the sequences map in descending order
     * @Return a sorted map with reduced top 100 values
     * @param sequences
     */
    protected Stream<Map.Entry<String, Integer>> sortSequencesByValue(Map<String, Integer> sequences) {
       return sequences.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(100);
    }

    /**
     * prints top values
     * @param sortedSequences
     */
    private void printMostCommonSequences(Stream<Map.Entry<String, Integer>> sortedSequences, String path) {
        System.out.println("Processing file: " + path + "---------------------------");
        sortedSequences.forEach(s -> System.out.println(s));
    }
}
