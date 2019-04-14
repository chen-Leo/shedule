package com.example.demo.entity;

import lombok.Data;

/**
 * @author hello
 */
@Data
public class OtherTimePlaceTea {
    private String classTime;
    private String teacher;
    private String place;


    public OtherTimePlaceTea(){}

    public OtherTimePlaceTea(String classTime,String teacher,String place){
        this.classTime = classTime;
        this.teacher =teacher;
        this.place = place;
    }
}
