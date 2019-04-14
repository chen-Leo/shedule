package com.example.demo.service;

import com.example.demo.entity.ClassInfoEntity;
import com.example.demo.entity.ReturnClassJson;
import com.example.demo.mapper.ClassInfoMapper;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author hello
 */
@Service
public class ClassService {

    ClassInfoMapper classInfoMapper;

    public void add(ClassInfoEntity classInfoEntity) {
        classInfoMapper.add(classInfoEntity);
    }

    /**
     * 查找是否存在已经添加了的课程
     * @param class_num
     * @return true(不存在) or false(存在)
     */
    public boolean selectExist(String class_num, String time, String place) {
        boolean model = true;
        ArrayList<ClassInfoEntity> classInfoEntityArrayList = classInfoMapper.select(class_num);
        if (classInfoEntityArrayList.size() == 0) {
            return false;
        } else {
            for (ClassInfoEntity classInfoEntity :
                    classInfoEntityArrayList) {
                if (classInfoEntity.getClassTime().equals(time) || classInfoEntity.getPlace().equals(place)) {
                    model = true;
                }
            }
        }
        return model;
    }


    public ReturnClassJson ReturnClassJson(String studentId, String class_num) {
        boolean model = true;
        ArrayList<ClassInfoEntity> classInfoEntityArrayList = classInfoMapper.select(class_num);
        ReturnClassJson returnJson = null;
        if (classInfoEntityArrayList.size() == 1) {
            returnJson = new ReturnClassJson(studentId, classInfoEntityArrayList.get(0).getClassNum(),
                    classInfoEntityArrayList.get(0).getClassTeachKind(), classInfoEntityArrayList.get(0).getClassName(),
                    classInfoEntityArrayList.get(0).getClassKind(), classInfoEntityArrayList.get(0).getClassTime(),
                    classInfoEntityArrayList.get(0).getTeacher(), classInfoEntityArrayList.get(0).getClassStatue(),
                    classInfoEntityArrayList.get(0).getPlace()
            );
        } else {
            returnJson = new ReturnClassJson(studentId, classInfoEntityArrayList.get(0).getClassNum(),
                    classInfoEntityArrayList.get(0).getClassTeachKind(), classInfoEntityArrayList.get(0).getClassName(),
                    classInfoEntityArrayList.get(0).getClassKind(), classInfoEntityArrayList.get(0).getClassTime(),
                    classInfoEntityArrayList.get(0).getTeacher(), classInfoEntityArrayList.get(0).getClassStatue(),
                    classInfoEntityArrayList.get(0).getPlace()
            );
            classInfoEntityArrayList.remove(0);
            for (ClassInfoEntity classInfoEntity :
                    classInfoEntityArrayList) {
                returnJson.otherAdd(classInfoEntity.getClassTime(), classInfoEntity.getTeacher(), classInfoEntity.getPlace());
            }
        }
        return returnJson;
    }


    public ArrayList<ReturnClassJson> selectAllElectiveClass(){
        ArrayList<String> classNums = classInfoMapper.selectAllElective();
                ArrayList<ReturnClassJson> returnClassJsons =  new ArrayList<>();
        for (String classNum: classNums){
            returnClassJsons.add(ReturnClassJson(classNum));
        }
        return returnClassJsons;
    }

    public ArrayList<ClassInfoEntity> selectClass(String classNum){
     return  classInfoMapper.select(classNum);
    }

    private ReturnClassJson ReturnClassJson(String class_num) {
        boolean model = true;
        ArrayList<ClassInfoEntity> classInfoEntityArrayList = classInfoMapper.select(class_num);
        ReturnClassJson returnJson = null;
        if (classInfoEntityArrayList.size() == 1) {
            returnJson = new ReturnClassJson(classInfoEntityArrayList.get(0).getClassNum(),
                    classInfoEntityArrayList.get(0).getClassTeachKind(), classInfoEntityArrayList.get(0).getClassName(),
                    classInfoEntityArrayList.get(0).getClassKind(), classInfoEntityArrayList.get(0).getClassTime(),
                    classInfoEntityArrayList.get(0).getTeacher(), classInfoEntityArrayList.get(0).getClassStatue(),
                    classInfoEntityArrayList.get(0).getPlace()
            );
        } else {
            returnJson = new ReturnClassJson( classInfoEntityArrayList.get(0).getClassNum(),
                    classInfoEntityArrayList.get(0).getClassTeachKind(), classInfoEntityArrayList.get(0).getClassName(),
                    classInfoEntityArrayList.get(0).getClassKind(), classInfoEntityArrayList.get(0).getClassTime(),
                    classInfoEntityArrayList.get(0).getTeacher(), classInfoEntityArrayList.get(0).getClassStatue(),
                    classInfoEntityArrayList.get(0).getPlace()
            );
            classInfoEntityArrayList.remove(0);
            for (ClassInfoEntity classInfoEntity :
                    classInfoEntityArrayList) {
                returnJson.otherAdd(classInfoEntity.getClassTime(), classInfoEntity.getTeacher(), classInfoEntity.getPlace());
            }
        }
        return returnJson;
    }




}
