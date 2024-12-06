You are an expert software architect, knowledgeable in Java Spring and JUnit5.  Do not apologize unnecessarily.  If any instructions are unclear ask for clarification before proposing solutions.  Your task is to propose a software architecture and development workflow for a specific problem.

Provide a detailed analysis of the problem in a scratchpad.

<problem>

The problem you are tasked with solving is to analyze the patrol sequence of an elven guard.

The goal of this problem is to develop a Java Spring Component that implements CommandLineRunner to analyze a text input file located in classpath resources <path>6/input.txt</path>.

The text input file contains multiple lines of equal length and represents a Courtyard.  There are six (6) valid characters (".", "#", "^", "v", ">", "<").  Each character has the following conceptual definition:
- "." : Represents a space that the guard may occupy (Grass)
- "#" : Represents a space that the guard may not occupy (Obstacle)
- "^" : Represents the position of a Guard
- "v" : Represents the position of a Guard
- "<" : Represents the position of a Guard
- ">" : Represents the position of a Guard

The goal of the Component is to simulate the movement of each Guard in the Courtyard until every Guard has exited the Courtyard.  The output of the Component is the total number of unique locations that were occupied by a guard.

Simulation of the Courtyard will follow this process:
1. For each Tick in the simulation, each guard will attempt to move based on its current state:
   - "^" : Indicates the Guard will move up one row (index - 1)
   - "v" : Indicates the Guard will move down one row (index + 1)
   - "<" : Indicates the Guard will move left one column (index - 1)
   - ">" : Indicates the Guard will move right one column (index + 1)
2. The movement of the Guard is valid if:
   1. The Guard will occupy Grass "." after moving
   2. The Guard will exit the Courtyard after moving
3. If the movement of the Guard is not valid, the guard will change direction:
   - "^" -> ">"
   - ">" -> "v"
   - "v" -> "<"
   - "<" -> "^"
4. Once a Guard has exited the Courtyard, the Guard will not return to the Courtyard and is no longer simulated.
5. Simulation will end once all Guards have exited the Courtyard.

Review the following example data:

<example data>
....#.....
.........#
..........
..#.......
.......#..
..........
.#..^.....
........#.
#.........
......#...
</example data>

If the sample data was analyzed, the output of the component would be:

<output>
....#.....
....XXXXX#
....X...X.
..#.X...X.
..XXXXX#X.
..X.X.X.X.
.#XXXXXXX.
.XXXXXXX#.
#XXXXXXX..
......#X..

Total spaces occupied: 41
</output>

General development guidelines:
- This Spring Component will be integrated into an existing application
- All console output should be logged using Slf4j
- Provide detailed explanation of any algorithm to be implemented during analysis of the input data

</problem>

Note: Remember your task is to propose a software architecture and development workflow that will guide implementation.  Only generate source code for one module at a time when prompted.
