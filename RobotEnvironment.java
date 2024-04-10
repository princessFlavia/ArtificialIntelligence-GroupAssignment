import java.awt.Point;

public class RobotEnvironment {

    // Define the size of the grid-based map
    private int numRows;
    private int numCols;

    // Define the grid-based map representing the environment
    private int[][] gridMap;

    // Define the robot's current position
    private Point robotPosition;

    // Constructor to initialize the environment
    public RobotEnvironment(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.gridMap = new int[numRows][numCols];
        this.robotPosition = new Point(0, 0); // Initialize the robot's position at the top-left corner
    }

    // Method to update the robot's position
    public void updateRobotPosition(int row, int col) {
        if (isValidPosition(row, col)) {
            robotPosition.setLocation(row, col);
        } else {
            System.out.println("Invalid position!");
        }
    }

    // Method to check if a position is valid within the grid
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols;
    }

    // Method to perform a move action: forward, backward, left, right
    public void moveRobot(String action) {
        int newRow = robotPosition.x;
        int newCol = robotPosition.y;

        switch (action) {
            case "forward":
                newRow--;
                break;
            case "backward":
                newRow++;
                break;
            case "left":
                newCol--;
                break;
            case "right":
                newCol++;
                break;
            default:
                System.out.println("Invalid action!");
                return;
        }

        // Check if the new position is valid and update the robot's position
        if (isValidPosition(newRow, newCol)) {
            updateRobotPosition(newRow, newCol);
            System.out.println("Robot moved to: (" + newRow + ", " + newCol + ")");
        } else {
            System.out.println("Cannot move in that direction!");
        }
    }

    // Example usage
    public static void main(String[] args) {
        int numRows = 10;
        int numCols = 10;

        RobotEnvironment environment = new RobotEnvironment(numRows, numCols);
        environment.moveRobot("forward");
        environment.moveRobot("right");
        environment.moveRobot("backward");
        environment.moveRobot("left");
    }
}
