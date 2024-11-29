# Question 1

## 1.1 

$X = \begin{bmatrix} 1 & -2 \\ 1 & -1 \\ 1 & 0 \\ 1 & 2\end{bmatrix}, y = \begin{bmatrix} 2 \\ 3 \\ 1 \\ -1 \end{bmatrix}$

Soma (squared) dos erros $(y - Xw)^T(y-Xw) = w^T X^T Xw - 2 w^T X^T y + y^T y$<br>
$0 = 2 X^T Xw - 2 X^T y <=> X^T y = X^T Xw <=> (X^T X)^{-1} X^T y = w$

Fazendo as contas:

$w = (X^T X)^{-1} X^T y = \Biggl( \begin{bmatrix} 1 & 1 & 1 & 1 \\ -2 & -1 & 0 & 2 \end{bmatrix} \begin{bmatrix} 1 & -2 \\ 1 & -1 \\ 1 & 0 \\ 1 & 2 \end{bmatrix} \Biggl)^{-1} \begin{bmatrix} 1 & 1 & 1 & 1 \\ -2 & -1 & 0 & 2 \end{bmatrix} \begin{bmatrix} 2 \\ 3 \\ 1 \\ -1 \end{bmatrix} = \begin{bmatrix} 1.0286 \\ -0.8857 \end{bmatrix}$

## 1.2

$\hat{y} = w^T \cdot x = \begin{bmatrix} 1.0286 & -0.8857 \end{bmatrix} \cdot \begin{bmatrix} 1 \\ 1 \end{bmatrix} = 0.1429$

# 1.3

$1.0286 - 0.8857 x_1$

<img src="Imagens/L3 1.3.png">

# 1.4

Formula: 
$$\dfrac{\sum\limits^N_{i=1} (y^{(i)} - w^T \cdot x^{(i)})^2}{N}$$
no nosso caso N = 4.

Fazendo as contas ficamos com
$$\dfrac{0.64 + 1.1788 + 0.0008 + 0.0661}{4} = 0.4714$$


# Question 2

## 2.1

A formula que queremos desenvolver:
$$w = w - \eta\dfrac{\partial L(w)}{\partial w}$$

Começamos pelo gradiente $\dfrac{\partial L(w)}{\partial w}$
E para ajudar a calcular o gradiente vamos divir em partes mais pequenas.:

$$\dfrac{\partial}{\partial w} y^{(i)} log[\sigma(w \cdot x^{(i)})] =  y^{(i)} \dfrac{\sigma'(w \cdot x^{(i)})}{\sigma(w \cdot x^{(i)})} = y^{(i)} \dfrac{\sigma(w \cdot x^{(i)})(1 - \sigma(w \cdot x^{(i)}))x^{(i)}}{\sigma(w \cdot x^{(i)})} = y^{(i)} (1 - \sigma(w \cdot x^{(i)}))x^{(i)}$$

Calculando a outra parte:

$$\dfrac{\partial}{\partial w} (1 - y^{(i)}) log[1 - \sigma(w \cdot x^{(i)})] = (1 - y^{(i)}) \dfrac{-\sigma'(w \cdot x^{(i)})}{1 - \sigma(w \cdot x^{(i)})} = (1 - y^{(i)}) \dfrac{-\sigma(w \cdot x^{(i)})(1 - \sigma(w \cdot x^{(i)}))x^{(i)}}{1 - \sigma(w \cdot x^{(i)})} = -(1 - y^{(i)})\sigma(w \cdot x^{(i)})x^{(i)}$$

Juntando as duas partes:

$$y^{(i)} (1 - \sigma(w \cdot x^{(i)}))x^{(i)}-(1 - y^{(i)})\sigma(w \cdot x^{(i)})x^{(i)} = x^{(i)}(y^{(i)} - \sigma(w \cdot x^{(i)}))$$

Adicionando o somatorio ficamos com:

$$\dfrac{\partial L(w)}{\partial w} = -\sum\limits^N_{i=1} x^{(i)}(y^{(i)} - \sigma(w \cdot x^{(i)}))$$

Assim acabamos com a gradient descent rule:

$$w = w + \eta\sum\limits^N_{i=1} x^{(i)}(y^{(i)} - \sigma(w \cdot x^{(i)}))$$

## 2.2

In stochastic gradient descent we make one update for each training example.

Mudamos a learning rule para:

$$w = w + \eta x(y - \sigma(w \cdot x))$$

Para o primeiro input:

$$w = w + \eta x_1(y_1 - \sigma(w \cdot x_1)) = \begin{bmatrix} 0 \\ 0 \\ 0 \end{bmatrix} + 1 \begin{bmatrix} 1 \\ -1 \\ 0 \end{bmatrix}
\Biggl( 0 -\sigma \Biggl( \begin{bmatrix} 0 \\ 0 \\ 0 \end{bmatrix} \cdot \begin{bmatrix} 1 \\ -1 \\ 0 \end{bmatrix}  \Biggl) \Biggl) = \begin{bmatrix} -0.5 \\ 0.5 \\ 0 \end{bmatrix}$$