# Anthony Wittemann
# 10/17/13
# Prog. Ex. 1-4

#4 - Number Analysis

def main():
    nums = [0] * 20
    n = 1
    while n < len(nums) + 1:
        print('Enter number ', n, sep='', end='\n')
        try:
            nums[n] = float(input())
        except ValueError:
            print('Please enter a valid number')
        except:
            print('Please enter a valid number')
        n += 1

    u = 0
    total = 0.0
    highest = nums[0]
    lowest = nums[len(nums) - 1]
    hIndex = 0
    lIndex = len(nums) - 1
    while u < len(nums):
        
        if nums[u] > highest:
            highest = nums[u]
            hIndex = u
        elif nums[u] < lowest:
            lowest = nums[u]
            lIndex = u
            
        total += nums[u]
        u +=1
        
    avg = total / len(nums) 
    
    print('The lowest number is: ', lIndex, ' with a value of ', lowest)
    print('The highest number is: ', hIndex, ' with a value of ', highest)
    print('The total is: ', format(total, '.2f'))
    print('The average is: ', format(avg, '.2f'))

main()
