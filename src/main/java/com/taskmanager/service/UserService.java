//package com.taskmanager.service;
//
//import com.taskmanager.entity.User;
//import com.taskmanager.repository.UserRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserService {
//
//    private final UserRepository userRepository;
//
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    public User createUser(User user) {
//        return userRepository.save(user);
//    }
//
//    public User getUserById(Long id) {
//        return userRepository.findById(id).orElse(null);
//    }
//}

//package com.taskmanager.service;
//
//import com.taskmanager.entity.User;
//import com.taskmanager.repository.UserRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserService {
//
//    private final UserRepository repo;
//
//    public UserService(UserRepository repo) {
//        this.repo = repo;
//    }
//
//    public User createUser(User user) {
//        return repo.save(user);
//    }
//
//    public List<User> getAllUsers() {
//        return repo.findAll();
//    }
//
//    public User getUserByUsername(String username) {
//        return repo.findByUsername(username);
//    }
//
//    public User getUserById(Long id) {
//        return repo.findById(id).orElse(null);
//    }
//}

//
//package com.taskmanager.service;
//
//import com.taskmanager.entity.User;
//import com.taskmanager.repository.UserRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserService {
//
//    private final UserRepository userRepo;
//
//    public UserService(UserRepository userRepo) {
//        this.userRepo = userRepo;
//    }
//
//    public User createUser(User user) {
//        return userRepo.save(user);
//    }
//
//    public List<User> getAllUsers() {
//        return userRepo.findAll();
//    }
//
//    public User getUserById(Long id) {
//        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
//    }
//}


package com.taskmanager.service;

import com.taskmanager.entity.User;
import com.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {
        return userRepository.save(user);
    }

    public User login(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
