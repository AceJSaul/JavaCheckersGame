package entities;

import java.util.Scanner;

public class Piece {
    private static final Scanner sc = new Scanner(System.in);
    // char piece stands for 'w' or 'b'
    public static void pieceMovementOnBoard(char[][] board, int pieceRowPosition, int pieceColumnPosition, char piece, char desiredDirection){
        if(piece == 'w'){
            moveWhitePieces(board, pieceRowPosition, pieceColumnPosition,desiredDirection);
            whitePiecePromotionToKing(board);
        }
        else if(piece == 'b'){
            moveBlackPieces(board, pieceRowPosition, pieceColumnPosition, desiredDirection);
            blackPiecePromotionToKing(board);
        }
    }

    public static void pieceMovementOnBoard(char[][] board, int pieceRowPosition, int pieceColumnPosition, char piece, char desiredDirection, char downOrUp){
        if(piece == 'W'){
            moveWhiteKingPiece(board, pieceRowPosition, pieceColumnPosition, desiredDirection, downOrUp);
        }

        else if(piece == 'B'){
            moveBlackKingPiece(board, pieceRowPosition, pieceColumnPosition, desiredDirection, downOrUp);
        }
    }
    // desiredDirection == ('L')Left or ('R')Right
    // ('U')Up or ('D')Down

    // Checking if there are any White Kings promoted
    public static void whitePiecePromotionToKing(char [][] board){
        for(int j=0; j<board[0].length; j++){
            if (board[0][j] == 'w'){
                board[0][j] = 'W';
            }
        }
    }

    public static void blackPiecePromotionToKing(char[][] board){
        for (int i=0; i<board[7].length; i++){
            if (board[7][i] == 'b'){
                board[7][i] = 'B';
            }
        }
    }

    // Moving checkers pieces
    public static void moveWhitePieces(char[][] board, int pieceRowPosition, int pieceColumnPosition, char desiredDirection){
        boolean isLegalMove = checkIfWhiteMovementAvailable(board, pieceRowPosition, pieceColumnPosition, desiredDirection);

        while (!isLegalMove){
            System.out.println("Insira um movimento válido");
            System.out.print("White movement (row, column, direction[L/R]): ");
            pieceRowPosition = sc.nextInt() - 1;
            pieceColumnPosition = sc.nextInt() - 1;
            desiredDirection = sc.next().charAt(0);

            isLegalMove = checkIfWhiteMovementAvailable(board, pieceRowPosition, pieceColumnPosition, desiredDirection);
        }

        board[pieceRowPosition][pieceColumnPosition] = ' ';
        if (desiredDirection == 'L'&& board[pieceRowPosition-1][pieceColumnPosition-1] == ' '){
            board[pieceRowPosition-1][pieceColumnPosition-1] = 'w';
        }
        else if(desiredDirection == 'R' && board[pieceRowPosition-1][pieceColumnPosition+1] == ' '){
            board[pieceRowPosition-1][pieceColumnPosition+1] = 'w';
        }
        else{
            whiteCaptures(board, pieceRowPosition, pieceColumnPosition, desiredDirection);
        }
    }

    public static void moveBlackPieces(char[][] board, int pieceRowPosition, int pieceColumnPosition, char desiredDirection){
        boolean isLegalMove = checkIfBlackMovementAvailable(board, pieceRowPosition, pieceColumnPosition, desiredDirection);

        while(!isLegalMove){
            System.out.println("Insira um movimento válido");
            System.out.print("Black movement (row, column, direction[L/R]): ");
            pieceRowPosition = sc.nextInt() -1;
            pieceColumnPosition = sc.nextInt() -1;
            desiredDirection = sc.next().charAt(0);

            isLegalMove = checkIfBlackMovementAvailable(board, pieceRowPosition, pieceColumnPosition, desiredDirection);
        }

        board[pieceRowPosition][pieceColumnPosition] = ' ';
        if (desiredDirection == 'L' && board[pieceRowPosition+1][pieceColumnPosition-1] == ' '){
            board[pieceRowPosition+1][pieceColumnPosition-1] = 'b';
        }
        else if(desiredDirection == 'R' && board[pieceRowPosition+1][pieceColumnPosition+1] == ' '){
            board[pieceRowPosition+1][pieceColumnPosition+1] = 'b';
        }
        else{
            blackCaptures(board, pieceRowPosition, pieceColumnPosition, desiredDirection);
        }

    }

