# Requisições de Teste da API de Currículos

Esta pasta contém requisições de teste para a API de Currículos. Você pode usar estes arquivos JSON com ferramentas como cURL, Postman ou qualquer cliente HTTP para testar os endpoints da API.

## Arquivos de Teste Disponíveis

1. `create_person.json` - Exemplo de requisição para criar uma nova pessoa com dados completos do currículo
2. `update_person.json` - Exemplo de requisição para atualizar informações de uma pessoa existente

## Instruções para Teste

### Usando cURL

1. Criar uma nova pessoa:
```bash
curl -X POST http://localhost:8080/api/persons \
  -H "Content-Type: application/json" \
  -d @create_person.json
```

2. Listar todas as pessoas:
```bash
curl http://localhost:8080/api/persons
```

3. Obter uma pessoa específica (substitua {id} pelo ID real):
```bash
curl http://localhost:8080/api/persons/{id}
```

4. Atualizar uma pessoa (substitua {id} pelo ID real):
```bash
curl -X PUT http://localhost:8080/api/persons/{id} \
  -H "Content-Type: application/json" \
  -d @update_person.json
```

5. Excluir uma pessoa (substitua {id} pelo ID real):
```bash
curl -X DELETE http://localhost:8080/api/persons/{id}
```

### Usando Postman

1. Importe os arquivos JSON no Postman
2. Crie uma nova requisição para cada endpoint
3. Defina o método HTTP apropriado (GET, POST, PUT, DELETE)
4. Configure o cabeçalho Content-Type como application/json
5. Use os arquivos JSON como corpos das requisições quando necessário

## Endpoints da API

- `GET /` - Página inicial com informações da API
- `GET /api/persons` - Listar todas as pessoas
- `GET /api/persons/{id}` - Obter uma pessoa específica
- `POST /api/persons` - Criar uma nova pessoa
- `PUT /api/persons/{id}` - Atualizar uma pessoa
- `DELETE /api/persons/{id}` - Excluir uma pessoa

## Códigos de Resposta

- 200: Sucesso
- 201: Criado
- 404: Não Encontrado
- 400: Requisição Inválida
- 500: Erro Interno do Servidor 