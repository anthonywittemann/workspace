# Anthony Wittemann
# 09/24/2013
# 9 Shipping Charges

def main():
    packageWeight = float(input('How much does your package weight in pounds?'));
    shippingCharges = 0.0;

    if(packageWeight <= 2.0):
        shippingCharges = 1.1 * packageWeight;
    elif(packageWeight < 6.0):
        shippingCharges = 2.2 * packageWeight;
    elif(packageWeight < 10.0):
        shippingCharges = 3.7 * packageWeight;
    else:
        shippingCharges = 3.8 * packageWeight;

    print('Shipping Charges: $', shippingCharges);

main();
