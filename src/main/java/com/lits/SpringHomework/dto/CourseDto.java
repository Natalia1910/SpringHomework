package com.lits.SpringHomework.dto;

import java.util.Date;
import java.util.List;

public class CourseDto {
    private int id;
    private String name;
    private Date startDate;
    private Date endDate;

    private int teacherID;
    private List<Integer> teachersID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public List<Integer> getTeachersID() {
        return teachersID;
    }

    public void setTeachersID(List<Integer> teachersID) {
        this.teachersID = teachersID;
    }
}
