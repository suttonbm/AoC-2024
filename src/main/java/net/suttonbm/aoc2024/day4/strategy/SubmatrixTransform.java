package net.suttonbm.aoc2024.day4.strategy;

public enum SubmatrixTransform {
    ONE {
        @Override
        public char[][] getPattern(char[][] pattern) {
            return pattern;
        }
    },
    TWO {
        @Override
        public char[][] getPattern(char[][] pattern) {
            char[][] newPattern = new char[pattern.length][pattern[0].length];
            int rows = pattern.length;
            int cols = pattern[0].length;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    newPattern[i][j] = pattern[rows - i - 1][cols - j - 1];
                }
            }

            return newPattern;
        }
    },
    THREE {
        @Override
        public char[][] getPattern(char[][] pattern) {
            char[][] newPattern = new char[pattern[0].length][pattern.length];
            int rows = pattern.length;
            int cols = pattern[0].length;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    newPattern[j][i] = pattern[i][j];
                }
            }

            return newPattern;
        }
    },
    FOUR {
        @Override
        public char[][] getPattern(char[][] pattern) {
            char[][] newPattern = new char[pattern[0].length][pattern.length];
            int rows = pattern.length;
            int cols = pattern[0].length;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    newPattern[j][i] = pattern[rows - i - 1][cols - j - 1];
                }
            }

            return newPattern;
        }
    };

    public abstract char[][] getPattern(char[][] pattern);
}
