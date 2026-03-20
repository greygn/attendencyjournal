package ru.krylov.attendencyjournal.controller;

import org.springframework.web.bind.annotation.*;
import ru.krylov.attendencyjournal.dto.CreateStudentRequest;
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
    public Student create(@RequestBody CreateStudentRequest request) {
        return service.create(
                request.getName(),
                request.getGroupId()
        );
    }

    @GetMapping
    public List<Student> getAll() {
        return service.getAll();
    }
}