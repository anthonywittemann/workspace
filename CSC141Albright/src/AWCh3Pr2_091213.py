# Anthony Wittemann
# Chapter 3 Exercises
# 9/12/2013

# 2 Sales Tax Program Refactoring
def main():
    price = float(input('What is the price of your item?'));
    localTax(price);
    stateTax(price);
    totalTax(price);
    totalPrice(price);

def localTax(price):
    localTax = .02 * price;
    print('Local Tax: ', format(localTax, '.2f'));

def stateTax(price):
    stateTax = .04 * price;
    print('State Tax: ', format(stateTax, '.2f'));

def totalTax(price):
    totalTax = .06 * price;
    print("Total Tax: ", format(totalTax, '.2f'));

def totalPrice(price):
    totalPrice = 1.06*price;
    print('Total price: ', format(totalPrice, '.2f'));

main();



