package com.example.demo.mapper;

import com.example.demo.entity.ClassInfoEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;


@Mapper
public interface ClassInfoMapper {

    /**
     *加入一个新的课程信息
     * @param classInfoEntity
     */
    @Insert("INSERT INTO class_info (class_num,class_teach_kind,class_name," +
            "class_kind,class_time,teacher,week,join_num,week_all,class_statue,place) VALUES(#{class_num},#{class_teach_kind},#{class_name}.#{class_kind},#{class_time},#{teacher},#{week}," +
            "#{join_num},#{week_all},#{class_statue},#{place})")
    @Options(useGeneratedKeys = true, keyProperty = "class_info_id", keyColumn = "class_info_id")
    void add(ClassInfoEntity classInfoEntity);

    @Select("SELECT * FROM class_info WHERE class_num = #{classNum}")
    ArrayList<ClassInfoEntity> select(String classNum);

    /**
     *返回所有选修课程
     *
     * @return 所有选修的课程编号
     */
    @Select("SELECT class_num FROM class_info WHERE class_kind = \"选修\"")
    ArrayList<String> selectAllElective();



}