    // Moving Kings pieces
    public static void moveWhiteKingPiece(char[][] board, int pieceRowPosition, int pieceColumnPosition, char desiredDirection, char downOrUpMovement){
        boolean isLegalMove = checkIfWhiteKingMovementAvailable(board, pieceRowPosition, pieceColumnPosition, desiredDirection, downOrUpMovement);

        while (!isLegalMove){
            System.out.println("Insira um movimento válido");
            System.out.print("White movement (row, column, direction[L/R], downOrUp[U/D]): ");
            pieceRowPosition = sc.nextInt() - 1;
            pieceColumnPosition = sc.nextInt() - 1;
            desiredDirection = sc.next().charAt(0);
            downOrUpMovement = sc.next().charAt(0);

            isLegalMove = checkIfWhiteKingMovementAvailable(board, pieceRowPosition, pieceColumnPosition, desiredDirection, downOrUpMovement);
        }

        board[pieceRowPosition][pieceColumnPosition] = ' ';
        // Upside movement
        if(downOrUpMovement == 'U'){
            if (desiredDirection == 'L'&& board[pieceRowPosition-1][pieceColumnPosition-1] == ' '){
                board[pieceRowPosition-1][pieceColumnPosition-1] = 'W';
            }
            else if(desiredDirection == 'R' && board[pieceRowPosition-1][pieceColumnPosition+1] == ' '){
                board[pieceRowPosition-1][pieceColumnPosition+1] = 'W';
            }
            else{
                whiteKingCaptures(board, pieceRowPosition, pieceColumnPosition, desiredDirection, 'U');
            }
        }
        // Downside movement
        if(downOrUpMovement == 'D'){
            if (desiredDirection == 'L'&& board[pieceRowPosition+1][pieceColumnPosition-1] == ' '){
                board[pieceRowPosition+1][pieceColumnPosition-1] = 'W';
            }
            else if(desiredDirection == 'R' && board[pieceRowPosition+1][pieceColumnPosition+1] == ' '){
                board[pieceRowPosition+1][pieceColumnPosition+1] = 'W';
            }
            else{
                whiteKingCaptures(board, pieceRowPosition, pieceColumnPosition, desiredDirection, 'D');
            }
        }
    }

    public static void moveBlackKingPiece(char[][] board, int pieceRowPosition, int pieceColumnPosition, char desiredDirection, char downOrUpMovement){
        boolean isLegalMove = checkIfBlackKingMovementAvailable(board, pieceRowPosition, pieceColumnPosition, desiredDirection, downOrUpMovement);

        while (!isLegalMove){
            System.out.println("Insira um movimento válido");
            System.out.print("Black movement (row, column, direction[L/R], downOrUp[U/D]): ");
            pieceRowPosition = sc.nextInt() - 1;
            pieceColumnPosition = sc.nextInt() - 1;
            desiredDirection = sc.next().charAt(0);
            downOrUpMovement = sc.next().charAt(0);

            isLegalMove = checkIfBlackKingMovementAvailable(board, pieceRowPosition, pieceColumnPosition, desiredDirection, downOrUpMovement);
        }

        board[pieceRowPosition][pieceColumnPosition] = ' ';
        // Upside movement
        if(downOrUpMovement == 'U') {
            if (desiredDirection == 'L' && board[pieceRowPosition - 1][pieceColumnPosition - 1] == ' ') {
                board[pieceRowPosition - 1][pieceColumnPosition - 1] = 'B';
            } else if (desiredDirection == 'R' && board[pieceRowPosition - 1][pieceColumnPosition + 1] == ' ') {
                board[pieceRowPosition - 1][pieceColumnPosition + 1] = 'B';
            } else {
                blackKingCaptures(board, pieceRowPosition, pieceColumnPosition, desiredDirection, 'U');
            }
        }

        // Downside movement
        else if(downOrUpMovement == 'D'){
            if (desiredDirection == 'L'&& board[pieceRowPosition+1][pieceColumnPosition-1] == ' '){
                board[pieceRowPosition+1][pieceColumnPosition-1] = 'B';
            }
            else if(desiredDirection == 'R' && board[pieceRowPosition+1][pieceColumnPosition+1] == ' '){
                board[pieceRowPosition+1][pieceColumnPosition+1] = 'B';
            }
            else{
                blackKingCaptures(board, pieceRowPosition, pieceColumnPosition, desiredDirection, 'D');
            }
        }
    }

