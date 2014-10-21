# Anthony Wittemann
# 1,2,3,5 WS
# 9/10/2013

### 1 Simple Arithmetic
##val1 = float(input('Give me a numeric value.'));
##val2 = float(input('Another, please.'));
##
##valSum = val1 + val2;
##difference = val1 - val2;
##product = val1 * val2;
##
##print('Sum: ', valSum);
##print('Difference: ', difference);
##print('Product: ', product);
##
##print();
##
###2 Weighted Average
##quizAvg = float(input('Input the average of your Quiz scores.'));
##midterm = float(input('What did you get on your Midterm?'));
##labsAvg = float(input('Input the average of your Lab grades.'));
##finalll = float(input('What did you get on your Final?'));
##
##grade = .2 * quizAvg + .2 * midterm + .35 * labsAvg + .25 * finalll;
##print('Grade: ', format(grade, '.0f'));
##






print();

#3 GPA -- Only one that doesn't work yet -- having problems accessing array values
# NEED TO CREATE 2D INT ARRAY FOR COURSE CREDITS AND GRADE
courseCredits = [0];
courseGrade = [0];
noMoreCourses = 0;

while(noMoreCourses == 0):
    newCC = int(input('How many course credits is this course worth?'));
    courseCredits.append(newCC);
    newCG = int(input('What was your grade in this course?'));
    courseGrade.append(newCG);

    noMoreCourses = int(input('If you have taken any more courses, enter 0, otherwise enter 1 to finish'));
    

#need to multiply corresponding course credits by the grade
cCListPosition = len(courseCredits);
qualityPoints = 0;

##while(cCListPosition > 1):
##    qualityPoints = qualityPoints + courseCredits[cCListPosition] * courseGrade[cClistPosition];
##    cCListPosition = cCListPosition - 1;
for s in courseCredits:
    print(s);
    

cCListPosition1 = len(courseCredits);
##while(cCListPosition1 > 1):
##    totalCredits = totalCredits + courseCredits[cCListPosition1];
for t in courseCredits:
    print(t);
    

GPA = qualityPoints/totalCredits;
print('GPA: ', GPA);




print();

#5 US Minimum Coins
totalPennies = int(input('Input the total number of pennies.'));
minCoins = 0;
minHalves = 0;
minQuarters = 0;
minDimes = 0;
minNickels = 0;
minPennies = 0;

while(totalPennies > 50):
    totalPennies = totalPennies - 50;
    minCoins = minCoins + 1;
    minHalves = minHalves + 1;
    
while(totalPennies > 25):
    totalPennies = totalPennies - 25;
    minCoins = minCoins + 1;
    minQuarters = minQuarters + 1;
    
while(totalPennies > 10):
    totalPennies = totalPennies - 10;
    minCoins = minCoins + 1;
    minDimes = minDimes + 1;
    
while(totalPennies > 5):
    totalPennies = totalPennies - 5;
    minCoins = minCoins + 1;
    minNickels = minNickels + 1;
    
minPennies = totalPennies;

print('Minimum number of Coins: ', minCoins);
print('Minimum number of Halvess: ', minHalves);
print('Minimum number of Quarters: ', minQuarters);
print('Minimum number of Dimes: ', minDimes);
print('Minimum number of Nickels: ', minNickels);
print('Minimum number of Pennies: ', minPennies);
