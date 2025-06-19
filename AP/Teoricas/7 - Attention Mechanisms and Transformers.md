

<!-- toc -->

- [Convolutional Encoder-Decoder](#convolutional-encoder-decoder)
- [Self-Attention and Transformer Networks](#self-attention-and-transformer-networks)
    + [Attention Mechanism](#attention-mechanism)
    + [Self-Attention](#self-attention)
    + [Masked Attention](#masked-attention)
- [Transformer](#transformer)
- [Multi-Head Attention](#multi-head-attention)
- [Positional Encoding](#positional-encoding)
- [Example Exercises](#example-exercises)
    + [Problem 1](#problem-1)
    + [Solution](#solution)

<!-- tocstop -->

# Convolutional Encoder-Decoder

- Drawbacks of Recurrent Neural Networks:
  - Sequential computation prohibits parallelization.
  - Long-term dependencies are hard to learn.
- A possible solution is to replace the RNN encoder with a hierarchical 1D CNN - Convolutional Encoder-Decoder.
  - Encoder is parallelizable, but decoder still requires sequential computation - the model is still auto-regressive.

<img src="Imagens/7 - Computational Cost.png">

# Self-Attention and Transformer Networks

Self-attention encoders are better than RNN encoders because they can better capture long-range relations between elements of the sequence, since information does not propagate sequentially (words can be compared with a constant number of operations). This usually reflects in more accurate models. Also, the training is usually faster, since the self-attention computation can be parallelized, unlike RNNs, that require sequential computation.

- We want NNs that automatically weight the relevant parts of the input, so we use attention mechanisms.
  - Performance gain.
  - None or few parameters.
  - Fast - parallelizable.
  - Tool for interpreting predictions.

### Attention Mechanism

1. We have a query vector $q$ and input keys (vectors) $H = [h_1, h_2, \dots , h_L]^T$

- Input vectors appear in two roles: keys and values.
  - Keys are used to compute the attention scores.
  - Values are used to compute the weighted average.

2. We compute affinity scores $s_1, s_2, \dots , s_L$ between $q$ and $h_i$ - there are
   several ways of comparing $q$ and $h_i$

- Additive attention: $s_i = w^T \text{tanh}(Ah_i+Bq) $
- Biliner attention: $s_i = q^TUh_i $
- Dot product attention: $s_i = q^Th_i$ - but queries and keys must have the same size.

3. We convert these scores to probabilities: $p = \text{softmax}(s)$.
4. We use this to output a representation as a weighted average: $c = H^Tp=\sum\limits^L\_{i=1}p_ih_i $

### Self-Attention

- At each position, the encoder attends to the other positions in the encoder itself - same for the decoder.
- Self-attention for a sequence of length $L$:
  - Query vectors: $Q = XW_Q = [q_1, q_2, \dots , q_L]^T$
  - Key vectors: $K = XW_K = [k_1, k_2, \dots , k_L]^T$
  - Value vectors: $V = XW_V = [v_1, v_2, \dots , v_L]^T$

1. Compute affinity scores $S = QK^T$
2. Convert these scores to probabilities: $P = \text{softmax}\Big(\dfrac{S}{\sqrt{d_k}}\Big)$. $\sqrt{d_k}$ is used to to stabilize gradients when $d_k$ (dimensionality of key vectors) is large.
3. Output the weighted average of the value: $Z = P V$

### Masked Attention

Refers to the practice of preventing certain tokens or positions in a sequence from attending to specific other tokens. This is done by masking certain values in the attention mechanism to zero (or some other value), ensuring that these positions cannot influence the computations for specific tokens during the model's forward pass.

# Transformer

- Transformer is a neural network architecture that uses self-attention in the encoder instead of RNNs/CNNs.
- Each word state attends to all the other words.
- Each self-attention is followed by a feed-forward transformation.
- Do several layers of this.
- Do the same for the decoder, attending only to already generated words.

# Multi-Head Attention

- Multi-head attention is a variant of self-attention that allows the model to jointly attend to information from different representation subspaces at different positions.
- Define $h$ attention heads.
- Apply attention in multiple channels, concatenate the outputs and pipe through linear layer: $\text{MultiHead}(X) = \text{Concat}(Z_1, \dots , Z_h)W^O$, where $Z_i = \text{Attention}(XW^Q_i, XW^K_i, XW^V_i)$

The outputs of multi-head attention are concatenated through a feed-forward neural network whose weights are learned during training. Multi-attention heads allow for attending to parts of the sequence differently (e.g. longer-term dependencies versus shorterterm dependencies), the network then learns also the weights to attribute to each attention output, thus encoding more information than what a single attention head would provide.

# Positional Encoding

- Positional encoding is a technique used to inject information about the relative or absolute position of tokens in a sequence.

# Example Exercises

### Problem 1

Suppose we use a (very simple) transformer (in fact, only a single selfattention layer with a single attention head; no feedforward layers and no residual connections) as the language model. The word embeddings are:

$$
\boldsymbol{x}_{\langle\text{START}\rangle} = \begin{bmatrix} 0 & 0 & 0 \end{bmatrix}^T, \quad
\boldsymbol{x}_{\text{Awful}} = \begin{bmatrix} 0 & -5 & 0 \end{bmatrix}^T, \quad
\boldsymbol{x}_{\text{plot}} = \begin{bmatrix} 1 & 0 & 0 \end{bmatrix}^T, \quad
\boldsymbol{x}_{\text{and}} = \begin{bmatrix} 0 & 0 & 0 \end{bmatrix}^T,
$$

$$
\boldsymbol{x}_{\text{actors.}} = \begin{bmatrix} -1 & 0 & 0 \end{bmatrix}^T, \quad
\boldsymbol{x}_{\text{The}} = \begin{bmatrix} 0 & 0 & 0 \end{bmatrix}^T, \quad
\boldsymbol{x}_{\text{movie}} = \begin{bmatrix} -2 & 0 & 0 \end{bmatrix}^T, \quad
\boldsymbol{x}_{\text{is}} = \begin{bmatrix} 0 & 0 & 0 \end{bmatrix}^T,
$$

$$
\boldsymbol{x}_{\text{good}} = \begin{bmatrix} 1 & 2 & 3 \end{bmatrix}^T, \quad
\boldsymbol{x}_{\text{bad}} = \begin{bmatrix} -1 & -2 & -3 \end{bmatrix}^T
$$

The positional embeddings are:

$$
\bm{p}_1 = \begin{bmatrix} 0 \\ 0 \\ 0 \end{bmatrix}, \quad
\bm{p}_2 = \begin{bmatrix} 0 \\ 0 \\ 1 \end{bmatrix}, \quad
\bm{p}_3 = \begin{bmatrix} 0 \\ 0 \\ 2 \end{bmatrix}, \quad \ldots, \quad
\bm{p}_t = \begin{bmatrix} 0 \\ 0 \\ t-1 \end{bmatrix}.
$$

The projection matrices are:

$$
\bm{W}_Q = \bm{W}_K = \bm{W}_V =
\begin{bmatrix}
0.1 & 0 \\
0 & -0.1 \\
0 & 0.1
\end{bmatrix}.
$$

Scaled dot product attention is used to compute the attention probabilities. The linear output layer uses the same matrix $\boldsymbol{W}_{yh}$ as above. What will be the prediction? Show all calculations. (Hint: notice that you do not need to compute the full attention probability matrix, only the attention probabilities associated to the last word.)

### Solution

By summing the word and positional encodings, we obtain the embedding matrix:

$$
\bm{X} =
\begin{bmatrix}
0 & 0 & 0 \\
0 & -5 & 1 \\
1 & 0 & 2 \\
0 & 0 & 3 \\
-1 & 0 & 4 \\
0 & 0 & 5 \\
-2 & 0 & 6 \\
0 & 0 & 7
\end{bmatrix}.
$$

The query, key, and value matrices are:

$$
\bm{Q} = \bm{K} = \bm{V} = \bm{X} \bm{W}_Q =
\begin{bmatrix}
0 & 0 \\
0 & 0.6 \\
0.1 & 0.2 \\
0 & 0.3 \\
-0.1 & 0.4 \\
0 & 0.5 \\
-0.2 & 0.6 \\
0 & 0.7
\end{bmatrix}.
$$

For the last token, the attention scores are:

$$
\bm{s}_8^\top = \frac{1}{\sqrt{2}} \bm{q}_8^\top \bm{K}^\top = \frac{1}{\sqrt{2}}
\begin{bmatrix}
0 & 0.7
\end{bmatrix}
\begin{bmatrix}
0 & 0 & 0.1 & 0 & -0.1 & 0 & -0.2 & 0 \\
0 & 0.6 & 0.2 & 0.3 & 0.4 & 0.5 & 0.6 & 0.7
\end{bmatrix}.
$$

This gives:

$$
\bm{s}_8^\top = \frac{1}{\sqrt{2}}
\begin{bmatrix}
0 & 0.42 & 0.14 & 0.21 & 0.28 & 0.35 & 0.42 & 0.49
\end{bmatrix}.
$$

The attention probability vector is:

$$
\bm{p}_8^\top = \begin{bmatrix}
0.101 & 0.136 & 0.112 & 0.118 & 0.124 & 0.130 & 0.136 & 0.143
\end{bmatrix}.
$$

The representation for the last token after self-attention is:

$$
\bm{z}_8^\top = \bm{p}_8^\top \bm{V} = \begin{bmatrix} -0.028 \\ 0.436 \end{bmatrix}.
$$

The logit for "good" is:

$$
[\bm{W}_{yh} \bm{z}_8]_{\text{good}} = (-0.028) \cdot 1 + 0.436 \cdot (-1) = -0.464.
$$

The logit for "bad" is:

$$
[\bm{W}_{yh} \bm{z}_8]_{\text{bad}} = (-0.028) \cdot (-1) + 0.436 \cdot 1 = 0.464.
$$

Since \( -0.464 < 0.464 \), the system predicts a negative sentiment $(-)$.
