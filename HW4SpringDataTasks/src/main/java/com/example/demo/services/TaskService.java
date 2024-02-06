package com.example.demo.services;

import com.example.demo.model.Task;
import com.example.demo.model.TaskStatus;
import com.example.demo.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task addTask(Task task) {
        task.setCreateDate(LocalDateTime.now());
        return repository.save(task);
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        return repository.getTasksByStatus(status);
    }

    public Task updateTaskStatus(Long id, TaskStatus status) {
        Task findTask = repository.findById(id).orElse(null);
        if (findTask != null && !findTask.getStatus().equals(status)) {
            findTask.setStatus(status);
            return repository.save(findTask);
        }
        return null;
    }

    public void deleteTaskByID(Long id) {
        repository.deleteById(id);
    }
}
