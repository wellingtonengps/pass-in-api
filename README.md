
# Pass-in
O pass.in é uma aplicação de **gestão de participantes em eventos presenciais**.

A ferramenta permite que o organizador cadastre um evento e abra uma página pública de inscrição.

Os participantes inscritos podem emitir uma credencial para check-in no dia do evento.

O sistema fará um scan da credencial do participante para permitir a entrada no evento.

## Stack utilizada

Spring Boot


## Rodando localmente

Clone o projeto

```bash
  git clone https://github.com/wellingtonengps/pass-in
```

Entre no diretório do projeto

```bash
  cd pass-in
```

Inicie o servidor (necessário java 17 ou superior) e Maven 4.0.0


```bash
  mvn spring-boot:run
```


## Documentação da API

A documentação foi criada utilizando swagger.

```bash
  http://localhost:8080/swagger-ui/index.html
```

![Texto Alternativo](https://uploaddeimagens.com.br/imagens/zFRMLn8)

## Documentação

### Requisitos funcionais

- O organizador deve poder cadastrar um novo evento;
- O organizador deve poder visualizar dados de um evento;
- O organizador deve poser visualizar a lista de participantes;
- O participante deve poder se inscrever em um evento;
- O participante deve poder visualizar seu crachá de inscrição;
- O participante deve poder realizar check-in no evento;

### Regras de negócio

- O participante só pode se inscrever em um evento uma única vez;
- O participante só pode se inscrever em eventos com vagas disponíveis;
- O participante só pode realizar check-in em um evento uma única vez;

### Requisitos não-funcionais

- O check-in no evento será realizado através de um QRCode;
## Melhorias

Adicionar outro banco de dados (Postgres) e colocar o projeto em um conteiner Docker

- [x] Postgres
- [x] Docker
    
