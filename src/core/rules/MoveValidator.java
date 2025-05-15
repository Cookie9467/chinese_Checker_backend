package core.rules;

import core.model.Board;
import core.model.BoardState;
import core.model.Position;

import java.util.ArrayList;
import java.util.List;


public class MoveValidator {

    private final BoardState board;

    public MoveValidator(BoardState board){
        this.board = board;
    }


    public List<Position> neighbor(Position piecePosition) {

        List<Position> neighbors = new ArrayList<>();
        int q = piecePosition.getQ();
        int r = piecePosition.getR();

        // 以 axial coordinate 計算六個方向
        Position[] directions = new Position[]{
                new Position(q + 1, r),     // 東
                new Position(q - 1, r),     // 西
                new Position(q, r + 1),     // 西南
                new Position(q, r - 1),     // 東北
                new Position(q + 1, r - 1), // 西北
                new Position(q - 1, r + 1)  // 東南
        };

        for (Position neighborPos : directions) {
            // 檢查是否為合法位置 不合法就不管
            if(!board.isValidPosition(neighborPos)) { continue; }
            // 檢查該位置是否被佔用 被佔用就不管
            if(board.isOccupied((neighborPos))) { continue; }
            neighbors.add(neighborPos);

            // [可擴充] 檢查是否為其他玩家的起始區域（目前沒這資料，未來加）

            neighbors.add(neighborPos);
        }

        return neighbors;
    }
}