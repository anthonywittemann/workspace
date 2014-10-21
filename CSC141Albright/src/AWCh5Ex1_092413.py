# Anthony Wittemann
# 9/24/2013
# Programming Exercises 1 - Bug Collector

def main():
    totBugs = 0;
    for r in range(7):
        bugs = int(input('How many bugs have you collected today?'));
        totBugs = totBugs + bugs;

    print('Total Bugs: ', totBugs);


main();
