// GameState.java
package core.model;
import java.util.*;

public class GameState {
    private int turnNumber;
    private Player currentPlayer;
    private boolean gameOver;
    private Player winner;
    private BoardState boardSnapshot;

    public GameState(int turnNumber, Player currentPlayer, BoardState boardSnapshot) {
        this.turnNumber = turnNumber;
        this.currentPlayer = currentPlayer;
        this.boardSnapshot = boardSnapshot;
        this.gameOver = false;
        this.winner = null;
    }

    public void advanceTurn(List<Player> players) {
        int index = players.indexOf(currentPlayer);
        this.currentPlayer = players.get((index + 1) % players.size());
        this.turnNumber++;
    }

    public void declareWinner(Player winner) {
        this.winner = winner;
        this.gameOver = true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public BoardState getBoardSnapshot() {
        return boardSnapshot;
    }
}