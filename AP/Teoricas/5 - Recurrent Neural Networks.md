Recurrent Neural Networks (RNNs) are a class of neural networks that process sequences of inputs by maintaining a hidden state that depends on the previous inputs and outputs.

- Much of the data we want to process is sequential (e.g. time series, sentences, videos, audio):

  - $h_t = g(Vx_t + Uh_{t-1} + c)$
  - $\hat{y}\_t = Wh_t + b $
  - $x_t$ is the input at time step $t$.
  - $h_t$ is the hidden state at time step $t$ - it encodes the history of the sequence up to time step $t$.
  - $y_t$ is the output at time step $t$.
  - $V$, $U$, $W$ are weight matrices
  - $c$, $b$ are bias vectors

- Backpropagation can be used to compute the gradients of the loss function with respect to the parameters - chain rule.
- Parameters are shared across time steps - backpropagation through time - BPTT:

$$\dfrac{\partial L}{\partial U} = \sum\limits^T_{t=1} \dfrac{\partial L}{\partial \hat{y}_t} \dfrac{\partial \hat{y}_t}{\partial h_t} \dfrac{\partial h_t}{\partial U} $$

Bidirectional RNNs combine a left-to-right RNN with a right-to-left RNN. After propagating the states in both directions, the states of the two RNNs are concatenated. Bidirectional RNNs are used as sequence encoders, their main advantage is that each state contains contextual information coming from both sides. Unlike unidirectional RNNs, they have the same focus on the beginning and on the end of the input sequence.

## Standard Applications of RNNs

- Sequence Generation: generate a sequence of outputs given a sequence of inputs. e.g. language modeling.
- Sequence Tagging: generate a sequence of outputs given a sequence of inputs. e.g. part-of-speech tagging.
- Pooled Classification: generate a single output given a sequence of inputs by pooling the hidden states. e.g. text classification.

## Sequence Generation

- Generate a sequence of outputs given a sequence of inputs. e.g. language modeling.
- The full history model: $p(y*1, \dots, y_L) = \prod\limits^{L+1}*{t=1} p(y*t|y*{t-1},\dots, y_0) $ - generates each word depending on all previous words - thats huge expressive power, but there are too many parameters to train.
- Markov models avoid this problem by using limited memory: $p(y_t|y_{t-1},\dots, y_0) \approx p(y_t|y_{t-1},\dots, y_{t-m}) $ - an order-$m$ Markov model.
- Another alternative is to consider all the history, but compress it into a fixed-length vector - recurrent neural networks.

Auto-Regressive Models

- Auto-regressive models are a class of models that predict a sequence of outputs by conditioning on previous outputs.
- Key idea: feed the previous output as input to the next step: $x_t = $ embedding of $y_{t-1}$.
- Maintain a state vector $h_t$ that encodes the history of the sequence up to time step $t$ - compresses all the history: $h_t = f(x_t, h_{t-1})$.
- To compute the next probability distribution: $p(y*t=i|y*{t-1},\dots,y_0) = \text{softmax}(Wh_t+b) $
  - To generate text, $y_t$ is a word in a large vocabulary - softmax is expensive.

<img src="Imagens/5 - Auto-Regression.png">

Algorithms are needed for:

- Sampling sequences from the probability distribution - easy.
  - Compute $h_t$ from $h_{t_1} and $x_t$.
  - Sample $y_t$ from $\text{softmax}(W h_t + b)$.
  - Repeat until the end-of-sequence token is generated.
- Obtaining the most probable sequence - hard.
  - Find the sequence $y_1, \dots , y_L$ that maximizes $(\text{softmax}(Wh_1+b))_{y_1} \times \dots \times (\text{softmax}(Wh_L+b))_{y_L}$
  - Picking the best $y_t$ greedily does not work.
  - This is important for conditional language modeling.
- Training the RNN (i.e. learning the parameters $W$, $U$, $V$, $b$, $c$).

  - Usually maximum likelihood estimation is used.
  - In other words, minimize the negative log-likelihood of the training data - cross-entropy loss:

  $$\mathcal{L}(\Theta, y_1, \dots, y_L) = -\dfrac{1}{L+1}\sum\limits^{L+1}_{t=1} \log p(y_t|y_{t-1},\dots, y_0), \text{ where } \Theta = \{W,U,V,b,c\}$$

  - This is equivalent to minimizing perplexity: $\exp(\mathcal{L}(\Theta, y_1, \dots , y_L))$.

