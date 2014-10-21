# Anthony Wittemann
# 11/04/13
# Chapter 5 #4, 7, 8, 9, 10
# 10 - average word length

def main():
    sentence = input('Write a sentence: ')
    wordList = sentence.split()
    totalChars = 0
    for w in wordList:
        totalChars += len(w)
    avgWordLength = totalChars/len(wordList)
    print('Average Word Length: ', format(avgWordLength, '.2f'), ' characters per word')
    
main()