package com.taskmanger.Task.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.taskmanger.Task.exception.TaskNotFoundException;
import com.taskmanger.Task.model.Task;

@Service
public class TaskService {

    List<Task> tasks = new ArrayList<>();

    public Task createTask(Task task) {
        tasks.add(task);
        return task;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getTaskById(Long id) {
        return tasks.stream()
            .filter(t -> t.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task updateTask(Long id, Task updatedTask) {
        try {
            Task task = getTaskById(id);
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setIsCompleted(updatedTask.getIsCompleted());
            return task;
        } catch (TaskNotFoundException e) {
            throw new TaskNotFoundException(id);
        }
    }

    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        tasks.remove(task);
    }
}