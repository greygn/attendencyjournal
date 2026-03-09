package ru.krylov.attendencyjournal.controller;

import org.springframework.web.bind.annotation.*;
import ru.krylov.attendencyjournal.entity.Checkin;
import ru.krylov.attendencyjournal.service.CheckinService;

import java.util.Map;

@RestController
@RequestMapping("/checkins")
public class CheckinController {

    private final CheckinService service;

    public CheckinController(CheckinService service) {
        this.service = service;
    }

    @PostMapping
    public Checkin mark(@RequestBody Map<String, Long> body) {
        return service.markAttendance(
                body.get("lessonId"),
                body.get("studentId")
        );
    }

    @GetMapping("/student/{id}/count")
    public long getCount(@PathVariable Long id) {
        return service.getStudentAttendanceCount(id);
    }
}