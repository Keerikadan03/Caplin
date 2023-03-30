import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MostFrequentLetter {
    public static void main(String[] args) {
        String url = "https://www.gutenberg.org/cache/epub/29364/pg29364.txt";
        String pageContent = "";

        try (Scanner scanner = new Scanner(new URL(url).openStream(), StandardCharsets.UTF_8.toString())) {
            scanner.useDelimiter("\\A");
            pageContent = scanner.next().toLowerCase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // use regex to find all words that start with a letter
        Pattern pattern = Pattern.compile("\\b\\w");
        Matcher matcher = pattern.matcher(pageContent);

        // count the frequency of each letter
        Map<Character, Integer> freq = new HashMap<>();
        while (matcher.find()) {
            char letter = matcher.group().toLowerCase().charAt(0);
            freq.put(letter, freq.getOrDefault(letter, 0) + 1);
        }

        // find the letter that starts the most words
        char mostCommon = '\0';
        int highestFreq = 0;
        for (char letter : freq.keySet()) {
            int letterFreq = freq.get(letter);
            if (letterFreq > highestFreq) {
                mostCommon = letter;
                highestFreq = letterFreq;
            }
        }

        // output the result
        System.out.println("The letter that starts the most words is: " + mostCommon);
    }
}
