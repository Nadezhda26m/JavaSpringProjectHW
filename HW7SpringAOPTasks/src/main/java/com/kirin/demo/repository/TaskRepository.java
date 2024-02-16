package com.kirin.demo.repository;

import com.kirin.demo.model.Task;
import com.kirin.demo.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Репозиторий для хранения задач в таблице tasks
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Получение задач с указанным статусом из репозитория.
     * @param status статус выполнения задачи (enum)
     * @return список задач с указанным статусом
     */
    List<Task> getTasksByStatus(TaskStatus status);

}