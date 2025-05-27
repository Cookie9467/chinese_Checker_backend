package core.model;

// Move.java
// 功能：封裝一次棋子的移動，包括起點與終點
// 狀態：已實作
public class Move {
    private final Position from; // 起始位置
    private final Position to;   // 目標位置

    public Move(Position from, Position to) { // 建構子
        this.from = from;
        this.to = to;
    }

    public Position getFrom() { return from; } // 取得起點
    public Position getTo() { return to; }     // 取得終點
}
