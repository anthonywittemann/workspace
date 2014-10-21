# Anthony Wittemann
# 11/04/13
# Chapter 5 #4, 7, 8, 9, 10
# 9 - word count

def main():
    sentence = input('Write a sentence: ')
    wordList = sentence.split()
    print('Word Count: ', len(wordList))
    
main()