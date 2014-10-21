# Anthony Wittemann
# 10/17/13
# Prog. Ex. 1-4

#1 - Total Sales
def main():
    sales = [0.0]*7
    r = 0
    while r < len(sales):
        print('What are the sales for day ', r+1, ' ', end='')
        sales[r] = float(input())
        r += 1

    totalSales = 0.0
    s = 0
    while s < len(sales):
        totalSales += sales[s]
        s += 1 
    
    print('Total Sales: $', format(totalSales,'.2f'))

main()

