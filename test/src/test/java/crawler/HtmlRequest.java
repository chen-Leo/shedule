package crawler;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;

/**
 * 通过网站域名URL获取该网站的源码
 *
 * @author Administrator
 */
public class HtmlRequest {


    public static void main(String[] args) throws Exception {


        String a = "07,03,08,09,04,17,";
        String c = "11,12,14,17,07,08,09";
        int s = a.indexOf(',');


        System.out.println(a.replaceAll(a.substring(0,s+1),""));
////        String filePar = "d:\\studentFile";// 文件夹路径
////        File myPath = new File(filePar);
////        if (!myPath.exists()) {//若此目录不存在，则创建之
////            myPath.mkdir();
////        }
//        Connection con = JDBCUtil.getConnection();
//        DatabaseUtils databaseUtils = new DatabaseUtils(con);
//        int studentId = 2018210001;
//        for (; studentId <= 2018214999; studentId++) {
//
////            URL url = new URL("http://jwzx.cqupt.edu.cn/kebiao/kb_stu.php?xh=" + student);
////            String fileName = studentId + ".text";
////            FileWriter out = new FileWriter(filePar + "\\" + fileName);   // 这里是读取了源代码并存入了text文件
////            String urlSource = getURLSource(url);
////            out.write(urlSource);
////            out.close();
//
//
//
//            String filePath = "D:\\studentFile\\" + studentId + ".text";
//            String htmlString = FileUtils.readFile(filePath);
//            if (htmlFilterClassesUtils.fileStudentMessage(htmlString,databaseUtils)) {
//                htmlFilterClassesUtils.htmlFilterClasses(htmlString, String.valueOf(studentId),databaseUtils);
//            }
//
//        }
//        JDBCUtil.close(databaseUtils.getRs(),databaseUtils.getPreparedStatment(),databaseUtils.getCon());
    }

    /**
     * 读取网页源代码
     */
    public static String getURLSource(URL url) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5 * 1000);
        InputStream inStream = conn.getInputStream();
        byte[] data = readInputStream(inStream);
        String htmlSource = new String(data);
        return htmlSource;
    }


    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1204];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

}

