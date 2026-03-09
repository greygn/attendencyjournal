# Система учёта посещаемости студентов

## Описание проекта

REST API для управления посещаемостью студентов на занятиях с возможностью добавления, редактирования и удаления записей. Система позволяет отслеживать посещение студентов на учебных занятиях, организованных по учебным группам.

## Архитектура

### Компоненты

- **Spring Boot 4.0.3** — REST API приложение для управления посещаемостью (Java 21)
- **PostgreSQL 18 Alpine** — реляционная база данных для хранения информации
- **Docker Network (bridge)** — изолированная сеть для взаимодействия контейнеров

### Структура данных

Реляционная база содержит таблицы:

- `study_groups` — учебные группы (id, name, course_number)
- `students` — студенты (id, name, group_id)
- `lessons` — занятия (id, name, datetime)
- `lesson_study_groups` — связь многие-ко-многим между занятиями и группами (lesson_id, group_id)
- `checkins` — отметки посещаемости (id, student_id, lesson_id, checked_at)

## Быстрый старт

### Требования

- Docker и Docker Compose
- Файл `.env` с переменными окружения

### Установка

1. **Подготовить `.env` файл** в корне проекта:

```env
POSTGRES_USER=journaluser
POSTGRES_PASSWORD=secret
POSTGRES_DB=appdb
```

2. **Запустить сервисы**:

```bash
docker compose down -v
docker compose build --no-cache
docker compose up
```

3. **Проверить логи**:

```bash
docker compose logs -f app
```

Приложение будет доступно по адресу `http://localhost:8080`.

## API Endpoints

### Учебные группы

```bash
# Создать группу
POST /study-groups
Content-Type: application/json
{
  "name": "b1-IFST-32",
  "courseNumber": 3
}

# Получить все группы
GET /study-groups
```

### Студенты

```bash
# Создать студента
POST /students
Content-Type: application/json
{
  "name": "Ivan Petrov",
  "groupId": 1
}

# Получить всех студентов
GET /students
```

### Занятия

```bash
# Создать занятие
POST /lessons
Content-Type: application/json
{
  "name": "Mobile App Development",
  "datetime": "2026-03-09T09:45:00",
  "groupIds": [1, 2]
}

# Получить все занятия
GET /lessons
```

### Отметки посещаемости

```bash
# Отметить посещаемость студента
POST /checkins
Content-Type: application/json
{
  "studentId": 1,
  "lessonId": 1
}

# Получить количество посещений студента
GET /checkins/student/{id}/count
```

## Реализованные функции

### Основной функционал

- [x] Двухконтейнерная архитектура (Spring Boot App + PostgreSQL)
- [x] Bridge-сеть для изолированного взаимодействия контейнеров
- [x] REST API с CRUD операциями для всех сущностей
- [x] Управление учебными группами (создание, просмотр)
- [x] Управление студентами (создание, просмотр, привязка к группам)
- [x] Управление занятиями (создание, просмотр, привязка к группам)
- [x] Отметки посещаемости студентов (создание, просмотр)

### Требования к Docker (соблюдены)

- [x] Организована docker-сеть в Docker-Compose файле (bridge network `app-network`)
- [x] Билд Docker-контейнера выполняется на любом окружении (multi-stage build с Maven)
- [x] Dockerfile разбит на разные stages (build и runtime)
- [x] Доступ извне docker-сети только к API (порт 8080 открыт только для app)
- [x] База данных без port-forwarding (скрыта в docker-сети)
- [x] Поддиректория volume для PostgreSQL (pgdata volume)
- [x] Отсутствуют пароли в Dockerfile и docker-compose
- [x] Приложение получает конфиденциальные данные из переменных окружения

## Технические детали

### Multi-stage Dockerfile

Dockerfile использует два этапа для оптимизации размера образа:

1. **Build stage** — использует Maven 3.9 с JDK 21 для сборки приложения
   - Копирует `pom.xml` и загружает зависимости
   - Компилирует проект и создаёт JAR-файл
2. **Runtime stage** — использует облегчённый `eclipse-temurin:21-jre`
   - Содержит только необходимые компоненты для запуска
   - Копирует готовый JAR из build stage

Результат: образ содержит только JRE и приложение, без инструментов сборки.

### Docker Compose конфигурация

- **PostgreSQL** — работает в контейнере с изолированным хранилищем (volume `pgdata`)
- **Spring Boot App** — зависит от БД (`depends_on`), получает параметры подключения из переменных окружения
- **Bridge Network** — контейнеры взаимодействуют по имени сервиса (`db:5432`)

### Переменные окружения

Приложение использует следующие переменные:

- `POSTGRES_USER` — имя пользователя PostgreSQL
- `POSTGRES_PASSWORD` — пароль PostgreSQL
- `POSTGRES_DB` — название базы данных
- `SPRING_DATASOURCE_URL` — URL подключения к БД (конструируется автоматически из переменных)
- `SPRING_DATASOURCE_USERNAME` — логин для приложения
- `SPRING_DATASOURCE_PASSWORD` — пароль для приложения

## Мониторинг

Все события логируются в консоль. Примеры логов:

```
2026-03-09 10:15:30.123  INFO 1 --- [main] r.k.a.AttendencyjournalApplication      : Starting AttendencyjournalApplication
2026-03-09 10:15:35.567  INFO 1 --- [main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080
2026-03-09 10:15:35.589  INFO 1 --- [main] r.k.a.AttendencyjournalApplication      : Started AttendencyjournalApplication in 5.466 seconds
```

### Проверка здоровья

```bash
# Проверить доступность API
curl http://localhost:8080/study-groups
```

## Остановка и очистка

```bash
# Остановить контейнеры
docker compose down

# Остановить контейнеры и удалить volumes
docker compose down -v

# Пересобрать образы и запустить заново
docker compose down -v && docker compose build --no-cache && docker compose up
```
