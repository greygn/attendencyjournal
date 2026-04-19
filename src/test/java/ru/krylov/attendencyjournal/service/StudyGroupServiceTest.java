package ru.krylov.attendencyjournal.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.krylov.attendencyjournal.entity.StudyGroup;
import ru.krylov.attendencyjournal.repository.StudyGroupRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudyGroupServiceTest {

    @Mock
    private StudyGroupRepository repository;

    @InjectMocks
    private StudyGroupService studyGroupService;

    private StudyGroup testGroup;

    @BeforeEach
    void setUp() {
        testGroup = new StudyGroup();
        testGroup.setId(1L);
        testGroup.setName("Group A");
        testGroup.setCourse(1);
    }

    @Test
    void testCreateGroup_Success() {
        when(repository.save(any(StudyGroup.class))).thenReturn(testGroup);

        StudyGroup result = studyGroupService.create("Group A", 1);

        assertNotNull(result);
        assertEquals("Group A", result.getName());
        assertEquals(1, result.getCourse());
        verify(repository).save(any(StudyGroup.class));
    }

    @Test
    void testCreateGroup_DifferentCourse() {
        StudyGroup group2 = new StudyGroup();
        group2.setId(2L);
        group2.setName("Group B");
        group2.setCourse(2);

        when(repository.save(any(StudyGroup.class))).thenReturn(group2);

        StudyGroup result = studyGroupService.create("Group B", 2);

        assertNotNull(result);
        assertEquals("Group B", result.getName());
        assertEquals(2, result.getCourse());
        verify(repository).save(any(StudyGroup.class));
    }

    @Test
    void testGetAllGroups() {
        List<StudyGroup> groupList = List.of(testGroup);
        when(repository.findAll()).thenReturn(groupList);

        List<StudyGroup> result = studyGroupService.getAll();

        assertEquals(1, result.size());
        assertEquals("Group A", result.get(0).getName());
        verify(repository).findAll();
    }

    @Test
    void testGetAllGroups_Empty() {
        when(repository.findAll()).thenReturn(List.of());

        List<StudyGroup> result = studyGroupService.getAll();

        assertEquals(0, result.size());
        verify(repository).findAll();
    }

    @Test
    void testGetAllGroups_Multiple() {
        StudyGroup group2 = new StudyGroup();
        group2.setId(2L);
        group2.setName("Group B");
        group2.setCourse(2);

        List<StudyGroup> groupList = List.of(testGroup, group2);
        when(repository.findAll()).thenReturn(groupList);

        List<StudyGroup> result = studyGroupService.getAll();

        assertEquals(2, result.size());
        assertEquals("Group A", result.get(0).getName());
        assertEquals("Group B", result.get(1).getName());
        verify(repository).findAll();
    }
}
