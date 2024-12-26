package net.suttonbm.aoc2024.day25.model;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class KeyData {
    private List<Key> keys;
    private List<Keyhole> keyHoles;

    public int numKeys() {
        return keys.size();
    }

    public int numKeyHoles() {
        return keyHoles.size();
    }

    public long possibleMatches() {
        long result = 0;
        for (Key key : keys) {
            for (Keyhole keyhole : keyHoles) {
                if (keyhole.fits(key)) {
                    result++;
                }
            }
        }
        return result;
    }
}