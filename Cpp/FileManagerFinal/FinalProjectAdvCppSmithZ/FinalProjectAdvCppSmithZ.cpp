// FinalProjectAdvCppSmithZ.cpp : This file contains the final project for advanced C++ 
//[Zachary Smith]

#include <iostream>
#include <iomanip>
#include <fstream>
#include <cstring>
#include <string>
#include <locale>

using namespace std;


const int NAME_SIZE = 50, ADDR_SIZE = 50, CITY_SIZE = 20, STATE_SIZE = 20, ZIP_SIZE = 10, TELEPHONE_SIZE = 20, PAYDATE_SIZE = 20; //sizes for structure variables 

//structure to hold customer information
struct Customer {
    char name[NAME_SIZE];
    char address[ADDR_SIZE];
    char city[CITY_SIZE];
    char state[STATE_SIZE];
    char zip[ZIP_SIZE];
    char telephone[TELEPHONE_SIZE];
    char dateOfPay[PAYDATE_SIZE];
    double balance;
};

//prototypes for functions that handle menu options
void addRecord();
void searchNdisplay();
void searchNdelete();
void searchNedit();
void displayAllRecords();



int main()
{
    cout.imbue(std::locale("en_US.UTF-8")); 
    int userInput = 7; //userinput variable
    do {
        //show menu
        cout << "\n1) Enter a new record.";
        cout << "\n2) Search for and display a record.";
        cout << "\n3) Search for and delete a record.";
        cout << "\n4) Search for and edit a record.";
        cout << "\n5) Display all records.";
        cout << "\n6) Exit Program.\n";
        cin >> userInput; //take in input

        //call appropriate function based on input
        if (userInput == 1) {
            addRecord();
        }
        else if (userInput == 2) {
            searchNdisplay();
        }
        else if (userInput == 3) {
            searchNdelete();
        }
        else if (userInput == 4) {
            searchNedit();
        }
        else if (userInput == 5) {
            displayAllRecords();
        }
        else if (userInput == 6) {

        }
        else {
            cout << "Invalid input.";
        }

    } while (userInput != 6); //until user enters 6
}
//add record function
void addRecord() {
    //open the folder
    fstream customersFile("customers.txt", ios::out | ios::in);
    if (!customersFile) {
        cout << "\nError when opening file.";
        return;
    }
    //create a customer structure
    Customer customer;
    string input; //used to hold user input
    cin.ignore(); //ignore initial input stored in the buffer
    do {
        //get name from user
        cout << "\nEnter customer name: ";
        getline(cin, input);
        if (input.empty()) { //if empty inform the user
            cout << "Please enter a value for name.\n";
        }
        strcpy_s(customer.name, input.c_str()); //take a copy of the input and apply it to the customer.name 
    } while (input.empty()); //repeat while the input is empty

    //REPEAT THIS PROCESS FOR THE OTHER C-STRING VARIABLES HELD IN THE STRUCTURE
    do {
        cout << "\nEnter customer address: ";
        getline(cin, input);
        if (input.empty()) {
            cout << "Please enter a value for address.\n";
        }
        strcpy_s(customer.address, input.c_str());
    } while (input.empty());
    do {
        cout << "\nEnter customer city: ";
        getline(cin, input);
        if (input.empty()) {
            cout << "Please enter a value for city.\n";
        }
        strcpy_s(customer.city, input.c_str());
    } while (input.empty());
    do {
        cout << "\nEnter customer state: ";
        getline(cin, input);
        if (input.empty()) {
            cout << "Please enter a value for state.\n";
        }
        strcpy_s(customer.state, input.c_str());
    } while (input.empty());
    do {
        cout << "\nEnter customer zipcode: ";
        getline(cin, input);
        if (input.empty()) {
            cout << "Please enter a value for zipcode.\n";
        }
        strcpy_s(customer.zip, input.c_str());
    } while (input.empty());
    do {
        cout << "\nEnter customer telephone number: ";
        getline(cin, input);
        if (input.empty()) {
            cout << "Please enter a value for telephone number.\n";
        }
        strcpy_s(customer.telephone, input.c_str());
    } while (input.empty());
    do {
        cout << "\nEnter customer date of last payment: ";
        getline(cin, input);
        if (input.empty()) {
            cout << "Please enter a value for date of last payment.\n";
        }
        strcpy_s(customer.dateOfPay, input.c_str());
    } while (input.empty());

    do {
        cout << "\nEnter customer balance: ";
        cin >> customer.balance;
        if (customer.balance < 0) {
            cout << "Please enter a value for balance greater than 0";
        }
        
    } while (customer.balance < 0);

    //once we collect all the data in the customer structure variable 
    customersFile.seekp(0L, ios::end); //move to the end of the file
    customersFile.write(reinterpret_cast<char*>(&customer), sizeof(customer)); //write the new data to the end of the file

    customersFile.close();//close the file! record added!
}

