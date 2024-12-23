# json-converter

API Rest template for JSON Converter

## Installation

```bash
mvn clean install
```

## Usage

```bash
mvn spring-boot:run
```

## Endpoints

```shell
curl -X 'GET' \
  'http://localhost:8081/students/all' \
  -H 'accept: */*'
```

```shell
curl -X 'POST' \
  'http://localhost:8081/students/add?admitYear=1990&jsonField=%7B%20%22postCode%22%3A%20%22TW9%202SF%22%2C%20%22city%22%3A%20%22London%22%2C%20%22telemetry%22%3A%20%22123%22%20%7D' \
  -H 'accept: */*' \
  -d ''
```

License
MIT License


