
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
  { "id_Usuario": 1, "nm_Usuario": "Gabriel Andrade", "tp_Usuario": "Tecnico" },
  { "id_Usuario": 2, "nm_Usuario": "Laura Ribeiro", "tp_Usuario": "Pesquisador" }
]
```

#### `POST /usuarios`
**Descrição**: Cria um novo usuário  
**Requisição**:
```json
{
  "id_Usuario": 6,
  "nm_Usuario": "Lucas Silva",
  "email": "lucas@pluvia.com",
  "tp_Usuario": "Analista"
}

```

#### `GET /usuarios/estatisticas/quantidade`
**Descrição**: Retorna a quantidade total de usuários  
**Resposta**:
```json
{ "5": }
```

#### `GET /usuarios/estatisticas/tipos`
**Descrição**: Agrupa por tipo de usuário  
**Resposta**:
```json
{ "Tecnico": 2, "Admin": 1, "Pesquisador": 1, "Analista": 1 }
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
    "idDispositivo": 1,
    "nome": "C.A.P.T.A. - Petrolina",
    "modelo": "PLV-X100",
    "idLocalizacao": 1
  }
]
```

#### `GET /dispositivos/{id}`
**Descrição**: Detalhes de um dispositivo específico  
**Resposta**:
```json
{
  "idDispositivo": 2,
  "nome": "C.A.P.T.A. - Campinas",
  "modelo": "PLV-X200",
  "idLocalizacao": 2
}
```

#### `POST /dispositivos`
**Requisição**:
```json
{
  "idDispositivo": 9,
  "nome": "C.A.P.T.A. - Teresina - Extra",
  "modelo": "PLV-X150",
  "idLocalizacao": 5
}
```

#### `GET /dispositivos/resumo`
**Resposta**:
```json
{
    "Modelo": {
        "PLV-X300": 2,
        "PLV-X200": 1,
        "PLV-X100": 3,
        "PLV-X150": 2
    },
    "Modelos por cidade": {
        "PLV-X300": [
            "Cuiabá",
            "Boa Vista"
        ],
        "PLV-X200": [
            "Campina Grande"
        ],
        "PLV-X100": [
            "Petrolina",
            "Cuiabá",
            "Teresina"
        ],
        "PLV-X150": [
            "Cuiabá",
            "Cuiabá"
        ]
    },
    "Quantidade de modelos em cada localizacao": {
        "Campina Grande": 1,
        "Teresina": 1,
        "Boa Vista": 1,
        "Cuiabá": 4,
        "Petrolina": 1
    }
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
    "idProducao": 1,
    "idDispositivo": 1,
    "dataProducao": "2025-05-25",
    "litrosGerados": 12.5
  }
]
```

#### `POST /producaoagua`
**Requisição**:
```json
{
  "idProducao": 7,
  "idDispositivo": 2,
  "dataProducao": "2025-06-08",
  "litrosGerados": 11.9
}
```

#### `GET /producaoagua/estatisticas/volume-por-dispositivo`
**Resposta**:
```json
{
    "1": 12.5,
    "2": 33.4,
    "3": 14.7,
    "4": 11.9,
    "5": 13.4
}
```

---

### 🌿 `/impactos`

#### `GET /impactos`
**Resposta**:
```json
[
  {
    "idImpacto": 1,
    "idProducao": 1,
    "co2Economizado_kg": 1.25,
    "pessoasBeneficiadas": 10
  }
]
```

#### `GET /impactos/estatisticas/totais`
**Resposta**:
```json
{
    "totalCO2": 6.27,
    "totalPessoas": 50
}
```

---

### 🌦️ `/previsao-climatica/simulada`

#### `GET`
**Resposta**:
```json
[
  {
    "idPrevisao": 1,
    "idLocalizacao": 1,
    "dataPrevisao": "2025-05-26",
    "umidadeRelativa": 67.3,
    "temperaturaCelsius": 33.5
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
        "idRelatorio": 1,
        "tipo": "diario",
        "dataGeracao": "2025-05-26",
        "idUsuario": 1
    },
]
```

#### `POST /relatorios`
**Requisição**:
```json
    {
        "idRelatorio": 7,
        "tipo": "impacto",
        "dataGeracao": "2025-07-10",
        "idUsuario": 4
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
