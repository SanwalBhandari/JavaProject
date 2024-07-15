
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Snake_And_Ladder {

    private static final int WINNING_POSITION = 100;
    private Map<Integer, Integer> snakes = new HashMap<>();
    private Map<Integer, Integer> ladders = new HashMap<>();
    private Random dice = new Random();

    public Snake_And_Ladder() {
        initializeSnakesAndLadders();
    }

    private void initializeSnakesAndLadders() {
        // Initialize snakes
        snakes.put(16, 6);
        snakes.put(47, 26);
        snakes.put(49, 11);
        snakes.put(56, 53);
        snakes.put(62, 19);
        snakes.put(64, 60);
        snakes.put(87, 24);
        snakes.put(93, 73);
        snakes.put(95, 75);
        snakes.put(98, 78);

        // Initialize ladders
        ladders.put(1, 38);
        ladders.put(4, 14);
        ladders.put(9, 31);
        ladders.put(21, 42);
        ladders.put(28, 84);
        ladders.put(36, 44);
        ladders.put(51, 67);
        ladders.put(71, 91);
        ladders.put(80, 100);
    }

 public void start() {
        Scanner scanner = new Scanner(System.in);
        int player1Position = 0;
        int player2Position = 0;
        boolean isPlayer1Turn = true;

        System.out.println("Welcome to Snake and Ladder Game!");
        System.out.println("Player 1 starts first.");

        while (player1Position < WINNING_POSITION && player2Position < WINNING_POSITION) {
            
            printBoard(player1Position, player2Position);
            System.out.println((isPlayer1Turn ? "Player 1" : "Player 2") + ", press 'r' to roll the dice.");
            String input = scanner.next();

 if (input.equalsIgnoreCase("r")) {
                int diceValue = rollDice();
                System.out.println("Dice rolled: " + diceValue);

                if (isPlayer1Turn) {
                    player1Position = movePlayer(player1Position, diceValue);
                    System.out.println("Player 1 is now at position: " + player1Position);
                    if (player1Position == WINNING_POSITION) {
                        System.out.println("Player 1 wins!");
                        break;
                    }
                } else {
                    player2Position = movePlayer(player2Position, diceValue);
                    System.out.println("Player 2 is now at position: " + player2Position);
                    if (player2Position == WINNING_POSITION) {
                        System.out.println("Player 2 wins!");
                        break;
                    }
                }

                isPlayer1Turn = !isPlayer1Turn;
            } else {
                System.out.println("Invalid input. Please press 'r' to roll the dice.");
            }
        }}

private int movePlayer(int currentPosition, int diceValue) {
        int newPosition = currentPosition + diceValue;

        if (newPosition > WINNING_POSITION) {
            return currentPosition; // Player cannot move beyond the winning position
        }

        if (snakes.containsKey(newPosition)) {
            System.out.println("Oh no! Bitten by a snake!");
            newPosition = snakes.get(newPosition);
        } else if (ladders.containsKey(newPosition)) {
            System.out.println("Yay! Climbed a ladder!");
            newPosition = ladders.get(newPosition);
        }

        return newPosition;
    }


private void printBoard(int player1Position, int player2Position) {
        for (int i = 100; i > 0; i -= 10) {
            for (int j = 0; j < 10; j++) {
                int position = (i - j);

                // Adjust for reverse order every other row
                if (((i / 10) % 2) == 0) {
                    position = (i - 9) + j;
                }

                if (position == player1Position && position == player2Position) {
                    System.out.print("[P1&P2]");
                } else if (position == player1Position) {
                    System.out.print("[P1]");
                } else if (position == player2Position) {
                    System.out.print("[P2]");
                } else if (snakes.containsKey(position)) {
                    System.out.print("[S]");
                } else if (ladders.containsKey(position)) {
                    System.out.print("[L]");
                } else {
                    System.out.print("[" + position + "]");
                }
            }
            System.out.println();
        }
    }

private int rollDice() {
        return dice.nextInt(6) + 1;
    }


 public static void main(String[] args) {
        Snake_And_Ladder game = new Snake_And_Ladder();
        game.start();
    }}
