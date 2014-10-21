# Anthony Wittemann
# 10/03/13
# Algorithm Workbenth 1-5 pg. 234

import random

def main():
    # 1
    rand = random.randint(1,100);

    #2
    half(random.randint(1,100));

    #3
    result = cube(4);

    #4
    times_ten(random.randint(1,100));

    #5
    firstName = get_first_name();

def half(num):
    return num * .50;

def cube(num1):
    return num1**3;

def times_ten(num2):
    return num2 * 10;

def get_first_name():
    firstN = input('What is your name?');
    return firstN;

main();
