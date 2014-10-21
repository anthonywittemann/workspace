# Anthony Wittemann
# 12/03/13
# Chapter 11 Program 6 - Employee Management System

#NOT FINDING ID NUMBER CORRECTLY !!!!!

from AWCh11Pr4 import Employee
import pickle
import shutil

LOOK_UP = 1
ADD = 2
CHANGE = 3
DELETE = 4
QUIT = 5

FILENAME = 'employees.dat'

def main():
    
    myEmployees = loadEmployees()
    
    choice = 0
    while choice != QUIT:
        choice = getMenuChoice()
         
        if choice == LOOK_UP:
            lookUp(myEmployees)
        elif choice == ADD:
            add(myEmployees)
        elif choice == CHANGE:
            change(myEmployees)
        elif choice == DELETE:
            delete(myEmployees)
            
    saveEmployees(myEmployees)
    


    
def loadEmployees():
    empDct = {}
    try:
        inputFile = open(FILENAME, 'rb')
        EOF = False
        while not EOF:
            try:
                empDct = pickle.load(inputFile)
            except EOFError:
                EOF = True
                
        inputFile.close()
    except IOError:
        print('File Not Found.')
    except FileNotFoundError:
        print('File Not Found.')
        
    return empDct    
    
   
    
def getMenuChoice():
    print()
    print('1. Look up an employee ')
    print('2. Add a new employee ')
    print('''3. Change an employee's info ''')
    print('4. Delete an employee ')
    print('5. Quit the program ')
    print()
    
    choice = int(input('Enter the number of your choice: '))
    
    while choice < 1 or choice > 5:
        choice = int(input('Please enter a valid numerical input: '))
        
    return choice



def lookUp(emps):
    empID = int(input('''Enter the employee's id number: '''))
    
    print(emps.get(empID, 'No employee found for the given id number.'))
    
    print()
    
    
    
def add(emps):
    newName = input('Name: ')
    newID = int(input('ID number: '))
    newDept = input('Department: ')
    newJobTitle = input('Job Title: ')
    
    newEntry = Employee(newName, newID, newDept, newJobTitle)
    
    if newID not in emps:
        emps[newID] = newEntry
        print('The new employee has been added.')
    else:
        print('An employee already uses that ID. Please contact your System Administrator. Haha :)')



def change(emps):
    empID = int(input('Enter the employee ID: '))
    
    if empID in emps:
        newName = input('Enter the new Name: ')
        newDept = input('Enter the new Department: ')
        newJobTitle = input('Enter the new Job Title: ')
        
        updatedEntry = Employee(newName, empID, newDept, newJobTitle)
        
        emps[empID] = updatedEntry
        print('Information updated.')
    else:
        print('Could not find ID number')



def delete(emps):
    empID = int(input('''Enter the employee's ID number: '''))
    
    if empID in emps:
        del emps[empID]
        print('''The employee's information has been deleted.''')
    else:
        print('Could not find ID number')
    
    
      
def saveEmployees(emps):
    #Couldn't get this to work with temp file
#     tempFile = open('tempFile.dat', 'wb')
#     pickle.dump(emps, tempFile)
#      
#     outputFile = open(FILENAME, 'wb')
#      
#     shutil.copy('tempFile.dat', FILENAME)
#      
#     tempFile.close() 
#     outputFile.close()
    
     
    outputFile = open(FILENAME, 'wb')
    pickle.dump(emps, outputFile)
    outputFile.close()
    


main()