<task>

Propose a prompt for Claude which will result in generation of the following classes and methods:

1. Create a new Java Spring @Component called `CorruptedMemoryProcessor`.

2. Implement the following methods in the `CorruptedMemoryProcessor` class:
   a. `parseInput()`: Read a list of strings from a text file located in classpath:/3/input.txt to be processed by `scanCorruptedMemory()`.
   b. `scanCorruptedMemory(String memory)`: Scan the input string, identify valid "mul" instructions, process the multiplications, and return the final sum.
   c. `processMultiplication(int x, int y)`: Perform the actual multiplication of two numbers.

3. Add error handling to the methods to handle invalid or corrupted input.

4. Create unit tests for the `CorruptedMemoryProcessor` class to cover various scenarios, including valid "mul" instructions, invalid/corrupted instructions, and edge cases.

5. Here are the rules for identifying and processing valid "mul" instructions:
   - A valid "mul" instruction has the format "mul(X,Y)" where X and Y are each 1-3 digit numbers.
   - You should multiply the numbers X and Y together and record the result.
   - Ignore any invalid or corrupted characters that appear to be part of a "mul" instruction, such as %&, [, !, ?, or extra spaces.

</task>