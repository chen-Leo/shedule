package com.example.demo.entity;

import lombok.Data;

/**
 *课表信息
 * @author hello
 */
@Data
public class ClassInfoEntity {

    private int classInfoId;
    /**
     * 教学班号--->主键,在课表中加入课也是以此为准
     */
    private String classNum;
    /**
     * 教学性质分类
     */
    private String classTeachKind;
    /**
     * 课程名
     */
    private String className;
    /**
     * 必修还其他什么
     */
    private String classKind;
    /**
     * 描述性上课时间->用来区分同一节课的不同时间与地点
     */
    private String classTime;
    /**
     * 教师名
     */
    private String teacher;
    /**
     *星期数
     */
    private String week;
    /**
     * 节数
     */
    private String jointNum;
    /**
     * 上课周数
     */
    private String weekAll;

    /**
     * 选课状态
     */
    private String classStatue;
    /**
     * 地点
     */
    private String place;


    public ClassInfoEntity() {
    }

    public ClassInfoEntity(String classNum, String classTeachKind,
                           String className, String classKind, String classTime, String teacher,
                           String week, String jointNum, String weekAll,
                           String classStatue, String place) {
        this.classNum = classNum;
        this.classKind = classKind;
        this.classTeachKind = classTeachKind;
        this.className = className;
        this.classTime = classTime;
        this.teacher = teacher;
        this.week = week;
        this.jointNum = jointNum;
        this.weekAll = weekAll;
        this.classStatue = classStatue;
        this.place = place;
    }

}


