package com.example.demo.entity;

import lombok.Data;

import java.util.ArrayList;

/**
 * 返回的课表json
 *
 * @author hello
 */
@Data
public class ReturnClassJson {

    private String studentId;
    private String classNum;
    private String classTeachKind;
    private String className;
    private String classKind;
    private String classTime;
    private String teacher;
    private String classStatue;
    private String place;
    private ArrayList<OtherTimePlaceTea> other;

    public ReturnClassJson() {
    }

    public ReturnClassJson(String studentId, String classNum, String classTeachKind, String className,
                           String classKind, String classTime, String teacher, String classStatue, String place) {
        this.studentId = studentId;
        this.classNum = classNum;
        this.classTeachKind = classTeachKind;
        this.className = className;
        this.classKind = classKind;
        this.classTime = classTime;
        this.teacher = teacher;
        this.classStatue = classStatue;
        this.place = place;
    }


    public ReturnClassJson( String classNum, String classTeachKind, String className,
                           String classKind, String classTime, String teacher, String classStatue, String place) {
        this.studentId = studentId;
        this.classNum = classNum;
        this.classTeachKind = classTeachKind;
        this.className = className;
        this.classKind = classKind;
        this.classTime = classTime;
        this.teacher = teacher;
        this.classStatue = classStatue;
        this.place = place;
    }

    public void otherAdd(String classTime,String teacher,String place){
       other.add(new OtherTimePlaceTea(classTime,teacher,place));
    }
}
