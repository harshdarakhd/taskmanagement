//package com.taskmanager.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class Task {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String title;
//    private String description;
//    private boolean completed;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User assignedTo;
//}

//
//package com.taskmanager.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class Task {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String description;
//    private boolean completed = false;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User assignedTo;
//}


//package com.taskmanager.entity;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "tasks")
//public class Task {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String title;
//    private String description;
//    private boolean completed;
//
//    @ManyToOne
//    @JoinColumn(name = "assigned_to")
//    private User assignedTo;
//
//    // Constructors
//    public Task() {}
//
//    // Getters & Setters
//    public Long getId() { return id; }
//
//    public String getTitle() { return title; }
//    public void setTitle(String title) { this.title = title; }
//
//    public String getDescription() { return description; }
//    public void setDescription(String description) { this.description = description; }
//
//    public boolean isCompleted() { return completed; }
//    public void setCompleted(boolean completed) { this.completed = completed; }
//
//    public User getAssignedTo() { return assignedTo; }
//    public void setAssignedTo(User assignedTo) { this.assignedTo = assignedTo; }
//}

//package com.taskmanager.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Entity
//@Data
//public class Task {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String title;
//    private String description;
//    private boolean completed = false;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//}


package com.taskmanager.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    private boolean completed = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;
}
