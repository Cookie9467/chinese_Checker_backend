package core.model;

import core.rules.MoveValidator;

import java.util.*;

public class Board implements BoardState{

    private final Map<Position, Piece> board;  // 每個位置對應的棋子（若有）
    private final Map<Position, PlayerColor> validPositions;

    public Board() {
        this.validPositions = BoardInitializer.addBoardToValidPosition();
        this.board = BoardInitializer.initializePieces(getValidPositions());
    }
    
    // 查詢是否被占用
    @Override
    public boolean isOccupied(Position pos) {
        return board.containsKey(pos);
    }

    // 查詢是否為valid
    @Override
    public boolean isValidPosition(Position pos) {
        return validPositions.containsKey(pos);
    }


    // getter
    public Map<Position, PlayerColor> getValidPositions() {
        return this.validPositions;
    }
    
    public Piece getPiece(Position pos) {
        return board.get(pos);
    }

    /**
     * 獲取六個方向上的鄰近位置（基本位置計算）
     * @param pos 當前位置
     * @return 六個方向上的鄰近位置
     */
    public List<Position> getAdjacentPositions(Position pos) {
        List<Position> positions = new ArrayList<>();
        int[][] directions = {
            {1, 0},    // 東
            {-1, 0},   // 西
            {0, 1},    // 東北
            {0, -1},   // 西南
            {1, -1},   // 東南
            {-1, 1}    // 西北
        };
        
        for (int[] dir : directions) {
            int adjQ = pos.getQ() + dir[0];
            int adjR = pos.getR() + dir[1];
            positions.add(new Position(adjQ, adjR));
        }
        
        return positions;
    }

    /**
     * 檢查是否可以從起始位置跳躍到目標位置
     * @param from 起始位置
     * @param to 目標位置
     * @return 如果可以跳躍則返回true
     */
    public boolean isJumpable(Position from, Position to) {
        // 計算中間位置
        int midQ = (from.getQ() + to.getQ()) / 2;
        int midR = (from.getR() + to.getR()) / 2;
        Position mid = new Position(midQ, midR);
        
        // 檢查是否符合跳躍條件
        return isValidPosition(mid) && isValidPosition(to) && 
               isOccupied(mid) && !isOccupied(to);
    }

    /**
     * 檢查移動是否合法
     * @param from 起始位置
     * @param to 目標位置
     * @param playerColor 玩家顏色
     * @param targetColor 目標顏色
     * @return 如果移動合法則返回true
     * @throws IllegalMoveException 如果移動不合法，提供具體原因
     */
    public boolean validMove(Position from, Position to, PlayerColor playerColor, PlayerColor targetColor) 
            throws IllegalMoveException {
        if (!isOccupied(from)) {
            throw new IllegalMoveException("起始位置沒有棋子");
        }
        
        // 檢查棋子顏色是否屬於當前玩家
        Piece piece = getPiece(from);
        if (piece.getColor() != playerColor) {
            throw new IllegalMoveException("這不是您的棋子");
        }
        
        if (isOccupied(to)) {
            throw new IllegalMoveException("目標位置已被占用");
        }
        
        if (!isJumpable(from, to)) {
            throw new IllegalMoveException("不能跳躍到目標位置");
        }
        
        if (isRestrictedZone(to, playerColor, targetColor)) {
            throw new IllegalMoveException("目標位置在受限區域");
        }
        
        return true;
    }

    /**
     * 獲取所有可能的跳躍位置
     * 保留原有的 neighbor 方法以保持兼容性
     * @param pos 起始位置
     * @param myStartColor 玩家顏色
     * @param myTargetColor 目標顏色
     * @return 所有可能的跳躍位置列表
     */
    public List<Position> neighbor(Position pos, PlayerColor myStartColor, PlayerColor myTargetColor) {
        return getValidJumpPositions(pos, myStartColor, myTargetColor);
    }
    
