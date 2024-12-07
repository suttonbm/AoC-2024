package net.suttonbm.aoc2024.day7.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Equation {
    private final long total;
    private final List<Long> values;
}