# Representations

- Can make models more expressive and accurate.
- They may allow transferring representations from one task to another.

## Hierarchical Compositionality

- Deep NNs learn coarse-to-fine representation layers.
- Hierarchical compositionality is the idea that complex concepts are composed of simpler ones.
- Layer closer to inputs learn simple concepts - edges, corners, etc..
- Layer closer to outputs learn more abstract representations - shapes, forms, objects, etc.

## Distributed Representations

- Local representations (one-hot) - one dimension per object.
- Distributed representations - one dimension per property.

  - No single neuron encodes everything - groups of neurons work together.
  - More compact and powerful.
  - Hidden units should capture diverse properties of objects - not all capturing the same property - ensured by random initialization.
  - Initializing all units to the same weights would never break the symmetry.
  - Initializing hidden layers using unsupervised learning can help break the symmetry - force network to represent latent structure in the data. encourage hidden layers to encode useful features.

- A NN computes the same function if we permute the hidden units within a layer (order doesn't matter, only diversity)

<img src="Imagens/3 - local vs distributed.png">

## Auto-Encoders

Auto-encoders are feed-forward NNs trained to reproduce its input at its output layer. They are useful to learn good representations of data in an unsupervised manner, for example to capture a lower-dimensional manifold that approximately contains the data.

- Encoder - maps input to a hidden representation : $h = g(W x + b)$.
  - Commonly used in tasks where the input needs to be analyzed or transformed into a meaningful representation. For example: sentiment analysis and text classification.
  - Example: BERT (Bidirectional Encoder Representations from Transformers)
- Decoder - maps hidden representation to a reconstruction : $\hat{x} = W^T h(x)+c$ .
  - Used for tasks that require generative outputs (e.g., language generation, text completion).
  - Example: GPT (Generative Pre-trained Transformer)
- Encoder-Decoder - Combines both encoder and decoder.
  - Typically used for sequence-to-sequence tasks. For example: machine translation, summarization, question answering and image captioning.
  - Example: Transformer models for machine translation
- Loss function - $\mathcal{L} (\hat{x}, x) = \dfrac{1}{2}||\hat{x} - x||^2$
- Objective - $\hat{W} = \text{arg} \min\limits_W \sum\limits_i ||W^Tg(Wx_i)-x_i||^2$.

### Single Value Decomposition (SVD)

SVD is a matrix factorization method that decomposes a matrix $A \in \mathbb{R}^{m\times n} $ with $m \geq n$ into the product of three matrices $U$, $\Sigma$ and $V$ such that $A = U\Sigma V^T$.

- $U \in \mathbb{R}^{m\times m}$ - columns are an orthonormal basis of $R(A)$ (left singular values)
- $\Sigma \in \mathbb{R}^{m\times n} $ - diagonal matrix with singular values of A.
- $V \in \mathbb{R}^{n\times n}$ columns are an orthonormal basis of $R(A^T)$ (right singular vectors).
- $\sigma_1 \geq \dots \geq \sigma_r$ - square roots of the eigenvalues of $A^T A$ or $AA^T$
- $U^TU = I$ and $V^TV = I$

## Linear Auto-Encoder

- Let $X \in \mathbb{R}^{N \times D}$ be a data matrix with $N$ samples and $D$ features, where $N > D$.
- Assume $W \in \mathbb{R}^{K \times D}$, where $K < D$.
- We aim to minimize the reconstruction error: $\sum_{i=1}^N \| x_i - \hat{x}_i \|_2^2 = \| X - X W^T W \|_F^2$, where:

  - $\| \cdot \|_F$ denotes the Frobenius norm.
  - $W^T W$ has rank $K$.

- According to the Eckart-Young theorem, the solution to this optimization problem is the truncated Singular Value Decomposition (SVD) of $X^T$. Specifically:
  - $\hat{X}^T = U_K \Sigma_K V_K^T$, where:
    - $U_K$ contains the top $K$ left singular vectors of $X^T$.
    - $\Sigma_K$ is the diagonal matrix of the top $K$ singular values.
    - $V_K$ contains the top $K$ right singular vectors.
- The weight matrix is given by $W = U_K^T$.
- This process corresponds to Principal Component Analysis (PCA), which fits a linear manifold to the data.
- By introducing non-linear activations in the encoder or decoder, the auto-encoder can learn more complex representations (non-linear manifolds).

### Sparse Auto-Encoders

- Add a sparsity penalty $\ell_1$ to the loss function to encourage sparse representations in the hidden layer.
- Typically, the number of hidden units is larger than the number of input features.
- The sparsity penalty acts as a regularization term that limits the number of active neurons.

### Stochastic Auto-Encoders

- The encoder and decoder are stochastic, incorporating noise or randomness.
- The encoder uses a probabilistic distribution $p_{\text{encoder}}(h|x)$, and the decoder uses $p_{\text{decoder}}(x|h)$.
- Training involves minimizing the negative log-likelihood: $-\log \left( p_{\text{decoder}}(x|h) \right)$.

### Denoising Auto-Encoders

- The input is perturbed with random noise: $\tilde{x} = x + n$, where $n$ is the noise.
- The objective is to reconstruct the clean input $x$ from the noisy input $\tilde{x}$ by minimizing: $\frac{1}{2} \| \hat{x} - \tilde{x} \|_2^2$.
- This acts as an implicit regularization, promoting smooth representations by ensuring the model can handle small perturbations in the input.

### Stacked Auto-Encoders

- Multiple layers of auto-encoders are stacked together to learn hierarchical representations.

### Variational Auto-Encoders (VAEs)

A Variational Auto-Encoder (VAE) is a type of generative model that learns to map input data to a probabilistic latent space and reconstruct the data from that latent space. Unlike traditional autoencoders, VAEs use probability distributions (typically Gaussian) to model the latent variables, allowing for sampling and generation of new data. The model consists of two parts:

- Encoder: Maps input data to a distribution (mean and variance) in the latent space.
- Decoder: Reconstructs the input data from a sampled latent variable.

The VAE is trained to minimize two losses:

- Reconstruction loss: Measures how well the model reconstructs the input.
- KL divergence: Ensures the latent space distribution is close to a prior distribution (usually a standard normal distribution).

VAEs are widely used for tasks like data generation, anomaly detection, and learning compact representations of data. They allow smooth interpolation and generation of new data points.

## Regularized Auto-Encoders

- The goal is to minimize: $L(\hat{x}, x) + \Omega(h, x)$, where:
  - $L(\hat{x}, x)$ is the reconstruction loss.
  - $\Omega(h, x)$ is the regularization term.
- Examples of regularization:
  - Regularizing the code: $\Omega(h, x) = \lambda \| h \|_2^2$.
  - Regularizing the derivatives: $\Omega(h, x) = \lambda \sum_i \| \nabla_x h_i \|_2^2$.

## Unsupervised Pre-training

- Unsupervised pre-training is a technique for initializing the weights of a deep NN.
- A greedy, layer-wise procedure:
  - Train one layer at a time, from first to last, using unsupervised criterion (e.g. auto-encoder).
  - Fix the parameters of previous hidden layers.
  - Previous layers viewed as feature extractors.
- After pre-training, the whole network is fine-tuned using supervised learning - fine-tuning.
  - Performed as in a regular feed-forward NN - forward propagation, backpropagation and update of weights.

## Word Representations

- Learning representations of words in natural language is also called word embeddings.
- Distributional similarity - represent a word by means of its neighbors - you shall know a word by the company it keeps.
- The objective is to obtain a vector representation for each word in a vocabulary. there are two main approaches:
  - Factorization of a co-occurrence word-context matrix.
  - Directly predicting a word from its neighbors in a continuous word-space - word2vec.

### Neural Language Models

- Each word is associated with a word embedding - a vector of real numbers.
- Given the context (previous words), the next word is predicted.
- The word embeddings in the context window are concatenated into a vector that is fed to a neural network.
- The output of the NN is a probability distribution over the vocabulary - softmax.
- The network is trained by a SGD with backpropagation.

## Word2Vec

- Often, we are not concerned with language modeling, but with the quality of the word embeddings. We do not need to predict the probability of the next word, just make sure that the true word is more likely than a random one.
- Word2Vec is a shallow, two-layer NN that is trained to predict the current word from the context. it comes with two variants:
  - Continuous Bag-of-Words (CBOW) - predict the current word from the context.
  - Skip-gram - predict the context from the current word - more popular.

### Skip-Gram

- Objective: maximize the log probability of any context word given the central word:
  $$J(\theta) = \dfrac{1}{T} \sum\limits^T_{t=1} \sum\limits_{-m \leq j \leq m, j\neq 0} \log p_\theta(x_{t+j}|x_t)$$

- There are 2 sets of parameters (2 embedding matrices - $\theta = (u,v) $):

  - Embeddings for each word o appearing as the center word - $u_o$.
  - Embeddings for each word c appearing in the context of another word - $v_c$.

- Uses a log-bilinear model: $p_\theta(x_{t+j} = c | x_t= o) \propto \exp(u_o^Tv_c)$.
- Every word gets two vectors.
- In the end, we only care about the word vectors $u$, the context vectors $v$ are discarded.

### Large Vocabulary Problem

- The softmax is expensive to compute for large vocabularies, so there are some alternatives:
  - Stochastic sampling.
  - Noise contrastive estimation.
  - Negative sampling.

### Negative Sampling

- Replace the softmax by binary logistic regressions for a true pair (center word, context word) and k random pairs (center word, random word):

$$J_t(\theta) = \log \sigma(u_o^Tv_c) + \sum\limits^k_{i=1} \log \sigma(-u^t_ov_{j_i}), j_i \sim P(x)$$

- There are several strategies for sampling the random words.
- Negative sampling is a simple form of unsupervised pre-training.

## Linear Relationships

- Word embeddings are good at encoding dimensions of similarity.
- Word analogies can be solved well simply via subtraction in the embedding space.
- A simple way to visualize the word embeddings is to use PCA to project them into 2D.
- There are other methods for obtaining word embeddings:
- GloVe - Global Vectors for Word Representation.
- FastText - subword embeddings.
