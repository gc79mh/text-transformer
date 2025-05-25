# Text Trannsformer

![Build](https://github.com/gc79mh/text-transformer/actions/workflows/ci.yml/badge.svg?branch=main)

## Start
### Linux
    ./mvnw spring-boot:run 
### Windows
    ./mvnw.cmd spring-boot:run 

## Command to use POST
    curl -s -X POST http://localhost:8080/transform \
    -H "Content-Type: application/json" \
    -d '{"text": "Hello World", "actions": ["lower", "reverse"]}'