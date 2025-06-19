package core.model;

import core.utils.BoardInitializer;

import java.util.*;

public class Board {

    private final Map<Position, Piece> board;  // 每個位置當下對應的棋子（若有）
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


    // 在 Board 類別中加入：
    public Board deepCopy() {
        Board copy = new Board();

        // 複製所有棋子
        for (Map.Entry<Position, Piece> entry : this.board.entrySet()) {
            Position pos = new Position(entry.getKey().getQ(), entry.getKey().getR());
            Piece piece = entry.getValue();
            copy.board.put(pos, new Piece(piece.getPlayerColor())); // 你可能要根據實際 Piece 結構修正
        }

        // 複製合法位置資訊（如果有）
        for (Map.Entry<Position, PlayerColor> entry : this.validPositions.entrySet()) {
            Position pos = new Position(entry.getKey().getQ(), entry.getKey().getR());
            copy.validPositions.put(pos, entry.getValue());
        }

        return copy;
    }



}

