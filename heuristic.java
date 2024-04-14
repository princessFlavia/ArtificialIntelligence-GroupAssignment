from typing import List, Tuple


class Heuristic:
    def cost(self, current: Tuple[int, int], next: Tuple[int, int], terrain_map: List[List[float]]) -> float:
        return 1.0

    def heuristic(self, current: Tuple[int, int], target: Tuple[int, int], obstacle_map: List[List[float]]) -> float:
        manhattan_distance = abs(current[0] - target[0]) + abs(current[1] - target[1])
        obstacle_penalty = self.calculate_obstacle_penalty(current, target, obstacle_map)
        return manhattan_distance + obstacle_penalty

    def calculate_obstacle_penalty(self, current: Tuple[int, int], target: Tuple[int, int], obstacle_map: List[List[float]]) -> float:
        # Here, let's assume we calculate the penalty based on the number of obstacle cells between current and target
        min_x = min(current[0], target[0])
        max_x = max(current[0], target[0])
        min_y = min(current[1], target[1])
        max_y = max(current[1], target[1])

        obstacle_count = sum(1 for i in range(min_x, max_x + 1) for j in range(min_y, max_y + 1) if obstacle_map[i][j] == 1)
        # Penalty is proportional to obstacle count
        return obstacle_count * 0.5  # Adjust the penalty factor as needed


# Example usage
if __name__ == "__main__":
    # Example terrain and obstacle maps
    terrain_map = [
        [1.0, 1.0, 1.0],
        [1.0, 0.8, 0.9],
        [1.0, 1.0, 1.0]
    ]

    obstacle_map = [
        [0, 0, 0],
        [0, 1, 0],
        [0, 0, 0]
    ]

    current = (0, 0)
    target = (2, 2)

    problem = Heuristic()
    cost = problem.cost(current, target, terrain_map)
    heuristic_value = problem.heuristic(current, target, obstacle_map)

    print("Cost:", cost)
    print("Heuristic:", heuristic_value)
