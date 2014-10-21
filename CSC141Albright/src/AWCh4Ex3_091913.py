# Anthony Wittemann
# 9/19/13
# Ch4 Ex 3 Mass and Weight

def main():
    mass = float(input('''What is the object's mass in kilograms?'''));
    weight = 9.8 * mass;
    if(weight > 1000):
        print('Your object is too heavy.');
    elif(weight < 10):
        print('Your object is too light.');


main();
