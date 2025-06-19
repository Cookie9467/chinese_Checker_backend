package ai; // 放在 core.logic 套件下，因為它需要訪問 Board 和 Move

import core.model.Player;
import core.model.PlayerColor;
import core.model.Position;
import core.model.Board;
import core.model.Piece; // 需要 Piece 才能判斷棋子顏色
import core.rules.MoveValidator; // 需要 MoveValidator 來驗證移動

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AiPlayer_easy extends Player {

    private final Random random;

    /**
     * 建構子：初始化 AI 玩家
     * @param id 玩家編號
     * @param color 玩家顏色
     * @param validPositions 由 BoardInitializer.addBoardToValidPosition() 回傳的 map
     */
    public AiPlayer_easy(int id, PlayerColor color, Map<Position, PlayerColor> validPositions) {
        super(id, color, validPositions);
        this.random = new Random();
    }

    /**
     * AI 決定下一步移動。
     * 這個方法會嘗試找出所有當前玩家的合法移動，並隨機選擇一個。
     *
     * @param board 遊戲的當前棋盤狀態
     * @param moveValidator 判斷移動合法性的驗證器
     * @return 一個包含起點和終點位置的陣列 (Position[from, to])，如果沒有合法移動則返回 null。
     */
    public Position[] decideMove(Board board, MoveValidator moveValidator) {
        // 獲取當前 AI 玩家所有棋子的位置
        List<Position> playerPiecesPositions = new ArrayList<>();
        Map<Position, Piece> boardMap = board.getBoard(); // 獲取實際棋盤的 Map

        // 遍歷整個棋盤，找出屬於當前 AI 玩家的棋子
        for (Map.Entry<Position, Piece> entry : boardMap.entrySet()) {
            Piece piece = entry.getValue();
            if (piece != null && piece.getPlayerColor() == this.getColor()) {
                playerPiecesPositions.add(entry.getKey());
            }
        }

        List<Position[]> possibleMoves = new ArrayList<>();

        // 對於每個屬於 AI 玩家的棋子，找出所有可能的合法移動
        for (Position from : playerPiecesPositions) {
            // 注意：這裡假設 moveValidator.getValidMoves(from) 可以返回所有從 from 位置的合法目的地。
            // 你的 MoveValidator 類別需要有這樣一個方法，或者你可以直接遍歷所有可能的 to 位置並用 isValidMove 判斷。
            // 由於你的 Game 類別中 tryMove 已經會呼叫 moveController.pieceMove(from, to)
            // 而 Move 類別內部又會使用 MoveValidator，所以這裡的邏輯需要稍微調整來「預測」合法的移動。
            // 一種簡單的方式是遍歷所有可能的目標位置。

            // 遍歷棋盤上的所有位置作為潛在的目標位置
            for (Position to : boardMap.keySet()) { // 可以優化為只檢查鄰近或跳躍範圍內的格子
                // 檢查這個 (from, to) 移動對於當前棋子是否合法
                // 注意：isValidMove 應該不考慮當前是誰的回合，只判斷移動路徑的合法性
                // 它也應該能夠處理單步移動和多步跳躍
                if (moveValidator.isValidMove(board.getBoard(), from, to)) {
                    // 如果合法，則將其加入到可能的移動列表中
                    possibleMoves.add(new Position[]{from, to});
                }
            }
        }

        // 如果有合法移動，則隨機選擇一個
        if (!possibleMoves.isEmpty()) {
            return possibleMoves.get(random.nextInt(possibleMoves.size()));
        } else {
            return null; // 沒有合法移動
        }
    }
}