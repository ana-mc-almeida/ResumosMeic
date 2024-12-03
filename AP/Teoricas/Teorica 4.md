# Activation functions

## Linear

Básicamente não se usa uma activation function.

$g(z) = z$

## Sigmoid

Pode ser visto como uma probabilidade

Com neuronio apenas é possível obter uma logistic regression, o que nos permite resolver problemas linearmente separaveis, mas não nos permite resolver problemas não-linearmente separaveis.

$g(z) = \sigma(z) = \dfrac{1}{1 + e^{-z}}$

## Hyperbolic Tangent

É basicamente o mesmo que a sigmoid mas entre -1 e 1.

Relaciona-se com a sigmoid: $\sigma(z) = \dfrac{1 + tanh(z/2)}{2}$

$g(z) = tanh(z) = \dfrac{e^z - e^{-z}}{e^z + e^{-z}}$ 

## ReLU - Rectified Linear Unit 

Não é diferenciavel em 0

Spartse activities: É bacano para simular a natureza. Podemos fazer com que alguns neuronios dentro da rede não contribuam para o resultado final, já que se $z$ for negativo, devolve 0.

$g(z) = relu(z) = max(0, z)$

## Softmax

Serve para multi classification. É usada para a final layer.

$o(z) =$ **softmax**$(z) = \Bigl[ \dfrac{e^{z_1}}{\Sigma _i e^{z_i}}, ..., \dfrac{e^{z_n}}{\Sigma _i e^{z_i}} \Bigl]$

## For the output layer

Se quisermos uma regression, não usamos uma função de ativação para a output layer.<br>
Se quisermos binary classification podemos usar sigmoid.<br>
Se quisermos multi classification podemos usar a softmax, que nos dá a probabilidade de ser cada classe, e podemos escolher a maior.

# Multiple (L > 1) Hidden Layers

Hidden Layer pre-activation ($h^{(0)} = x$):

$z^{(l)} = W^{(l)}h^{(l-1)}(x) + b(l)$

Hidden Layer activation:

$h^{(l)}(x) = g(z^{(l)}(x))$

Output Layer activation:

$f(x) = o(z^{(L + 1)}(x))$

# Universal Approximation Theorem

An NN with one hidden layer and a linear output can approximate arbitrarily well any continuous function, given enough hidden units.

# Loss Function

### Squared Loss

$L(y, \hat{y}) = \dfrac{1}{2}||\hat{y} - y||^2$

$\dfrac{\partial L(y, \hat{y})}{\partial z^{[L]}} = \dfrac{\partial \dfrac{1}{2}||z^{[L]} - y||^2}{\partial z^{[L]}} = z^{[L]} - y = \hat{y} - y$

### Cross-Entropy

Aqui $y_k$ é um vetor em que todas as entradas são 0, menos a respetiva ao valor certo, que tem valor 1.

$L(y,p) = - \sum\limits^d_{k=1} y_k \log(p_k) = -y^Tz + \log \sum\limits_j e^{z_j}$

$\dfrac{\partial L(y,p)}{\partial z} = -y+$ softmax($z$) $= -y + p$

# Gradient Descent

### Gradient output layer:

$$\dfrac{\partial L(y, \hat{y})}{\partial z^{[L]}}$$

### Gradient hidden layer parameters:

$$\dfrac{\partial L(y, \hat{y})}{\partial W^{[l]}} = \dfrac{\partial L(y, \hat{y})}{\partial z^{[l]}} h^{[l-1]^T}$$

$$\dfrac{\partial L(y, \hat{y})}{\partial b^{[l]}} = \dfrac{\partial L(y, \hat{y})}{\partial z^{[l]}}$$

### Gradient hidden layer below:

$$\dfrac{\partial L(y, \hat{y})}{\partial h^{[l]}} = W^{[l+1]^T}\dfrac{\partial L(y, \hat{y})}{\partial z^{[l+1]}}$$

### Gradient hidden layer below before activation $g$:

$$\dfrac{\partial L(y, \hat{y})}{\partial z^{[l]}} = \dfrac{\partial L(y, \hat{y})}{\partial h^{[l]}} \odot g'(z^{[l]}) $$

## Backpropagation

<img src="Imagens/T4 Backpropagation.png">