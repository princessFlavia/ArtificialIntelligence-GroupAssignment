from typing import List, Tuple
from queue import PriorityQueue

class AStarSearch:
    def __init__(self, grid_map: List[List[int]], start_position: Tuple[int, int], target_position: Tuple[int, int]):
        self.grid_map = grid_map
        self.num_rows = len(grid_map)
        self.num_cols = len(grid_map[0])
        self.start_position = start_position
        self.target_position = target_position

    class Node:
        def __init__(self, position: Tuple[int, int], g_score: float, f_score: float, parent):
            self.position = position
            self.g_score = g_score
            self.f_score = f_score
            self.parent = parent

        def __lt__(self, other):
            return self.f_score < other.f_score

    def find_path(self) -> List[Tuple[int, int]]:
        closed_set = set()
        open_set = PriorityQueue()
        start_node = self.Node(self.start_position, 0, self.heuristic(self.start_position), None)
        open_set.put(start_node)

        while not open_set.empty():
            current = open_set.get()

            if current.position == self.target_position:
                return self.reconstruct_path(current)

            closed_set.add(current.position)

            for neighbor in self.generate_neighbors(current.position):
                if neighbor in closed_set:
                    continue

                tentative_g_score = current.g_score + self.cost(current.position, neighbor)
                neighbor_node = self.Node(neighbor, tentative_g_score, tentative_g_score + self.heuristic(neighbor), current)
                open_set.put(neighbor_node)

        return []

    def reconstruct_path(self, current: 'Node') -> List[Tuple[int, int]]:
        path = []
        while current is not None:
            path.insert(0, current.position)
            current = current.parent
        return path

    def generate_neighbors(self, position: Tuple[int, int]) -> List[Tuple[int, int]]:
        neighbors = []
        directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]  # Up, Down, Left, Right

        for dx, dy in directions:
            new_row, new_col = position[0] + dx, position[1] + dy
            if self.is_valid_position(new_row, new_col):
                neighbors.append((new_row, new_col))

        return neighbors

    def is_valid_position(self, row: int, col: int) -> bool:
        return 0 <= row < self.num_rows and 0 <= col < self.num_cols and self.grid_map[row][col] == 0

    def cost(self, current: Tuple[int, int], next: Tuple[int, int]) -> float:
        return 1.0

    def heuristic(self, position: Tuple[int, int]) -> float:
        return abs(position[0] - self.target_position[0]) + abs(position[1] - self.target_position[1])


# Example usage
if __name__ == "__main__":
    grid_map = [
        [0, 0, 1, 0, 0],
        [0, 1, 1, 1, 0],
        [0, 0, 0, 0, 0],
        [0, 1, 1, 1, 0],
        [0, 0, 1, 0, 0]
    ]

    start_position = (0, 0)
    target_position = (4, 4)

    astar = AStarSearch(grid_map, start_position, target_position)
    path = astar.find_path()

    if path:
        print("Path found:")
        for point in path:
            print(point)
    else:
        print("No path found!")
