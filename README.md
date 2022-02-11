# credit-analysis
API para análise de crédito
<br/><br/>

## **Proposta**

💎 Dado o cenário abaixo, elabore uma sugestão para o back-end necessária para resolver o problema.
<br/><br/>

✅ Uma empresa de empréstimo precisa criar um sistema de análise de crédito para fornecer aos seus clientes as seguintes funcionalidades:

- 🔺 I. Cadastro de clientes

  - O cliente pode cadastrar nome, e-mail, CPF, RG, endereço completo, renda e senha.

- 🔺 II. Login
  - A autenticação será realizada por e-mail e senha.

- 🔺 III. Solicitação de empréstimo
  - Para solicitar um empréstimo, precisamos do valor do empréstimo, data da primeira parcela e quantidade de parcelas. 
    O máximo de parcelas será 60 e a data da primeira parcela deve ser no máximo 3 meses após o dia atual.

- 🔺 IV. Acompanhamento das solicitações de empréstimo
  - O cliente pode visualizar a lista de empréstimos solicitados por ele mesmo e também os detalhes de um de seus empréstimos.
    Na listagem, devemos retornar no mínimo o código do empréstimo, o valor e a quantidade de parcelas.
    No detalhe do empréstimo, devemos retornar: código do empréstimo, valor, quantidade de parcelas, data da primeira parcela, e-mail do cliente e renda do cliente.
    
## **Tecnologias**

- Spring Boot 2.6.2
- Spring Data JPA
- Spring Security
- JWT
- Java 11
- Maven
- Lombok
- Model Mapper
- H2
- Postman

---

## **Obervação**

  - Antes de rodar, configure na sua IDE as variáveis de ambiente presentes no arquivo _application.properties_
  
  - Configure a variável de ambiente ATTRIBUTE_HEADER com o valor Authorization

---


## **Como utilizar? (Passo a passo)**

  - Cadastrar cliente:
  
    - Faça uma requisição POST para http://localhost:8080/v1/client com os dados do cliente.
    
    - **Exemplo:**
  
```json
{
    "name": "Alfredo Martins",
    "cpf": "53838950003",
    "rg": "456960978",
    "income": 3000.0,
    "email": "alfredo@gmail.com",
    "password": "123",
    "addresses": [
        {
            "addressType": "HOME",
            "state": "RJ",
            "city": "Rio de Janeiro",
            "district": "Botafogo",
            "publicArea": "Praia de Botafogo",
            "number": 620,
            "complement": "Apartamento 205"
        }
    ]
}
```

##

  - Realizar autenticação e obtenção de token:
  
    - Faça uma requisição POST para http://localhost:8080/login passando o respectivo e-mail e senha no corpo da requisição.
    
    - Em caso de sucesso, será retornado o token gerado e o _status code_ 200.
    
##

  - Solicitar empréstimo:
  
    - Faça uma requisição POST para http://localhost:8080/v1/loan com os seguintes detalhes:
    
      - No Header da requisição deve ser passado o token gerado na autenticação com a _KEY_ Authorization.
      
      - No corpo da requisição devem ser passados os dados do empréstimo. Exemplo:
      
```json
{
    "value": 10000.00,
    "installmentQuantity": 20,
    "dateFirstInstallment": "aaaa-mm-dd"
}
```

##

  - Obter sua lista de empréstimos:
  
    - Faça uma requisição GET para http://localhost:8080/v1/loan passando no Header da requisição o token gerado na autenticação com a _KEY_ Authorization.
    
##

  - Obter detalhes de empréstimo:
  
    - Faça uma requisição GET para http://localhost:8080/v1/loan/id passando no Header da requisição o token gerado na autenticação com a _KEY_ Authorization.
    
---

 **Este projeto ainda está em andamento. Em breve, serão feitas atualizações**
