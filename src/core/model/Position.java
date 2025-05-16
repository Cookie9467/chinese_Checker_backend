// Position.java
package core.model;

import java.util.*;

public class Position {
    private int q; // axial coordinate
    private int r;

    public Position(int q, int r) {
        this.q = q;
        this.r = r;
    }

    public int getQ() { return q; }
    public int getR() { return r; }

    public boolean equals(Object o) {
        if (!(o instanceof Position)) return false;
        Position other = (Position) o;
        return this.q == other.q && this.r == other.r;
    }

    public int hashCode() {
        return Objects.hash(q, r);
    }

    public List<Position> getAdjacent() {
        return Arrays.asList(
                new Position(q + 1, r), new Position(q - 1, r),
                new Position(q, r - 1), new Position(q, r + 1),
                new Position(q - 1, r + 1), new Position(q + 1, r - 1)
        );
    }
}