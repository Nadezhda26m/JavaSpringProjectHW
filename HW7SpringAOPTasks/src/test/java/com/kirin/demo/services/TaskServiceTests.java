package com.kirin.demo.services;

import com.kirin.demo.exceptions.IncorrectDataException;
import com.kirin.demo.exceptions.TaskNotFoundException;
import com.kirin.demo.model.Task;
import com.kirin.demo.model.TaskStatus;
import com.kirin.demo.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Модульные тесты для проверки работы сервиса задач
 */
@ExtendWith(MockitoExtension.class)
public class TaskServiceTests {

    /**
     * Mock-объект репозитория
     */
    @Mock
    private TaskRepository taskRepository;

    /**
     * Тестируемый сервис
     */
    @InjectMocks
    private TaskService taskService;

    /**
     * Проверка добавления задачи с валидными данными
     */
    @Test
    public void addTaskGoodTest() {
        Task task = new Task();
        task.setDescription("Описание 1");

        Task task1 = new Task();
        task1.setDescription("Описание 1");
        task1.setStatus(TaskStatus.NOT_STARTED);
        task1.setCreateDate(LocalDateTime.now());

        Task task2 = new Task();
        task2.setDescription("Описание 1");
        task2.setId(1L);
        task2.setStatus(TaskStatus.NOT_STARTED);
        task2.setCreateDate(LocalDateTime.now());

        given(taskRepository.save(task1)).willReturn(task2);

        Task res = taskService.addTask(task);

        verify(taskRepository).save(task1);
        assertEquals(task2, res);
    }

    /**
     * Проверка вызова исключения IncorrectDataException при передаче
     * в метод addTask задачи с пустой ссылкой на описание.
     * Проверка полученного в исключении сообщения.
     */
    @Test
    public void addTaskNullDescriptionTest() {
        Task task = new Task();

        Exception exception = assertThrows(
                IncorrectDataException.class,
                () -> taskService.addTask(task)
        );

        verify(taskRepository, never()).save(any());
        assertEquals("Received task has null description", exception.getMessage());
    }

    /**
     * Проверка вызова исключения IncorrectDataException при передаче в метод addTask
     * задачи с описанием, длина которого меньше минимального количества символов.
     * Проверка полученного в исключении сообщения.
     */
    @Test
    public void addTaskShortDescriptionTest() {
        Task task = new Task();
        task.setDescription("А");

        Exception exception = assertThrows(
                IncorrectDataException.class,
                () -> taskService.addTask(task)
        );

        verify(taskRepository, never()).save(any());
        assertEquals("Received task has length = 1, but min length should be "
                        + TaskService.MIN_LEN_TASK_DESCRIPTION, exception.getMessage());
    }

    /**
     * Проверка обновления статуса задачи с валидными данными
     */
    @Test
    public void updateTaskStatusGoodTest() {
        Task task = new Task();
        task.setId(1L);
        task.setDescription("Описание 1");
        task.setStatus(TaskStatus.NOT_STARTED);
        task.setCreateDate(LocalDateTime.now());

        Task task2 = new Task();
        task2.setId(1L);
        task2.setDescription("Описание 1");
        task2.setStatus(TaskStatus.IN_PROGRESS);
        task2.setCreateDate(LocalDateTime.now());

        given(taskRepository.findById(1L)).willReturn(Optional.of(task));
        given(taskRepository.save(task2)).willReturn(task2);

        Task res = taskService.updateTaskStatus(1L, TaskStatus.IN_PROGRESS);

        verify(taskRepository).findById(1L);
        verify(taskRepository).save(task2);
        assertEquals(task2, res);
    }

    /**
     * Проверка вызова исключения TaskNotFoundException при передаче
     * в метод updateTaskStatus id заметки, которой нет в репозитории.
     * Проверка полученного в исключении сообщения.
     */
    @Test
    public void updateNotFoundTaskTest() {
        long id = anyLong();
        given(taskRepository.findById(id))
                .willReturn(Optional.empty());

        Exception exception = assertThrows(
                TaskNotFoundException.class,
                () -> taskService.updateTaskStatus(id, any(TaskStatus.class))
        );

        verify(taskRepository, never()).save(any());
        assertEquals("Task not found with id='" + id + "'", exception.getMessage());
    }
}
