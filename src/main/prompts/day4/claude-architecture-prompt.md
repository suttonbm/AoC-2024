You are an expert software architect, knowledgeable in Java Spring and JUnit5.  Do not apologize unnecessarily.  If any instructions are unclear ask for clarification before proposing solutions.  Your task is to propose a software architecture and development workflow for a specific problem.

Provide a detailed analysis of the problem in a scratchpad.

<problem>
The goal of this problem is to develop a Java Spring Component that implements CommandLineRunner to analyze a text input file located in class path resources.

The text input file describes a crossword puzzle.  The puzzle consists of multiple lines of text, where each line is a series of characters.  The total content represents a rectangular matrix of characters.

When run, the Spring Component should analyze the input data and count the number of times a specified string appears in the data.  Valid matches conform to the following rules:

The match may equal the string or the reversed string.
The match may appear in a single row of characters.
The match may appear in a single column of characters. In other words, the same column index in sequential rows.
The match may appear diagonally. In other words, sequentially increasing or decreasing column indices in sequential rows.
Matches may overlap with other matches as long as the above rules are followed.
Consider the following example input data:

<data>

MMMSXXMASM
MSAMXMSMSA
AMXSXMAAMM
MSAMASMSMX
XMASAMXAMM
XXAMMXXAMA
SMSMSASXSS
SAXAMASAAA
MAMMMXMMMM
MXMXAXMASX

</data>

For a specified string “XMAS” the resulting count is: 18.

To illustrate the solution, the following solution text has replaced any character not included in a match for “XMAS” with “.”:

<solution text>

....XXMAS.
.SAMXMS...
...S..A...
..A.A.MS.X
XMASAMX.MM
X.....XA.A
S.S.S.S.SS
.A.A.A.A.A
..M.M.M.MM
.X.X.XMASX

</solution text>

</problem>

Note: Remember your task is not to propose source code to solve the problem.  Your task is to propose a software architecture and development workflow that will guide implementation.
