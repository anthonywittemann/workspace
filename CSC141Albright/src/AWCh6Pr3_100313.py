# Anthony Wittemann
# 10/03/13
# Programming Exercises 3


def main():
    num1 = int(input('Could you give me a number please?'))
    num2 = int(input('And another?'))
    theMax = maximum(num1,num2)
    print('The max is ', theMax)

    
def maximum(num1,num2):
    if(num1 > num2):
        return num1
    return num2

main()
