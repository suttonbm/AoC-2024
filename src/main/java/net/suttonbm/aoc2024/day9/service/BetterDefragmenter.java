package net.suttonbm.aoc2024.day9.service;

import net.suttonbm.aoc2024.day9.model.File;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class BetterDefragmenter {
    public List<File> run(List<File> disk) {
        List<File> tempDisk = new ArrayList<>(disk);

        int i = tempDisk.size() - 1;
        while (i >= 0) {
            int candidateSize = tempDisk.get(i).getSize();
            Integer candidateId = tempDisk.get(i).getId();

            if (candidateId == null) {
                i -= candidateSize;
                continue;
            }

            int j = 0;
            while (j < i) {
                int firstFileSize = tempDisk.get(j).getSize();
                Integer firstFileId = tempDisk.get(j).getId();

                if (firstFileId != null || firstFileSize < candidateSize) {
                    // Skip to next file (or empty space) from front
                    j += firstFileSize;
                    continue;
                }

                // Move candidate
                for (int k = 0; k < firstFileSize; k++) {
                    if (k < candidateSize) {
                        // Up to candidate size
                        tempDisk.set(j+k, tempDisk.get(i-k));
                        tempDisk.set(i-k, new File(null, candidateSize));
                    } else {
                        tempDisk.set(j+k, new File(null, firstFileSize - candidateSize));
                    }
                }

                break;
            }

            // Move to next file from end
            i -= candidateSize;
        }

        return tempDisk;
    }
}
