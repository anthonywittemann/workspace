# Anthony Wittemann
# 11/19/13
# Chapter 10 Programming Exercise 3 - File Encryption and Decryption

# Encryption program

def main():
    codes = {'A': '!', 'a': '@', 'B': '#', 'b': '$', 'C': 'Z', 'c': 'x', 'D': '.', 'd': 'p', 
             'E': 'X', 'e': 'o', 'F': 'g', 'f': 'q', 'G': '/', 'g': '[', 'H': 'f', 'h': 'O',
             'I': '*', 'i': 'J', 'J': 'S', 'j': 'E', 'K': ':', 'k': '^', 'L': 'i', 'l': 'm',
             'M': 'n', 'm': 'a', 'N': '+', 'n': 'w', 'O': 'G', 'o': '|', 'P': 'b', 'p': 'V',
             'Q': 'r', 'q': 'F', 'R': '=', 'r': '(', 'S': 'k', 's': 'C', 'T': 'W', 't': 'R',
             'U': '%', 'u': 'H', 'V': '?', 'v': 'D', 'W': '_', 'w': 'y', 'X': '}', 'x': '0',
             'Y': 'h', 'y': 'A', 'Z': 'I', 'z': ';', ' ': '7', '  ': '8'}
    
    inputFile = input('Enter the file to be encrypted: ')
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
    
        outputFile = open('EncryptedFile.txt', 'w')
        outputFile.write(outputLines)
        outputFile.close()
    except IOError:
        print('Error opening the file.')
    except:
        print('Error opening the file.')
    
    
    
    
main()
