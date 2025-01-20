# Feature Representation

A parte mais difícil de classificar imagens é saber que features devem ser retiradas da imagem.

A abordagem imediata é criar um vetor de features $\phi(x)$. Pode incluir todo o tipo de features: boolean, continous, etc. Por exemplo, em NLP diferentes objetivos podem ter diferentes features:
- Word occurences: binary features (word occurs or not in document)
- Word counts: numeric features (how many times a word occurs)
- POS tags: classification features (classify words as nouns, verbs, etc)

Em NLP para verificar a qualidade da tradução podemos usar vários mecanismos:
- number of tokens in the source and in target segment
- probability of source and target segment
- ...

# Linear Regression

Segue o modelo $\hat{y} = w^Tx + b$

E tem a closed form solution: $\hat{w} = (\boldsymbol{X}^T \boldsymbol{X})^{-1} \boldsymbol{X}^T y$, com $\boldsymbol{X} = \begin{bmatrix} \phi(x_1) \\ \vdots \\ \phi(x_n) \end{bmatrix} $

### Regression Losses

1. Squared Loss: $L(y, \hat{y}) = \dfrac{1}{2}(y - \hat{y})^2$
2. Absolute Error Loss: $L(y, \hat{y}) = |y - \hat{y}|$.
3. Huber Loss: $L(y, \hat{y}) = \begin{cases} \dfrac{1}{2}(y - \hat{y})^2 & \text{if } |y - \hat{y}| \leq 1 \\ |y - \hat{y}| - \dfrac{1}{2} & \text{otherwise} \end{cases}$

# Ridge Regression and Regularization

If the term $\boldsymbol{X}^T \boldsymbol{X}$ in $\hat{w} = (\boldsymbol{X}^T \boldsymbol{X})^{-1} \boldsymbol{X}^T y$ is not invertible, we need to use the ridge regression:

$\hat{w}_\text{ridge} = (\boldsymbol{X}^T \boldsymbol{X} + \lambda I)^{-1} \boldsymbol{X}^T y = \text{arg } \min\limits_w ||\boldsymbol{X}\boldsymbol{w} - \boldsymbol{y}||^2 + \lambda||\boldsymbol{w}||^2_2$, with $||\boldsymbol{w}||^2_2 = \sum\limits_i w_i^2$, the squared $\ell_2$ norm. $\ell_2$ regularization is also called weigth decay, or penalized Least Squares.

# Maximum A Posteriori (MAP)

$\hat{w}_\text{MAP} = \text{arg } \max\limits_w P(w | y) = \text{arg } \max\limits_w \dfrac{P(y | w) P(w)}{P(y)} = \text{arg } \max\limits_w P(y | w) P(w)$ <- (Bayes)

$\hat{w}_\text{MAP} = \text{arg } \min\limits_w \lambda||\boldsymbol{w}||^2_2 + ||\boldsymbol{X}\boldsymbol{w} - \boldsymbol{y}||^2$ <- loss function + regularization term 

Regularization constant: $\lambda = \dfrac{\sigma^2}{\tau^2}$

# Perceptron

### Algorithm

1. Predict $\hat{y}_n$ using $x_n$ with the current $w$.
2. If $\hat{y}_n$ is correct, do nothig.
3. If $\hat{y}_n$ is incorrect, $w = w + y_n \phi(x_n)$

If the data set is linearly separable, a correct $w$ will be found in at most $k = \dfrac{R^2}{\gamma^2}$ mistakes. $R$ is the radius of the data $R = \max\limits_n ||\phi(x_n)||$ and $\gamma$ é a margem pela qual o data set é separável.

# Multi-class classification

- One-vs-all (OvA): train K binary classifiers, one per class, using all others as negative examples. To classify, pick the class with the highest score.
- One-vs-one (OvO): train K(K - 1)/2 pairwise classifiers and use majority voting.
- Binary coding: use binary code (maybe an error correcting code - ECoC) for the class labels and learn a binary classifier for each bit.

# Logistic Regression

Is a linear model that gives a score for each class.

### Softmax

$\text{softmax} = \dfrac{e^{w_y^T\phi(x)}}{\sum\limits_{y'} e^{w_{y'}^T\phi(x)}} $

Derivative, com cross-entropy loss: $\dfrac{\partial L}{\partial z_i} = \hat{y}_i - y_i$

### Sigmoid

$\text{sigmoid}(u) = \dfrac{e^u}{1 + e^u}$

Derivative: $\sigma'(x) = \sigma(x)(1 - \sigma(x))$

ReLUs are significantly simpler and have a much simpler derivative than the sigmoid, leading to faster computation times. Also, sigmoids are easy to saturate and, when that happens, the corresponding gradients are only residual, making learning slower. ReLUs saturate only for negative inputs, and have constant gradient for positive inputs, often exhibiting faster learning.

# Gradient Descent

Loss function in Logistic Regression:

$L(W;(x,y)) = \log [\sum\limits_{y'} e^{w_{y'}^T\phi(x)} - w_{y}^T\phi(x) ]$

$W = W - \eta \nabla L(W;(x,y))$

### Stochastic Gradient Descent

Works the same way as Gradient Descent but a random data point is used instead.

# Regularization

$\text{arg} \min\limits_W \sum\limits_t^N L(W;(x,y)) + \lambda \Omega(W) $

$\Omega(W)$ is the regularization function and $\lambda$ controls the weight.

- $\ell_2$ regularization promotes smaller weights: $\Omega(W) = \sum\limits_y ||w_y||_2^2$
- $\ell_1$ regularization promotes smaller and sparse weights: $\Omega(W) = \sum\limits_y ||w_y||_1$

# Non-Linear Classifiers

Ways to deal with non-linear data:
- Feature engineering - manually define non-linear features.
- Kernel methods - implicitly map data to a higher-dimensional space.
- Neural networks - learn non-linear features.

## KNN Classifier

- Don't train, just memorize the data.
- Predict $\hat{y}(x) = y_n$, where $n = \text{arg} \min\limits_i ||x - x_i|| $
- Disadvantage: Requires searching over the entire training data.

## Kernel Methods

- Similarity function $k : \Chi \times \Chi \rightarrow \mathbb{R}$, that is symmetric
and positive semi-definite.
- Given dataset $D = {(xn, yn)}^N_{n=1}$, we can define the Gram matrix $K \in \mathbb{R}^{N \times N}$, where $K_{ij} = k(x_i, x_j)$.
  - Symmetric - $K_{ij} = K_{ji}$;
  - Positive semi-definite - $v^TKv \geq 0$, $\forall v \in \mathbb{R}^N$.
- Mercer's theorem: a function $k$ is a valid kernel if and only if $K$ is a valid Gram matrix;
- Kernel trick: take a feature-based algorithm (e.g. linear regression) and replace the dot product $x^Tx'$ with a kernel $k(x, x')$. The resulting algorithm is a non-linear algorithm that operates in the feature space - many models can be kernelized.

There are several popular kernels:
- Polynomial kernel: $k(x, x') = (x^Tx' + c)^d$
- Gaussian radial basis function (RBF): $k(x, x') = \exp(-\dfrac{||x-x'||^2}{2\sigma^2})$
- String kernels.
- Tree kernels.