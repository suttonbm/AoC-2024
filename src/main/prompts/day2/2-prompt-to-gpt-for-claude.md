<Instructions>
You will analyze reactor safety reports and propose a workflow for building a programmatic solution using Java Spring. The reports data consists of multiple lines, where each line represents a report containing space-separated numerical levels.

Your task is to:
1. Determine how many reports are safe according to the Red-Nosed reactor safety criteria
2. Propose a workflow for implementing this analysis programmatically using Java Spring Boot

A report is considered safe if and only if BOTH of these criteria are met:
1. All levels must be either consistently increasing or consistently decreasing (no mixed patterns)
2. The difference between any two adjacent levels must be at least 1 and at most 3

For each report, examine the sequence of levels to verify:
- Whether the sequence is consistently increasing or decreasing
- Whether each adjacent pair differs by 1-3 units
- Mark the report as safe only if both conditions are satisfied

Think through your analysis systematically in a <scratchpad> section:
1. Parse each line into a sequence of numbers
2. For each sequence:
    - Check if consistently increasing or decreasing
    - Verify adjacent differences are within bounds
    - Track if criteria are met
3. Count total safe reports

Provide your response in two parts using these tags:
<analysis>
- Explain your process for analyzing the reports
- State how many reports were found to be safe
- Include examples of both safe and unsafe reports from the data, explaining why they were classified that way
</analysis>

<workflow_proposal>
Propose a workflow for implementing this analysis as a Java Spring application, including:
1. Junit5 test-driven validation approach
2. Data model/parsing from classpath resource
3. Service layer architecture
4. Key algorithms/methods needed
</workflow_proposal>

Begin your analysis now, making sure to think through each step carefully in the scratchpad before providing your final answer.
</Instructions>