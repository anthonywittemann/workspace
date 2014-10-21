# Anthony Wittemann
# 11/7/13
# # 5 Alphabet Telephone Number Translator

def main():
    try:
        phoneNum = input('Enter a telephone number in the format XXX-XXX-XXXX: ')
        
        if len(phoneNum) != 12 or phoneNum[3] != '-' or  phoneNum[7] != '-':
            raise ValueError
        
        digPhoneNum = ''
        for num in phoneNum:
            if num.upper() == 'A' or num.upper() == 'B' or num.upper() == 'C':
                digPhoneNum += '2'
            elif num.upper() == 'D' or num.upper() == 'E' or num.upper() == 'F':
                digPhoneNum += '3'
            elif num.upper() == 'G' or num.upper() == 'H' or num.upper() == 'I':
                digPhoneNum += '4'
            elif num.upper() == 'J' or num.upper() == 'K' or num.upper() == 'L':
                digPhoneNum += '5'
            elif num.upper() == 'M' or num.upper() == 'N' or num.upper() == 'O':
                digPhoneNum += '6'
            elif num.upper() == 'P' or num.upper() == 'Q' or num.upper() == 'R' or num.upper() == 'S':
                digPhoneNum += '7'
            elif num.upper() == 'T' or num.upper() == 'U' or num.upper() == 'V':
                digPhoneNum += '8'
            elif num.upper() == 'W' or num.upper() == 'X' or num.upper() == 'Y' or num.upper() == 'Z':
                digPhoneNum += '9'
            elif num == '-' or num.isdigit():
                digPhoneNum += num
            else:
                raise ValueError
        
        print('Translated Telephone Number: ', digPhoneNum)
            
    except ValueError:
        print('Your input was invalid.')
    
main()
