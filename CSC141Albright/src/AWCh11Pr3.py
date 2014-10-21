# Anthony Wittemann
# 11/20/13
# Chapter 11 Program 3 - Personal Information Class

class PersonalInfo:
    def __init__(self, n, ad, ag, pn):
        self.__name = n
        self.__address = ad
        self.__age = ag
        self.__phoneNum = pn
        
        
    def setName(self, name):
        self.__name = name
    
    def setAddress(self, address):
        self.__address = address
        
    def setAge(self, age):
        self.__age = age
        
    def setPhoneNum(self, phoneN):
        self.__phoneNum = phoneN
        
        
    def getName(self):
        return self.__name
    
    def getAddress(self):
        return self.__address
    
    def getAge(self):
        return self.__age
    
    def getPhoneNum(self):
        return self.__phoneNum
    
def main():
    myInfo = PersonalInfo("Anthony", "345 Happy Lane", 19, 7178675309)
    myFriendInfo = PersonalInfo("Dave", "123 Oak Drive", 18, 6105293344)
    myFamilyInfo = PersonalInfo("Mom", "345 Happpy Lane", 47, 7174643311)
    
    print('My info:   Name: ', myInfo.getName(), '  Address: ', myInfo.getAddress(), ' Age: ', myInfo.getAge(), ' Phone Number: ', myInfo.getPhoneNum())
    print('''My friend's info:   Name: ''', myFriendInfo.getName(), '  Address: ', myFriendInfo.getAddress(), ' Age: ', myFriendInfo.getAge(), ' Phone Number: ', myFriendInfo.getPhoneNum())
    print('''My Mom's info:   Name: ''', myFamilyInfo.getName(), '  Address: ', myFamilyInfo.getAddress(), ' Age: ', myFamilyInfo.getAge(), ' Phone Number: ', myFamilyInfo.getPhoneNum())
    
    myInfo.setPhoneNum(7174643000)
    print('My new phone number: ', myInfo.getPhoneNum())
main()