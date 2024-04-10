import java.awt.Point;
import java.util.ArrayList;

public class heuristic {
    public double cost(Point current, Point next, double[] terrainMap) {
        return 1.0;
    }

    public double heuristic(Point current, Point target, double[][] obstaclesMap) {

        double manhattanDistance = Math.abs(current.x - target.x) + Math.abs(current.y - target.y);

        double obstaclePenalty = calculateObstaclePenalty(current, target, obstacleMap);

        return manhattanDistance = obstaclePenalty;


    }

    private double calculateObstaclePenalty(Point current, Point target, double[][] obstacleMap) {
        // Implementation specific to the problem domain
        // Here, let's assume we calculate the penalty based on the number of obstacle cells between current and target

        int minX = Math.min(current.x, target.x);
        int maxX = Math.max(current.x, target.x);
        int minY = Math.min(current.y, target.y);
        int maxY = Math.max(current.y, target.y);

        int obstacleCount = 0;
        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                if (obstacleMap[i][j] == 1) {
                    obstacleCount++;
                }
            }
        }

        // Penalty is proportional to obstacle count
        return obstacleCount * 0.5; // Adjust the penalty factor as needed
    }

    // Example usage
    public static void main(String[] args) {
        // Example terrain and obstacle maps
        double[][] terrainMap = {
                {1.0, 1.0, 1.0},
                {1.0, 0.8, 0.9},
                {1.0, 1.0, 1.0}
        };

        double[][] obstacleMap = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };

        Point current = new Point(0, 0);
        Point target = new Point(2, 2);

        heuristic problem = new heuristic();
        double cost = problem.cost(current, target, terrainMap);
        double heuristic = problem.heuristic(current, target, obstacleMap);

        System.out.println("Cost: " + cost);
        System.out.println("Heuristic: " + heuristic);
    }
}

