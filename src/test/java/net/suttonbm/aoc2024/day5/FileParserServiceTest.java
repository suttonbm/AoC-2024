package net.suttonbm.aoc2024.day5;

import net.suttonbm.aoc2024.day5.model.Constraint;
import net.suttonbm.aoc2024.day5.model.ProposedUpdate;
import net.suttonbm.aoc2024.day5.service.FileParserService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FileParserServiceTest {

    private FileParserService fileParserService = new FileParserService();

    @Test
    void testParseFile() throws IOException {
        List<Object> parsedData = fileParserService.parseFile();
        List<Constraint> constraints = (List<Constraint>) parsedData.get(0);
        List<ProposedUpdate> proposedUpdates = (List<ProposedUpdate>) parsedData.get(1);

        assertNotNull(constraints);
        assertNotNull(proposedUpdates);
        assertFalse(constraints.isEmpty());
        assertFalse(proposedUpdates.isEmpty());
    }
}