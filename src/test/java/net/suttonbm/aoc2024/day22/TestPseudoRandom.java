package net.suttonbm.aoc2024.day22;

import net.suttonbm.aoc2024.day22.service.PseudoRandom;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestPseudoRandom {

    private final PseudoRandom pr = new PseudoRandom();

    @Test
    public void testSingleIteration() {
        int initial = 123;

        long result = pr.get(initial);
        assertThat(result).isEqualTo(15887950L);
        result = pr.get(result);
        assertThat(result).isEqualTo(16495136L);
        result = pr.get(result);
        assertThat(result).isEqualTo(527345L);
        result = pr.get(result);
        assertThat(result).isEqualTo(704524L);
        result = pr.get(result);
        assertThat(result).isEqualTo(1553684L);
        result = pr.get(result);
        assertThat(result).isEqualTo(12683156L);
        result = pr.get(result);
        assertThat(result).isEqualTo(11100544L);
        result = pr.get(result);
        assertThat(result).isEqualTo(12249484L);
        result = pr.get(result);
        assertThat(result).isEqualTo(7753432L);
        result = pr.get(result);
        assertThat(result).isEqualTo(5908254L);
    }
}