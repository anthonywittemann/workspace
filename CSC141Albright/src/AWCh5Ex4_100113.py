# Anthony Wittemann
# 10/01/13
# 4 Distance Traveled

def main():
    speed = float(input('What is the speed in mph?'));
    hoursTraveled = int(input('How many hours have been traveled?'));
    for h in range(hoursTraveled):
        distance = speed * h;
        print('Hours traveled: ', h);
        print('Miles traveled: ', distance);

main();
