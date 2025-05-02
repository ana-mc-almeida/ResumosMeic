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

## Abstract Architectures

Environment states: (finite) set of (discrete) states 
- $E = \{e_0, e_1, ...\}$

Actions: set of actions which transform the environment's state 
- $Ac = \{\alpha_0, \alpha_1, ...\}$

Run: finite sequence of interleaved states and actions

- $r: e_0 \xrightarrow{\alpha_0} e_1 \xrightarrow{\alpha_1} e_2 \xrightarrow{\alpha_2} ... \xrightarrow{\alpha_{n - 1}} e_n $

Let:

- $R$ be the set of all such possible runs (over $E$ and $Ac$) where $r$ and $r'$ are members of $R$.
- $R^{Ac}$ is the subset of these that end with an action.
- $R^E$ is the subset of these that end with an environment state.

A state transformer (environment changes): Maps a run (ending in an action) to a set of possible environment states
- $\tau : R^{Ac} \rightarrow \wp(E)$
- History dependent
- Non-determinism
- if $\tau(r) = \varnothing$, there are no possible successor states to $r$ (system has ended its run)

An environment can now be fully defined as a triple $Env = <E,e_0,\tau>$ where:
- $E$ is a set of environment states
- $e_0 \in E$ is the initial state
- $\tau$ is a state transformer function

Agent is a function which maps runs to actions: An agent makes a decision (i.e., action to perform) based on the history of system that it has witnessed to date (i.e., $R^E$)
- $Ag:R^E\rightarrow Ac$

$AG$ is the set of all agents, $Ag \in AG$

We denote the set of runs of agent $Ag$ in Environment $Env$ by $R(Ag, Env)$

## Deductive reasoning agents

Agent as a knowledge-based system. This paradigm is known as symbolic AI.

Two key problems to be solved:
1. Transduction problem: translating real-world environment into an accurate, adequate symbolic description. 
2. Representation/reasoning problem: symbolically representing information, how to get agents to reason with this information

Deductive reasoning agent (architecture) is one that contains an explicit symbolic representation of the world and an internal state given by formulae (predicate logic).
- internal state may include incorrect/outdated info
- makes decisions via symbolic reasoning - proving theorems without breaking axioms on what is possible

Agent decision:
- $D$ (internal state = set of formulae or database)
- $see : S \rightarrow Per$ (observe the environment), $Per$ = Percept
- $next: D \times Pert\rightarrow D$ (update internal state)
- $action: D \rightarrow Ac$ (decision making with deduction rules)

<img src="Imagens/Dedutive agent decision.png">

An agent can decide what to do using theorem proving by using logic to encode a theory stating the best action to perform in a given situation.

Let:
- $\rho$ be this theory (typically deduction rules)
- $DB$ be the logical data (database) describing current state of the world
- $Ac$ be the set of actions the agent can perform
- $DB\vdash_\rho\phi$ means that formula $\phi$ can be proved from database DB using deduction rules $\rho$

Agent's action selection function (i.e., $action: D \rightarrow Ac$) is defined in terms of its deduction rules.

```
function action(DB:D) returns an action Ac
  begin
  /* for each action, attempts to prove Do(a) from its database using deduction rules */
  for each a : Ac do
    if Do(a) then 
      return a
    end if
  end for

  /* attempts to find an action such that !Do(a) cannot be derived (i.e., not explicitly forbidden) */
  for each a : Ac do
    if !Do(a) == false then 
      return a
    end if
  end for

  return null /* no action found */
end function
```

### Advantages

- Agent's decision making strategy is encoded as logical theory.
- Agent's action reduces to a problem of proof.
- Logic-based approach are elegant and have (clean) logical semantics.

### Disadvantages:

- Inherent computational complexity of theorem proving.
- Cannot operate effectively in time-constrained environments.
- The environment cannot change while the agent is making a decision.
- Not easy to represent and reason about complex and dynamic environments.

## Agents as Intentional Systems

Intentional stance: Develop agent behaviors in terms of mental states (beliefs, desires, wishes, hopes, ...)
- "Michael took his umbrella because he believed it was going to rain."
- "John worked hard because he wanted to obtain a PhD."

Practical Reasoning = Deliberation + Means-Ends Reasoning
- Deliberation: deciding what state of affairs an agent wants to achieve from (possibly conflicting) desires.
- Means-Ends Reasoning: deciding how an agent wants to achieve these states of
affairs.

The B.D.I model: an architecture for intelligent agents based on the mental attitudes of beliefs, desires and intentions.
- Beliefs: Information about the environment, other agents, and itself
- Desires: Desires/goals are state of affairs to achieve.
- Intentions: Commitments to achieving particular goals.

### Deliberation

