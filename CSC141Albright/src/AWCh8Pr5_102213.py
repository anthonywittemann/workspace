# Anthony Wittemann
# 10/22/13
# 5 Charge Account Validation

def main():
    accounts = [5658845, 4520125, 7895122, 8777541, 8451277, 1302850, 8080152, 4562555, 5552012, 5050552, 7825877, 1250255, 1005231, 6545231, 3852085, 7576651, 7881200, 4581002]
    try:
        desiredAccount = int(input('Enter an account number: '))
    except ValueError:
        print('Please enter a valid number')
    except:
        print('Please enter a valid number')
    
    inList = False
    for a in accounts:
        if a == desiredAccount:
            inList = True
            break
    if(inList == True):
        print('The number is valid')
    else:
        print('The number is not valid')

main()
    
            
    
    
