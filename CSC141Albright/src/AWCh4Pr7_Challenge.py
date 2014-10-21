# Anthony Wittemann
# 10/29/13
# Chapter 4, programming exercises #7, 8, 9, 10, 11
# 7 - Circle Intersection

from graphics import *
import math


def main():
    radius = int(input('Enter the radius of the circle from 0-10: '))
    yInt = int(input('Enter the y intercept of the line from -10 to 10: '))

    # set everything up
    win = GraphWin('Anthony Wittemann 4.7', 400,400)
    win.setCoords(-10,-10,10,10)
    shape = Circle(Point(0,0), radius)
    line = Line(Point(-10, yInt), Point(10, yInt))

    #calculate intersection
    r2y2 = radius**2 - yInt**2
    if r2y2 < 0:
        pointsText = 'The line and the circle do not intersect'
        Text(Point(0,7), pointsText).draw(win)
    else:
        xIntersection = math.sqrt(r2y2)
        intPt1 = Point(xIntersection, yInt)
        intPt2 = Point(-xIntersection, yInt)
        intPt1.setFill('red')
        intPt2.setFill('red')

        intPt1.draw(win)
        intPt2.draw(win)
    
        #display x coordinates text
        pt1Str = str(format(intPt1.getX(),'.2f'))
        pt2Str = str(format(intPt2.getX(),'.2f'))
        pointsText = 'X values of intersection: ' + pt1Str + ' , ' + pt2Str
        Text(Point(0,7), pointsText).draw(win)

        #print('X values of intersection: ', format(intPt1.getX(),'.2f'), ' , ', format(intPt2.getX(),'.2f'))

    #display objects in window
    shape.draw(win)
    line.draw(win)
    
    
main()
