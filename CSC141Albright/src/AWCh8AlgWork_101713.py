# Anthony Wittemann
# 10/17/13
# Alg. Workbench pg. 338 1,2,3,5 -- Prog. Ex. 1-4

# 1
scientists = ['Einstein', 'Newton', 'Copernicus', 'Kepler']

#2
for n in names:
    print(names[n])

#3
numbers2 = numbers1


#5
def funct(daList):
    total = 0
    d = 0
    while d < len(daList):
        total += daList[d]
        d +=1
    return total
