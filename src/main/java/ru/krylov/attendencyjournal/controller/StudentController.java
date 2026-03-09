package ru.krylov.attendencyjournal.controller;

import org.springframework.web.bind.annotation.*;
import ru.krylov.attendencyjournal.entity.Student;
import ru.krylov.attendencyjournal.service.StudentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public Student create(@RequestBody Map<String, Object> body) {
        return service.create(
                (String) body.get("name"),
                Long.valueOf(body.get("groupId").toString())
        );
    }

    @GetMapping
    public List<Student> getAll() {
        return service.getAll();
    }
}