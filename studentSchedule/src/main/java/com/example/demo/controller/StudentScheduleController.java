package com.example.demo.controller;

import com.example.demo.entity.ReturnClassJson;
import com.example.demo.entity.ScheduleInfoEntity;
import com.example.demo.service.ClassService;
import com.example.demo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @author hello
 */
@RestController
@RequestMapping(value = "/schedule")
public class StudentScheduleController {

    @Autowired
    ClassService classService;
    @Autowired
    ScheduleService scheduleService;

    @RequestMapping(value = "/findElective", method = RequestMethod.POST)
    public ArrayList<ReturnClassJson> findElective() {
        return classService.selectAllElectiveClass();
    }

    @RequestMapping(value = "/findStudentSchedule", method = RequestMethod.POST)
    public ArrayList<ReturnClassJson> findStudentSchedule(String studentId) {
        return scheduleService.selectStuAllSchedule(studentId);
    }

    @RequestMapping(value = "/addNew", method = RequestMethod.POST)
    public Object findStudentSchedule(String studentId, String classNum) {
        if (scheduleService.addClassOK(studentId, classNum)) {
            scheduleService.add(new ScheduleInfoEntity(classNum, studentId));
            return "ok";
        } else {
            return "时间冲突";
        }
    }
}
