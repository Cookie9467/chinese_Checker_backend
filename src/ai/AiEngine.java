package ai;

import core.model.Board;
import core.model.Move;
import core.rules.MoveValidator;
import core.model.Position;
import core.model.Piece;
import core.model.PlayerColor;

import java.util.*;

/**
 * Minimax AI：使用 α-β 剪枝，僅考慮 MoveValidator 定義的合法走法。
 * 評估函數：我方可走步數 - 對方可走步數。
 */
public class AiEngine {
    private static final int MAX_DEPTH = 3;

    public static Position[] evaluate(Board board, PlayerColor color) {
        Node result = minimax(board, color, color,
                MAX_DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, true);

        if (result.action == null) {
            return new Position[]{null, null};
        }

        Position from = getActualPosition(board, result.action.from, color);
        Position to   = getActualPosition(board, result.action.to, color);

        // Debug 印出
//        System.out.println("AI 建議移動 from: " + from + ", 該位置有棋子？ " + (board.getBoard().get(from) != null));
//        System.out.println("AI 建議移動 to  : " + to   + ", 該位置有棋子？ " + (board.getBoard().get(to) != null));

        return new Position[]{from, to};
    }

    private static Position getActualPosition(Board board, Position target, PlayerColor color) {
        for (Map.Entry<Position, Piece> entry : board.getBoard().entrySet()) {
            Position p = entry.getKey();
            Piece piece = entry.getValue();
            if (p.equals(target) && piece != null && piece.getPlayerColor() == color) {
                return p;
            }
        }
        return target;
    }

    private static Node minimax(Board board,
                                PlayerColor currentColor,
                                PlayerColor rootColor,
                                int depth,
                                int alpha,
                                int beta,
                                boolean maximizing) {
        MoveValidator validator = new MoveValidator(board);
        List<Action> actions = generateActions(board, validator, currentColor);
        if (depth == 0 || actions.isEmpty()) {
            int score = evaluateBoard(board, rootColor);
            return new Node(null, score);
        }
        Node best = new Node(null, maximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE);
        for (Action a : actions) {
            Move move = new Move(board);
            boolean moved = move.pieceMove(a.from, a.to);
            if (!moved) continue;

            Node node = minimax(board,
                    PlayerColor.getOpponent(currentColor),
                    rootColor,
                    depth - 1,
                    alpha, beta,
                    !maximizing);

            move.pieceMove(a.to, a.from);
            node.action = a;

            if (maximizing) {
                if (node.score > best.score) {
                    best = node;
                }
                alpha = Math.max(alpha, best.score);
            } else {
                if (node.score < best.score) {
                    best = node;
                }
                beta = Math.min(beta, best.score);
            }
            if (beta <= alpha) break;
        }
        return best;
    }

    private static int evaluateBoard(Board board, PlayerColor color) {
        MoveValidator validator = new MoveValidator(board);
        int myMoves = countMoves(board, validator, color);
        int oppMoves = countMoves(board, validator, PlayerColor.getOpponent(color));
        return myMoves - oppMoves;
    }

    private static int countMoves(Board board,
                                  MoveValidator validator,
                                  PlayerColor color) {
        int count = 0;
        for (Position from : board.getBoard().keySet()) {
            Piece piece = board.getBoard().get(from);
            if (piece != null && piece.getPlayerColor() == color) {
                count += validator.neighbor(from, color).size();
            }
        }
        return count;
    }

    private static List<Action> generateActions(Board board,
                                                MoveValidator validator,
                                                PlayerColor color) {
        List<Action> list = new ArrayList<>();
        for (Position from : board.getBoard().keySet()) {
            Piece piece = board.getBoard().get(from);
            if (piece != null && piece.getPlayerColor() == color) {
                for (Position to : validator.neighbor(from, color)) {
                    list.add(new Action(from, to));
                }
            }
        }
        return list;
    }

    private static class Node {
        Action action;
        int score;
        Node(Action action, int score) {
            this.action = action;
            this.score = score;
        }
    }

    private static class Action {
        Position from, to;
        Action(Position from, Position to) {
            this.from = from;
            this.to = to;
        }
    }
}
