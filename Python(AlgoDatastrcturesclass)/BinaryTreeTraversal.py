# [Zachary Jones Smith]
#  P8 - Create Min & Max functions for a Binary Tree

# A utility class that represents 
# an individual node in a BST
class Node:
    def __init__(self, key):
        self.left = None
        self.right = None
        self.val = key

# A utility function to insert 
# a new node with the given key


def insert(root, key):
    if root is None:
        return Node(key)
    else:
        if root.val == key:
            return root
        elif root.val < key:
            root.right = insert(root.right, key)
        else:
            root.left = insert(root.left, key)
    return root

def get_min(self):
    
    if self.left == None: #if the next left node == None then you have reached the smallest value in the tree
        return self.val #return that value
    else:
        return get_min(self.left) #return a recursive call the get_min() function on the next left node to travserse the tree. 

def get_max(self):
    
    if self.right == None: #if the next right node == None then you have reached the largest value in the tree
        return self.val # return the current value
    else:
        return get_max(self.right) #return a recursive call to the get_max() function using the next right node

import statistics

numList = [45, 32, 12, 4, 98, 54, 99, 17, 41, 14]
median = statistics.median(numList)

# Evaluate the code in the next 6 lines and let me know why it's needed
#The next 6 lines of code are needs to calculate the median value of the list when there is an even number of items in the list
#The median is normally = to the middle value in the list, so when there is no direct middle value this is the best solution
#as it loops through it finds the value closest to the calculated median provided by the statistics module (36.5)
if (len(numList) % 2 == 0): #if len(list) is even (so no direct middle value)
    closestToMedian = abs(numList[0]-median) #closestToMedian is the absolute value of the first value in list - the median
    for num in numList: #loop through list 
        if (abs(num-median) < abs(closestToMedian-median)): #if abs(the num - median) greater than abs(closestToMedian - median)
            closestToMedian = num #reassign the value of clostToMedian to current num
    median = closestToMedian #reassign median to the closestToMedian value

root = Node(median)
for num in numList:
    insert(root, num)


print ("Minimum= ", get_min(root))
print ("Maximum= ", get_max(root))

