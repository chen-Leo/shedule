package crawler;

import entity.ClassInfoEntity;
import entity.ScheduleInfoEntity;
import entity.StudentInfoEntity;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class htmlFilterClassesUtils {


    //处理学生课表
    public static void htmlFilterClasses(String html,String studentId,DatabaseUtils databaseUtils) throws SQLException {


        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(html);
        html = m.replaceAll("");

        p = Pattern.compile("<divid=\"kbStuTabs-list\"><divclass=\"printTable\">\\S+?</table></div>");
        m = p.matcher(html);

        if (m.find()) {
            html = m.group();
            p = Pattern.compile("<tr><tdrowspan=\\S+?</tr>(<tr><td>\\S+?</td></tr><tr><tdrow)?");
            m = p.matcher(html);
            while (m.find()) {
                String goal = m.group();
                String html2 = m.group();
                p = Pattern.compile("<tr><td>\\S+?</td></tr><tr><tdrow");
                m = p.matcher(html2);

                if (m.find()) {
                    //这里了处理多时间与多地点问题
                    ArrayList<String[]> otherTimePlace = getOtherTimePlace(m.group());

                    html = html.replace(goal, "<tr><tdrow");

                    String[] classTeachKind = getClassTeachKind(goal);
                    String[] className = getClassName(classTeachKind[0]);
                    String[] classNum = getClassNum(className[0]);
                    String[] classKind = getClassKind(classNum[0]);
                    String[] courseSelectionStatus = getCourseSelectionStatus(classKind[0]);
                    String[] teacherName = getTeacherName(courseSelectionStatus[0]);
                    String[] classTime = getClassTime(teacherName[0]);
                    String[] place = getPlace(classTime[0]);

                    for (String[] rest : otherTimePlace
                    ) {

                        if (!databaseUtils.selectExist(classNum[1], rest[5], rest[4])) {
                            ClassInfoEntity classInfoEntity = new ClassInfoEntity(classNum[1], classTeachKind[1], className[1], classKind[1], rest[5], rest[0], rest[1], rest[2], rest[3], courseSelectionStatus[1], rest[4]);
                            //....
                            databaseUtils.insertClassInfo(classInfoEntity);
                        }
                        ScheduleInfoEntity scheduleInfoEntity = new ScheduleInfoEntity(classNum[1], studentId);
                        //....
                        databaseUtils.insertScheduleInfo(scheduleInfoEntity);
                    }

                    if (!databaseUtils.selectExist(classNum[1], classTime[4], place[1])) {
                        ClassInfoEntity classInfoEntity = new ClassInfoEntity(classNum[1], classTeachKind[1], className[1], classKind[1], classTime[4], teacherName[1], classTime[1], classTime[2], classTime[3], courseSelectionStatus[1], place[1]);
                        //....
                        databaseUtils.insertClassInfo(classInfoEntity);

                    }

                    ScheduleInfoEntity scheduleInfoEntity = new ScheduleInfoEntity(classNum[1], studentId);
                    //....
                    databaseUtils.insertScheduleInfo(scheduleInfoEntity);

                } else {
                    html = html.replace(goal, "");
                    String[] classTeachKind = getClassTeachKind(goal);
                    String[] className = getClassName(classTeachKind[0]);
                    String[] classNum = getClassNum(className[0]);
                    String[] classKind = getClassKind(classNum[0]);
                    String[] courseSelectionStatus = getCourseSelectionStatus(classKind[0]);
                    String[] teacherName = getTeacherName(courseSelectionStatus[0]);
                    String[] classTime = getClassTime(teacherName[0]);
                    String[] place = getPlace(classTime[0]);



                    if (!databaseUtils.selectExist(classNum[1], classTime[4], place[1])) {
                        ClassInfoEntity classInfoEntity = new ClassInfoEntity(classNum[1], classTeachKind[1], className[1], classKind[1], classTime[4], teacherName[1], classTime[1], classTime[2], classTime[3], courseSelectionStatus[1], place[1]);
                        //
                        databaseUtils.insertClassInfo(classInfoEntity);
                    }
                    ScheduleInfoEntity scheduleInfoEntity = new ScheduleInfoEntity(classNum[1], studentId);
                    //....
                    databaseUtils.insertScheduleInfo(scheduleInfoEntity);

                }
                p = Pattern.compile("<tr><tdrowspan=\\S+?</tr>(<tr><td>\\S+?</td></tr><tr><tdrow)?");
                m = p.matcher(html);
            }
        }


    }

    public static boolean fileStudentMessage(String html,DatabaseUtils databaseUtils) throws SQLException{
        boolean model =false;
        String[] student = getStudentMessage(html);
        if (student!= null){

            //......
            databaseUtils.insertStudentInfo(new StudentInfoEntity(student[0],student[1]));
            model = true;
        }
        return  model;
    }


   private static String[] getStudentMessage(String html) {
        //获取课程性质
        Pattern p = Pattern.compile("<li>〉〉2018-2019学年2学期 学生课表>>\\S+?  </li>");
        Matcher m = p.matcher(html);
        if (m.find()) {
            String[] result = new String[2];
            int firstNum = m.group().indexOf("课表>>");
            int lastNum = m.group().indexOf("  </li>");
            String studentMessage = null;
            studentMessage = m.group().substring(firstNum + 4, lastNum);
            result[0] = studentMessage.substring(0, 10);
            result[1] = studentMessage.substring(10);
            if (result[1].equals("")) {
                return null;
            } else return result;
        } else return null;
    }


    private static String[] getClassTeachKind(String html) {
        //获取课程性质
        Pattern p = Pattern.compile("'>\\S+?</td>");
        Matcher m = p.matcher(html);
        String[] result = new String[2];
        if (m.find()) {
            int num = m.group().indexOf('<');
            String classTeachKind = null;
            classTeachKind = m.group().substring(2, num);

            result[1] = classTeachKind;
            result[0] = html.replaceAll(m.group(), "");
        }
        return result;
    }

    private static String[] getClassName(String html) {

        Pattern p = Pattern.compile(">\\w+\\x2D\\S+?</td>");
        Matcher m = p.matcher(html);
        String[] result = new String[2];
        if (m.find()) {
            int num = m.group().indexOf('<');
            String classTeachName = null;
            classTeachName = m.group().substring(1, num);

            result[1] = classTeachName;
            result[0] = html.replaceAll(m.group(), "");
        }
        return result;
    }

    private static String[] getClassNum(String html) {

        Pattern p = Pattern.compile(">\\w+?</td>");
        Matcher m = p.matcher(html);
        String[] result = new String[2];
        if (m.find()) {
            int num = m.group().indexOf('<');
            String classNum = null;
            classNum = m.group().substring(1, num);
            result[1] = classNum;
            result[0] = html.replaceAll(m.group(), "");
        }
        return result;
    }

    private static String[] getClassKind(String html) {
        Pattern p = Pattern.compile(">[\\u4e00-\\u9fa5]+?</td>");
        Matcher m = p.matcher(html);
        String[] result = new String[2];
        if (m.find()) {
            int num = m.group().indexOf('<');
            String classKind = null;
            classKind = m.group().substring(1, num);
            result[1] = classKind;
            result[0] = html.replaceAll(m.group(), "");
        }
        return result;
    }

    private static String[] getCourseSelectionStatus(String html) {
        Pattern p = Pattern.compile(">[\\u4e00-\\u9fa5]+?</td>");
        Matcher m = p.matcher(html);
        String[] result = new String[2];
        if (m.find()) {
            int num = m.group().indexOf('<');
            String courseSelectionStatus = null;
            courseSelectionStatus = m.group().substring(1, num);
            result[1] = courseSelectionStatus;
            result[0] = html.replaceAll(m.group(), "");
        }
        return result;
    }

    private static String[] getTeacherName(String html) {

        Pattern p = Pattern.compile("<td>\\S+?</td>");

        Matcher m = p.matcher(html);
        String[] result = new String[2];
        if (m.find()) {
            int num = m.group().indexOf("</td>");
            String teacherName = null;
            teacherName = m.group().substring(4, num);

            result[1] = teacherName;
            result[0] = html.replaceAll(m.group(), "");
        }
        return result;
    }

    private static String[] getClassTime(String html) {
        //获取上课时间
        Pattern p = Pattern.compile(">星\\S+?</td>");
        Matcher m = p.matcher(html);
        String goal = null;
        String[] result = new String[5];
        if (m.find()) {

            int num = m.group().indexOf("</td>");
            String classTime = m.group().substring(1, num);
            result[4] = classTime;//获取时间的大致描述

            html = html.replaceAll(m.group(), "");
            goal = m.group();


            //处理星期
            p = Pattern.compile("星期\\d");
            m = p.matcher(goal);
            if (m.find()) {
                int weekTime = Integer.parseInt(m.group().substring(2));
                result[1] = String.valueOf(weekTime);
            }

            //处理节数
            String nodeTime = new String();
            p = Pattern.compile("第\\d+-\\d+节");
            m = p.matcher(goal);
            if (m.find()) {

                int startN = Integer.parseInt(m.group().substring(1, m.group().indexOf('-')));
                int endN = Integer.parseInt(m.group().substring(m.group().indexOf('-') + 1, m.group().indexOf('节')));
                while (startN <= endN) {
                    if (startN < 10) {
                        nodeTime = nodeTime + "0" + startN + ',';
                    } else {
                        nodeTime = nodeTime + startN + ',';
                    }
                    startN++;
                }
                result[2] = nodeTime;
            }

            //处理周数
            String nodeWeek = new String();
            //处理5-10周单周（及可能的5-10周单周,11-14周双周)类型
            p = Pattern.compile("\\d+-\\d+周[单|双]");
            m = p.matcher(goal);
            while (m.find()) {
                int startN = Integer.parseInt(m.group().substring(0, m.group().indexOf('-')));
                int endN = Integer.parseInt(m.group().substring(m.group().indexOf('-') + 1, m.group().indexOf('周')));
                if (m.group().indexOf("单") != -1) {
                    while (startN <= endN) {
                        if (startN % 2 == 1) {
                            if (startN < 10) {
                                nodeWeek = nodeWeek + "0" + startN + ',';
                            } else {
                                nodeWeek = nodeWeek + startN + ',';
                            }
                        }
                        startN++;
                    }
                } else {
                    while (startN <= endN) {
                        if (startN % 2 == 0) {
                            if (startN < 10) {
                                nodeWeek = nodeWeek + "0" + startN + ',';
                            } else {
                                nodeWeek = nodeWeek + startN + ',';
                            }
                        }
                        startN++;
                    }
                }
                goal = goal.replaceAll(m.group(), "");
                m = p.matcher(goal);
            }
            //处理5-10周（及可能的5-10周,11-14周)类型
            p = Pattern.compile("\\d+-\\d+周");
            m = p.matcher(goal);
            while (m.find()) {
                int startN = Integer.parseInt(m.group().substring(0, m.group().indexOf('-')));
                int endN = Integer.parseInt(m.group().substring(m.group().indexOf('-') + 1, m.group().indexOf('周')));
                while (startN <= endN) {
                    if (startN < 10) {
                        nodeWeek = nodeWeek + "0" + startN + ',';
                    } else {
                        nodeWeek = nodeWeek + startN + ',';
                    }
                    startN++;
                }
                goal = goal.replaceAll(m.group(), "");
                m = p.matcher(goal);
            }
            //处理10周（及可能的5周,14周)类型
            p = Pattern.compile("\\d+周");
            m = p.matcher(goal);
            while (m.find()) {
                int startN = Integer.parseInt(m.group().substring(0, m.group().indexOf('周')));
                if (startN < 10) {
                    nodeWeek = nodeWeek + "0" + startN + ',';
                } else {
                    nodeWeek = nodeWeek + startN + ',';
                }
                goal = goal.replaceAll(m.group(), "");
                m = p.matcher(goal);
            }
            result[3] = nodeWeek;
            result[0] = html;
        }
        return result;
    }

    //获取上课地点
    private static String[] getPlace(String html) {
        Pattern p = Pattern.compile("<td>\\S+?</td>");
        Matcher m = p.matcher(html);
        String[] result = new String[2];
        if (m.find()) {
            String place = m.group().substring(m.group().indexOf(">") + 1, m.group().indexOf("/td") - 1);
            result[1] = place;
            result[0] = m.group().replaceAll(m.group(), "");
        }
        return result;
    }


    private static ArrayList<String[]> getOtherTimePlace(String html) {

        ArrayList<String[]> result = new ArrayList<String[]>();
        Pattern p = Pattern.compile("<tr><td>\\S+?</td></tr>");
        Matcher m = p.matcher(html);

        while (m.find()) {
            String[] name, time, place;
            String[] res = new String[6];
            String goal = m.group();
            name = getTeacherName(goal);
            time = getClassTime(name[0]);
            place = getPlace(time[0]);
            res[0] = name[1];
            res[1] = time[1];
            res[2] = time[2];
            res[3] = time[3];
            res[4] = place[1];
            res[5] = time[4];
            result.add(res);
            m = p.matcher(goal.replace(m.group(), ""));
        }
        return result;
    }
}






