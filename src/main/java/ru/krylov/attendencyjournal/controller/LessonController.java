package ru.krylov.attendencyjournal.controller;

import org.springframework.web.bind.annotation.*;
import ru.krylov.attendencyjournal.dto.CreateLessonRequest;
import ru.krylov.attendencyjournal.entity.Lesson;
import ru.krylov.attendencyjournal.service.LessonService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService service;

    public LessonController(LessonService service) {
        this.service = service;
    }

    @PostMapping
    public Lesson create(@RequestBody CreateLessonRequest request) {
        return service.create(
                request.getName(),
                request.getDatetime(),
                new HashSet<>(request.getGroupIds())
        );
    }

    @GetMapping
    public List<Lesson> getAll() {
        return service.getAll();
    }
}