//function to display a record once the customer has been searched for
void searchNdisplay() {
    //open the file
    fstream customersFile("customers.txt", ios::out | ios::in);
    if (!customersFile) {
        cout << "\nError when opening file.";
        return;
    }
    //get a name to search for
    string searchName;
    cout << "\nPlease enter a name to search for: ";
    cin.ignore();
    getline(cin, searchName);
    //create a targCustomer structure to hold the data for the desired customer
    Customer targCustomer;
    customersFile.seekg(0L, ios::beg); //go to the beginning of file
    customersFile.read(reinterpret_cast<char*>(&targCustomer), sizeof(targCustomer)); //read the first line for length of targCustomer

    bool found = false; //bool to check if we find our customer
    while (!customersFile.eof()) {//while the end of file has not been reached
        
        if (targCustomer.name == searchName) { //if we find a match then set found to true and break the while loop
            found = true;
            break;
        }
        customersFile.read(reinterpret_cast<char*>(&targCustomer), sizeof(targCustomer)); //read the next line
    }
    //once out of the while loop if we found our target we read the information off
    if (found == true) {
        cout << "\nName: " << targCustomer.name;
        cout << "\nAddress: " << targCustomer.address << ". " << targCustomer.city << ", " << targCustomer.state << " " << targCustomer.zip;
        cout << "\nTelephone: " << targCustomer.telephone;
        cout << "\nDate of last payment: " << targCustomer.dateOfPay;
        cout << "\nCurrent balance: $" << fixed << setprecision(2) << targCustomer.balance;
    }
    else { //else say we didn't find the customer
        cout << "\nTarget not found.";
    }

    customersFile.close(); //close the file

}

//function to search for a customer and delete their information
void searchNdelete() {
    //open file
    fstream customersFile("customers.txt", ios::out | ios::in);
    if (!customersFile) {
        cout << "\nError when opening file.";
        return;
    }
    //get a name to search for
    string searchName;
    cout << "\nPlease enter a name to delete: ";
    cin.ignore();
    getline(cin, searchName);

    Customer targCustomer; //structure to hold data for desired customer
    Customer blank {"", "", "", "", "", "", "", 0}; //structure that holds blank values
    
    customersFile.seekg(0L, ios::beg); //go to beginning of file
    customersFile.read(reinterpret_cast<char*>(&targCustomer), sizeof(targCustomer)); //read a customer from the file

    bool found = false; //found bool
    while (!customersFile.eof()) { //while we havent reached the end of file.

        if (targCustomer.name == searchName) { //if a match is found the set to true and break the while loop
            found = true;
            break;
        }
        customersFile.read(reinterpret_cast<char*>(&targCustomer), sizeof(targCustomer)); //else read to next line
    }
    if (found) { //if we found our target
        customersFile.seekp((0-sizeof(targCustomer)), ios::cur); //rewind the file back by the length of one customer structure
        customersFile.write(reinterpret_cast<char*>(&blank), sizeof(blank)); //overwrite the data with the blank customer data (kind of a deletion)
    }
    else { //else inform the user we didn't find the target
        cout << "Customer not found";
    }

    customersFile.close(); //close the file
}

