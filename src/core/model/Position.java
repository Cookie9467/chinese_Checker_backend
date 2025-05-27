package core.model;

// Position.java
// 功能：表示棋盤位置（使用 axial coordinate），提供鄰居格子與座標判等
// 狀態：已實作
import java.util.*;

public class Position {
    private int q; // axial coordinate q
    private int r; // axial coordinate r

    public Position(int q, int r) {
        this.q = q;
        this.r = r;
    }

    public int getQ() { return q; } // 取得 q
    public int getR() { return r; } // 取得 r

    @Override
    public boolean equals(Object o) { // 比對座標是否相同
        if (!(o instanceof Position)) return false;
        Position other = (Position) o;
        return this.q == other.q && this.r == other.r;
    }

    @Override
    public int hashCode() { // 用於放入 Map 中做比對
        return Objects.hash(q, r);
    }

    public List<Position> getAdjacent() { // 回傳六個相鄰位置
        return Arrays.asList(
                new Position(q + 1, r), new Position(q - 1, r),
                new Position(q, r - 1), new Position(q, r + 1),
                new Position(q - 1, r + 1), new Position(q + 1, r - 1)
        );
    }
}