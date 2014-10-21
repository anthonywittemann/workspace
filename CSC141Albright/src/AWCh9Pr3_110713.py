# Anthony Wittemann
# 11/7/13
# Programming Exercises 1,2,3

# 3 - Date Printer

MONTHS = ("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")

def main():
    date = input('Enter a date in the form mm/dd/yyyy: ')
    dateList = date.split('/')
    formattedDate = MONTHS[int(dateList[0]) - 1] + ' ' + dateList[1] + ', ' + dateList[2]
    print('Formatted Date: ', formattedDate)
    
main()