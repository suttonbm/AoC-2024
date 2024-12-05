package net.suttonbm.aoc2024.day5;

import net.suttonbm.aoc2024.day5.model.Constraint;
import net.suttonbm.aoc2024.day5.model.ProposedUpdate;
import net.suttonbm.aoc2024.day5.service.ValidationService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationServiceTest {

    private final ValidationService validationService = new ValidationService();

    @Test
    void testValidProposedUpdate() {
        List<Constraint> constraints = Arrays.asList(
                new Constraint(47, 53),
                new Constraint(29, 97)
        );

        ProposedUpdate validUpdate = new ProposedUpdate(Arrays.asList(75, 47, 61, 53, 29));
        ProposedUpdate invalidUpdate = new ProposedUpdate(Arrays.asList(97, 61, 53, 29, 13));

        List<ProposedUpdate> proposedUpdates = Arrays.asList(validUpdate, invalidUpdate);

        List<ProposedUpdate> validUpdates = validationService.validateProposedUpdates(constraints, proposedUpdates);

        assertEquals(1, validUpdates.size());
        assertTrue(validUpdates.contains(validUpdate));
    }

    @Test
    void testCalculateSumOfMiddleElements() {
        List<ProposedUpdate> validUpdates = Arrays.asList(
                new ProposedUpdate(Arrays.asList(75, 47, 61, 53, 29)),
                new ProposedUpdate(Arrays.asList(97, 61, 53, 29, 13)),
                new ProposedUpdate(Arrays.asList(75, 29, 13))
        );

        int sum = validationService.calculateSumOfMiddleElements(validUpdates);

        assertEquals(143, sum);
    }

    @Test
    void testCorrectInvalidUpdates() {
        // Define constraints
        List<Constraint> constraints = Arrays.asList(
                new Constraint(47, 53),  // First constraint
                new Constraint(97, 13),  // Second constraint
                new Constraint(97, 61),
                new Constraint(97, 47),
                new Constraint(75, 29),
                new Constraint(61, 13),
                new Constraint(75, 53),
                new Constraint(29, 13),
                new Constraint(97, 29),
                new Constraint(53, 29),
                new Constraint(61, 53),
                new Constraint(97, 53),
                new Constraint(61, 29),
                new Constraint(47, 13),
                new Constraint(75, 47),
                new Constraint(97, 75),
                new Constraint(47, 61),
                new Constraint(75, 61),
                new Constraint(47, 29),
                new Constraint(75, 13),
                new Constraint(53, 13)
        );

        // Define invalid proposed updates
        List<ProposedUpdate> proposedUpdates = List.of(new ProposedUpdate(Arrays.asList(75, 47, 61, 53, 29)),
                                                        new ProposedUpdate(Arrays.asList(97, 61, 53, 29, 13)),
                                                        new ProposedUpdate(Arrays.asList(75, 29, 13)),
                                                        new ProposedUpdate(Arrays.asList(75, 97, 47, 61, 53)),
                                                        new ProposedUpdate(Arrays.asList(61, 13, 29)),
                                                        new ProposedUpdate(Arrays.asList(97, 13, 75, 29, 47))
                                                        );

        List<ProposedUpdate> validUpdates = validationService.validateProposedUpdates(constraints, proposedUpdates);
        System.out.println(Arrays.toString(validUpdates.toArray()));

        assertEquals(3, validUpdates.size());
        assertTrue(validUpdates.contains(new ProposedUpdate(Arrays.asList(75, 47, 61, 53, 29))));
        assertTrue(validUpdates.contains(new ProposedUpdate(Arrays.asList(97, 61, 53, 29, 13))));
        assertTrue(validUpdates.contains(new ProposedUpdate(Arrays.asList(75, 29, 13))));

        assertEquals(143, validationService.calculateSumOfMiddleElements(validUpdates));

        List<ProposedUpdate> invalidUpdates = new ArrayList<>(proposedUpdates);
        invalidUpdates.removeAll(validUpdates);

        // Correct the invalid updates
        List<ProposedUpdate> correctedUpdates = validationService.correctInvalidUpdates(constraints, invalidUpdates);
        System.out.println(Arrays.toString(correctedUpdates.toArray()));

        // Check if the corrected updates are correct
        assertEquals(3, correctedUpdates.size());
        assertTrue(correctedUpdates.contains(new ProposedUpdate(Arrays.asList(97, 75, 47, 61, 53))));
        assertTrue(correctedUpdates.contains(new ProposedUpdate(Arrays.asList(61, 29, 13))));
        assertTrue(correctedUpdates.contains(new ProposedUpdate(Arrays.asList(97, 75, 47, 29, 13))));

        assertEquals(123, validationService.calculateSumOfMiddleElements(correctedUpdates));
    }
}