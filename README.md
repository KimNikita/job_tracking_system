# Базовая система для назначения задач и отслеживания их времени выполнения

**Файл для конфигурации подключения к БД*

[Application.yaml](src/main/resources/application.yaml)

**Скрипты для создания таблиц в базе данных, но для создания таблиц нужно запустить в maven терминале mvn spring-boot:run**

[persons table](src/main/resources/migration/persons.sql)

[tasks table](src/main/resources/migration/tasks.sql)

[WebSecurityConfig](src/main/java/com/job_tracking_system/configuration/WebSecurityConfig.java)
Класс конфигурации аутентификации, то есть тут происходит конфигурация всего что отвечает за security

[AuthController](src/main/java/com/job_tracking_system/controllers/AuthController.java)
Эндпоинты регистрации и аутентификации, а так же выхода из системы

[PersonController](src/main/java/com/job_tracking_system/controllers/PersonController.java)
Эндпоинты для работы с людьми

[TaskController](src/main/java/com/job_tracking_system/controllers/TaskController.java)
Эндпоинты для работы с задачами

[Security](src/main/java/com/job_tracking_system/security)
Все что связано с генерацией аутентификационного токена и сервисами аутентификации и авторизации

[Mappers](src/main/java/com/job_tracking_system/mappers)
Маппинги из приходящих в запросе json сущностей в используемые в проекте

[PersonService](src/main/java/com/job_tracking_system/services/PersonService.java)
Конечная сверка пришедшего пользователя с базой данных посредством Spring Security