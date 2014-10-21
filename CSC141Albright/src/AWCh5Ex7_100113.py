#Anthony Wittemann
#10/1/13
#7 Pennies for Pay

def main():
    days = int(input('How many days are you being paid for?'));
    pay = .01;
    totalPay = 0;
    for d in range(days):
        print('Day: ', d + 1, '      Pay: $', pay);
        totalPay += pay;
        pay *= 2;
    print('Total pay: $', totalPay);
    
main();
