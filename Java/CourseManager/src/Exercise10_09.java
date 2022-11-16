//Gerry Loccisano
//Course Class - Driver Program
//Exercise 10-09

public class Exercise10_09 {
  public static void main(String[] args) {

/** Load Courses and Students */ 
    CourseNew course1 = new CourseNew("Data Structures");
    CourseNew course2 = new CourseNew("Database Systems");

    System.out.println("  ... Adding Students to Courses");
    course1.addStudent("Peter Jones");
    course1.addStudent("Brian Smith");
    course1.addStudent("Anne Kennedy");
    course1.addStudent("Susan Kennedy");
    course1.addStudent("John Kennedy");
    course1.addStudent("Kim Johnson");
    course1.addStudent("Mary Brown");
    course1.addStudent("Ken Greene");
    course1.addStudent("Bennett Johnson");
    course1.addStudent("Mike Wagner");
    course1.addStudent("Cindy Shay");
    course1.addStudent("Juan Conde");
    course1.addStudent("Mark Mendon");
      
    course2.addStudent("Peter Jones");
    course2.addStudent("Steve Smith");

/** Print student list and # of students in the first course */ 
    System.out.println("Number of students in course1: "
      + course1.getNumberOfStudents());
    String[] students = course1.getStudents();

    for (int i = 0; i < students.length; i++)
      System.out.print(students[i] + ", ");

/** Print student list and # of students in the Second course */     
    System.out.println();
    System.out.print("Number of students in course2: "
      + course2.getNumberOfStudents() + "\n");
   students = course2.getStudents();
    for (int i = 0; i < students.length; i++)
      System.out.print(students[i] + ", ");
    System.out.println();
    

   System.out.println("\n" + "  ... Dropping Students from Course 1");
/** Drop a student named S1 from course 1*/ 
    course1.dropStudent("Cindy Shay");

/** Print # of students and Show new class list */ 
    System.out.println("Number of students in course1: "
      + course1.getNumberOfStudents());
    students = course1.getStudents();
    for (int i = 0; i < course1.getNumberOfStudents(); i++)
      System.out.print(students[i] + (i < course1.getNumberOfStudents() - 1 ? ", " : " "));
 
/** Clear all students from course #2 */ 
    System.out.println("\n\n" + "  ... Clearing All Students from Course 2");
    course2.clear();
    System.out.println("\nNumber of students in course 2: "
      + course2.getNumberOfStudents());
  }
}

