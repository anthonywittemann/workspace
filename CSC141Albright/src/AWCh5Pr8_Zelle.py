# Anthony Wittemann
# 11/04/13
# Chapter 5 #4, 7, 8, 9, 10
# 8 - Caesar Cipher Modification
# NEED TO GET THE MESSAGE TO BE ONLY SPACES AND a-z, A-Z
AB = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']

def main():
    eORd = int(input('Enter 1 to encode, any other integer to decode: '))
    message = input('Enter the message to be encoded or decoded: ')
    key = int(input('Enter the positive value of the key: '))   
    if(eORd == 1):
        encode(message, key)
    else:
        decode(message, key)
        
#decodes by subtracting the key value from alphabet value
def decode(encodedMessage,theKey):
    messageNums = []
    splitMessages = encodedMessage.split()
    joinMessage = ' '.join(splitMessages)
    for e in joinMessage:
        for a in range(len(AB)):
            if AB[a] == e:
                if a - theKey < 0:
                    messageNums.append(len(AB) - 1 - abs(a - theKey))
                else:
                    messageNums.append(a - theKey)
                break
    decodedMessage = ''
    for m in messageNums:
        decodedMessage = decodedMessage + AB[m]
    print('The decoded message is: ', decodedMessage)

# ecodes by adding key value to alphabet value - DOESN'T WORK    
def encode(plainMessage, dasKey):
    messageNums = []
    for ch in plainMessage:
        for a in range(len(AB)):
            if AB[a] == ch:
                if a + dasKey > len(AB) - 1:
                    messageNums.append((a + dasKey) - len(AB) - 1)
                else:
                    messageNums.append(a + dasKey)
                break
    encodedMessage = ''
    for m in messageNums:
        encodedMessage = encodedMessage + AB[m]
    print('The encoded message is: ', encodedMessage)
    
main()