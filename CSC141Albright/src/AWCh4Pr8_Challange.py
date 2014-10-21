# Anthony Wittemann
# 10/29/13
# Chapter 4, programming exercises #7, 8, 9, 10, 11
# 8 - Line Segment Information

from graphics import *
import math


def main():
    win = GraphWin('Anthony Wittemann 4.8', 400,400)
    win.setCoords(-200, -200, 200, 200)
    p1 = win.getMouse()
    p2 = win.getMouse()
    line = Line(p1,p2)
    
    dx = p2.getX() - p1.getX()
    dy = p2.getY() - p1.getY()
    
    midpoint = Point((p1.getX() + p2.getX())/2, (p1.getY() + p2.getY())/2)
    midpoint.setFill('cyan')
    
    midpoint.draw(win)
    line.draw(win)
    if dx != 0:
        slope = dy/dx       
    else:
        slope = 99999999999
    length = math.sqrt(dx**2 + dy**2)

    # get text into one string 
    lTStr = str(format(length,'.2f'))
    sTStr = str(format(slope,'.2f'))
    lengthText = 'Line Length: ' + lTStr + ' pixels'
    slopeText = 'Line Slope: ' + sTStr

    #display length and slope text on window
    Text(Point(0, 150), lengthText).draw(win)
    Text(Point(0, -150), slopeText).draw(win)

#    print('The length of the line is: ', format(length,'.2f'), ' pixels.')
#    print('The slope of the line is: ', format(slope,'.2f'))
    
main()
