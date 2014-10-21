# Anthony Wittemann
# Unit 2 Programming Excersises
# 9/5/2013

# 1 personal information -- promting user for input then printing it
name = input('What is your name?'); 
address = input('What is your address?'); 
city = input('What city do you live in?'); 
state = input('What State do you live in?'); 
ZIP = input('What is your Zip code?'); 
teleNumber = input('What is your telephone number?'); 
major = input('What is your college major?');

print('Name: ', name);
print('Address: ', address);
print('City: ', city);
print('State: ', state);
print('ZIP: ', ZIP);
print('Telephone number: ',teleNumber);
print('Major: ', major);

print();

# 2 Sales prediction -- predicting profit from a given sales input
sales = float(input("What were your sales?"));
profit = sales * .23;
print('Profit: ', format(profit, '.2f'));

print();

# 3 Land Calculation -- convert square feet input to acres
areaSqFt = float(input('What is the area of your property in Square Feet?'));
areaAcres = areaSqFt/43560.;
print('Area of property in Acres: ', areaAcres);

print();

#4 Total Purchase -- Gives user subtotal, sales tax and total from an input of price
price = float(input('What is the price of your item?'));
salesTax = price * .06;
totalPrice = 1.06 * price;

print('Subtotal', format(price, '.2f'));
print('Sales tax', format(salesTax, '.2f'));
print('Total price', format(totalPrice, '.2f'));

print();

# 5 Distance Traveled -- Outputs the distance or car travelling 60 mph for 3 different times
dist5 = 60 * 5;
dist8 = 60 * 8;
dist12 = 60 * 12;

print("Distance after 5 hrs: ", dist5 'miles.');
print("Distance after 8 hrs: ", dist8, 'miles.');
print("Distance after 12 hrs: ", dist12, 'miles.');

print();

# 6 Sales Tax -- Gives user local tax, state tax, total tax and total from an input of price
price = float(input('What is the price of your item?'));
localTax = .02 * price;
stateTax = .04 * price;
totalTax = .06 * price;
totalPrice = 1.06*price;

print('Local Tax: ', format(localTax, '.2f'));
print('State Tax: ', format(stateTax, '.2f'));
print("Total Tax", format(totalTax, '.2f'));
print('Total price: ', format(totalPrice, '.2f'));

print()

# 7 MPG
milesDriven = float(input('How many miles have you driven?'));
gallonsUsed = float(input('How many gallons of gasoline have you used?'));
mpg = float(milesDriven/gallonsUsed);
print('Miles per gallon: ', format(mpg, '.3f'));

print()

# 8 Tip, Tax and Total
foodPrice = float(input('What was the price of your food and drink?'));
tax = .07*foodPrice;
tip = .15 * foodPrice;
total = 1.22 * foodPrice;

print('Tax: ', format(tax, '.2f'));
print('Tip: ', format(tip, '.2f'));
print('Total: ', format(total, '.2f'));

print();

#9 Celsius to Fahrenheit
degCel = float(input('What is the temperature in Celsius?'));
degFahr = (9*degCel) / 5 + 32;
print('Temperature in Fahrenheit: ', format(degFahr, '.2f'));

print();

#10 Stock Transaction
sharesBought = 1000;         buyPrice = 32.87;         buyCommission = sharesBought * buyPrice * .02;
sharesSold = 1000;           sellPrice = 33.92;        sellCommission = sharesSold * sellPrice * .02;
amountPaid = sharesBought * buyPrice;                  amountSold = sharesSold * sellPrice;

print('Amount paid for stock: ', format(amountPaid, '.2f'));
print('Commission paid for buy: ', format(buyCommission, '.2f'));
print('Amount sold: ', format(amountSold, '.2f'));
print('Sell Commission: ', format(sellCommission, '.2f'));

net = amountSold - amountPaid - buyCommission - sellCommission;

print('Net: ', format(net, '.2f'));







