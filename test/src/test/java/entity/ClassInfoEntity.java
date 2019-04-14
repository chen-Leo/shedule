package entity;

import lombok.Data;

/**
 * 课程信息
 */
@Data
public class ClassInfoEntity {
    private int class_info_id;

    private String class_num;//教学班号--->主键,在课表中加入课也是以此为准
    private String class_teach_kind; //教学性质分类
    private String class_name;//课程名
    private String class_kind;//必修还其他什么
    private String class_time;//描述性上课时间->用来区分同一节课的不同时间与地点
    private String teacher;//教师名
    private String week;//星期数
    private String joint_num;//节数
    private String week_all;//上课周数
    private String class_statue;//选课状态
    private String place;//地点


    public ClassInfoEntity(String classNum, String classTeachKind,
                           String className, String classKind, String classTime, String teacher,
                           String week, String jointNum, String weekAll,
                           String classStatue, String place) {
        this.class_num = classNum;
        this.class_teach_kind = classTeachKind;
        this.class_name = className;
        this.class_kind = classKind;
        this.class_time = classTime;
        this.teacher = teacher;
        this.week = week;
        this.joint_num = jointNum;
        this.week_all = weekAll;
        this.class_statue = classStatue;
        this.place = place;
    }

    public ClassInfoEntity(int class_info_id,String classNum, String classTeachKind, String className, String classKind, String classTime, String teacher, String week, String jointNum, String weekAll, String classStatue, String place) {
        this.class_info_id = class_info_id;
        this.class_num = classNum;
        this.class_teach_kind = classTeachKind;
        this.class_name = className;
        this.class_kind = classKind;
        this.class_time = classTime;
        this.teacher = teacher;
        this.week = week;
        this.joint_num = jointNum;
        this.week_all = weekAll;
        this.class_statue = classStatue;
        this.place = place;
    }

}