//function to search for and edit a users data
void searchNedit() {
    //open the file
    fstream customersFile("customers.txt", ios::out | ios::in);
    if (!customersFile) {
        cout << "\nError when opening file.";
        return;
    }
    //get a name to search for
    string searchName;
    cout << "\nPlease enter a name to edit: ";
    cin.ignore();
    getline(cin, searchName);

    Customer currCustomer;//structure to hold the target customers data

    customersFile.seekg(0L, ios::beg); //go to beginning of file
    customersFile.read(reinterpret_cast<char*>(&currCustomer), sizeof(currCustomer)); //read a customer from the file

    while (!customersFile.eof()) {//while we havent reached the end of the file
        if (currCustomer.name == searchName) {//if a match is found 
            customersFile.seekp((0 - sizeof(currCustomer)), ios::cur); //rewind the file the length of a customer structure
            Customer customer;//create a customer structure to hold data
            string input;//variable to take in user input
            //get input from user
            do {
                cout << "\nEnter customer name: ";
                
                getline(cin, input);
                if (input.empty()) { //if empty tell the user 
                    cout << "Please enter a value for name.\n";
                }
                strcpy_s(customer.name, input.c_str());
            } while (input.empty()); //repeat until user provides desired input
            //REPEAT FOR OTHER STRING VARIABLES
            do {
                cout << "\nEnter customer address: ";
                getline(cin, input);
                if (input.empty()) {
                    cout << "Please enter a value for address.\n";
                }
                strcpy_s(customer.address, input.c_str());
            } while (input.empty());
            do {
                cout << "\nEnter customer city: ";
                getline(cin, input);
                if (input.empty()) {
                    cout << "Please enter a value for city.\n";
                }
                strcpy_s(customer.city, input.c_str());
            } while (input.empty());
            do {
                cout << "\nEnter customer state: ";
                getline(cin, input);
                if (input.empty()) {
                    cout << "Please enter a value for state.\n";
                }
                strcpy_s(customer.state, input.c_str());
            } while (input.empty());
            do {
                cout << "\nEnter customer zipcode: ";
                getline(cin, input);
                if (input.empty()) {
                    cout << "Please enter a value for zipcode.\n";
                }
                strcpy_s(customer.zip, input.c_str());
            } while (input.empty());
            do {
                cout << "\nEnter customer telephone number: ";
                getline(cin, input);
                if (input.empty()) {
                    cout << "Please enter a value for telephone number.\n";
                }
                strcpy_s(customer.telephone, input.c_str());
            } while (input.empty());
            do {
                cout << "\nEnter customer date of last payment: ";
                getline(cin, input);
                if (input.empty()) {
                    cout << "Please enter a value for date of last payment.\n";
                }
                strcpy_s(customer.dateOfPay, input.c_str());
            } while (input.empty());
            do {
                //get the balance
                cout << "\nEnter customer balance: ";
                cin >> customer.balance;
                //if negative inform the user
                if (customer.balance < 0) {
                    cout << "Please enter a balance greater than 0.";
                }

            } while (customer.balance < 0);//repeat while negative

            customersFile.write(reinterpret_cast<char*>(&customer), sizeof(customer));//write the new customer data into the file
            cout << "\nFile has been edited.";//inform the user of the edit
            break; //break the loop
        }
        customersFile.read(reinterpret_cast<char*>(&currCustomer), sizeof(currCustomer));//if the user wasnt found on the last read then read the next line
        
        
    }
    customersFile.close(); //close the file
}

//function to display all of the user records 
void displayAllRecords() {
    //open the file
    fstream customersFile("customers.txt", ios::out | ios::in);
    if (!customersFile) {
        cout << "\nError when opening file.";
        return;
    }
    //structure to hold customer data
    Customer currCustomer;
    //start a beginning of file and read a customer from the file
    customersFile.seekg(0L, ios::beg);
    customersFile.read(reinterpret_cast<char*>(&currCustomer), sizeof(currCustomer));
    
    //if we're not at the end of the file
    while (!customersFile.eof()) {
        //skip over empty records(where data was deleted)
        if (strcmp(currCustomer.name, "")) {//if the customer name is not empty then display the data
            cout << "\nName: " << currCustomer.name;
            cout << "\nAddress: " << currCustomer.address << ", " << currCustomer.city << ", " << currCustomer.state << " " << currCustomer.zip;
            cout << "\nTelephone: " << currCustomer.telephone;
            cout << "\nDate of last payment: " << currCustomer.dateOfPay;
            cout << "\nBalance: $" << fixed << setprecision(2) << currCustomer.balance << "\n\n";
        }
        customersFile.read(reinterpret_cast<char*>(&currCustomer), sizeof(currCustomer));//read the next customer from the file
    }

    customersFile.close();//close the file
}

