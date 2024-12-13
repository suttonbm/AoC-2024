package net.suttonbm.aoc2024.day13.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ClawMachine {
    private int id;
    private Operation a;
    private Operation b;
    private Prize prize;

    public ClawMachine(int id) {
        this.id = id;
    }

    public long prizeCost() {
        double bPresses = getPressesB();
        if (!closeEnough(bPresses)
                || bPresses < 0) {
            return 0;
        }
        bPresses = Math.round(bPresses);
        double aPresses = (prize.x - b.getX() * bPresses) / a.getX();
        if (!closeEnough(aPresses)
                || aPresses < 0) {
            return 0;
        }

        return (long) bPresses + (long) aPresses * 3;
    }

    private double getPressesB() {
        double c1 = prize.x - a.getX() * (double) prize.y / a.getY();
        double c2 = b.getX() - a.getX() * (double) b.getY() / a.getY();
        return c1 / c2;
    }

    private boolean closeEnough(double x) {
        double test = x % 1;
        return x % 1 < 0.001 || x % 1 > 0.999;
    }
}
