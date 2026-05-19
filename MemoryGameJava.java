// base code for student Memory Card Game assessment
//
//The cards are A B C D E F G H.
// A player can pick card’s by entering row and column. For example, for the first pick 'row: 2' and on the next line 'column: 0'
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class MemoryGameJava {
    private static final int SIZE = 4; // 4x4 board
    private static final String HIDDEN = " * ";
    private static String[][] board = new String[SIZE][SIZE];
    private static boolean[][] revealed = new boolean[SIZE][SIZE];
    private static int attemptCounter = 0;
    private static int row1 = 4;
    private static int column1 = 4;
    private static int row2 = 4;
    private static int column2 = 4;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Memory Pair Game (4x4 : 0,1,2,3)\n");
        initBoard();
        userInput();
    }

    // ===== Game setup =====
    private static void initBoard() {
        // assigns values to each position on the board
        List<String> cards = new ArrayList<>();
        for (char c = 'A'; c < 'A' + (SIZE * SIZE) / 2; c++) {
            cards.add(" " + c + " ");
            cards.add(" " + c + " ");
        }
        Collections.shuffle(cards);
        Iterator<String> it = cards.iterator();
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                board[r][c] = it.next(); // value for each board position
                revealed[r][c] = false; // determines whether a board position is revealed to the user or not
            }
        }
    }
    private static void printBoard() {
        // this function creates the board with its row and col. numbers in the terminal 
        // header
        System.out.print("    ");
        for (int c = 0; c < SIZE; c++) System.out.printf(" %d ", c);
        System.out.println();
        System.out.print("    ");
        for (int c = 0; c < SIZE; c++) System.out.print("---");
        System.out.println();

        for (int r = 0; r < SIZE; r++) {
            System.out.printf(" %d |", r);
            for (int c = 0; c < SIZE; c++) {
                System.out.print(revealed[r][c] ? board[r][c] : HIDDEN);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void userInput() {
        // this function will take the users choice of which cards to reveal
        int matchesFound = 0; // number of card matches found by the user 
        int totalMatchesFound = 8; // obtained via. (SIZE*SIZE/2)
        Instant startTime = Instant.now(); // records first instance of time - when the gameplay has started
        while (matchesFound < totalMatchesFound) {
            // loop continues until all possible matches have been found
            getCoords1(); //
            getCoords2();
            if (board[MemoryGameJava.row1][MemoryGameJava.column1].equals(board[MemoryGameJava.row2][MemoryGameJava.column2])) {
                // if its a match
                printBoard();
                matchesFound++; // increase the matches found by the user by one
                System.out.println("Match found! Keep going!");
            }
            else {
                // if not a match
                printBoard();
                System.out.println("Not a match unfortunately, keep going!");
                revealed[MemoryGameJava.row1][MemoryGameJava.column1] = false; // resets both spots on the board user has guessed and hides the value
                revealed[MemoryGameJava.row2][MemoryGameJava.column2] = false;
                row1 = 4; // each row and column the user has guessed is "reset" to an unacceptable value to prevent an infinite loop
                row2 = 4;
                column1 = 4;
                column2 = 4;
            }
            // each valid pair of spots guessed on the board (not necessarily correct guesses) will increase the attempt counter by one
            MemoryGameJava.attemptCounter++;
        }
        Instant finishTime = Instant.now(); // records second instance of time - when the the player has won and gameplay has finished
        long timeElapsed = Duration.between(startTime, finishTime).toSeconds(); // calculates time elapsed between start and finish time
        // game is finished if user reaches here
        System.out.println(String.format("Congratulations You Win. You have found all the matching pairs! It took you %d attempts", MemoryGameJava.attemptCounter)); // win game statement
        System.out.println("Time taken (seconds):");
        System.out.println(timeElapsed);
    }

    public static void getCoords2() {
        // will get the second card coordinate from the user 
        while(column2 < 0 || column2 > 3 || row2 < 0 || row2 > 3 || revealed[row2][column2] == true) { // numbers chosen by user must return false for each condition
            Scanner sc = new Scanner(System.in);
            try {
                printBoard();
                System.out.println("Make sure your numbers are between 0-3 and are not currently revealed.");
                System.out.print("Second column number: ");
                column2 = sc.nextInt(); // user column number
                System.out.print("Second row number: ");
                row2 = sc.nextInt(); // user row number
            } catch (InputMismatchException e) { // raises exception if a non-integer is submitted
               System.out.println("\nPlease type a number from 0-3\n"); 
            }
        }
        revealed[row2][column2] = true; // if user board space is valid it will be revealed
    }

    public static void getCoords1() {
        // will get the first card coordinate from the user
        while(column1 < 0 || column1 > 3 || row1 < 0 || row1 > 3 || revealed[row1][column1]==true) { // numbers chosen by user must return false for each condition
            Scanner sc = new Scanner(System.in);
            try {
                printBoard();
                System.out.println("Make sure your numbers are between 0-3 and are not currently revealed.");
                System.out.print("First column number: ");
                column1 = sc.nextInt(); // user column number
                System.out.print("First row number: ");
                row1 = sc.nextInt(); // user row number
            } catch (InputMismatchException e) { // raises exception a non-integer is submitted
               System.out.println("\nPlease type a number\n"); 
            }
        }
        revealed[row1][column1] = true; // if user coordinate is valid it will be revealed
    }
}