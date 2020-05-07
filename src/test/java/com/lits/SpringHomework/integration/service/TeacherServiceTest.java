package com.lits.SpringHomework.integration.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lits.SpringHomework.dto.TeacherDto;
import com.lits.SpringHomework.model.Teacher;
import com.lits.SpringHomework.repository.TeacherRepository;
import com.lits.SpringHomework.service.TeacherService;
import com.lits.SpringHomework.utils.ParseDataUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherServiceTest {
    @Autowired
    private TeacherService teacherService;
    @MockBean
    private TeacherRepository teacherRepository;

    @Test
    public void findAll_RetrieveTeachers_ReturnTeachers() throws IOException {
        // Arrange
        List<Teacher> teachers = ParseDataUtils.prepareData("integration/service/teacher/find_all_positive/" +
                "positive_data.json", new TypeReference<>() {});
        List<TeacherDto> expected = ParseDataUtils.prepareData("integration/service/teacher/find_all_positive/" +
                "result.json", new TypeReference<>() {});

        Mockito.when(teacherRepository.findAll()).thenReturn(teachers);

        // Act
        List<TeacherDto> result = teacherService.findAll();

        // Assert
        Assertions.assertThat(result).isEqualTo(expected);
    }

  /*      @Test
    public void createTeacher_CreateNewTeacher_ReturnNewTeacher() throws IOException {
        // Arrange
        Teacher teacher = ParseDataUtils.prepareData("unit/service/teacher/create_teacher_positive/" +
                        "positive_data.json", new TypeReference<>() {});
        TeacherDto expected = ParseDataUtils.prepareData("unit/service/teacher/create_teacher_positive/" +
                        "result.json", new TypeReference<>() {});
        Mockito.when(teacherRepository.save(eq(teacher))).thenReturn();

        // Act
        Long result = teacherService.create(expected);

        // Assert
        Assertions.assertThat(result).isEqualTo(expected);
    }*/
}
