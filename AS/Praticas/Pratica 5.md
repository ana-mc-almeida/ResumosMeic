

<!-- toc -->

- [Unique ID Generator](#unique-id-generator)
    + [Multi-master replication](#multi-master-replication)
    + [UUID](#uuid)
    + [Ticket Server](#ticket-server)
    + [Twitter Snowflake](#twitter-snowflake)
- [URL Shortener](#url-shortener)
  * [API Endpoints (REST-style)](#api-endpoints-rest-style)
    + [301 vs 302 Redirect](#301-vs-302-redirect)
  * [Hash function](#hash-function)
    + [Hash + collision resolution](#hash--collision-resolution)
    + [Base 62 convertion](#base-62-convertion)

<!-- tocstop -->

# Unique ID Generator

### Multi-master replication

Utiliza-se sharding para se guardar informação. Gera-se o unique id utilizando o _auto_increment_ das databases. O valor aumenta pelo número de databases. Ou seja Se houverem 2 datbases, o gerador de ids da primeira vai gerar: 1, 3, 5, ...

Desvantagens:

- Não é escalável facilmente
- Não é comparável por tempo

### UUID

Cada servidor gera o seu próprio UUID. Isto oferece performance e retira todos os problemas de sincronização. Escala facilmente porque cada servidor só depende de si para gerar UUIDs.

Desvantagens:

- UUIDs têm 128 bits.
- UUIDs não são comparáveis por tempo.
- UUIDs não são numericos apenas.

### Ticket Server

Existe um servidor que incrementa os ids e vai distribuindo a outros servidores quando lhe pedem. Oferece ids numericos, é fácil de fazer e funciona bem para pequenas e médias aplicações.

Desvantagens:

- É um single point of failure.
- É dificil de escalar porque envolve sincronização.

### Twitter Snowflake

Divide-se o ID em várias secções:

- Sign bit: 1 bit -> Fica a 0 e pode ser usado para distinguir entre numeros positivos e negativos.
- Timestamp: 41 bits -> Millisecods desde uma certa data.
- Datacenter id: 5 bits -> 5^32 = 32 datacenters
- Machine id: 5 bits -> 5^32 = 32 maquinas por datacenter
- Sequence Number: 12 bits -> Aumenta em 1 a cada id gerado. O número volta a 0 a cada milisegundo. 2^12 = 4096 ids por segundo por máquina.

# URL Shortener

## API Endpoints (REST-style)

1. URL Shortening: POST

- Request parameter: longURL
- Return shortURL

2. Redirecting: GET

- Return longURL

### 301 vs 302 Redirect

301 é um redirect permanente. Quando um cliente voltar a tentar usar o short URL vai ser redirecionado diretamente para o long URL sem passar pelo serviço de URL Shortener. É vantagoso para performance, porque não sobrecarrega o sistema com pedidos.

302 é um temporário. Vaia passar sempre pelo URL Shortener para obter o long URL. É vantagoso para obter informações estatisticas, por exemplo quantas vezes se carregou no short URL.

## Hash function

Precisamos de mapear o long URL baseado num short URL, para isso usamos uma hash function. O HashValue é composto por [A-Z,a-z,0-9], 10 + 26 + 26 = 62 carateres possiveis.

### Hash + collision resolution

Podemos usar algumas hashfunctions conhecidas como CRC32, MD5, SHA-1. Mas isto vai gerar hash values demasiado grandes.

### Base 62 convertion

Converte um numero para um numero de base 62. Por exemplo 11157 = 2TX. No entanto, isto faz com que seja preciso depender de um unique id generator, mas evitar collisions.
