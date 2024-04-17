package boxes;

import java.math.BigDecimal;

public class Box {
    private BigDecimal width;
    private BigDecimal height;
    private BigDecimal depth;

    public Box(BigDecimal width, BigDecimal height, BigDecimal depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    /**
     * to see the values
     * toString() се включват всички данни, които са свързани с одтделният обект
     */
    @Override
    public String toString() {
        return "Box{" +
                "width=" + width +
                ", height=" + height +
                ", depth=" + depth +
                '}';
    }
}
