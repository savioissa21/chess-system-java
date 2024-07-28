package chess.pieces;

import Bordergame.Board;
import Bordergame.Position;
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

        Position p = new Position(0,0);
        //Above
        p.setValue(position.getRow() -1 , position.getColumn());
        while(getBoard().positionExists(p) && !getBoard().thereIsPiece(p)) {
            m[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() -1);
        }
        if (getBoard().positionExists(p) && isThereOpponent(p)){
            m[p.getRow()][p.getColumn()] = true;
        }
        //Left
        p.setValue(position.getRow() , position.getColumn()-1);
        while(getBoard().positionExists(p) && !getBoard().thereIsPiece(p)) {
            m[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn()-1);
        }
        if (getBoard().positionExists(p) && isThereOpponent(p)){
            m[p.getRow()][p.getColumn()] = true;
        }
        //Right
        p.setValue(position.getRow() , position.getColumn()+1);
        while(getBoard().positionExists(p) && !getBoard().thereIsPiece(p)) {
            m[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn()+1);
        }
        if (getBoard().positionExists(p) && isThereOpponent(p)){
            m[p.getRow()][p.getColumn()] = true;
        }
        //Below
        p.setValue(position.getRow() +1 , position.getColumn());
        while(getBoard().positionExists(p) && !getBoard().thereIsPiece(p)) {
            m[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() +1);
        }
        if (getBoard().positionExists(p) && isThereOpponent(p)){
            m[p.getRow()][p.getColumn()] = true;
        }

        return m;
    }
}
