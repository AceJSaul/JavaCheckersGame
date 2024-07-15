package winCondition;

import entities.Board;

public class WinCondition {

    public static boolean isWin(char[][] board){
        int numberOfWhitePieces = 0;
        int numberOfBlackPieces = 0;

        for (int i=0; i<board.length; i++){
            for (int j=0; j<board[i].length; j++){
                if(board[i][j] == 'w' || board[i][j] == 'W'){
                    numberOfWhitePieces++;
                }
                else if(board[i][j] == 'b' || board[i][j] == 'B'){
                    numberOfBlackPieces++;
                }
            }
        }
        return numberOfWhitePieces == 0 || numberOfBlackPieces == 0;
    }

    public static char checkWinner(char[][] board) {
        if (isWin(board)) {
            int blackPieces = 0;
            int whitePieces = 0;

            for (char[] chars : board) {
                for (char aChar : chars) {
                    if (aChar == 'w' || aChar == 'W') {
                        whitePieces++;
                    } else if (aChar == 'b' || aChar == 'B') {
                        blackPieces++;
                    }
                }
            }

            if (blackPieces == 0) {
                return 'w';
            } else if(whitePieces ==0){
                return 'b';
            }
        }
        return 0;
    }
}
