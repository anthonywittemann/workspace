# Anthony Wittemann
# 1/7/14
# Chapter 13 Program 1 - Zelle

# I know I'm using a global variable, but it seems to be the only way  
# to keep track of the number of times that fib(3) has been called 
count3 = 0

def main():
    print(str(fib(10)), ' fib(3) has been called ', count3, ' times.')
    
def fib(n):
    global count3
    if(n == 3):
        count3 += 1
    if n < 3:
        return 1
    else:
        print('Computing fib(', n, ')')
        n1 = fib(n-1) + fib(n-2)
        print('Leaving fib(',   n , ')    Returning ', n1)
        return n1

main()