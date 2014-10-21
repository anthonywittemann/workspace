# Anthony Wittemann
# 10/17/13
# Prog. Ex. 1-4

#2 - Lottery Number Generator

import random

def main():
    nums = [0]*7
    n = 0
    while n < len(nums):
        nums[n] = random.randint(0,9)
        n +=1

    u = 0    
    while u < len(nums):
        print(nums[u], ' ', end='')
        u +=1

main()
