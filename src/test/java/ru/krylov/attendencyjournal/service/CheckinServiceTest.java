package ru.krylov.attendencyjournal.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.krylov.attendencyjournal.entity.Checkin;
import ru.krylov.attendencyjournal.entity.Lesson;
import ru.krylov.attendencyjournal.entity.Student;
import ru.krylov.attendencyjournal.entity.StudyGroup;
import ru.krylov.attendencyjournal.repository.CheckinRepository;
import ru.krylov.attendencyjournal.repository.LessonRepository;
import ru.krylov.attendencyjournal.repository.StudentRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CheckinServiceTest {

    @Mock
    private CheckinRepository checkinRepository;

    @Mock
    private LessonRepository lessonRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private CheckinService checkinService;

    private StudyGroup testGroup;
    private Student testStudent;
    private Lesson testLesson;
    private Checkin testCheckin;

    @BeforeEach
    void setUp() {
        testGroup = new StudyGroup();
        testGroup.setId(1L);
        testGroup.setName("Group A");
        testGroup.setCourse(1);

        testStudent = new Student();
        testStudent.setId(1L);
        testStudent.setName("John Doe");
        testStudent.setGroup(testGroup);

        testLesson = new Lesson();
        testLesson.setId(1L);
        testLesson.setName("Math Lesson");
        testLesson.setLessonDatetime(LocalDateTime.now().plusHours(1));
        testLesson.setGroups(Set.of(testGroup));

        testCheckin = new Checkin();
        testCheckin.setId(1L);
        testCheckin.setStudent(testStudent);
        testCheckin.setLesson(testLesson);
    }

//    @Test
//    void testMarkAttendance_Success() {
//        when(checkinRepository.existsByLessonIdAndStudentId(1L, 1L)).thenReturn(false);
//        when(lessonRepository.findById(1L)).thenReturn(Optional.of(testLesson));
//        when(studentRepository.findById(1L)).thenReturn(Optional.of(testStudent));
//        when(checkinRepository.save(any(Checkin.class))).thenReturn(testCheckin);
//
//        Checkin result = checkinService.markAttendance(1L, 1L);
//
//        assertNotNull(result);
//        assertEquals(testStudent, result.getStudent());
//        assertEquals(testLesson, result.getLesson());
//        verify(checkinRepository).save(any(Checkin.class));
//    }
//
//    @Test
//    void testMarkAttendance_AlreadyCheckedIn() {
//        when(checkinRepository.existsByLessonIdAndStudentId(1L, 1L)).thenReturn(true);
//
//        assertThrows(RuntimeException.class, () -> checkinService.markAttendance(1L, 1L));
//        verify(checkinRepository, never()).save(any(Checkin.class));
//    }
//
//    @Test
//    void testMarkAttendance_LessonNotFound() {
//        when(checkinRepository.existsByLessonIdAndStudentId(1L, 1L)).thenReturn(false);
//        when(lessonRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(RuntimeException.class, () -> checkinService.markAttendance(1L, 1L));
//        verify(checkinRepository, never()).save(any(Checkin.class));
//    }
//
//    @Test
//    void testMarkAttendance_StudentNotFound() {
//        when(checkinRepository.existsByLessonIdAndStudentId(1L, 1L)).thenReturn(false);
//        when(lessonRepository.findById(1L)).thenReturn(Optional.of(testLesson));
//        when(studentRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(RuntimeException.class, () -> checkinService.markAttendance(1L, 1L));
//        verify(checkinRepository, never()).save(any(Checkin.class));
//    }
//
//    @Test
//    void testMarkAttendance_StudentNotInLessonGroup() {
//        StudyGroup otherGroup = new StudyGroup();
//        otherGroup.setId(2L);
//        otherGroup.setName("Group B");
//
//        Student otherStudent = new Student();
//        otherStudent.setId(2L);
//        otherStudent.setName("Jane Doe");
//        otherStudent.setGroup(otherGroup);
//
//        when(checkinRepository.existsByLessonIdAndStudentId(1L, 2L)).thenReturn(false);
//        when(lessonRepository.findById(1L)).thenReturn(Optional.of(testLesson));
//        when(studentRepository.findById(2L)).thenReturn(Optional.of(otherStudent));
//
//        assertThrows(RuntimeException.class, () -> checkinService.markAttendance(1L, 2L));
//        verify(checkinRepository, never()).save(any(Checkin.class));
//    }
//
//    @Test
//    void testGetStudentAttendanceCount() {
//        when(checkinRepository.countByStudentId(1L)).thenReturn(5L);
//
//        long result = checkinService.getStudentAttendanceCount(1L);
//
//        assertEquals(5L, result);
//        verify(checkinRepository).countByStudentId(1L);
//    }
//
//    @Test
//    void testGetStudentAttendanceCount_NoAttendance() {
//        when(checkinRepository.countByStudentId(1L)).thenReturn(0L);
//
//        long result = checkinService.getStudentAttendanceCount(1L);
//
//        assertEquals(0L, result);
//        verify(checkinRepository).countByStudentId(1L);
//    }
}
