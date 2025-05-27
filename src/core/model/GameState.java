package core.model;

// GameState.java
// 功能：紀錄當前回合狀態，包括輪到誰、是否結束、棋盤快照
// 狀態：已實作
import java.util.List;

public class GameState {
    private int turnNumber; // 當前回合數
    private Player currentPlayer; // 輪到的玩家
    private boolean gameOver; // 是否遊戲結束
    private Player winner; // 獲勝者
    private BoardState boardSnapshot; // 當前棋盤快照

    public GameState(int turnNumber, Player currentPlayer, BoardState boardSnapshot) {
        this.turnNumber = turnNumber;
        this.currentPlayer = currentPlayer;
        this.boardSnapshot = boardSnapshot;
        this.gameOver = false;
        this.winner = null;
    }

    public void advanceTurn(List<Player> players) { // 輪到下一位玩家
        int index = players.indexOf(currentPlayer);
        this.currentPlayer = players.get((index + 1) % players.size());
        this.turnNumber++;
    }

    public void declareWinner(Player winner) { // 設定勝利者並結束遊戲
        this.winner = winner;
        this.gameOver = true;
    }

    public boolean isGameOver() { return gameOver; } // 查詢是否結束
    public BoardState getBoardSnapshot() { return boardSnapshot; } // 取得棋盤快照
}