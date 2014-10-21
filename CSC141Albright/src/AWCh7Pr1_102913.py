# Anthony Wittemann
# 10/29/13
# Chapter 7 Program 1 - 8
# Program 1 - File Display

def main():
    numbers = open('numbers.txt', 'r')
    num = int(numbers.readline())
    while num != '':
        print(num)
        num = int(numbers.readline())
    numbers.close()
        
    
main()