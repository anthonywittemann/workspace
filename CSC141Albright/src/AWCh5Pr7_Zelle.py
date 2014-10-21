# Anthony Wittemann
# 11/04/13
# Chapter 5 #4, 7, 8, 9, 10
# 7 - Caesar Cipher encoder and decoder


def main():
    eORd = int(input('Enter 1 to encode, anything else to decode: '))
    message = input('Enter the message to be encoded or decoded: ')
    key = int(input('Enter the positive value of the key: '))   
    if(eORd == 1):
        encode(message, key)
    else:
        decode(message, key)
        
#decodes by subtracting the key value from ord value   
def decode(encodedMessage,theKey):
    messageNums = []
    splitMessages = encodedMessage.split()
    joinMessage = ' '.join(splitMessages)
    for e in joinMessage:
        messageNums.append(chr(ord(e) - theKey))
    decodedMessage = ''
    for m in messageNums:
        decodedMessage = decodedMessage + m
    print('The decoded message is: ', decodedMessage)

# ecodes by adding key value to ord value    
def encode(plainMessage, dasKey):
    messageNums = []
    for ch in plainMessage:
        messageNums.append(chr(ord(ch) + dasKey))
    encodedMessage = ''
    for m in messageNums:
        encodedMessage = encodedMessage + m
    print('The encoded message is: ', encodedMessage)
    
main()