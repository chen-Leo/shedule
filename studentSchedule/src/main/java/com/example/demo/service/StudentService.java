package com.example.demo.service;

import com.example.demo.entity.StudentInfoEntity;
import com.example.demo.mapper.StudentInfoMapper;

import org.springframework.stereotype.Service;

/**
 * @author hello
 */
@Service
public class StudentService {

    StudentInfoMapper studentInfoMapper;

    public void add(StudentInfoEntity studentInfoEntity){
        studentInfoMapper.add(studentInfoEntity);
    }


}
