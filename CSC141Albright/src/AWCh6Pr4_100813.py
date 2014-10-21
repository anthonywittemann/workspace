# Anthony Wittemann
# 10/08/13
# Programming Exercises 4 - Falling Distance


def main():
    for r in range(1,11):
        distance = falling_distance(r)
        print('Distance fallen: ', format(distance,'.2f'), 'm')

def falling_distance(time):
    return 9.8 * time**2 * .5

main()
