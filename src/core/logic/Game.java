package core.logic;

import core.model.*;
import core.rules.MoveValidator;

import java.util.*;

public class Game {
    private final Board board;
    private final List<Player> players;
    private final Move moveController;
    private int currentPlayerIndex;

    public Game(int numPlayers) {
        this.board = new Board();
        this.players = initializePlayers(numPlayers);
        this.moveController = new Move(board);
        this.currentPlayerIndex = 0;
    }

    // 這邊使出土法煉鋼之法
    private List<Player> initializePlayers(int numPlayers) {
        List<PlayerColor> selectedColors = switch (numPlayers) {
            case 2 -> List.of(PlayerColor.GREEN, PlayerColor.PURPLE);
            case 3 -> List.of(PlayerColor.YELLOW, PlayerColor.BLUE, PlayerColor.PURPLE);
            case 4 -> List.of(PlayerColor.BLUE, PlayerColor.RED, PlayerColor.YELLOW, PlayerColor.ORANGE);
            case 6 -> List.of(PlayerColor.GREEN, PlayerColor.YELLOW, PlayerColor.ORANGE,
                                PlayerColor.RED, PlayerColor.BLUE, PlayerColor.PURPLE);
            default -> throw new IllegalArgumentException("不支援的人數: " + numPlayers);
        };

        List<Player> result = new ArrayList<>();
        for (int i = 0; i < selectedColors.size(); i++) {
            result.add(new Player(i + 1, selectedColors.get(i), board.getValidPositions()));
        }
        return result;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public boolean tryMove(Position from, Position to) {
        Piece piece = board.getBoard().get(from);

        // 無棋子或不是當前玩家的棋子則不允許移動
        if (piece == null || piece.getPlayerColor() != getCurrentPlayer().getColor()) {
            return false;
        }

        // 執行移動（內部會驗證是否合法）
        boolean moved = moveController.pieceMove(from, to);
        if (moved) {
            nextTurn();
        }
        return moved;
    }

    private void nextTurn() {
        int total = players.size();
        do {
            currentPlayerIndex = (currentPlayerIndex + 1) % total;
        } while (isPlayerFinished(players.get(currentPlayerIndex)));
    }

    private boolean isPlayerFinished(Player player) {
        Map<Position, Piece> boardMap = board.getBoard();
        for (Position target : player.getTargetArea()) {
            Piece piece = boardMap.get(target);
            if (piece == null || piece.getPlayerColor() != player.getColor()) {
                return false;
            }
        }
        return true;
    }

    public boolean isGameOver() {
        return players.stream().anyMatch(this::isPlayerFinished);
    }

    public Player getWinner() {
        return players.stream().filter(this::isPlayerFinished).findFirst().orElse(null);
    }
}