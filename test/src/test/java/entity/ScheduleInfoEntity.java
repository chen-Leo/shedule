package entity;

import lombok.Data;

/**
 * 学生课表详细信息
 */
@Data
public class ScheduleInfoEntity {

    private int schedule_info_id;

    private String class_num;//教学班号--->主键,在课表中加入课也是以此为准
    private String studentId;


    public ScheduleInfoEntity(){

    }

    public  ScheduleInfoEntity(String classNum,String studentId)
                              {

        this.class_num = classNum;
        this.studentId = studentId;

    }
 }
