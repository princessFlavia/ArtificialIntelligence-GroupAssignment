

import matplotlib.pyplot as plt

class City:
    def __init__(self, x, y):
        self.x = x
        self.y = y

class RouteVisualizer:
    def __init__(self, city_set):
        self.city_set = city_set

    def plot_route(self, route, title):
        x = [city.x for city in route]
        y = [city.y for city in route]
        
        plt.figure(figsize=(8, 6))
        plt.plot(x, y, 'ro-')
        for i, city in enumerate(route):
            plt.text(city.x, city.y, str(i+1), fontsize=12, ha='center', va='center')
        plt.title(title)
        plt.xlabel('X')
        plt.ylabel('Y')
        plt.grid(True)
        plt.show()

def generate_initial_route(city_set):
    return city_set

def main():
    
    city_set = [City(1, 2), City(3, 5), City(7, 1), City(9, 6), City(12, 3)]

    
    visualizer = RouteVisualizer(city_set)

    
    initial_route = generate_initial_route(city_set)

    
    visualizer.plot_route(initial_route, 'Initial Route')

    

    
    intermediate_route = initial_route

    
    visualizer.plot_route(intermediate_route, 'Intermediate Route')

    
    final_route = initial_route

    
    visualizer.plot_route(final_route, 'Final Route')

if __name__ == "__main__":
    main()
