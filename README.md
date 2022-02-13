# Integração das plataformas Apache Kafka e Apache Flink

- Neste projeto são mostrados exemplos de integração do flink com o kafka, sendo possível conectar um programa java usando dependencias do flink para intermediar mensagens de um tópico do Kafka, assim como fazer o intermédio de comunicação entre dois tópicos Kafka.

# O que é o Apache Kafka?
Apache Kafka é uma plataforma open-source de processamento de streams desenvolvida pela Apache Software Foundation,
escrita em Scala e Java. O projeto tem como objetivo fornecer uma plataforma unificada, de alta capacidade e baixa latência para tratamento de dados em tempo real.

# O que é o Apache Flink?

Apache Flink é uma estrutura de processamento em lote e stream unificada de código aberto desenvolvida pela Apache Software Foundation.  <br/>
O núcleo do Apache Flink é um mecanismo de fluxo de dados de streaming distribuído escrito em Java e Scala.

# O que precisa para rodar os exemplos?
- O kafka instalado na máquina com dois tópicos abertos
- Java 15

# Sobre o código
- Quando em execução a classe "Receptor" recebe os dados enviados pro tópico definido no código
- Quando em execução a classe "Remetente" envia dados no formato de String para o tópico definido
- Quando em execução a classe "Receptor_e_Remetente" recebe dados enviados pelo tópico definido, ler as informações através do tópico de input definido, executa um breve "evento" por assim dizer e retorna a mensagem "editada" para o tópico de output definido

# Tutorial
- https://docs.google.com/document/d/1dz-xP35K6VDTFQZ3FfqSLYUZdSPJ569cmUp1Kc3Tmko/edit
