package application;

import entities.*;
import winCondition.WinCondition;

import java.util.Scanner;

public class MainGame {

    public static void main(String[] args){

        char[][] gameBoard = Board.getBoard();
        //char[][] testBoard = Board.testBoard();

        Scanner sc = new Scanner(System.in);

        boolean playing = true;
        boolean player1Turn = true;
        while (playing){
            // Showing gameBoard
            Board.showBoard(gameBoard);

            // Getting userInput
            if(player1Turn){
                System.out.print("White movement (row, column, direction[L/R], upOrDown[U/D](If not king piece, write 'x')): ");
            } else{
                System.out.print("Black movement (row, column, direction[L/R], upOrDown[U/D](If not king piece, write 'x')): ");
            }

            int pieceRowPosition = sc.nextInt() - 1; // -1 bcz there's a difference between the index counter and board counter
            int pieceColumnPosition = sc.nextInt() - 1;
            char direction = sc.next().charAt(0);
            char piece = gameBoard[pieceRowPosition][pieceColumnPosition];
            char upOrDown = sc.next().charAt(0);

            if (player1Turn){
                while(piece == 'b' || piece == 'B' || piece == ' '){
                    System.out.println("You control the white pieces, try again");
                    pieceRowPosition = sc.nextInt() -1;
                    pieceColumnPosition = sc.nextInt() -1;
                    piece = gameBoard[pieceRowPosition][pieceColumnPosition];
                }
            }
            else{
                while(piece == 'w' || piece == 'W' || piece == ' '){
                    System.out.println("You control the black pieces, try again");
                    pieceRowPosition = sc.nextInt() -1;
                    pieceColumnPosition = sc.nextInt() -1;
                    piece = gameBoard[pieceRowPosition][pieceColumnPosition];
                }
            }

            if (piece == 'W' || piece == 'B'){
                Piece.pieceMovementOnBoard(gameBoard, pieceRowPosition, pieceColumnPosition, piece, direction, upOrDown);
            }
            else{
                Piece.pieceMovementOnBoard(gameBoard, pieceRowPosition, pieceColumnPosition, piece, direction);
            }

            // Testing winning condition
            if(WinCondition.isWin(gameBoard)){
                playing = false;

                Board.showBoard(gameBoard);
                char winner = WinCondition.checkWinner(gameBoard);
                if (winner == 'w'){
                    System.out.println("White won!");
                }
                else if (winner == 'b'){
                    System.out.println("Black won!");
                }
            };


            player1Turn = !player1Turn;
        }
    }
}