- Belief revision function: Update beliefs with sensory input and previous belief.
  - $brf: 2^{Bel} \times Per \rightarrow 2^{Bel}$
- Function to generate options: Use beliefs and existing intentions to generate options (= desires).
  - $options: 2^{Bel} \times 2^{Int} \rightarrow 2^{Des}$
- Filtering function: Choose between competing alternatives and commit to their achievement.
  - $filter: 2^{Bel} \times 2^{Des} \times 2^{Int} \rightarrow 2^{Int}$

### Means-ends reasoning 
- $plan: 2^{Bel} \times 2^{Int} \times 2^{Ac} \rightarrow Plan$

Decision-making is a loop:
1. Observe the world and update beliefs
2. Deliberate to decide the intention(s) -> determine available options -> filter
3. Use means-ends reasoning to find a plan for the intention(s)
4. Execute the plan
5. Return to 1

### Commitment strategies

- Blind commitment: Maintains an intention until it believes the intention has been achieved.
- Single-minded commitment: Maintains an intention until it believes that either the intention has been achieved or is no longer possible to achieve.
- Open-minded commitment: Maintains an intention as long as it has not been achieved and it is still desired.

## Reactive agents

Rejection of symbolic representation and syntactic manipulation (e.g., logic programming).

The idea that intelligent behavior is linked to the environment. Intelligent behavior can emerge from the interaction of various simpler behaviors. Agents equipped with simple processing units that perceive and quickly react to changes in
the environment. Do not use complex symbolic reasoning.

In reactive agent systems, intelligence is not a property of a single component. The intelligence is distributed in the system and emerges from the interaction among agent components and the environment.

### Purely reactive agents

Purely reactive agents make no reference to their history. They keep no internal state and the decision making depends entirely on the present.

Formally: $Ag: E \rightarrow Ac$

```
function decide(perception)
  current_state <- INTERPRET-INPUT(perception)
  rule <- RULE-MATCH(current_state,rules)
  action <- RULE-ACTION(rule)
  return action
```

Simple behaviors of each individual agent generates complex behaviors when combining individual behaviors.

### Brooks: subsumption architecture

Decision-making:
- A set of task-accomplishing behaviors.
- Each behavior module can be seen as an action selection function.
  - Perceptual inputs are mapped into actions.
  - Each behavior module is intended to perform a task.

Behavior modules are finite-state machines:

<img src="Imagens/Behavior Module Statet Machine.png">

However, many behaviors can fire simultaneously. In a subsumption hierarchy behaviors are arranged into layers and lower layers inhibit higher layers.

<img src="Imagens/Subsumption Layers.png">

### Agent development using Brooks' architecture

1. Create a module for a particular task that should link perception and action and should work by itself.
2. Add more modules. The priorities for the behaviors need to be re-adjusted every time one module is added.

Requirements:
- Deal with multiple goals.
- Deal with multiple sensors that may provide conflicting data.
- Be robust in dealing with changes in the environment.
- Deal with time constraints.

Advantages:
- Simplicity.
- Economy.
- Computational tractability.
- Robustness against failure
- Elegance.
- Extensibility.

Limitations:
- Decisions are only based on local information: agents have a short-term view.
- Agents need sufficient local information to make decisions.
- No learning: how to guarantee reactive rules evolve?
- Not trivial to engineer agents with complex behavior.
- Not easy to predict complex behavior when agents have a high number of layers.

## Hybrid Agents

Many researchers argue that neither a completely deliberative nor completely reactive approach is suitable. A Hybrid system should be used instead.

Requirements: 
- Agent must have both reactive and deliberative behaviors.
- Often, the reactive subsystem is given some kind of precedence over the deliberative subsystem.
- This kind of structuring leads naturally to the idea of a layered architecture.

A key problem in hybrid architectures: what kind of control flow should we consider between the agent's subsystems?
- Horizontal layering: Layers are directly connected to the sensory input and action output. Each layer itself acts like an agent, producing suggestions on what action to perform.
  - Advantages: simple, distributed, fault-tolerant.
  - Disadvantages: global behavior may not be coherent, difficult to avoid conflict between layers.
- Vertical layering: Sensory input and action output are each dealt with by at most one layer each.

<img src="Imagens/Horizontal and Vertical layering.png">

### Advantages

- One of the most used architectures currently.
- Allows for a real-time response combined with goal oriented-behavior.
- Reactivity can be privileged in relation to deliberation.
- Knowledge about the world can be subdivided into layers.
- Different levels of abstraction.

### Disadvantages

- Vertical layering: bottlenecks and fault intolerance.
- Horizontal layering: complexity of decision making.
- Interactions between layers are difficult to program and to test.
  - One will need to analyze all the possible interactions between these layers.
  - An integration problem.