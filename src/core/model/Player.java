// Player.java
package core.model;
import java.util.*;

public class Player {
    private String name;
    private PlayerColor color;
    private boolean isAI;
    private Set<Position> targetArea;

    public Player(String name, PlayerColor color, boolean isAI, Set<Position> targetArea) {
        this.name = name;
        this.color = color;
        this.isAI = isAI;
        this.targetArea = targetArea;
    }

    public String getName() {
        return name;
    }

    public PlayerColor getColor() {
        return color;
    }

    public boolean isAI() {
        return isAI;
    }

    public Set<Position> getTargetArea() {
        return targetArea;
    }
}