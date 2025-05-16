// Game.java
package core.model;
import java.util.*;
import core.rules.MoveValidator;

public class Game {
    private List<Player> players;
    private Board board;
    private GameState currentState;
    private int currentTurn;

    public Game(List<Player> players) {
        this.players = players;
        Map<Position, PlayerColor> validPositions = BoardInitializer.addBoardToValidPosition();
        this.board = new Board(validPositions);
        this.currentState = new GameState(0, players.get(0), BoardState.fromBoard(board));
        this.currentTurn = 0;
    }

    public void startGame() {
        System.out.println("Game started with " + players.size() + " players.");
    }

    public boolean makeMove(Position from, Position to) {
        Player currentPlayer = getCurrentPlayer();
        BoardState snapshot = BoardState.fromBoard(board);
        MoveValidator validator = new MoveValidator(snapshot);

        List<Position> validNeighbors = validator.neighbor(from, currentPlayer.getColor());
        if (!validNeighbors.contains(to)) {
            System.out.println("不合法移動！");
            return false;
        }

        Piece piece = board.getPiece(from);
        if (piece == null || !piece.getColor().equals(currentPlayer.getColor())) {
            System.out.println("起始位置無合法棋子！");
            return false;
        }

        board.movePiece(from, to);

        if (checkVictory(currentPlayer)) {
            currentState.declareWinner(currentPlayer);
        }

        currentState = new GameState(currentTurn, currentPlayer, BoardState.fromBoard(board));
        return true;
    }

    public void nextTurn() {
        currentTurn++;
        currentState.advanceTurn(players);
    }

    public boolean isGameOver() {
        return currentState.isGameOver();
    }

    public Player getCurrentPlayer() {
        return players.get(currentTurn % players.size());
    }

    private boolean checkVictory(Player player) {
        Set<Position> targetArea = player.getTargetArea();
        for (Position pos : targetArea) {
            Piece piece = board.getPiece(pos);
            if (piece == null || !piece.getColor().equals(player.getColor())) {
                return false;
            }
        }
        return true;
    }
}