# Anthony Wittemann
# 10/22/13
# 6 Driver's License Exam

# Make a list of chars that only has 10 answers
# Create another list that has 10 letters in it for 2 students
# >= 7/10 pass

def main():
    answers = ['B','D', 'A', 'A', 'C',' A', 'B', 'A', 'C', 'D']
    student1Responses = ['B','D', 'B', 'A', 'C',' A', 'B', 'A', 'D', 'D']
    student2Responses = ['A','D', 'A', 'A', 'C',' A', 'C', 'A', 'B', 'A']
    incorrectlyAnsweredS1 = []
    incorrectlyAnsweredS2 = []
    totalCorrectS1 = 0
    totalCorrectS2 = 0
    length = len(answers)
    
    i = 0
    while i < length:
        if student1Responses[i] == answers[i]:
            totalCorrectS1 += 1
        else:
            incorrectlyAnsweredS1.append(i + 1)
        if student2Responses[i] == answers[i]:
            totalCorrectS2 += 1
        else:
            incorrectlyAnsweredS2.append(i + 1)
        i += 1
            
    if totalCorrectS1 > 6:
        print('You have passed the test, Driver 1')
    else:
        print('You have failed the test, Driver 1')
    print('Driver 1, you incorrectly answered questions', incorrectlyAnsweredS1)

    if totalCorrectS2 > 6:
        print('You have passed the test, Driver 2')
    else:
        print('You have failed the test, Driver 2')
    print('Driver 2, you incorrectly answered questions', incorrectlyAnsweredS2)

main()
    
