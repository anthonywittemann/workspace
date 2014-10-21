# Anthony Wittemann
# Chapter 3 Exercises
# 9/12/2013

# 3 How Much Insurance
def main():
    replaceCost = float(input('How much would it cost to replace your home/building? '));
    minInsurance = .8 * replaceCost;
    print('You should insure your home for at least $', format(minInsurance, '2f'));

main();
