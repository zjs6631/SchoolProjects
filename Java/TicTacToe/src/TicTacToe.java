import java.io.InputStream;
import java.util.Scanner;

public class TicTacToe {


    public enum cellValues{X, O, E}

    public static void main(String[] args) {

        A9Smith ttt = new A9Smith();
        Scanner s = new Scanner(System.in);
        int r=0, c=0;

        //Initialize game variables
        ttt.clearGameBoard();
        do
        {
            ttt.toString();
            System.out.println(ttt.player==cellValues.X?"Player X turn":"Player O turn");
            System.out.println("Enter Row,Col (1 - 3)");
            String line=s.nextLine();
            String data[]=line.split(",");
            r=Integer.parseInt(data[0].trim())-1;
            c=Integer.parseInt(data[1].trim())-1;

            ttt.setCellValue(r, c);
            System.out.println(ttt.toString());
            System.out.println("_____________________________");
            ttt.displayWinner();

        }while(ttt.isEmpty && (!ttt.isWin(cellValues.X) && !ttt.isWin(cellValues.O))); //added to make the loop stop if a win occurs

    }

}


