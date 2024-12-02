<language>Java</language>

<framework>Spring Boot</framework>

<requirements>
- Implement a Java Spring Boot application to analyze reactor safety reports.
- Parse a list of reports from a text file stored in the `src/main/resources/2` directory.
- Check each report against the following safety criteria:
  - All levels in the report must be either consistently increasing or consistently decreasing.
  - The difference between adjacent levels must be at least 1 and at most 3.
- Determine and count how many reports are considered safe.
- Use a service-oriented architecture to separate parsing and safety validation logic.
- Write JUnit5 tests to validate the parsing and safety-check functionalities.
- Optimize for scalability and maintainability.
</requirements>

<code_example>
<!-- Include the already implemented code for parsing reports -->
@Service
public class ReportParserService {

    private final ResourceLoader resourceLoader;

    public ReportParserService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public List<List<Integer>> parseReports(String resourcePath) throws IOException {
        List<List<Integer>> reports = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resourceLoader.getResource(resourcePath).getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<Integer> reportLevels = parseLineToNumbers(line);
                reports.add(reportLevels);
            }
        }
        return reports;
    }

    private List<Integer> parseLineToNumbers(String line) {
        List<Integer> levels = new ArrayList<>();
        String[] tokens = line.trim().split("\\s+");
        for (String token : tokens) {
            levels.add(Integer.parseInt(token));
        }
        return levels;
    }
}
</code_example>

<input>
- A text file `input.txt` located in the `src/main/resources/2` directory containing:
  ```
  1 2 3 4
  1 3 7 10
  5 4 3 2
  ```
</input>

<output>
- Total safe reports: `2`
- Example safe report: `[1, 2, 3, 4]`
- Example unsafe report: `[1, 3, 7, 10]`
</output>

<constraints>
- Code should adhere to Spring Boot conventions.
- Ensure test-driven development with JUnit5.
- Handle edge cases, such as empty lines or non-numeric input, gracefully.
</constraints>

<objective>
Complete the implementation of the workflow, including:
1. A `SafetyCheckService` to validate the safety of each report.
2. A method in the main application class to parse the reports, validate them, and output the total number of safe reports.
3. JUnit5 tests for both `ReportParserService` and `SafetyCheckService`.
4. Any necessary classes or methods to fulfill the requirements.
</objective> 
