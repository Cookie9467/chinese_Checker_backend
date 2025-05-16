// BoardState.java
package core.model;
import java.util.*;

public class BoardState {
    private Map<Position, Piece> snapshotMap;

    public BoardState(Map<Position, Piece> snapshotMap) {
        this.snapshotMap = new HashMap<>(snapshotMap);
    }

    public static BoardState fromBoard(Board board) {
        return new BoardState(board.getBoardMap());
    }

    public Piece getPieceAt(Position pos) {
        return snapshotMap.get(pos);
    }

    public boolean isOccupied(Position pos) {
        return snapshotMap.containsKey(pos);
    }

    public boolean isValidPosition(Position pos) {
        return true; // 補上合法格子條件邏輯
    }

    public boolean isInOpponentRegion(PlayerColor color, Position pos) {
        return true; // 這裡可依顏色和目標區域配置擴充
    }

    public boolean equals(BoardState other) {
        return this.snapshotMap.equals(other.snapshotMap);
    }
}