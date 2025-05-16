// Board.java
package core.model;
import java.util.*;

public class Board {
    private Map<Position, PlayerColor> validPositions;
    private Map<Position, Piece> boardMap;

    public Board(Map<Position, PlayerColor> validPositions) {
        this.validPositions = validPositions;
        this.boardMap = BoardInitializer.initializePieces(validPositions);
    }

    public Piece getPiece(Position pos) {
        return boardMap.get(pos);
    }

    public void placePiece(Position pos, Piece piece) {
        boardMap.put(pos, piece);
    }

    public void movePiece(Position from, Position to) {
        Piece piece = boardMap.remove(from);
        if (piece != null) {
            boardMap.put(to, piece);
        }
    }

    public boolean isValidPosition(Position pos) {
        return validPositions.containsKey(pos);
    }

    public Map<Position, Piece> getBoardMap() {
        return boardMap;
    }
}