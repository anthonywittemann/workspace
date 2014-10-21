# Anthony Wittemann
# 11/19/13
# Chapter 10 Programming Exercise 8 - Name and Email Addresses

import pickle

def main():
    nameEmailDict = {} 
    try:
        try:
            input_file = open('nameEmail.dat', 'rb')
            
            end_of_file = False
            while not end_of_file:
                try:
                    nameEmailDict = pickle.load(input_file)
                except EOFError:
                    end_of_file = True
                    if not nameEmailDict:
                        # create a dictionary since there isn't one and create the data file
                        nameEmailDict = {'Anthony':'aw@gmail.com'}
                        #close the current reading file and open a write file
                        input_file.close()
                        input_file = open('nameEmail.dat', 'wb')
                        pickle.dump(nameEmailDict, input_file)
                        input_file.close() 
                        
            input_file.close()  
             
        except FileNotFoundError:
            # create a dictionary since there isn't one and create the data file
            nameEmailDict = {'Anthony':'aw@gmail.com'}
            input_file = open('nameEmail.dat', 'wb')
            pickle.dump(nameEmailDict, input_file)
            input_file.close()  
                 
        except IOError:
            print('Error opening file')    
        
        
        wantsToContinue = True
        while wantsToContinue:
            userDecision = input('''Enter l to look up a person's email address, a to add a new name and email address,
            c to change an email address, d to delete a name and email address or q to quit  ''')
        
            if userDecision.lower() == 'l' or userDecision.lower() == 'a' or userDecision.lower() == 'c' or userDecision.lower() == 'd':
                goPickle(userDecision.lower(), nameEmailDict)
            elif userDecision.lower() == 'q':
                wantsToContinue = False
                break
            else:
                raise ValueError
                
    except ValueError:
        print('Invalid input')   
        
    
def goPickle(usrDec, nE):
#     print()
#     print(nE)
#     print()
    if usrDec == 'l':
        name = input('Enter the name of the person whose email you desire: ')
        print('          ',  nE.get(name, 'Could not find an email address for the name provided.'))
    elif usrDec == 'a':
        name = input('Enter the name of the person to be added to the database: ')
        email = input('Enter the email of the person to be added to the database: ')
        nE[name] = email
    elif usrDec == 'c':
        name = input('Enter the name of the person whose email you wish to change: ')
        newEmail = input('Enter the new email: ')
        nE[name] = newEmail
    elif usrDec == 'd':
        name = input('Enter the name of the person whose email and name you wish to delete: ')
        if name in nE:
            del nE[name]
        else:
            print('          Could not find an email address for the name provided.')
        
    output_file = open('nameEmail.dat', 'wb')
    pickle.dump(nE, output_file)
    output_file.close()
        
       
main()