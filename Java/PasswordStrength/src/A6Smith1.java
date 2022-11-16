//Zachary Smith
//Assignment 6 - Set Password

import java.util.Scanner; //import scanner for input

public class A6Smith1 {
    public static void main(String[] args) {
        String passStrength = "invalid"; //initiate passStrength variable

        //set a while loop that will repeat until strength is high
        while (passStrength != "High"){

            System.out.print("Please enter a password: "); //ask for password

            Scanner input = new Scanner(System.in); //create scanner obj
            String inStr = input.nextLine(); //take in password from user

            //Initiate all the boolean values used to determine strength/ and length
            boolean hasUpper = hasUpper(inStr);
            boolean hasLower = hasLower(inStr);
            boolean hasDigit = hasDigit(inStr);
            boolean hasSpecialCharacter = SpecialCharacter(inStr);
            int strLength = inStr.length();

            //set if/elseif statements to determine strength based on values return by functions above
            if (hasUpper && hasLower && hasDigit && hasSpecialCharacter && strLength >= 8){
                passStrength = "High";
            } else if (hasUpper && hasLower && hasDigit && strLength >= 6){
                passStrength = "Medium";
            } else if (strLength >= 5){
                passStrength = "Low";
            } else {
                passStrength = "Invalid";
            }

            //added this to make output for invalid password read better
            if (passStrength == "High" || passStrength == "Medium" || passStrength == "Low"){
                System.out.println("You have entered a " + passStrength + " security level password.");
            } else {
                System.out.println("You have entered an invalid password.");
            }
        }

    }

    //define function to see if input string has an uppercase letter
    public static boolean hasUpper(String str){
        boolean result = false;
        int strLength = str.length();
        for (int i=0; i < strLength; i++){ //for the length of the string...
            char testedChar = str.charAt(i); //assign the character at index i to a variable
            if (Character.isUpperCase(testedChar)){ //test the variable to see if uppercase
                result = true; //set to true if it is uppercase
            }
        }
        return result; //return that boolean value
    }

    //define function to find if string contains a lowercase character
    public static boolean hasLower(String str) {
        boolean result = false; //initiate bool variable
        int strLength = str.length();
        for (int i = 0; i < strLength; i++) { //for the length of the variable
            char testedChar = str.charAt(i); //test the character at index i
            if (Character.isLowerCase(testedChar)) { //if lowercase
                result = true; //set bool to true
            }
        }
        return result; //return boolean value
    }

    //define function for seeing if string contains a digit
    public static boolean hasDigit(String str) {
        boolean result = false; //initiate bool variable
        int strLength = str.length();
        for (int i = 0; i < strLength; i++) { //for length of the string
            char testedChar = str.charAt(i); //test character at index i
            if (Character.isDigit(testedChar)) { //if it is a digit
                result = true; //set boolean value to true
            }
        }
        return result; //return bool value
    }

    //define function to see if string contains special character
    public static boolean SpecialCharacter(String str) {
        boolean result = false; //initiate bool variable
        int strLength = str.length();
        for (int i = 0; i < strLength; i++) { //for length of variable
            char testedChar = str.charAt(i); // test character at index i
            if (!Character.isLetterOrDigit(testedChar)) { //if it is not a digit or letter
                result = true; //set bool to true (must be a special character)
            }
        }
        return result; //return result
    }
}
