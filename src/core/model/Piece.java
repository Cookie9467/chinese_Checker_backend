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

    // 棋子移動
//    public void moveTo(Position newPosition){
//
//    }

    // 判斷移動是否合法
//    public boolean checkMove(Position newPosition){
//
//
//        return false;
//    }

}
