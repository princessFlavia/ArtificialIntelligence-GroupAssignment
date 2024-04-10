import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStarSearch {

    // Define the grid-based map representing the environment
    private int[][] gridMap;

    // Define the size of the grid-based map
    private int numRows;
    private int numCols;

    // Define the robot's starting position and target position
    private Point startPosition;
    private Point targetPosition;

    // Constructor to initialize the environment
    public AStarSearch(int[][] gridMap, Point startPosition, Point targetPosition) {
        this.gridMap = gridMap;
        this.numRows = gridMap.length;
        this.numCols = gridMap[0].length;
        this.startPosition = startPosition;
        this.targetPosition = targetPosition;
    }

    // Define a node class for A* search
    private class Node {
        Point position;
        double gScore;
        double fScore;
        Node parent;

        public Node(Point position, double gScore, double fScore, Node parent) {
            this.position = position;
            this.gScore = gScore;
            this.fScore = fScore;
            this.parent = parent;
        }
    }

    // Implement A* search algorithm
    public ArrayList<Point> findPath() {
        // Initialize open and closed sets
        HashSet<Point> closedSet = new HashSet<>();
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingDouble(node -> node.fScore));

        // Initialize the start node
        Node startNode = new Node(startPosition, 0, heuristic(startPosition), null);
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            // Get the node with the lowest fScore from the open set
            Node current = openSet.poll();

            // Check if the current node is the target position
            if (current.position.equals(targetPosition)) {
                // Reconstruct the path
                return reconstructPath(current);
            }

            // Add the current node to the closed set
            closedSet.add(current.position);

            // Generate neighbors of the current node
            ArrayList<Point> neighbors = generateNeighbors(current.position);

            // Iterate through neighbors
            for (Point neighbor : neighbors) {
                if (closedSet.contains(neighbor)) {
                    continue; // Skip if already evaluated
                }

                // Calculate tentative gScore
                double tentativeGScore = current.gScore + cost(current.position, neighbor);

                // Check if neighbor is not in the open set or has a lower gScore
                Node neighborNode = new Node(neighbor, tentativeGScore, tentativeGScore + heuristic(neighbor), current);
                if (!openSet.contains(neighborNode) || tentativeGScore < neighborNode.gScore) {
                    openSet.add(neighborNode);
                }
            }
        }

        // If no path found, return null
        return null;
    }

    // Helper method to reconstruct the path from the current node to the start node
    private ArrayList<Point> reconstructPath(Node current) {
        ArrayList<Point> path = new ArrayList<>();
        while (current != null) {
            path.add(0, current.position);
            current = current.parent;
        }
        return path;
    }

    // Method to generate neighbors of a given position
    private ArrayList<Point> generateNeighbors(Point position) {
        ArrayList<Point> neighbors = new ArrayList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right

        for (int[] dir : directions) {
            int newRow = position.x + dir[0];
            int newCol = position.y + dir[1];
            if (isValidPosition(newRow, newCol)) {
                neighbors.add(new Point(newRow, newCol));
            }
        }

        return neighbors;
    }

    // Method to check if a position is valid within the grid
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols && gridMap[row][col] == 0;
    }

    // Define the cost function (unit cost for each movement)
    private double cost(Point current, Point next) {
        return 1.0;
    }

    // Define the heuristic function (Manhattan distance)
    private double heuristic(Point position) {
        return Math.abs(position.x - targetPosition.x) + Math.abs(position.y - targetPosition.y);
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example grid map with obstacles (0 for free cell, 1 for obstacle)
        int[][] gridMap = {
                {0, 0, 1, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 1, 0, 0}
        };

        Point startPosition = new Point(0, 0);
        Point targetPosition = new Point(4, 4);

        AStarSearch astar = new AStarSearch(gridMap, startPosition, targetPosition);
        ArrayList<Point> path = astar.findPath();

        if (path != null) {
            System.out.println("Path found:");
            for (Point point : path) {
                System.out.println("(" + point.x + ", " + point.y + ")");
            }
        } else {
            System.out.println("No path found!");
        }
    }
}