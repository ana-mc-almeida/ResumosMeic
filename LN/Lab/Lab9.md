# 1

N: N, V, ADJ, ADV <br>
T: Set of terminaal symbols

alex eats soup everyday:

- S
  - NP
    - N
      - Alex
  - VP
    - V
      - eats
    - N
      - soup
  - ADV
    - everyday


children like chips:

- S
  - NP
    - N
      - children
  - VP
    - V
      - like
    - NP
      - N
        - chips

cats adore salty soup

- S
  - NP
    - N
      - cats
  - VP
    - V
      - adore
    - NP
      - ADJ
        - salty
      - N
        - soup


Rules:<br>
S -> NP VP | NP VP ADV<br>
<br>
NP -> N | ADJ N<br>
VP -> V NP<br>
<br>
N -> "Alex" | "soup" | "children" | "cats" | "chips"<br>
V -> " eats" | "like" | "adore"<br>
ADJ -> "salty"<br>
ADV -> "everyday"

<br>

# 2

- S
  - NP
    - N
      - children
  - VP
    - V
      - adore
    - NP
      - N
        - soup

# 3

S -> NP VP

NP -> N<br>
N -> "children"

VP -> V NP<br>
V -> "adore"<br>
NP -> N<br>
N -> "soup"



