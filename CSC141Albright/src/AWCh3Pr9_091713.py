# Anthony Wittemann
#9/17/2013
# Chapter 3 Exercise 9

def main():
    squareFeet = int(input("How many square feet of wall space needs to be painted?"));
    paintExpenses = float(input("How much does it cost for 1 gallon of paint?"));

    gallonsPaintNeeded = int(squareFeet / 115.);
    leftOver = squareFeet % 115;
    
    if(leftOver > 0):
        gallonsPaintNeeded = gallonsPaintNeeded + 1;
        
    print('Gallons of Paint Needed:', gallonsPaintNeeded);


    hoursLaborNeeded = float(squareFeet / 115. * 8);
    print(hoursLaborNeeded, 'hours of Labor are required.');


    paintCost = float(paintExpenses * gallonsPaintNeeded);
    print('The cost of the paint is: $', format(paintCost, '.2f'));


    laborCost = float(hoursLaborNeeded * 20.00);
    print('The cost of labor is: $', format(laborCost, '.2f'));


    totalCost = laborCost + paintCost;
    print('The total cost of the job is: $', format(totalCost, '.2f'));


main();
