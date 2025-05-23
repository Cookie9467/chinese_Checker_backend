package core.model;

import core.utils.BoardInitializer;

import java.util.*;

public class Board {

    private final Map<Position, Piece> board;  // 每個位置對應的棋子（若有）
    private final Map<Position, PlayerColor> validPositions;

    public Board() {
        this.validPositions = BoardInitializer.addBoardToValidPosition();
        this.board = BoardInitializer.initializePieces(getValidPositions());
    }





    // getter
    public Map<Position, PlayerColor> getValidPositions() {
        return this.validPositions;
    }
    public Map<Position, Piece> getBoard(){
        return this.board;
    }


}

