# Api de cardápio digital
<p>
  A API permite criar um item, listar, atualizar e deletar. Permite criar uma categoria para os itens, listar, atualizar e deletar.
</p>

<h2> Tecnologias utilizadas </h2>
<p>
  🔹<strong> Java 17 </strong> <br>
  🔹<strong> Spring Boot </strong> <br>
  🔹<strong> Spring Data JPA </strong> <br>
  🔹<strong> PostgreSQL </strong> <br>
  🔹<strong> Maven </strong><br>
</p>

<h2>Requisitos</h2>

- Necessário ter o Java 17 e o PostgreSQL instalados em sua máquina.

<h2>Instalação</h2>

- Clone o repositório:

```bash
git clone https://github.com/MarcosSilva13/Cardapio-digital-API
```
- Abra a pasta Cardapio-digital-API na IDE IntelliJ e instale as dependências com o Maven.<br>
- Abra o arquivo `DigitalmenuApplication.java` e pressione `Shift+F10` para executar, ou clique no icone ▶️ de execução.
- A aplicação estará disponível no endereço: http://localhost:8080 ou http://localhost:8080/swagger-ui/index.html

<h2> Endpoints - Item </h2>

<h3>🔹GET🔹</h3>

```bash
/item ==> (Retorna uma lista de itens com suas informações).
```
<h3>🔹POST🔹</h3>

```bash
/item ==> (Cadastra um novo item).
```
<h3>🔹PUT🔹</h3>

```bash
/item/{id} ==> (Atualiza os dados de um item pelo id).
```

<h3>🔹DELETE🔹</h3>

```bash
/item/{id} ==> (Deleta um item pelo seu id).
```

<h2> Endpoints - Categoria </h2>

<h3>🔹GET🔹</h3>

```bash
/categories ==> (Retorna uma lista de categorias com suas informações).
```

<h3>🔹POST🔹</h3>

```bash
/categories ==> (Cadastra uma nova categoria).
```

<h3>🔹PUT🔹</h3>

```bash
/categories/{id} ==> (Atualiza os dados de uma categoria pelo id).
```

<h3>🔹DELETE🔹</h3>

```bash
/categories/{id} ==> (Deleta uma categoria pelo seu id).
```
<h3>Exemplo de retorno de um item </h3>

```bash
{
  "id": "3d470e4b-5ac2-4f29-af26-905da3123329",
  "name": "X Tudo",
  "price": 22.5,
  "image": "url da imagem do x tudo",
  "ingredients": "Pão, Milho, Batata palha, Alface, Tomate, Ovo",
  "category": {
      "id": "24f3dbff-cd4b-484e-a33e-71430e41ec9e",
      "name": "Burgues Tradicionais"
  }
}
```
<h3>Exemplo de retorno de uma categoria </h3>

```bash
{
    "id": "24f3dbff-cd4b-484e-a33e-71430e41ec9e",
    "name": "Burgues Tradicionais"
}
```
