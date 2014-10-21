# Anthony Wittemann
# 10/08/13
# Programming Exercises 5 - Kinetic Energy


def main():
    mass = float(input('''What is the object's mass in kilograms?'''))
    velocity = float(input('''What is the object's velocity in m/s?'''))
    ke = kinetic_energy(mass, velocity)
    print('''The object's kinetic energy is ''', ke, 'joules')

def kinetic_energy(mass, velocity):
    return .5 * mass * velocity**2

main()
