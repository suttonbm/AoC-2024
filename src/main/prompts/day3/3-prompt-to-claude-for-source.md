<language>Java</language>
<framework>Spring</framework>
<requirements>
- Create a Java Spring @Component called `CorruptedMemoryProcessor`.
- Implement the following methods:
    1. `parseInput()`: Read a list of strings from a text file located in classpath:/3/input.txt.
    2. `scanCorruptedMemory(String memory)`: Identify valid "mul" instructions, process multiplications, and return the sum.
    3. `processMultiplication(int x, int y)`: Multiply two integers.
- Add error handling for invalid or corrupted input.
- Create unit tests covering various scenarios:
    - Valid "mul" instructions.
    - Invalid or corrupted instructions.
    - Edge cases.
      </requirements>
      <constraints>
- A valid "mul" instruction has the format "mul(X,Y)" where X and Y are integers with 1-3 digits.
- Ignore invalid or corrupted characters in the input, such as %&, [, !, ?, or extra spaces.
</constraints>
<output>
- The code should adhere to clean coding practices, including proper error handling and logging.
- Ensure maintainability and scalability of the generated code.
</output>
<code_example>
  @Component
  public class CorruptedMemoryProcessor {
  // Define the methods `parseInput`, `scanCorruptedMemory`, and `processMultiplication` here
  }
</code_example>
<input>
- Text file located at classpath:/3/input.txt with content:
  mul(23,45)
  invalid_mul
  mul(10,20)
  mul(5,?
</input>
<output>
- For the above input, the `scanCorruptedMemory` method should return the sum: 1035 (1035 = 23*45 + 10*20).
- Errors should be logged for invalid entries.
</output>
<thought>
- Think step-by-step to generate the `CorruptedMemoryProcessor` class, starting with the structure, then method implementations, followed by error handling and test cases.
</thought>