//package com.taskmanager.service;
//
//import com.taskmanager.entity.Task;
//import com.taskmanager.repository.TaskRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TaskService {
//
//    private final TaskRepository taskRepository;
//
//    public TaskService(TaskRepository taskRepository) {
//        this.taskRepository = taskRepository;
//    }
//
//    public List<Task> getAllTasks() {
//        return taskRepository.findAll();
//    }
//
//    public Task createTask(Task task) {
//        return taskRepository.save(task);
//    }
//
//    public Task updateTaskStatus(Long taskId, boolean completed) {
//        Task task = taskRepository.findById(taskId).orElse(null);
//        if (task != null) {
//            task.setCompleted(completed);
//            return taskRepository.save(task);
//        }
//        return null;
//    }
//
//    public List<Task> getTasksByUserId(Long userId) {
//        return taskRepository.findByAssignedToId(userId);
//    }
//}


//package com.taskmanager.service;
//
//import com.taskmanager.entity.Task;
//import com.taskmanager.entity.User;
//import com.taskmanager.repository.TaskRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TaskService {
//
//    private final TaskRepository taskRepo;
//    private final UserService userService;
//
//    public TaskService(TaskRepository taskRepo, UserService userService) {
//        this.taskRepo = taskRepo;
//        this.userService = userService;
//    }
//
//    public Task createTask(Task task, Long userId) {
//        User user = userService.getUserById(userId);
//        task.setAssignedTo(user);
//        return taskRepo.save(task);
//    }
//
//    public List<Task> getTasksForUser(Long userId) {
//        User user = userService.getUserById(userId);
//        return taskRepo.findByAssignedTo(user);
//    }
//
//    public Task markComplete(Long taskId) {
//        Task task = taskRepo.findById(taskId).orElse(null);
//        if (task != null) {
//            task.setCompleted(true);
//            taskRepo.save(task);
//        }
//        return task;
//    }
//
//    public List<Task> getAllTasks() {
//        return taskRepo.findAll();
//    }
//}

//
//package com.taskmanager.service;
//
//import com.taskmanager.entity.Task;
//import com.taskmanager.entity.User;
//import com.taskmanager.repository.TaskRepository;
//import com.taskmanager.repository.UserRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TaskService {
//
//    private final TaskRepository taskRepo;
//    private final UserRepository userRepo;
//
//    public TaskService(TaskRepository taskRepo, UserRepository userRepo) {
//        this.taskRepo = taskRepo;
//        this.userRepo = userRepo;
//    }
//
//    public Task createTask(Task task, Long userId) {
//        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        task.setAssignedTo(user);
//        task.setCompleted(false);
//        return taskRepo.save(task);
//    }
//
//    public List<Task> getAllTasks() {
//        return taskRepo.findAll();
//    }
//
//    public List<Task> getTasksByUser(Long userId) {
//        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        return taskRepo.findByAssignedTo(user);
//    }
//
//    public Task markTaskComplete(Long taskId) {
//        Task task = taskRepo.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
//        task.setCompleted(true);
//        return taskRepo.save(task);
//    }
//}


//package com.taskmanager.service;
//
//import com.taskmanager.entity.Task;
//import com.taskmanager.entity.User;
//import com.taskmanager.repository.TaskRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TaskService {
//
//    private final TaskRepository taskRepository;
//    private final UserService userService;
//
//    public TaskService(TaskRepository taskRepository, UserService userService) {
//        this.taskRepository = taskRepository;
//        this.userService = userService;
//    }
//
//    public Task assignTask(Long userId, Task task) {
//        User user = userService.getUserById(userId);
//        if (user != null) {
//            task.setAssignedTo(user);
//            return taskRepository.save(task);
//        }
//        return null;
//    }
//
//    public List<Task> getTasksByUserId(Long userId) {
//        User user = userService.getUserById(userId);
//        if (user != null) {
//            return taskRepository.findByAssignedTo(user);
//        }
//        return null;
//    }
//
//    public Task markTaskAsCompleted(Long taskId) {
//        Task task = taskRepository.findById(taskId).orElse(null);
//        if (task != null) {
//            task.setCompleted(true);
//            return taskRepository.save(task);
//        }
//        return null;
//    }
//}


//package com.taskmanager.service;
//
//import com.taskmanager.entity.Task;
//import com.taskmanager.entity.User;
//import com.taskmanager.repository.TaskRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TaskService {
//
//    @Autowired
//    private TaskRepository taskRepository;
//
//    // CREATE
//    public Task createTask(Task task) {
//        return taskRepository.save(task);
//    }
//
//    // READ
//    public List<Task> getTasksByUser(User user) {
//        return taskRepository.findByUser(user);
//    }
//
//    // UPDATE
//    public Task updateTask(Task updatedTask) {
//        return taskRepository.save(updatedTask); // assumes task has valid ID
//    }
//
//    // DELETE
//    public void deleteTask(Long taskId) {
//        taskRepository.deleteById(taskId);
//    }
//}

package com.taskmanager.service;

import com.taskmanager.entity.Task;
import com.taskmanager.entity.User;
import com.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // CREATE
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // READ
    public List<Task> getTasksByUser(User user) {
        return taskRepository.findByUser(user);
    }

    // UPDATE
    public Optional<Task> updateTask(Task updatedTask) {
        return taskRepository.findById(updatedTask.getId()).map(existing -> {
            existing.setTitle(updatedTask.getTitle());
            existing.setDescription(updatedTask.getDescription());
            existing.setCompleted(updatedTask.isCompleted());
            return taskRepository.save(existing);
        });
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }

    // DELETE
    public boolean deleteTask(Long taskId) {
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
            return true;
        }
        return false;
    }
}