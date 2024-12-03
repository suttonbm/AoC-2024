# Anthropic's XML Tagging System for Code Generation Prompts

Anthropic's XML tagging system helps structure code generation prompts, making them clearer and more effective for Claude. The custom GPT model should use these tags consistently when generating prompts.

## `<language>`
<description>
Specifies the programming language in which the code should be generated.
</description>
<example>
<language>Python</language>
</example>

## `<framework>`
<description>
Indicates the framework or library that should be used in the generated code, if applicable.
</description>
<example>
<framework>Django</framework>
</example>

## `<requirements>`
<description>
Lists the functional and non-functional requirements that the generated code must fulfill.
</description>
<example>
<requirements>
- Accept user input for name and age
- Validate that age is a positive integer
- Display a personalized greeting message
</requirements>
</example>

## `<input>`
<description>
Provides sample input data that the generated code should be able to handle.
</description>
<example>
<input>
name = "John"
age = 25
</input>
</example>

## `<output>`
<description>
Shows the expected output or behavior of the generated code based on the provided input.
</description>
<example>
<output>
Hello, John! You are 25 years old.
</output>
</example>

## `<code_example>`
<description>
Includes a code snippet that demonstrates the desired syntax, formatting, or structure of the generated code.
</description>
<example>
<code_example>
def greet(name, age):
    print(f"Hello, {name}! You are {age} years old.")
</code_example>
</example>

## `<constraints>`
<description>
Specifies any constraints or limitations that the generated code must adhere to, such as time or space complexity.
</description>
<example>
<constraints>
- Must have a time complexity of O(n)
- Should not use any external libraries
</constraints>
</example>

By using these XML tags in the generated prompts, you can provide Claude with well-structured and informative prompts tailored to code generation tasks. This will help Claude generate high-quality, requirements-compliant code that follows best practices and coding standards.