## Sequence Tagging

- Input: a sequence of words $x_1, \dots, x_L$.
- Goal: assign a tag to each element of the sequence, yielding and output sequence $y_1, \dots , y_L$.
- Examples: POS tagging, named entity recognition, etc.
- Different from sequence generation because the input and the output are distinct (no need for auto regression), and the length of the output is known.
- POS tagging maps a sequence of words to a sequence of POS (part-of-speech) tags. example:
  - Input: "I am a student".
  - Output: "PRON VERB DET NOUN".
  - Can be implemented using RNNs, and improved by using bidirectional RNNs - two RNNs are used, one forward and one backward.

## Pooled Classification

- Predict a single label for a sequence of inputs.
- Pool the RNN hidden states into a fixed-length vector and use a single softmax to output the final label.
- There are some pooling strategies:
  - Last hidden state: $h_L$ - simple, but losses the history.
  - Average pooling.
  - Use a bidirectional RNN and combine both last states.
  - ...

## GRUs and LSTMs - The Vanishing Gradient Problem

- As we go back in time, the gradient decreases exponentially.

$$\prod\limits_t \dfrac{\partial h_t}{\partial h_{t-1}} = \prod\limits_t \dfrac{\partial h_t}{\partial z_t} \dfrac{\partial z_t}{\partial h_{t-1}} = \prod\limits_t \text{Diag}(g'(z_t))U $$

Three cases:

- Eigenvalues of U are all greater than 1: exploding gradients.
  - Dealt with by gradient clipping - truncate the gradient if it is too large.
- Eigenvalues of U are all smaller than 1: vanishing gradients.
  - Long-term dependencies are hard to learn.
  - Solutions:
    - Better optimizers.
    - Normalization to keep the gradient norms stable across time.
    - Clever initialization to start with good spectra.
    - Alternative parameterizations: GRUs and LSTMs - instead of multiplying across time, we want the error to be approximately constant across time.
- Eigenvalues of U are exactly 1: gradient propagation stable.

### Gradient Clipping

- If the gradient norm is larger than a threshold, rescale it to the threshold:
  $$\tilde{\nabla} \leftarrow \begin{cases} \dfrac{\nabla}{||\nabla||} \times \text{threshold} & \text{if } ||\nabla|| > threshold \\ \nabla & \text{otherwise} \end{cases} $$
- Element-wise clipping is also possible: $\tilde{\nabla}\_i \leftarrow \min \text{threshold} |\nabla_i| \times \text{sign}(\nabla_i) $ .

### GRUs - Gated Recurrent Units

GRUs address the problem of vanishing gradients, which makes it difficult for traditional RNNs to learn long-term dependencies. The key difference between GRUs and vanilla RNNs is that GRUs use gates to control the flow of information.

- The error must backpropagate through all the intermediate states.
- Create some shortcuts to skip some of the intermediate states - adaptive shortcuts controlled by gates.
- $h*t = u_t \odot \tilde{h_t} + (1 - u_t) \odot h*{t-1} $, where:
  - $u_t$ is the update gate - controls how much of the previous state is kept: $u*t = \sigma(V_ux_t+U_uh*{t-1}+b_u) $.
  - $\tilde{h}_t $ is the candidate state - the new state $\tilde{h}\_t = g(Vx_t+U(r_t \odot h_{t-1})+b) $.
    - $r_t$ is the reset gate - controls how much of the previous state is forgotten: $r*t =\sigma(V_rx_t+U_rh*{t-1}+b_r) $.

### LSTMs - Long Short-Term Memory

Long Short-Term Memory (LSTM) networks are a type of recurrent neural network (RNN) designed to address the limitations of traditional RNNs, particularly in preventing the vanishing gradient problem. They do it by using memory cells (propagated additively) and gating functions that control how much information is propagated from the previous state to the current and how much input influences the curreent state.

- Memory cells $c_t$ store information for long periods of time.
- To avoid the multiplicative effect, we use addition instead of multiplication.
- Control the flow with special gates: input, forget and output gates.
- $c*t = f_t \odot c*{t-1} + i*t \odot g(Vx_t+Uh*{t-1}+b) $, where:
  - $f_t$ is the forget gate - $f*t = \sigma(V_fx_t+U_fh*{t-1}+b_f) $
  - $i_t$ is the input gate - controls how much of the candidate state is added to the memory cell: $i*t = \sigma(V_ix_t+U_ih*{t-1}+b_i) $
  - $g$ is the candidate state - the new state.

## Recursive Neural Networks

- Recursive neural networks are a class of neural networks that process trees of inputs by maintaining a hidden state that depends on the previous inputs and outputs.
- Assume a binary tree structure.
- Propagate states bottom-up in the tree, computing the parent state $p$ from the children states $c_1$ and $c_2$: $p = \text{tanh}(W[c_1; c_2] + b)$.
- Use the same parameters at all nodes.
- Tree-LSTM is a variant of LSTM that uses tree structures instead of sequences.

## Pixel RNNs

- Pixel RNNs are a class of neural networks that process images by maintaining a hidden state that depends on the previous inputs and outputs.
- They can be used as auto-regressive models for image generation.

## Tricks of the Trade

- Depth in recurrent layers helps in practice (2-8 layers seem to be standard).
- Input connections may or may not be used.
- Apply dropout between layers, but not on recurrent connections.
- For speed:
  - Use diagonal matrices instead of full matrices.
  - Concatenate parameter matrices for all gates and do a single matrixvector multiplication.
  - Use optimized implementations.
  - Use GRUs or reduced-gate LSTMs.
- For learning speed and performance:
  - Initialize so that the bias on the forget gate is large.
  - Use random orthogonal matrices to initialize the square matrices.

# Example Exercises

### Problem 1:

Consider a sentiment classification problem where the goal is to predict if a movie review is
positive ($+$) or negative ($-$). The following prompt template is used:

[X] The movie is [Z]

where the slot [X] is filled with the text of the review and [Z] is a slot from which the sentiment prediction can be generated. Let $t$ be the time-step corresponding to the slot [Z], and let $w_1, \dots, w_t$ the words of the sequence after filling the prompt template above. The sentiment prediction $\hat{y} \in \{+1, -1\} $ is computed according to the rule:

$$\hat{y} = \begin{cases} +1 & P(w_t = "\text{good}" | w_1, \dots, w_{t-1}) \geq P(w_t = "\text{bad}" | w_1, \dots, w_{t-1}) \\ -1 & \text{otherwise} \end{cases}  $$

<img src="Imagens/5 - Problem 1.png">

Suppose that the movie review is "Awful plot and actors." and that we use an
autoregressive RNN model as the language model. The word embeddings are

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

and the parameters of the RNN are

$$
\boldsymbol{W}_{hx} = \begin{bmatrix} 1 & 1 & 0 \\ 0 & -1 & -1 \end{bmatrix}, \quad
\boldsymbol{W}_{hh} = \begin{bmatrix} 1 & 0 \\ 0 & 1 \end{bmatrix},
\boldsymbol{W}_{yh} = \begin{bmatrix} 0 & 0 \\ 1 & 2 \\ 3 & 4 \\ 5 & 6 \\ 7 & 8 \\ 9 & 10 \\ 11 & 12 \\ 13 & 14 \\ 1 & -1 \\ -1 & 1 \end{bmatrix} \begin{array}{l} \#\langle\text{START}\rangle \\ \#\text{Awful} \\ \#\text{plot} \\ \#\text{and} \\ \#\text{actors.} \\ \#\text{The} \\ \#\text{movie} \\ \#\text{is} \\ \#\text{good} \\ \#\text{bad} \end{array}.
$$

All biases are zero. The initial RNN state is $h_0 = [0, 0]^T$ and the activation function for the RNN is the ReLU function. What will be the prediction? Show all calculations.

### Solution:

$$
\boldsymbol{h}_1 = \text{ReLU}(\boldsymbol{W}_{hx}\boldsymbol{x}_{\langle \text{start} \rangle} + \boldsymbol{W}_{hh}\boldsymbol{h}_0) =
\text{ReLU}\begin{pmatrix}
\begin{bmatrix} 0 \\ 0 \end{bmatrix}
+
\begin{bmatrix} 0 \\ 0 \end{bmatrix}
\end{pmatrix}
=
\begin{bmatrix} 0 \\ 0 \end{bmatrix}.
$$

$$
\boldsymbol{h}_2 = \text{ReLU}(\boldsymbol{W}_{hx}\boldsymbol{x}_{\text{Awful}} + \boldsymbol{W}_{hh}\boldsymbol{h}_1) =
\text{ReLU}\begin{pmatrix}
\begin{bmatrix} -5 \\ 5 \end{bmatrix}
+
\begin{bmatrix} 0 \\ 0 \end{bmatrix}
\end{pmatrix}
=
\begin{bmatrix} 0 \\ 5 \end{bmatrix}.
$$

$$
\boldsymbol{h}_3 = \text{ReLU}(\boldsymbol{W}_{hx}\boldsymbol{x}_{\text{plot}} + \boldsymbol{W}_{hh}\boldsymbol{h}_2) =
\text{ReLU}\begin{pmatrix}
\begin{bmatrix} 1 \\ 0 \end{bmatrix}
+
\begin{bmatrix} 0 \\ 5 \end{bmatrix}
\end{pmatrix}
=
\begin{bmatrix} 1 \\ 5 \end{bmatrix}.
$$

$$
\boldsymbol{h}_4 = \text{ReLU}(\boldsymbol{W}_{hx}\boldsymbol{x}_{\text{and}} + \boldsymbol{W}_{hh}\boldsymbol{h}_3) =
\text{ReLU}\begin{pmatrix}
\begin{bmatrix} 0 \\ 0 \end{bmatrix}
+
\begin{bmatrix} 1 \\ 5 \end{bmatrix}
\end{pmatrix}
=
\begin{bmatrix} 1 \\ 5 \end{bmatrix}.
$$

$$
\boldsymbol{h}_5 = \text{ReLU}(\boldsymbol{W}_{hx}\boldsymbol{x}_{\text{actors.}} + \boldsymbol{W}_{hh}\boldsymbol{h}_4) =
\text{ReLU}\begin{pmatrix}
\begin{bmatrix} -1 \\ 0 \end{bmatrix}
+
\begin{bmatrix} 1 \\ 5 \end{bmatrix}
\end{pmatrix}
=
\begin{bmatrix} 0 \\ 5 \end{bmatrix}.
$$

$$
\boldsymbol{h}_6 = \text{ReLU}(\boldsymbol{W}_{hx}\boldsymbol{x}_{\text{The}} + \boldsymbol{W}_{hh}\boldsymbol{h}_5) =
\text{ReLU}\begin{pmatrix}
\begin{bmatrix} 0 \\ 0 \end{bmatrix}
+
\begin{bmatrix} 0 \\ 5 \end{bmatrix}
\end{pmatrix}
=
\begin{bmatrix} 0 \\ 5 \end{bmatrix}.
$$

$$
\boldsymbol{h}_7 = \text{ReLU}(\boldsymbol{W}_{hx}\boldsymbol{x}_{\text{movie}} + \boldsymbol{W}_{hh}\boldsymbol{h}_6) =
\text{ReLU}\begin{pmatrix}
\begin{bmatrix} -2 \\ 0 \end{bmatrix}
+
\begin{bmatrix} 0 \\ 5 \end{bmatrix}
\end{pmatrix}
=
\begin{bmatrix} 0 \\ 5 \end{bmatrix}.
$$

$$
\boldsymbol{h}_8 = \text{ReLU}(\boldsymbol{W}_{hx}\boldsymbol{x}_{\text{is}} + \boldsymbol{W}_{hh}\boldsymbol{h}_7) =
\text{ReLU}\begin{pmatrix}
\begin{bmatrix} 0 \\ 0 \end{bmatrix}
+
\begin{bmatrix} 0 \\ 5 \end{bmatrix}
\end{pmatrix}
=
\begin{bmatrix} 0 \\ 5 \end{bmatrix}.
$$

$$\hat{\boldsymbol{y}}_\text{good} = \boldsymbol{W}_{yh_\text{good}}\boldsymbol{h}_8 = 0 \times 1 + 5 \times (-1) = -5$$

$$\hat{\boldsymbol{y}}_\text{bad} = \boldsymbol{W}_{yh_\text{bad}}\boldsymbol{h}_8 = 0 \times (-1) + 5 \times 1 = 5$$

The system will predict a negative sentiment $(-1)$.
