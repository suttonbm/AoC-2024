package net.suttonbm.aoc2024.day9;

import net.suttonbm.aoc2024.day9.model.File;
import net.suttonbm.aoc2024.day9.service.DiskFileParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InputFileParserTest {
    final DiskFileParser parser = new DiskFileParser();

    @Test
    public void testParse() {
        List<File> result = parser.parseInputFile("9/test.txt");
        System.out.println(result);
    }
}
