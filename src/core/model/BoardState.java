package core.model;

// BoardState.java
// 功能：封裝棋盤當前狀態的快照，用於合法移動與規則驗證
// 狀態：部分實作（方法固定回傳 true，可擴充為真正的幾何邏輯）
import java.util.*;

public class BoardState {
    private Map<Position, Piece> snapshotMap; // 位置與棋子對應的快照

    public BoardState(Map<Position, Piece> snapshotMap) {
        this.snapshotMap = new HashMap<>(snapshotMap);
    }

    public static BoardState fromBoard(Board board) { // 從 Board 建立快照
        return new BoardState(board.getBoardMap());
    }

    public Piece getPieceAt(Position pos) { return snapshotMap.get(pos); } // 查詢某格棋子
    public boolean isOccupied(Position pos) { return snapshotMap.containsKey(pos); } // 該格是否被佔據
    public boolean isValidPosition(Position pos) { return true; } // 判斷是否為合法格子（尚未實作）
    public boolean isInOpponentRegion(PlayerColor color, Position pos) { return true; } // 是否進入對家區域（尚未實作）

    public boolean equals(BoardState other) { return this.snapshotMap.equals(other.snapshotMap); } // 快照是否一致
}