# Anthony Wittemann
# 10/29/13
# Chapter 7 Program 1 - 8
# Program 10 - Golf Scores - Entry

def main():
    golfInfo = open('golf.txt', 'w')
    player = input('Enter the players name: ')
    score = input('''Enter the player's score: ''')
    goOn = input('Enter Y to enter another player: ')
    golfInfo.write(player + '\n')
    golfInfo.write(str(score) + '\n')
    while goOn == 'Y' or goOn == 'y':
        player = input('Enter the players name: ')
        score = input('''Enter the player's score: ''')
        goOn = input('Enter Y to enter another player: ')
        golfInfo.write(player + '\n')
        golfInfo.write(str(score) + '\n')
    golfInfo.close()
    
main()