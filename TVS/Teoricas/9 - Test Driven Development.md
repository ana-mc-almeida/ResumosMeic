There are two aways of developing programs:
- test-last AKA code-first
- test-first 

## test-last Approach
Advantage: Tests developed after functionality of SUT is well understood.

Disadvantages:
- Developer focus on testing the implementation (written some minutes before) instead of focus on behavior.
- Select the test cases with a higher probability of passing.
- There is no incentive to write tests if the code is running.

# Test Driven Development Rhythm

Test-first coding consists of a few very simple steps
repeated over and over again -> incremental development.

Red, green, refactor:
- Write a test that fails.
- Make the code work.
- Eliminate redundancy.

<img src="Imagens/9 - red, green, refactor.png">

Note:
- Never write code without a failing test.
- Run all tests before new code and after new code.

## Pick a test

There are some heuristics to select the next test:
- The Low-Hanging Fruit.
  - Start with something really simple. Implement an obvious test case.
- The Most Informative One.
- First The Typical Case, Then Corner Cases.
- Listen To Your Experience.

## Red - Write a test that fails

Each test must represent an expected functionality of the code. When we make tests we know that the functionality does not work, because it was not implemented yet.

By writing test first:
- You think like a client of the SUT.
- You concentrate on what is really required.
- You test behavior and not implementation details. 
- Tests become more maintainable.

## Green - Write the simplest solution

Just code the smallest and simplest amount of code that satisfies the test.
- Do not think (too much) about possible enhancements.
- Do not try to fulfill those requirements of tests that have not yet been implemented.
- Concentrate on the task at hand.
- Quickly make the code run.
- Quick green excuses all sins (ugly code). This is not just about accepting sin; it is about being sinful.

## Refactor - Improve the code

Look at the broader picture and refactor all code.

You do not write new unit tests here.

Also refactor the tests, but don't create new ones.

## Advantages of TDD

- At least one test case for each functionality.
- Keeps unused code out of application.
- Finds bugs earlier.
- Improves software quality.
- Follows YAGNI principle - code is written only to satisfy the tests.
- Follows KISS principle - writing the smallest amount of code to make the test pass.
- DRY principle - refactoring phase make the code clean and readable.