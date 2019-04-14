package com.example.demo.mapper;

import com.example.demo.entity.StudentInfoEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * @author hello
 */
@Mapper
public interface StudentInfoMapper {

   @Insert("INSERT INTO student_info(student_id,student_name) VALUES(#{student_id},#{student_name})")
   @Options(useGeneratedKeys = true,keyProperty = "student_info_id",keyColumn = "student_info_id")
   void add(StudentInfoEntity studentInfoEntity);

}
