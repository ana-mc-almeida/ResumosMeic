# 1

|        | $t_0$ | $t_1$ | $t_2$ | $t_3$ |
| ------ | ----- | ----- | ----- | ----- |
| happy  | 1     | 1     | 0     | 0     |
| zombie | 1     | 0     | 1     | 0     |
| room   | 1     | 1     | 1     | 1     |
| flower | 0     | 0     | 0     | 1     |

# 2

CosSim($t_0$, $t_1$) = $\dfrac{(1, 1, 0, 0) \cdot (1, 0, 1, 0)}{\sqrt{1^2 + 1^2 + 1^2} \cdot \sqrt{1^2 + 1^2}} = \dfrac{1 + 1}{\sqrt{3} \cdot \sqrt{2}} = \dfrac{2}{\sqrt{6}} \approx 0.82 $

CosSim($t_0$, $t_2$) = $\dfrac{2}{\sqrt{6}} \approx 0.82$

CosSim($t_0$, $t_3$) = $\dfrac{1}{\sqrt{6}} \approx 0.41$

# 3

tf-idf(zombie, $t_0$, D) = tf(zombie, $t_0$) $\cdot$ idf(zombie, D) = 1 $\cdot ln(\dfrac{4}{2})$ = 0.69

tf-idf(room, $t_0$, D) = tf(room, $t_0$) $\cdot$ idf(room, D) = 1 $\cdot ln(\dfrac{4}{4}) = 0$

# 4

Como o tf-idf de room é zero, quer dizer que é um termo irrelevante para classificar os livros porque aparece em todos os documentos. Por outro lado, o termo zombie é melhor para isso e talvez seja melhor para classificar.
