//[Assignment 9 - A9Smith - TicTacToe - Contains methods required to make a functional game of TicTacToe]
//[Zachary Smith]

public class A9Smith {

    // define matrix (gameBoard)
    TicTacToe.cellValues gameBoard[][] = new TicTacToe.cellValues[3][3];
    public TicTacToe.cellValues player = TicTacToe.cellValues.X;
    public boolean isEmpty = false;

    public void clearGameBoard() {
        //initialize matrix to empty
        //[your code here]
        //use a for loop with a nested for loop to visit each cell in the 2D array
        for (int row = 0; row < gameBoard.length; row++) {
            for (int column = 0; column < gameBoard[row].length; column++) {
                gameBoard[row][column] = TicTacToe.cellValues.E; //set each cell to the enum value empty
            }
        }
        return; //return

    }

    public void setCellValue(int row, int col) {
        //Check for valid (row, column) input
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            System.out.println("Invalid board position");
            return;
        }

        //Check for Unused space
        if (gameBoard[row][col] != TicTacToe.cellValues.E) {
            System.out.println("Board position occupied");
            return;
        }
        //Set X or O in the cell
        gameBoard[row][col] = player;

        // set player value to next player (alternate x and o)
        if (player == TicTacToe.cellValues.X) {
            player = TicTacToe.cellValues.O;
        } else {
            player = TicTacToe.cellValues.X;
        }
        return;
    }

    public boolean isWin(TicTacToe.cellValues XOValue) {
        //[your code here]
        boolean isWin = false; //create a bool value to use while checking for possible wins
        for (int row = 0; row < gameBoard.length; row++){ //check each row for a horizontal win
            if ((gameBoard[row][0].equals(gameBoard[row][1]) && gameBoard[row][1].equals(gameBoard[row][2])) && (gameBoard[row][0].equals(XOValue))){
                //if the first cell matches the second cell and the second cell matches the third cell then that counts as a win!
                isWin = true;
            }
        }
        for (int col = 0; col < gameBoard.length; col++){
            //use a for loop to visit each column in the 2D array to check for vertical wins
            if ((gameBoard[0][col].equals(gameBoard[1][col]) && gameBoard[1][col].equals(gameBoard[2][col])) && (gameBoard[0][col].equals(XOValue))){
                //if the first cell equals the seconds cell and the second equals the third then it's a win!
                isWin = true;
            }
        }

        //added if/elseif statement to check for diagonal wins
        if ((gameBoard[0][0].equals(gameBoard[1][1]) && gameBoard[1][1].equals(gameBoard[2][2])) && gameBoard[0][0].equals(XOValue)){
            isWin = true;
        } else if ((gameBoard[0][2].equals(gameBoard[1][1]) && gameBoard[1][1].equals(gameBoard[2][0])) && gameBoard[0][2].equals(XOValue)){
            isWin = true;
        }



        return isWin; //returns true if any wins were detected. else it returns the false value defined at the top
    }

    public void displayWinner() {
        //[your code here]
        //check for a win with value X calling the isWin method
        if (isWin(TicTacToe.cellValues.X)){
            System.out.println("X wins!!");
            return;
        }
        //check for a win with the value O calling the isWin method
        if(isWin(TicTacToe.cellValues.O)){
            System.out.println("O Wins!!");
            return;
        }

        //check if is empty is false last.. to make sure the final move didn't result in a win
        if (isEmpty == false){
            System.out.println("It's a tie! No more moves available.");
            return;
        }

    }
    public String toString() {
        //[your code here]
        //create a variable to represent the current board
        String currentBoard = gameBoard[0][0] + " | " + gameBoard[0][1] + " | " + gameBoard[0][2] + "\n" +
                "-------------\n" + gameBoard[1][0] + " | " + gameBoard[1][1] + " | " + gameBoard[1][2] + "\n" +
                "-------------\n" + gameBoard[2][0] + " | " + gameBoard[2][1] +  " | " + gameBoard[2][2];
        //used a counter to check if there are any empty cells left on the board
        int emptyCounter = 0;
        for (int row = 0; row < gameBoard.length; row++){ //for loop with another nested for loop to visit each cell
            for (int col = 0; col < gameBoard[row].length; col++){
                if (gameBoard[row][col].equals(TicTacToe.cellValues.E)){ //if the cell holds the enum value empty
                    emptyCounter += 1; //add one to the counter
                }
            }
        }
        //if the counter = 0 then set isEmpty to false (no more space)
        if (emptyCounter == 0){
            isEmpty = false;
        } else {
            //else set isEmpty to true
            isEmpty = true;
        }
        //return to variable that represents the board
        return currentBoard;

    }
}
