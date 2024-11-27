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

// TODO

# Gradient Descent

## Hidden Layer Gradient

Recap: $z^{(l+1)} = W^{(l+1)}h^{(l)}(x) + b(l+1)$, $\theta = (W^{(l)}, b^{(l)})$

$\dfrac{\partial L(f(x;\theta), y)}{\partial h_j^{(l)}} = \sum\limits_i \dfrac{\partial L(f(x;\theta), y)}{\partial z_i^{(l + 1)}} \dfrac{\partial z_i^{(l + 1)}}{\partial h_j^{(l)}} = \sum\limits_i \dfrac{\partial L(f(x;\theta), y)}{\partial z_i^{(l + 1)}} W_{i,j}^{(l+1)}$

## Parametre Gradient

$\dfrac{\partial L(f(x; \theta), y)}{\partial W_{i,j}^{(l)}} = \dfrac{\partial L(f(x; \theta), y)}{\partial z_i^{(l)}} \dfrac{\partial z_i^{(l)}}{\partial W_{i,j}^{(l)}} = \dfrac{\partial L(f(x; \theta), y)}{\partial z_i^{(l)}} h_j^{(l-1)}$

## Backpropagation

<img src="Imagens/T4 Backpropagation.png">