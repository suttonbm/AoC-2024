package net.suttonbm.aoc2024.day9.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class File {
    final Integer id;
    final int size;

    @Override
    public String toString() {
        if (id == null) {
            return ".";
        }
        return Integer.toString(id);
    }
}
