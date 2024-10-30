# Conflict sets

### Dead-end Definition

Um dead-end ocorre quando numa árvore se encontra um estado em que todos os valores de uma variável entram em conflito com a solução parcial encontrada até lá. Essa variável é também considerada uma dead-end variable.

<br>

# Backjumping styles

## Gashnig's backjumping

Neste algoritmo percorre-se a árvore até se atingir um ramo onde nenhum dos elementos do domínio consegue satisfazer as constraints em que essa variável está. Quando isso acontece volta-se a percorrer do inicio a ver as variáveis que causam a diminuição do domínio, a última é para onde se vai fazer o backjump.

### Pseudo-code

<img src="Imagens/Aula6 Gashnig1.png">

<img src="Imagens/Aula6 Gashnig2.png">

### Example

Neste exemplo, ao percorrer a árvore pelo quando chegamos a $x_7$ não há valores do domínio que satisfaçam as constraints $x_1$ != $x_7$ e $x_3$ != $x_7$. Como isto acontece vamos voltar a olhar para o inicio da árvore e ver quando o domínio é reduzido.<br>
A primeira diminuição do domínio de $x_7$ acontece por causa da escolha $x_1$ = red. Com esta decisão o $D_7$ = {blue}. Mais tarde, a escolha de $x_3$ = blue, vai também reuzir o domínio de $x_7$, esvaziando-o. Como a decisão em $x_3$ foi a última a diminuir o domínio de $x_7$, o $latest_7$ = 3. E é para $x_3$ que se faz o backjump. 

<img src="Imagens/Aula6 Gashnig example1.png">

<br>

## Graph-based backjumping

