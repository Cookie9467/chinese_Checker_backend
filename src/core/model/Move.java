package core.model;

import core.rules.MoveValidator;
import java.util.List;

public class Move {
    private final Board board;
    private final MoveValidator validator;

    public Move(Board board) {
        this.board = board;
        this.validator = new MoveValidator(board);
    }

    /**
     * 若移動有效，則將棋子從一個位置移動到另一個位置
     *
     * @param from 要移動的棋子的起始位置
     * @param to 棋子的目標位置
     * @return 若移動成功則返回true，否則返回false
     */
    public boolean pieceMove(Position from, Position to) {
        // 檢查起始位置是否有棋子
        if (!board.isOccupied(from)) {
            return false;
        }

        // 獲取起始位置的棋子
        Piece piece = board.getBoard().get(from);
        PlayerColor pieceColor = piece.getPlayerColor();

        // 檢查目標位置是否是該棋子的有效相鄰位置
        List<Position> validMoves = validator.neighbor(from, pieceColor);
        if (!validMoves.contains(to)) {
            return false;
        }

        // 執行移動
        return setPosition(piece, from, to);
    }

    /**
     * 將棋子設置到棋盤上的新位置
     *
     * @param piece 要移動的棋子
     * @param from 原始位置
     * @param to 目標位置
     * @return 若設置成功則返回true
     */
    private boolean setPosition(Piece piece, Position from, Position to) {
        // 從原始位置移除棋子
        board.getBoard().remove(from);

        // 將棋子放置在新位置
        board.getBoard().put(to, piece);

        return true;
    }
}