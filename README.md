
# CAPTA - Coletor Atmosférico Portátil de Tratamento de Água

## Participantes
- **Patrick Castro** – RM559271  
- **Gabriel Rossi** – RM560967  
- **Rodrigo Yamasaki** – RM560759  

---

## 🌎 Visão Geral do Projeto

O projeto **CAPTA** é um sistema backend desenvolvido em Java com Quarkus, criado para gerenciar e monitorar os dados ambientais coletados pelo dispositivo C.A.P.T.A., que transforma umidade do ar em água potável. A plataforma é capaz de registrar produção hídrica, impactos ambientais, previsões climáticas, alertas e relatórios — todos integrados com banco de dados Oracle.

---

## ⚙️ Funcionalidades Principais

- Cadastro e estatísticas de usuários
- Registro e listagem de dispositivos CAPTA
- Produção diária de água por dispositivo
- Impactos ambientais: economia de CO₂ e beneficiários
- Simulação de condições climáticas
- Geração e exclusão de relatórios
- Alertas por risco, produção e clima
- Visualização por região/localização

---

## 🧰 Tecnologias Utilizadas

- **Java 21**
- **Quarkus Framework**
- **Jakarta REST (JAX-RS)**
- **JDBC + Oracle SQL**
- **SLF4J para logging**
- **Arquitetura em camadas (DTO, BO, Repository, Resource)**

---

## 🔌 Endpoints da API RESTful (com exemplos)

### 🧑‍💻 `/usuarios`

#### `GET /usuarios`
**Descrição**: Lista nome e tipo dos usuários  
**Resposta**:
```json
[
  { "id": 1, "nome": "Gabriel Andrade", "tipo": "Técnico" },
  { "id": 2, "nome": "Laura Ribeiro", "tipo": "Pesquisador" }
]
```

#### `POST /usuarios`
**Descrição**: Cria um novo usuário  
**Requisição**:
```json
{
  "nome": "João Silva",
  "email": "joao@pluvia.com",
  "tipo": "Admin"
}
```

#### `GET /usuarios/estatisticas/quantidade`
**Descrição**: Retorna a quantidade total de usuários  
**Resposta**:
```json
{ "totalUsuarios": 5 }
```

#### `GET /usuarios/estatisticas/tipos`
**Descrição**: Agrupa por tipo de usuário  
**Resposta**:
```json
{ "Técnico": 2, "Admin": 1, "Pesquisador": 1, "Analista": 1 }
```

#### `DELETE /usuarios/{id}`
**Descrição**: Remove um usuário

---

### 📟 `/dispositivos`

#### `GET /dispositivos`
**Descrição**: Lista todos os dispositivos  
**Resposta**:
```json
[
  {
    "id_dispositivo": 1,
    "nome": "C.A.P.T.A. - Petrolina",
    "modelo": "PLV-X100",
    "id_localizacao": 1
  }
]
```

#### `GET /dispositivos/{id}`
**Descrição**: Detalhes de um dispositivo específico  
**Resposta**:
```json
{
  "id_dispositivo": 2,
  "nome": "C.A.P.T.A. - Campinas",
  "modelo": "PLV-X200",
  "id_localizacao": 2
}
```

#### `POST /dispositivos`
**Requisição**:
```json
{
  "id_dispositivo": 6,
  "nome": "C.A.P.T.A. - Natal",
  "modelo": "PLV-X150",
  "id_localizacao": 6
}
```

#### `GET /dispositivos/resumo`
**Resposta**:
```json
{
  "PLV-X100": 3,
  "PLV-X200": 1,
  "PLV-X300": 1
}
```

---

### 💧 `/producaoagua`

#### `GET /producaoagua`
**Descrição**: Lista registros de produção de água  
**Resposta**:
```json
[
  {
    "id_producao": 1,
    "id_dispositivo": 1,
    "data_producao": "2025-05-25",
    "litros_gerados": 12.5
  }
]
```

#### `POST /producaoagua`
**Requisição**:
```json
{
  "id_dispositivo": 1,
  "data_producao": "2025-06-08",
  "litros_gerados": 10.7
}
```

#### `GET /producaoagua/estatisticas/volume-por-dispositivo`
**Resposta**:
```json
{
  "C.A.P.T.A. - Petrolina": 12.5,
  "C.A.P.T.A. - Cuiabá": 14.7
}
```

---

### 🌿 `/impactos`

#### `GET /impactos`
**Resposta**:
```json
[
  {
    "id_impacto": 1,
    "id_producao": 1,
    "co2_economizado_kg": 1.25,
    "pessoas_beneficiadas": 10
  }
]
```

#### `GET /impactos/estatisticas/totais`
**Resposta**:
```json
{
  "total_pessoas": 50,
  "media_co2": 1.25
}
```

---

### 🌦️ `/previsao-climatica/simulada`

#### `GET`
**Resposta**:
```json
[
  {
    "id_previsao": 1,
    "id_localizacao": 1,
    "data_previsao": "2025-05-26",
    "umidade_relativa": 67.3,
    "temperatura_celsius": 33.5
  }
]
```

---

### 📊 `/relatorios`

#### `GET /relatorios`
**Resposta**:
```json
[
  {
    "id_relatorio": 1,
    "tipo": "diário",
    "data_geracao": "2025-05-26",
    "id_usuario": 1
  }
]
```

#### `POST /relatorios`
**Requisição**:
```json
{
  "tipo": "impacto",
  "data_geracao": "2025-06-08",
  "id_usuario": 3
}
```

#### `DELETE /relatorios/{id}`
**Descrição**: Remove um relatório

---

## 📊 Estrutura do Banco de Dados

Tabelas:
- `PP_USUARIO`
- `PP_LOCALIZACAO`
- `PP_AREA_RISCO`
- `PP_DISPOSITIVO`
- `PP_PRODUCAO_AGUA`
- `PP_IMPACTO`
- `PP_PREVISAO_CLIMATICA`
- `PP_ALERTA`
- `PP_RELATORIO`

---

## 📎 Repositório

[🔗 GitHub - GS-DDD-PluviaPlus](https://github.com/Eventos-Extremos-Gs/GS-DDD-PluviaPlus)

---

## 📌 Observações

- API pronta para integração com frontend.
- Banco relacional normalizado em 3FN.
- Dados simulados inseridos via script.
