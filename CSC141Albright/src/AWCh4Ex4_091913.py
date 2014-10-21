# Anthony Wittemann
# 9/19/13
# Ch4 Ex 4 Magic Dates

def main():
    day = int(input('What day of the month is it?'));
    month = int(input('What month is it? Enter as a numeric value.'));
    year = int(input('What are the last 2 digits of the year?'));

    if((day * month) == year):
        print('The date is magical!');
    else:
        print('No magic today :(');


main();
