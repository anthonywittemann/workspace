# Anthony Wittemann
# 11/19/13
# Chapter 10 Programming Exercise 2 - Capital Quiz

import random

def main():
    stateCapitals = {'Alabama': 'Montgomery', 'Alaska': 'Juneau', 'Arizona': 'Phoenix', 'Arkansas': 'Little Rock', 
                     'California': 'Sacramento', 'Colorado': 'Denver', 'Connecticut': 'Hartford', 'Delaware': 'Dover',  
                     'Florida': 'Tallahassee', 'Georgia': 'Atlanta', 'Hawaii': 'Honolulu', 'Idaho': 'Boise', 
                     'Illinois': 'Springfield', 'Indiana': 'Indianapolis', 'Iowa': 'Des Moines', 'Kansas': 'Topeka', 
                     'Kentucky': 'Frankfort', 'Louisiana': 'Baton Rouge', 'Maine': 'Augusta', 'Maryland': 'Annapolis',
                     'Massachusetts': 'Boston', 'Michigan': 'Lansing', 'Minnesota': 'Saint Paul', 'Mississippi': 'Jackson',
                     'Missouri': 'Jefferson City', 'Montana': 'Helena', 'Nebraska': 'Lincoln', 'Nevada': 'Carson City',
                     'New Hampshire': 'Concord', 'New Jersey': 'Trenton', 'New Mexico': 'Santa Fe', 'New York': 'Albany',
                     'North Carolina': 'Raleigh', 'North Dakota': 'Bismarck', 'Ohio': 'Columbus', 'Oklahoma': 'Oklahoma City',
                     'Oregon': 'Salem', 'Pennsylvania': 'Harrisburg', 'Rhode Island': 'Providence', 'South Carolina': 'Columbia',
                     'South Dakota': 'Pierre', 'Tennessee': 'Nashville', 'Texas': 'Austin', 'Utah': 'Salt Lake City',
                     'Vermont': 'Montpelier', 'Virginia': 'Richmond', 'Washington': 'Olympia', 'West Virginia': 'Charleston',
                     'Wisconsin': 'Madison', 'Wyoming': 'Cheyenne'}
    
    correctCount = 0
    incorrectCount = 0
    numQuizQuestions = int(len(stateCapitals)/10)
    
    for s in range(numQuizQuestions):
        state, capital = stateCapitals.popitem()
        print('What is the capital of ', state, '?')
        userAnswer = input()
        if userAnswer == capital:
            correctCount += 1
        else:
            incorrectCount += 1
            
    print('You got ', correctCount, ' correct and ', incorrectCount, ' incorrect.')
    
main()