[Fonte](https://pdf.sciencedirectassets.com/271585/1-s2.0-S0004370200X00872/1-s2.0-S0004370202001200/main.pdf?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEDQaCXVzLWVhc3QtMSJIMEYCIQDEnC3WFgniEBCi%2Fv0iuFebY%2BrCbc13kU4nwqlqO4JBwAIhAK4UFYO53SXcAl17zXzGIb6Ljc0w9KJCQheerRLRESu1KrIFCHwQBRoMMDU5MDAzNTQ2ODY1IgwzZpjks7WW7M8zTF4qjwWKymDv0J%2BikXGzpBP6s32jFq7IaaunLrmGtntr0iYEYyXBys4sOvgBV2GLUYsSs%2FcwdJxcGxytuP27X0lgcD4oWCuxjDb8vFwLAkx%2B6Xrcu5ZYkkCnN4oa%2FZuqUpKYqPoktqlzhgZf1VV7lhgNe7Bc6dB5yC6CrDel0aHT1rqJFBd03z7LPNd7%2B7PiE7PKm4EUiUkZLX3iycCa8CdZYWftNt7tsAD1ijkHkyAxgf0jVsqD0JmQlLbvRR5OBLKm5ibJz3AWdN9pinS1UNzPM3fKeFbTOnEO8xLBx7i63xYGDxkS1IHqR7zvimzaUVySRDGKJv4wNdD3UgakzrFwe7jJmrd1Dj181zSUnrmWPTKEQpLkTPTe2Cnr4PILAoGgGsYsbVi8cdpMkDR9jKXGnPXg3lmRMSw1za9rDB%2FWxD%2BJo7c2tWxzhdyzcyG2AsodH1SlUGaSKK4xSG1v72yBiGmLCVFA5iWbSJv2Yj2VpHxbCEnZXiSNOBTXM3ftlmmkY665t7SpdIhZv1MN0LWSHegKhclJD4ahXUxfmYzg4eNI9JXhYI0h%2BEQoGE43phyoom3M7ymGasx%2BYDyQmg%2FK6ksFdzuewV8%2FCRVOxd%2FUgSZzE0MWUokFzgtr2INaAILesmQ91xmzNZTPdRaNGtDFhwfT2YDRfbSbWDxL469e8XGGZrVm2OXYUQZXICr8RUQ%2FnAq%2Fd%2Fnbtc2NyEH%2Bm7a4wK3Zwlkbrhiu9mtylAXUqiZONb7n6yXo9qJ1Vk4j3gGsPzPIRt4SzqmicGvY%2BiTCTh8X5h7SvGcBsss4BaNA4i%2Fw7PK5t1j8U72xs9t3fdLnT0SnYR6VSyXD7l1GrifUGS6AlV2AnFwlB7NiQrMD4uQqMK%2Fz67cGOrABnpYukfSrl6O%2BLlqHwiHDGos9U65nhhd89wLG4U9gEGY%2FRGsa03wfmYgTUReFYy8WOD8X1woXJIqlq1Il8y6AgVtVcqdX5Z%2FO8tpw80Uu%2F867EK%2F7ljxydhNDvAu2L5OXp9xIJvSDtK9tjN%2BK12WF%2FWlfjoAS%2BoQxEqxUfE2H2OMZrRlrtkkCbrx%2F7Ke5KxA0OMg79DipN%2FN3WDezVKSjIBRXfNAWp216j6D09%2FbLHD0%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20240930T203238Z&X-Amz-SignedHeaders=host&X-Amz-Expires=300&X-Amz-Credential=ASIAQ3PHCVTYZAQOFFPS%2F20240930%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=1af16b7708d77ccba685f077a091035e022d2eb394621cd0f6b58ed21c6accae&hash=c864e94b0f32d43c1af72dc158e5aef68371416feca2b98f5f1876d9f6ddb0b2&host=68042c943591013ac2b2430a89b270f6af2c76d8dfd086a07176afe7c76c2c61&pii=S0004370202001200&tid=spdf-d2293975-1b8c-4dd7-b5f4-df430c03e222&sid=6e5d56fd1f58e24a94294d45c9cd0fa76d03gxrqb&type=client&tsoh=d3d3LnNjaWVuY2VkaXJlY3QuY29t&ua=16125b03055405000056&rr=8cb6fbc0b8ee94ef&cc=pt) Página 14

### Ancestor Definition

Os ancestors de uma variável são as variáveis que a precedem e que estão conectadas a ela.

### Parent Definition

Last ancestor in the list of ancestors.

### Induced ancestors Definition

Induced ancestors de uma variável são apenas as variáveis que precedem e estão ligadas à variável em questão quando se faz o induced graph.

Para o graph-based backjumping, precisamos de usar induced ancestors. Se não o fizermos podemos ter situações em que não garantimos segurança ao fazer backjump. Como mostra a imagem:

<img src="Imagens/Aula6 graph-based problem.png">

<br>

### Explanation

Percorre-se a árvore até se encontrar um leaft dead-end. Geram-se os induced ancestors para essa variável. Faz-se backjump até ao induced parent dessa variável. Continua-se a procura se existirem valores não explorados para esse induced parent. Se já todos os valores tiverem sido explorados, no branch atual, remove-se essa variável da lista dos induced ancestors e faz-se backjump para o novo induced parent. Se não tiver valores por explorar, repete-se o processo.

### Example

Map coloring problem:

<img src="Imagens/Aula6 graph-based example2.png">

<img src="Imagens/Aula6 graph-based example1.png">

## Conflict-directed backjumping

### Explanation

Este algoritmo é basicamente uma mistura do Gashnig e do Graph-based backjumping. Percorre-se a árvore e quando aparecer um dead-end identificam-se as causas da diminuição do domínio, tal como no gashnig. E agora mantemos uma lista JUMP de variáveis que causaram a diminuição, caso o parent da variável não tenha mais valores por testar, usa-se o próximo parent, e assim por diante, tal como no graph-based.

### Example

Map coloring problem:

<img src="Imagens/Aula6 graph-based example2.png">

<img src="Imagens/Aula6 conflict-based example.png">