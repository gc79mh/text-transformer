# Text Transformer API

![Build](https://github.com/gc79mh/text-transformer/actions/workflows/ci.yml/badge.svg?branch=main) ![Package](https://github.com/gc79mh/text-transformer/actions/workflows/release.yml/badge.svg?branch=main) ![javadoc](https://github.com/gc79mh/text-transformer/actions/workflows/javadoc.yml/badge.svg?branch=main)

Welcome to the **Text Transformer API**

For people working with text data, our Text Transformer application will allow you to transform text data (e.g. change case, eliminate duplicates, etc.). The application will be available via GUI as well as remote API, thanks to which it will be possible to integrate it with existing tools.

## 📦 Download
[Download the latest release](https://github.com/gc79mh/text-transformer/releases/tag/latest)

## 📚 JavaDoc
[View API Documentation](https://gc79mh.github.io/text-transformer/)

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

## Debug
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

---

© 2025 Text Transformer API
