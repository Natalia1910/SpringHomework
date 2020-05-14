package com.lits.SpringHomework.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lits.SpringHomework.dto.TeacherDto;
import com.lits.SpringHomework.service.TeacherService;
import com.lits.SpringHomework.utils.ParseDataUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TeacherController.class)
public class TeacherControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TeacherService teacherService;

    @Mock
    ObjectMapper objectMapper = new ObjectMapper();

/*    @Test
    public void findAll() throws Exception {

        List<TeacherDto> expected = ParseDataUtils.prepareData("integration/service/teacher/find_all_positive/" +
                "positive_data.json", new TypeReference<>() {
        });
        Mockito.when(teacherService.findAll()).thenReturn(expected);

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/teachers/teacher/all")).andDo(print())
                .andExpect(status().isFound()).andReturn().getResponse().getContentAsString();

        List<TeacherDto> result = objectMapper.readValue(response, new TypeReference<>() {
        });

        Assertions.assertThat(result).isEqualTo(expected);

    }*/
}
