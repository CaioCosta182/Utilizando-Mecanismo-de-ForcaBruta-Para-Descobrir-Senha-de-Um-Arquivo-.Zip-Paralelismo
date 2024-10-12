# Teste de Força Bruta para Arquivo ZIP com Senha

Este projeto é uma aplicação Java para testar senhas em arquivos ZIP protegidos, utilizando força bruta e processamento paralelo. A aplicação tenta descobrir a senha do arquivo ZIP ao testar diferentes combinações de caracteres ASCII.

## Tecnologias Utilizadas

- **Java**
- **Biblioteca Zip4j**: para manipulação e extração de arquivos ZIP
- **Multithreading**: usando a API de `ExecutorService` do Java para paralelizar o processo de teste de senhas

## Funcionalidades

- **Teste de Senhas por Força Bruta**: Tenta encontrar a senha de arquivos ZIP protegidos gerando e testando combinações de caracteres.
- **Processamento Paralelo**: Utiliza múltiplas threads para acelerar o processo de teste.
- **Validação de Diretório**: Verifica se o diretório e o arquivo ZIP existem antes de iniciar o processo.

## Estrutura do Código

- `testaSenha(String senha)`: Testa se a senha fornecida pode desbloquear o arquivo ZIP.
- `forcaBruta()`: Método principal que realiza a tentativa de quebra de senha usando força bruta.
- `gerarSenhas(...)`: Gera todas as combinações de caracteres dentro do intervalo ASCII fornecido e tenta desbloquear o arquivo.
- `main(String[] args)`: O ponto de entrada do programa que verifica o diretório e inicia a execução da força bruta.

## Como Executar

1. Certifique-se de que o arquivo ZIP (`doc1.zip`) esteja localizado no diretório especificado no código (`caminho`).
   
2. Compile e execute o código Java. Por exemplo:
   ```bash
   javac -cp zip4j-2.6.4.jar TesteAbrirFilePassword.java
   java -cp .:zip4j-2.6.4.jar TesteAbrirFilePassword
   ```
3. O programa tentará encontrar a senha por meio da geração e teste de combinações de caracteres ASCII.
   
## Observações

Por padrão, o comprimento máximo da senha que o programa tentará é de 3 caracteres. Esse valor pode ser ajustado no método forcaBruta().

A biblioteca Zip4j deve estar incluída no classpath para a execução correta.

## Requisitos

Java 8 ou superior
Biblioteca Zip4j (inclusa no classpath)

## Referências

Zip4j Documentation


