class RobotEnvironment:
    def __init__(self, numRows, numCols):
        self.numRows = numRows
        self.numCols = numCols
        self.gridMap = [[0] * numCols for _ in range(numRows)]
        self.robotPosition = [0, 0]  # Initialize the robot's position at the top-left corner

    def update_robot_position(self, row, col):
        if self.is_valid_position(row, col):
            self.robotPosition = [row, col]
        else:
            print("Invalid position!")

    def is_valid_position(self, row, col):
        return 0 <= row < self.numRows and 0 <= col < self.numCols

    def move_robot(self, action):
        newRow, newCol = self.robotPosition

        if action == "forward":
            newRow += 1
        elif action == "backward":
            newRow -= 1
        elif action == "left":
            newCol -= 1
        elif action == "right":
            newCol += 1
        else:
            print("Invalid action!")
            return

        if self.is_valid_position(newRow, newCol):
            self.update_robot_position(newRow, newCol)
            print(f"Robot moved to: ({newRow}, {newCol})")
        else:
            print("Cannot move in that direction!")


# Example usage
if __name__ == "__main__":
    numRows = 10
    numCols = 10

    environment = RobotEnvironment(numRows, numCols)
    environment.move_robot("forward")
    environment.move_robot("right")
    environment.move_robot("backward")
    environment.move_robot("left")