    // Checking if checker's movement is legal
    public static boolean checkIfWhiteMovementAvailable(char[][] board, int pieceRowPosition, int pieceColumnPosition, char direction){
        if(!isWhitePiece(board, pieceRowPosition, pieceColumnPosition)) {
            return false;
        }
        if (pieceRowPosition == 0){
            System.out.println("Não pode mover para cima, já está na linha superior");
            return false;
        }
        return isWhiteForwardMovementAvailable(board, pieceRowPosition, pieceColumnPosition, direction);
    }

    public static boolean checkIfBlackMovementAvailable(char[][] board, int pieceRowPosition, int pieceColumnPosition, char direction){
        if(!isBlackPiece(board, pieceRowPosition, pieceColumnPosition)){
            System.out.println("Não é uma peça preta");
            return false;
        }

        if (pieceRowPosition >= 7){
            System.out.println("Não pode mover para baixo, já está na linha inferior");
            return false;
        }

        if(direction == 'L'){
            if(pieceColumnPosition == 0){
                System.out.println("Não pode mover para a esquerda, já está na coluna mais à esquerda");
                return false;
            }
            else if(pieceColumnPosition == 1 && board[pieceRowPosition+1][pieceColumnPosition-1] == 'w'){
                System.out.println("Posição para a esquerda não está vazia");
                return false;
            }

            return board[pieceRowPosition+1][pieceColumnPosition-1] != 'b';
        }

        if(direction == 'R'){
            if (pieceColumnPosition == 7){
                System.out.println("Não pode mover para a direita, já está na coluna mais à direita");
                return false;
            }
            else if(pieceColumnPosition == 6 && board[pieceRowPosition+1][pieceColumnPosition+1] == 'w'){
                System.out.println("Posição para a direita não está vazia");
                return false;
            }
            return board[pieceRowPosition+1][pieceColumnPosition+1] != 'b';
        }

        return false;
    }

    // Checking if king's movement is legal
    public static boolean checkIfWhiteKingMovementAvailable(char[][] board, int pieceRowPosition, int pieceColumnPosition, char desiredDirection, char downOrUpMovement){
        if(!isWhitePiece(board, pieceRowPosition, pieceColumnPosition)){
            return false;
        }
        if (downOrUpMovement == 'U'){
            return isWhiteForwardMovementAvailable(board, pieceRowPosition, pieceColumnPosition, desiredDirection);
        }

        else if(downOrUpMovement == 'D'){
            return isWhiteBackwardMovementAvailable(board, pieceRowPosition, pieceColumnPosition, desiredDirection);
        }

        return false;
    }

    public static boolean checkIfBlackKingMovementAvailable(char[][] board, int pieceRowPosition, int pieceColumnPosition, char desiredDirection, char downOrUpMovement){
        if(!isBlackPiece(board, pieceRowPosition, pieceColumnPosition)){
            return false;
        }
        if (downOrUpMovement == 'U'){
            return isBlackForwardMovementAvailable(board, pieceRowPosition, pieceColumnPosition, desiredDirection);
        }
        else if(downOrUpMovement == 'D'){
            return isBlackBackwardMovementAvailable(board, pieceRowPosition, pieceColumnPosition, desiredDirection);
        }

        return false;
    }

