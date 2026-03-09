package ru.krylov.attendencyjournal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.krylov.attendencyjournal.entity.Checkin;

public interface CheckinRepository extends JpaRepository<Checkin, Long> {

    boolean existsByLessonIdAndStudentId(Long lessonId, Long studentId);

    long countByStudentId(Long studentId);
}