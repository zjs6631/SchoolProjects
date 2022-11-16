//Zachary Smith
//A5- Assignment 5 - Loan Amortization

import java.util.Scanner; //import scanner for inputs

public class A5Smith {
    public static void main(String[] args) {
        //initialize variables for input
        double loanAmt;
        double annualInterestRate = 0;
        int years = 0;

        Scanner input = new Scanner(System.in);

        /* had a hard time figuring out the data validation part. Tried similar methods I've used in Python, but using
        the .hasNext() methods took a second to get the hang of.
         */

        //use do/while loop for data validation
        do {
            System.out.println("Enter the initial loan amount: "); //ask for users initial input
            while (!input.hasNextDouble()){ //if the value entered is not a double
                System.out.println("Please enter a valid value: "); //ask for valid input
                input.next(); //take in the next value
            }
            loanAmt = input.nextDouble(); //when the value is a double it will be assigned to loanAmt
        } while (loanAmt < 0); //also make sure the value is not negative or 0

        //use a do/while loop for data validation
        do{
            System.out.println("Enter the interest rate as a percent: "); //ask for initial input
            while (!input.hasNextDouble()){ //if the input is not a double
                System.out.println("Please enter a valid value for a percent: "); //ask again
                input.next(); //take in next input from user
            }
            annualInterestRate = input.nextDouble(); //when the input is a double it will be assigned to annualInterestRate
        } while (annualInterestRate <= 0); //Make sure value is greater than or equal to 0 (could have 0 interest)

        //use a do/while loop to validate years entered
        do {
            System.out.println("Enter the number of years you will pay on the loan: "); //ask for initial input
            while (!input.hasNextInt()) { //if it is not an integer
                System.out.println("Please enter a valid value for number of years: "); //ask again
                input.next(); //take in next value
            }
            years = input.nextInt(); //when it is an int assign it to variable years
        } while (years < 0); //make sure years is greater than 0

        /*calculate monthly interest, monthly payment, total payment (book said monthly payment formula
        comes from an earlier example in the book */
        double monthlyInterestRate = annualInterestRate / 100 / 12;
        double monthlyPayment = loanAmt * monthlyInterestRate / (1 - 1 / Math.pow(1 + monthlyInterestRate, years * 12));
        double totalPayment = monthlyPayment * 12 * years;

        //print out monthly payment and total payment and headers for the final results
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("Monthly payment: %,6.2f\n", monthlyPayment);
        System.out.printf("Total payment: %,8.2f \n\n", totalPayment);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Payment#" + "\t\t" + "Interest" + "\t\t" + "Principle" + "\t\t" + "Balance");

        //use a for loop to display each monthly payment and the variables that relate to it
        //also practiced utilizing formatters to make the output line up appropriately
        System.out.println("-------------------------------------------------------------------------");
        for (int i = 1; i <= (years * 12); i++) {
            double interestAmt = monthlyInterestRate * loanAmt;
            double principle = monthlyPayment - interestAmt;
            loanAmt = loanAmt - principle;
            System.out.printf("%,8d" + "\t\t" + "%,8.2f" + "\t\t" + "%,9.2f" + "\t\t" + "%,8.2f" + " \n", i, interestAmt, principle, loanAmt);
            }
        System.out.println("-------------------------------------------------------------------------");

    }
}

