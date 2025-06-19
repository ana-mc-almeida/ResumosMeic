# 2.1

- How many lines and words?

  - Command `wc friends.txt`
  - Result `60363  756783 4117566 friends.txt`

- How many main characters?
  - Command `cut -f1 friends.txt | sort -f | uniq -ci | sort`
  - Result: Não conseguimos os personagens principais, apenas uma lista dos personagens e quantidade de vezes que aparecem.

# 2.2

Top 10 most used words:

- Command: `tr -sc 'A-Za-z' '\n' < friends.txt | sort | uniq -ci | sort | tail -10`
- Solution: `tr -sc 'A-Za-z' '\n' < friends.txt | sort -f | uniq -ci | sort -n | tail -10`
- Result:
- 10. 11286 THAT
- 9. 11701 ROSS
- 8. 13165 AND
- 7. 13531 A
- 6. 13863 IT
- 5. 15301 S
- 4. 16749 TO
- 3. 19234 THE
- 2. 27790 YOU
- 1. 33856 I

# 2.3.1

- Extract all lines with the word "Monica"

  - Command: `grep "Monica" < friends.txt`
  - Nem todos os resultados são as falas da Monica, também há linhas em que o nome dela apenas é mencionado na fala de outra personagem.

- How many lines with the word "Monica" can be found?

  - Command: `grep "Monica" < friends.txt | wc`
  - Result: 9295 lines, 126433 words, 695692 bytes

- Extract all Monica's dialogue lines

  - Command: `grep "^Monica" < friends.txt`

- Who, between the 6 main characters of the series (Monica, Rachel, Ross, Phoebe, Joey or Chandler) has more dialogue lines?
  - Command: `grep -E '^Monica|^Rachel|^Ross|^Phoebe|^Joey|^Chandler' friends.txt | cut -f1 | sort | uniq -c | sort -nr | head -6`
  - Solution: Rachel

# 2.3.2

- Find the most sarcastic friend
  - Command: `grep -E "^Monica|^Rachel|^Ross|^Phoebe|^Joey|^Chandler" friends.txt | grep "(sarcastic)" | cut -f1 | sort | uniq -c`
  - Solution: Ross (10)
