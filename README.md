# Text Transformer

![Build](https://github.com/gc79mh/text-transformer/actions/workflows/ci.yml/badge.svg?branch=main) ![Package](https://github.com/gc79mh/text-transformer/actions/workflows/release.yml/badge.svg?branch=main) ![javadoc](https://github.com/gc79mh/text-transformer/actions/workflows/javadoc.yml/badge.svg?branch=main)

### 📦 Download
[Download the latest release](https://github.com/gc79mh/text-transformer/releases/tag/latest)

### 📚 JavaDoc
[View API Documentation](https://gc79mh.github.io/text-transformer/)

## Start
### Linux
    ./mvnw spring-boot:run 
### Windows
    ./mvnw.cmd spring-boot:run 

## Command to use POST
### Linux
    curl -s -X POST http://localhost:8080/transform \
    -H "Content-Type: application/json" \
    -d '{"text": "Hello World", "actions": ["lower", "reverse"]}'
### Windows
    curl -X POST http://localhost:8080/transform \
    -H "Content-Type: application/json" \
    -d "{\"text\": \"Hello World\", \"actions\": [\"lower\", \"reverse\"]}"



# Text Transformer API

Welcome to the **Text Transformer API**

## 📘 Usage Instructions

To apply a transformation you can either do a:

### ➤ GET Request:
    /transform?text=YOUR_TEXT&actions=YOUR_ACTION_1&actions=YOUR_ACTION_2


### ➤ POST Request with Payload:

```json
{
  "text": "YOUR_TEXT",
  "actions": [ "YOUR_ACTION_1", "YOUR_ACTION_2" ]
}
```

---

## 📤 Response Format

```json
{
  "text": "YOUR_TEXT",
  "actions": [ "YOUR_ACTION_1", "YOUR_ACTION_2" ],
  "result": "YOUR_TEXT_AFTER_TRANSFORMATION"
}
```

---

## 🛠 Available Actions

- **lower** – Puts all letters to lowercase  
- **upper** – Puts all letters to uppercase  
- **reverse** – Reverses the text  
- **capitalize** – Capitalizes each word  
- **number2words** – Converts integers in range [0, 10000] to words  
- **acronym** – Makes an acronym from the input  
  **Examples:**
  ```
  for example → e.g.
  among others → i.a.
  and so on → aso
  ```
- **expand** – Expands known acronyms to full strings  
  **Examples:**
  ```
  prof. → professor
  dr → doctor
  e.g. → for example
  aso → and so on
  ```
- **latex** – Converts text to LaTeX-supported format  
  **Examples:**
  ```
  & → \&
  $ → \$
  ```
- **dedup** – Removes repetitive words  

---

© 2025 Text Transformer API
