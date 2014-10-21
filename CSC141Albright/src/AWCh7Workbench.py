# Anthony Wittemann
# 10/29/13
# Chapter 7 Workbench

# 1
	my_name = open('my_name.txt', 'w')
	my_name.write('Anthony Wittemann \n')
	my_name.close()
	
# 2
	my_name = open('my_name.txt', 'r')
	print(my_name.readline())
	my_name.close()
	
# 3
	output = open('number_list.text', 'w')
	for num in range(1,101):
		output.write(str(num) + '\n')
	output.close()
	
# 4
	nums = open('my_name.txt', 'r')
	numbers = nums.readlines()
	for num in numbers:
		print(num)
	nums.close()
	
# 5
	nums = open('my_name.txt', 'r')
	total = 0
	numbers = nums.readlines()
	for num in numbers:
		total += int(num)
		print(num)
	nums.close()
	print('Total: ', total)
	
# 6
	output = open('number_list.text', 'r')
	erase = False
		for r in range(1,101):
			if(int(output.readline()) == r):
				break
			else:
				erase = True
	output.close()
	if(erase == True):
		oput = open('number_list.text', 'w')
		for num in range(1,101):
			oput.write(num + \n)
		oput.close()
	
# 7
	found = False
	student_file = open('students.txt', 'r')
	temp_file = open('temp.txt', 'w')
	student = student_file.readline()
	
	while student != '':
		score = int(student)
		stu = student.rstrip('\n')
		if student != 'John Perz':
			temp_file.write(stu + '\n')
			temp_file.write(str(score) + '\n')
		else:
			found = True
		student = student_file.readline()
		
	student_file.close()
	temp_file.close()
	os.remove('students.txt')
	os.rename('temp.txt', 'students.txt')
	if found == True:
		print('John Perz's record has been deleted')
	else:
		print('John Perz's record has not been found')
	
# 8
	found = False
	student_file = open('students.txt', 'r')
	temp_file = open('temp.txt', 'w')
	student = student_file.readline()
	
	while student != '':
		score = int(student)
		stu = student.rstrip('\n')
		if student != 'Julie Milan':
			temp_file.write(stu + '\n')
			temp_file.write(str(score) + '\n')
		else:
			found = True
			temp_file.write(stu + '\n')
			temp_file.write(str(100) + '\n')
		student = student_file.readline()
		
	student_file.close()
	temp_file.close()
	os.remove('students.txt')
	os.rename('temp.txt', 'students.txt')
	if found == True:
		print('Julie Milan's score has been changed')
	else:
		print('Julie Milan's record has not been found')
	