# Anthony Wittemann
# 11/7/13
# # 4 Morse Code Converter

def main():
    CHARACTERS = (' ', ',', '.', '?', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                  'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                  'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z')
    MORSECODE = (' ', '--..--', '.-.-.-', '..--..', '-----', '.----', '..---', '...--', '....-', '.....', '-....', 
                 '--...', '---..', '----.','.-', '-...', '-.-.', '-..', '.', '..-.', '--.', '....', '..', '.---', '-.-', 
                 '.-..', '--', '-.','---', '.--.', '--.-', '.-.', '...', '-', '..-', '...-', '.--', '-..-', '-.-', '--..')
    try:
        userInput = input('Enter your message to be encoded: ')
    except:
        print('Your input was invalid')
    
    charIndexes = []
    
    try:
        for char in userInput:
            ind = CHARACTERS.index(char.upper())
            charIndexes.append(ind)
    except:
        print('At least 1 of the characters you entered cannot be converted into morse code.')  
    
    morseCodeOutput = ''
    for r in range(len(charIndexes)):
        morseCodeOutput += MORSECODE[charIndexes[r]]
    print(morseCodeOutput, sep='', end='');
    
main()