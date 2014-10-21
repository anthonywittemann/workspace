# Anthony Wittemann
# 11/07/13
# 9 - World Series Champions

def main():
    try:
        wsWinners = open('WorldSeriesWinners.txt', 'r')
    except IOError:
        print('''Error opening file 'WorldSeriesWinners.txt' ''')
    except:
        print('''Can't open file WorldSeriesWinners.txt' ''')
    
    wsWinnerList = wsWinners.readlines()
    
    try:
        userTeam = input('Enter a team: ')
    except:
        print('Invalid input')
        
    totalWins = 0
    for w in wsWinnerList:
        if w == userTeam:
            totalWins += 1
    print('The ', userTeam, 's has won ', totalWins, ' World Series')
        
    
main()