# Anthony Wittemann
# 10/29/13
# Chapter 4, programming exercises #7, 8, 9, 10, 11
# 11 - Five-click House

from graphics import *
import math


def main():
    win = GraphWin('Anthony Wittemann 4.11', 400,400)
    win.setCoords(-200, -200, 200, 200)
    
    p1 = win.getMouse()
    p2 = win.getMouse()
    #Rectangular Frame of the house
    rectFrame = Rectangle(p1,p2)
    rectFrame.draw(win)
    
    
    p3 = win.getMouse()
    #door
    p1D = Point(p3.getX() - (p1.getX() - p2.getX())/10, p3.getY())
    p2D = Point(p3.getX() + (p1.getX() - p2.getX())/10, p1.getY())
    rectDoor = Rectangle(p1D,p2D)
    rectDoor.draw(win)
    
    
    p4 = win.getMouse()
    #window
    p1W = Point(p4.getX() - (p1.getX() - p2.getX())/20, p4.getY() + (p1.getX() - p2.getX())/20)
    p2W = Point(p4.getX() + (p1.getX() - p2.getX())/20, p4.getY() - (p1.getX() - p2.getX())/20)
    rectWindow = Rectangle(p1W,p2W)
    rectWindow.draw(win)
    
    
    p5 = win.getMouse()
    #roof
    p1R = p5
    p2R = p2
    p3R = Point(p1.getX(), p2.getY())
    triangleRoof = Polygon(p1R,p2R,p3R)
    triangleRoof.draw(win) 
    
main()
