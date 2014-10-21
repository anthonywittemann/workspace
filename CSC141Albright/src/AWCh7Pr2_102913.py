# Anthony Wittemann
# 10/29/13
# Chapter 7 Program 1 - 8
# Program 2 - File Head Display

def main():
    file_name = input('Enter the file name ')
    the_file = open(file_name, 'r')
    line = the_file.readline()
    count = 0
    while line != '' and count < 5:
        line = line.rstrip('\n')
        print(line)
        count += 1
        line = the_file.readline()
    the_file.close()
    
main()
