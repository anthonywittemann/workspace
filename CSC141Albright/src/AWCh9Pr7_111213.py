# Anthony Wittemann
# 11/7/13
# # 7 Character Analysis

def main():
    try:
        textFile = open('text.txt', 'r')
        textLines = textFile.readlines()
        textFile.close()
    
        uCount = 0
        lCount = 0
        dCount = 0
        wCount = 0
        
        for line in textLines:
            for char in line:
                if char.isupper():
                    uCount += 1
                elif char.islower():
                    lCount += 1
                elif char.isdigit():
                    dCount += 1
                elif char.isspace():
                    wCount += 1
                else:
                    raise ValueError
                    
                
    except IOError:
        print('Error opening the text file')
    except ValueError:
        print('Please enter only spaces, and alphanumeric characters')
    except:
        print('Error processing the file')
        
        
    
            
    
main()