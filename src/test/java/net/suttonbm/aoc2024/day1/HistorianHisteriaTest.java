package net.suttonbm.aoc2024.day1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HistorianHisteriaTest {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    HistorianHisteria scriptRunner;

    Resource input;

    @BeforeEach
    void setUp() {
        input = resourceLoader.getResource("classpath:/1/testInput.txt");
    }

    @Test
    void testRunDistanceAlgorithm() throws IOException {
        // given
        File file = input.getFile();
        List<List<Integer>> testData = scriptRunner.getDataFrom(file);

        // when
        int result = scriptRunner.runDistanceAlgo(testData);

        // then
        assertThat(result).isEqualTo(11);
    }

    @Test
    void testRunSimilarityAlgorithm() throws IOException {
        // given
        File file = input.getFile();
        List<List<Integer>> testData = scriptRunner.getDataFrom(file);

        // when
        long result = scriptRunner.runSimilarityAlgo(testData);

        // then
        assertThat(result).isEqualTo(31L);
    }

    @Test
    void testLoadData() throws IOException {
        // given
        File file = input.getFile();

        // when
        List<List<Integer>> testData = scriptRunner.getDataFrom(file);

        //then
        assertThat(testData.size()).isEqualTo(2);

        List<Integer> list1 = testData.get(0);
        List<Integer> list2 = testData.get(1);

        assertThat(list1.size()).isEqualTo(6);
        assertThat(list2.size()).isEqualTo(6);

        assertThat(list1).isEqualTo(List.of(3,4,2,1,3,3));
        assertThat(list2).isEqualTo(List.of(4,3,5,3,9,3));
    }
}