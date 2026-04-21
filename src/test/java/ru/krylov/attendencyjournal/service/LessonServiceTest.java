package ru.krylov.attendencyjournal.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.krylov.attendencyjournal.entity.Lesson;
import ru.krylov.attendencyjournal.entity.StudyGroup;
import ru.krylov.attendencyjournal.repository.LessonRepository;
import ru.krylov.attendencyjournal.repository.StudyGroupRepository;

import java.time.LocalDateTime;
import java.util.List;
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
class LessonServiceTest {

    @Mock
    private LessonRepository lessonRepository;

    @Mock
    private StudyGroupRepository groupRepository;

    @InjectMocks
    private LessonService lessonService;

    private StudyGroup testGroup;
    private Lesson testLesson;

    @BeforeEach
    void setUp() {
        testGroup = new StudyGroup();
        testGroup.setId(1L);
        testGroup.setName("Group A");
        testGroup.setCourse(1);

        testLesson = new Lesson();
        testLesson.setId(1L);
        testLesson.setName("Math Lesson");
        testLesson.setLessonDatetime(LocalDateTime.now().plusHours(1));
        testLesson.setGroups(Set.of(testGroup));
    }

//    @Test
//    void testCreateLesson_Success() {
//        when(groupRepository.findById(1L)).thenReturn(Optional.of(testGroup));
//        when(lessonRepository.save(any(Lesson.class))).thenReturn(testLesson);
//
//        Lesson result = lessonService.create("Math Lesson", LocalDateTime.now().plusHours(1), Set.of(1L));
//
//        assertNotNull(result);
//        assertEquals("Math Lesson", result.getName());
//        assertEquals(1, result.getGroups().size());
//        verify(groupRepository).findById(1L);
//        verify(lessonRepository).save(any(Lesson.class));
//    }
//
//    @Test
//    void testCreateLesson_PastDateTime() {
//        LocalDateTime pastDateTime = LocalDateTime.now().minusHours(1);
//
//        assertThrows(RuntimeException.class, () -> lessonService.create("Math Lesson", pastDateTime, Set.of(1L)));
//        verify(lessonRepository, never()).save(any(Lesson.class));
//    }
//
//    @Test
//    void testCreateLesson_GroupNotFound() {
//        when(groupRepository.findById(999L)).thenReturn(Optional.empty());
//
//        assertThrows(RuntimeException.class,
//                () -> lessonService.create("Math Lesson", LocalDateTime.now().plusHours(1), Set.of(999L)));
//        verify(lessonRepository, never()).save(any(Lesson.class));
//    }
//
//    @Test
//    void testCreateLesson_MultipleGroups() {
//        StudyGroup group2 = new StudyGroup();
//        group2.setId(2L);
//        group2.setName("Group B");
//        group2.setCourse(2);
//
//        Lesson lessonWithMultipleGroups = new Lesson();
//        lessonWithMultipleGroups.setId(2L);
//        lessonWithMultipleGroups.setName("Physics Lesson");
//        lessonWithMultipleGroups.setLessonDatetime(LocalDateTime.now().plusHours(2));
//        lessonWithMultipleGroups.setGroups(Set.of(testGroup, group2));
//
//        when(groupRepository.findById(1L)).thenReturn(Optional.of(testGroup));
//        when(groupRepository.findById(2L)).thenReturn(Optional.of(group2));
//        when(lessonRepository.save(any(Lesson.class))).thenReturn(lessonWithMultipleGroups);
//
//        Lesson result = lessonService.create("Physics Lesson", LocalDateTime.now().plusHours(2), Set.of(1L, 2L));
//
//        assertNotNull(result);
//        assertEquals("Physics Lesson", result.getName());
//        assertEquals(2, result.getGroups().size());
//        verify(groupRepository).findById(1L);
//        verify(groupRepository).findById(2L);
//        verify(lessonRepository).save(any(Lesson.class));
//    }
//
//    @Test
//    void testGetAllLessons() {
//        List<Lesson> lessonList = List.of(testLesson);
//        when(lessonRepository.findAll()).thenReturn(lessonList);
//
//        List<Lesson> result = lessonService.getAll();
//
//        assertEquals(1, result.size());
//        assertEquals("Math Lesson", result.get(0).getName());
//        verify(lessonRepository).findAll();
//    }
//
//    @Test
//    void testGetAllLessons_Empty() {
//        when(lessonRepository.findAll()).thenReturn(List.of());
//
//        List<Lesson> result = lessonService.getAll();
//
//        assertEquals(0, result.size());
//        verify(lessonRepository).findAll();
//    }
}