    // Minor checks for White
    public static boolean isWhitePiece(char[][] board, int pieceRowPosition, int pieceColumnPosition){
        if(board[pieceRowPosition][pieceColumnPosition] != 'w' && board[pieceRowPosition][pieceColumnPosition] != 'W'){
            System.out.println("Não é uma peça branca");
            return false;
        }
        return true;
    }

    public static boolean isWhiteForwardMovementAvailable(char[][] board, int pieceRowPosition, int pieceColumnPosition, char direction){
        if(direction == 'L'){
            if(pieceColumnPosition == 0){
                System.out.println("Não pode mover para a esquerda, já está na coluna mais à esquerda");
                return false;
            }
            else if(pieceColumnPosition == 1 && board[pieceRowPosition-1][pieceColumnPosition-1] == 'b' || pieceColumnPosition == 1 && board[pieceRowPosition-1][pieceColumnPosition-1] == 'B'){
                System.out.println("Posição para a esquerda não está vazia");
                return false;
            }

            return board[pieceRowPosition-1][pieceColumnPosition-1] != 'w' && board[pieceRowPosition-1][pieceColumnPosition-1] != 'W';
        }

        if(direction == 'R'){
            if (pieceColumnPosition == 7){
                System.out.println("Não pode mover para a direita, já está na coluna mais à direita");
                return false;
            }
            else if(pieceColumnPosition == 6 && board[pieceRowPosition-1][pieceColumnPosition-1] == 'b' || pieceColumnPosition == 6 && board[pieceRowPosition-1][pieceColumnPosition-1] == 'B'){
                System.out.println("Posição para a direita não está vazia");
                return false;
            }

            return board[pieceRowPosition-1][pieceColumnPosition+1] != 'w' && board[pieceRowPosition-1][pieceColumnPosition+1] != 'W';
        }
        return false;
    }

    public static boolean isWhiteBackwardMovementAvailable(char[][] board, int pieceRowPosition, int pieceColumnPosition, char direction){
        if(direction == 'L'){
            if(pieceColumnPosition == 0){
                System.out.println("Não pode mover para a esquerda, já está na coluna mais à esquerda");
                return false;
            }
            else if(pieceColumnPosition == 1 && board[pieceRowPosition+1][pieceColumnPosition-1] == 'b' || board[pieceRowPosition+1][pieceColumnPosition-1] == 'B'){
                System.out.println("Posição para a esquerda não está vazia");
                return false;
            }

            return board[pieceRowPosition+1][pieceColumnPosition-1] != 'w' && board[pieceRowPosition+1][pieceColumnPosition-1] != 'W';
        }
        else if(direction == 'R'){
            if (pieceColumnPosition == 7){
                System.out.println("Não pode mover para a direita, já está na coluna mais à direita");
                return false;
            }
            else if(pieceColumnPosition == 6 && board[pieceRowPosition+1][pieceColumnPosition+1] == 'b' || board[pieceRowPosition+1][pieceColumnPosition-1] == 'B'){
                System.out.println("Posição para a direita não está vazia");
                return false;
            }

            return board[pieceRowPosition+1][pieceColumnPosition+1] != 'w' && board[pieceRowPosition+1][pieceColumnPosition+1] != 'W';
        }


        return false;
    }

    // Minor checks for Black
    public static boolean isBlackPiece(char[][] board, int pieceRowPosition, int pieceColumnPosition){
        if(board[pieceRowPosition][pieceColumnPosition] != 'b' && board[pieceRowPosition][pieceColumnPosition] != 'B'){
            System.out.println("Não é uma peça preta");
            return false;
        }
        return true;
    }

