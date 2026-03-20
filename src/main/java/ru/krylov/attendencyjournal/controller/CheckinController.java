package ru.krylov.attendencyjournal.controller;

import org.springframework.web.bind.annotation.*;
import ru.krylov.attendencyjournal.dto.CheckinRequest;
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
    public Checkin mark(@RequestBody CheckinRequest request) {
        return service.markAttendance(
                request.getLessonId(),
                request.getStudentId()
        );
    }

    @GetMapping("/student/{id}/count")
    public long getCount(@PathVariable Long id) {
        return service.getStudentAttendanceCount(id);
    }
}