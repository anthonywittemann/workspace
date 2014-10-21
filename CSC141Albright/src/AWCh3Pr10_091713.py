# Anthony Wittemann
#9/17/2013
# Chapter 3 Exercise 10

def main():
    monthlySales = float(input('What were your total monthly sales?'));
    
    countyTax = .02 * monthlySales;
    stateTax = .04 * monthlySales;
    totalTax = .06 * monthlySales;

    print('The county sales tax is $', format(countyTax, '.2f'));
    print('The state sales tax is $', format(stateTax, '.2f'));
    print('The total sales tax is $', format(totalTax, '.2f'));
    
main();
