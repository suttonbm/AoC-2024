package net.suttonbm.aoc2024.day14.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@AllArgsConstructor
public class Robot {
    @Setter
    private Point position;
    private int velocityX;
    private int velocityY;
}
