import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashSet;

/**
 * This program finds the common elements found in rocks.
 */
public class Solution {
    /**
     * Takes input and validates number of rocks and elements of each rock.
     * @param STDIN String input
     * @return String array of all rocks
     */
    public static String[] inputPhase(String STDIN){
        int numRocks;
        Scanner in = new Scanner(System.in);

        System.out.print("Enter number of rocks: "); //comment out for default test case
        STDIN = in.nextLine();
        numRocks = Integer.parseInt(STDIN);

        if(numRocks<1 || numRocks>100) {
            throw new IllegalArgumentException("Invalid input. Enter value between 1-100");
        }

        String[] rock = new String[numRocks];
        String pattern = "[a-z]+";
        Pattern r = Pattern.compile(pattern);

        //Loops input request for number of rocks entered.
        for(int i=0; i<=numRocks-1; i++) {
            System.out.print("Enter combination for rock " + (i+1) + ": "); //comment out for dfault test case
            STDIN = in.nextLine();

            Matcher m = r.matcher(STDIN);
            if(!m.matches() || STDIN.length()>100) {
                throw new IllegalArgumentException("Invalid input. Enter lowercase value between a-z between 1-100 characters");
            }

            rock[i] = STDIN;
        }

        return rock;
    }

    /**
     * Builds ArrayList of elements from first rock to be compared to other rocks.
     * @param rocks Array of rocks with elements
     * @return elements holding first rock's elements
     */
    public static ArrayList<Character> originalElements(String[] rocks) {
        ArrayList<Character> elements = new ArrayList<Character>();

        //Make list of elements of first rock to be matched
        for(int i=0; i<=rocks[0].length()-1; i++) {
            char letter = rocks[0].charAt(i);
            elements.add(letter);
        }

        return elements;
    }

    /**
     * Match remaining array of rocks with ArrayList created from first rock.
     * @param elements ArrayList to be compared to
     * @param rocks Array of rock to be compared with
     * @return elements with common characters found in all rocks
     */
    public static ArrayList<Character> matchElements(ArrayList<Character> elements, String[] rocks) {
        //Replace index with null value if no match is found in whole array
        for(int i=0; i<=elements.size()-1; i++) { //loop through all values in ArrayList
            char compareLetter = elements.get(i);
            for(int j=1; j<=rocks.length-1; j++) { //loop through all rock array
                for(int k=0; k<=rocks[j].length()-1; k++) { //loop through each character from current rock array
                    if(!rocks[j].contains(Character.toString(compareLetter))) {
                        elements.set(i, null);
                    }
                    else {
                        break;
                    }
                }
            }
        }
        return elements;
    }

    /**
     * Remove all null value in ArrayList and create HashSet to remove of duplicate values from ArrayList
     * @param elements ArrayList of all matching characters
     * @return result holding only the characters that matched in all rock array
     */
    public static StringBuilder cleanUp(ArrayList<Character> elements) {
        elements.removeAll(Collections.singleton(null));//removes all null values in ArrayList
        HashSet<Character> noDuplicate = new HashSet<Character>();
        noDuplicate.addAll(elements);

        StringBuilder result = new StringBuilder(elements.size());

        for(Character c : noDuplicate) {
            result.append(c);
        }

        return result;
    }

    /**
     * Displays number of characters present in String result.
     * @param result
     * @param STDOUT
     */
    public static void displayResult(StringBuilder result, String STDOUT) {
        STDOUT = result.toString();
        System.out.println(STDOUT.length());
    }

    public static void main(String[] args) {
        String STDIN = null;
        String STDOUT = null;

        String[] rocks;
        ArrayList<Character> elements;
        StringBuilder result;

        rocks = inputPhase(STDIN);
        elements = originalElements(rocks);
        elements = matchElements(elements, rocks);
        result = cleanUp(elements);
        displayResult(result, STDOUT);
    }
}