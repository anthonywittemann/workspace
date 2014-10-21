# Anthony Wittemann
# 1/7/14
# Chapter 13 Program 2 - Zelle

class FibCount:
    
    def __init__(self, n):
        self.__FibCounter = 0
        self.__nthFN = self.fib(n)
       
    def getCount(self):
        return self.__FibCounter 
    
    def fib(self, n):
        self.__FibCounter += 1
        if n < 3:
            return 1
        else:
            return self.fib(n-1) + self.fib(n-2)
        
    def resetCount(self):
        self.FibCounter = 0
        
    def __str__(self):
        return str(self.__nthFN)

    


def main():
    acceptableInput = False
    # I'm assuming that the user enters an integer above 2
    nthV = 0
    while  not acceptableInput:
        try:
            nth = input('What Fibonacci number (integer above 2) would you like to calculate? ')
            nthV = int(nth)
        except ValueError:
            print('Only integers, please')
        if nthV > 2:
            acceptableInput = False
            break
    
    fibC = FibCount(nthV)
    
    print('The ', nthV, 'th Fibonacci number is: ', fibC, '      The fib() function was called ', str(fibC.getCount()), ' times.', sep='')

main()

