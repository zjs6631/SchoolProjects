def main():
    again = 'y'#I set a while loop to keep the program going until a valid menu#
    #option is selected#

    while again == 'y' or again == 'Y':
        #prints the menu for the user to know what they can select#
        print("Press 1 to verify the check digit of an ISBN-10.")
        print("Press 2 to verify the check digit of an ISBN-13.")
        print("Press 3 to convert an ISBN-10 to an ISBN-13.")
        print("Press 4 to convert an ISBN-13 to an ISBN-10.")
        print("Press 5 to exit the program.")
        menuselection=int(input("Please input your selection:"))
        #use an if/elif statement to call the proper function for whatever#
        #the user is wanting to do#
        if menuselection == 1:
            check10()
            again=str(input("Enter y or Y to use this program again."))
            if again != 'y' and again!= 'Y':
                print("Thank you for using this program.")
                
        elif menuselection == 2:
            check13()
            again=str(input("Enter y or Y to use this program again."))
            if again != 'y' and again!= 'Y':
                print("Thank you for using this program.")
            
        elif menuselection == 3:
            convert10to13()
            again=str(input("Enter y or Y to use this program again."))
            if again != 'y' and again!= 'Y':
                print("Thank you for using this program.")
                
        elif menuselection == 4:
            convert13to10()
            again=str(input("Enter y or Y to use this program again."))
            if again != 'y' and again != 'Y':
                 print("Thank you for using this program.")
    
        elif menuselection == 5:
            #5 exits the program by printing a statement#
            print("Thank you for using this program.")
            again = 'n'
        else:
            #If an entry not mentioned is entered then a message is printed#
            #and the user must enter y/Y to try again#
            print("Invalid menu entry.")
            again=str(input("Enter y or Y to use this program again."))
    

    
            
        
def check10():
#check10() checks to make sure the check digit is valid for the ISBN entered#   
    userinput='prime'#I prime the while statement, so the code will run atleast once#
    while len(userinput) != 10:
        #I have the user enter the ISBN and remove the dashes#
        userinput=str(input("please enter the ISBN-10 number you wish to check:"))
        userinput=(userinput.replace("-",""))
        #if there are 10 digits in the ISBN after dashes are removed I place#
        #each digit in a list#
        if len(userinput) == 10:
            index=0
            end=1
            formatted_list=[]
            while index < len(userinput):
                for ch in userinput:
                    formatted_list.append(userinput[index:end])
                    index+=1
                    end+=1
            check=int(formatted_list[-1])#The last digit in the list is assigned to the variable 'check'#
            count=0
            num=0
            multiple=1
            for range in formatted_list[0:9]:   
                #for every digit besides the check digit  I use the formula to calculate what the#
                #check digit should be#
                count+=(int(formatted_list[num])*multiple)
                num+=1
                multiple+=1
            checknumberverified=count%11 
            if check != checknumberverified:
            #if the check number of the ISBN entered does not == the check number calculated I change the#
            #final index in the list to be the calculated check number#
                formatted_list[-1]=checknumberverified
                #I enter the dashes back into the list#
                formatted_list.insert(1,"-")
                formatted_list.insert(4,"-")
                formatted_list.insert(11,"-")
                newstr=''.join(str(num) for num in formatted_list)
                #then I join each entry in the list into a string named newstr#
                print("The correct formatted ISBN number is:",newstr)
                print("The correct check digit for your ISBN number is:",checknumberverified)
                #print the correct ISBN name and the correct check digit back to the user#
            else:
                #if the check digits do match I enter the dash back into the list#
                #I then join the entries back together as a string and return that to the user#
                #I also state that the ISBN they entered is valid#
                formatted_list.insert(1,"-")
                formatted_list.insert(4,"-")
                formatted_list.insert(11,"-")
                newstr=''.join(str(num) for num in formatted_list)
                print("You entered:",newstr)
                print("Your ISBN is valid.")

        else:
            #If there are not 10 digits then I print the ISBN is formatted improperly# 
            print("The ISBN entered is formatted improperly.")
def check13():
#check13() is very similar to check10()#
    userinput='prime'#prime the userinput to make the function run once#
    while len(userinput) != 13:
        userinput=str(input("please enter the ISBN-13 number you wish to check:"))
        userinput=(userinput.replace("-",""))
        #removes the dashes from the user input#
        if len(userinput) == 13:
            #if there are 13 digits I separate them into a list#
            index=0
            end=1
            formatted_list=[]
            while index < len(str(userinput)):
                for ch in userinput:
                    formatted_list.append(userinput[index:end])
                    index+=1
                    end+=1
            check=int(formatted_list[-1])#I check the check digit of the number entered#
            count=0
            num=0
            #create odd/even for the 13 digit check digit formula#
            odd=3#set odd to 3#
            even=1#set even to 1#
            for index in formatted_list[:-1]:
                if num == 0 or num%2 == 0:
                    #if the index is even or == 0 I multiply the content of that index by 1#
                    count+=(int(formatted_list[num])*even)
                    num+=1
                else:
                    #if the index is odd I multiply the content of that index by 3#
                    count+=(int(formatted_list[num])*odd)
                    num+=1
            #calculate the MOD of the sum of the multiplied digits to get the calculated check digit#
            checknumberverified=10-(count%10)
            #if the check number entered != the check number calculated I assign the last#
            #index in the list the calculated check value then I put digits in the list#
            #back together by joining them into a string and added the dashes#
            if check != checknumberverified:
                formatted_list[-1]=checknumberverified
                formatted_list.insert(3,"-")
                formatted_list.insert(5,"-")
                formatted_list.insert(8,"-")
                formatted_list.insert(15,"-")
                newstr=''.join(str(num) for num in formatted_list)
                print("The correct ISBN number would be:",newstr)
            else:
                #If they are == then I join the list indexes and assign them to a string and#
                #print a message back to the user stating their ISBN is valid#
                formatted_list.insert(3,"-")
                formatted_list.insert(5,"-")
                formatted_list.insert(8,"-")
                formatted_list.insert(15,"-")
                newstr=''.join(str(num) for num in formatted_list)
                print("You entered:",newstr)
                print("The ISBN is valid.")
        else:
            #if the ISBN digits entered != 13 I print this message#
            print("The ISBN you entered in improperly formatted.")