    public static boolean isBlackForwardMovementAvailable(char[][] board, int pieceRowPosition, int pieceColumnPosition, char direction){
        if(direction == 'L'){
            if(pieceColumnPosition == 0){
                System.out.println("Não pode mover para a esquerda, já está na coluna mais à esquerda");
                return false;
            }
            else if(pieceColumnPosition == 1 && board[pieceRowPosition-1][pieceColumnPosition-1] == 'w' || pieceColumnPosition == 1 && board[pieceRowPosition-1][pieceColumnPosition-1] == 'W'){
                System.out.println("Posição para a esquerda não está vazia");
                return false;
            }

            return board[pieceRowPosition-1][pieceColumnPosition-1] != 'b' && board[pieceRowPosition-1][pieceColumnPosition-1] != 'B';
        }

        if(direction == 'R'){
            if (pieceColumnPosition == 7){
                System.out.println("Não pode mover para a direita, já está na coluna mais à direita");
                return false;
            }
            else if(pieceColumnPosition == 6 && board[pieceRowPosition-1][pieceColumnPosition+1] == 'w' || pieceColumnPosition == 6 && board[pieceRowPosition-1][pieceColumnPosition-1] == 'W'){
                System.out.println("Posição para a direita não está vazia");
                return false;
            }

            return board[pieceRowPosition-1][pieceColumnPosition+1] != 'b' && board[pieceRowPosition-1][pieceColumnPosition+1] != 'B';
        }
        return false;
    }

    public static boolean isBlackBackwardMovementAvailable(char[][] board, int pieceRowPosition, int pieceColumnPosition, char direction){
        if(direction == 'L'){
            if(pieceColumnPosition == 0){
                System.out.println("Não pode mover para a esquerda, já está na coluna mais à esquerda");
                return false;
            }
            else if(pieceColumnPosition == 1 && board[pieceRowPosition+1][pieceColumnPosition-1] == 'w' || board[pieceRowPosition+1][pieceColumnPosition-1] == 'W'){
                System.out.println("Posição para a esquerda não está vazia");
                return false;
            }

            return board[pieceRowPosition+1][pieceColumnPosition-1] != 'b' && board[pieceRowPosition+1][pieceColumnPosition-1] != 'B';
        }

        if(direction == 'R'){
            if (pieceColumnPosition == 7){
                System.out.println("Não pode mover para a direita, já está na coluna mais à direita");
                return false;
            }
            else if(pieceColumnPosition == 6 && board[pieceRowPosition+1][pieceColumnPosition+1] == 'w' || board[pieceRowPosition+1][pieceColumnPosition-1] == 'W'){
                System.out.println("Posição para a direita não está vazia");
                return false;
            }

            return board[pieceRowPosition+1][pieceColumnPosition+1] != 'b' && board[pieceRowPosition+1][pieceColumnPosition+1] != 'B';
        }

        return false;
    }

    // Checkers Piece Capture
    public static void whiteCaptures(char[][] board, int pieceRowPosition, int pieceColumnPosition, char direction){
        if(isWhiteCaptureLegal(board, pieceRowPosition, pieceColumnPosition, direction)){
            board[pieceRowPosition][pieceColumnPosition] = ' ';

            if (direction == 'L'){
                board[pieceRowPosition-1][pieceColumnPosition-1] = ' ';
                board[pieceRowPosition-2][pieceColumnPosition-2] = 'w';
            }
            else if(direction == 'R'){
                board[pieceRowPosition-1][pieceColumnPosition+1] = ' ';
                board[pieceRowPosition-2][pieceColumnPosition+2] = 'w';
            }

        }
    }

