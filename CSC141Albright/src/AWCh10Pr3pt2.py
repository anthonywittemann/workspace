# Anthony Wittemann
# 11/19/13
# Chapter 10 Programming Exercise 3 - File Encryption and Decryption

# Decrytion Program

def main():
    codes = {'!': 'A', '@': 'a', '#': 'B', '$': 'b', 'Z': 'C', 'x': 'c', '.': 'D', 'p': 'd', 
             'X': 'E', 'o': 'e', 'g': 'F', 'q': 'f', '/': 'G', '[': 'g', 'f': 'H', 'O': 'h',
             '*': 'I', 'J': 'i', 'S': 'J', 'E': 'j', ':': 'K', '^': 'k', 'i': 'L', 'm': 'l',
             'n': 'M', 'a': 'm', '+': 'N', 'w': 'n', 'G': 'O', '|': 'o', 'b': 'P', 'V': 'p',
             'r': 'Q', 'F': 'q', '=': 'R', '(': 'r', 'k': 'S', 'C': 's', 'W': 'T', 'R': 't',
             '%': 'U', 'H': 'u', '?': 'V', 'D': 'v', '_': 'W', 'y': 'w', '}': 'X', '0': 'x',
             'h': 'Y', 'A': 'y', 'I': 'Z', ';': 'z', '7': ' ', '8': '  '}
    
    inputFile = input('Enter the name of the encrypted file: ')
    try:
        file = open(inputFile, 'r')
        lines = file.read()
        file.close()
    
        outputLines = ''
        for char in lines:
            if char in codes:
                outputLines += codes[char]
            else:
                outputLines += char
    
        print(outputLines)
        
    except IOError:
        print('Error opening the file.')
    except:
        print('Error opening the file.')
    
    
    
    
main()
