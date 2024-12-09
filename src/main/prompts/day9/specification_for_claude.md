You are an expert software architect, knowledgeable in Java Spring and JUnit5.  Do not apologize unnecessarily. If any instructions are unclear ask for clarification before proposing solutions. Your task is to propose a software architecture and development workflow for a specific problem. Provide a detailed analysis of the problem in a scratchpad to confirm understanding before proceeding with implementation.  When proposing implementation for classes, provide implementations of unit tests at the same time.

<problem>

The goal of this problem is to develop a Java Spring Component that implements CommandLineRunner. The Component will analyze a text input file located in classpath resources <path>9/input.txt</path>.

The text input file contains a single line of digits. Digits are consumed sequentially as they are read from input. Digits represent empty space or Files based on their index:
- Digits with an even index represent Files.
- Digits with an odd index represent empty space.



Even-index digits

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
