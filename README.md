# Spring homework

## Settings

Maven project

JDK: Amazon Corretto 17 (corretto-17)

Language level: 17 (SDK default)

## Projects

### HW1CRUDSpringWeb

**_Базовое задание:_**

Добавить в простое CRUD веб-приложение, которое было разработано на семинаре
функцию удаления данных о пользователе:

1) В класс UserRepository добавить метод `public void deleteById(int id)`

   /- удаление записи пользователя из БД по ID.

   (подсказка: SQL запрос `"DELETE FROM userTable WHERE id=?"`, метод jdbc.update)
2) В класс UserService добавить метод `public void deleteById(int id)` -

   /- удаление пользователя через репозиторий.

   (подсказка: в нем вызывается метод userRepository.deleteById)
3) В класс UserController добавить метод

   `public String deleteUser(@PathVariable("id") int id)`

   (с аннотацией: `@GetMapping("user-delete/{id}"`))

   /- перехват команды на удаление студента от браузера.

   (подсказка: в нем вызывается метод userService.deleteById)

Если задание выполнено верно, то при запуске приложения
по адресу http://localhost:9090/users будет работать кнопка удаления
пользователя по ID.

**_Задание "со звездочкой":_**

Реализовать метод обновления данных о пользователе.

- `@GetMapping("/user-update/{id}")`
- `@PostMapping("/user-update")`
- `User update(User user)`
- `User getUserById(int id)`

Dependencies:

* Spring Web
* JDBC API
* H2 Database
* Thymeleaf

---

### HW2RESTUserService

**_Предзадание_**

1. Создать сервис "DataProcessingService". Этот сервис должен принимать на вход список
   объектов типа User (с полями "name", "age", "email"), и выполнять следующие операции:
    * сортировка списка пользователей по возрасту,
    * фильтрация списка по заданному критерию (например, возраст больше 18),
    * рассчет среднего возраста пользователей.

2. Создать два сервиса - "UserService" и "NotificationService".

   UserService должен содержать метод `createUser(String name, int age, String email)`,
   который создает пользователя и возвращает его.

   NotificationService должен иметь метод `notifyUser(User user)`, который
   просто печатает сообщение о том, что пользователь был создан.

   Ваша задача - использовать @Autowired в UserService для внедрения NotificationService
   и вызвать метод notifyUser после создания нового пользователя.

3. Создать сервис "RegistrationService", который принимает на вход данные
   о пользователе (имя, возраст, email), создает пользователя с помощью UserService,
   затем использует DataProcessingService для добавления пользователя в список и выполнения
   операций над этим списком. После выполнения каждой операции, использовать
   NotificationService для вывода информации о выполненной операции.

**_Домашнее задание:_**

1) В класс RegistrationService добавить поля UserService, NotificationService

   (добавить в IOC контейнер аннотацией @Autowired или через конструктор класса)
2) Разработать метод processRegistration в котором:
    - создается пользователь из параметров метода
    - созданный пользователь добавляется в репозиторий
    - через notificationService выводится сообщение в консоль
3) В TaskController добавить обработчики

   filterUsersByAge() (Подсказка `@GetMapping("/filter/{age}")`)

   и calculateAverageAge (Подсказка `@GetMapping("/calc")`)
4) В методе filterUsersByAge параметр age получать через аннотацию @PathVariable

**_Задание со звездочкой:_**

1) В классе UserController добавить обработчик userAddFromParam извлекающий данные
   для создания пользователя из параметров HTTP запроса
2) Перенести репозиторий проекта с List<User> на базу данных H2

**_Проверка работы_**

Для теcтирования проекта использовать программу PostMan

a) Добавление пользователя - запрос :

* метод - POST
* адрес - http://localhost:8080/users/body
* тело -

  {
  "name":"Artur",
  "age":23,
  "email":"exam1@yandex.ru"
  }

b) Получение списка пользователей на сервере - запрос:

* метод - GET
* адрес - http://localhost:8080/users

c) Проверка сортировки - запрос:

* метод - GET
* адрес - http://localhost:8080/tasks/sort

d) Проверка фильтрации - запрос:

* метод - GET
* адрес - http://localhost:8080/tasks/filter/23

e) Проверка среднего арифметического - запрос:

* метод - GET
* адрес - http://localhost:8080/tasks/calc

Dependencies:

* Spring Web

---

### HW3SimpleThymeleaf

**_Базовое задание:_**

1. Создание базового веб-приложения Spring MVC

   Начните с создания простого веб-приложения с использованием Spring MVC.
   Это может быть простой сайт, который выводит "Привет, мир!" на главной странице.
   Используйте аннотацию @Controller и @RequestMapping для маршрутизации запросов на эту страницу.
2. Добавление Thymeleaf в проект

   Добавьте Thymeleaf в свое веб-приложение Spring MVC. Создайте простую страницу
   с некоторыми переменными, которые заполняются с помощью модели Spring MVC
   и отображаются на странице с использованием Thymeleaf.
