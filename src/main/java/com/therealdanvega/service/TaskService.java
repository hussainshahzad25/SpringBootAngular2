package com.therealdanvega.service;

import java.util.List;

import com.therealdanvega.domain.Task;

public interface TaskService {

    Iterable<Task> list();

    Task save(Task task);

	boolean deleteTask(Long id);

	Task getTask(Long id);

	List<Task> getTasks();
}