    public static void whiteKingCaptures(char[][] board, int pieceRowPosition, int pieceColumnPosition, char direction, char downOrUp){
        if(isWhiteKingCaptureLegal(board, pieceRowPosition, pieceColumnPosition, direction, downOrUp)){
            // Upside Capture
            if (downOrUp == 'U'){
                if (direction == 'L'){
                    board[pieceRowPosition-1][pieceColumnPosition-1] = ' ';
                    board[pieceRowPosition-2][pieceColumnPosition-2] = 'W';
                }
                else if(direction == 'R'){
                    board[pieceRowPosition-1][pieceColumnPosition+1] = ' ';
                    board[pieceRowPosition-2][pieceColumnPosition+2] = 'W';
                }
            }

            // Downside Capture
            else if(downOrUp == 'D'){
                if (direction == 'L'){
                    board[pieceRowPosition+1][pieceColumnPosition-1] = ' ';
                    board[pieceRowPosition+2][pieceColumnPosition-2] = 'W';
                }
                else if(direction == 'R'){
                    board[pieceRowPosition+1][pieceColumnPosition+1] = ' ';
                    board[pieceRowPosition+2][pieceColumnPosition+2] = 'W';
                }
            }
        }
    }

    public static void blackCaptures(char[][] board, int pieceRowPosition, int pieceColumnPosition, char direction){
        if(isBlackCaptureLegal(board, pieceRowPosition, pieceColumnPosition, direction)){
            board[pieceRowPosition][pieceColumnPosition] = ' ';

            if (direction == 'L'){
                board[pieceRowPosition+1][pieceColumnPosition-1] = ' ';
                board[pieceRowPosition+2][pieceColumnPosition-2] = 'b';
            }
            else if(direction == 'R'){
                board[pieceRowPosition+1][pieceColumnPosition+1] = ' ';
                board[pieceRowPosition+2][pieceColumnPosition+2] = 'b';
            }

        }
    }

    public static void blackKingCaptures(char[][] board, int pieceRowPosition, int pieceColumnPosition, char direction, char downOrUp){
        if(isBlackKingCaptureLegal(board, pieceRowPosition, pieceColumnPosition, direction, downOrUp)){
            // Upside Capture
            if (downOrUp == 'U'){
                if (direction == 'L'){
                    board[pieceRowPosition-1][pieceColumnPosition-1] = ' ';
                    board[pieceRowPosition-2][pieceColumnPosition-2] = 'B';
                }
                else if(direction == 'R'){
                    board[pieceRowPosition-1][pieceColumnPosition+1] = ' ';
                    board[pieceRowPosition-2][pieceColumnPosition+2] = 'B';
                }
            }
            // Downside Capture
            else if(downOrUp == 'D'){
                if (direction == 'L'){
                    board[pieceRowPosition+1][pieceColumnPosition-1] = ' ';
                    board[pieceRowPosition+2][pieceColumnPosition-2] = 'B';
                }
                else if(direction == 'R'){
                    board[pieceRowPosition+1][pieceColumnPosition+1] = ' ';
                    board[pieceRowPosition+2][pieceColumnPosition+2] = 'B';
                }
            }
        }
    }

    // Checking if move is legal
    public static boolean isWhiteCaptureLegal(char[][] board, int pieceRowPosition, int pieceColumnPosition, char direction){
        if (direction == 'L'){
            if(pieceColumnPosition > 1 && board[pieceRowPosition-1][pieceColumnPosition-1] == 'b' || board[pieceRowPosition-1][pieceColumnPosition-1] == 'B'){
                return board[pieceRowPosition-2][pieceColumnPosition-2] == ' ';
            }
            return false;
        }
        else if (direction == 'R'){
            if (pieceColumnPosition < 6 && board[pieceRowPosition-1][pieceColumnPosition+1] == 'b' || board[pieceRowPosition-1][pieceColumnPosition+1] == 'B'){
                return board[pieceRowPosition-2][pieceColumnPosition+2] == ' ';
            }
        }

        return false;
    }

