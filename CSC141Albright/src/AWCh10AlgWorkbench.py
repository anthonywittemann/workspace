# Anthony Wittemann
# 11/14/13
# Chapter 10 Algorithm Workbench # 1-4

def main():
    # 1
    dict = {'a':1, 'b':2, 'c':3}

    # 2
    emptyDict = {}
    
    dct = {'James': 'This is the message you should get', 'Jim': 'This is the other message you should get'}

    # 3
    if 'James' in dct:
        print(dct['James'])
    else:
        print('James was not found in this dictionary')
    
    # 4
    if 'Jim' in dct:
        del dct['Jim']
    else:
        print('Jim was not found in this dictionary')
    
main()