package net.suttonbm.aoc2024.day9;

import net.suttonbm.aoc2024.day9.model.File;
import net.suttonbm.aoc2024.day9.service.Checksum;
import net.suttonbm.aoc2024.day9.service.Defragmenter;
import net.suttonbm.aoc2024.day9.service.DiskFileParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DefragmenterTest {
    final DiskFileParser inputFileParser = new DiskFileParser();
    final Defragmenter defragmenter = new Defragmenter();
    final Checksum checksum = new Checksum();

    @Test
    public void testRun() {
        List<File> input = inputFileParser.parseInputFile("9/test.txt");
        System.out.println(input);
        List<File> output = defragmenter.run(input);
        System.out.println(output);

        long cs = checksum.calculate(output);
        assertThat(cs).isEqualTo(1928);
    }
}