    public static boolean isWhiteKingCaptureLegal(char[][] board, int pieceRowPosition, int pieceColumnPosition, char direction, char upOrDown){
        if(upOrDown == 'U') {
            if (direction == 'L') {
                if (pieceColumnPosition > 1 && board[pieceRowPosition - 1][pieceColumnPosition - 1] == 'b' || board[pieceRowPosition - 1][pieceColumnPosition - 1] == 'B') {
                    return board[pieceRowPosition - 2][pieceColumnPosition - 2] == ' ';
                }
                return false;
            } else if (direction == 'R') {
                if (pieceColumnPosition < 6 && board[pieceRowPosition - 1][pieceColumnPosition + 1] == 'b' || board[pieceRowPosition - 1][pieceColumnPosition + 1] == 'B') {
                    return board[pieceRowPosition - 2][pieceColumnPosition + 2] == ' ';
                }
            }
        }
        else if(upOrDown == 'D'){
            if (direction == 'L') {
                if (pieceColumnPosition > 1 && board[pieceRowPosition + 1][pieceColumnPosition - 1] == 'b' || board[pieceRowPosition + 1][pieceColumnPosition - 1] == 'B') {
                    return board[pieceRowPosition + 2][pieceColumnPosition - 2] == ' ';
                }
                return false;
            } else if (direction == 'R') {
                if (pieceColumnPosition < 6 && board[pieceRowPosition + 1][pieceColumnPosition + 1] == 'b' || board[pieceRowPosition + 1][pieceColumnPosition + 1] == 'B') {
                    return board[pieceRowPosition + 2][pieceColumnPosition + 2] == ' ';
                }
            }
        }

        return false;
    }

    public static boolean isBlackCaptureLegal(char[][] board, int pieceRowPosition, int pieceColumnPosition, char direction){
            if (direction == 'L'){
                if(pieceColumnPosition > 1 && board[pieceRowPosition+1][pieceColumnPosition-1] == 'w' || board[pieceRowPosition+1][pieceColumnPosition-1] == 'W'){
                    return board[pieceRowPosition+2][pieceColumnPosition-2] == ' ';
                }
                return false;
            }
            else if (direction == 'R'){
                if (pieceColumnPosition < 6 && board[pieceRowPosition+1][pieceColumnPosition+1] == 'w' || board[pieceRowPosition+1][pieceColumnPosition+1] == 'W'){
                    return board[pieceRowPosition+2][pieceColumnPosition+2] == ' ';
                }
            }

            return false;
        }

    public static boolean isBlackKingCaptureLegal(char[][] board, int pieceRowPosition, int pieceColumnPosition, char direction, char upOrDown){
        // UpsideMove
        if(upOrDown == 'U') {
            // Upside to the Left
            if (direction == 'L') {
                if (pieceColumnPosition > 1 && board[pieceRowPosition - 1][pieceColumnPosition - 1] == 'w' || board[pieceRowPosition - 1][pieceColumnPosition - 1] == 'W') {
                    return board[pieceRowPosition - 2][pieceColumnPosition - 2] == ' ';
                }
                return false;
                // Upside to the Right
            } else if (direction == 'R') {
                if (pieceColumnPosition < 6 && board[pieceRowPosition - 1][pieceColumnPosition + 1] == 'w' || board[pieceRowPosition - 1][pieceColumnPosition + 1] == 'W') {
                    return board[pieceRowPosition - 2][pieceColumnPosition + 2] == ' ';
                }
            }
        }
        // DownsideMove
        else if(upOrDown == 'D'){
            // Downside to the Left
            if (direction == 'L') {
                if (pieceColumnPosition > 1 && board[pieceRowPosition + 1][pieceColumnPosition - 1] == 'w' || board[pieceRowPosition + 1][pieceColumnPosition - 1] == 'W') {
                    return board[pieceRowPosition + 2][pieceColumnPosition - 2] == ' ';
                }
                return false;
                // Downside to the Right
            } else if (direction == 'R') {
                if (pieceColumnPosition < 6 && board[pieceRowPosition + 1][pieceColumnPosition + 1] == 'w' || board[pieceRowPosition + 1][pieceColumnPosition + 1] == 'W') {
                    return board[pieceRowPosition + 2][pieceColumnPosition + 2] == ' ';
                }
            }
        }

        return false;
    }
}
