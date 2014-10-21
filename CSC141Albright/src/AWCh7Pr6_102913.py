# Anthony Wittemann
# 10/29/13
# Chapter 7 Program 1 - 8
# Program 6 - Average of Numbers

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
    count = 0
    while line != '':
        the_sum += int(line)
        
        try:
            line = nums.readline()
        except IOError as IOE:
            print(IOE)
        
        count += 1
    
    nums.close()
    
    avg = the_sum / count * 1.0
    print('There average is: ', avg)   
    
main()
