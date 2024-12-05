Let's add additional functionality to the project.  The additional functionality will analyze the collection of invalid ProposedUpdates.

Analysis of invalid ProposedUpdate will follow these steps:
- For each ProposedUpdate, determine if elements can be reordered to satisfy all Constraints.
- Build a collection of corrected ProposedUpdates.
- Extract the middle element after correcting each Proposed Update.
- Return the sum of the middle elements.

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

After analysis, the new output of the Component will be:

<output>
Valid ProposedUpdates:
75,47,61,53,29
97,61,53,29,13
75,29,13

Sum of middle elements:
143

Corrected ProposedUpdates:
97,75,47,61,53
61,29,13
97,75,47,29,13

Sum of corrected middle elements:
123
</output>
