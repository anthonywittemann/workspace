# Anthony Wittemann
# 09/24/2013
# 8 Software Sales

def main():
    packagesPurch = int(input('How many packages have been purchased?'));
    discount = 0.0;
    packagePrice = 99;

    if(packagesPurch > 9 and packagesPurch < 20):
        discount = .20;
    elif(packagesPurch < 50):
        discount = .30;
    elif(packagesPurch < 100):
        discount = .40;
    else:
        discount = .50;

    priceDisc = discount * packagePrice;
    print('Discount: ', priceDisc);

main();
