# What is quality in software?

Quality, simplistically, means that a product should meet its specification. From a software point of view it means that a product should meet the requirements. However, some requirements are difficult to specify in an unambiguous way, software specifications are usually incomplete and often inconsistent.

The quality compromise: we cannot wait for specifications to improve before paying
attention to quality, and procedures must be put into place to improve quality in spite of imperfect specification.

Quality = expectations - delivery

# Quality cost

Being X a normalized unit of cost:
- Design - 1X
- Implementation - 5X
- Integration - 10X
- Beta Testing - 15X
- After Release - 30X

### Costs of conformance

All costs associated with planning and running tests (and revisions) just one time and activities that promote good quality (code reviews, training, ...)

### Costs of nonconformance

Costs due to internal failures (before release)
-  Cost of isolating, reporting and regression testing bugs (found before the product is released) to assure that they're fixed.

Costs due to external failures (after release)
- If bugs are missed and make it through to the customers, the result will be costly
product support calls, possibly fixing, retesting, and releasing the software, and, in a worst case-scenario, a product recall or lawsuits.
- Angry clients.

# Types of tests

### Unit Test

Scope: a relatively small executable: a class, a method or a cluster of
interdependent classes.

Goal: to show that the target of the test works has required.

### System Test

Scope: complete integrated application.

Focus on the capabilities and characteristics that are present only with the entire system.

Tests may be functional (input/output), performance and stress or load.

### Integration testing

Scope: a complete system or subsystem of software.

Can be difficult to place objects in the states required by the test. Some classes might not be developped yet.

### Regression testing

Changes often break working code. Rerun tests of version $n - 1$ on version $n$ before testing anything new. But which tests? All of them may take too much time.

# Limits of Testing

Passing a test is not enough to show the absence of bugs. Proof of correctness is equivalent to exhaustive testing.

### Triangle example:
- Length side in ] 0,10000 ]
- 104 possibilities for a segment size
- 1012 for a triangle.
- Testing 1000 cases per second, you would need 317 years!

The number of input and output combinations for trivial programs is surprisingly large, for typical programs is astronomical and for typical systems is beyond comprehension.

The best bugs are those that cause a failure every time they execute. However, most bugs are not like that.

Fault sensitivity: The ability of code to hide faults from a test suite. Executing buggy code does not always expose the bug.

Coincidental correctness: Buggy code can produce correct results for some inputs
  - Have x + x instead of x * x. Same result for x = 0 or x = 2.

# Testing Model

- Model the IUT, Implementation under test.
- Consider its fault model - A fault model identifies relationships and components of the SUT, System under test, that are more likely to have faults.
- Generate test cases based on the fault model
- Apply a test strategy
- Add test cases based on code analysis, suspicions and error-guessing

# Testing strategies:

### Black box (behavioral) testing

Uses expected responsabilities of IUT to design tests. Tests are design independently of the language or algorithm used in IUT.

### White box testing

Also relies on source code analisys to develop tests

### Fault-based testing

Purposely introduces faults into code to test. Then check if those faults are detected by the test suite.

# Testing cycle

Specification of a test case includes:
- The method to test.
- The initial state of the system.
  - initial state of the object being invoked (OUT).
  - maybe some other global variables.
- Input test values.
  - Includes the parameters of the MUT
- The expected output
  - Includes returned value (if any).
  - expected result state of the invoked object.
  - (maybe) expected state of parameters.
  - (maybe) expected state of global variables.

Properties of a test case:

- Test a single condition of the IUT.
- Independent
- Self-cleaning
- Documented
- Simple, small and clear to understand
- Accurate
- Reasonable probability of catching a defect
- Repeatable
- Fast

Test execution:
1. Establish that the IUT is minimally operational
2. Execute the test suite(s)
3. Use a coverage tool
4. If necessary, develop aditional test
5. Stop testing when coverage goal is met and all tests pass

### When is testing complete?

When you run out of time or money.
