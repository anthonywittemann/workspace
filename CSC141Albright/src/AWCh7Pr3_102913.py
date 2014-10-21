# Anthony Wittemann
# 10/29/13
# Chapter 7 Program 1 - 8
# Program 3 - Line Numbers

import sys

def main():
    isValid = False
    count = 0
    while isValid == False:
        file_name = input('Enter the file name: ')
        try:
            the_file = open(file_name, 'r')
        except:
            print('Unable to open file: ', file_name)
            count += 1
        else:
            isValid = True
            break
        if count > 4:
            print('You have failed to enter a valid file. The program has quit.')
            sys.exit(0)
    
    try:
        line = the_file.readline()
    except IOError as IOE:
        print(IOE)
    
    line_num = 1
    while line != '':
        line = line.rstrip('\n')
        print(line_num, ': ', line)
        line_num += 1
        
        try:
            line = the_file.readline()
        except IOError as IOE:
            print(IOE)
            
    the_file.close()
        
    
main()
