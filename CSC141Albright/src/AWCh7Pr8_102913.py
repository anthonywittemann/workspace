# Anthony Wittemann
# 10/29/13
# Chapter 7 Program 1 - 8
# Program 8 - Random Number File Reader

def main():
    nums = open('numbers.txt', 'r')
    line = int(nums.readline())
    total = 0
    numRandomNums = 0
    while line != '':
        numRandomNums += 1
        total += line   
    nums.close()
    print('Number of random numbers in file: ', numRandomNums)
    print('Total of random numbers in file: ', total)  
    
main()