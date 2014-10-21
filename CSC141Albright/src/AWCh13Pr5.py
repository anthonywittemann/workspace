# Anthony Wittemann
# 1/7/14
# Chapter 13 Program 5 - Zelle

class BC:
    
    def __init__(self, n, b):
        self.__outputStr = ''
        # The max base power is how many digits the number is in the new base
        self.__maxBasePower = 0
        while n//(b**self.__maxBasePower) > 0:
            self.__maxBasePower += 1
        self.__maxBasePower -= 1  
        
        self.__outputStr = self.baseConversion(n, b)
        

    def baseConversion(self, num, base):
        remainder = num%(base**self.__maxBasePower)
        currentDigit = num//(base**self.__maxBasePower)
        # base case 
        if self.__maxBasePower == 0:
            return str(num)    
        # recursive case    
        else:
            self.__maxBasePower -= 1
            return str(currentDigit) + ' ' + str(self.baseConversion(remainder, base))
            
                
    def __str__(self):
        return self.__outputStr    



def main():
    acceptableInput = False
    while not acceptableInput:
        try:
            numB10Input = input('Enter a base 10 integer: ')
            numB10 = int(numB10Input)
            newBaseInput = input('Enter the base to be converted to: ')
            newBase = int(newBaseInput)
            acceptableInput = True
            break
        except ValueError:
            print('Only integers, please')
            
    
    print(BC(numB10, newBase))
    
    
    
main()