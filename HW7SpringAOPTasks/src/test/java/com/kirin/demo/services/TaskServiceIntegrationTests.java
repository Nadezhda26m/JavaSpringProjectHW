package com.kirin.demo.services;

import com.kirin.demo.model.Task;
import com.kirin.demo.model.TaskStatus;
import com.kirin.demo.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Интеграционные тесты для проверки работы сервиса задач
 */
@SpringBootTest
public class TaskServiceIntegrationTests {

    /**
     * Bean-заглушка для репозитория
     */
    @MockBean
    private TaskRepository taskRepository;

    /**
     * Тестируемый сервис
     */
    @Autowired
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

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(task2)).thenReturn(task2);

        Task res = taskService.updateTaskStatus(1L, TaskStatus.IN_PROGRESS);

        verify(taskRepository).findById(1L);
        verify(taskRepository).save(task2);
        assertEquals(task2, res);
    }
}
