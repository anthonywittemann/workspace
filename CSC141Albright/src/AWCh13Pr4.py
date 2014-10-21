# Anthony Wittemann
# 1/7/14
# Chapter 13 Program 4 - Zelle


def main():
    list1 = [1, 2, 3, 4, 5, 6, 7, 123, 432, 63546, 32589, 23901, 14891, 8219, 1289, 1902312094, 97865645678, 9]
    sortedList = mergeSort(list1)
    theMax = sortedList[-1]
    print('The maximum value is ', theMax)
    
# Use merge sort to order the list,
# the last value is the max... 
# I believe it's more computationally efficient to sort the list
# than to blindly compare an unordered list recursively  
def mergeSort(nums):
    l = len(nums)
    if l > 1:
        m = l//2
        nums1, nums2 = nums[:m], nums[m:]
        mergeSort(nums1)
        mergeSort(nums2)
        merge(nums1, nums2, nums)
    return nums    

def merge(list1, list2, list3):
    i1, i2, i3 = 0, 0, 0
    
    len1, len2 = len(list1), len(list2)
    
    while i1 < len1 and i2 < len2:
        if list1[i1] < list2[i2]:
            list3[i3] = list1[i1]
            i1 += 1
        else:
            list3[i3] = list2[i2]
            i2 += 1
        i3 += 1
        
    while i1 < len1:
        list3[i3] = list1[i1]
        i1 += 1
        i3 += 1
    while i2 < len2:
        list3[i3] = list2[i2]
        i2 += 1
        i3 += 1 
    
    
main()