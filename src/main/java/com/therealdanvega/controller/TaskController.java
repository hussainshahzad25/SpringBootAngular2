package com.therealdanvega.controller;

import java.util.List;

import com.therealdanvega.domain.Response;
import com.therealdanvega.domain.Task;
import com.therealdanvega.service.TaskService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class TaskController {

	private final Logger log = LoggerFactory.getLogger(TaskController.class);
	private TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	// @GetMapping( value = {"","/"})
	// public Iterable<Task> listTasks() {
	// Iterable<Task> list = taskService.list();
	// log.info("Tast List :: " +list );
	// return taskService.list();
	// }

	@GetMapping(value = {"","/"})
	public ResponseEntity<Object> listTasks() {
		
		List<Task> tasks = taskService.getTasks();
		
		
		return new ResponseEntity<Object>(tasks, HttpStatus.OK);
	}

	@PostMapping("/save")
	public Task saveTask(@RequestBody Task task) {
		log.info("Going To Save :: " + task);
		return taskService.save(task);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getTask(@PathVariable("id") Long id) {
		Response response = null;
		Task task = taskService.getTask(id);

		if (task == null) {
			response = new Response();
			response.setMessage("Not Found");
			return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(task, HttpStatus.OK);
	}

	@PostMapping("/{id}")
	public ResponseEntity<Object> deleteTask(@PathVariable("id") Long id) {
		log.info("Deleting Task Having Id :: " + id);
		Response response = null;
		boolean deleteTask = taskService.deleteTask(id);
		if (!deleteTask) {
			response = new Response();
			response.setMessage("Not Found");
			return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
		}
		response = new Response();
		response.setMessage("Record got Deleted.");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@PostMapping("/deleteUsingBody")
	public ResponseEntity<Object> deleteUsingBody(@RequestBody Task task) {

		log.info("Deleting Task Having Id :: " + task.getId());
		Response response = null;
		boolean deleteTask = taskService.deleteTask(task.getId());
		if (!deleteTask) {
			response = new Response();
			response.setMessage("Not Found");
			return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
		}
		response = new Response();
		response.setMessage("Record got Deleted.");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}
