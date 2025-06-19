# Deep Generative Models

Deep Generative Models (DGMs) are a class of models in machine learning that learn to generate new data samples from the same distribution as the training data. These models are typically used for generating images, videos, text, or other complex data types. Unlike discriminative models, which are designed to classify or predict specific outputs based on input data, generative models aim to model the underlying distribution of the data. They have applications in fields such as image generation, text generation, and data augmentation.

In essence, DGMs try to learn how the data is generated, so they can create new samples that resemble the training data.

- Latent Variable Models: DGMs often introduce latent variables (unobserved random variables) to model the underlying structure of the data. These latent variables capture the hidden factors that generate the observed data. The model then learns how to generate data from these latent variables.
- Generative Process: DGMs typically assume there is a probabilistic process that generates data. The model learns this process in reverse: instead of trying to classify data, it learns how to generate new data from random noise or some hidden representation.
- Training Objective: In general, DGMs are trained by maximizing the likelihood of the observed data under the model, meaning they are trained to produce data samples that are as close as possible to real data.
  - Supervised learning: $p(x, y)$, while unsupervised learning: $p(x)$;
  - Generative models use latent (random) variables $h$ such that: $P(x) = \sum\limits_h P(x, h)$

## Restricted Boltzmann Machines (RBMs) (or harmoniums)

Energy based model over binary vectors. Consists of two layers: a visible layer (representing the input data) and a hidden layer (capturing underlying features). These layers are connected with weights, but there are no connections within the same layer.

The model is trained using a method called contrastive divergence, where the goal is to adjust the weights so that the model's distribution matches the data distribution. This is done by alternating between two phases: a positive phase (activating hidden units based on visible units) and a negative phase (reconstructing the visible units and updating hidden units).

RBMs can be used for:

- Dimensionality reduction (compressing data into a lower-dimensional form).
- Collaborative filtering (recommender systems).
- Pretraining deep networks (to initialize weights before fine-tuning with backpropagation).
- Generating new samples similar to training data.

In relation to normal Boltzmann Machines

- Layer Connections: In RBMs, there are no connections within a layer. The visible layer (input) is connected only to the hidden layer (features), and vice versa. This restriction simplifies training and makes the model easier to learn.
- Training: The lack of intra-layer connections makes the Gibbs sampling process more efficient, allowing for faster and more stable training using methods like contrastive divergence.
- Usage: RBMs are mainly used for unsupervised learning, dimensionality reduction, and as building blocks in deep belief networks (DBNs) for pretraining.

## Generative Adversarial Networks (GANs)

Used for generating new data instances that resemble a given training dataset. A GAN consists of two main components:

- Generator: A neural network that generates synthetic data (like images) from random noise. Its goal is to create data that looks as realistic as possible.
- Discriminator: A neural network that distinguishes between real data (from the training set) and fake data (produced by the generator). It provides feedback to the generator, guiding it to improve the quality of its outputs.

These two networks are trained together in a minimax game:

- The generator tries to fool the discriminator by creating more realistic fake data.
- The discriminator tries to become better at distinguishing real data from fake data.

This adversarial process results in the generator improving over time, eventually producing very realistic synthetic data. The training objective is for the generator to generate data so realistic that the discriminator cannot tell the difference between real and fake data.
