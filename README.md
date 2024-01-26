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
### HW3
