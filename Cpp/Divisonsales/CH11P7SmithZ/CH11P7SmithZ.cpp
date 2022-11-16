// CH11P7SmithZ.cpp : This file contains the solution to program 7 from chapter 11.
// [Zachary Smith]

#include <iostream>
#include <iomanip>
#include <locale>

using namespace std;
//create my divsales class
class DivSales {
//have a private static variable that all instances share
private:
    static double totalSales;

public:
    double quarters[4] = { 0, 0, 0, 0 }; //have an array for each quarter that holds their sales information
    void setQuarterInfo(double, double, double, double); //prototype for function to set quarter info
    void displayQuarterInfo(int); //prototype to display sales info for a quarter
    void displayTotal(); //prototype to display the totalSales variable

};

double DivSales::totalSales = 0; //initialize the totalSales to 0

void DivSales::setQuarterInfo(double first, double second, double third, double fourth) { //define function to set sales
    double total = first + second + third + fourth; //get the total for this division by adding the inputs 
    //set the value of each quarter using pointer to this objects array index
    (*this).quarters[0] = first;
    (*this).quarters[1] = second;
    (*this).quarters[2] = third;
    (*this).quarters[3] = fourth;
    totalSales += total; //add the total for this div to the final total
};

void DivSales::displayQuarterInfo(int num) {
    //function to display quarterinfo
    cout.imbue(std::locale("en_US.UTF-8"));
    cout << "Quarter " << num + 1  <<  fixed << setprecision(2) << ": $" << (*this).quarters[num] << " "; //use int to access index of array for this object
}

void DivSales::displayTotal() {
    cout << " \nsales total for all divisons: " << fixed << setprecision(2) << "$" << totalSales << ".\n"; //display the total that is shared by all instances of this object
    //could not figure out how to access without creating a class function to access it.
}
int main()
{
    //create 6 instances of the DivSales class
    DivSales div1,
        div2,
        div3,
        div4,
        div5,
        div6;
    //create an array that holds those objects
    DivSales divs[6] = { div1, div2, div3, div4, div5, div6 };
    //loop through the array and gather sales data
    for (int i = 1; i < 7; i++) {
        double first = -1, second = -1, third = -1, fourth = -1;
        //if we get any negatives we say there must be a mistake and try again
        while (first < 0 || second < 0 || third < 0 || fourth < 0) {
            cout << "Please enter the sales for the first quarter for division " << i << ": ";
            cin >> first;
            cout << "Please enter the sales for the second quarter for division " << i << ": ";
            cin >> second;
            cout << "Please enter the sales for the third quarter for division " << i << ": ";
            cin >> third;
            cout << "Please enter the sales for the fourth quarter for division " << i << ": ";
            cin >> fourth;
            if (first < 0 || second < 0 || third < 0 || fourth < 0) {
                cout << "\nA value was outside of the valid range. Try again.\n";
            }
        }
        //once we have valid input set the quarter info by provided the four inputs gathered
        divs[i-1].setQuarterInfo(first, second, third, fourth);   
    }
    cout << "\n\n\n---------------------------------------------------\n";
    //loop through array one more time and display each divisions sales info by calling class functions
    for (int j = 1; j < 7; j++) {
        cout << "The sales for division " << j << ": ";
        divs[j-1].displayQuarterInfo(0);
        divs[j-1].displayQuarterInfo(1);
        divs[j-1].displayQuarterInfo(2);
        divs[j-1].displayQuarterInfo(3);
        cout << "\n";
        if (j == 6) { //if we are at the last object then display the total by calling display total
            divs[j - 1].displayTotal();
        }
    }

    cout << "---------------------------------------------------";

    
    


}


