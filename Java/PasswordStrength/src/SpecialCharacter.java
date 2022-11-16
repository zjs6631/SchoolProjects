//Zachary Smith
//A6 - Assignment 6 - Method for seeing if a string include a special character


public class SpecialCharacter {

    //define function to see if string contains special character
    public static boolean SpecialCharacter(char c) {
        boolean result = false; //initiate bool variable

        if (!Character.isLetterOrDigit(c)) {
            result = true; //char is not a digit or letter return true
        }
        return result; //return result
    }
}


