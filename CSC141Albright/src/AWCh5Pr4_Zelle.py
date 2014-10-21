# Anthony Wittemann
# 11/04/13
# Chapter 5 #4, 7, 8, 9, 10
# 4 - Acronym maker

def main():
    phrase = input('Enter a phrase to be converted into an acronym: ')
    firstLetters = []
    for p in phrase.split():
        p = p.capitalize()
        firstLetter = p[0]
        firstLetters.append(firstLetter)
        
    acronym = ''
    for f in firstLetters:
        acronym = acronym + f
    print('Acronym: ', acronym)
    
main()