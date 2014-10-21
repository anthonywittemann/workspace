# Anthony Wittemann
# 10/03/13
# Programming Exercises 2

import random

def main():
    rand1 = random.randint(1,1000);
    rand2 = random.randint(1,1000);
    print(rand1);
    print(rand2);
    
    userAnswer = int(input('What is the sum of the 2 previous numbers'));
    
    ##userAnswer = int(input('What is the sum of ', rand1,' and ', rand2, '?'));
    
    
    if(userAnswer == rand1+rand2):
        print('Well done. You are capable of doing in a few seconds what omputers do in a few milliseconds');
    else:
        print('Well, the correct answer is', rand1 + rand2);

main();
    

    
