# Prompt Engineering Techniques for Claude

## Be clear and direct
<purpose>Provide clear instructions and context to guide Claude's code generation responses</purpose>
<guidelines>
- Use simple, unambiguous language when describing the desired code functionality
- Break down complex requirements into a step-by-step list of instructions
- Specify the programming language, frameworks, or libraries to be used
  </guidelines>

## Use examples
<purpose>Enhance Claude's code generation performance and guide it to produce desired outputs</purpose>
<tips>
- Include code snippets that demonstrate the desired syntax, formatting, and structure
- Provide examples of input/output pairs to clarify expected behavior
- Use `<example>` tags to structure code examples
  </tips>

## Give Claude a role
<purpose>Prime Claude to generate code in a specific style or adhere to certain best practices</purpose>
<guidelines>
- Assign roles such as "Senior Python Developer" or "JavaScript Expert" to influence coding style
- Specify the target coding environment or platform (e.g., "Generate code for an AWS Lambda function")
- Mention any specific coding standards, conventions, or best practices to follow
  </guidelines>

## Use XML tags
<purpose>Structure code generation prompts to improve clarity and specificity</purpose>
<tips>
- Use tags like `<language>`, `<framework>`, `<input>`, `<output>`, `<requirements>`, and `<constraints>` to organize information
- Consistently format tags and their contents across prompts
- Use closing tags to clearly indicate the end of a section
  </tips>

## Chain prompts
<purpose>Break down complex code generation tasks into smaller, manageable steps</purpose>
<guidelines>
- Divide the overarching task into a series of subtasks, each focusing on a specific aspect of the code
- Use the output of one prompt as the input for the next prompt in the chain
- Ensure that the final prompt in the chain combines the outputs of all previous prompts coherently
  </guidelines>

## Let Claude think
<purpose>Encourage step-by-step problem-solving for more accurate and efficient code generation</purpose>
<guidelines>
- Prompt Claude to break down the coding task into a series of steps or pseudocode before generating the final code
- Ask Claude to explain its reasoning or thought process as it generates each code snippet
- Use `<thought>` and `<code>` tags to separate Claude's reasoning from the generated code
  </guidelines>

## Minimize ambiguity
<purpose>Reduce the likelihood of Claude generating incorrect or suboptimal code</purpose>
<guidelines>
- Provide clear, unambiguous instructions and requirements for the desired code
- Specify any constraints, limitations, or edge cases that the code must handle
- Avoid using vague or open-ended language that could lead to misinterpretation
  </guidelines>

By incorporating these prompt engineering techniques, you can generate high-quality prompts that elicit accurate, efficient, and well-structured code from Claude. The generated prompts will be tailored to the specific code generation task at hand, ensuring optimal performance and adherence to best practices.