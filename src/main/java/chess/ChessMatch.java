package chess;

import Bordergame.Board;
import Bordergame.Piece;
import Bordergame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

    private Board board;

    // Construtor padrão
    public ChessMatch() {
        board = new Board(8, 8);
        initialSetup();
    }

    // Construtor com tabuleiro como parâmetro
    public ChessMatch(Board board) {
        this.board = board;
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
    public ChessPiece perfomChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        Piece capturedPiece = makeMove(source, target);
        return  (ChessPiece) capturedPiece;
    }
    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);
        return capturedPiece;
    }
    private void validateSourcePosition(Position position) {
        if (!board.thereIsPiece(position)){
            throw new ChessException("Não existe peça nessa posição ");
        }
        if (!board.piece(position).isthereAnyPossibleMove()){
            throw new ChessException("Não existe movimentos para peça escolhida: ");
        }
    }


    private void placeNewPiece(char column, int row, ChessPiece piece) {
        System.out.println("Placing " + piece + " at " + column + row);  // Mensagem de debug
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    private void initialSetup() {
        System.out.println("Initializing setup...");  // Mensagem de debug
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
        System.out.println("Setup completed.");  // Mensagem de debug
    }
}
