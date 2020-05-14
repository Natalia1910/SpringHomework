package com.lits.SpringHomework.unit.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lits.SpringHomework.dto.TeacherDto;
import com.lits.SpringHomework.model.Teacher;
import com.lits.SpringHomework.repository.CourseRepository;
import com.lits.SpringHomework.repository.TeacherRepository;
import com.lits.SpringHomework.service.TeacherService;
import com.lits.SpringHomework.service.impl.TeacherServiceImpl;
import com.lits.SpringHomework.utils.ParseDataUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;

@RunWith(MockitoJUnitRunner.class)
public class TeacherServiceImplTest {
    private TeacherService teacherService;

    @Mock
    private TeacherRepository teacherRepository;
    @Mock
    private CourseRepository courseRepository;

    public void init() {
        teacherService = new TeacherServiceImpl(teacherRepository, courseRepository, new ModelMapper());
    }

/*    @Test
    public void createTeacher_CreateNewTeacher_ReturnNewTeacher() throws IOException {
        // Arrange
        init();
        Teacher teacher = ParseDataUtils.prepareData("unit/service/teacher/create_teacher_positive/" +
                        "positive_data.json", new TypeReference<>() {});
        TeacherDto expected = ParseDataUtils.prepareData("unit/service/teacher/create_teacher_positive/" +
                        "result.json", new TypeReference<>() {});
        Mockito.when(teacherRepository.save(teacher).thenReturn(teacher.getId());

        // Act
        TeacherDto result = teacherService.create();

        // Assert
        Assertions.assertThat(result).isEqualTo(expected);
    }*/

    @Test
    public void findAll_RetrieveAllTeachers_ReturnAllTeachers() throws IOException {
        // Arrange
        init();

        List<Teacher> teachers = ParseDataUtils.prepareData("unit/service/teacher/find_all_positive/" +
                        "positive_data.json", new TypeReference<>() {});
        List<TeacherDto> expected = ParseDataUtils.prepareData("unit/service/teacher/find_all_positive/" +
                        "result.json", new TypeReference<>() {});

        Mockito.when(teacherRepository.findAll()).thenReturn(teachers);

        // Act
        List<TeacherDto> actual = teacherService.findAll();

        // Assert
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findOneById_RetrieveTeacher_ReturnTeacher() throws IOException {
        // Arrange
        init();
        Teacher teacher = ParseDataUtils.prepareData("unit/service/teacher/find_one_positive/" +
                        "positive_data.json", new TypeReference<>() {});
        TeacherDto expected = ParseDataUtils.prepareData("unit/service/teacher/find_one_positive/" +
                        "result.json", new TypeReference<>() {});
        Mockito.when(teacherRepository.findById(eq(1L))).thenReturn(Optional.of(teacher));

        // Act
        TeacherDto result = teacherService.findOneById(1L);

        // Assert
        Assertions.assertThat(result).isEqualTo(expected);
    }

}
