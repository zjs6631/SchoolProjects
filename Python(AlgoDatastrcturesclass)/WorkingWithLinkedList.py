# [Zachary Smith]
#  P9 - Employee Hash Table
# #4 is below the functions!

# The Hash Dictionary points to first element of the Employee Linked List with it's hash (0-11)

class HashDictionary:
    def __init__(self, hashLength):
        self.beginChain = []
        self.endChain = []
        for n in range(hashLength):
            self.beginChain.append(None)
            self.endChain.append(None)

# Employee Linked List 
# 
class Employee:
    def __init__(self, empID, empLastName, empFirstName, empPhone):
        self.empID = empID
        self.LastName = empLastName
        self.FirstName = empFirstName
        self.phone = empPhone
        self.ptr = None

    def insertEmployee(self, empID):
    #[provide insert function here]
        i = empID%12 #set the hash function to MOD(12)
        if HD.beginChain[i] == None: #if the beginchain == None then we set both chains to the object being inserted
            HD.beginChain[i] = self
            HD.endChain[i] = self
            self.ptr = None #set pointer of obj to None, since it's the first obj
        else:
            lastNode = HD.endChain[i] # assign address of last node to a variable
            lastNode.ptr = self #have its pointer point to new node
            self.ptr = None #set new nodes pointer to none
            HD.endChain[i] = self #endchain set to the new node

    def searchEmployee(self, empID):
    #[provide insert function here]
        i = empID%12 #MOD(12)
        if HD.beginChain[i] == None:
            return #if chain is empty then return
        currEmp = HD.beginChain[i] #start at beginning of the chain
        while currEmp.empID != empID: #while the empIDs != each other traverse the chain
            nextEmp = currEmp.ptr
            currEmp = nextEmp
            if nextEmp == None: #if ptr points to None and the empID has not been found then return
                return
        return currEmp #return the employee being searched for

    def deleteEmployee(self, empID, deletePtr):
    #[provide insert function here]
        i = empID%12 #MOD(12)
        if HD.beginChain[i] == None:
            return #if beginning is empty then no chain to search through
        currEmp = HD.beginChain[i] #start at beginning
        if currEmp.empID == empID: #added this to allow for deletion of the first item or beginchain at an index
            HD.beginChain[i] = currEmp.ptr
        nextEmp = currEmp.ptr # set the next emp to a variable
        while nextEmp.empID != empID: #since we are deleting we should look one space ahead
            currEmp = nextEmp #this will let us set up the pointers to pass over the employee being deleted
            nextEmp = currEmp.ptr
            if nextEmp == None:
                return 
        thirdEmp = nextEmp.ptr #used thirdEmp to hold the value of the emp after nextEmp
        if thirdEmp == None:
            currEmp.ptr = None #set pointer of curr emp to either None or the thirdEmp to pass over deleted employee
        else:
            currEmp.ptr = thirdEmp
    
    #---------------------------------------------------------------------------------------------------------------------------
    #---------------------------------------------------------------------------------------------------------------------------
    # 4. in order to allow the program to scale to a larger dataset you could increase the array size and utilize a more complex hash function
    #    to make it possible to spread the data out more efficiently and reduce density. You could possible base the function off of the first names. The book mentioned
    #    dropping the first letter of the name (to avoid common letters clustering) and subtracting the ASCII code of the last letter before the hash
    #    is calculated. 
    #---------------------------------------------------------------------------------------------------------------------------------
    # ---------------------------------------------------------------------------------------------------------------------------------     
        
def traverseChain(startEMP):
    print ("        ", startEMP.empID, startEMP.LastName)
    node = startEMP.ptr
    while (node != None):
        print ("        ", node.empID, node.LastName)
        node = node.ptr

from random import randint
import csv

hashLength = 12
empList = []

file = open('EmployeeDB.csv')
csvreader = csv.reader(file)
header = next(csvreader)
for employee in csvreader:
    empList.append(employee)

HD = HashDictionary(hashLength)

for empRecord in empList:
    empID = int(empRecord[0])
    empLastName = empRecord[1]
    empFirstName = empRecord[2]
    empPhone = empRecord[3]
    EMP = Employee(empID, empLastName, empFirstName, empPhone)
    EMP.insertEmployee(empID)
    
empIDin = input("Enter Employee ID (001-999, 0 to Exit): ")
empID = int(empIDin)
while (empID > 0 and empID < 1000):
    oper = input("Enter Operation Type (S)earch, (I)nsert, (D)elete: ")
    if (oper == "S"):
        E = EMP.searchEmployee(empID)
        if (E != None):
            print(E.empID, E.FirstName, E.LastName, E.phone)
        else:
            print(empID, " Doesn't Exist")
    elif (oper == "I"):
        E = EMP.searchEmployee(empID)
        if (E != None):
            print (empID, " Already Exists.", E.LastName, " Cannot Have Duplicates")
        else:
            fName = input("Enter Employee First Name: ")
            lName = input("Enter Employee Last Name: ")
            phoneNum = input("Enter Employee Phone Number: ")
            EMP = Employee(empID, lName, fName, phoneNum)
            EMP.insertEmployee(empID)
            print (EMP.empID, EMP.LastName, " Record Inserted")
    elif (oper == "D"):
        E = EMP.searchEmployee(empID)
        if (E != None):
            EMP.deleteEmployee(empID, E)
            print (E.empID, E.LastName, " Deleted")
        else:
            print(empID, " Not Found.  Cannot Delete")
    empIDin = input("Enter Employee ID (001-999, 0 to Exit): ")
    empID = int(empIDin)

for n in range(hashLength):
    print (n)
    if (HD.beginChain[n] != None):
        traverseChain(HD.beginChain[n])    
