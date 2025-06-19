

<!-- toc -->

- [Allocation Styles](#allocation-styles)
    + [Overview](#overview)
    + [Elements](#elements)
    + [Relations](#relations)
  * [Deployment Style](#deployment-style)
    + [Overview](#overview-1)
    + [Elements](#elements-1)
    + [Relations](#relations-1)
    + [Constraints](#constraints)
  * [Install Style](#install-style)
    + [Overview](#overview-2)
    + [Elements](#elements-2)
      - [Relations](#relations-2)
    + [Constraints](#constraints-1)
  * [Work Assignment Style](#work-assignment-style)
    + [Overview](#overview-3)
    + [Elements](#elements-3)
    + [Relations](#relations-3)
    + [Constraints](#constraints-2)

<!-- tocstop -->

# Allocation Styles

### Overview

Allocation styles describe the mapping between the software architecture and its environment.

### Elements

- Software element and environmental element. A software element has properties that are required of the environment. An environmental element has properties that are provided to the software.

### Relations

- Allocated-to. A software element is mapped (allocated to) an environmental element. Properties are dependent on the particular style.

## Deployment Style

### Overview

The deployment style describes the mapping of components and connectors in the software architecture to the hardware of the computing platform.

### Elements

- Software element: elements from a C&C view. Useful properties to document include the significant features required from hardware, such as processing, memory, capacity requirements, and fault tolerance.
- Environmental elements: hardware of the computing platform-processor, memory, disk, network (such as router, bandwidth, firewall, bridge), and so on. Useful properties of an environmental element are the significant hardware aspects that influence the allocation decision.

### Relations

- Allocated-to. Physical units on which the software elements reside during execution. Properties include whether the allocation can change at execution time or not.
- Migrates-to, copy-migrates-to, and/or executionmigrates-to if the allocation is dynamic. Properties include the trigger that causes the migration.

### Constraints

- The allocation topology is unrestricted. However, the required properties of the software must be satisfied by the provided properties of the hardware.

## Install Style

### Overview

The install style describes the mapping of components in the software architecture to a file system in the production environment.

### Elements

- Software element: a C&C component. Required properties of a software element, if any, usually include requirements on the production environments, such as a requirement to support Java or a database, or specific permissions on the file system.
- Environmental element: a configuration item, such as a file or a folder. Provided properties of an environmental element include indications of the characteristics provided by the production environments.

#### Relations

- Allocated-to. A component is allocated to a configuration item.
- Containment. One configuration item is contained in another.

### Constraints

- Files and folders are organized in a tree structure, following an is-contained-in relation.

## Work Assignment Style

### Overview

The work assignment style describes the mapping of the software architecture to the teams in the development organization.

### Elements

- Software element: a module. Properties include the required skill set and available capacity (effort, time) needed.
- Environmental element: an organizational unit, such as a person, a team, a department, a subcontractor, and so on. Properties include the provided skill set and the capacity in terms of labor and calendar time available.

### Relations

- Allocated-to. A software element is allocated to an organizational unit.

### Constraints

- In general, the allocation is unrestricted; in practice, it is usually restricted so that one module is allocated to one organizational unit.
