package core.logic;

import core.logic.Game;
import ai.*;
import java.util.*;
import core.model.*;

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
            Position[] best = AiEngine.evaluate(game.getBoard(), current.getColor());
            Position from = best[0];
            Position to   = best[1];


            boolean moved = game.tryMove(from, to);
            if (moved) {
                System.out.println("玩家 " + current.getId() + " 移動成功: " + from + " -> " + to);
            } else {
                System.out.println("非法移動: " + from + " -> " + to);
            }

            // 這邊可以簡單地印出棋盤狀態（可選）
//            printBoardState();
            try {
                Thread.sleep(1); // 暫停 0.5 秒
            } catch (InterruptedException e) {
                e.printStackTrace(); // 如果被中斷就印出錯誤（通常不會發生）
            }
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

    private Game getGame(){ return this.game; }
}
