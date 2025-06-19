package core.model;

import java.util.Objects;

/*
Initialize : Position(q, r)



*/

public class Position {
    private final int q;
    private final int r;

    public Position(int q, int r) {
        this.q = q;
        this.r = r;
    }

    public int getQ() {
        return q;
    }

    public int getR() {
        return r;
    }

    // 透過公式推得 z
    public int getZ() {
        return -q - r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position that = (Position) o;
        return this.q == that.q && this.r == that.r;
    }

    @Override
    public int hashCode() {
        return Objects.hash(q, r); // 或 return 31 * q + r;
    }

    @Override
    public String toString() {
        return "(" + q + "," + r + "," + ")";
    }
}
