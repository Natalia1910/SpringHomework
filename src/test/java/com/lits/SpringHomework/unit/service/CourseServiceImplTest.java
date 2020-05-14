package com.lits.SpringHomework.unit.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lits.SpringHomework.dto.CourseDto;
import com.lits.SpringHomework.model.Course;
import com.lits.SpringHomework.repository.CourseRepository;
import com.lits.SpringHomework.service.CourseService;
import com.lits.SpringHomework.service.impl.CourseServiceImpl;
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
public class CourseServiceImplTest {
    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;

    public void init(){
        courseService = new CourseServiceImpl(courseRepository, new ModelMapper());
    }

    @Test
    public void findAll_RetrieveAllCourses_ReturnAllCourses() throws IOException {
        // Arrange
        init();
        List<Course> courses = ParseDataUtils.prepareData("unit/service/course/find_all_positive/" +
                "positive_data.json", new TypeReference<>() {});
        List<CourseDto> expected = ParseDataUtils.prepareData("unit/service/course/find_all_positive/" +
                "result.json", new TypeReference<>() {});
        Mockito.when(courseRepository.findAll()).thenReturn(courses);

        // Act
        List<CourseDto> result = courseService.findAll();

        // Assert
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    public void findOneById_RetrieveCourse_ReturnCourse() throws IOException {
        // Arrange
        init();
        Course course = ParseDataUtils.prepareData("unit/service/course/find_one_positive/" +
                "positive_data.json", new TypeReference<>() {});
        CourseDto expected = ParseDataUtils.prepareData("unit/service/course/find_one_positive/" +
                "result.json", new TypeReference<>() {});
        Mockito.when(courseRepository.findById(eq(1L))).thenReturn(Optional.of(course));

        // Act
        CourseDto result = courseService.findOneById(1L);

        // Assert

        Assertions.assertThat(result).isEqualTo(expected);

    }
}
