### Perceptron Algorithm:

Initialize $w^{0}$ = **0**<br>
Initialize k = 0 (number of mistakes)<br>
repeat<br>
&emsp;for each i<br>
&emsp;&emsp;get new training example ($x_i, y_i$)<br>
&emsp;&emsp;predict $\hat{y}_i$ = $z(w^{(k)} \cdot x_i)$ <br>
&emsp;&emsp;if $\hat{y}_i != y_i$ then:<br>
&emsp;&emsp;&emsp;update $W^{(k + 1)} = W^{(k)} + y_ix_i$<br>
&emsp;&emsp;&emsp;k++<br>
&emsp;&emsp;endif<br>
&emsp;endfor<br>
until maximum number of epochs<br>
output = $W^{(k)}$

# Question 1

## 1.1

$W^{(0)} = \begin{bmatrix} 0 \\ 0 \\ 0 \end{bmatrix}$

Epoch 1:

$\hat{y}^{(1)}$ = sign($\begin{bmatrix} 0 & 0 & 0 \end{bmatrix} \cdot \begin{bmatrix} 1 \\ -1 \\ 0 \end{bmatrix}$) = sign(0) = 1 != $y^{(1)}$ -> Errado, temos de atualizar pesos!

$W^{(1)} = W^{(0)} -1 \cdot \begin{bmatrix} 1 \\ -1 \\ 0 \end{bmatrix} = \begin{bmatrix} -1 \\ 1 \\ 0 \end{bmatrix}$

$\hat{y}^{(2)} = sign(\begin{bmatrix} -1 & 1 & 0 \end{bmatrix} \cdot \begin{bmatrix} 1 \\ 0 \\ 0.25 \end{bmatrix})$ = sign(-1) = -1 != $y^{(2)}$ -> Errado

$W^{(2)} = W^{(1)} + 1 \cdot \begin{bmatrix} 1 \\ 0 \\ 0.25 \end{bmatrix} = \begin{bmatrix} -1 \\ 1 \\ 0 \end{bmatrix} + \begin{bmatrix} 1 \\ 0 \\ 0.25 \end{bmatrix} = \begin{bmatrix} 0 \\ 1 \\ 0.25 \end{bmatrix}$

*...Acelerando as partes das contas...*

$\hat{y}^{(3)}$ = sign(1.25) = 1 == $y^{(3)}$ -> Certo

$\hat{y}^{(4)}$ = sign(0.75) = 1 != $y^{(4)}$ -> Errado

$W^{(3)} = W^{(2)} -1 \cdot \begin{bmatrix} 1 \\ 1 \\ -1 \end{bmatrix} = \begin{bmatrix} -1 \\ 0 \\ 1.25 \end{bmatrix}$

Epoch 2:

$\hat{y}^{(1)}$ = sign(-1) = -1 != $y^{(1)}$ -> Errado

$W^{(4)} = W^{(3)} -1 \cdot \begin{bmatrix} 1 \\ -1 \\ 0 \end{bmatrix} = \begin{bmatrix} -2 \\ 1 \\ 1.25 \end{bmatrix}$

$\hat{y}^{(2)}$ = sign(-1,6875) = -1 != $y^{(2)}$ -> Errado

$W^{(5)} = W^{(4)} + 1 \cdot \begin{bmatrix} 1 \\ 0 \\ 0.25 \end{bmatrix} = \begin{bmatrix} -1 \\ 1 \\ 1.5 \end{bmatrix}$

$\hat{y}^{(3)}$ = sign(1.5) = 1 == $y^{(3)}$ -> Certo

$\hat{y}^{(4)}$ = sign(-1.5) = -1 != $y^{(4)}$ -> Certo

## 1.2

$0 + 1 \cdot x_1 + 1.75 \cdot x_2 <=> x_2 = \dfrac{1}{1.75} \cdot x_1 <=> x_2 = 0.57 \cdot x_1$

<img src="Imagens/L2 1.2.png">

## 1.3

$\hat{y} =$ sign($\begin{bmatrix} 0 & 1 & 1.75 \end{bmatrix} \cdot \begin{bmatrix} 1 \\ 0 \\ 1 \end{bmatrix}$) $=$ sign(1.75) = 1

## 1.4

*Segundo o chatgpt,* sim, o algoritmo do perceptron converge sempre que os dados são linearmente separáveis.

# Question 2

## 2.1

<img src="Imagens/L2 2.1.png">

Exemplo: $W = \begin{bmatrix} 0 & 0.5 \end{bmatrix}$

## 2.2

<img src="Imagens/L2 2.2.png">

Exemplo: $W = \begin{bmatrix} 1.5 & 1 & -1 \end{bmatrix}$

## 2.3

<img src="Imagens/L2 2.3.png">

Exemplo: $W = \begin{bmatrix} 0.5 & 1 & -1 \end{bmatrix}$

## 2.4

<img src="Imagens/L2 2.4.png">


Os dados não são linearmente separáveis.