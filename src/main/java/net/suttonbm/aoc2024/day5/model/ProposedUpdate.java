package net.suttonbm.aoc2024.day5.model;

import lombok.Getter;

import java.util.List;

@Getter
public class ProposedUpdate {
    private final List<Integer> numbers;

    public ProposedUpdate(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        ProposedUpdate that = (ProposedUpdate) o;
        return numbers.equals(that.numbers);
    }

    @Override
    public int hashCode() {
        return numbers.hashCode();
    }

    @Override
    public String toString() {
        return "ProposedUpdate{" +
                "numbers=" + numbers +
                '}';
    }
}