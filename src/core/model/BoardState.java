package core.model;

public interface BoardState {

    boolean isValidPosition(Position pos);
    boolean isOccupied(Position pos);
    // 是否非對家區域
    boolean isInOpponentRegion(PlayerColor curPieceColor, Position targetPos);
}
