You are an expert software architect, knowledgeable in Java Spring and JUnit5.  Do not apologize unnecessarily.  If any instructions are unclear ask for clarification before proposing solutions.  Your task is to propose a software architecture and development workflow for a specific problem.

Provide a detailed analysis of the problem in a scratchpad.

<problem>

The problem you are tasked with solving is to analyze sleigh launch safety manual updates.

The goal of this problem is to develop a Java Spring Component that implements CommandLineRunner to analyze a text input file located in classpath resources <path>5/input.txt</path>.

The text input file contains two sections delimited by a single blank line.

The first section consists of multiple lines, where each line consists of two numbers delimited by "|". Each pair of numbers represents a Constraint.

The second section consists of multiple lines, where each line consists of a list of numbers delimited by ",".  Each list of numbers represents a ProposedUpdate.

The program will analyze the ProposedUpdates in the following way:
1. Produce a collection of valid ProposedUpdates
2. Extract the middle list element from each valid ProposedUpdate
3. Return the sum of the elements extracted from ProposedUpdate

A ProposedUpdate is valid if all Constraints are satisfied.  A Constraint is satisfied when:
- Either number in a Constraint is not contained in a ProposedUpdate
- Both numbers in a Constraint are contained in a ProposedUpdate, and the index of the first number in the constraint is strictly less than the index of the second number in the constraint.

Review the following example data:

<example data>
47|53
97|13
97|61
97|47
75|29
61|13
75|53
29|13
97|29
53|29
61|53
97|53
61|29
47|13
75|47
97|75
47|61
75|61
47|29
75|13
53|13

75,47,61,53,29
97,61,53,29,13
75,29,13
75,97,47,61,53
61,13,29
97,13,75,29,47
</example data>

If the sample data was analyzed, the output of the component would be:

<output>
Valid ProposedUpdates:
75,47,61,53,29
97,61,53,29,13
75,29,13

Sum of middle elements:
143
</output>

General development guidelines:
- This Spring Component will be integrated into an existing application
- All console output should be logged using Slf4j
- Provide detailed explanation of any algorithm to be implemented during analysis of the input data

</problem>

Note: Remember your task is to propose a software architecture and development workflow that will guide implementation.  Only generate source code for one module at a time when prompted.
