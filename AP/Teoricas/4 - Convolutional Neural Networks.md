

<!-- toc -->

  * [Image Size, Filter Size, Stride, Channels](#image-size-filter-size-stride-channels)
  * [Convolutions](#convolutions)
    + [Pooling Layer](#pooling-layer)
  * [Residual Networks (ResNets)](#residual-networks-resnets)
- [Example Exercise](#example-exercise)
    + [Problem 1:](#problem-1)
    + [Solution:](#solution)
    + [Problem 2:](#problem-2)
    + [Solution](#solution)

<!-- tocstop -->

Convolutional neural networks (CNNs) are a class of deep neural networks that are specialized for processing data that has a grid-like topology, such as images.

- In a fully-connected layer, each neuron is connected to every neuron in the previous layer - all activations depend on all inputs;
- In a convolutional layer, each neuron is connected to only a local region of the previous layer - local connectivity;
- Filters are always extended along the full depth of the input volume - convolve the filter with the image (slide over the image and compute dot products);
- Convolving a filter with an image produces an activation map.

## Image Size, Filter Size, Stride, Channels

- Stride: shift in pixels between two consecutive windows;
- Number of channels - number of filters used in each layer;
- Given an $N \times N \times D$ image, a $F \times F \times D$ filters, and stride $S$, the resulting output will be of size $M \times M \times K$, where:
  - $M = \dfrac{N-F}{S}+1 $
  - $K$ is the number of filters.
- The number of parameters will be the filter size + 1 bias. If the filter is $2 \times 2$, the number of parameters will be $2 \times 2 + 1 = 5$.

## Convolutions

- The convolution of a signal $x$ and a filter $w$ is defined as $(x * w): h[t] = (x * w)[t] = \sum\limits^\infty_{a=-\infty} x[t-a]w[a]$
- Leads to translation/shift equivariance;
- The second component of CNNs is pooling - reduces the size of the representation, which makes the network more efficient and reduces the number of parameters;
- CNNs alternate between convolutional layers and pooling layers (provide invariance);

Notes:

- Equivariance is a property of a function that preserves some property of the input in the output.
- Invariance is a property of a function that does not preserve some property of the input in the output.

### Pooling Layer

- Makes the representations smaller and more manageable;
- Operates over each activation map independently;
- Max pooling - take the maximum value in each window;

## Residual Networks (ResNets)

Residual networks work by learning the residual function $F(x) = H(x) - x$ instead of directly learning the target function $H(x)$. This reformulation changes the learning task to finding the "difference" between the input $x$ and the desired output $H(x)$.

- They use skip connections - passing the input of the function to the output.
- Improves gradient flow, avoiding vanishing gradient problem.

- With $H(x) = \mathcal{F}(x) + \lambda x$, the gradient backpropagation becomes:

$$\dfrac{\partial L}{\partial x} = \dfrac{\partial L}{\partial H} \dfrac{\partial H}{\partial x} = \dfrac{\partial L}{\partial H} \Big(\dfrac{\partial \mathcal{F}}{\partial x} + \lambda\Big)$$

# Example Exercise

### Problem 1:

Consider a convolutional layer with a $3 \times 3$ filter and a ReLU nonlinearity, where stride = 1 and padding = 1. Suppose that, after training, the parameters of the filter are

$$\boldsymbol{K} = \begin{bmatrix} 1 & 0 & -1 \\ 2 & 0 & -2 \\ 1 & 0 & -1 \end{bmatrix}, \qquad b = 0,$$

where $b$ is the bias term.

Suppose that the layer receives, as input, an image which can be represented as

$$\boldsymbol{x} = \begin{bmatrix} 0 & 0 & 1 & 0 \\ 0 & 1 & 0 & 0 \\ 0 & 1 & 0 & 0 \\ 0 & 1 & 0 & 0 \end{bmatrix}$$

Compute the output $\boldsymbol{h}$ of the convolutional layer when the input is x. Indicate all relevant computations.

### Solution:

Apply padding:

$$x_{padded} = \begin{bmatrix} 0 & 0 & 0 & 0 & 0 & 0 \\ 0 & 0 & 0 & 1 & 0 & 0 \\ 0 & 0 & 1 & 0 & 0 & 0 \\ 0 & 0 & 1 & 0 & 0 & 0 \\ 0 & 0 & 1 & 0 & 0 & 0 \\ 0 & 0 & 0 & 0 & 0 & 0 \end{bmatrix}$$

Compute convolution with $\boldsymbol{K}$ (each cell is the sum of cellwise multiplication of $\boldsymbol{K}$ and the selected $3\times 3$ square in $x\_{padded} $)

$$z = \begin{bmatrix} 0 & 0 & 0 & 0 & 0 & 0 \\ 0 & 0 & 0 & 1 & 0 & 0 \\ 0 & 0 & 1 & 0 & 0 & 0 \\ 0 & 0 & 1 & 0 & 0 & 0 \\ 0 & 0 & 1 & 0 & 0 & 0 \\ 0 & 0 & 0 & 0 & 0 & 0 \end{bmatrix} * \begin{bmatrix} 1 & 0 & -1 \\ 2 & 0 & -2 \\ 1 & 0 & -1 \end{bmatrix} = \begin{bmatrix} -1 & -2 & 1 & 2 \\ -3 & -1 & 3 & 1 \\ -4 & 0 & 4 & 0 \\ -3 & 0 & 3 & 0 \end{bmatrix}$$

Applying ReLU:

$$\boldsymbol{h} = \text{ReLU}(\boldsymbol{x}) = \begin{bmatrix} 0 & 0 & 1 & 2 \\ 0 & 0 & 3 & 1 \\ 0 & 0 & 4 & 0 \\ 0 & 0 & 3 & 0 \end{bmatrix}$$

### Problem 2:

Suppose now that the convolutional layer is followed by a $2 \times 2$ max-pool layer, with a stride of $2$ and no padding. Compute the output of the max-pool layer when the input to the convolutional layer is the image in problem 1.

### Solution

$$\boldsymbol{h} = \begin{bmatrix} 0 & 0 & 1 & 2 \\ 0 & 0 & 3 & 1 \\ 0 & 0 & 4 & 0 \\ 0 & 0 & 3 & 0 \end{bmatrix}$$

Applying max-polling (max of each $2\times 2$ section):

$$z = \begin{bmatrix} 0 & 3 \\ 0 & 4 \end{bmatrix}$$
