# Anthony Wittemann
# 11/7/13
# Programming Exercises 1,2,3

# 1 - Initials

def main():
    name = input('Enter your first, middle and last names separated by spaces: ')
    initialsList = name.split()
    fI = initialsList[0][0] + '.'
    mI = initialsList[1][0] + '.'
    lI = initialsList[2][0] + '.'
    print(fI.upper(), mI.upper(), lI.upper())
    
main()