3. Создание формы ввода и обработка данных формы

   Создайте страницу с формой ввода, используя Thymeleaf для рендеринга формы.
   Затем создайте контроллер Spring MVC, который обрабатывает отправку формы
   и выводит полученные данные. Это может быть форма для регистрации или любая
   другая форма, которая собирает информацию от пользователя.

**_Задание со звездочкой:_**
Проект домашнего задания для 1 семинара (CRUD приложение USER SERVICE) переписать:

1. Использовать библиотеку Lombok:

   a) @Data - для полей классов

   b) @AllArgsConstructor - для классов с конструкторами

   c) @Log - логировать работу всех контроллеров

2. Использовать configuration-processor для работы с настройками приложения:

   a) Создать объект инкапсулирующий шаблоны запросов к базе данных H2

   b) Использовать аннотации @ConfigurationProperties и @ConfigurationPropertiesScan
   для заполнения полей этого класса

   с) Вынести все SQL шаблоны в настройки приложения.

Dependencies:

* Spring Web
* Thymeleaf
* Lombok
* H2 Database
* JDBC API

---

### HW4SpringDataTasks

**_Базовое задание:_**

Вам предстоит создать приложение для управления списком задач с использованием Spring Boot
и Spring Data JPA. Требуется реализовать следующие функции:

* Добавление задачи.

  Подсказка метод в контроллере:

      @PostMapping 
      public Task addTask(@RequestBody Task task)

* Просмотр всех задач.

  Подсказка метод в контроллере:

      @GetMapping 
      public List<Task> getAllTasks()

* Просмотр задач по статусу (например, "завершена", "в процессе", "не начата").

  Подсказка метод в контроллере:

      @GetMapping("/status/{status}") 
      public List<Task> getTasksByStatus(@PathVariable TaskStatus status)

* Изменение статуса задачи.

  Подсказка метод в контроллере:

      @PutMapping("/{id}") 
      public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task)

* Удаление задачи.

  Подсказка метод в контроллере:

      @DeleteMapping("/{id}")
      public void deleteTask(@PathVariable Long id)

Репозиторий подсказка

      public interface TaskRepository extends JpaRepository<Task, Long>

Структура задачи (класс Task):

- ID (автоинкрементное)(тип Long)
- Описание (не может быть пустым)(тип String)
- Статус (одно из значений: "не начата", "в процессе", "завершена")(Тип TaskStatus )
- Дата создания (автоматически устанавливается при создании задачи)(Тип LocalDateTime)

Подсказка понадобится энумератор:

      enum TaskStatus {
         NOT_STARTED, IN_PROGRESS, COMPLETED;
      }

**_Проверка решения в Postman_**

1. (GET) http://localhost:8080/tasks - получение списка всех задач
2. (POST) http://localhost:8080/tasks - добавление задачи

        {
        "description": "описание 1"
        }
3. (PUT) http://localhost:8080/tasks/1 - изменение статуса задачи по ID

        {
        "status": "IN_PROGRESS"
        }
4. (GET) http://localhost:8080/tasks/status/NOT_STARTED - просмотр задач с определенным статусом

   (GET) http://localhost:8080/tasks/status/IN_PROGRESS

5. (DELETE) http://localhost:8080/tasks/2 - удаление задачи по ID

Dependencies:

* Spring Web
* H2 Database
* Spring Data JPA
* Lombok


---

### HW5SpringDataNotes

**_Базовое задание:_**

Разработайте небольшое веб-приложение на Spring Boot, которое будет представлять
из себя сервис для учета личных заметок. Приложение должно поддерживать следующие функции:

1. Добавление заметки. (Подсказка @PostMapping )
2. Просмотр всех заметок.(Подсказка @GetMapping )
3. Получение заметки по id. (Подсказка @GetMapping("/{id}"))
4. Редактирование заметки.(Подсказка @PutMapping("/{id}"))
5. Удаление заметки.(Подсказка @DeleteMapping("/{id}"))

Все методы контроллера возвращают ResponseEntity.

Структура заметки:

* ID (автоинкрементное)(тип - Long)
* Заголовок (не может быть пустым)(тип - String)
* Содержимое (не может быть пустым)(тип - String)
* Дата создания (автоматически устанавливается при создании заметки)(тип - LocalDateTime)

**_Проверка решения в Postman_**

1. (POST) http://localhost:8080/notes - создание заметки

        {
        "title": "Title 1",
        "text": "Text 1"
        }
2. (GET) http://localhost:8080/notes - получение списка всех заметок
3. (GET) http://localhost:8080/notes/1 - получение заметки по ID
4. (PUT) http://localhost:8080/notes/1 - редактирование заметки 

        {
        "title": "Title 1",
        "text": "New text 1"
        }
5. (DELETE) http://localhost:8080/notes/1 - удаление заметки по ID

Dependencies:

* Spring Web
* H2 Database
* Spring Data JPA
* Lombok

---