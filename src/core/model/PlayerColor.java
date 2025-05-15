package core.model;

import java.util.Map;

public enum PlayerColor {
    RED,
    BLUE,
    GREEN,
    YELLOW,
    PURPLE,
    ORANGE,
    NONE; // 用於中間六角形或空格

    private static final Map<PlayerColor, PlayerColor> OPPONENT_MAP = Map.of(
            RED, YELLOW,
            YELLOW, RED,
            BLUE, ORANGE,
            ORANGE, BLUE,
            GREEN, PURPLE,
            PURPLE, GREEN
    );

    public static PlayerColor getOpponent(PlayerColor color) {
        return OPPONENT_MAP.getOrDefault(color, NONE);
    }
}
