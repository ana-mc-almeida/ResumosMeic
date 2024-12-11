## Functionalities

- Notifications: Product updates, etc
- Notifications format: SMS, push, email 
- Device types: iOS, Android and Laptop
- Actors: client applications, schedulers
- opt-out functionality
- actor: APNS, FCM, SMS service, email service
- Store device tokens
- Output - Payload
- Signup functionality
- Repository persistent (structure) device tokens, email, phone, notifications, settings, etc
- Notification API
- Verify user info in sign up

## Qualities

- Scalability (the same as modifiability)
- Performance: Normal operation & Peak load (during peak load limit event response)
- Performance: throughput: (10M push + 1M SMS + 5M emails) / per day -> It means I need to have 10x more resources for push than for SMS, to achieve same latency
- Modifiability: add and remove third party services during runtime. (this could also be integrability because it is about external systems)
- Modifiability: adding a new third party service during deisgn time.
- Availability: crash notification system.
- Availability: crash thrid party service -> (send at least one)
- Modifiability: increase in number of notifications per device type, increase in number of users
- Performance - bottlenecks: Payload generation, syncrhonous send of notifications over internet
- Security - spam

## Context

- Notification system
- User receives notifications
- Device types: iOS, Android and Laptop
- Actors: client applications
- Third--party services