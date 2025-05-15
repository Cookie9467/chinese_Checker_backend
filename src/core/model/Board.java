package core.model;

import core.rules.MoveValidator;

import java.util.*;

public class Board implements BoardState{

    private final Map<Position, Piece> board;  // 每個位置對應的棋子（若有）
    private final Map<Position, PlayerColor> validPositions;

    public Board() {
        this.board = new HashMap<>();
        this.validPositions = BoardInitializer.addBoardToValidPosition();



    }

    // 查詢是否被占用
    @Override
    public boolean isOccupied(Position pos) {
        return board.containsKey(pos);
    }

    // 查詢是否為valid
    @Override
    public boolean isValidPosition(Position pos) {
        return validPositions.containsKey(pos);
    }



    public Map<Position, PlayerColor> getValidPositions() {
        return this.validPositions;
    }


}

