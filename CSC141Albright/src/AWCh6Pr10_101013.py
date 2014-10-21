# Anthony Wittemann
# 10/10/13
# 10 - Future Value

def main():
    presentValue = float(input('''What is your account's present value? '''))
    monthlyIR = float(input('''What is your account's monthly interest rate as a decimal? '''))
    numMonths = int(input('''How many months will the money be in the account? '''))
    futureValue = futureVal(presentValue, monthlyIR, numMonths)
    print('The value of your account will be $', format(futureValue,'.2f'), 'after', numMonths, 'months.')

def futureVal(P, i, t):
    F = P * (1 + i)**t
    return F

main()
