package net.suttonbm.aoc2024.day2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ReportParserServiceTest {

    @Autowired
    private ReportParserService reportParserService;

    @Test
    void testParseReports() throws IOException {
        String resourcePath = "classpath:/2/testInput.txt";
        List<List<Integer>> reports = reportParserService.parseReports(resourcePath);

        assertEquals(6, reports.size());
        assertEquals(Arrays.asList(7, 6, 4, 2, 1), reports.get(0));
        assertEquals(Arrays.asList(9, 7, 6, 2, 1), reports.get(2));
        assertEquals(Arrays.asList(1, 3, 6, 7, 9), reports.get(5));
    }
}