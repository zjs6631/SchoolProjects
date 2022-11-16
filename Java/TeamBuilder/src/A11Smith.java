//A11Smith - Final Project - 4/29/2022
//Sport team picker
//[Zachary Smith]


import java.util.Locale;
import java.util.Scanner; //import scanner
public class A11Smith {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); //create scanner obj
        int numOfStudents = 0; //number of students variable
        boolean isNum = true; //bool value to validate user input
        do { //do/while loop to make sure the user uses a valid value
            try { //try to take in the input from the user and parse it as an integer
                System.out.println("Please enter the number of students you would like to process (0 to exit): ");
                String strNumOfStudents = input.next();
                numOfStudents = Integer.parseInt(strNumOfStudents);
                if (numOfStudents == 0){
                    //if they enter 0 end the program
                    System.out.println("Program terminated");
                    return;
                } else if (numOfStudents <= 1){
                    //if the number is less than or equal to 1 inform the user
                    System.out.println("Number of students must be greater than 1.");
                }
                isNum = true; //set isNum to true if parsed successfully
            } catch (NumberFormatException e) { //catch a number format error
                System.out.println("Invalid entry."); //inform the user
                isNum = false; //set isNum to false
            }
        } while (isNum == false || numOfStudents <= 1); //if a value outside of range or a value that is not numeric is entered then
        //the loop will repeat

        PhysEd[] studentArray = new PhysEd[numOfStudents]; //create an array to hold student objects
        //size it determined by number of students entered initially

        //use a for loop to gather the student information for "i" number of students
        for (int i = 0; i < numOfStudents; i ++){
            //initialize boolean variables to track if valid entries are provided for name and skill points.
            boolean validName = true,
            validIntel = true,
                    validSpd = true,
                    validStr = true;

            String name = "1"; //initialize name variable
            //initialize skill variables
            int intelligence = 0,
                    speed = 0,
                    strength = 0;

            do{ // do/while loop to get a valid name value
                //take in a string to represent the name of a student
                System.out.println("Enter Student Name: ");
                name = input.next();

                char testChar; //test character variable created
                int charCounter = 0; //counter for characters that are not letters
                for (int j = 0; j < name.length(); j++){
                    //loop through the string and test each character to see if it is a letter
                    testChar = name.charAt(j);
                    if(!Character.isLetter(testChar)){
                        charCounter+=1;
                    }
                }
                //if any characters where not letters then it is not a valid name
                if(charCounter>0){
                    validName = false;
                } else {
                    //else it is a valid name
                    validName = true; //set bool to true
                    name = name.toLowerCase(Locale.ROOT); //set name variable to be all lowercase to make checking for..
                    //duplicates easier
                }

                if (validName == false){ //if the bool is false, inform the user that something is incorrect.
                    System.out.println("Invalid entry for name.");
                }
            } while (validName == false); //loop while an invalid name is provided

            do{ //do while loop to get a valid intelligence integer
                try{
                    //try to take in input from the user and parse it into an integer variable
                    System.out.println("Please enter an intelligence value (-10-100):");
                    String strIntel = input.next();
                    intelligence = Integer.parseInt(strIntel);
                    if (intelligence < -10 || intelligence > 100){
                        //if parsed value is outside valid range inform the user.
                        System.out.println("input outside of range.");
                    }
                    //set valid Intel to true if integer was entered
                    validIntel = true;
                } catch (NumberFormatException e) {
                    //catch format errors and inform the user. set the boolean var to false
                    System.out.println("Please enter a number value.");
                    validIntel = false;
                }
            }while(validIntel == false || (intelligence > 100 || intelligence < -10)); //if int is outside of range
            //or boolean val is false repeat the loop until the user enters a correct value.
            System.out.println("INTELLIGENCE ACCEPTED");
            do{ //do/while loop to get a valid speed value from the user
                try{
                    //try to take in input from the user and parse it into an integer variable
                    System.out.println("Please enter an speed value (-10-100):");
                    String strSpeed = input.next();
                    speed = Integer.parseInt(strSpeed);
                    if (speed < -10 || speed > 100){
                        //if the integer value is outside  the valid range then inform the user
                        System.out.println("input outside of range.");
                    }
                    //set bool to true if integer was entered
                    validSpd = true;
                } catch (NumberFormatException e) {
                    //catch format errors and inform the user
                    System.out.println("Please enter a number value.");
                    validSpd = false; //set bool to false
                }
            }while(validSpd == false || (speed > 100 || speed  < -10)); //if bool is false or int was outside of range
            //repeat the loop
            System.out.println("SPEED ACCEPTED");
            do{ //do/while loop to get a valid strength level
                try{
                    //take in input from user and parse to an integer value
                    System.out.println("Please enter an strength value (-10-100):");
                    String strStr = input.next();
                    strength = Integer.parseInt(strStr);
                    if (strength < -10 || strength > 100){
                        //if the integer value is outside range then inform the user
                        System.out.println("input outside of range.");
                    }
                    //get bool to true if an int was entered
                    validStr = true;
                } catch (NumberFormatException e) {
                    //catch format errors and inform the user and set bool to false
                    System.out.println("Please enter a number value.");
                    validStr = false;
                }
            }while(validStr == false || (strength > 100 || strength < -10));
            //if bool was false or int was outside of range then repeat the loop.
            System.out.println("STRENGTH ACCEPTED");
            //Create a boolean to check for valid object stats
            boolean invalidEntry = false;
            for (int j = 0; j < studentArray.length; j++){
                //loop through current student array
                if(studentArray[j] == null){ //if null value then continue
                    continue;
                } else if (intelligence == studentArray[j].intelligence && speed == studentArray[j].speed && strength == studentArray[j].strength){
                    invalidEntry = true; //if the stats of the new object are equal to all the stats of the obj at index i then it is invalid (true).
                } else if (name.equals(studentArray[j].name)){
                    //if name is found in the array then invalid is true.
                    invalidEntry = true;
                }
            }
            //if invalid gets set to true then inform the user and turn back the counter on the loop
            if (invalidEntry == true){
                System.out.println("Students can not have the exact same skill values and names cannot be used twice.");
                i--;
            } else {
                //else print out the stats entered and create a new object out of the provided information
                //assign the new object to the current student array index

                studentArray[i] = new PhysEd(name, intelligence, speed, strength);
                System.out.println("Name: " + studentArray[i].getName() + " Intelligence: " + studentArray[i].getIntelligence() + " Speed: " + studentArray[i].getSpeed() + " Strength: " + studentArray[i].getStrength());
            }


        }
        //set up a menu for the user to pick sports teams
        //bool to track valid choice
        boolean validChoice = false;
        //String to hold userChoice
        String userChoice;
        //int to hold userChoice after it is parsed
        int choiceNum = 0;
        do { //do while loop to print out a menu for the user
            System.out.println("\nPick sport to assemble team:");
            System.out.println("1) Programming.");
            System.out.println("2) Rope Climb.");
            System.out.println("3) Freeze Tag.");
            System.out.println("0) Quit.");
            try { //try to take in a choice and parse it to the int variable
                userChoice = input.next();
                choiceNum = Integer.parseInt(userChoice);
                validChoice = true; //set valid choice to true if int is entered
            } catch(NumberFormatException e){ //catch format errors and inform the user
                System.out.println("Invalid menu choice.");
                validChoice = false; //set bool to false
            }
            if (choiceNum < 0 || choiceNum > 3){
                //if choice is outside of range set bool to false
                validChoice = false;
            }
            //if/elseif statements to handle choices
            if (choiceNum == 1){
                PhysEd.pickTeamProgramming(studentArray, numOfStudents); //call the PhysEd class method to pick Programming team
            } else if (choiceNum == 2){
                PhysEd.pickTeamRope(studentArray, numOfStudents); //call  PhysEd class method to pick Rope Climb team
            } else if (choiceNum == 3){
                PhysEd.pickTeamTag(studentArray, numOfStudents); //call PhysEd class method to pick Freeze Tag team
            } else if (choiceNum == 0) {
                //if they enter 0 end the program
                System.out.println("Program ended");
                return;
            } else {
                //else invalid menu choice
                System.out.println("Invalid menu choice.");
            }

        }while (validChoice == false || choiceNum != 0); //repeat the menu until the user entered 0 or valid choice entered







    }
}
