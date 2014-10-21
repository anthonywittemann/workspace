# Anthony Wittemann
# 10/29/13
# Chapter 7 Program 1 - 8
# Program 10 - Golf Scores - Reading

def main():
    golfInfo = open('golf.txt', 'r')
    isPlayer = True
    for g in golfInfo:
        g = g.rstrip('\n')
        if isPlayer:
            print('Player: ', g)
            isPlayer = False
        else:
            print('Score: ', g)
            isPlayer = True
    golfInfo.close()
    
main()