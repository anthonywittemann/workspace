# Anthony Wittemann
# 09/24/2013
# 7 Book Club Points

def main():
	booksPurchased = int(input('How many books have you purchased this past month?'));
	points = 0;

	if(booksPurchased == 0):
		points = 0;
	elif(booksPurchased == 1):
		points = 5;
	elif(booksPurchased == 2):
		points = 15;
	elif(booksPurchased == 3):
		points = 30;
	else:
		points = 60;

	print('Points: ', points);

main();
