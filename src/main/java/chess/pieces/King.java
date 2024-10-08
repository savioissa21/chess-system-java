package chess.pieces;

import Bordergame.Board;
import Bordergame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean canMove(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p == null || p.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMove() {
        boolean[][] m = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);
        // above
        p.setValue(position.getRow() - 1, position.getColumn());
        if (getBoard().positionExists(p) && canMove(p)) {
            m[p.getRow()][p.getColumn()] = true;
        }
        //below 
        p.setValue(position.getRow() + 1, position.getColumn());
        if (getBoard().positionExists(p) && canMove(p)) {
            m[p.getRow()][p.getColumn()] = true;
        }
         //left
         p.setValue(position.getRow() , position.getColumn() -1);
         if (getBoard().positionExists(p) && canMove(p)) {
             m[p.getRow()][p.getColumn()] = true;
         }
         //rigth
         p.setValue(position.getRow() , position.getColumn() +1);
         if (getBoard().positionExists(p) && canMove(p)) {
             m[p.getRow()][p.getColumn()] = true;
         }
           //nw
           p.setValue(position.getRow() - 1, position.getColumn() -1);
           if (getBoard().positionExists(p) && canMove(p)) {
               m[p.getRow()][p.getColumn()] = true;
           }
           //ne
           p.setValue(position.getRow() - 1, position.getColumn() +1);
           if (getBoard().positionExists(p) && canMove(p)) {
               m[p.getRow()][p.getColumn()] = true;
           }
           //sw
           p.setValue(position.getRow() + 1, position.getColumn() -1);
           if (getBoard().positionExists(p) && canMove(p)) {
               m[p.getRow()][p.getColumn()] = true;
           }
           //se
           p.setValue(position.getRow() + 1, position.getColumn() +1);
           if (getBoard().positionExists(p) && canMove(p)) {
               m[p.getRow()][p.getColumn()] = true;
           }

        return m;

    }
}
