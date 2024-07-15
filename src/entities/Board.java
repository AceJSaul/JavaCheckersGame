package entities;

public class Board {

    public static char[][] getBoard(){
        return new char[][]{
                {' ', 'b', ' ', 'b', ' ', 'b', ' ', 'b'},
                {'b', ' ', 'b', ' ', 'b', ' ', 'b', ' '},
                {' ', 'b', ' ', 'b', ' ', 'b', ' ', 'b'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'w', ' ', 'w', ' ', 'w', ' ', 'w', ' '},
                {' ', 'w', ' ', 'w', ' ', 'w', ' ', 'w'},
                {'w', ' ', 'w', ' ', 'w', ' ', 'w', ' '}
        };
    }

    public static char[][] testBoard(){
        return new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'b', ' ', 'W', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'B', ' ', ' ', ' '}
        };
    }

    public static void showBoard(char[][] board){
        System.out.println("  1 2 3 4 5 6 7 8");
        for(int rowI=0; rowI< board.length; rowI++){
            System.out.print((rowI+1) + " ");
            for(int columnI=0; columnI<board[rowI].length; columnI++){
                System.out.print((board[rowI][columnI] + " "));
            }
            System.out.println();
        }
    }

}
