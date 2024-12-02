# Question 1

## 1.1

tanh($x$) = $\dfrac{e^x - e^{-x}}{e^x + e^{-x}} = \dfrac{e^{-x}(e^x - e^{-x})}{e^{-x}(e^x + e^{-x})} = \dfrac{1 - e^{-2x}}{1 + e^{-2x}} = \dfrac{(-1 + 1) + 1 - e^{-2x}} {1 + e^{-2x}} = \dfrac{2 - 1 - e^{-2x}}{1 + e^{-2x}} = \dfrac{2}{1 + e^{-2x}} - 1$

E a derivada fica:

$$\dfrac{\partial}{\partial x} tanh(x) = \dfrac{\partial}{\partial x} \bigg(\dfrac{2}{1 + e^{-2x}} - 1\bigg) = 2\dfrac{-\dfrac{\partial}{\partial x} (1 + e^{-2x})}{(1 + e^{-2x})^2} = 4\dfrac{e^{-2x}}{(1 + e^{-2x})^2} = 4\dfrac{e^{-2x} + 1 - 1}{(1 + e^{-2x})^2} = 4\bigg(\dfrac{e^{-2x} + 1}{(1 + e^{-2x})^2} - \dfrac{1}{(1 + e^{-2x})^2}\bigg) = \dfrac{4}{1 + e^{-2x}} - \dfrac{4}{(1 + e^{-2x})^2} = $$

$$\dfrac{4}{1 + e^{-2x}} - \dfrac{4}{(1 + e^{-2x})^2} - 1 + 1 = -\bigg(\dfrac{2}{1 + e^{-2x}} - 1\bigg)^2 + 1 = 1 - tanh(x)^2$$

Forward propagation:

$x = h^{[0]} = \begin{bmatrix} 1 \\ 0 \\ 1 \\ 0 \end{bmatrix}$

$h^{[1]} = tanh\Bigg(W^{[1]} \cdot h^{0} + b^{[1]}\Bigg) = tanh\Bigg(\begin{bmatrix} 0.3 \\ 0.3 \\ 0.3 \\ 0.3 \end{bmatrix}\Bigg) = \begin{bmatrix} 0.2913 \\ 0.2913 \\ 0.2913 \\ 0.2913 \end{bmatrix}$

$h^{[2]} = \begin{bmatrix} 0.2165 \\ 0.2165 \\ 0.2165 \end{bmatrix}$

$z^{[3]} = \begin{bmatrix} 0.16396 \\ 0.16396 \\ 0.16396 \end{bmatrix}$

Calculando o erro:

$$L(y, \hat{y}) = \dfrac{1}{2} \sum\limits^3_{i=1}(\hat{y}_i - y_i)^2 = 0.376$$

---

Gradient output layer:

$$\dfrac{\partial L(y, \hat{y})}{\partial z^{[L]}} = \dfrac{\partial \dfrac{1}{2}||z^{[L]} - y||^2}{\partial z^{[L]}} = z^{[L]} - y = \hat{y} - y$$

Gradient hidden layer parameters (*não tou a conseguir provar isto, vou só assumir que é uma regra*):

$$\dfrac{\partial L(y, \hat{y})}{\partial W^{[l]}} = \dfrac{\partial L(y, \hat{y})}{\partial z^{[l]}} h^{[l-1]^T}$$

$$\dfrac{\partial L(y, \hat{y})}{\partial b^{[l]}} = \dfrac{\partial L(y, \hat{y})}{\partial z^{[l]}}$$

Gradient hidden layer below:

$$\dfrac{\partial L(y, \hat{y})}{\partial h^{[l]}} = W^{[l+1]^T}\dfrac{\partial L(y, \hat{y})}{\partial z^{[l+1]}}$$

Gradient hidden layer below before activation $g$:

$$\dfrac{\partial L(y, \hat{y})}{\partial z^{[l]}} = \dfrac{\partial L(y, \hat{y})}{\partial h^{[l]}} \odot g'(z^{[l]})$$

---

Contas para a atualização:

$$\dfrac{\partial L(y, \hat{y})}{\partial z^{[3]}} = \hat{y} - y = \begin{bmatrix} 0.16396 \\ -0.83604 \\ 0.16396 \end{bmatrix}$$

$$\dfrac{\partial L(y, \hat{y})}{\partial W^{[3]}} = \dfrac{\partial L(y, \hat{y})}{\partial z^{[3]}} h^{[2]^T} = \begin{bmatrix} 0.03496 & 0.03496 & 0.03496 \\ -0.17825 & -0.17825 & -0.17825 \\ 0.03496 & 0.03496 & 0.03496 \end{bmatrix}$$

$$\dfrac{\partial L(y, \hat{y})}{\partial b^{[3]}} = \dfrac{\partial L(y, \hat{y})}{\partial z^{[3]}} = \begin{bmatrix} 0.16396 \\ -0.83604 \\ 0.16396 \end{bmatrix}$$

---

$$\dfrac{\partial L(y, \hat{y})}{\partial h^{[2]}} = W^{[3]^T}\dfrac{\partial L(y, \hat{y})}{\partial z^{[3]}} = \begin{bmatrix} -0.050812 \\ -0.050812 \\ -0.050812 \end{bmatrix}$$

