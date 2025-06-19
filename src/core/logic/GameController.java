package core.logic;

import core.logic.Game;
import core.model.Player;
import core.model.Position;

public class GameController {
    private final Game game;

    public GameController(int playerCount) {
        this.game = new Game(playerCount);
        System.out.println("遊戲初始化完成，有 " + playerCount + " 位玩家。");
    }

    public void startGame() {
        while (!game.isGameOver()) {
            Player current = game.getCurrentPlayer();
            System.out.println("目前輪到: 玩家 " + current.getId() + "（" + current.getColor() + "）");

            // 這裡你可以插入 AI 移動邏輯、或模擬從 UI 取得移動
            Position from = mockFrom();  // 假資料或測試點
            Position to = mockTo();

            boolean moved = game.tryMove(from, to);
            if (moved) {
                System.out.println("玩家 " + current.getId() + " 移動成功: " + from + " -> " + to);
            } else {
                System.out.println("非法移動: " + from + " -> " + to);
            }

            // 這邊可以簡單地印出棋盤狀態（可選）
            printBoardState();
        }

        Player winner = game.getWinner();
        if (winner != null) {
            System.out.println("🎉 玩家 " + winner.getId() + "（" + winner.getColor() + "）獲勝！");
        } else {
            System.out.println("平手！");
        }
    }

    private Position mockFrom() {
        return new Position(0, 0); // 測試用
    }

    private Position mockTo() {
        return new Position(0, 1); // 測試用
    }

    private void printBoardState() {
        // 可根據 game.getBoard().getBoard() 印出狀態
        System.out.println("（此處可實作棋盤狀態印出）");
    }
}
