import cv2
import numpy as np
import math

img = cv2.imread('bone1.jpg')
img=cv2.blur(img,(3,3))
gray = cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)
th,dst=cv2.threshold(img,200,250,cv2.THRESH_BINARY)
dst=cv2.dilate(dst,(7,7),iterations=3)
dst=cv2.erode(dst,(7,7),iterations=3)
cv2.imshow('sthresh',dst)
edges = cv2.Canny(dst,100,400,apertureSize =3)
cv2.imshow('canny',edges)
im2, contours, hierarchy = cv2.findContours(edges,cv2.RETR_TREE,cv2.CHAIN_APPROX_SIMPLE)
cv2.drawContours(img, contours, -1, (0, 255, 0), 3)
cv2.imshow('images 2',img)
for c in contours :
    perimeter=cv2.arcLength(c,True)
    approx=cv2.approxPolyDP(c,0.05*perimeter,True)
    cv2.drawContours(img, approx, -1, (0, 0, 255), 7)
    cv2.imshow('images 3',img)

cv2.waitKey(0)