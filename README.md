## Описание задачи
Разработать Spring Boot REST API (deal) для работы со сделками приложения.

### Требования к решению:
1. СУБД: PostgreSQL
2. Взаимодействие с БД: через Spring Data JPA
3. Аудит: Использование библиотеки audit-lib для логирования всех запросов

## Запуск проекта с использованием Docker
1. Создать контейнер PostgreSQL:
```bash
docker run --name deal -e POSTGRES_PASSWORD=deal -p 5433:5432 -d postgres
```

2. Запустить контейнер PostgreSQL:
```bash
docker start deal
```