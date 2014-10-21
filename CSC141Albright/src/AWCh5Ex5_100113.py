#Anthony Wittemann
#10/1/13
#5 Average Rainfall

def main():
    years = int(input('How many years are we calculating the rainfall for?'));
    totalRainfall = 0.0;
    monthlyRainfall = 0.0;
    for r in range(years):
        print('Year ', r + 1);
        for m in range(12):
            monthlyRainfall = float(input('How much rain fell this month?'));
            totalRainfall = totalRainfall + monthlyRainfall;
    months = years * 12;
    avgMonthlyRainfall = float(totalRainfall / months);
    print('Total Months: ', months);
    print('Total Rainfall: ', format(totalRainfall, '.2f'), 'inches');
    print('Average monthly rainfall: ', format(avgMonthlyRainfall, '.2f'), ' inches');

main();
