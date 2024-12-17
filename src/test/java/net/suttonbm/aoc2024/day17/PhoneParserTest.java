package net.suttonbm.aoc2024.day17;

import net.suttonbm.aoc2024.day17.model.Phone;
import net.suttonbm.aoc2024.day17.service.PhoneParserService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PhoneParserTest {

    private final PhoneParserService parser = new PhoneParserService();

    @Test
    public void parsePhone() {
        List<String> input = List.of("Register A: 0", "Register B: 1161", "Register C: 1837867");
        Phone phone = parser.initializePhone(input);

        assertThat(phone).isNotNull();
        assertThat(phone).isInstanceOf(Phone.class);
        assertThat(phone.getRegA()).isEqualTo(0);
        assertThat(phone.getRegB()).isEqualTo(1161);
        assertThat(phone.getRegC()).isEqualTo(1837867);
    }

    @Test
    public void parseProgram() {
        List<String> input = List.of("","","","","Program: 1,5,3,6,7,4,2,4,1");
        List<Integer> program = parser.initializeProgram(input);

        assertThat(program).isNotNull();
        assertThat(program).hasSize(9);
    }

    @Test
    public void parseFile() {
        String input = "17/test.txt";
        Phone phone = parser.initialize(input);
        assertThat(phone).isNotNull();
        assertThat(phone).isInstanceOf(Phone.class);
        assertThat(phone.getRegA()).isEqualTo(729);
        assertThat(phone.getRegB()).isEqualTo(0);
        assertThat(phone.getRegC()).isEqualTo(0);
        assertThat(phone.getProgram()).isNotNull();
        assertThat(phone.getProgram()).hasSize(6);
    }
}
