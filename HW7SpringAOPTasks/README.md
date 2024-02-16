# HW7SpringAOPTasks

_На основе проекта HW4SpringDataTasks_

**_Зависимости в проекте_**:

* Spring Web
* H2 Database
* Spring Data JPA
* spring-aspects
* Lombok

Приложение для работы с заметками (без проверок). С помощью Spring AOP добавлено логирование 
действий пользователя с печатью в консоль информации о некоторых вызываемых методах.

Примеры логирования с использованием аспектов и перехватчиков.

1) Изменение статуса задачи по ID (`{"status": "IN_PROGRESS"}`)

          =============================================
          Request URL: http://localhost:8080/tasks/1
          Method: PUT
          Status: 200
          Обработчик: com.kirin.demo.controllers.TaskController#updateTaskStatus(Long, Task)
          Метод updateTaskStatus был вызван с аргументами: [1, Task(id=null, description=null, status=IN_PROGRESS, createDate=null)]
          >>>
          Метод Task com.kirin.demo.services.TaskService.updateTaskStatus(Long,TaskStatus) был вызван
          Аргументы метода: [1, IN_PROGRESS]
          Hibernate: select t1_0.id,t1_0.create_date,t1_0.description,t1_0.status from tasks t1_0 where t1_0.id=?
          Hibernate: update tasks set create_date=?,description=?,status=? where id=?
          Возвращаемая задача: Task(id=1, description=Сходить в магазин, status=IN_PROGRESS, createDate=2024-02-06T00:00)
          Метод TaskService.updateTaskStatus(..) завершил свою работу
          >>>
          Время работы метода: 811 milliseconds
          =============================================

2) Удаление задачи по ID

          =============================================
          Request URL: http://localhost:8080/tasks/4
          Method: DELETE
          Status: 200
          Обработчик: com.kirin.demo.controllers.TaskController#deleteTask(Long)
          Метод deleteTask был вызван с аргументами: [4]
          >>>
          Метод void com.kirin.demo.services.TaskService.deleteTaskByID(Long) был вызван
          Аргументы метода: [4]
          Hibernate: select t1_0.id,t1_0.create_date,t1_0.description,t1_0.status from tasks t1_0 where t1_0.id=?
          Hibernate: delete from tasks where id=?
          Метод TaskService.deleteTaskByID(..) завершил свою работу
          >>>
          Время работы метода: 27 milliseconds
          =============================================

3) Добавление задачи (`{"description": "описание 1"}`)

          =============================================
          Request URL: http://localhost:8080/tasks
          Method: POST
          Status: 200
          Обработчик: com.kirin.demo.controllers.TaskController#addTask(Task)
          Метод addTask был вызван с аргументами: [Task(id=null, description=описание 1, status=null, createDate=null)]
          >>>
          Метод Task com.kirin.demo.services.TaskService.addTask(Task) был вызван
          Аргументы метода: [Task(id=null, description=описание 1, status=null, createDate=null)]
          Hibernate: insert into tasks (create_date,description,status,id) values (?,?,?,default)
          Возвращаемая задача: Task(id=5, description=описание 1, status=NOT_STARTED, createDate=2024-02-16T01:02:03.040000000)
          Метод TaskService.addTask(..) завершил свою работу
          >>>
          Время работы метода: 44 milliseconds
          =============================================

4) Просмотр задач с определенным статусом

          =============================================
          Request URL: http://localhost:8080/tasks/status/IN_PROGRESS
          Method: GET
          Status: 200
          Обработчик: com.kirin.demo.controllers.TaskController#getTasksByStatus(TaskStatus)
          Метод getTasksByStatus был вызван с аргументами: [IN_PROGRESS]
          >>>
          Метод List com.kirin.demo.services.TaskService.getTasksByStatus(TaskStatus) был вызван
          Аргументы метода: [IN_PROGRESS]
          Hibernate: select t1_0.id,t1_0.create_date,t1_0.description,t1_0.status from tasks t1_0 where t1_0.status=?
          AfterReturning для: execution(public java.util.List com.kirin.demo.services.TaskService.getTasksByStatus(com.kirin.demo.model.TaskStatus))
          Всего найдено задач: 1
          Метод TaskService.getTasksByStatus(..) завершил свою работу
          >>>
          Время работы метода: 61 milliseconds
          =============================================