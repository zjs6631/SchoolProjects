#[CSC249 - P6Smith - Zachary Smith]
class filelystIterator(object):

    def __init__(self, lyst):
    #initializes the iterators starting state
        self.lyst =lyst
        self.first()
    
    def first(self):
    #resets cursor to beginning of lyst
        self.cursor = 0
        self.lastItemPos = -1
    
    def hasNext(self):
    #checks if there is a value next to the cursor
        return self.cursor < len(self.lyst)

    def next(self):
    #first checks to make sure hasNext is true or that 
        if not self.hasNext():
            raise ValueError("There is not a value next in lyst")
    #moves the cursor foward by one index
        self.lastItemPos = self.cursor
        self.cursor += 1
    #returns item that was next to the cursor to the right
        return self.lyst[self.lastItemPos]
    
    def last(self):
    #sets cursor to end of the lyst (length of the lyst)
        self.cursor = len(self.lyst)
        self.lastItemPos = -1
    def hasPrevious(self):
    #makes sure the cursor value is greater than 0 meaning there is an
    #index before it
        return self.cursor > 0
    
    def previous(self):
    #checks to make sure hasprevious is true
        if not self.hasPrevious():
            raise ValueError("No previous items in lyst")
    #moves cursor positions to the left by one
    #sets the lastItemPos to the cursors location
    #returns the item located where the cursor last was    
        self.cursor -= 1
        self.lastItemPos = self.cursor
        return self.lyst[self.lastItemPos]

    def replace(self, item):
    #checks if cursor is at end of lyst or "undefined"
        if self.lastItemPos == -1:
            raise AttributeError("Current position undefined")
    #sets the last item position = to the new item    
        self.lyst[self.lastItemPos] = item
    #sets the last item position to -1
        self.lastItemPos = -1

    def insert(self, item):   
        #if the last item position is -1 (end of lyst) the new item is appended
        if self.lastItemPos == -1:
            self.lyst.append(item)
        #else it is inserted using the insert lyst function
        else:
            self.lyst.insert(self.lastItemPos,item)
        #last item position is set to -1
        self.lastItemPos = -1
        
def main():
    #I get a filename from the user until they input a txt file
    filename = input("Please enter the name of a text file you would like to view. The name must end in .txt: ")
    while filename[-4:] != ".txt":
        filename = input("The file must be a text file! Try again:")
    #I process the file name with the file handler function
    filelyst = filehandler(filename)
    #I print the contents of the file list
    print(filelyst)
    #I create an instance of the filelystiterator class using the file list
    iteratedlyst = filelystIterator(filelyst)
    #I set the menu on a loop until the user chooses to exit with or without saving
    again = 'y'
    while again == 'y':
        displaymenu() #I display the menu
        menuchoice = input("Please enter operation selection:") #take their input choice
        if menuchoice == "<":
            iteratedlyst.first() #move cursor to first position
            if iteratedlyst.hasNext():
                item = iteratedlyst.next() #print next item, which will be the first item
                print(item)
            again = 'y'
        elif menuchoice == ">":
            iteratedlyst.last() #moves cursor to end of list
            if iteratedlyst.hasPrevious():
                item = iteratedlyst.previous() #prints the last item
                print(item)
            iteratedlyst.cursor+=1
            again = 'y'
        elif menuchoice == "+":
            if iteratedlyst.hasNext(): #prints the next item
                item = iteratedlyst.next()
                print(item)
            if not iteratedlyst.hasNext():
                print("You are at the end of this lyst.") #If at end of list inform the user
            again = 'y'
        elif menuchoice == "-":
            if iteratedlyst.hasPrevious():
                item = iteratedlyst.previous() #prints previous item
                print(item)
            if not iteratedlyst.hasPrevious():
                print("You are at the beginning of the lyst.") #if at beginning of list inform the user
            again = 'y'
        elif menuchoice == 'M' or menuchoice == 'm':
            item = input("enter the new item to modify this line:") #modifies the currently displayed line
            iteratedlyst.replace(item)
            again = 'y'
        elif menuchoice == 'I' or menuchoice == 'i':
            item = input("Enter line to enter as next line:") 
            
            if iteratedlyst.hasNext(): #if not at end of list then insert the item
                iteratedlyst.next() 
                iteratedlyst.lyst.insert(iteratedlyst.lastItemPos, item)
            
            if iteratedlyst.cursor == len(iteratedlyst.lyst): #if at end of list then append to the end
                iteratedlyst.lyst.append(item)
                   
            again = 'y'
        elif menuchoice == 'V' or menuchoice == 'v': #displays the current list
            iteratedlyst.first()
            print("This is the current lyst:")
            while iteratedlyst.hasNext():
                item = iteratedlyst.next()
                print(item)
        elif menuchoice == "X" or menuchoice == 'x': #saves the file as Smith.txt
            newfile = open("Smith.txt", 'w')
            iteratedlyst.first()
            while iteratedlyst.hasNext():
                line = iteratedlyst.next()
                newfile.write(line + "\n") #add \n to add to file on separate lines
            newfile.close
            print("Changes saved under file name Smith.txt")
            again = 'n'#end loop
        elif menuchoice == "Z" or menuchoice == "z":
            print("Changes not saved.")
            again = 'n' #end loop

        else:
            print("invalid menu entry.") #menu validation
            again = 'y'
    return again


import os
def filehandler(filename):
    filelyst=[] #make the empty list
    
    if os.path.exists(filename): #if the file exists open to read and readline into the empty list
        openedfile = open(filename, "r")
        line = openedfile.readline()
        if line == "":
            print("The file exists but is empty.")
        
        while line != "":
            filelyst.append(line.rstrip("\n")) #strip the \n characters also
            line = openedfile.readline()
        
        openedfile.close() 
    
    else:
        openedfile = open(filename, "a") #if it doesnt exist then open a new file and write dummy text
        openedfile.write("This is the first line\nThis is the second line\nThis is the third line\n This is the fourth line \n This is the fifth line.")
        openedfile.close()
        openedfile = open(filename, "r") #open the newly made file to read and readline into list
        line = openedfile.readline()
        if line == "":
            print("The file exists but is empty.")
        while line != "":
                filelyst.append(line.rstrip("\n")) #strip \n characters again
                line = openedfile.readline()
        
        openedfile.close()
    return filelyst #return the processed file as a list

#displaymenu() prints menu for the user to see 
def displaymenu():
    print("Operations:")
    print("      Enter < to go to first line")
    print("      Enter > to go to last line")
    print("      Enter + to go to next line")
    print("      Enter - to go to previous line")
    print("      Enter M to modify current line")
    print("      Enter I to insert a new line after current line")
    print("      Enter V to view the current file changes")
    print("      Enter X to save file and exit program")
    print("      Enter Z to exit program without saving")


main()




