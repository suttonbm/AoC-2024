package net.suttonbm.aoc2024.day17.service;

import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day17.model.Phone;
import net.suttonbm.aoc2024.utils.ResourceReaderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class PhoneParserService {
    public Phone initialize(String inputFile) {
        List<String> lines = ResourceReaderService.get(inputFile);
        Phone phone = initializePhone(lines);
        List<Integer> program = initializeProgram(lines);
        phone.setProgram(program);
        return phone;
    }

    public List<Integer> initializeProgram(List<String> lines) {
        List<Integer> instructions = new ArrayList<>();
        Pattern pattern = Pattern.compile(("\\d"));
        Matcher matcher = pattern.matcher(lines.get(4));
        while (matcher.find()) {
            instructions.add(Integer.parseInt(matcher.group()));
        }
        return instructions;
    }

    public Phone initializePhone(List<String> lines) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(lines.get(0));
        matcher.find();
        long a = Long.parseLong(matcher.group());
        matcher = pattern.matcher(lines.get(1));
        matcher.find();
        long b = Long.parseLong(matcher.group());
        matcher = pattern.matcher(lines.get(2));
        matcher.find();
        long c = Long.parseLong(matcher.group());

        Phone phone = new Phone(a, b, c);
        return phone;
    }
}
