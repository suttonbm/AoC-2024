
You are an expert in Java Spring development.  Review the attached software specification.  Your task is to implement each of the classes listed in Component Structure based on the requirements.  Generate detailed source code one class at a time, pausing to confirm correct implementation before proceeding to the next class.

<language>Java</language> <framework>Spring Framework</framework> <requirements> Refer to attached specification </requirements> <constraints> - Use Java 17+ and Spring Framework.  - Adhere to the provided class architecture and package structure. - Test coverage >90%. </constraints> <input> Puzzle Matrix: [ ['X', 'M', 'A', 'S'], ['A', 'B', 'C', 'D'] ] Pattern: "XMAS" </input> <output> Matches: - Position: Row 0, Column 0 - Direction: Horizontal </output>

----------------------------------------------------

# Crossword Puzzle Analysis - Software Specification
Version 1.0

## 1. Overview

### 1.1 Purpose
This document specifies the requirements, architecture, and implementation guidelines for a Spring-based text puzzle analysis system. The system analyzes a crossword puzzle matrix to find and count pattern matches in multiple directions.

### 1.2 Scope
The system will:
- Read puzzle input from classpath resources
- Analyze text patterns in multiple directions
- Support bidirectional matching
- Handle overlapping matches
- Provide accurate match counts

### 1.3 Technical Constraints
- Java Spring Framework
- JUnit 5 Testing Framework
- Maven or Gradle build system
- Java 17+ runtime environment

## 2. System Architecture

### 2.1 Component Structure
```
net.suttonbm.aoc2024.day4/
├── config/
│   └── PuzzleConfig.java
├── runner/
│   └── PuzzleRunner.java
├── service/
│   ├── PuzzleService.java
│   └── impl/
│       └── PuzzleServiceImpl.java
├── strategy/
│   ├── SearchStrategy.java
│   ├── HorizontalSearchStrategy.java
│   ├── VerticalSearchStrategy.java
│   └── DiagonalSearchStrategy.java
├── model/
│   ├── PuzzleMatrix.java
│   └── Match.java
├── util/
│   └── ResourceReader.java
└── exception/
    ├── InvalidInputException.java
    └── ResourceNotFoundException.java
```

### 2.2 Component Specifications

#### 2.2.1 ResourceReader (util)
```java
public class ResourceReader {
    /**
     * Reads and validates puzzle input file
     * @throws InvalidInputException if input is not rectangular
     * @throws ResourceNotFoundException if file not found
     */
    PuzzleMatrix readPuzzle(String resourcePath);
    
    // Internal methods
    private boolean validateInput(List<String> lines);
    private char[][] convertToMatrix(List<String> lines);
}
```

**Validation Rules:**
- Non-null input
- Rectangular dimensions
- Non-empty content
- Valid character set

#### 2.2.2 PuzzleMatrix (model)
```java
public class PuzzleMatrix {
    private final char[][] matrix;
    
    // Core methods
    public char getChar(int row, int col);
    public char[] getRow(int row);
    public char[] getColumn(int col);
    public List<char[]> getDiagonals();
    
    // Utility methods
    public int getRows();
    public int getColumns();
    public boolean isValidPosition(int row, int col);
}
```

**Invariants:**
- Immutable after construction
- Non-null matrix elements
- Fixed dimensions

#### 2.2.3 SearchStrategy (strategy)
```java
public interface SearchStrategy {
    /**
     * Finds all matches for a pattern in the specified direction
     * @return List of Match objects containing positions
     */
    List<Match> findMatches(PuzzleMatrix matrix, String pattern);
    
    /**
     * Indicates supported search direction
     */
    boolean supportsDirection(SearchDirection direction);
}
```

**Strategy Implementations:**
1. HorizontalSearchStrategy
    - Row-wise searching
    - Forward and reverse matching
    - Overlap handling

2. VerticalSearchStrategy
    - Column-wise searching
    - Forward and reverse matching
    - Overlap handling

3. DiagonalSearchStrategy
    - Diagonal traversal
    - Both directions
    - Boundary management

