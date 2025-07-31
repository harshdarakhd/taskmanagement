//package com.taskmanager.controller;
//
//import com.taskmanager.entity.Task;
//import com.taskmanager.service.TaskService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/tasks")
//public class TaskController {
//
//    private final TaskService service;
//
//    public TaskController(TaskService service) {
//        this.service = service;
//    }
//
//    @PostMapping("/assign/{userId}")
//    public Task assignTask(@RequestBody Task task, @PathVariable Long userId) {
//        return service.createTask(task, userId);
//    }
//
//    @GetMapping("/user/{userId}")
//    public List<Task> getUserTasks(@PathVariable Long userId) {
//        return service.getTasksForUser(userId);
//    }
//
//    @PostMapping("/complete/{taskId}")
//    public Task markAsComplete(@PathVariable Long taskId) {
//        return service.markComplete(taskId);
//    }
//
//    @GetMapping
//    public List<Task> getAllTasks() {
//        return service.getAllTasks();
//    }
//}


//package com.taskmanager.controller;
//
//import com.taskmanager.entity.Task;
//import com.taskmanager.service.TaskService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/tasks")
//public class TaskController {
//
//    private final TaskService taskService;
//
//    public TaskController(TaskService taskService) {
//        this.taskService = taskService;
//    }
//
//    @PostMapping("/create/{userId}")
//    public Task createTask(@PathVariable Long userId, @RequestBody Task task) {
//        return taskService.createTask(task, userId);
//    }
//
//    @GetMapping("/all")
//    public List<Task> getAllTasks() {
//        return taskService.getAllTasks();
//    }
//
//    @GetMapping("/user/{userId}")
//    public List<Task> getUserTasks(@PathVariable Long userId) {
//        return taskService.getTasksByUser(userId);
//    }
//
//    @PutMapping("/complete/{taskId}")
//    public Task markAsCompleted(@PathVariable Long taskId) {
//        return taskService.markTaskComplete(taskId);
//    }
//}

//package com.taskmanager.controller;
//
//import com.taskmanager.entity.Task;
//import com.taskmanager.service.TaskService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/tasks")
//public class TaskController {
//
//    private final TaskService taskService;
//
//    public TaskController(TaskService taskService) {
//        this.taskService = taskService;
//    }
//
//    @PostMapping("/assign/{userId}")
//    public Task assignTask(@PathVariable Long userId, @RequestBody Task task) {
//        return taskService.assignTask(userId, task);
//    }
//
//    @GetMapping("/user/{userId}")
//    public List<Task> getTasksByUser(@PathVariable Long userId) {
//        return taskService.getTasksByUserId(userId);
//    }
//
//    @PutMapping("/complete/{taskId}")
//    public Task completeTask(@PathVariable Long taskId) {
//        return taskService.markTaskAsCompleted(taskId);
//    }
//}


//package com.taskmanager.controller;
//import com.taskmanager.service.UserService;
//import com.taskmanager.entity.Task;
//import com.taskmanager.entity.User;
//import com.taskmanager.service.TaskService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/tasks")
//public class TaskController {
//
//    private final TaskService taskService;
//    private final UserService userService;
//
//    @Autowired
//    public TaskController(TaskService taskService, UserService userService) {
//        this.taskService = taskService;
//        this.userService = userService;
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<?> createTask(@RequestBody Task task) {
//        Long userId = task.getUser().getId();
//        task.setUser(userService.getUserById(userId));
//        Task createdTask = taskService.createTask(task);
//        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/update-task")
//    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
//        Task updated = taskService.updateTask(task);
//        return ResponseEntity.ok(updated);
//    }
//
//    @DeleteMapping("/delete-task/{taskId}")
//    public ResponseEntity<String> deleteTask(@PathVariable Long taskId) {
//        taskService.deleteTask(taskId);
//        return ResponseEntity.ok("Task deleted successfully");
//    }
//
//    @PostMapping("/user-tasks")
//    public ResponseEntity<List<Task>> getTasksByUser(@RequestBody User user) {
//        List<Task> tasks = taskService.getTasksByUser(user);
//        return ResponseEntity.ok(tasks);
//    }
//
//}


package com.taskmanager.controller;

import com.taskmanager.entity.Task;
import com.taskmanager.entity.User;
import com.taskmanager.service.TaskService;
import com.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        Long userId = task.getUser().getId();
        User user = userService.getUserById(userId);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        task.setUser(user);
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/update-task")
    public ResponseEntity<Optional<Task>> updateTask(@RequestBody Task task) {
        Optional<Task> updated = taskService.updateTask(task);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete-task/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId) {
        Task task = taskService.getTaskById(taskId);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("Task deleted successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getTasksByUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        List<Task> tasks = taskService.getTasksByUser(user);
        return ResponseEntity.ok(tasks);
    }
}
