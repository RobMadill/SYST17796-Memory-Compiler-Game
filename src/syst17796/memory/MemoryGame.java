package syst17796.memory;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MemoryGame extends Game {

    private String playAgain;

    private int player1Score;
    private int player2Score;

    private Player p1;
    private Player p2;

    private CardGrid grid = new CardGrid(4, 13);

    private Scanner in = new Scanner(System.in);
    private final ArrayList<Card> pastPlayerCardChoice = new ArrayList<>();

    public MemoryGame(String gameName) {
        super(gameName);
        this.p1 = new Player("");
        this.p2 = new Player("");
        this.player1Score = 0;
        this.player2Score = 0;
        play();
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }

    public boolean compare(PlayingCard firstCard, PlayingCard secondCard) {
        boolean match = false;
        if (firstCard.getValue() == (secondCard.getValue()) && firstCard.getColourSuit().equals(secondCard.getColourSuit())) {
            if (!pastPlayerCardChoice.contains(firstCard) && !pastPlayerCardChoice.contains(secondCard)) {
                match = true;
            } else if (pastPlayerCardChoice.contains(firstCard) && pastPlayerCardChoice.contains(secondCard)) {
                match = false;
            }
        }
        return match;
    }

    public String[][] gameGrid() {
        int setRows = grid.getNumOfRows() + 1;
        int setColumns = grid.getNumOfColumns() + 1;

        String[][] num = new String[setRows][setColumns];

        for (int i = 1; i < setRows; i++) {//rows
            for (int j = 1; j < setColumns; j++) {//columns
                num[0][0] = "";
                num[0][j] = String.valueOf(j);//1234etc...                
                num[i][0] = String.valueOf(Character.toChars(i + 64));//ABCD...
                num[i][j] = String.valueOf(Character.toChars(i + 64)) + j;
            }
        }
        return num;
    }

    public void playerTurn(Player activePlayer) {
        char rowChoice = '!';
        char rowChoice2 = '!';
        int numRow;
        int columnChoice = 0;
        int columnChoice2 = 0;
        boolean valid = true;
        PlayingCard pc1;
        PlayingCard pc2;
        do {
            System.out.println("Enter a row you'd like to pick from");
            do {
                try {
                    rowChoice = in.next().toUpperCase().charAt(0);
                    numRow = Character.getNumericValue(rowChoice) - 10;
                    valid = true;
                    if (numRow > grid.getNumOfRows()) {
                        System.out.println("Error. Please enter a valid character.");
                        valid = false;
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Error. Please enter a valid character.");
                }
            } while (valid == false);
            in.reset();
            System.out.println("Enter which column you'd like to pick from");
            valid = true;
            do {
                try {
                    columnChoice = in.nextInt();
                    valid = true;
                    if (columnChoice > grid.getNumOfColumns()) {
                        System.out.println("Error. Please enter a valid column choice");
                        valid = false;
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Error. Please enter a valid number.");
                }
            } while (valid == false);
            in.reset();
            System.out.println("You selected: " + getHiddenCard(rowChoice, columnChoice).toString());
            pc1 = getHiddenCard(rowChoice, columnChoice);
            System.out.println("Enter a row you'd like to pick from");
            do {
                try {
                    rowChoice2 = in.next().toUpperCase().charAt(0);
                    numRow = Character.getNumericValue(rowChoice) - 10;
                    valid = true;
                    if (numRow > grid.getNumOfRows()) {
                        System.out.println("Error. Please enter a valid character.");
                        valid = false;
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Error. Please enter a valid character.");
                }
            } while (valid == false);
            System.out.println("Enter which column you'd like to pick from");
            do {
                try {
                    columnChoice2 = in.nextInt();
                    valid = true;
                    if (columnChoice > grid.getNumOfColumns()) {
                        System.out.println("Error. Please enter a valid column choice");
                        valid = false;
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Error. Please enter a valid number.");
                }
            } while (valid == false);
            pc2 = getHiddenCard(rowChoice2, columnChoice2);
            System.out.println("You selected: " + getHiddenCard(rowChoice2, columnChoice2).toString());
            if (rowChoice == rowChoice2 && columnChoice == columnChoice2) {
                System.out.println("You selected the same index twice");
                System.out.println("Please try again.");
            }
        } while (rowChoice == rowChoice2 && columnChoice == columnChoice2);

        if (compare(pc1, pc2)) {
            if (p1.getName().equals(activePlayer.getName())) {
                setPlayer1Score(player1Score += 1);
                pastPlayerCardChoice.add(pc1);
                pastPlayerCardChoice.add(pc2);

            }
            if (p2.getName().equals(activePlayer.getName())) {
                setPlayer2Score(player2Score += 1);
                pastPlayerCardChoice.add(pc1);
                pastPlayerCardChoice.add(pc2);
            }
            System.out.printf("It's a match! Current score: \n%s: %d %s: %d\n", p1.getName(),
                    getPlayer1Score(), p2.getName(), getPlayer2Score());
        } else {
            System.out.println("No match.");
        }
    }

    public PlayingCard getHiddenCard(char row, int column) {
        int numRow = Character.getNumericValue(row) - 9;
        PlayingCard card = (PlayingCard) grid.getCard(numRow - 1, column - 1);
        return card;
    }

    public boolean isCardGridEmpty() {
        return grid.getSize() == this.pastPlayerCardChoice.size();
    }

    public void displayGameGrid() {
        for (int i = 0; i < grid.getNumOfRows() + 1; i++) {
            for (int j = 0; j < grid.getNumOfColumns() + 1; j++) {
                if (i >= 1 && j >= 1 && pastPlayerCardChoice.contains(grid.getCard(i - 1, j - 1))) {
                    System.out.print("--\t");
                } else {
                    System.out.print(gameGrid()[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    @Override
    public void play() {
        do {
            displayRules();
            boolean valid = false;
            grid.shuffle();
            while (p1.getName() == "" && p2.getName() == "") {
                System.out.println("Please enter Player 1's name: ");
                p1.setName(in.nextLine());
                System.out.println("Please enter Player 2's name: ");
                p2.setName((in.nextLine()));
            }

            do {
                displayGameGrid();
                System.out.println(p1.getName() + "'s Turn");
                playerTurn(p1);
                displayGameGrid();
                System.out.println(p2.getName() + "'s Turn");
                playerTurn(p2);
            } while (isCardGridEmpty() == false);

            declareWinner();
            System.out.println();
            do {
                System.out.println("\nDo you want to play again? Type Y or N");
                playAgain = in.next();
                if (playAgain.equalsIgnoreCase("Y") || playAgain.equalsIgnoreCase("N")) {
                    valid = true;
                } else {
                    System.out.println("Error: Must enter Y or N");
                    valid = false;
                }
            } while (valid == false);
        } while (playAgain.equalsIgnoreCase("Y"));
    }

    static void displayRules() {
        System.out.println("--Ladies and Gents let us present to you a marvel of software development... The card game Memory!!!--\n");
        System.out.println("--* The rules are as follows *--");
        System.out.println("1. The deck of cards is shuffled and dealt out in a 4x13 grid. A,B,C & D will be our rows. 1-13 will be our columns.");
        System.out.println("2. The players each take turns flipping the cards over.");
        System.out.println("3. If the cards are both the same value and color then it's a match.");
        System.out.println("4. If the cards are not a match, then its the opponents turn.");
    }

    @Override
    public void declareWinner() {
        if (this.player1Score > this.player2Score) {
            System.out.println(this.p1.getName() + " is the winner!");
        } else if (this.player1Score < this.player2Score) {
            System.out.println(this.p2.getName() + " is the winner!");
        } else {
            System.out.println("The game is a tie!");
        }
    }
}
