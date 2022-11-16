//Zachary Smith
//Assignment 6 - Set Password

/* I initially did this project without seeing the file with the design tips and thought that the program was decent
, but I see where this approach would be more efficient. My program needed to loop through the string 4 times for each variable
instead of using counters for digit, upper, and lower. I used 4 methods in that approach, so it did give me a little more
practice with methods.
 */

import java.util.Scanner; //import scanner for input

public class A6Smith {
    public static void main(String[] args) {
        //set the passStrength which will control the loop
        String passStrength = "invalid";

        //start a while loop that will stop when a high security password is entered.
        while (passStrength != "high") {
            //prompt the user for input and assign that to the inStr variable
            System.out.println("Please enter a password: "); //ask for a password
            Scanner input = new Scanner(System.in);
            String inStr = input.nextLine();

            //set counter variables for upper, lower, and digit characters
            //get string length and create a boolean to check if password contains
            //a special character
            int upperCounter = 0;
            int lowerCounter = 0;
            int digitCounter = 0;
            int specialCounter = 0;
            int passLength = inStr.length();


            //use a for loop to loop through the password and count upper, lower, and digit characters
            for (int i = 0; i < passLength; i++) {
                char testChar = inStr.charAt(i);
                if (Character.isUpperCase(testChar)) {
                    upperCounter += 1;
                } else if (Character.isLowerCase(testChar)) {
                    lowerCounter += 1;
                } else if (Character.isDigit(testChar)) {
                    digitCounter += 1;
                } else if (SpecialCharacter(testChar)){
                    specialCounter += 1;
                }
            }

            //set the password strength based on values gathered above
            /*
            * High - contains at least 1 uppercase, lowercase, number, special character, and has a length of 8 or more
            * Medium - 1 uppercase, lowercase, number, and a length of 6 or more
            * Low - length of at least 5
            * Invalid - less than 5 length
            * */
            if (upperCounter >= 1 && lowerCounter >= 1 && digitCounter >=1 && specialCounter >=1 && passLength>=8){
                passStrength = "high";
            } else if (upperCounter >= 1 && lowerCounter >= 1 && digitCounter >= 1 && passLength >= 6){
                passStrength = "medium";
            } else if (passLength >= 5){
                passStrength = "low";
            } else {
                passStrength = "invalid";
            }

            //added this to adjust the output if password is invalid, so that is made more sense to read.
            if (passStrength == "high" || passStrength =="medium" || passStrength == "low"){
                System.out.println("You have entered a " + passStrength + " security password.");
            } else if (passStrength == "invalid"){
                System.out.println("You have entered an " + passStrength + " password.");
            }


        }
    }

    //define function to see if char is a special character
    public static boolean SpecialCharacter(char c) {
        boolean result = false; //initiate bool variable

        if (!Character.isLetterOrDigit(c)){
            result = true; //char is not a digit or letter return true
        }
        return result; //return result
    }
}
