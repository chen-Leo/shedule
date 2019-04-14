package crawler;
import entity.ClassInfoEntity;
import entity.ScheduleInfoEntity;
import entity.StudentInfoEntity;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseUtils {
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;

    public Connection getCon() {
        return con;
    }

    public PreparedStatement getPreparedStatment(){
        return pstmt;
    }

    public ResultSet getRs() {
        return rs;
    }

    public DatabaseUtils(Connection con) {
        this.con = con;
    }


    public boolean insertClassInfo(ClassInfoEntity classInfoEntity)  throws SQLException{
        int i = 0;
        String sql = "INSERT INTO class_info (class_num,class_teach_kind,class_name,class_kind,class_time,teacher,week,join_num,week_all,class_statue,place) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

            pstmt =  con.prepareStatement(sql);
            pstmt.setString(1, classInfoEntity.getClass_num());
            pstmt.setString(2, classInfoEntity.getClass_teach_kind());
            pstmt.setString(3, classInfoEntity.getClass_name());
            pstmt.setString(4, classInfoEntity.getClass_kind());
            pstmt.setString(5, classInfoEntity.getClass_time());
            pstmt.setString(6, classInfoEntity.getTeacher());
            pstmt.setString(7, classInfoEntity.getWeek());
            pstmt.setString(8, classInfoEntity.getJoint_num());
            pstmt.setString(9, classInfoEntity.getWeek_all());
            pstmt.setString(10, classInfoEntity.getClass_statue());
            pstmt.setString(11, classInfoEntity.getPlace());
            //executeUpdate()，返回int ，表示有多少条数据受到了影响
            i = pstmt.executeUpdate();
        if (i == 1) return true;
        else return false;
    }



    public boolean insertScheduleInfo(ScheduleInfoEntity scheduleInfoEntity)  throws SQLException {
        int i = 0;
        String sql = "INSERT INTO schedule_info(class_num,student_id) VALUES(?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, scheduleInfoEntity.getClass_num());
            pstmt.setString(2, scheduleInfoEntity.getStudentId());
            //executeUpdate()，返回int ，表示有多少条数据受到了影响
            i = pstmt.executeUpdate();

        if (i == 1) return true;
        else return false;
    }

    public boolean insertStudentInfo(StudentInfoEntity studentInfoEntity) throws SQLException {
        int i = 0;
        String sql = "INSERT INTO student_info(student_id,student_name) VALUES(?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,studentInfoEntity.getStudent_id());
            pstmt.setString(2,studentInfoEntity.getStudent_name());
            //executeUpdate()，返回int ，表示有多少条数据受到了影响
            i = pstmt.executeUpdate();
        if (i == 1) return true;
        else return false;
    }



    public ArrayList<ClassInfoEntity> selectClassInfo(String class_num)  throws SQLException{

        String sql = "select * from class_info WHERE class_num = ?";
        pstmt =  con.prepareStatement(sql);
        pstmt.setString(1,class_num);
        rs = pstmt.executeQuery();

        ArrayList<ClassInfoEntity> result =  new  ArrayList<ClassInfoEntity>();
        while (rs.next()) {
            ClassInfoEntity classInfoEntity  = new ClassInfoEntity(rs.getInt(1), rs.getString(2), rs.getString(3),
                   rs.getString(4), rs.getString(5), rs.getString(6),
                   rs.getString(7),rs.getString(8),rs.getString(9),
                   rs.getString(10), rs.getString(11),rs.getString(12));
            result.add(classInfoEntity);
        }
        return result;
    }


    public boolean selectExist(String class_num, String time, String place) throws SQLException {
        boolean model = true;
        ArrayList<ClassInfoEntity> classInfoEntityArrayList = selectClassInfo(class_num);
        if (classInfoEntityArrayList.size() == 0) return false;
        else {
            for (ClassInfoEntity classInfoEntity :
                    classInfoEntityArrayList) {
                if (classInfoEntity.getClass_time().equals(time) || classInfoEntity.getPlace().equals(place)) {
                    model = true;
                }
            }
        }
        return model;
    }


}