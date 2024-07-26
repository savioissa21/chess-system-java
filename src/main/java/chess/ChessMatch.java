package chess;

import Bordergame.Board;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

    private Board board;

    // Construtor padrão
    public ChessMatch() {
        board = new Board(8, 8);
        initialSetup();
        // Inicializa o tabuleiro no construtor padrão
    }

    // Construtor com tabuleiro como parâmetro
    public ChessMatch(Board board) {
        this.board = board;  // Corrige a atribuição do tabuleiro
    }

    // Método para obter as peças do tabuleiro
    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j);  // Certifique-se de que o método piece retorna um ChessPiece ou um tipo compatível
            }
        }
        return mat;
    }
    private void placeNewPiece(char column,int row,ChessPiece piece) {
        board.placePiece(piece,new ChessPosition(column, row).toPosition());
    }
    private void initialSetup(){
        placeNewPiece('b' , 6, new Rook(board, Color.WHITE));
        placeNewPiece('e' , 8, new King(board, Color.BLACK));
        placeNewPiece('e' , 1, new King(board, Color.WHITE));

       ;
    }
}
