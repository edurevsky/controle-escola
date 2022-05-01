# Controle-Escola
> Status: Em desenvolvimento
- Este projeto está sendo desenvolvido para a aprendizagem do <a href="https://spring.io/">Framework Spring</a> em geral.
- Java 17
- MySQL

## Como executar
1 - Clonar o repositório
```git
git clone https://github.com/edurevsky/controle-escola.git
```

2 - Criar o banco de dados
```sql
CREATE DATABASE controleescola;
```

3 - Editar o application.properties
```
spring.datasource.username={username do seu MySQL}
spring.datasource.password={sua senha}
```

4 - Execução

Você pode rodar o Main (ControleescolaApplication.java) através de alguma IDE. <br/>
Ou dentro da pasta onde contém o mvnw, digitar em seu console:

```bash
./mvnw spring-boot:run
```

O sistema estará disponível na porta 8080 do seu localhost.
