package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Bordergame.Board;
import Bordergame.Piece;
import Bordergame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

    private Board board;
    private int turn;
    private Color currentPlayer;
    private boolean check;

    private List<Piece> piecesONTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean getCheck(){
        return check;
    }

    // Construtor padrão
    public ChessMatch() {
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
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
                mat[i][j] = (ChessPiece) board.piece(i, j); // Certifique-se de que o método piece retorna um ChessPiece
                                                            // ou um tipo compatível
            }
        }
        return mat;
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMove();
    }

    public ChessPiece perfomChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);

        if (testCheck(currentPlayer)) {
            undoMove(source, target, capturedPiece);
            throw new ChessException("Você não pode se colocar em xeque!");
        }

       check = (testCheck(opponent(currentPlayer))) ? true : false;

        nextTurn();
        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);
        if (capturedPiece != null) {
            piecesONTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }

        return capturedPiece;
    }

    private void undoMove(Position source, Position target, Piece capturedPiece) {
        Piece p = board.removePiece(target);
        board.placePiece(p, source);

        if (capturedPiece != null) {
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesONTheBoard.add(capturedPiece);
        }
    }

    private void validateSourcePosition(Position position) {
        if (!board.thereIsPiece(position)) {
            throw new ChessException("Não existe peça nessa posição ");
        }
        if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
            throw new ChessException("A peça escolhida não é sua");
        }
        if (!board.piece(position).isthereAnyPossibleMove()) {
            throw new ChessException("Não existe movimentos para peça escolhida: ");
        }
    }

    private void validateTargetPosition(Position source, Position target) {
        if (!board.piece(source).possibleMove(target)) {
            throw new ChessException("A peça escolhida não pode se mover para a posição de destino");
        }
    }

    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private Color opponent(Color color) {
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private ChessPiece king(Color color) {
        List<Piece> list = piecesONTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
                .collect(Collectors.toList());
        for (Piece p : list) {
            if (p instanceof King) {
                return (ChessPiece) p;
            }
        }
        throw new IllegalStateException("There is no " + color + "King on the board");
    }

private boolean testCheck(Color color){
    Position kingPosition = king(color).getChessPosition().toPosition();
    List<Piece> opponentPieces = piecesONTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == opponent(color))
    .collect(Collectors.toList());
    for (Piece p : opponentPieces) {
        boolean [][] mat = p.possibleMove();
        if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
            return true;
        }
    }
    return false;
}

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        System.out.println("Placing " + piece + " at " + column + row); // Mensagem de debug
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesONTheBoard.add(piece);
    }

    private void initialSetup() {
        System.out.println("Initializing setup..."); // Mensagem de debug
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
        System.out.println("Setup completed."); // Mensagem de debug
    }
}
