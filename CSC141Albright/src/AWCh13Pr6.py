# Anthony Wittemann
# 1/7/14
# Chapter 13 Program 6 - Zelle


class DigitToWord:
    
    def __init__(self, n):
        #create dictionary of digits to words
        self.__digitDict = {'1': 'One ', '2': 'Two ', '3': 'Three ', '4': 'Four ', '5': 'Five ', 
                            '6': 'Six ', '7': 'Seven ', '8': 'Eight ', '9': 'Nine ', '0': 'Zero '}
        # initialize variable to keep track of index
        self.__strIndex = 0 
        # convert the number back to a string
        strN = str(n)
        # number of digits the number has
        self.__strLen = len(strN)
        # initialize string to be appended and output
        self.__numWord = ''
        self.convert(strN)
       
    
    def convert(self, nStr):
        # recursive case
        if self.__strIndex < self.__strLen:
            currentNum = nStr[self.__strIndex]
            self.__numWord += self.__digitDict[currentNum]
            self.__strIndex += 1
            self.convert(nStr)
        #base case
        else:
            return self.__numWord
            
        
        self.__strIndex += 1
        
    def __str__(self):
        return str(self.__numWord)



def main():
    acceptableInput = False
    while not acceptableInput:
        try:
            numInput = input('Enter an integer: ')
            numInt = int(numInput)
            acceptableInput = True
            break
        except ValueError:
            print('Only integers, please')
    verbalizedNumber = DigitToWord(numInt)
    print(verbalizedNumber)
    
    
main()