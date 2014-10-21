# Anthony Wittemann
# 10/10/13
# 7 - Odd/Even Counter

import random

def main():
    evenCount = 0
    oddCount = 0
    for r in range(100):
        n = random.randint(0,100)
        if(n % 2 == 0):
            evenCount += 1
        else:
            oddCount += 1
    print('Even Numbers: ', evenCount, ' Odd Numbers: ', oddCount)

main()
