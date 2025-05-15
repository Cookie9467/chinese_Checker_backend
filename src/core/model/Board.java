package core.model;

import java.util.*;

public class Board implements BoardState{

    private final Map<Position, Piece> board;  // 每個位置對應的棋子（若有）
    private final Map<Position, PlayerColor> validPositions;

    public Board() {
        this.validPositions = BoardInitializer.addBoardToValidPosition();
        this.board = BoardInitializer.initializePieces(getValidPositions());
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

    // 是否非對家區域
    @Override
    public boolean isInOpponentRegion(PlayerColor curPieceColor, Position targetPos){
        PlayerColor opponentColor = PlayerColor.getOpponent(curPieceColor);
        PlayerColor targetColor = validPositions.get(targetPos);

        return curPieceColor != targetColor &&
               curPieceColor != PlayerColor.NONE;
    }


    // getter
    public Map<Position, PlayerColor> getValidPositions() {
        return this.validPositions;
    }
    public Map<Position, Piece> getBoard(){
        return this.board;
    }


}

