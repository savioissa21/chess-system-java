package chess.pieces;

import Bordergame.Board;
import Bordergame.Position;
import chess.ChessPiece;
import chess.Color;

     public class Bishop extends ChessPiece {
       public Bishop (Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean[][] possibleMove() {
        boolean[][] m = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0,0);
        //NW
        p.setValue(position.getRow() -1 , position.getColumn() -1);
        while(getBoard().positionExists(p) && !getBoard().thereIsPiece(p)) {
            m[p.getRow()][p.getColumn()] = true;
            p.setValue(p.getRow() -1, p.getColumn() -1);
        }
        if (getBoard().positionExists(p) && isThereOpponent(p)){
            m[p.getRow()][p.getColumn()] = true;
        }
        //NE
        p.setValue(position.getRow() -1 , position.getColumn()+1);
        while(getBoard().positionExists(p) && !getBoard().thereIsPiece(p)) {
            m[p.getRow()][p.getColumn()] = true;
            p.setValue(p.getRow() - 1, p.getColumn() +1);;
        }
        if (getBoard().positionExists(p) && isThereOpponent(p)){
            m[p.getRow()][p.getColumn()] = true;
        }
        //SE
        p.setValue(position.getRow() +1, position.getColumn()+1);
        while(getBoard().positionExists(p) && !getBoard().thereIsPiece(p)) {
            m[p.getRow()][p.getColumn()] = true;
            p.setValue(p.getRow() + 1, p.getColumn() +1);
        }
        if (getBoard().positionExists(p) && isThereOpponent(p)){
            m[p.getRow()][p.getColumn()] = true;
        }
        //SW
        p.setValue(position.getRow() +1 , position.getColumn() -1);
        while(getBoard().positionExists(p) && !getBoard().thereIsPiece(p)) {
            m[p.getRow()][p.getColumn()] = true;
            p.setValue(p.getRow() + 1, p.getColumn() -1);;
        }
        if (getBoard().positionExists(p) && isThereOpponent(p)){
            m[p.getRow()][p.getColumn()] = true;
        }

        return m;
    }
}
