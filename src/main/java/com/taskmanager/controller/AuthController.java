//package com.taskmanager.controller;
//
//import com.taskmanager.entity.User;
//import com.taskmanager.service.UserService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    private final UserService userService;
//
//    public AuthController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @PostMapping("/register")
//    public User register(@RequestBody User user) {
//        return userService.createUser(user);
//    }
//
//    @GetMapping("/users")
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
//}

//package com.taskmanager.controller;
//
//import com.taskmanager.entity.User;
//import com.taskmanager.service.UserService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    private final UserService service;
//
//    public AuthController(UserService service) {
//        this.service = service;
//    }
//
//    @PostMapping("/register")
//    public User register(@RequestBody User user) {
//        return service.createUser(user);
//    }
//
//    @GetMapping("/users")
//    public List<User> getAllUsers() {
//        return service.getAllUsers();
//    }
//}

//
//package com.taskmanager.controller;
//
//import com.taskmanager.entity.User;
//import com.taskmanager.service.UserService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    private final UserService userService;
//
//    public AuthController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @PostMapping("/register")
//    public User register(@RequestBody User user) {
//        return userService.createUser(user);
//    }
//
//    @GetMapping("/users")
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
//}

//package com.taskmanager.controller;
//
//import com.taskmanager.entity.User;
//import com.taskmanager.repository.UserRepository;
//import com.taskmanager.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    private final UserService userService;
//    @Autowired
//    private UserRepository userRepository;
//
//    public AuthController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<User> register(@RequestBody User user) {
//        // ✅ Username uniqueness check
//        Optional<User> existing = userRepository.findByUsername(user.getUsername());
//        if (existing.isPresent()) {
//            throw new RuntimeException("Username already exists");
//        }
//
//        // ✅ Save and return
//        User savedUser = userRepository.save(user);
//        return ResponseEntity.ok(savedUser);
//    }
//
//    @PostMapping("/login")
//    public User login(@RequestBody User user) {
//        User loggedIn = userService.login(user.getUsername(), user.getPassword());
//        if (loggedIn != null) {
//            return loggedIn;
//        } else {
//            throw new RuntimeException("Invalid username or password");
//        }
//    }
//
//    @GetMapping("/users")
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
//}


package com.taskmanager.controller;

import com.taskmanager.entity.User;
import com.taskmanager.repository.UserRepository;
import com.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public AuthController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        // ✅ Username uniqueness check
        Optional<User> existing = userRepository.findByUsername(user.getUsername());
        if (existing.isPresent()) {
            return ResponseEntity.badRequest().body(null); // Cleaner error response
        }

        // ✅ Save user with default USER role (can be enhanced later)
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User loggedIn = userService.login(user.getUsername(), user.getPassword());
        if (loggedIn != null) {
            return ResponseEntity.ok(loggedIn);
        } else {
            return ResponseEntity.status(401).build(); // Unauthorized
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
