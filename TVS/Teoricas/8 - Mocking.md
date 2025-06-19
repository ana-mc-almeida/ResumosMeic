

<!-- toc -->

- [Mocking](#mocking)

<!-- tocstop -->

# Mocking

- Dummy objects: are passed around but never actually used.
- Fake objects: actually have working implementations, but usually take some shortcut.
- Stubs: provide canned answers to calls made during the test. Control over the indirect inputs.
  <img src="Imagens/8 - stubs.png">
- Spies: are stubs that also record some information based on how they were called.
  <img src="Imagens/8 - spies.png">
- Mocks: are pre-programmed with expectations which form a specification of the calls they are expected to receive.
  <img src="Imagens/8 - mock.png">
