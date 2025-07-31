package com.taskmanager.repository;//package com.taskmanager.repository;
//
//import com.taskmanager.entity.Task;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
//public interface TaskRepository extends JpaRepository<Task, Long> {
//    List<Task> findByAssignedToId(Long userId);
//}

//package com.taskmanager.repository;
//
//import com.taskmanager.entity.Task;
//import com.taskmanager.entity.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
//public interface TaskRepository extends JpaRepository<Task, Long> {
//    List<Task> findByUserId(Long userId);
//}


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.taskmanager.entity.Task;
import com.taskmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}
