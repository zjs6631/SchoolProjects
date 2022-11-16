//A10Smith - CourseNew class - class roster manipulation
//[Zachary Smith - 4/22/22]


public class CourseNew {
    private String courseName; //initialize courseName variable
    private String[] students = new String[100]; //initialize array to hold 100 students
    private int numberOfStudents; //initialize int variable to count number of students;

    public CourseNew(String courseName) {
        this.courseName = courseName; //sets the course name
    }

    public String[] addStudent (String student) {
        String [] newArr = new String[numberOfStudents+1]; //create a new array fitted to be the size of current...
        //number of students + 1
        for (int i = 0; i < numberOfStudents; i++){ //loop through the array and copy the existing students to newArr
            newArr[i] = students[i];
        }
        newArr[numberOfStudents] = student; //make newArr[numberOfStudents] contain the new student
        students = newArr; //reassign students array to be a copy of the newly created newArr

        numberOfStudents++; //increase the numberOfStudents counter

        return students; //return the updated array
    }

    public String[] getStudents() {
        String[] newArr = new String[numberOfStudents]; //create a new array that can hold the current number of students
        for (int i = 0; i < numberOfStudents; i++){
            //loop through the array and copy the current student array index values to the newArr array
            newArr[i] = students[i];
        }
        students = newArr; //reassign the students array variable to contain a copy of the newArr
        return students; //return the updated students array
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public String getCourseName() {
        return courseName;
    }

    public String[] dropStudent (String student){
        int indexFound = 0; //create a variable to hold the index at which the student to remove is found
        for (int i = 0; i < numberOfStudents; i ++){
            //loop through and find the student to remove
            if (students[i].equals(student)){
                indexFound = i; //assign the index to the indexFound variable
            }
        }
        while (indexFound < numberOfStudents-1){
            //start at the index where the student was found and assign that index to the next students name
            students[indexFound] = students[indexFound+1];
            indexFound+=1; //increment indexFound
        }
        numberOfStudents-=1; //decrease the numberOfStudents

        return students; //return the updated array
    }

    public String[] clear (){
        //create a new empty array
        String[] clearedArr = new String[0];
        //reassign student variable to contain the new empty array
        students = clearedArr;
        //set number of students to 0
        numberOfStudents = 0;
        //return the empty array
        return students;
    }
}
