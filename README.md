# CAPTA - Coletor Atmosférico Portátil de Tratamento de Água

## Participantes
- **Patrick Castro** – RM559271  
- **Gabriel Rossi** – RM560967  
- **Rodrigo Yamasaki** – RM560759  

---

## 🌎 Visão Geral do Projeto

O projeto **CAPTA** é uma aplicação Java Quarkus desenvolvida para dar suporte ao dispositivo ambiental **C.A.P.T.A.**, que transforma a umidade do ar em água potável. A plataforma foi projetada para monitorar, armazenar e analisar dados de produção de água, impacto ambiental, previsões climáticas e localização dos dispositivos em campo.

O backend funciona como núcleo do ecossistema, integrando dados em tempo real a interfaces frontend para visualização e gestão.

---

## ⚙️ Funcionalidades Principais

- **Gestão de Usuários**: Cadastro, listagem e associação de relatórios.
- **Gestão de Dispositivos**: Registro, localização e modelo dos dispositivos.
- **Registro de Produção de Água**: Quantidade diária produzida por dispositivo.
- **Visualização de Impacto Ambiental**: Economia de CO₂ e pessoas beneficiadas.
- **Previsão Climática**: Umidade e temperatura por local.
- **Alertas Estratégicos**: Geração de alertas baseados em condições climáticas e operacionais.
- **Relatórios**: Registro de relatórios gerados por usuários.

---

## 🧰 Tecnologias Utilizadas

- **Java 21**
- **Quarkus Framework**
- **Banco de Dados Oracle**
- **JDBC + SQL puro**
- **Arquitetura em Camadas (DTOs, BOs, Repositories, Resources)**
- **Deploy local com Docker (opcional)**

---

## 🔌 Estrutura da API (Exemplo de Endpoints)

### 📁 Rotas de Usuários (`/usuarios`)

#### 1. Criar Usuário  
`POST /usuarios`  
**Body:**
```json
{
  "nome": "Gabriel Andrade",
  "email": "gabriel@pluvia.com",
  "tipo": "Técnico"
}
```

#### 2. Listar Usuários  
`GET /usuarios`  
**Retorno:**
```json
[
  {
    "id": 1,
    "nome": "Gabriel Andrade",
    "email": "gabriel@pluvia.com",
    "tipo": "Técnico"
  }
]
```

---

### 🌍 Rotas de Localização (`/localizacoes`)

#### 1. Listar Localizações  
`GET /localizacoes`  
Retorna as coordenadas e cidades monitoradas.

---

### 💧 Rotas de Produção de Água (`/producoes`)

#### 1. Registrar Produção  
`POST /producoes`  
**Body:**
```json
{
  "id_dispositivo": 1,
  "data_producao": "2025-05-25",
  "litros_gerados": 12.5
}
```

---

### 📊 Rotas de Impacto Ambiental (`/impactos`)

#### 1. Obter Impacto por Produção  
`GET /impactos/{id_producao}`  
Retorna a economia de CO₂ e pessoas beneficiadas.

---

### 🔔 Rotas de Alertas (`/alertas`)

#### 1. Gerar ou Listar Alertas por Localização  
`GET /alertas?local=1`  
Exibe mensagens como “baixa produção”, “clima favorável”, etc.

---

### 🌡️ Rotas de Previsão Climática (`/previsoes`)

#### 1. Previsão por Região  
`GET /previsoes/{id_localizacao}`  
Exibe temperatura e umidade previstas.

---

### 📝 Rotas de Relatórios (`/relatorios`)

#### 1. Gerar Relatório  
`POST /relatorios`  
**Body:**
```json
{
  "tipo": "impacto",
  "data_geracao": "2025-05-26",
  "id_usuario": 1
}
```

---

## 📎 Repositório do Projeto

[🔗 GitHub - GS-DDD-PluviaPlus](https://github.com/Eventos-Extremos-Gs/GS-DDD-PluviaPlus)

---

## 📊 Estrutura do Banco de Dados

- `PP_USUARIO`
- `PP_LOCALIZACAO`
- `PP_DISPOSITIVO`
- `PP_PRODUCAO_AGUA`
- `PP_IMPACTO`
- `PP_PREVISAO_CLIMATICA`
- `PP_ALERTA`
- `PP_AREA_RISCO`
- `PP_RELATORIO`

---

## 📌 Observações

- O projeto está preparado para integração com o frontend via REST.
- O backend foi validado com dados simulados inseridos diretamente no banco Oracle.
- Estrutura normalizada em 3FN e scripts de criação incluídos no repositório.
