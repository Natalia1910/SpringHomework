package com.lits.SpringHomework.dto;


import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;
@Data
public class CourseDto {
    private Long id;
    private String name;

    @FutureOrPresent
    private Date startDate;

    @PastOrPresent
    private Date endDate;

}
