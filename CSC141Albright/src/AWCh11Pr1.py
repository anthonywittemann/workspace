# Anthony Wittemann
# 11/20/13
# Chapter 11 Program 1 - Pet Class

class Pet:
    def __init__(self):
        self.name = ""
        self.animal_type = ""
        self.age = 0
        
        
    def setName(self, name):
        self.name = name
    
    def setAnimalType(self, animal_type):
        self.animal_type = animal_type
        
    def setAge(self, age):
        self.age = age
        
        
    def getName(self):
        return self.name
    
    def getAnimal_type(self):
        return self.animal_type
    
    def getAge(self):
        return self.age
    
def main():
    my_pet = Pet()
    petName = input('Enter the name of your pet: ')
    petType = input('Enter the type of pet you have: ')
    petAge = int(input('Enter the age of your pet in years: '))
    
    my_pet.setName(petName)
    my_pet.setAnimalType(petType)
    my_pet.setAge(petAge)
    
    print('You have a ', my_pet.getAnimal_type(), ' named ', my_pet.getName(), ' that is ', my_pet.getAge(), ' years old.')
    
    
main()