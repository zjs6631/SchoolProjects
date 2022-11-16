import java.util.Scanner;

public class charTest {
    public static void main(String[] args) {
        String passStrength = "Invalid";
        Scanner input = new Scanner(System.in);
        String password = input.nextLine();
        int length = password.length();
        boolean isUpper = false;
        boolean isLower = false;
        boolean isSpecial = false;
        boolean isDigit = false;

        for (int i = 0; i < length; i++){
            char testedChar = password.charAt(i);
            if (Character.isUpperCase(testedChar)){
                isUpper = true;
            }
            else if(Character.isLowerCase(testedChar)){
                isLower = true;
            }
            else if(Character.isDigit(testedChar)){
                isDigit = true;
            }
            else if (!Character.isLetterOrDigit(testedChar)){
                isSpecial = true;
            }
        }
        System.out.println(isUpper);
        System.out.println(isLower);
        System.out.println(isDigit);
        System.out.println(isSpecial);


    }
}
