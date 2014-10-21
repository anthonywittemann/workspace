# Anthony Wittemann
# 10/01/13
# 6 Celsius to Fahrenheit Table

def main():
    for r in range(0,21):
        cels = r;
        fahr = 9.0 * cels / 5.0 + 32.0;
        print('Degrees Fahrenheit: ', fahr, '          Degrees Celsius: ', cels);
        
main();
