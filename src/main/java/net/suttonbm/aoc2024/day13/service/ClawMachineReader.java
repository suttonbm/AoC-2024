package net.suttonbm.aoc2024.day13.service;

import net.suttonbm.aoc2024.day13.model.ClawMachine;
import net.suttonbm.aoc2024.day13.model.Operation;
import net.suttonbm.aoc2024.day13.model.Prize;
import net.suttonbm.aoc2024.utils.ResourceReaderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ClawMachineReader {
    public List<ClawMachine> parse(String inputFile) {
        List<String> lines = new ArrayList<>(ResourceReaderService.get(inputFile));
        List<ClawMachine> clawMachines = new ArrayList<>();

        int id = 0;
        ClawMachine currentMachine = new ClawMachine(id);
        for (String line : lines) {
            if (line.startsWith("Button A")) {
                currentMachine.setA(parseButton(line));
            } else if (line.startsWith("Button B")) {
                currentMachine.setB(parseButton(line));
            } else if (line.startsWith("Prize")) {
                currentMachine.setPrize(parsePrize(line));
                clawMachines.add(currentMachine);
            } else if (line.isEmpty()) {
                id++;
                currentMachine = new ClawMachine(id);
            }
        }

        return clawMachines;
    }

    private Operation parseButton(String line) {
        Pattern pattern = Pattern.compile("\\b\\d+\\b");
        Matcher matcher = pattern.matcher(line);

        try {
            matcher.find();
            int x = Integer.parseInt(matcher.group());
            matcher.find();
            int y = Integer.parseInt(matcher.group());
            return new Operation(x, y);
        } catch (Exception e) {
            System.out.println("Error parsing line: " + line);
            return null;
        }
    }

    private Prize parsePrize(String line) {
        Pattern pattern = Pattern.compile("\\b\\d+\\b");
        Matcher matcher = pattern.matcher(line);

        try {
            matcher.find();
            long x = Long.parseLong(matcher.group());
            matcher.find();
            long y = Long.parseLong(matcher.group());
            return new Prize(x, y);
        } catch (Exception e) {
            System.out.println("Error parsing line: " + line);
            return null;
        }
    }
}
