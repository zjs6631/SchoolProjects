// CH13P13SmithZ.cpp : This file contains the solution for program #13 in chapter 13
//[Zachary Smith]

#include <iostream>
#include <string>
#include <fstream> //fstream for working with files
#include <iomanip> //used to format

using namespace std;

//division class to create an obj for each division
class Division {
    //initialize the variables for each division (name and sales for each quarter 
    string divName= " ";
    double Q1sales = 0.0,
        Q2sales = 0.0,
        Q3sales = 0.0,
        Q4sales= 0.0;

public:
    //used to set the attributes for the object
    void setName(string name) { divName = name; }
    void setQ1sales(double sales) { Q1sales = sales; }
    void setQ2sales(double sales) { Q2sales = sales; }
    void setQ3sales(double sales) { Q3sales = sales; }
    void setQ4sales(double sales) { Q4sales = sales; }

    void store(fstream& outFile); //prototype for store function that will store all the objects information

    Division(string name = " ", double Q1s = 0.0, double Q2s = 0.0, double Q3s = 0.0, double Q4s = 0.0); //default constructor
};
//store function
void Division::store(fstream& outFile) {
    
    int divNameLength = divName.length(); //get length of name
    outFile << "\nDivision: ";
    outFile.write(divName.data(), divNameLength); //write name to file
    //write each sales figure into the file
    outFile << "\n$" << fixed << setprecision(2) << Q1sales << " \n"; 
    outFile << "$" << fixed << setprecision(2) << Q2sales << " \n";
    outFile << "$" << fixed << setprecision(2) << Q3sales << " \n";
    outFile << "$" << fixed << setprecision(2) << Q4sales << " \n";
}
//define the constructor
Division::Division(string name, double Q1s, double Q2s, double Q3s, double Q4s)
{
    //set each attribute based off the variables passed in
    this->divName = name;
    this->Q1sales = Q1s;
    this->Q2sales = Q2s;
    this->Q3sales = Q3s;
    this->Q4sales = Q4s;

}

int main()
{
    //open the file
    fstream file("sales.txt", ios::in | ios::out);
    //if it fails to open then tell the user and return 1
    if (file.fail()) {
        cout << "error when opening file.";
        return 1;
    }
    //loop to collect 4 divisions info
    for (int i = 0; i < 4; i++) {
        string divName = " ";
        double q1Sales = 0.0,
            q2Sales = 0.0,
            q3Sales = 0.0,
            q4Sales = 0.0;
        //take in all the information
        cout << "Please enter a Division name: ";
        cin >> divName;
        cout << "\nEnter a Q1 sales figure: ";
        cin >> q1Sales;
        cout << "\nEnter a Q2 sales figure: ";
        cin >> q2Sales;
        cout << "\nEnter a Q3 sales figure: ";
        cin >> q3Sales;
        cout << "\nEnter a Q4 sales figure: ";
        cin >> q4Sales;

        //create an obj based on the info
        Division newDiv = Division(divName, q1Sales, q2Sales, q3Sales, q4Sales);
        //store the info in the file using the store function
        newDiv.store(file);

    }

    return 0;
    
}


