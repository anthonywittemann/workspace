# Anthony Wittemann
# 9/19/13
# Ch4 Ex 2 Areas of Rectangles

def main():
    length1 = float(input('What is the length of the 1st rectangle?'));
    width1 = float(input('What is the width of the 1st rectangle?'));
    length2 = float(input('What is the length of the 2nd rectangle?'));
    width2 = float(input('What is the width of the 2nd rectangle?'));

    area1 = length1 * width1;
    area2 = length2 * width2;

    if(area1 > area2):
        print('Rectangle 1 has the greater area.');
    else:
        print('Rectangle 2 has the greater area.');

main();