#### 2.2.4 PuzzleService (service)
```java
public interface PuzzleService {
    /**
     * Primary analysis method
     * @return total number of matches found
     */
    int analyzePattern(PuzzleMatrix matrix, String pattern);
    
    /**
     * Detailed match information
     * @return List of all matches found
     */
    List<Match> findAllMatches(PuzzleMatrix matrix, String pattern);
}
```

**Service Guarantees:**
- Thread safety
- Performance monitoring
- Complete error handling
- No duplicate matches

#### 2.2.5 PuzzleRunner (runner)
```java
@Component
public class PuzzleRunner implements CommandLineRunner {
    private final PuzzleService puzzleService;
    private final ResourceReader resourceReader;
    
    @Override
    public void run(String... args);
}
```

**Runner Responsibilities:**
- Argument validation
- Resource loading
- Service orchestration
- Result presentation

## 3. Test Specifications

### 3.1 Unit Tests

#### ResourceReader Tests
```java
class ResourceReaderTest {
    @Test
    void validInputTest() {
        String input = "ABC\nDEF\nGHI";
        assertDoesNotThrow(() -> reader.readPuzzle("valid.txt"));
    }
    
    @Test
    void nonRectangularInputTest() {
        assertThrows(InvalidInputException.class, 
            () -> reader.readPuzzle("invalid.txt"));
    }
}
```

#### SearchStrategy Tests
```java
class HorizontalSearchStrategyTest {
    @Test
    void simplePatternTest() {
        char[][] data = {
            {'X', 'M', 'A', 'S'},
            {'A', 'B', 'C', 'D'}
        };
        List<Match> matches = strategy.findMatches(
            new PuzzleMatrix(data), "XMAS"
        );
        assertEquals(1, matches.size());
    }
}
```

### 3.2 Integration Tests
```java
@SpringBootTest
class PuzzleIntegrationTest {
    @Test
    void examplePuzzleTest() {
        String input = """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            """;
        assertEquals(18, service.analyzePattern(input, "XMAS"));
    }
}
```

## 4. Development Workflow

### 4.1 Implementation Phases

Phase 1: Foundation (Week 1)
- Project setup
- Basic models
- Resource handling

Phase 2: Core Logic (Week 2)
- Search strategies
- Pattern matching
- Unit tests

Phase 3: Integration (Week 3)
- Service implementation
- Performance optimization
- Integration tests

Phase 4: Finalization (Week 4)
- Runner implementation
- Documentation
- Performance testing

### 4.2 Quality Gates

1. Code Quality
- 90% test coverage
- Zero critical issues
- Clean code principles

2. Performance
- Sub-second response (100x100)
- Linear scaling
- Memory efficient

3. Documentation
- Complete Javadoc
- Architecture records
- User guide

## 5. Performance Requirements

### 5.1 Response Time Targets
| Matrix Size | Maximum Time |
|------------|--------------|
| 10x10      | 100ms       |
| 50x50      | 250ms       |
| 100x100    | 500ms       |

### 5.2 Memory Constraints
- Heap usage < 256MB
- No memory leaks
- Efficient matrix traversal

## 6. Error Handling

### 6.1 Exception Hierarchy
```
RuntimeException
├── InvalidInputException
├── ResourceNotFoundException
└── ProcessingException
```

### 6.2 Error Codes
- E001: Invalid input format
- E002: Resource not found
- E003: Empty input
- E004: Invalid pattern
- E005: System error

## 7. Sample Test Cases

### 7.1 Basic Pattern
```
Input:
XMAS
AMSX
SAMA
XMAS

Pattern: "XMAS"
Expected: 5 matches
```

### 7.2 Complex Pattern
```
Input:
MMMSXXMASM
MSAMXMSMSA
AMXSXMAAMM
...

Pattern: "XMAS"
Expected: 18 matches
```

## 8. Dependencies

### 8.1 Required Libraries
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### 8.2 Development Tools
- Java 17+
- Maven/Gradle
