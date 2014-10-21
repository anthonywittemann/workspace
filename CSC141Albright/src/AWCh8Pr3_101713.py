# Anthony Wittemann
# 10/17/13
# Prog. Ex. 1-4

#3 - Rainfall Statistics
def main():
    monthlyRain = [0.0] * 12
    m = 0
    while m < len(monthlyRain):
        print('Enter the rainfall for month ', m+1,' ', sep='', end='')
        monthlyRain[m] = float(input())
        m += 1
        
    totalRain = 0.0
    n = 0
    while n < len(monthlyRain):
        totalRain += monthlyRain[n]
        n += 1
        
    print('Total Rainfall: ', totalRain, 'inches')
    avgRain = totalRain/12.0
    print('Average monthly Rainfall: ', format(avgRain,'.2f'), 'inches')

    highest = len(monthlyRain) - 1
    lowest = 0
    o = 0
    while o < len(monthlyRain):
        if(highest < monthlyRain[o]):
            highest = o
        elif(lowest > monthlyRain[o]):
            lowest = o
        o += 1
    print('The month with the highest rainfall is month', highest + 1)
    print('The month with the lowest rainfall is month', lowest + 1)

main()
