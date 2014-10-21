# Anthony Wittemann
# 10/01/13
# 8 Sum of Numbers

def main():
    keepGoing = True;
    sum0 = 0;
    while(keepGoing == True):
        inp = float(input('Enter a positive number to continue and a negative number to end'));
        if(inp > 0):
            sum0 += inp;
        elif(inp == 0):
            print('Really? Just enter a positive or negative number!'); 
        else:
            keepGoing = False;
            print('Sum: ', sum0);
            break

main();
