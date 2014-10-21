# Anthony Wittemann
# 11/7/13
# Programming Exercises 1,2,3

# 2 - Sum of Digits in a String

def main():
    digits = input('Enter a set a digits with nothing separating them: ')
    digSum = 0
    for d in digits:
        digSum += int(d)
    print('The sum of the digits is: ', digSum)
    
main()