def convert10to13():
#convert10to13() converts 10 digit ISBNs to 13 digit ISBNs#

    userinput='prime'#I prime the userinput to make the program run once#
    while len(userinput) != 10:
        userinput=str(input("Please enter a ISBN-10 to convert:"))
        userinput=(userinput.replace("-",""))#remove the dashes from user input#
        formatted_list=[]#I make an empty list#
        if len(userinput) == 10:#once the length of userinput==10 I add those digits to a list#
            index=0
            end=1
            
            while index < len(userinput):
                for ch in userinput:
                    formatted_list.append(userinput[index:end])
                    index+=1
                    end+=1
            #I insert 9, 7, and 8 into the list at the beginning of the list#
            #since these values are at the beginning of a 13 digit ISBN#
            formatted_list.insert(0,"9")
            formatted_list.insert(1,"7")
            formatted_list.insert(2,"8")
            
            
            count=0
            num=0
            odd=3
            even=1
            #using the formula to calculate a 13 digit ISBN check digit I use odd/even again#
            #for each index in the list except the last index#
            #I keep a sum of the result of each multiplication using a variable named 'count'#
            for index in formatted_list[:-1]:
                    if num == 0 or num%2 == 0:
                        count+=(int(formatted_list[num])*(even))
                        num+=1
                    else:
                        count+=(int(formatted_list[num])*(odd))
                        num+=1
            newchecknumber=10-(count%10)#I finish calculated the check digit by finding the MOD of the 'count'#
            #set the value of the last index to the new check number#
            formatted_list[-1]=(newchecknumber)#I set the last index of the list to be equal to the new calculated check number#
            formatted_list.insert(3,"-")
            formatted_list.insert(5,"-")
            formatted_list.insert(8,"-")
            formatted_list.insert(15,"-")
            #I insert the dashes into the list#
            #I join the indexes in the list into a string named 'convertedISBN'#
            convertedISBN=''.join(str(num) for num in formatted_list)
            print("The ISBN-13 number is",convertedISBN)
        else:
            #if the digits of the userinput are not == 10 this is printed#
            print("The ISBN you entered is improperly formatted.")

def convert13to10():
#convert13to10() is similar to convert10to13() except it uses the formula for a 10 digit ISBN#
    userinput='prime'#I prime the user input so the code will run at least once#
    while len(userinput) != 13:
        userinput=str(input("Please enter a ISBN-13 to convert:"))
        userinput=(userinput.replace("-",""))#remove the dashes#
        formatted_list=[]#make an empty list#
        
        if len(userinput) == 13:
            index=0
            end=1
            #place each digit in the userinput into a list#
            while index < len(userinput):
                for ch in userinput:
                    formatted_list.append(userinput[index:end])
                    index+=1
                    end+=1
            #I use this if statement as a check to make sure a valid 13 digit ISBN has been entered$
            #since the first 3 digits should be 9, 7 , and 8#
            if formatted_list[0] == "9" and formatted_list[1] == "7" \
               and formatted_list[2] == "8":
                #I remove the first instance of these values#
                #which should be at the beginning if a valid number was entered#
                formatted_list.remove("9")
                formatted_list.remove("7")
                formatted_list.remove("8")
                

                count=0
                num=0
                multiple=1
                #I use the formula to calculate a check digit for a 10 digit ISBN#
                for range in formatted_list[0:9]:   
                    count+=(int(formatted_list[num])*multiple)
                    num+=1
                    multiple+=1
                
                checknumber=count%11
                #I set the last index in the list to be the newly calculated check number#
                formatted_list[-1]=checknumber
                formatted_list.insert(1,"-")
                formatted_list.insert(4,"-")
                formatted_list.insert(11,"-")
                #I added the dashes back and join the list indexes to a string named newstr#
                newstr=''.join(str(num) for num in formatted_list)
                print("The ISBN-10 number is:",newstr)
            else:
                print("The first 3 digits must be 978. Please try again.")
                userinput="invalid"
                
                

        else:
                #if the digits in userinput != 13 I print this and return a value that will make the program run again#
                print("Invalid 13 digit ISBN entered.")
                userinput="invalid"

            
            
    return userinput

main()
    #I call main#
