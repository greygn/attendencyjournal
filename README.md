# Attendance Journal

REST API для управления посещаемостью студентов.

## Описание

Приложение предоставляет HTTP API для работы с учебными группами, студентами, занятиями и регистрацией посещаемости. База данных построена на PostgreSQL.

## Запуск через Docker Compose

```bash
docker-compose up
```

API будет доступен по адресу `http://localhost:8080`.

## Локальная разработка

### Предварительные требования

- Maven 3.9+
- Java 21+
- PostgreSQL 16+ (для локального развертывания)

### Запуск тестов

```bash
mvn clean test
```

Выполняются unit тесты с проверкой coverage (минимум 50%).

### Сборка

```bash
mvn clean package
```

### Проверка качества кода

```bash
mvn checkstyle:check
```

### Запуск приложения локально

Требуется работающий PostgreSQL. Установите переменные окружения:

```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/attendancy_db
export SPRING_DATASOURCE_USERNAME=journaluser
export SPRING_DATASOURCE_PASSWORD=password
```

Затем запустите:

```bash
mvn spring-boot:run
```

## Docker

### Сборка образа

```bash
docker build -t attendencyjournal:latest .
```

### Запуск контейнера

```bash
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/appdb \
  -e SPRING_DATASOURCE_USERNAME=journaluser \
  -e SPRING_DATASOURCE_PASSWORD=password \
  attendencyjournal:latest
```

## CI/CD

GitHub Actions pipeline автоматически запускается при push в `main` или `develop`. Pipeline включает:

- Сборку кода
- Проверку Checkstyle
- Запуск тестов с проверкой coverage
- Сборку Docker образа
- Публикацию образа в Docker Hub

Для работы с Docker Hub, добавьте GitHub Secrets:

- `DOCKER_HUB_USERNAME`
- `DOCKER_HUB_TOKEN`

## Структура проекта

```
src/main/java/ru/krylov/attendencyjournal/
├── controller/       HTTP endpoints
├── service/          Бизнес-логика
├── entity/           Модели базы данных
├── repository/       Data access
└── dto/             Transfer objects
```

## Endpoints

### Study Groups

**POST /groups** — Создать учебную группу

```bash
curl -X POST http://localhost:8080/groups \
  -H "Content-Type: application/json" \
  -d '{"name": "b1-IFST-32", "courseNumber": 3}'
```

**GET /groups** — Получить все группы

```bash
curl http://localhost:8080/groups
```

### Students

**POST /students** — Создать студента

```bash
curl -X POST http://localhost:8080/students \
  -H "Content-Type: application/json" \
  -d '{"name": "Ivan Petrov", "groupId": 1}'
```

**GET /students** — Получить всех студентов

```bash
curl http://localhost:8080/students
```

### Lessons

**POST /lessons** — Создать занятие

```bash
curl -X POST http://localhost:8080/lessons \
  -H "Content-Type: application/json" \
  -d '{"name": "Lecture", "datetime": "2026-04-09T09:45:00", "groupIds": [1]}'
```

**GET /lessons** — Получить все занятия

```bash
curl http://localhost:8080/lessons
```

### Checkins

**POST /checkins** — Отметить посещение

```bash
curl -X POST http://localhost:8080/checkins \
  -H "Content-Type: application/json" \
  -d '{"lessonId": 1, "studentId": 1}'
```

**GET /checkins/student/{id}/count** — Получить количество посещений студента

```bash
curl http://localhost:8080/checkins/student/1/count
```