    /**
     * 獲取所有可能的跳躍位置
     * 簡化版本，只需提供玩家顏色，自動確定目標顏色
     * @param pos 起始位置
     * @param playerColor 玩家顏色
     * @return 所有可能的跳躍位置列表
     */
    public List<Position> neighbor(Position pos, PlayerColor playerColor) {
        PlayerColor targetColor = getOpponentColor(playerColor);
        return neighbor(pos, playerColor, targetColor);
    }

    /**
     * 獲取所有可能的跳躍位置（原 neighbor 方法的功能）
     * @param pos 起始位置
     * @param playerColor 玩家顏色
     * @return 所有可能的跳躍位置列表
     */
    public List<Position> getValidJumpPositions(Position pos, PlayerColor playerColor) {
        // 獲取玩家的目標顏色（對手顏色）
        PlayerColor targetColor = getOpponentColor(playerColor);
        return getValidJumpPositions(pos, playerColor, targetColor);
    }
    
    /**
     * 獲取所有可能的跳躍位置（原 neighbor 方法的功能）
     * @param pos 起始位置
     * @param playerColor 玩家顏色
     * @param targetColor 目標顏色
     * @return 所有可能的跳躍位置列表
     */
    public List<Position> getValidJumpPositions(Position pos, PlayerColor playerColor, PlayerColor targetColor) {
        List<Position> result = new ArrayList<>();
        int[][] directions = {
            {1, 0},    // 東
            {-1, 0},   // 西
            {0, 1},    // 東北
            {0, -1},   // 西南
            {1, -1},   // 東南
            {-1, 1}    // 西北
        };

        for (int[] dir : directions) {
            int destQ = pos.getQ() + 2 * dir[0];
            int destR = pos.getR() + 2 * dir[1];
            Position dest = new Position(destQ, destR);
            
            try {
                if (validMove(pos, dest, playerColor, targetColor)) {
                    result.add(dest);
                }
            } catch (IllegalMoveException e) {
                // 忽略不合法的移動
            }
        }
        
        return result;
    }

    private boolean isRestrictedZone(Position pos, PlayerColor myStartColor, PlayerColor myTargetColor) {
        PlayerColor tileColor = validPositions.get(pos);
        if (tileColor == null) return false;
        return !tileColor.equals(myStartColor) && !tileColor.equals(myTargetColor);
    }

    public Map<Position, Piece> getBoard(){
        return this.board;
    }

    /**
     * 根據玩家顏色獲取目標顏色（對手的顏色）
     * @param playerColor 玩家顏色
     * @return 目標顏色（對手顏色）
     */
    public PlayerColor getOpponentColor(PlayerColor playerColor) {
        switch (playerColor) {
            case RED: return PlayerColor.YELLOW;
            case YELLOW: return PlayerColor.RED;
            case BLUE: return PlayerColor.ORANGE;
            case ORANGE: return PlayerColor.BLUE;
            case GREEN: return PlayerColor.PURPLE;
            case PURPLE: return PlayerColor.GREEN;
            default: return PlayerColor.NONE;
        }
    }

    /**
     * 執行棋子跳躍移動
     * @param from 起始位置
     * @param to 目標位置
     * @param playerColor 玩家顏色
     * @return 如果移動成功則返回true
     * @throws IllegalMoveException 如果移動不合法，提供具體原因
     */
    public boolean movePiece(Position from, Position to, PlayerColor playerColor) 
            throws IllegalMoveException {
        // 獲取玩家的目標顏色（對手顏色）
        PlayerColor targetColor = getOpponentColor(playerColor);
        
        if (validMove(from, to, playerColor, targetColor)) {
            Piece piece = getPiece(from);
            board.remove(from);
            board.put(to, piece);
            return true;
        }
        return false; // 永遠不會執行到這裡，因為 validMove 如果失敗會丟出異常
    }
}

/**
 * 自定義異常：非法移動異常
 */
class IllegalMoveException extends Exception {
    public IllegalMoveException(String message) {
        super(message);
    }
}