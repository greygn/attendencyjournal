package ru.krylov.attendencyjournal.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.krylov.attendencyjournal.entity.Student;
import ru.krylov.attendencyjournal.entity.StudyGroup;
import ru.krylov.attendencyjournal.repository.StudentRepository;
import ru.krylov.attendencyjournal.repository.StudyGroupRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudyGroupRepository groupRepository;

    @InjectMocks
    private StudentService studentService;

    private StudyGroup testGroup;
    private Student testStudent;

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
    }

    @Test
    void testCreateStudent_Success() {
        when(groupRepository.findById(1L)).thenReturn(Optional.of(testGroup));
        when(studentRepository.save(any(Student.class))).thenReturn(testStudent);

        Student result = studentService.create("John Doe", 1L);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals(testGroup, result.getGroup());
        verify(groupRepository).findById(1L);
        verify(studentRepository).save(any(Student.class));
    }

//    @Test
//    void testCreateStudent_GroupNotFound() {
//        when(groupRepository.findById(999L)).thenReturn(Optional.empty());
//
//        assertThrows(RuntimeException.class, () -> studentService.create("John Doe", 999L));
//        verify(groupRepository).findById(999L);
//    }
//
//    @Test
//    void testGetAllStudents() {
//        List<Student> studentList = List.of(testStudent);
//        when(studentRepository.findAll()).thenReturn(studentList);
//
//        List<Student> result = studentService.getAll();
//
//        assertEquals(1, result.size());
//        assertEquals("John Doe", result.get(0).getName());
//        verify(studentRepository).findAll();
//    }
//
//    @Test
//    void testGetAllStudents_Empty() {
//        when(studentRepository.findAll()).thenReturn(List.of());
//
//        List<Student> result = studentService.getAll();
//
//        assertEquals(0, result.size());
//        verify(studentRepository).findAll();
//    }
}
