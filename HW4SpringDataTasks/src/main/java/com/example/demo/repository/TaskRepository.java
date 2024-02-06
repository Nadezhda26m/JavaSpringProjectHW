package com.example.demo.repository;

import com.example.demo.model.Task;
import com.example.demo.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Репозиторий для хранения задач в таблице tasks
 */
// @Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Получение задач с указанным статусом из репозитория.
     * @param status статус выполнения задачи (enum)
     * @return список задач с указанным статусом
     */
    // @Query("SELECT * FROM tasks WHERE status = :status")
    List<Task> getTasksByStatus(TaskStatus status);

    // @Modifying
    // @Query("UPDATE tasks SET status = :status WHERE id = :id")
    // void updateTaskStatus(Long id, TaskStatus status);
}