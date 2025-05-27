package core.model;

// PlayerColor.java
// 功能：定義所有可能的玩家顏色（包含空格）
// 狀態：已實作

public enum PlayerColor {
    RED,
    BLUE,
    GREEN,
    YELLOW,
    PURPLE,
    ORANGE,
    NONE; // 表示非玩家所屬區域（例如中心空白區）

    public static PlayerColor getOpponent(PlayerColor self) { // 回傳對家的顏色（簡化示範用，可依照遊戲邏輯修正對應關係）
        switch (self) {
            case RED: return GREEN;
            case BLUE: return ORANGE;
            case YELLOW: return PURPLE;
            case GREEN: return RED;
            case ORANGE: return BLUE;
            case PURPLE: return YELLOW;
            default: return NONE;
        }
    }
}
