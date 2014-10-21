# Anthony Wittemann
# 1/7/14
# Chapter 13 Program 3 - Zelle


def main():
    usrInput = input('Enter a string to see if it is a palindrome: ')
    if isPalindrome(usrInput):
        print('Your sentence is a palindrome.')
    else:
        print('Your sentence is not a palindrome.')
    
def isPalindrome(sent):
    sSent = sent.replace(' ', '')
    sSent1 = sSent.replace(',', '')
    sSent2 = sSent1.replace("""'""", '')
    sSent3 = sSent2.replace('''"''', '')
    sSent4 = sSent3.replace('.', '')
    sSent5 = sSent4.replace('?', '')
    sSent6 = sSent5.replace('!', '')
    
    lowerSent = sSent6.lower()
    
    cFront = 0
    cBack = len(lowerSent) - 1
    
    if isIdentical(cFront, cBack, lowerSent):
        return True
    else:
        return False

# boolean function returns True if palindrome, 
# recursively calls itself if front and back characters are the same 
# and front and back character indexes are not in the middle yet    
def isIdentical(cFront, cBack, lowerSent):
    if lowerSent[cFront] == lowerSent[cBack]:
        # check to see if the front & back are in the middle/same place
        if cFront == cBack or cBack - cFront == 1:
            return True
        else:
            return isIdentical(cFront + 1, cBack - 1, lowerSent)
    else:
        return False
        
    
    
main()