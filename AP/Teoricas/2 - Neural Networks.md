### Hyperbolic Tangent

$\text{tanh}(z) = \dfrac{e^z - e^{-z}}{e^z + e^{-z}}$

$\sigma(z) = \dfrac{1 + \text{tanh}(z/2)}{2}$

### Universal Approximation Theorem

An NN with one hidden layer and a linear output can approximate arbitrarily well any continuous function, given enough hidden units.

The number of linear regions carved out by a deep neural network with $D$ inputs, depth $L$, and $K$ hidden units per layer with ReLU activations is $O\Bigg(\begin{pmatrix} K \\ D \end{pmatrix}^{D(L-1)}K^D\Bigg)$

## Backpropagation

### Gradient output layer:

$$\dfrac{\partial L(y, \hat{y})}{\partial z^{[L]}}$$

### Gradient hidden layer parameters:

$$\dfrac{\partial L(y, \hat{y})}{\partial W^{[l]}} = \dfrac{\partial L(y, \hat{y})}{\partial z^{[l]}} h^{[l-1]^T}$$

$$\dfrac{\partial L(y, \hat{y})}{\partial b^{[l]}} = \dfrac{\partial L(y, \hat{y})}{\partial z^{[l]}}$$

### Gradient hidden layer below:

$$\dfrac{\partial L(y, \hat{y})}{\partial h^{[l]}} = W^{[l+1]^T}\dfrac{\partial L(y, \hat{y})}{\partial z^{[l+1]}}$$

### Gradient hidden layer below before activation $g$:

$$\dfrac{\partial L(y, \hat{y})}{\partial z^{[l]}} = \dfrac{\partial L(y, \hat{y})}{\partial h^{[l]}} \odot g'(z^{[l]}) $$

<img src="Imagens/T4 Backpropagation.png">

## Autodiff

### Strategies

Symbol-to-number:
- Take a computational graph and numerical inputs, returns a set of numerical values describing the gradient at those input values.
- Advantage: simpler to implement and debug.
- Disadvantage: only works for first-order derivatives

Symbol-to-symbol differentiation:
- Take a computational graph and add additional nodes to the graph that provide a symbolic description of the desired derivatives (i.e. the derivatives are just another computational graph).
- Advantage: generalizes automatically to higher-order derivatives
- Disadvantage: harder to implement and to debug

## Regularization

We want to minize:

$\mathcal{L}(\theta) = \lambda\Omega(\theta) + \dfrac{1}{N}\sum\limits^N_{n=1}L(f(x_i.\theta),y_i)$

### $\ell_2$ regularization:

$\Omega(\theta) = \dfrac{1}{2} \sum\limits_{\ell=1}^{L+1} ||W^{(\ell)}||^2_2$

### $\ell_1$ regularization:

$\Omega(\theta) = \sum\limits_{\ell} ||W^{(\ell)}||_1$

### Dropout regularization

There is a probability $p$ of changing the return of the preceptron to 0.

## Dataset

- Training set: serves to train the model.
- Validation set: used to tune hyperparameters (learning rate, number of hidden units, regularization coefficient, dropout probability, best epoch, etc.).
- Test set: used to estimate the generalization performance.

## Hyperparameter Tuning

- Grid search: specify a set of values to test for each hyperparameter, and try all configurations of these values.
- Random search: specify a distribution over the values of each hyper-parameter (e.g. uniform in some range) and sample independently each hyper-parameter to get configurations.
- Bayesian optimization (Snoek et al., 2012).

## Normalization

- Subtract the mean and divide by the standard deviation.
- It makes each input dimension have zero mean and unit variance.
- It speeds up the training.
- Does not work for sparse data.

## Decaying the Learning Rate

- Learning rate $\ell$ is a hyperparameter that controls the step size in the gradient descent algorithm.
- In SGD, as we get closer to the minimum, we want to reduce the step size:
  - Start with a large learning rate (e.g. 0.1).
  - Keep it fixed while the validation error is decreasing.
  - Divide by 2 and repeat.

## Mini-Batches

- Instead of updating after a single sample, update after a mini-batch of samples (e.g. 50-200 samples), and compute the average gradient for the entire mini-batch.
- Less noisy than SGD.
- Can leverage matrix-matrix computations.

## Gradient Checking

- To debug, we can compute the numerical gradient and compare it with the analytical gradient:

$$\dfrac{\partial f}{\partial x} \approx \dfrac{f(x+\epsilon) - f(x-\epsilon)}{2\epsilon} $$

## Momentum

- Momentum is a technique that accelerates gradient descent in the relevant direction and dampens oscillations.
- It means: remember the previous gradients and use them to update the current gradient: 
  - $\theta_t = \theta_{t-1} - \alpha_t g(\theta_{t-1}) + \gamma_t(\theta_{t-1} - \theta_{t-2}) $

  - $g(\theta_t)$ is the gradient estimate at time $t$.
- Advantages: reduces the update in irrelevant directions and accelerates the update in relevant directions.

## Adaptive Gradient (AdaGrad)

- AdaGrad is a technique that adapts the learning rate to the parameters, performing smaller updates for frequent parameters and larger updates for infrequent parameters.
- Scale the update of each component ($\epsilon$ for numerical stability): 
  - $\theta_{j,t} = \theta_{j,t-1} - \dfrac{\alpha}{\sqrt{G_{j,t-1}+\epsilon}}g_j(\theta_{t-1}) $
  - $G_{j,t} $ is the sum of squares of the gradients w.r.t. $\theta_j$ up to time $t$: $G_{j_t} = \sum\limits^t_{i=1} g^2_{j,i} $
- Advantages: robust to choice of $\alpha$ and learning rate decay.
- Disadvantages: step size vanishes, because $G_{j,t}$ is monotonically increasing.

## Root Mean Square Propagation (RMSProp)

- RMSProp addresses the vanishing learning issue.
- Same scaled update for each component: $\theta_{j,t} = \theta_{j,t-1} - \dfrac{\alpha}{\sqrt{G_{j,t-1}+\epsilon}}g_j(\theta_{t-1}) $
- Use a forgetting factor $\gamma$ to decay the sum of squares of the gradients (typically $\gamma$ = 0.9): $G_{j,t} = \gamma G_{j,t-1} + (1 - \gamma)g^2_{j,t} $ - now the sum of
squares of the gradients is decaying.

## Adaptive Moment Estimation (Adam)

- Adam is a combination of momentum and RMSProp.
- Separate moving averages of gradient and squared gradient.
- Bias correction is used to initialize the moving averages to zero.
- Hyperparameters: $\alpha_1, \beta_1, \beta_2, \epsilon$
- Advantages: computationally efficient, low memory requirements, well suited for problems with large datasets and/or parameters.
- Disadvantages: possible convergence issues and noisy gradient estimates.