# Anthony Wittemann
# 9/24/2013
# Programming Exercises 3 - Budget Analysis

def main():
    budget = float(input('How much have you budgeted for this month?'));
    keepGoing = 0;
    totExpenses = 0;
    keepGoing = int(input('If you have any more expenses, enter 0'));
    while(keepGoing == 0):
        expenses = float(input('Enter the expense'));
        keepGoing = int(input('If you have any more expenses, enter 0'));
        totExpenses = totExpenses + expenses; 
    over = totExpenses - budget;
    under = budget – totExpenses;
    if(over > 0):
        print('You are $', over, 'over budget');
    else:
        print('You are $', under, 'under budget');
    
main();
