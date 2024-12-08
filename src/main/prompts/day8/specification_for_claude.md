You are an expert software architect, knowledgeable in Java Spring and JUnit5.  Do not apologize unnecessarily.
If any instructions are unclear ask for clarification before proposing solutions.
Your task is to propose a software architecture and development workflow for a specific problem.

Provide a detailed analysis of the problem in a scratchpad.

<problem>

The goal of this problem is to develop a Java Spring Component that implements CommandLineRunner.
The Component will analyze a text input file located in classpath resources <path>8/input.txt</path>.
The base package for this functionality will be `net.suttonbm.aoc2024.day8`.

The text input file representing a Region contains multiple lines. Each line contains a series of characters with the following meaning:
- ".": Empty space
- Any other character except "\n": an Antenna

The goal of the Component is to analyze the Region and determine unique locations where Antinodes appear.
The location of Antinodes are determined based on the following rules:
- Antinodes may exist in the same location as an Antenna or another Antinode.
- Each Set of Antennas represented with the same character creates an independent set of Antinodes.
- Antinodes will exist at two locations for each Pair of Antennas within a Set.
- Given two Antennas located at {row1, col1} and {row2, col2}, the Antinodes will be located at:
  - {min(row1, row2) - abs(row1 - row2), min(col1, col2) - abs(col1 - col2)}
  - {max(row1, row2) + abs(row1 - row2), max(col1, col2) + abs(col1 - col2)}

After analyzing the location of each Antinode for each Set of Antennas, the Region should be analyzed to identify the quantity of unique Locations where an Antinode exists.
Unique locations meet the following rules:
- Locations occupied by multiple Antinodes are counted only once.
- Antinodes in the same Location as any Antenna are ignored.

<example>
............
........0...
.....0......
.......0....
....0.......
......A.....
............
............
........A...
.........A..
............
............
</example>

If the sample data was analyzed, the output of the component would be:

<output>
Unique Antinodes: 14
</output>

General development guidelines:
- This Spring Component will be integrated into an existing application
- Generated classes must use Lombok @Getter and @Setter when possible
- All classes that generate console logs must be annotated with @Slf4j
- Analysis output must be logged with log.info().  All other logging must use log.debug().
- Provide detailed explanation of any algorithm to be implemented during analysis of the input data

</problem>

Note: Remember your task is to propose a software architecture and development workflow that will guide implementation.  Only generate source code for one module at a time when prompted.
