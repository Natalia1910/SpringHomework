package com.lits.SpringHomework.service.impl;

import com.lits.SpringHomework.dto.CourseDto;
import com.lits.SpringHomework.exception.CourseNotFoundException;
import com.lits.SpringHomework.model.Course;
import com.lits.SpringHomework.model.Student;
import com.lits.SpringHomework.model.Teacher;
import com.lits.SpringHomework.repository.CourseRepository;
import com.lits.SpringHomework.repository.StudentRepository;
import com.lits.SpringHomework.repository.TeacherRepository;
import com.lits.SpringHomework.service.CourseService;
import com.lits.SpringHomework.util.StatusOfCourse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.lang.reflect.Type;

import static java.util.stream.Collectors.toList;


@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, TeacherRepository teacherRepository,
                             StudentRepository studentRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long create(CourseDto courseDto) {
        return courseRepository.save(modelMapper.map(courseDto, Course.class)).getId();
    }

    @Override
    public CourseDto findOneById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException());
        return modelMapper.map(course, CourseDto.class);
    }

    @Override
    public List<CourseDto> findAll() {
        List<Course> courses = (List<Course>) courseRepository.findAll();
        return courses.stream().map(c -> modelMapper.map(c, CourseDto.class)).collect(toList());
    }

    @Override
    public List<CourseDto> getFilteredCourses(StatusOfCourse statusOfCourse) {
        Type listType = new TypeToken<List<CourseDto>>(){}.getType();
        List<CourseDto> courseDtos;
        switch (statusOfCourse){
            case NOT_STARTED:
                courseDtos = modelMapper.map(courseRepository.findAllByStartDateGreaterThan(LocalDate.now()), listType);
                break;
            case IN_PROCESS:
                courseDtos = modelMapper.map(courseRepository.findAllByEndDateLessThan(LocalDate.now()), listType);
                break;
            case FINISHED:
                courseDtos = modelMapper.map(courseRepository
                        .findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate.now(), LocalDate.now()), listType);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + statusOfCourse);
        }

        return courseDtos;
    }

    @Override
    public List<CourseDto> findAllCoursesAssignedToTeacher(Long teacherId) {
        Teacher teacher = (Teacher) teacherRepository.findOneById(teacherId);
        List<Course> courses = courseRepository.findAllByTeachersContaining(teacher);
        return courses.stream().map(c -> modelMapper.map(c, CourseDto.class)).collect(toList());
    }

    @Override
    public CourseDto assignTeacherToCourse(Long courseId, Long teacherId) {
        Course course = (Course) courseRepository.findOneById(courseId);
        Teacher teacher = (Teacher) teacherRepository.findOneById(teacherId);
        course.getTeachers().add(teacher);
        return update(modelMapper.map(course, CourseDto.class));
    }

    @Override
    public CourseDto unassignTeacherFromCourse(Long courseId, Long teacherId) {
        Course course = (Course) courseRepository.findOneById(courseId);
        Teacher teacher = (Teacher) teacherRepository.findOneById(teacherId);
        course.getTeachers().remove(teacher);
        return update(modelMapper.map(course, CourseDto.class));
    }

    @Override
    public List<CourseDto> getCoursesThatLast(int numberOfDays) {
        Type listType = new TypeToken<List<CourseDto>>(){}.getType();
        return modelMapper.map(courseRepository.findAllByDateDiffBetweenStartDateAndEndDateEqualTo(numberOfDays), listType);
    }

    @Override
    public void delete(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    public CourseDto update(CourseDto courseDto) {
        Course course = modelMapper.map(courseDto, Course.class);
        return modelMapper.map(courseRepository.save(course), CourseDto.class);
    }

    @Override
    public List<CourseDto> findAllCoursesAssignedToStudent(Long studentId) {
        Student student = (Student) studentRepository.findOneById(studentId);
        List<Course> courses = courseRepository.findAllByStudentsContaining(student);
        return courses.stream().map(c -> modelMapper.map(c, CourseDto.class)).collect(toList());
    }

    @Override
    public List<CourseDto> findAllCoursesWithAssignedTeacherAndStudent(Long teacherId, Long studentId) {
        Teacher teacher = (Teacher) teacherRepository.findOneById(teacherId);
        Student student = (Student) studentRepository.findOneById(studentId);
        List<Course> courses = courseRepository.findAllByTeachersContainingAndStudentsContaining(teacher, student);
        return courses.stream().map(c -> modelMapper.map(c, CourseDto.class)).collect(toList());
    }

    @Override
    public CourseDto assignStudentToCourse(Long courseId, Long studentId) {
        Course course = (Course) courseRepository.findOneById(courseId);
        Student student = (Student) studentRepository.findOneById(studentId);
        course.getStudents().add(student);
        return update(modelMapper.map(course, CourseDto.class));
    }

    @Override
    public CourseDto unassignStudentFromCourse(Long courseId, Long studentId) {
        Course course = (Course) courseRepository.findOneById(courseId);
        Student student = (Student) studentRepository.findOneById(studentId);
        course.getStudents().remove(student);
        return update(modelMapper.map(course, CourseDto.class));
    }
}
