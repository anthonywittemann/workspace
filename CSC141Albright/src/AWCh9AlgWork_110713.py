# Anthony Wittemann
# 11/07/13
# Algorithm Workbench

# 1
if choice.upper() == 'Y':
    
# 2
spaceCount = 0
for m in mystring:
    if m == ' ':
        spaceCount += 1 
             
# 3
digitCount = 0
for m in mystring:
    if m.isdigit():
        digitCount += 1 
        
# 4
lowerCount = 0
for m in mystring:
    if m.islower():
        lowerCount += 1 
        
# 5
def endsInDotCom(theString):
    if theString.endswith('.com'):
        return True
    return False

# 6
newString = ''
for o in origString:
    if o == 't':
        newString += 'T'
    else:
        newString += o
        
# 7 
def makeBackwards(dasString):
    neuString = ''
    strLen = len(dasString)
    for d in range(strLen: 0: -1):
        neuString += dasString[d]
    print('Backwards String: ', neuString)
    
# 8
print(mystring[:3])

# 9
print(mystring[-3:])

# 10
myList = mystring.split('>')

    
    