import random

class Suburb:
    def __init__(self, name):
        self.name = name
        self.distances = {}

    def add_distance(self, to_suburb, distance):
        self.distances[to_suburb.name] = distance

class TSPSolver:
    def __init__(self, suburbs):
        self.suburbs = suburbs

    def total_distance(self, route):
        total = 0
        for i in range(len(route) - 1):
            suburb1 = route[i]
            suburb2 = route[i + 1]
            total += suburb1.distances[suburb2.name]
# Add distance back to starting suburb
        total += route[-1].distances[route[0].name] 
        return total

    def generate_random_route(self):
        route = self.suburbs.copy()
        random.shuffle(route)
        return route

    def get_neighbors(self, route):
        neighbors = []
        for i in range(len(route)):
            for j in range(i + 1, len(route)):
                neighbor = route.copy()
                neighbor[i], neighbor[j] = neighbor[j], neighbor[i]
                neighbors.append(neighbor)
        return neighbors

    def hill_climbing(self, start_route, max_iterations):
        current_route = start_route
        current_distance = self.total_distance(current_route)

        for _ in range(max_iterations):
            neighbors = self.get_neighbors(current_route)
            next_route = min(neighbors, key=lambda route: self.total_distance(route))
            next_distance = self.total_distance(next_route)

            if next_distance >= current_distance:
                break  # Local optimum reached
            else:
                current_route = next_route
                current_distance = next_distance

        return current_route, current_distance

# DATA STRUCTURE TO REPRESENT DIFFERENT SUBURBS AND THE DISTANCES BETWEEN THEM:

# Create suburb objects
dorado_park = Suburb("Dorado Park")
khomasdal = Suburb("Khomasdal")
katutura = Suburb("Katutura")
eros = Suburb("Eros")
klein_windhoek = Suburb("Klein Windhoek")

# Add distances between suburbs
dorado_park.add_distance(khomasdal, 10)
dorado_park.add_distance(katutura, 20)
dorado_park.add_distance(eros, 15)
dorado_park.add_distance(klein_windhoek, 12)

khomasdal.add_distance(dorado_park, 10) 
khomasdal.add_distance(katutura, 6)
khomasdal.add_distance(eros, 14)
khomasdal.add_distance(klein_windhoek, 18)

katutura.add_distance(dorado_park, 20)  
katutura.add_distance(khomasdal, 6)
katutura.add_distance(eros, 25)
katutura.add_distance(klein_windhoek, 30)

eros.add_distance(dorado_park, 15)  
eros.add_distance(khomasdal, 14)
eros.add_distance(katutura, 25)
eros.add_distance(klein_windhoek, 2)

klein_windhoek.add_distance(dorado_park, 12)  
klein_windhoek.add_distance(khomasdal, 18)
klein_windhoek.add_distance(katutura, 30)
klein_windhoek.add_distance(eros, 2)

# Create a TSPSolver instance with suburbs:

solver = TSPSolver([dorado_park, khomasdal, katutura, eros, klein_windhoek])
start_route = solver.generate_random_route()
max_iterations = 1000
best_route, best_distance = solver.hill_climbing(start_route, max_iterations)

print("Best Route:")
for suburb in best_route:
    print(suburb.name)
print("Total Distance:", best_distance)
