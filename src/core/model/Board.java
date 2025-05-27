package core.model;

// Board.java
// 功能：儲存棋盤所有合法位置與棋子擺放狀態，提供操作與查詢
// 狀態：已實作
import java.util.*;

public class Board {
    private Map<Position, PlayerColor> validPositions; // 合法的格子
    private Map<Position, Piece> boardMap; // 棋子位置對應圖

    public Board(Map<Position, PlayerColor> validPositions) {
        this.validPositions = validPositions;
        this.boardMap = BoardInitializer.initializePieces(validPositions);
    }

    public Piece getPiece(Position pos) { return boardMap.get(pos); } // 取得指定位置棋子
    public void placePiece(Position pos, Piece piece) { boardMap.put(pos, piece); } // 放置棋子
    public void movePiece(Position from, Position to) { // 移動棋子
        Piece piece = boardMap.remove(from);
        if (piece != null) boardMap.put(to, piece);
    }
    public boolean isValidPosition(Position pos) { return validPositions.containsKey(pos); } // 是否是合法格子
    public Map<Position, Piece> getBoardMap() { return boardMap; } // 取得整張棋盤資料
}
