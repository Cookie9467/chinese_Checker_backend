package core.model;

// Player.java
// 功能：紀錄玩家資訊（名稱、顏色、是否為 AI、目標區域）
// 狀態：已實作（尚未支援 AI 策略模組，可擴充）
import java.util.*;

public class Player {
    private String name; // 玩家名稱
    private PlayerColor color; // 玩家顏色
    private boolean isAI; // 是否為 AI 玩家
    private Set<Position> targetArea; // 該玩家的勝利區域格子

    public Player(String name, PlayerColor color, boolean isAI, Set<Position> targetArea) {
        this.name = name;
        this.color = color;
        this.isAI = isAI;
        this.targetArea = targetArea;
    }

    public String getName() { return name; } // 取得玩家名稱
    public PlayerColor getColor() { return color; } // 取得顏色
    public boolean isAI() { return isAI; } // 是否為 AI
    public Set<Position> getTargetArea() { return targetArea; } // 取得目標區域
}