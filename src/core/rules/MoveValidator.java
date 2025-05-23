package core.rules;

import core.model.Position;
import core.model.PlayerColor;
import core.model.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MoveValidator {

    private final Board board;
    private final Map<Position, PlayerColor> validPositions;

    // #constructor
    public MoveValidator(Board board){
        this.board = board;
        this.validPositions = board.getValidPositions();
    }

    // 顯示周圍為valid的格子
    public List<Position> neighbor(Position piecePosition, PlayerColor curPlayerColor) {

        List<Position> neighbors = new ArrayList<>();
        int q = piecePosition.getQ();
        int r = piecePosition.getR();

        // 以 axial coordinate 計算六個方向
        Position[] directions = new Position[]{
                new Position(q + 1, r),     // 東
                new Position(q - 1, r),     // 西
                new Position(q, r - 1),     // 西南
                new Position(q, r + 1),     // 東北
                new Position(q - 1, r + 1), // 西北
                new Position(q + 1, r - 1)  // 東南
        };

        for (Position neighborPos : directions) {
            // 檢查是否為合法位置 不合法就不管
            if(!isValidPosition(neighborPos)) { continue; }
            // 檢查該位置是否被佔用 被佔用就不管
            if(isOccupied((neighborPos))) { continue; }
            // 不是目標區域就就不管
            if(!isInOpponentRegion(curPlayerColor, neighborPos)){ continue; }

            neighbors.add(neighborPos);
        }

        return neighbors;
    }

    // 查詢是否被占用
    public boolean isOccupied(Position pos) {
        return board.getBoard().containsKey(pos);
    }

    // 查詢是否為valid
    public boolean isValidPosition(Position pos) {
        return validPositions.containsKey(pos);
    }

    // 是否非對家區域
    public boolean isInOpponentRegion(PlayerColor curPieceColor, Position targetPos){
        PlayerColor opponentColor = PlayerColor.getOpponent(curPieceColor);
        PlayerColor targetColor = validPositions.get(targetPos);

        return curPieceColor != targetColor &&
                curPieceColor != PlayerColor.NONE;
    }

}
