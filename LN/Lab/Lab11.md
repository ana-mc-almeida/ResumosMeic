# Decoding

## 1. Greedy

Assembling

## 2. Beam Search

Ou Assembling ou Fighting

## 3. Random Decoding

- 50% Assembling
- 20% Fighting
- 15% Resting
- 10% Training
- 5% Recruiting

## 4. Top-K Sampling

- SoftMax(50%) Assembling
- SoftMax(20%) Fighting
- SoftMax(15%) Resting

## 5. Top-p Sampling

- SoftMax(50%) Assembling
- SoftMax(20%) Fighting

## 6. Top-p Sampling + Temperature

| token      | p    | p $^{\dfrac{1}{T}}$ | Normalize |
| ---------- | ---- | ------------------- | --------- |
| Assembling | 0.5  | 0,4204              | 0.5781    |
| Fighting   | 0.2  | 0.1337              | 0.1839    |
| Resting    | 0.15 | 0.0933              | 0.1283    |
| Training   | 0.1  | 0.0562              | 0.0773    |
| Recruiting | 0.05 | 0.0236              | 0.0325    |
|            | Sum: | 0.7272              |

- 0.5781 Assembling
- 0.1839 Fighting
