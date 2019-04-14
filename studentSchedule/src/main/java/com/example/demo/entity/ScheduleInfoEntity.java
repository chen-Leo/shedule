package com.example.demo.entity;

import lombok.Data;

/**
 *学生课表详细信息
 * @author hello
 */
@Data
public class ScheduleInfoEntity {

    private int schedule_info_id;
    private String class_num;
    private String studentId;


    public ScheduleInfoEntity(){

    }

    public  ScheduleInfoEntity(String classNum,String studentId)
                              {

        this.class_num = classNum;
        this.studentId = studentId;

    }
 }
