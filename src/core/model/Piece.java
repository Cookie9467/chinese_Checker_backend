package core.model;

import java.awt.Color;

public class Piece{

    private final PlayerColor playerColor;

    public Piece(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }
    
    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public Piece(Piece other) {
        this.playerColor = other.playerColor;
    }


}
