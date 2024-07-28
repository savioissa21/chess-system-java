package chess.pieces;

import Bordergame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {
    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "R";
    }

    @Override
    public boolean[][] possibleMove() {
        boolean[][] m = new boolean[getBoard().getRows()][getBoard().getColumns()];
        return m;
    }
}
