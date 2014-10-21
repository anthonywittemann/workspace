# Anthony Wittemann
# 10/29/13
# Chapter 7 Program 1 - 8
# Program 4 - Item Counter

def main():
    try:
        names = open('names.txt', 'r')
    except:
        print('''Unable to open file 'names.txt' ''')
    
    try:
        line = names.readline() 
    except IOError as IOE:
        print(IOE)
    
    count = 0
    while line != '':
        count += 1
        
        try:
            line = names.readline() 
        except IOError as IOE:
            print(IOE)
    
    names.close()
    
    print('There are ', count, ' items in the file.')   
    
main()