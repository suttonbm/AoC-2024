package net.suttonbm.aoc2024.day2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SafetyCheckServiceTest {

    @Autowired
    private SafetyCheckService safetyCheckService;

    @Test
    void testIsSafeReport() {
        assertTrue(safetyCheckService.isSafeReport(Arrays.asList(1, 2, 3, 4)));
        assertFalse(safetyCheckService.isSafeReport(Arrays.asList(1, 3, 7, 10)));
        assertTrue(safetyCheckService.isSafeReport(Arrays.asList(5, 4, 3, 2)));
    }

    @Test
    void testIsSingleRemovalSafe() {
        // Test report that becomes safe after single removal
        SingleRemovalResult result1 = safetyCheckService.isSingleRemovalSafe(Arrays.asList(1, 3, 2, 4));
        assertTrue(result1.isSafe());
        assertEquals(1, result1.getRemovedIndex());
        assertEquals(Arrays.asList(1, 2, 4), result1.getSafeReport());

        // Test report that remains unsafe after single removal
        SingleRemovalResult result2 = safetyCheckService.isSingleRemovalSafe(Arrays.asList(1, 3, 7, 10));
        assertFalse(result2.isSafe());
        assertEquals(-1, result2.getRemovedIndex());
        assertNull(result2.getSafeReport());
    }
}