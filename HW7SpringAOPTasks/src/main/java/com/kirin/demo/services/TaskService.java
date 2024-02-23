package com.kirin.demo.services;

import com.kirin.demo.aspects.TrackUserAction;
import com.kirin.demo.exceptions.IncorrectDataException;
import com.kirin.demo.exceptions.TaskNotFoundException;
import com.kirin.demo.model.Task;
import com.kirin.demo.model.TaskStatus;
import com.kirin.demo.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с задачами
 */
@Service
@RequiredArgsConstructor
public class TaskService {

    /**
     * Репозиторий для работы с задачами
     */
    private final TaskRepository repository;

    /**
     * Минимальное количество символов в описании задачи
     */
    public final static int MIN_LEN_TASK_DESCRIPTION = 4;

    /**
     * Получение списка всех задач из репозитория.
     *
     * @return список задач
     */
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    /**
     * Добавление задачи в репозиторий. Задача должна быть передана
     * с подходящим по требованиям описанием.
     *
     * @param task объект задачи с заполненным описанием
     * @return добавленная задача с описанием, статусом и датой создания
     */
    @TrackUserAction
    public Task addTask(Task task) {
        checkTaskDescription(task);
        if (task.getCreateDate() == null) {
            task.setCreateDate(LocalDateTime.now());
        }
        if (task.getStatus() == null) {
            task.setStatus(TaskStatus.NOT_STARTED);
        }
        return repository.save(task);
    }

    /**
     * Получение задач с указанным статусом из репозитория.
     *
     * @param status статус выполнения задачи (enum)
     * @return список задач с указанным статусом
     */
    @TrackUserAction
    public List<Task> getTasksByStatus(TaskStatus status) {
        return repository.getTasksByStatus(status);
    }

    /**
     * Обновление статуса задачи в репозитории по ID. Выдает ошибку TaskNotFoundException,
     * если задача не найдена.
     *
     * @param id     идентификатор задачи
     * @param status статус выполнения задачи (enum)
     * @return измененная задача
     */
    @TrackUserAction
    public Task updateTaskStatus(Long id, TaskStatus status) {
        Optional<Task> findTask = repository.findById(id);
        if (findTask.isEmpty())
            throw new TaskNotFoundException("Task not found with id='" + id + "'");
        if (!findTask.get().getStatus().equals(status)) {
            findTask.get().setStatus(status);
            return repository.save(findTask.get());
        }
        return findTask.get();
    }

    /**
     * Удаление задачи с указанным ID из репозитория
     *
     * @param id идентификатор задачи
     */
    @TrackUserAction
    public void deleteTaskByID(Long id) {
        repository.deleteById(id);
    }

    /**
     * Проверка описания задачи. Оно не должно быть null. Количество символов в описании
     * должно быть не меньше установленного значения.
     * @param task объект задачи для проверки
     */
    private void checkTaskDescription(Task task) {
        if (task.getDescription() == null) {
            throw new IncorrectDataException("Received task has null description");
        }
        if (task.getDescription().length() < MIN_LEN_TASK_DESCRIPTION) {
            throw new IncorrectDataException(
                    "Received task has length = " + task.getDescription().length()
                            + ", but min length should be " + MIN_LEN_TASK_DESCRIPTION);
        }
    }
}
