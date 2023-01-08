package yuxm.flashsale.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import yuxm.flashsale.entity.User;
import yuxm.flashsale.vo.RespBean;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserUtil {
    private static void createUsers(int count) throws Exception {
        List<User> users = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setId("user" + i + "@test.com");
            user.setUsername("user" + i);
            user.setSalt("randomsalt");
            user.setPassword(MD5Util.encrypt("123456", user.getSalt()));
            user.setLoginCount(1);
            user.setRegisterDate(new Date());
            users.add(user);
        }
        System.out.println("" + count + " users were created");
        //insert to database
        Connection connection = getConnection();
        String sql = "INSERT INTO t_user(login_count, id, username, salt, password, register_date) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            statement.setInt(1, user.getLoginCount());
            statement.setString(2, user.getId());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getSalt());
            statement.setString(5, user.getPassword());
            statement.setTimestamp(6, new Timestamp(user.getRegisterDate().getTime()));

            statement.addBatch();
        }
        statement.executeBatch();
        statement.clearParameters();
        connection.close();
        System.out.println("Users has been inserted to database.");
        //generate userTickets to config.txt
        String urlStr = "http://localhost:8080/login/doLogin";
        File file = new File("/Users/yux.m/Desktop/config.txt");
        if (file.exists()) {    //if it already exists, delete and regenerate one
            file.delete();
        }
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        raf.seek(0);
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            OutputStream out = conn.getOutputStream();
            String params = "email=" + user.getId() + "&password=" + MD5Util.firstLayer("123456");
            out.write(params.getBytes());
            out.flush();

            InputStream in = conn.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = in.read(buff)) >= 0) {
                byteArrayOutputStream.write(buff, 0, len);
            }
            in.close();
            byteArrayOutputStream.close();

            String response = new String(byteArrayOutputStream.toByteArray());
            ObjectMapper objectMapper = new ObjectMapper();
            RespBean respBean = objectMapper.readValue(response, RespBean.class);
            String userTicket = (String) respBean.getObject();
            System.out.println("\tcreate userTicket: " + i);

            String row = user.getId() + "," + userTicket;
            raf.seek(raf.length());
            raf.write(row.getBytes());
            raf.write("\n".getBytes());
            System.out.println("\twrite to file: " + i);
        }
        raf.close();
        System.out.println("END");
    }

    private static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/flashsale?useUnicode=true&characterEncoding=UTF-8&serverTimezone=America/New_York";
        String username = "root";
        String password = "000000";
        String driver = "com.mysql.cj.jdbc.Driver";
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * Helper for load testing. Generate {count} users, insert to database and create userTickets.
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        createUsers(1500);
    }
}
