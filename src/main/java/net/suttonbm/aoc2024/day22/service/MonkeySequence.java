package net.suttonbm.aoc2024.day22.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MonkeySequence {
    private record Sequence(long a, long b, long c, long d) {

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Sequence sequence = (Sequence) o;
            return a == sequence.a && b == sequence.b && c == sequence.c && d == sequence.d;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c, d);
        }
    }

    private Map<Sequence, List<Long>> tradeResults;

    public long solve(List<List<Long>> values) {
        tradeResults = new HashMap<>();

        for (List<Long> buyer : values) {
            addTrades(buyer);
        }

        long result = 0;
        for (List<Long> sales : tradeResults.values()) {
            long test = sales.stream().mapToLong(i -> i).sum();
            if (test > result) {
                result = test;
            }
        }

        return result;
    }

    public void addTrades(List<Long> buyer) {
        Map<Sequence, Long> buyerTrades = new HashMap<>();

        for (int i = 4; i < buyer.size(); i++) {
            long a = buyer.get(i-3) % 10 - buyer.get(i-4) % 10;
            long b = buyer.get(i-2) % 10 - buyer.get(i-3) % 10;
            long c = buyer.get(i-1) % 10 - buyer.get(i-2) % 10;
            long d = buyer.get(i) % 10 - buyer.get(i-1) % 10;
            long val = buyer.get(i) % 10;
            Sequence seq = new Sequence(a, b, c, d);

            if (!buyerTrades.containsKey(seq)) {
                buyerTrades.put(seq, val);
            }
        }

        merge(buyerTrades);
    }

    private void merge(Map<Sequence, Long> buyerTrades) {
        for (Sequence seq : buyerTrades.keySet()) {
            if (tradeResults.containsKey(seq)) {
                tradeResults.get(seq).add(buyerTrades.get(seq));
            } else {
                List<Long> l = new ArrayList<>();
                l.add(buyerTrades.get(seq));
                tradeResults.put(seq, l);
            }
        }
    }
}