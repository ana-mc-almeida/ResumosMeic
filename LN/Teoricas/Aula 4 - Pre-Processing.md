

<!-- toc -->

- [Pre-Processing](#pre-processing)
  * [Tokenization](#tokenization)
    + [Definition](#definition)
    + [Character-level](#character-level)
      - [Example](#example)
      - [Advantages](#advantages)
      - [Disadvantages](#disadvantages)
    + [Word-Level](#word-level)
      - [Example:](#example)
      - [Advantages](#advantages-1)
      - [Disadvantages](#disadvantages-1)
    + [Subword-Level](#subword-level)
      - [Example](#example-1)
      - [Advantages](#advantages-2)
  * [Setence / Word Manipulation](#setence--word-manipulation)
    + [Remove words and expressions](#remove-words-and-expressions)
    + [Lowercasing](#lowercasing)
      - [Disadvantages](#disadvantages-2)
    + [Normalization](#normalization)
  * [Conclusions](#conclusions)

<!-- tocstop -->

# Pre-Processing

Prepare the text in order to make it less expensive computationally and don't lose important information at the same time.

## Tokenization

### Definition

Process of breaking down a stream of text into smaller, manageable units called tokes.

The goal is to create tokens that retain meaningful linguistic information while making the text more accessible for computational models.

Tokens can be:

- Characters
- Words
- Subwords

### Character-level

#### Example

Input: hello<br>
Output: [h, e, l, l, o]

#### Advantages

- Can manage out-of-vocabulary elements.

#### Disadvantages

- Tokens carry very little context.

<br>

### Word-Level

#### Example:

Input: Mr. Smith finished his Ph.D on the 28th April.<br>
Output: [Mr., Smith, finished, his, Ph.D, on, the, 28th, April, .]

#### Advantages

- It is intuitive because words are natural linguistic units.

#### Disadvantages

- Requires language-specific rules
  - Mr.
  - 55.5 or 55,5
  - U.S.A
- Struggles with unknown words, typos or words not present in the training vocabulary.
- For languages with no space between words it is even harder to distinguish words. (ex: Chinese)

<br>

### Subword-Level

#### Example

Input: unhapiness<br>
Output: [un, happi, ness]

#### Advantages

- Cobines the benefits ot character and word-level tokenization by breaking down OOV (out of vocabulary) words into known subwords.
- Widely used nowadays.
- Requires more sophisticated algorithms to split text.
- May break down words in a way that loses meaningful context.

<br>

## Setence / Word Manipulation

### Remove words and expressions

- "é que"
  - ex: Onde (é que) ficam os campi do IST? - o sentido mantém-se.
- Stop words (mainly functional words)
  - a, de, para, ...
  - the, a, before, this<br>
- Punctuation
  - Os assassinos de D. Carlos, Afonso Costa e Buiça, foram...
  - Os assassinos de D. Carlos, Afonso Costa e Buiça foram...
    - Punctuation can generate different meanings when removed !

### Lowercasing

- Avoid data sparness
  - **The** dog is nice vs. I like **the** dog

#### Disadvantages

Some words are supposed to have upper case letters:

- Us vs us
- Windows vs. windows
- Figo vs. figo

### Normalization

- Dates
  - 8th-Feb | February 8th | 02/08
- Names
- Numbers

## Conclusions

- If you pre-process the training set, pre-process in the exact way the test set.
- Pre-processing is not a good idea with LLMs
- Pre-processing does not guarantee better results.
