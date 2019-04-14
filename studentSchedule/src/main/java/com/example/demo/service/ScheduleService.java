package com.example.demo.service;

        import com.example.demo.entity.ClassInfoEntity;
        import com.example.demo.entity.ReturnClassJson;
        import com.example.demo.entity.ScheduleInfoEntity;
        import com.example.demo.mapper.ClassInfoMapper;
        import com.example.demo.mapper.ScheduleInfoMapper;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.ArrayList;

/**
 * @author hello
 */
@Service
public class ScheduleService {

    ScheduleInfoMapper scheduleInfoMapper;

    @Autowired
    ClassService classService;

    public void add(ScheduleInfoEntity scheduleInfoEntity){
        scheduleInfoMapper.add(scheduleInfoEntity);
    }

    public ArrayList<ReturnClassJson> selectStuAllSchedule(String studentId){
        ArrayList<String> classNums =  scheduleInfoMapper.select(studentId);
        ArrayList<ReturnClassJson> returnClassJsons =  new ArrayList<>();
        for (String classNum: classNums){
            returnClassJsons.add(classService.ReturnClassJson(studentId,classNum));
        }
        return returnClassJsons;
    }

    public  boolean addClassOK(String studentId,String newClassNum) {
        boolean model = true;
        ArrayList<String> classNums = scheduleInfoMapper.select(studentId);
        ArrayList<ClassInfoEntity> newClass  = classService.selectClass(newClassNum);
        ArrayList<ClassInfoEntity> oldClass =  new ArrayList<>();
        for (String classNum : classNums) {
            oldClass.addAll(classService.selectClass(classNum));
        }
        for (ClassInfoEntity newClassInfoEntity : newClass){
            for (ClassInfoEntity oldClassInfoEntity:oldClass){
                if (newClassInfoEntity.getWeek().equals(oldClassInfoEntity.getWeek())) {
                    String newJointNum = newClassInfoEntity.getJointNum();
                    String oldJointNum = oldClassInfoEntity.getJointNum();
                    while (newJointNum.length()!=0) {
                        int s = newJointNum.indexOf(',');
                        if (oldJointNum.indexOf(newJointNum.substring(0, s)) != -1) {
                            newJointNum = newJointNum.replaceAll(newJointNum.substring(0, s + 1), "");
                        }
                    }
                    if (newJointNum.length() == 0){
                        String newWeekAll = newClassInfoEntity.getWeekAll();
                        String oldWeekAll = oldClassInfoEntity.getWeekAll();
                        while (newWeekAll.length()!=0) {
                            int s = newJointNum.indexOf(',');
                            if (oldWeekAll.indexOf(newWeekAll.substring(0, s)) != -1) {
                                newWeekAll = newWeekAll.replaceAll(newWeekAll.substring(0, s + 1), "");
                            }
                            if(newWeekAll.length() != 0) {model = false;}
                        }
                    }
                    else{model = false;}
                }
            }
        }
        return model;
    }
}
