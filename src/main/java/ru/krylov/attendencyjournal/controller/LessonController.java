package ru.krylov.attendencyjournal.controller;

import org.springframework.web.bind.annotation.*;
import ru.krylov.attendencyjournal.entity.Lesson;
import ru.krylov.attendencyjournal.service.LessonService;

import java.time.LocalDateTime;
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
    public Lesson create(@RequestBody Map<String, Object> body) {

        String name = (String) body.get("name");
        LocalDateTime datetime =
                LocalDateTime.parse((String) body.get("datetime"));

        List<Integer> ids = (List<Integer>) body.get("groupIds");

        Set<Long> groupIds = ids.stream()
                .map(Long::valueOf)
                .collect(Collectors.toSet());

        return service.create(name, datetime, groupIds);
    }

    @GetMapping
    public List<Lesson> getAll() {
        return service.getAll();
    }
}