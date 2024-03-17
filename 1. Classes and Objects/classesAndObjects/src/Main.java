import exersices.Box;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Box box1 = new Box(BigDecimal.valueOf(10), BigDecimal.valueOf(20), BigDecimal.valueOf(30));
        System.out.println(box1);
    }
}