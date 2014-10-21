# Anthony Wittemann
# 10/29/13
# Chapter 7 Program 1 - 8
# Program 5 - Sum of Numbers

def main():
    try:
        nums = open('numbers.txt', 'r')
    except:
        print('''Unable to open file 'numbers.txt' ''')
    
    try:
        line = nums.readline()
    except IOError as IOE:
        print(IOE)
    
    the_sum = 0
    
    while line != '':
        the_sum += int(line)
        
        try:
            line = nums.readline()
        except IOError as IOE:
            print(IOE)
            
    nums.close()
    
    print('There sum is: ', the_sum)   
    
main()