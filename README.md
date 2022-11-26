TAREFA 1 (80% da AV1):
Problema da fábrica de biscoitos:
Considere o diagrama apresentado na Figura 1. Nele é apresentado um esquema simplificado de funcionamento de uma fábrica de biscoitos. Nessa fábrica, estão presentes 3 linhas de produção, onde são fabricados diversos tipos de biscoitos, sejam eles doces ou salgados. Informações importantes:
 Cada linha é alimentada por uma fila de pedidos, nomeadas com as letras A, B e C;
 Os pedidos devem ser enviados para as linhas de produção de forma equilibrada, verificando o tamanho da fila de pedidos;
2
 Apenas as linhas A e B podem fazer biscoitos recheados. Biscoitos recheados têm um adicional de produção de 20% em seu tempo total;
 Uma vez na linha de produção, o pedido passa por três etapas de adição de ingredientes;
 A quantidade de ingredientes adicionados é proporcional ao tempo gasto no forno. Assim, um biscoito simples composto por 70 kg de ingrediente 1, 100 kg de ingrediente 2 e 30 kg de ingrediente 3, necessitará de 200∙𝑇 segundos no forno para finalizar sua concepção. Já um biscoito recheado com a mesma receita gastará 1,2∙200∙𝑇 segundos no forno. 𝑇 é uma constante de tempo definida no início da simulação;
 Os Fornos 1 e 2 podem apenas assar um pedido por vez, sendo que o primeiro pode assar pedidos das linhas A e B e o segundo das linhas B e C;
 Ao final deve ser gerado um relatório com o total de biscoitos produzidos, o tempo total gasto para a produção de cada pedido e a quantidade de ingredientes gastos.
Figura 1 - Diagrama representando a linha de produção da fábrica de biscoitos.
O que deve conter a solução:
 Interface gráfica simulando o processo, como a da figura anterior. Indique visualmente a fabricação em cada ponto da linha de produção. Usem a criatividade!
 Usar Herança, Polimorfismo, Threads (ou Runnables no lugar de Threads) e Exclusão Mútua;
 Interface amigável para a inserção de novos pedidos, permitindo a escolha entre biscoito simples ou recheado e a quantidade de ingredientes que será utilizada.
Lembrem-se de comentar o código e tratar corretamente todas as entradas de dados!
3
TAREFA 2 (20% da AV1):
Para o processo da Tarefa 1, proponha um sistema com 15 nós (sensores) em que todos os fluxos de materiais possam ser medidos (ex. massa total de ingredientes para a fabricação dos biscoitos) desde a entrada até a finalização dos biscoitos produzidos. Simule os valores medidos, gerando valores aleatórios (com pelo menos 500 leituras, equivalente a leituras em sequência por meio de um medidor de fluxo) a partir de uma função de distribuição normal (Gaussiana). Apresente esta distribuição graficamente. Calcule a média, desvio padrão, polarização (bias), precisão e incerteza. Considere uma perda de 30 % da massa total final do biscoito após serem assados nos fornos.
Demonstre (passo a passo) no relatório PDF a técnica de Reconciliação de Dados para conciliar as medições e apresente cada uma das matrizes encontradas, explicando cada passo necessário para obter a solução final. Como nem todos os fluxos são medidos em uma planta, devido a razões físicas ou económicas, exclua cinco dos fluxos propostos. Em seguida, demonstre a técnica de Reconciliação de Dados para conciliar as medições e estimar também as taxas de fluxo não medidas. Apresente cada uma das matrizes encontradas explicando cada passo necessário para obter a solução final. Classifique cada um dos fluxos como redundante ou não-redundante e explique claramente o resultado obtido na função QR, apresentando como elaborou a solução (cite a referência explícita sobre isso, documento e página, link, etc.).

Referencias:
[1] https://www.alura.com.br/conteudo/threads-java-1
