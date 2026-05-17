# ContractService

Какой-то сервис, которому очень нужны курсы валют

## Project Structure

```
src/main/java/my/idp/spring/
├── ContractServiceApplication.java          # Main application entry point
├── contract/
│   ├── controller/                          # REST controllers
│   ├── dto/                                 # Request DTOs & Response VOs
│   ├── entity/                              # Entities
│   ├── exception/                           # Exception handles
│   ├── mapper/                              # Entity-to-dto/vo mappers
│   ├── repository/                          # JPA repositories
│   └── validation/                          # REST api validations
```

## Model

### Contract

**Description:** contract for the purchase of something that specified in contract items

### ContractItem

**Description:** contract product position
