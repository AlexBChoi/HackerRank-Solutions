import java.util.Scanner;

/**
 * This program finds the height of a tree after user specified growth cycles.
 */
public class Solution {
    /**
     * Takes input and validates number of test cases and cycles of tree sapling.
     * @param STDIN String to hold input
     * @return testCase array holding value of number of cycles
     */
    public static int[] inputPhase(int STDIN) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter number of test cases to be run: "); //comment out to pass default test case
        STDIN = in.nextInt();

        if(STDIN>10) {
            throw new IllegalArgumentException("Value must be equal to or less than 10.");
        }

        int[] testCase = new int[STDIN];

        for(int i=0; i<testCase.length; i++) {
            System.out.print("Enter number of cycle for test case " + (i+1) + ": "); //comment out to pass default test case
            STDIN = in.nextInt();
            testCase[i] = STDIN;

            if(STDIN>60) {
                throw new IllegalArgumentException("Value must be equal to or less than 60.");
            }
        }

        return testCase;
    }

    /**
     * Calculates growth cycle of tree sapling.
     * @param testCase array holding number of cycles
     * @return testCase holding value of height
     */
    public static int[] calculatePhase(int[] testCase) {
        int[] cycles = new int[testCase.length];

        //Extract number of cycles for each test cases
        for(int i=0; i<cycles.length; i++) {
            cycles[i] = testCase[i];
        }

        //Go through each test case. replace testCase value with growth height
        for(int i=0; i<testCase.length; i++) { //loop through each test cases
            int height = 0;
            for(int j=0; j<=cycles[i]; j++) { //loop through each cycle in test cases
                if(cycles[i]==0) {
                    testCase[i] = 1;
                }
                else if(cycles[i]==1) {
                    testCase[i] = 2;
                }
                else if(j%2==0) {
                    height = height+1;
                    testCase[i] = height;
                }
                else {
                    height = height*2;
                    testCase[i] = height;
                }
            }
        }

        return testCase;
    }

    /**
     * Displays growth of each tree sapling test cases.
     * @param STDOUT String to display output
     * @param testCase array holding value of height grown
     */
    public static void outputPhase(int STDOUT, int[] testCase) {
        for(int i=0; i<testCase.length; i++) {
            STDOUT = testCase[i];
            System.out.println("Growth of Test Case " + (i+1) + ": " + STDOUT); //comment out and output STDOUT only to pass default test case
        }
    }
    
    public static void main(String[] args) {
        int STDIN = 0;
        int STDOUT = 0;

        int[] testCase;

        testCase = inputPhase(STDIN);
        testCase = calculatePhase(testCase);
        outputPhase(STDOUT, testCase);
    }
}