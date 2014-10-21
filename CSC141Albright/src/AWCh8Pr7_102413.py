# Anthony Wittemann
# 10/24/13
# 7 Name Search

def main():
    infile1 = open('GirlNames.txt', 'r')   
    isInGirls = False
    isInBoys = False
    isInBoth = False
    inputName = input('Enter a name: ')
    
#    girlsNames = infile1.readlines()

    g = infile1.readline()
    while g != '':
        g = g.rstrip('\n')
        print(g)
        if g == inputName:
            isInGirls = True
            print('Pow')
        g = infile1.readline()  
        
#     for g in girlsNames:
#         g = g.rstrip('\n')
#         print(g)
#         if g == inputName:
#             isInGirls = True
            
    infile1.close()    
    
    infile2 = open('BoyNames.txt', 'r') 
#    boysNames = infile2.readlines()
#    print(boysNames)

    b = infile2.readline()
    while b != '':
        b = b.rstrip('\n')
        print(b)
        if b == inputName:
            isInBoys = True
            if isInGirls == True:
                isInBoth = True
        b = infile2.readline()
        
#     for b in boysNames:
#         b = b.rstrip('\n')
#         print(b)
#         if b == inputName:
#             isInBoys = True
#             if isInGirls:
#                 isInBoth = True
        
    infile2.close()        
    if isInBoth:
        print('The name entered was among both the 200 most popular girl and the 200 most popular boys names.')
    elif isInGirls:
        print('The name entered was among the 200 most popular girl names.')
    elif isInBoys:
        print('The name entered was among the 200 most popular boy names.')
    else:
        print('The name entered was not among the 200 most popular girls or boys names.')
    

main()
