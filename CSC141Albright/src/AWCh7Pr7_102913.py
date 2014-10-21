# Anthony Wittemann
# 10/29/13
# Chapter 7 Program 1 - 8
# Program 7 - Random Number File Writer

import random

def main():
    nums = open('numbers.txt', 'w')
    lines = int(input('How many random numbers would you like the list to contain?'))
    for l in range(lines):
        num = random.randint(1,100)
        nums.write(str(num) + '\n')  
    nums.close()
    
main()