package core.model;

public interface BoardState {

    boolean isValidPosition(Position pos);
    boolean isOccupied(Position pos);

}
