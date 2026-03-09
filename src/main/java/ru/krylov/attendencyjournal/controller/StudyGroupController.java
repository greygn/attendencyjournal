package ru.krylov.attendencyjournal.controller;

import org.springframework.web.bind.annotation.*;
import ru.krylov.attendencyjournal.entity.StudyGroup;
import ru.krylov.attendencyjournal.service.StudyGroupService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/groups")
public class StudyGroupController {

    private final StudyGroupService service;

    public StudyGroupController(StudyGroupService service) {
        this.service = service;
    }

    @PostMapping
    public StudyGroup create(@RequestBody Map<String, Object> body) {
        return service.create(
                (String) body.get("name"),
                (Integer) body.get("course")
        );
    }

    @GetMapping
    public List<StudyGroup> getAll() {
        return service.getAll();
    }
}