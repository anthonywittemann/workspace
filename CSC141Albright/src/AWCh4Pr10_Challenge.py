# Anthony Wittemann
# 10/29/13
# Chapter 4, programming exercises #7, 8, 9, 10, 11
# 10 - Triangle Information

from graphics import *
import math


def main():
    win = GraphWin('Anthony Wittemann 4.10', 400,400)
    win.setCoords(-200, -200, 200, 200)
    p1 = win.getMouse()
    p2 = win.getMouse()
    p3 = win.getMouse()
    
    dxA = p1.getX() - p2.getX()
    dxB = p2.getX() - p3.getX()
    dxC = p3.getX() - p1.getX()
    dyA = p1.getY() - p2.getY()
    dyB = p2.getY() - p3.getY()
    dyC = p3.getY() - p1.getY()
    
    lenA = math.sqrt(dxA**2 + dyA**2)
    lenB = math.sqrt(dxB**2 + dyB**2)
    lenC = math.sqrt(dxC**2 + dyC**2)
    s = (lenA + lenB + lenC)/2
    
    area = math.sqrt(s * (s-lenA) * (s-lenB) * (s-lenC))
    perimeter = lenA + lenB + lenC
    
    triangle = Polygon(p1,p2,p3)
    triangle.draw(win)
    
    areaTxt = str('Area: ' + format(area, '.2f') + ' square pixels')
    perimTxt = str('Perimeter: ' + format(perimeter,'.2f') + ' pixels')
    Text(Point(0, 150), areaTxt).draw(win)
    Text(Point(0, 100), perimTxt).draw(win)
    
    
main()
