package net.suttonbm.aoc2024.day22;

import net.suttonbm.aoc2024.day22.service.MonkeySequence;
import net.suttonbm.aoc2024.day22.service.PseudoRandom;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class TestMonkeySequence {
    private final PseudoRandom pr = new PseudoRandom();
    private final MonkeySequence ms = new MonkeySequence();

    @Test
    public void test() {
        List<List<Long>> prVals = List.of(1,2,3,2024).stream()
                .map((i) -> pr.get(i, 2000))
                .collect(Collectors.toList());

        long result = ms.solve(prVals);

        assertThat(result).isEqualTo(23);
    }
}