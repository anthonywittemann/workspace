# Anthony Wittemann
# 10/24/13
# 5 Charge Account Validation - Reading files

def main():
    infile = open('charge_accounts.txt', 'r')
    accounts = infile.readlines()
    infile.close()
    desiredAccount = int(input('Enter an account number: '))
    
    inList = False
    for a in accounts:
        if int(a) == desiredAccount:
            inList = True

    if(inList == True):
        print('The number is valid')
    else:
        print('The number is not valid')

main()

