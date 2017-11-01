package com.therealdanvega.service;

import java.util.List;

import com.therealdanvega.domain.Task;
import com.therealdanvega.repository.TaskRepository;

import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

	private TaskRepository taskRepository;

	public TaskServiceImpl(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public Iterable<Task> list() {
		return taskRepository.findAll();
	}

	@Override
	public Task save(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public boolean deleteTask(Long id) {
		try {
			taskRepository.delete(id);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public Task getTask(Long id) {
		Task findOne = taskRepository.findOne(id);
		return findOne;

	}

	@Override
	public List<Task> getTasks() {
		List<Task> findAll = null;
		try {
			findAll = (List<Task>) taskRepository.findAll();
		} catch (Exception e) {
			return null;
		}
		return findAll;

	}
}
