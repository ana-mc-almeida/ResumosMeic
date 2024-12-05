# News Feed System

### Functionalities

Publish Post:
- Actors: An authentitcated user.
- Input: Post containing text.
- Output: Post is persistently stored and all friends are notified.

Retrieve new feed:
- Actors: An authentitcated user.
- Output: Most recent posts published by friens in reverse chronological order.

### Qualities

Availability:
- Stimulus: News feed system fails
- Artifact: News feed system
- Environment: Normal operation
- Response: Keep providing service
- Response measure: 99.99% availability 

Security:
- #1
  - Source of stimulus: Any user, authenticated or not.
  - Stimulus: Denial-of-Service Attack - Submit large number of posts in a small period of time.
  - Environment: Normal operation
  - Artifact: News feed system
  - Response: Continues to provide service to non-attackers.
  - Response measure: 100% availability
- #2
  - Source of stimulus: Any user, authenticated or not.
  - Stimulus: Submit post impersonating someone else.
  - Environment: Normal operation
  - Artifact: News feed system
  - Response: Refuse submission. Notify operators
  - Response measure: No integrity compromise.
- #3
  - Source of stimulus: Any user, authenticated or not.
  - Stimulus: Try to read something from a user who is not friend.
  - Environment: Normal operation
  - Artifact: News feed system
  - Response: Refuse read
  - Response measure: No confidentiality compromise.

Performance:
- #1
  - Source of stimulus: Authenticated users.
  - Stimulus: Retrieve news feed periodically 10^4 / second.
  - Environment: Normal operation.
  - Artifact: News feed system.
  - Response: News are retrieved.
  - Response measure: Latency of ~500ms, TP = 10^4 news / second.
- #2
  - Source of stimulus: Authenticated users.
  - Stimulus: Retrieve news feed stocastic distribution.
  - Environment: Peak load.
  - Artifact: News feed system.
  - Response: News are retrieved.
  - Response measure: Latency of ~1s, TP = 20^4 news / second.
- #3
  - Source of stimulus: Authenticated users.
  - Stimulus: Publish post.
  - Environment: Normal operation.
  - Artifact: News feed system.
  - Response: News are retrieved.
  - Response measure: Latency of 3-4s

## Rounds

### Round 1

- Purpose: Fully functional system and 
- Arquitectural significant requirements: F1, F2, Security1, Security2 (related with authentication), constraint about web and mobile clients.

Iteration 1:
- Goal: Support functionalities
  - Decompose news feed system in 2 submodules: Post and Retrival
  - Data model for User: Has posts, has friends that are users and has a feed.
  - Add a componenet&connector that connects a web server to a web client and a mobile app. Connect the web server to the database.

Iteration 2:
- Goal: Support authentication
  - Add a new authentication model as a submodel of news feed system module.
  - Authentication server:
    - Option 1: Add a property to web server to handle authentication requests.
    - Option 2: Use external authentication.

### Round 2

- Purpose: Support performance during normal operation
- Arquitectural significant requirements: Performance1, Performance2.

Iteration 1:
- Goal: Support retrieval
  - Change the database to multiple key-store value tables. To make things fast, they should be in caches.