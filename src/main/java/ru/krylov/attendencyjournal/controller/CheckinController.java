package ru.krylov.attendencyjournal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.krylov.attendencyjournal.dto.CheckinRequest;
import ru.krylov.attendencyjournal.entity.Checkin;
import ru.krylov.attendencyjournal.service.CheckinService;

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
                request.getStudentId());
    }

    @GetMapping("/student/{id}/count")
    public long getCount(@PathVariable Long id) {
        return service.getStudentAttendanceCount(id);
    }
}