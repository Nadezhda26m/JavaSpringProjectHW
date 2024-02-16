package com.kirin.demo.controllers;

import com.kirin.demo.model.Task;
import com.kirin.demo.model.TaskStatus;
import com.kirin.demo.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-Контроллер для работы с задачами
 */
@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    /**
     * Сервис для работы с задачами
     */
    private TaskService service;

    /**
     * Получение списка всех задач.
     * @return список задач
     */
    @GetMapping
    public List<Task> getAllTasks() {
        return service.getAllTasks();
    }

    /**
     * Добавление задачи.
     * @param task объект задачи с заполненным описанием
     * @return добавленная задача
     */
    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return service.addTask(task);
    }

    /**
     * Получение задач с указанным статусом.
     * @param status статус выполнения задачи  (enum)
     * @return список задач с указанным статусом
     */
    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status) {
        return service.getTasksByStatus(status);
    }

    /**
     * Обновление статуса задачи по ID.
     * @param id идентификатор задачи
     * @param task объект задачи с новым статусом
     * @return измененная задача
     */
    @PutMapping("/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task) {
        return service.updateTaskStatus(id, task.getStatus());
    }

    /**
     * Удаление задачи с указанным ID
     * @param id идентификатор задачи
     */
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        service.deleteTaskByID(id);
    }
}
