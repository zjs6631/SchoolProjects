//Zachary Smith
//Assignment 3 - finding future dates

import java.util.InputMismatchException;
import java.util.Scanner; //import Scanner for input

public class FindingFutureDays {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); //create the scanner obj
        //get input to represent the current day
        int currDay = -1;

        //added 11/15/2022 to make sure valid input is given
        while(currDay < 0 || currDay > 6) {
            try {
                System.out.println("Please enter a number 0-6 (Sunday-Saturday) to represent" +
                        " the current day: ");
                currDay = input.nextInt();
                if(currDay < 0 || currDay > 6) {
                    System.out.println("You entered an invalid number.");
                    System.out.println("Please enter a number in the range of 0-6 (Sunday-Saturday)");
                    currDay = input.nextInt();
                }
            } catch (InputMismatchException e) { //catch input that does not match variable type
                System.out.println("You entered a non numerical value");
                currDay = 7;
                input.nextLine(); //advance the buffer to the next line
            }
        }


        //get input to represent the days elapsed days since currDay


        int elapsedDays = -1;
        while(elapsedDays < 0){
            try{
                System.out.println("Please enter the number of days that have" +
                        " elapsed since current day: ");
                elapsedDays = input.nextInt();
                if(elapsedDays < 0){
                    System.out.println("You entered a negative number.");
                }
            }catch(InputMismatchException e){
                System.out.println("You entered a non-numerical value.");
                elapsedDays = -1;
                input.nextLine();
            }
        }

        //Add the inputs together
        int sumOfDays = currDay + elapsedDays;

        //print the first part of the output based on currDay
        if (currDay == 0) {
            System.out.print("Today is Sunday and the future day is ");
        } else if (currDay == 1) {
            System.out.print("Today is Monday and the future day is ");
        } else if (currDay == 2) {
            System.out.print("Today is Tuesday and the future day is ");
        } else if (currDay == 3) {
            System.out.println("Today is Wednesday and the future day is ");
        } else if (currDay == 4) {
            System.out.print("Today is Thursday and the future day is ");
        } else if (currDay == 5) {
            System.out.print("Today is Friday and the future day is ");
        } else if (currDay == 6){
            System.out.print("Today is Saturday and the future day is ");
        } else {
            System.out.print("Invalid entry");
            System.exit(1);
        }

        //determine how to calculate futureDay
        int futureDay = 0;
        if (sumOfDays < 7){
            futureDay = sumOfDays; //sum of days will represent future day if it is 6 or less
        } else {
            futureDay = (sumOfDays % 7); //if sumOfDays is > 6 you can use modulus to find the future day
        }

        //used to determine the last word or future day for output.
        if (futureDay == 0){
            System.out.print("Sunday.");
        } else if (futureDay == 1) {
            System.out.print("Monday.");
        } else if (futureDay == 2){
            System.out.print("Tuesday.");
        } else if (futureDay == 3){
            System.out.print("Wednesday.");
        } else if (futureDay == 4){
            System.out.print("Thursday.");
        } else if (futureDay == 5){
            System.out.print("Friday.");
        } else if (futureDay == 6){
            System.out.print("Saturday.");
        } else {
            System.out.print("How'd you do that?");
            System.exit(1);
        }
    }
}
