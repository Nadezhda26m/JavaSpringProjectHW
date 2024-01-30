## Запросы для проверки в PostMan

1. (POST) Добавление пользователя

   http://localhost:8080/users/body

       {
       "name": "Artur",
       "age": 23,
       "email": "exam1@yandex.ru"
       }

       {
       "name": "Tom",
       "age": 45,
       "email": "exam2@yandex.ru"
       }

       {
       "name": "Sam",
       "age": 29,
       "email": "exam3@yandex.ru"
       }

2. (GET) Получение списка пользователей на сервере

   http://localhost:8080/users

3. (POST) Создание пользователя из параметров запроса

   http://localhost:8080/users/add/Jhon/18/exam4@yandex.ru

   http://localhost:8080/users/add/Milly/57/exam5@yandex.ru

4. (GET) Получение списка задач

   http://localhost:8080/tasks

5. (GET) Получение списка пользователей, отсортированного по возрасту

   http://localhost:8080/tasks/ageSort

6. (GET) Получение списка пользователей, которые старше указанного возраста

   http://localhost:8080/tasks/filter/more/27

7. (GET) Получение списка пользователей, которые младше указанного возраста

   http://localhost:8080/tasks/filter/less/40

8. (GET) Получение среднего возраста зарегистрированных пользователей

   http://localhost:8080/tasks/calc
