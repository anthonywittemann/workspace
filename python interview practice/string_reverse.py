# Write a function to reverse a string in place.

# convert s to a list of chars since python strings are immutable,
# swap chars in list
# join at end and return that string
def reverse(s):
	last = len(s) - 1
	s_list = []
	s_list.extend(s)
	for i in range(len(s) / 2):
		s_list[i], s_list[last - i] = s_list[last - i], s_list[i]
	return ''.join(s_list)

# pythonic way
def reverse_pythonic(s):
	return s[::-1]

def main():
	print reverse('I am a god')
	print reverse(' ')
	print reverse(' iIi ')

	# print reverse_pythonic('I am a god')
	# print reverse_pythonic('')
	# print reverse_pythonic(' iIi ')


if __name__ == '__main__':
	main()