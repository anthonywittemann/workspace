# Anthony Wittemann
# 10/08/13
# Programming Exercises 6 - Test Average and Grade


def main():
    ts1 = int(input('Enter your 1st test score'))
    ts2 = int(input('Enter your 2nd test score'))
    ts3 = int(input('Enter your 3rd test score'))
    ts4 = int(input('Enter your 4th test score'))
    ts5 = int(input('Enter your 5th test score'))
    avg = calc_average(ts1, ts2, ts3, ts4, ts5)
    grade = determine_grade(avg)
    print('You have earned a ', grade)


def calc_average(ts1, ts2, ts3, ts4, ts5):
    return (ts1+ ts2+ ts3+ ts4+ ts5)/5.0

def determine_grade(testScore):
    if(testScore < 60):
        return 'F'
    elif(testScore < 70):
        return 'D'
    elif(testScore < 80):
        return 'C'
    elif(testScore < 90):
        return 'B'
    return 'A'

main()
