# Anthony Wittemann
# 9/24/2013
# Algorithm Workbench 2

def main():
    keepGoing = 0;
    while(keepGoing == 0):
        num1 = float(input('Enter a number'));
        num2 = float(input('Enter another number'));
        sum0 = num1 + num2;
        print('Sum: ', sum0);
        keepGoing = int(input('If you would like to continue enter 0'));

main();
