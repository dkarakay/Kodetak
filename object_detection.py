import sys
import os ; os.environ['HDF5_DISABLE_VERSION_CHECK']='2'
import cv2
import time
import matplotlib.pyplot as plt
import cvlib as cv
from cvlib.object_detection import draw_bbox

def max_area(arr):
    area = 0
    coord = 0
    areas = []
    coords = []
    for arri in arr:
        area = abs(arri[0] - arri[2]) * abs(arri[1] - arri[3])
        areas.append(area)
        coord = (arri[0] + arri[2]) / 640
        if coord > 1.15:
            coord = coord - 2.2
        coords.append(coord)
    if(len(areas)<1):
        return -1,-1,-1
    else:  
        return max(areas), areas.index(max(areas)), coords[areas.index(max(areas))]
cap = cv2.VideoCapture(0)
#cap = cv2.VideoCapture("http://a:1@192.168.43.1:8080/video")
time.sleep(1)  ### letting the camera autofocus

while True:
    _, im = cap.read()
    bbox, label, conf = cv.detect_common_objects(im) 
    print(bbox)
    maxval, maxindex, coord = max_area(bbox)
    if (coord<-1):
        coord = -1
    elif (coord>1):
        coord = 1
    if maxindex == -1:
        print("no object")
    else:
        print(bbox[maxindex], label[maxindex], coord * maxval)
    output_image = draw_bbox(im, bbox, label, conf)
    cv2.imshow('Frame',output_image)
    cv2.waitKey(50)
    k = cv2.waitKey(30) & 0xff
    if k==27:
            break
# Release the VideoCapture object
cap.release()
