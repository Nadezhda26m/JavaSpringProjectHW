package com.example.demo.services;

import com.example.demo.model.Task;
import com.example.demo.model.TaskStatus;
import com.example.demo.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Сервис для работы с задачами
 */
@Service
@AllArgsConstructor
public class TaskService {

    /**
     * Репозиторий для работы с задачами
     */
    private TaskRepository repository;

    /**
     * Получение списка всех задач из репозитория.
     * @return список задач
     */
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    /**
     * Добавление задачи в репозиторий.
     * @param task объект задачи с заполненным описанием
     * @return добавленная задача с описанием, статусом и датой создания
     */
    public Task addTask(Task task) {
        task.setCreateDate(LocalDateTime.now());
        return repository.save(task);
    }

    /**
     * Получение задач с указанным статусом из репозитория.
     * @param status статус выполнения задачи (enum)
     * @return список задач с указанным статусом
     */
    public List<Task> getTasksByStatus(TaskStatus status) {
        return repository.getTasksByStatus(status);
    }

    /**
     * Обновление статуса задачи в репозитории по ID.
     * @param id идентификатор задачи
     * @param status статус выполнения задачи (enum)
     * @return измененная задача
     */
    public Task updateTaskStatus(Long id, TaskStatus status) {
        Task findTask = repository.findById(id).orElse(null);
        if (findTask != null && !findTask.getStatus().equals(status)) {
            findTask.setStatus(status);
            return repository.save(findTask);
        }
        return findTask;
    }

    /**
     * Удаление задачи с указанным ID из репозитория
     * @param id идентификатор задачи
     */
    public void deleteTaskByID(Long id) {
        repository.deleteById(id);
    }
}
