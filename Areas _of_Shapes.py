from math import pi

length = int(input("Enter the length of the shape: "))
width = int(input("Enter the width of the shape: "))
area = length * width
print(f"The area of the shape is {area}")

radius = int(input("Enter the radius of the shape: "))
area1 = pi * (radius * radius)
print(f"The area of the shape is {area1}")

base = int(input("Enter the base of the shape: "))
height = int(input("Enter the height of the shape: "))
area2 = (base * height)/2
print(f"The area of the shape is {area2}")