$$\dfrac{\partial L(y, \hat{y})}{\partial z^{[2]}} = \dfrac{\partial L(y, \hat{y})}{\partial h^{[2]}} \odot (1 - (h^{[2]})^2) = \begin{bmatrix} -0.04850 \\ -0.04850 \\ -0.04850 \end{bmatrix}$$

$$\dfrac{\partial L(y, \hat{y})}{\partial W^{[2]}} = \dfrac{\partial L(y, \hat{y})}{\partial z^{[2]}} h^{[1]^T} = \begin{bmatrix} -0.01413 & -0.01413 & -0.01413 & -0.01413 \\ -0.01413 & -0.01413 & -0.01413 & -0.01413 \\ -0.01413 & -0.01413 & -0.01413 & -0.01413 \end{bmatrix}$$

$$\dfrac{\partial L(y, \hat{y})}{\partial b^{[2]}} = \dfrac{\partial L(y, \hat{y})}{\partial z^{[2]}} = \begin{bmatrix} -0.04850 \\ -0.04850 \\ -0.04850 \end{bmatrix}$$

---

$$\dfrac{\partial L(y, \hat{y})}{\partial h^{[1]}} = W^{[2]^T}\dfrac{\partial L(y, \hat{y})}{\partial z^{[2]}} = \begin{bmatrix} -0.01455 \\ -0.01455 \\ -0.01455 \\ -0.01455 \end{bmatrix}$$

$$\dfrac{\partial L(y, \hat{y})}{\partial z^{[1]}} = \dfrac{\partial L(y, \hat{y})}{\partial h^{[1]}} \odot (1 - (h^{[1]})^2) = \begin{bmatrix} -0.01332 \\ -0.01332 \\ -0.01332 \\ -0.01332 \end{bmatrix}$$

$$\dfrac{\partial L(y, \hat{y})}{\partial W^{[1]}} = \dfrac{\partial L(y, \hat{y})}{\partial z^{[1]}} x^T = \begin{bmatrix} -0.01332 & 0 & -0.01332 & 0 \\ -0.01332 & 0 & -0.01332 & 0 \\ -0.01332 & 0 & -0.01332 & 0 \\ -0.01332 & 0 & -0.01332 & 0 \end{bmatrix}$$

$$\dfrac{\partial L(y, \hat{y})}{\partial b^{[1]}} = \dfrac{\partial L(y, \hat{y})}{\partial z^{[1]}} = \begin{bmatrix} -0.01332 \\ -0.01332 \\ -0.01332 \\ -0.01332 \end{bmatrix}$$

---

Fazendo o update:

$W^{[1]} - \eta \dfrac{\partial L(y, \hat{y})}{\partial W^{[1]}} = \begin{bmatrix} 0.101332 & 0.1 & 0.101332 & 0.1 \\ 0.101332 & 0.1 & 0.101332 & 0.1 \\ 0.101332 & 0.1 & 0.101332 & 0.1 \\ 0.101332 & 0.1 & 0.101332 & 0.1 \end{bmatrix}$

$b^{[1]} = b^{[1]} - \eta \dfrac{\partial L(y, \hat{y})}{\partial b^{[1]}} = \begin{bmatrix} 0.101332 \\ 0.101332 \\ 0.101332 \\ 0.101332 \end{bmatrix}$

*E fazer igual para* $W^{[2]}$, $b^{[2]}$, $W^{[3]}$, $b^{[3]}$

## 1.2

É para fazer literalemente tudo igual ao que fizemos em cima e fazer uma dupla atualização a $W^{[i]}$ e $b^{[i]}$ tendo em conta os dois inputs. *Obviamente não vou repetir isto tudo '-'.*

# Question 2

## 2.1

softmax $\big(\begin{bmatrix} z_1 & z_2 & ... & z_d \end{bmatrix}^T\big) = \begin{bmatrix} p_1 & p_2 & ... & p_d \end{bmatrix}^T$

Onde $p_i = \dfrac{e^{z_i}}{\sum\limits^d_{k=1} e^{z_k}} $

Cross-Entropy: $L(y,p) = - \sum\limits^d_{k=1} y_k \log(p_k) = -y^Tz + \log \sum\limits_j e^{z_j}$

Logo $\dfrac{\partial L(y,p)}{\partial z} = -y$ softmax($z$) $= -y + p$

---

Forward propagation: *Igual à do primeiro exercício.*

$p = $ softmax($\begin{bmatrix} 0.16396 \\ 0.16396 \\ 0.16396 \end{bmatrix}$) $= \begin{bmatrix} 0.3333 \\ 0.3333 \\ 0.3333 \end{bmatrix}$

Calculando o erro: $L(y,p) = - \sum\limits^d_{k=1} y_k \log(p_k) = 1.0986$

---

Gradient output layer: $\dfrac{\partial L(y,p)}{\partial z^{[L]}} = -y + p$

*Os outros gradients são iguais aos do primeiro exercicio.*

A partir daqui é só contas e fazer o update.

## 2.2

*Igual ao* [1.2](#12)