package com.example.demo.mapper;

import com.example.demo.entity.ScheduleInfoEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * @author hello
 */
@Mapper
public interface ScheduleInfoMapper {

    @Insert("INSERT INTO schedule_info(class_num,student_id) VALUES(#{class_num},#{student_id})")
    @Options(useGeneratedKeys = true,keyProperty = "schedule_info_id",keyColumn = "schedule_info_id")
    void add (ScheduleInfoEntity scheduleInfoEntity);

    @Select("SELECT class_num FROM schedule_info WHERE student_id  = #{student_id}")
    ArrayList<String> select(String student_id);
}
