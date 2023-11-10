import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class SnakeGame {

    public static void main(String[] args) {
        new SnakeGame().startGame();
    }

    private static final int BOARD_SIZE = 10;
    private static final char EMPTY_CELL = '.';
    private static final char SNAKE_CELL = 'O';
    private static final char FOOD_CELL = 'F';

    private char[][] board;
    private LinkedList<Point> snake;
    private Point food;
    private boolean isGameOver;

    public SnakeGame() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        snake = new LinkedList<>();
        isGameOver = false;
    }

    public void startGame() {
        initializeBoard();
        initializeSnake();
        placeFood();

        Scanner scanner = new Scanner(System.in);

        while (!isGameOver) {
            printBoard();
            System.out.print("Enter direction (W/A/S/D): ");
            char direction = scanner.next().toUpperCase().charAt(0);
            moveSnake(direction);
            checkCollision();
        }

        System.out.println("Game Over! Your score: " + (snake.size() - 1));
        scanner.close();
    }

    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
    }

    private void initializeSnake() {
        int startX = BOARD_SIZE / 2;
        int startY = BOARD_SIZE / 2;
        Point head = new Point(startX, startY);
        snake.add(head);
        board[startX][startY] = SNAKE_CELL;
    }

    private void placeFood() {
        Random random = new Random();
        int x, y;

        do {
            x = random.nextInt(BOARD_SIZE);
            y = random.nextInt(BOARD_SIZE);
        } while (board[x][y] == SNAKE_CELL);

        food = new Point(x, y);
        board[x][y] = FOOD_CELL;
    }

    private void printBoard() {
        System.out.println("Score: " + (snake.size() - 1));

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void moveSnake(char direction) {
        Point head = snake.peek();
        int newX = head.x;
        int newY = head.y;

        switch (direction) {
            case 'W':
                newX--;
                break;
            case 'A':
                newY--;
                break;
            case 'S':
                newX++;
                break;
            case 'D':
                newY++;
                break;
        }

        // Move the snake
        Point newHead = new Point(newX, newY);
        snake.add(newHead);
        board[newX][newY] = SNAKE_CELL;

        // Check if the snake ate the food
        if (newHead.equals(food)) {
            placeFood();
        } else {
            // If not, remove the tail
            Point tail = snake.poll();
            board[tail.x][tail.y] = EMPTY_CELL;
        }
    }

    private void checkCollision() {
        Point head = snake.peek();

        // Check if the snake hits the walls
        if (head.x < 0 || head.x >= BOARD_SIZE || head.y < 0 || head.y >= BOARD_SIZE) {
            isGameOver = true;
            return;
        }

        // Check if the snake bites itself
        for (Point bodyPart : snake) {
            if (bodyPart.equals(head) && !bodyPart.equals(snake.getLast())) {
                isGameOver = true;
                return;
            }
        }
    }

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Point other) {
            return this.x == other.x && this.y == other.y;
        }
    }
}
