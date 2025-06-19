import ai.AiEngine;
import core.model.*;
import core.utils.*;
import core.rules.*;
import core.logic.*;

import javax.swing.*;
import java.util.Map;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        System.out.println("Main started.");

        GameController gameController = new GameController(6);
        gameController.startGame();

//        Game game = new Game(6);
//        Position from = new Position(-4, -1);
//        Position to   = new Position(-4, 0);
//        boolean moved = game.tryMove(from, to);
//        System.out.println(moved);
//
//        moved = game.tryMove(new Position(-4, -1), new Position(-4, 0));
//        System.out.println(moved);


//        Game game = new Game(2); // 2位玩家 GREEN, PURPLE
//        Board board = game.getBoard();
//
//        Player current = game.getCurrentPlayer();
//        System.out.println("目前玩家：" + current.getColor());

        // 從當前玩家找到一個棋子
//        Position from = null;
//        for (Map.Entry<Position, Piece> entry : board.getBoard().entrySet()) {
//            if (entry.getValue().getPlayerColor() == current.getColor()) {
//                from = entry.getKey();
//                break;
//            }
//        }
//
//        if (from == null) {
//            System.out.println("找不到可移動的棋子");
//            return;
//        }
//        Position from = new Position(-4, -1);
//        // 嘗試找鄰近合法位置
//        MoveValidator validator = new MoveValidator(board);
//        List<Position> neighbors = validator.neighbor(from, current.getColor());
//
//        if (neighbors.isEmpty()) {
//            System.out.println("找不到可移動的位置");
//            return;
//        }
//
//        Position to = new Position(-4, 0); // 嘗試移動到第一個合法位置
//        System.out.println("嘗試從 " + from + " 移動到 " + to);
//
//        boolean moved = game.tryMove(from, to);
//        System.out.println("移動結果：" + (moved ? "成功" : "失敗"));





    }
}