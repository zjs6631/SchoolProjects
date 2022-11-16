//Assignment 7 - A7Smith Class Grades
//[Zachary Smith]



public class A7Smith {
    public static void main(String[] args) {
        /*
        int argLength = args.length; //get the length of args

        if (argLength > 30){ //if the user enters more than 30 then return and inform the user
            System.out.println("This program can only hold 30 grades.");
            return;
        }
        */

        //create a new list of ints named gradeList
        int [] gradeList = new int[30];

        for(int i = 0; i < gradeList.length; i++){
            gradeList[i] = (int)(Math.random()*101);
        }

        java.util.Scanner input = new java.util.Scanner(System.in); //create scanner
        //use a for loop to parse integers from the arguments the user entered
        /*
        for (int i = 0; i < gradeList.length; i++){
            //I haven't used a try/catch yet, but have seen them regularly while studying
            try {
                if (Integer.parseInt(args[i]) < 0 || Integer.parseInt(args[i]) > 100){
                    System.out.println("Values must be between 0-100"); //if statement to make sure value is in range
                    return;
                }
                gradeList[i] = Integer.parseInt(args[i]);//try to parseInt
            } catch (NumberFormatException nfe) {
                //if it throws an error then inform the user and return
                System.out.println("You entered a list entry that is not an integer.");
                return;
            }
        }
    */
        System.out.println("Random grades:");
        for(int i = 0; i < gradeList.length; i++){
            System.out.print(gradeList[i] + ", ");
        }
        bubbleSwap(gradeList); //perform a bubbleSwap using bubbleSwap function

        System.out.println("Sorted grades: ");
        for(int i = 0; i < gradeList.length; i++){
            System.out.print(gradeList[i] + ", ");
        }
        //initialize the userGrade variable
        int userGrade = 0;
        //use a while loop to continue taking input from the user while they enter a value between 0-100
        while (userGrade >= 0 && userGrade <= 100){

            System.out.println("\nEnter the next grade (0-100) to find the rank of: ");
            userGrade = input.nextInt(); //take in user input

            if (userGrade < 0 || userGrade > 100){ //if the user input is outside the range inform the user
                System.out.println("You have entered a value out of range.");
                System.out.println("Thank you for using this program!");
                return; //and return
            }

            int rank = 1; //create a rank variable to keep track of class rank
            boolean tie = false; //create a boolean to test if there is a tie
            //use a for loop to test the input against the values in the list
            for (int i = 0; i < gradeList.length; i++){
                //if you are at the end of the list and the last value is > user input
                if (i == gradeList.length-1 && gradeList[29] > userGrade) {
                    rank+=1; //increase the rank by 1
                    break;
                } else if (i == gradeList.length-1 && gradeList[29] < userGrade){
                    //else if you are at the end of list and last value < user input...
                    //rank remains the same
                    break;
                }
                if ((i < gradeList.length-2) && (gradeList[i] == gradeList[i+1])){
                    //if you are not at end of list of args or total list (including 0's)
                    //or current gradelist[i] is the same as next gradelist[i+1]
                    //rank remains the same
                } else if (userGrade < gradeList[i]){
                    //if you are not at the end of the args entered and user input is < current gradeList[i]
                    //increase the rank
                    rank+=1;
                }

                if (userGrade == gradeList[i]){
                    //if userGrade is == to current gradeList[i]
                    //set tie to true
                    if(i < gradeList.length-1 && gradeList[i] == gradeList[i+1]) {
                        tie = true;
                    }
                }
            }
            //print out the results and add (tie) if tie is true
            System.out.print("Class rank = " + rank);
            if (tie) {
                System.out.println(" (tie)");
            }

        }

    }
    //create a bubble swap function that takes in an array
    public static void bubbleSwap(int[] array){
        //use a for loop to loop through list
        for (int i = 0; i < array.length-1; i++){
            //set swapped to false
            boolean swapped = false;
            //add a nested for loop
            for (int j = 0; j < array.length-1; j++){
                //if element is less than the next element
                if (array[j] < array[j+1]){
                    int temp = array[j+1]; //assign next element to temp variable
                    array[j+1] = array[j]; // assign next element variable to first element value
                    array[j] = temp; //set first element value to be the next element value held in temp variable
                    swapped = true; //set swapped to true
                }
            }
            //if swapped == false break
            if (!swapped){
                break;
            }
        }
    }
}