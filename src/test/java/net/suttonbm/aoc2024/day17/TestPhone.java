package net.suttonbm.aoc2024.day17;

import net.suttonbm.aoc2024.day17.model.Phone;
import net.suttonbm.aoc2024.day17.service.PhoneFixerService;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestPhone {

    final PhoneFixerService fixer = new PhoneFixerService();

    @Test
    void test1() {
        Phone phone = new Phone(0, 0, 9);
        phone.setProgram(List.of(2, 6));
        phone.run();

        assertThat(phone.getRegB()).isEqualTo(1);
    }

    @Test
    void test2() {
        Phone phone = new Phone(10, 0, 0);
        phone.setProgram(List.of(5,0,5,1,5,4));
        List<Integer> out = phone.run();

        assertThat(out).isNotEmpty();
        assertThat(out).isEqualTo(List.of(0,1,2));
    }

    @Test
    void test3() {
        Phone phone = new Phone(2024, 0, 0);
        phone.setProgram(List.of(0,1,5,4,3,0));
        List<Integer> out = phone.run();

        assertThat(phone.getRegA()).isEqualTo(0);
        assertThat(out).isNotEmpty();
        assertThat(out).isEqualTo(List.of(4,2,5,6,7,7,7,7,3,1,0));
    }

    @Test
    void test4() {
        Phone phone = new Phone(0, 29, 0);
        phone.setProgram(List.of(1, 7));
        phone.run();

        assertThat(phone.getRegB()).isEqualTo(26);
    }

    @Test
    void test5() {
        Phone phone = new Phone(0, 2024, 43690);
        phone.setProgram(List.of(4, 0));
        phone.run();

        assertThat(phone.getRegB()).isEqualTo(44354);
    }

    @Test
    void test6() {
        Phone phone = new Phone(729, 0, 0);
        phone.setProgram(List.of(0, 1, 5, 4, 3, 0));
        List<Integer> out = phone.run();

        assertThat(out).isNotEmpty();
        assertThat(out).isEqualTo(List.of(4, 6, 3, 5, 6, 3, 5, 2, 1, 0));
    }

    @Test
    void test7() {
        Phone phone = new Phone(45483412, 0, 0);
        phone.setProgram(List.of(2,4,1,3,7,5,0,3,4,1,1,5,5,5,3,0));
        List<Integer> out = phone.run();

        assertThat(out).isNotEmpty();
        assertThat(out).isEqualTo(List.of(1,5,0,5,2,0,1,3,5));
    }

    @Test
    void test8() {
        Phone phone = new Phone(0, 0, 0);
        phone.setProgram(List.of(2, 4, 1, 3, 7, 5, 0, 3, 4, 1, 1, 5, 5, 5, 3, 0));
        fixer.fixPhone(phone);
    }

    @Test
    void test10() {
        Phone phone = new Phone(2024, 0, 0);
        phone.setProgram(List.of(0, 3, 5, 4, 3, 0));
        System.out.println("Target: " + phone.getProgram());
        assertThat(fixer.fixPhone(phone)).isEqualTo(117440);
    }

    @Test
    void test9() {
        Phone phone = new Phone(177166631824499L, 0, 0);
        phone.setProgram(List.of(2, 4, 1, 3, 7, 5, 0, 3, 4, 1, 1, 5, 5, 5, 3, 0));
        System.out.println(phone.run());
    }
}
