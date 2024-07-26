package chess;

import Bordergame.Board;

public class ChessMatch {

    private Board board;

    // Construtor padrão
    public ChessMatch() {
        board = new Board(8, 8);  // Inicializa o tabuleiro no construtor padrão
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
}
