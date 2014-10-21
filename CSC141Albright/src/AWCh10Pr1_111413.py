# Anthony Wittemann
# 11/19/13
# Chapter 10 Programming Exercise 1 - Course Information

def main():
    courseRooms = {'CSC101': 3004, 'CSC102': 4501, 'CSC103': 6755, 'NT110': 1244, 'CM241': 1411}
    courseInstructors = {'CSC101': 'Haynes', 'CSC102': 'Alvarado', 'CSC103': 'Rich', 'NT110': 'Burke', 'CM241': 'Lee'}
    courseMeetingTimes = {'CSC101': '8:00 am', 'CSC102': '9:00 am', 'CSC103': '10:00 am', 'NT110': '11:00 am', 'CM241': '1:00 pm'}
    
    courseNum = input('Enter the course number: ')
    
    if courseNum in courseRooms:
        print(courseNum, 'meets in room', courseRooms[courseNum], 'at', courseMeetingTimes[courseNum], 'and is taught by Professor', courseInstructors[courseNum])  
    else:
        print('The course you entered does not exist in the records.')
    
main()

