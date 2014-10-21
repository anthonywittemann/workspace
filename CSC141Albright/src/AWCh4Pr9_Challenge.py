# Anthony Wittemann
# 10/29/13
# Chapter 4, programming exercises #7, 8, 9, 10, 11
# 9 - Rectangle Information

from graphics import *
import math


def main():
    win = GraphWin('Anthony Wittemann 4.9', 400,400)
    win.setCoords(-200, -200, 200, 200)
    p1 = win.getMouse()
    p2 = win.getMouse()
    
    length = abs(p1.getX() - p2.getX())
    width = abs(p1.getY() - p2.getY())
    area = format(length * width, '.2f')
    perimeter = format(2 * (width + length), '.2f')
    
    rect = Rectangle(p1,p2)
    rect.draw(win)
    
    areaTxt = str('Area: ' + area + ' square pixels')
    perimTxt = str('Perimeter: ' + perimeter + ' pixels')
    Text(Point(0, 150), areaTxt).draw(win)
    Text(Point(0, 100), perimTxt).draw(win)
    
    
main()
