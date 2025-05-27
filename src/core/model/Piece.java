package core.model;

// Piece.java
// 功能：表示棋子，紀錄顏色與所屬玩家
// 狀態：已實作
public class Piece {
    private PlayerColor color; // 棋子顏色
    private Player owner; // 棋子所屬玩家

    public Piece(PlayerColor color) { // 使用顏色建構
        this.color = color;
    }

    public Piece(Player owner) { // 使用玩家建構
        this.owner = owner;
        this.color = owner.getColor();
    }

    public Player getOwner() { return owner; } // 取得所屬玩家
    public PlayerColor getColor() { return color; } // 取得顏色
}