package chess.pieces;

import Bordergame.Board;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
    public King(Board board, Color color) {
        super(board, color);
    }
    @Override
    public String toString(){
        return "K";
    }

    @Override
    public boolean[][] possibleMove() {
        boolean[][] m = new boolean[getBoard().getRows()][getBoard().getColumns()];
        return m;
    }
}
