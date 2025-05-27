package core.model;

// Game.java
// 功能：控制整體遊戲流程，包括玩家輪替、合法移動檢查、勝利判斷
// 狀態：已實作
import java.util.*;
import core.rules.MoveValidator;

public class Game {
    private List<Player> players; // 所有參與遊戲的玩家
    private Board board; // 當前遊戲棋盤
    private GameState currentState; // 當前遊戲狀態
    private int currentTurn; // 當前回合編號

    public Game(List<Player> players) { // 建構子，初始化玩家、棋盤與狀態
        this.players = players;
        Map<Position, PlayerColor> validPositions = BoardInitializer.addBoardToValidPosition();
        this.board = new Board(validPositions);
        this.currentState = new GameState(0, players.get(0), BoardState.fromBoard(board));
        this.currentTurn = 0;
    }

    public void startGame() { // 顯示開始訊息（可擴充為開始動畫）
        System.out.println("Game started with " + players.size() + " players.");
    }

    public boolean makeMove(Move move) { // 執行一個移動動作，並更新狀態與勝利判斷
        Position from = move.getFrom();
        Position to = move.getTo();

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

    public void nextTurn() { // 將回合推進到下一位玩家
        currentTurn++;
        currentState.advanceTurn(players);
    }

    public boolean isGameOver() { // 回傳遊戲是否已結束
        return currentState.isGameOver();
    }

    public Player getCurrentPlayer() { // 回傳目前輪到的玩家
        return players.get(currentTurn % players.size());
    }

    private boolean checkVictory(Player player) { // 判斷該玩家是否已完成勝利條件
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