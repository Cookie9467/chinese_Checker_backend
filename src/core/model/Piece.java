// Piece.java
package core.model;
import java.util.*;
public class Piece {
    private PlayerColor color;
    private Player owner;

    public Piece(PlayerColor color) {
        this.color = color;
    }

    public Piece(Player owner) {
        this.owner = owner;
        this.color = owner.getColor();
    }

    public Player getOwner() {
        return owner;
    }

    public PlayerColor getColor() {
        return color;
    }
}