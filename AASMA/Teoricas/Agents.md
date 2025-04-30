# Agents

An agent is a computer system that is capable of independent (autonomous) action on behalf of its user or owner. More formally, an agent is a computer system capable of autonomous action in some environment in order to meet its design objectives.

We think of an agent as being in a closed-couple continual interaction with its environment: sense -> decide -> act -> sense -> decide -> act...

<img src="Imagens/Agent and Env.png">

A multiagent system is one that consists of a set of agents, which interact with one another. In the most general case, agents will be acting on behalf of users with different goals and motivations. To successfully interact, they will require the ability to cooperate, coordinate, and negotiate with each other, much as people do.

Agent design: How do we build agents?<br>
R: Agents that are independent, autonomous, and able to carry out tasks we delegate to them. Decision making is a key aspect!

Society design: How do we build agents that are capable of interacting?<br>
R: Cooperating, coordinating, negotiating with other agents

These are the micro and macro perspectives

## Properties

- Reactive: A reactive system is one that maintains an ongoing interaction with its environment, and responds to changes that occur in it (in time for the response to be useful)
- Proactiveness: Generating and attempting to achieve goals. Not driven solely by events from the environment. Taking the initiative. Recognizing opportunities.
  - Reactiveness and Proactiveness can be at odds with one another. Designing an agent that can balance the two remains an open research problem.
- Social: The ability to interact with other agents (and possibly humans) through coordination, cooperation, negotiation and modeling others.
  - Coordination is managing the interdependencies between activities.
  - Cooperation is working together as a team to achieve a shared goal.
  - Negotiation is the ability to reach agreements on matter of common interest.
- Autonomy: agent's ability to act independently and thus determine how to achieve its delegated goals/tasks.
- Adaptivity: agent's ability to learn from experience (to better interact with the particular environment).
- Rationality: agent's ability to act in a way that maximizes some utility function.
- Curiosity: agent's ability to engage creative imaginative or inquisitive reasoning.
- Believability: agent's ability to create a suspension of disbelief, temporarily leading a user to accept the agent as an alive or a real character.
- Mobility: agent's ability to change its location in the environment, being it the physical world (robots), or the virtual world.

Sensors: define the perceptors for the agent perceive the world.<br>
Effectors: define the actuators for the agent perform in the world

## Environment Properties

- Accessibility: agent can obtain complete, accurate, up-to-date data about the environment's state. Most moderately complex environments are inaccessible.
- Deterministic: action has a single guaranteed effect. Physical world is for us (humans) non-deterministic.
- Static: world does not change while the agent is deliberating.
- Discrete: fixed, finite number of possible actions and percepts.
- Episodic: world can be divided in a series of intervals (episodes) independent of each other.
