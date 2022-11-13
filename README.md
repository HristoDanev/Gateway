# Gateway

The system gathers currency information periodically. It provides the following endpoints: <br />
```/json_api/current``` <br />
```/json_api/history``` <br />
```/xml_api/command``` <br />
It gathers statistics about the requests and stores it in db 

## Installation

The project uses PostgreSQL, Redis, RabbitMQ; <br />
The following need to be provided in application.properties file <br />
```properties
redis.host=<redis host>
redis.port=<redis port>

spring.datasource.url= jdbc:postgresql://<postgre host>:<postgre port>/gateway
spring.datasource.username= <user name>
spring.datasource.password= <password>

rabbitmq.host=<host>
rabbitmq.port=<port>
rabbitmq.username=<user>
rabbitmq.password=<password>
```
The currency information is gathered from [fixer](https://fixer.io/). It needs a valid apikey to be provided <br />
```properties
currencies.key=<apiKey>
```
A schedule can be configured in order to pull data periodically. The interval is in
```properties
scheduler.interval=<iso duration format>
scheduler.enabled=<true or false>
```

## Usage

The system supports the following requests: <br />

```
curl --location --request POST 'localhost:8080/json_api/current' \
--header 'Content-Type: application/json' \
--data-raw '{
"requestId": "b99577fe-8d47-4972-4bf4-7db59a255149", 
"timestamp": 1586335186721,
"client": "1234",
"currency":"EUR"
}'
```

```
curl --location --request POST 'localhost:8080/json_api/history' \
--header 'Content-Type: application/json' \
--data-raw '{
    "requestId": "b89577fe-8c37-4962-8af3-7cb89a24q979",
    "timestamp": 1586335186721,
    "client": "1234",
    "currency": "EUR",
    "period": 24
}'
```

```
curl --location --request POST 'localhost:8080/xml_api/command' \
--header 'Content-Type: application/xml' \
--data-raw '<command id="123554" >
    <get consumer="13617163" >
        <currency>EUR</currency>
    </get>
</command>'
```

```
curl --location --request POST 'localhost:8080/xml_api/command' \
--header 'Content-Type: application/xml' \
--data-raw '<command id="4234-8785" >
    <history consumer="13617162" currency="EUR" period="24" />
</command>'
```