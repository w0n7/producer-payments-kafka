# 💸 Real-Time Payment Pipeline

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-green)
![Python](https://img.shields.io/badge/Python-3.12-yellow)
![Kafka](https://img.shields.io/badge/Kafka-3.7-orange)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)
![Docker](https://img.shields.io/badge/Docker-Yes-lightgrey)
![Grafana](https://img.shields.io/badge/Grafana-11-red)

---

## 🌐 Visão Geral

Este projeto demonstra um pipeline de pagamentos em tempo real com foco em:

- 📤 **Spring Boot Producer**: gera e publica pagamentos no Kafka  
- 🔄 **Kafka**: mensageria confiável e escalável  
- 🐍 **Python ETL**: consome mensagens, converte datas para UTC e insere no PostgreSQL  
- 🗃 **PostgreSQL**: persistência dos dados de pagamento  
- 📈 **Grafana**: visualização e métricas em tempo real  

O sistema garante **consistência temporal**, **resiliência** e **observabilidade completa**.

---

## 🏗 Arquitetura

```mermaid
flowchart LR
    A[Spring Boot Producer] -->|JSON Payment| B[Kafka Broker]
    B -->|Message| C[Python ETL Consumer]
    C -->|Insert| D[Postgres DB]
    D --> E[Grafana Dashboard]
```

> 🔄 `paymentDate` é convertido para UTC no ETL. Kafka assegura entrega confiável.

---

## ⚙️ Tecnologias Utilizadas

| Componente   | Tecnologia     |
|--------------|----------------|
| Producer     | Spring Boot    |
| Broker       | Kafka          |
| ETL          | Python         |
| Banco        | PostgreSQL     |
| Dashboards   | Grafana        |
| Contêineres  | Docker         |

---

## 🚀 Setup Rápido

### 1️⃣ Subindo os serviços com Docker Compose

O `docker-compose.yml` já inclui:

- 🐘 PostgreSQL  
- ☕ Kafka + Zookeeper  
- 📊 Grafana  

```bash
docker-compose up -d
```

---

### 2️⃣ Spring Boot Producer

- Endpoint: `POST /payments/generate`  
- Payload de exemplo:

```json
{
  "amount": 150.0,
  "method": "pix"
}
```

- Resposta esperada:

```json
{
  "paymentId": "f5c6f769-c24f-464d-a972-c409311fc83a",
  "paymentDate": "2025-08-29T19:39:23+00:00",
  "amount": 150.0,
  "method": "pix"
}
```

---

### 3️⃣ Kafka

- Broker: `localhost:29092`  
- Tópico: `payments-topic`  
- Mensagens JSON:

```json
{
  "paymentId": "uuid",
  "paymentDate": "2025-08-29T19:39:23+00:00",
  "amount": 150.0,
  "method": "pix"
}
```

---

### 4️⃣ Python ETL

- Instalação de dependências:

```bash
pip install confluent-kafka psycopg2-binary python-dateutil
```

- Configuração `.env`:

```env
KAFKA_BROKER=localhost:29092
POSTGRES_URL=postgresql://postgres:postgres@localhost:5435/payments-kafka-db
```

- Execução:

```bash
python etl.py
```

> ✅ Converte `paymentDate` para UTC  
> 🛡 Evita duplicidade com `ON CONFLICT DO NOTHING`  
> 📋 Logs mostram pagamentos processados em tempo real

---

### 5️⃣ PostgreSQL

- Banco: `payments-kafka-db`  
- Tabela `payments`:

| Campo        | Tipo                     |
|--------------|--------------------------|
| payment_id   | VARCHAR PRIMARY KEY      |
| payment_date | TIMESTAMP WITH TIME ZONE |
| amount       | NUMERIC                  |
| method       | VARCHAR                  |

---

### 6️⃣ Grafana

- URL: [http://localhost:3000](http://localhost:3000)  
- Login: `admin / admin`  
- Datasource: PostgreSQL

#### Dashboards sugeridos:

- Total de pagamentos por método  
- Valor acumulado diário  
- Média de valores por método  

---

## 📌 Observações

- Todas as datas são armazenadas em **UTC** para consistência entre sistemas  
- ETL Python é **modular e escalável**, pronto para múltiplos consumers  
- Ideal para demonstração de integração **Java → Kafka → Python → Postgres → Grafana**
