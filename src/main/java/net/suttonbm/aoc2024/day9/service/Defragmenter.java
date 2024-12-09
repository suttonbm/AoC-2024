package net.suttonbm.aoc2024.day9.service;

import net.suttonbm.aoc2024.day9.model.File;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class Defragmenter {
    public List<File> run(List<File> disk) {
        LinkedList<File> tempDisk = new LinkedList<>(disk);
        List<File> result = new ArrayList<>();
        while(tempDisk.size() > 0) {
            if (tempDisk.getLast().getId() == null) {
                // Make sure there's always a valid File at the end of the list
                tempDisk.pollLast();
                continue;
            }

            File candidate = tempDisk.pollFirst();
            if (candidate.getId() == null) {
                // Pull File from the end to the current position
                result.add(tempDisk.pollLast());
            } else {
                // Keep file at current position
                result.add(candidate);
            }
        }
        return result;
    }
}
