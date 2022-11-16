// CH12P4SmithZ.cpp : This file contains the solution to program 4 from chapter 12 'counting vowels and consonants'
// [Zachary Smith]
//

#include <iostream>
#include <string>

using namespace std;
const char VOWELS[5] = {'A', 'E', 'I', 'O', 'U'}; //array to holds vowels
const char CONS[21] = { 'B', 'C', 'D','F','G','H','J','K','L','M','N','P','Q','R','S','T','V','W','X','Y','Z' }; //array to hold consonants
int countVowels(char[], const char[], int); //prototype for vowel counter
int countCons(char[], const char[], int); //prototype for consonant counter

int main()
{
    const int LENGTH = 100; //set length of the userInput to 100
    char userInput[LENGTH]; //create a C-string variable for the userInput
    cout << "Please enter a string to test: ";
    cin.getline(userInput, LENGTH); //get the users input using getline 
    char userSelection = 'Z'; //variable for menu input
    do {
        //display menu
        cout << "\n---------------------------------------------";
        cout << "\n\nA) Count Vowels.";
        cout << "\nB) Count Consanants.";
        cout << "\nC) Count Vowels and Consonants.";
        cout << "\nD) Enter another string.";
        cout << "\nE) Exit Program.\n";
        cout << "----------------------------------------------\n";
        //get selection
        cin >> userSelection;
        //convert to uppercase
        userSelection = toupper(userSelection);
        //count vowels and cons using functions
        int numVowels = countVowels(userInput, VOWELS, LENGTH);
        int numCons = countCons(userInput, CONS, LENGTH);
        
        
        if (userSelection == 'A') {
            //display vowels
            cout << "\nThe number of vowels in your string is: " << numVowels;
        }
        else if (userSelection == 'B') {
            //display cons
            cout << "\nThe number of consonants in your string is: " << numCons;
        }
        else if (userSelection == 'C') {
            //display both
            cout << "\nThe number of vowels in your string is: " << numVowels;
            cout << "\nThe number of consonants in your string is: " << numCons;
        }
        else if (userSelection == 'D') {
            //take in a new string
            cout << "\nPlease enter a new string: ";
            cin.ignore();//since mixing the use of >> and getline() I had to use .ignore() to clear the input buffer
            cin.getline(userInput,LENGTH); //take the input for a new string
        }
        else if (userSelection == 'E') {
            //exit the program
            cout << "\nThank you for using this program!";
            return 0;
        }
        else {
            //display invalid if nput doesnt match a menu choice
            cout << "\ninvalid input.";
        }

        
    } while (userSelection != 'E');

    return 0;
    

}

int countVowels(char userinput[], const char VOWELS[], int LENGTH) { //define counting function
    int count = 0; //count is 0
    for (int i = 0; i < 5; i++) { //loop through vowel list
        char vowel = VOWELS[i]; //set the current vowel to a variable
        for (int j = 0; i < LENGTH; j++) { //loop through the C-string and compare to the currently observed vowel
            
            char currTarget = toupper(userinput[j]);
            if (vowel == currTarget) { //if a vowel is found increase the count
                count += 1;
            }
            else if (currTarget == '\0') { //if null terminator is found then break the loop to move to next vowel
                break;
            }
        }
    }
    return count; //return the count
}

int countCons(char userinput[], const char CONS[], int LENGTH) { //counting function for consonants
    int count = 0;
    for (int i = 0; i < 21; i++) { //loop through consonant array
        char consonant = CONS[i];
        for (int j = 0; i < LENGTH; j++) { //loop through the  string
            char currTarget = toupper(userinput[j]);
            if (consonant == currTarget) { //if match is found increase the count
                count += 1;
            }
            else if (userinput[j] == '\0') { //if null terminator if found break the loop to move to the next consonant 
                break;
            }
        }
    }
    return count;//return the count
}

