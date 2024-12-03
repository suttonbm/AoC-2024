package net.suttonbm.aoc2024.day3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class CorruptedInstructionTest {

    @Test
    void testParseValidInstruction() {
        String validInstruction = "mul(123,456)";
        CorruptedInstruction instruction = new CorruptedInstruction(validInstruction);

        Assertions.assertTrue(instruction.isValid());
        Assertions.assertEquals(Arrays.asList(56088), instruction.getResults());
        Assertions.assertEquals(validInstruction, instruction.getOriginalInstruction());
    }

    @Test
    void testParseInvalidInstruction() {
        String invalidInstruction = "mul(123,abc)";
        CorruptedInstruction instruction = new CorruptedInstruction(invalidInstruction);

        Assertions.assertFalse(instruction.isValid());
        Assertions.assertEquals(Arrays.asList(), instruction.getResults());
        Assertions.assertEquals(invalidInstruction, instruction.getOriginalInstruction());
    }

    @Test
    void testParseMultipleInstructions() {
        String multipleInstructions = "mul(123,456)mul(789,101)mul(42,0)";
        CorruptedInstruction instruction = new CorruptedInstruction(multipleInstructions);

        Assertions.assertTrue(instruction.isValid());
        List<Integer> expectedResults = Arrays.asList(56088, 79689, 0);
        Assertions.assertEquals(expectedResults, instruction.getResults());
        Assertions.assertEquals(multipleInstructions, instruction.getOriginalInstruction());
    }

    @Test
    void testParseMultipleInstructionsBadString() {
        String multipleInstructions = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";
        CorruptedInstruction instruction = new CorruptedInstruction(multipleInstructions);

        Assertions.assertTrue(instruction.isValid());
        List<Integer> expectedResults = Arrays.asList(8, 25, 88, 40);
        Assertions.assertEquals(expectedResults, instruction.getResults());
        Assertions.assertEquals(multipleInstructions, instruction.getOriginalInstruction());
    }
}