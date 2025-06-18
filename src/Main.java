import core.model.*;
import core.utils.*;

import javax.swing.*;
import java.util.Map;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        System.out.println("Main started.");

        Board board = new Board();
        // 2. 建立玩家列表（你可以試試 2~6 位）
        List<Player> players = List.of(
                new Player(1, PlayerColor.RED, board.getValidPositions()),
                new Player(2, PlayerColor.YELLOW, board.getValidPositions())
        );

        GameState game = new GameState(players);

        System.out.println("=== 測試遊戲初始化 ===");
        System.out.println("目前玩家: " + game.getCurrentPlayer().getColor());

        System.out.println("\n=== 測試換人 ===");
        game.nextTurn();
        System.out.println("換下一位玩家: " + game.getCurrentPlayer().getColor());

        System.out.println("\n=== 測試勝利狀態 ===");
        System.out.println("目前是否有人勝利: " + game.isWin());
        System.out.println("是否有兩人同時勝利（平手）: " + game.isWinWin());

        System.out.println("\n=== 測試棋盤狀態（顯示幾個棋子位置）===");

        int count = 0;
        for (Map.Entry<Position, Piece> entry : board.getBoard().entrySet()) {
            if (entry.getValue() != null) {
                System.out.println("位置 " + entry.getKey() + " 上有棋子: " + entry.getValue().getPlayerColor());
                count++;
            }
            if (count >= 5) break; // 只印前 5 個有棋子的格子
        }


    }
}