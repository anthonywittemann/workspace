# Anthony Wittemann
# 11/20/13
# Car Class

class Car:
    def __init__(self):
        self.make = "Honda"
        self.model = "Civic"
        self.color = "Silver"
        
        
    def setMake(self, make):
        self.make = make
    
    def setModel(self, model):
        self.model = model
        
    def setColor(self, color):
        self.color = color
        
        
    def getMake(self):
        return self.make
    
    def getModel(self):
        return self.model
    
    def getColor(self):
        return self.color


def main():
    myCar = Car()
    
    userIsDone = False
    while not userIsDone:
        print('The current car is a ', myCar.getColor(), ' ', myCar.getMake(), ' ', myCar.getModel())
        usrInput = input('Enter Color to change the color, Make to change the make, Model to change the model, and any other button to quit')
        if usrInput.lower() == 'color':
            newColor = input('Enter the new car color: ')
            myCar.setColor(newColor)
        elif usrInput.lower() ==  'make':
            newMake = input('Enter the new make of the car: ')
            myCar.setMake(newMake)
        elif usrInput.lower() == 'model':
            newModel = input('Enter the new make of the car: ')
            myCar.setModel(newModel)
        else:
            userIsDone = True
            break
    
main()