import java.util.ArrayList;
import java.util.List;

public class Wordle {

    // Reads all words from dictionary filename into a String array.
    public static String[] readDictionary(String filename) {
        // dynamic object type to not iterate the list twice
        List<String> strList = new ArrayList<>();
        In in = new In(filename);
        while (!in.isEmpty()) {
            String s = in.readLine();
            strList.add(s);
        }
        String[] allWords = strList.toArray(new String[0]);
        return allWords;
    }
    // Reads all words from dictionary filename into a String array.
    /*
     * public static String[] readDictionary(String filename) {
     * // ...
     * // String[] dict = readDictionary("dictionary.txt");
     * //String[] words=In(dictionary.txt);
     * //String[] allWords={"hi"};
     * int cnt=0;
     * //System.out.println(str.In.exists(str));
     * In in = new In(filename);
     * while (!in.isEmpty()) {
     * String s = in.readLine();
     * //System.out.println(s);
     * cnt++;
     * }
     * String[] allWords= new String[cnt];
     * int i=0;
     * while (!in.isEmpty()) {
     * String s = in.readLine();
     * allWords[i]=s;
     * //System.out.println(s);
     * i++;
     * }
     * return allWords;
     * }
     */

    // Choose a random secret word from the dictionary.
    // Hint: Pick a random index between 0 and dict.length (not including) using
    // Math.random()
    public static String chooseSecretWord(String[] dict) {
        // ...
        int arrLength = dict.length;
        int rndInt = (int) (Math.random() * arrLength);
        // System.out.println(rndInt);
        // System.out.println("SecretWord: " + dict[rndInt]);
        return dict[rndInt];
    }

    // Simple helper: check if letter c appears anywhere in secret (true), otherwise
    // return false.
    public static boolean containsChar(String secret, char c) {
        // ...
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == (c)) {
                return true;
            }
        }
        return false;
    }

    // Compute feedback for a single guess into resultRow.
    // G for exact match, Y if letter appears anywhere else, _ otherwise.
    public static void computeFeedback(String secret, String guess, char[] resultRow) {
        // ...
        // you may want to use containsChar in your implementation
        // hello heeyo
        for (int i = 0; i < guess.length(); i++) {
            char guessChar = guess.charAt(i);
            char secretChar = secret.charAt(i);
            boolean isCorrect = containsChar(guess, secretChar);
            //System.out.println(resultRow[i]);
            if (isCorrect) {
                if (guessChar == secretChar) {
                    resultRow[i] = 'G';
                    //System.out.println("here 1");
                } else {
                    resultRow[i] = 'Y';
                    //System.out.println("here 2");
                }
            } else {
                resultRow[i] = '_';
                //System.out.println("here 3");
            }
        }
    }



    // Store guess string (chars) into the given row of guesses 2D array.
    // For example, of guess is HELLO, and row is 2, then after this function
    // guesses should look like:
    // guesses[2][0] // 'H'
    // guesses[2][1] // 'E'
    // guesses[2][2] // 'L'
    // guesses[2][3] // 'L'
    // guesses[2][4] // 'O'

    public static void storeGuess(String guess, char[][] guesses, int row) {// maybe add results[][]
        // ...
        for (int i = 0; i < guess.length(); i++) {
            guesses[row][i] = guess.charAt(i);
        }
    }

    // Prints the game board up to currentRow (inclusive).
    public static void printBoard(char[][] guesses, char[][] results, int currentRow) {
        System.out.println("Current board:");
        for (int row = 0; row <= currentRow; row++) {
            System.out.print("Guess " + (row + 1) + ": ");
            for (int col = 0; col < guesses[row].length; col++) {
                System.out.print(guesses[row][col]);
            }
            System.out.print("   Result: ");
            for (int col = 0; col < results[row].length; col++) {
                System.out.print(results[row][col]);
            }
            System.out.println();
        }
        System.out.println();
    }

    // Returns true if all entries in resultRow are 'G'.
    public static boolean isAllGreen(char[] resultRow) {
        // ...
        for (int i = 0; i < resultRow.length; i++) {
            if (resultRow[i] != 'G') {
                //System.out.println(resultRow[i] + " " + i);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        int WORD_LENGTH = 5;
        int MAX_ATTEMPTS = 6;

        // Read dictionary
        String[] dict = readDictionary("dictionary.txt");
        /*
         * for(int i=0;i<dict.length;i++){
         * System.out.println(dict[i]);
         * }
         */

        // Choose secret word
        String secret = chooseSecretWord(dict);
        System.out.println(secret);
        // String inp=In.readString();
        // In in = new In.readStrings()

        // Prepare 2D arrays for guesses and results
        char[][] guesses = new char[MAX_ATTEMPTS][WORD_LENGTH];// ...
        char[][] results = new char[MAX_ATTEMPTS][WORD_LENGTH];// ...

        // Prepare to read from the standart input
        In inp = new In();
        // inp.readString();

        // String s = inp.readLine();
        // s=s.toUpperCase();

        // System.out.println(s);
        // System.out.println(containsChar(s, 'A'));

        int attempt = 0;
        boolean won = false;

        while (attempt < MAX_ATTEMPTS && !won) {

            String guess = "";
            boolean valid = false;

            // Loop until you read a valid guess
            while (!valid) {
                System.out.print("Enter your guess (5-letter word): ");
                guess = inp.readString();// ... read from the standrad input
                guess = guess.toUpperCase();
                if (!(guess.length() == WORD_LENGTH)/* ... check if the guess is valid */) {
                    System.out.println("Invalid word. Please try again.");
                } else {
                    valid = true;
                }
            }

            // Store guess and compute feedback
            // ... use storeGuess and computeFeedback
            storeGuess(guess, guesses, attempt);
            computeFeedback(secret, guess, results[attempt]);

            // Print board
            printBoard(guesses, results, attempt);

            // Check win

            if (isAllGreen(results[attempt])) {
                System.out.println("Congratulations! You guessed the word in " + (attempt +
                        1) + " attempts.");
                won = true;
            }

            attempt++;
        }

        if (!won) {
        // ... follow the assignment examples for how the printing should look like
        System.out.println("Sorry, you did not guess the word.");
        System.out.println("The secret word was: " + secret);
        }
        
        inp.close();
        }
    }
