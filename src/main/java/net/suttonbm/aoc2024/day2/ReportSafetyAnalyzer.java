package net.suttonbm.aoc2024.day2;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ReportSafetyAnalyzer implements CommandLineRunner {

    private final ReportParserService reportParserService;
    private final SafetyCheckService safetyCheckService;

    public ReportSafetyAnalyzer(ReportParserService reportParserService, SafetyCheckService safetyCheckService) {
        this.reportParserService = reportParserService;
        this.safetyCheckService = safetyCheckService;
    }

    @Override
    public void run(String... args) throws Exception {
        String resourcePath = "classpath:/2/input.txt";
        List<List<Integer>> reports = reportParserService.parseReports(resourcePath);

        int safeReportCount = 0;
        int safeModifiedReportCount = 0;
        List<List<Integer>> safeReports = new ArrayList<>();
        List<ModifiedReport> modifiedReports = new ArrayList<>();

        for (List<Integer> report : reports) {
            if (safetyCheckService.isSafeReport(report)) {
                safeReportCount++;
                safeReports.add(report);
            } else {
                SingleRemovalResult singleRemovalResult = safetyCheckService.isSingleRemovalSafe(report);
                if (singleRemovalResult.isSafe()) {
                    safeModifiedReportCount++;
                    modifiedReports.add(new ModifiedReport(report, singleRemovalResult.getRemovedIndex(), singleRemovalResult.getSafeReport()));
                } else {
                    modifiedReports.add(new ModifiedReport(report, -1, null));
                }
            }
        }

        log.info("Total safe reports: {}", safeReportCount);
        log.info("Total safe reports after single removal: {}", safeModifiedReportCount);
    }
}