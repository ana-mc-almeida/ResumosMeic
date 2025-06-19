# Event-Based Style (aka Publish-Subscribe Style)

Overview: Components (publisher) place events on the bus by announcing them. The connector then delivers those events to the components that have registered an interest in those events (subscribers).

Elements:

- Any C&C component with at least one publish or subscribe port. Properties vary, but they should include which events are announced and/or subscribed to, and the conditions under which an announcer is blocked.
- Publish-subscribe connector, which will have announce and listen roles for components that wish to publish and/or subscribe to events.

Relations: Attachment relation associates components with the publish-subscribe connector by prescribing which components announce events and which components have registered to receive events.

Computational Model: Components subscribe to events. When an event is announced by a component, the connector dispatches the event to all subscribers.

Constraints:

- All components are connected to an event distributor that may be viewed as either a bus - that is, a connector - or a component. Publish ports are attached to announce roles, and subscribe ports are attached to listen roles. Constraints may restrict which components can listen to which events, whether a component can listen to its own events, and how many publish-subscribe connectors can exist within a system.
- A component may be both a publisher and a subscriber, by having ports of both types.

What It's For:

- Sending events to unknown recipients, isolating event producers from event consumers.
- Providing core functionality for GUI frameworks, mailing lists, bulletin boards, and social networks.

# Repository Style - Shared Data Style

Overview: In the shared-data style, the pattern of interaction is dominated by the exchange of persistent data. The data has multiple accessors and at least one shared-data store for retaining persistent data.

Elements:

- Repository component. Properties include types of data stored, data performance-oriented properties, data distribution, number of accessors permitted.
- Data accessor component.
- Data reading and writing connector. An important property is whether the connector is transactional or not.

Relations: Attachment relation determines which data accessors are connected to which data repositories.

Computational Model: Communication between data accessors is mediated by a shared-data store. Control may be initiated by the data accessors or the data store. Data is made persistent by the data store.

Constraints:

- Data accessors interact with the data store(s).

What It's For:

- Allowing multiple components to access persistent data.
- Providing enhanced modifiability by decoupling data producers from data consumers.
