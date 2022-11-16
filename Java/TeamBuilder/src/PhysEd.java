
//PhysEd Class - A11Smith - 4/29/2022 - Sport team picker class
//[Zachary Smith]
public class PhysEd{
    //initialize variables
    public String name;
      public int intelligence,
      speed,
      strength;

    //create constructor method that uses the users input to create new obj
    public PhysEd(String n, int i, int spd, int str){
        name = n;
        intelligence = i;
        speed = spd;
        strength = str;
    }

    //getter methods that can return an objects stats
    public String getName(){
        return name;
    }
    public int getIntelligence(){
        return intelligence;
    }
    public int getSpeed(){
        return speed;
    }
    public int getStrength(){
        return strength;
    }

    //https://www.geeksforgeeks.org/bubble-sort/ was followed to make sure I did bubble sort correctly
    //logic involving equal value skills was done on my own.
    public static void pickTeamProgramming(PhysEd[] studentArray, int num) { //void function returns no value but takes in an array and number of objects



        for (int i = 0; i < studentArray.length - 1; i++) { //for loop to loop through each item
            for (int j = 0; j < studentArray.length - 1; j++) { //nested for loop to loop through the list for each item
                if (studentArray[j].intelligence < studentArray[j + 1].intelligence) { // if current index less than next index
                    PhysEd temp = studentArray[j]; //assign current to temp
                    studentArray[j] = studentArray[j + 1]; //assign next to current
                    studentArray[j + 1] = temp; //assign temp to next
                    //swapping them
                } else if (studentArray[j].intelligence == studentArray[j + 1].intelligence) { //if equal intelligence
                    if (studentArray[j].speed < studentArray[j + 1].speed) { //check speed
                        PhysEd temp = studentArray[j]; //swap if difference in speed is found
                        studentArray[j] = studentArray[j + 1];
                        studentArray[j + 1] = temp;
                    } else if (studentArray[j].speed == studentArray[j + 1].speed) { //if speed is equal
                        if (studentArray[j].strength < studentArray[j + 1].strength) { //find difference in strength
                            //swapped based on difference in strength
                            PhysEd temp = studentArray[j];
                            studentArray[j] = studentArray[j + 1];
                            studentArray[j + 1] = temp;
                        }
                    }
                }
            }
        }


        //print out the programming team by name
        System.out.println("-------------------------");
        System.out.print("Programming Team: ");

        for(int i = 0; i < studentArray.length; i++){
            //if else statement to control formatting based on if first index or last
            if (i == 0) {
                System.out.print(" " + studentArray[i].name + ", ");
            } else if (i == studentArray.length-1){
                System.out.print(" " + studentArray[i].name + ".");
            } else {
                System.out.print(studentArray[i].name + ", ");
            }
        }
        System.out.println("\n--------------------------");

    }

    public static void pickTeamTag(PhysEd[] studentArray, int num) { //function to pick Freeze Tag team


        //nested for loop to bubble sort each item in the array
        for (int i = 0; i < studentArray.length - 1; i++) {
            for (int j = 0; j < studentArray.length - 1; j++) {
                //compare speeds and swap to get the greater speeds at a lower index
                if (studentArray[j].speed < studentArray[j + 1].speed) {
                    PhysEd temp = studentArray[j];
                    studentArray[j] = studentArray[j + 1];
                    studentArray[j + 1] = temp;
                } else if (studentArray[j].speed == studentArray[j + 1].speed) { //if speed is equal
                    if (studentArray[j].intelligence < studentArray[j + 1].intelligence) { //compare intelligence
                        //swap based on intelligence
                        PhysEd temp = studentArray[j];
                        studentArray[j] = studentArray[j + 1];
                        studentArray[j + 1] = temp;
                    } else if (studentArray[j].intelligence == studentArray[j + 1].intelligence) { //if intelligence equal
                        if (studentArray[j].strength < studentArray[j + 1].strength) { //compare strength
                            //swap based on strength
                            PhysEd temp = studentArray[j];
                            studentArray[j] = studentArray[j + 1];
                            studentArray[j + 1] = temp;
                        }
                    }
                }
            }
        }

        //loop through and print out tag team
        System.out.println("-------------------------------");
        System.out.print("Freeze Tag Team: ");

        for(int i = 0; i < studentArray.length; i++){
            //if else to help in formatting based on first/last/ or middle item
            if (i == 0) {
                System.out.print(" " + studentArray[i].name + ", ");
            } else if (i == studentArray.length-1){
                System.out.print(" " + studentArray[i].name + ".");
            } else {
                System.out.print(studentArray[i].name + ", ");
            }
        }
        System.out.println("\n-------------------------------");
    }

    public static void pickTeamRope(PhysEd[] studentArray, int num) { //function to pick rope climbing team


        //nested for loop to bubble sort the objects
        for (int i = 0; i < studentArray.length - 1; i++) {
            for (int j = 0; j < studentArray.length - 1; j++) {
                if (studentArray[j].strength < studentArray[j + 1].strength) { //compare strength
                    //swap based on strength values
                    PhysEd temp = studentArray[j];
                    studentArray[j] = studentArray[j + 1];
                    studentArray[j + 1] = temp;
                } else if (studentArray[j].strength == studentArray[j + 1].strength) { //if strength is equal
                    if (studentArray[j].speed < studentArray[j + 1].speed) { //compare speed
                        //swap based on speed
                        PhysEd temp = studentArray[j];
                        studentArray[j] = studentArray[j + 1];
                        studentArray[j + 1] = temp;
                    } else if (studentArray[j].speed == studentArray[j + 1].speed) { //if speed is equal
                        if (studentArray[j].intelligence < studentArray[j + 1].intelligence) { //compare intelligence
                            //swap based on intelligence
                            PhysEd temp = studentArray[j];
                            studentArray[j] = studentArray[j + 1];
                            studentArray[j + 1] = temp;
                        }
                    }
                }
            }
        }


        //print out the rope climbing team
        System.out.println("---------------------------");
        System.out.print("Rope Climb: ");

       for(int i = 0; i < studentArray.length; i++){
           //if else for formatting
           if (i == 0) {
               System.out.print(" " + studentArray[i].name + ", ");
           } else if (i == studentArray.length-1){
               System.out.print(" " + studentArray[i].name + ".");
           } else {
               System.out.print(studentArray[i].name + ", ");
           }
       }
        System.out.println("\n-----------------------------");
    }




}
