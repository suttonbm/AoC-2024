package net.suttonbm.aoc2024.day3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class CorruptedMemoryProcessorTest {
    private static final Logger logger = LoggerFactory.getLogger(CorruptedMemoryProcessorTest.class);

    private CorruptedMemoryProcessor corruptedMemoryProcessor;

    @BeforeEach
    void setUp() {
        corruptedMemoryProcessor = new CorruptedMemoryProcessor();
    }

    @Test
    void scanCorruptedMemory_ValidInstructions_CalculatesCorrectSum() {
        // Arrange
        String corruptedMemory = "mul(23,45) invalid_mul mul(10,20) mul(5,?";

        // Act
        int sum = corruptedMemoryProcessor.scanCorruptedMemory(corruptedMemory);

        // Assert
        Assertions.assertEquals(1235, sum);
    }

    @Test
    void scanCorruptedMemory_InvalidInstructions_CalculatesCorrectSum() {
        // Arrange
        String corruptedMemory = "invalid_mul mul(10,20) mul(5,?) mul(23,45)";

        // Act
        int sum = corruptedMemoryProcessor.scanCorruptedMemory(corruptedMemory);

        // Assert
        Assertions.assertEquals(1235, sum);
    }

    @Test
    void scanCorruptedMemory_EmptyInput_ReturnsZero() {
        // Act
        int sum = corruptedMemoryProcessor.scanCorruptedMemory("");

        // Assert
        Assertions.assertEquals(0, sum);
    }
}