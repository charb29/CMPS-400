package GoBang;

import java.util.*;

public class GoBang {
    public static void main(String[] args) {
        GoBangGame game = new GoBangGame();
        game.play();
    }

    public static class GoBangGame {
        private static final int SIZE = 15;
        private static final int WIN_CONDITION = 5;
        private static final char EMPTY = '.';
        private static final char PLAYER_1 = 'X';
        private static final char PLAYER_2 = 'O';
    
        private final char[][] board = new char[SIZE][SIZE];
    
        public GoBangGame() {
            this.init();
        }
    
        public void play() {
            Scanner scanner = new Scanner(System.in);
            char currentPlayer = PLAYER_1;
    
            while (true) {
                printBoard();
                System.out.print("Player " + currentPlayer + "'s turn. Enter row and column (0-based): ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();
    
                if (!isValidMove(row, col)) {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }
    
                board[row][col] = currentPlayer;
    
                if (hasWon(row, col, currentPlayer)) {
                    printBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                }
    
                currentPlayer = (currentPlayer == PLAYER_1) ? PLAYER_2 : PLAYER_1;
            }
    
            scanner.close();
        }
    
        private boolean isValidMove(int row, int col) {
            return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == EMPTY;
        }
    
        private boolean hasWon(int row, int col, char player) {
            return countInDirection(row, col, 0, 1, player) + countInDirection(row, col, 0, -1, player) - 1 >= WIN_CONDITION ||
                   countInDirection(row, col, 1, 0, player) + countInDirection(row, col, -1, 0, player) - 1 >= WIN_CONDITION ||
                   countInDirection(row, col, 1, 1, player) + countInDirection(row, col, -1, -1, player) - 1 >= WIN_CONDITION ||
                   countInDirection(row, col, 1, -1, player) + countInDirection(row, col, -1, 1, player) - 1 >= WIN_CONDITION;
        }
    
        private int countInDirection(int row, int col, int dRow, int dCol, char player) {
            int count = 0;
            int r = row, c = col;
            while (r >= 0 && r < SIZE && c >= 0 && c < SIZE && board[r][c] == player) {
                count++;
                r += dRow;
                c += dCol;
            }
            return count;
        }
    
        private void printBoard() {
            for (char[] row : board) {
                for (char c : row) {
                    System.out.print(c + " ");
                }
                System.out.println();
            }
        }
    
        private void init() {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    board[i][j] = EMPTY;
                }
            }
        }
    }
}