# Anthony Wittemann
# 12/03/13
# Chapter 11 Program 4 - Employee Class

class Employee:
    
    def __init__(self, n, idn, dpt, jT):
        self.__name = n
        self.__id = idn
        self.__dept = dpt
        self.__jobTitle = jT
        
    def setName(self, newName):
        self.__name = newName
        
    def setID(self, newID):
        self.__id = newID
        
    def setDept(self, newDept):
        self.__dept = newDept
        
    def setJobTitle(self, newJT):
        self.__jobTitle = newJT
        
    
    def getName(self):
        return self.__name
    
    def getID(self):
        return self.__id
    
    def getDept(self):
        return self.__dept
    
    def getJobTitle(self):
        return self.__jobTitle
    
    #I'm assuming this is similar to Java's toString() method
    def __str__(self):
        return 'Name: ' + self.getName() + '\nID: ' + str(self.getID()) + '\nDepartment: ' + self.getDept() + '\nJob Title: ' + self.getJobTitle()
    
def main():
    emp1 = Employee('Susan Meyers', 47899, 'Accounting', 'Vice President')
    emp2 = Employee('Mark Jones', 39119, 'IT', 'Programmer')
    emp3 = Employee('Joy Rogers', 81774, 'Manufacturing', 'Engineer')
    
    print('Name:', emp1.getName(), '   ID:', emp1.getID(), '   Department:', emp1.getDept(), '   Job Title:', emp1.getJobTitle())
    print('Name:', emp2.getName(), '   ID:', emp2.getID(), '   Department:', emp2.getDept(), '   Job Title:', emp2.getJobTitle())
    print('Name:', emp3.getName(), '   ID:', emp3.getID(), '   Department:', emp3.getDept(), '   Job Title:', emp3.getJobTitle())
    
main()