package Data;
import Bean.Admin;
import Bean.Subscriber;
import Bean.movie;
import Bean.user;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class UserDao {

    private DataSource datasource;

    private void closeSQL(Connection connection, Statement statement, ResultSet resultset) {
        try {
            if (resultset != null) {
                resultset.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public Subscriber login(String email, String password) throws SQLException {

        Connection conn = null;    PreparedStatement pst = null;
        ResultSet rs = null;
        conn = DriverManager.getConnection("jdbc:mysql://localhost/redfox"
                ,"root", "Whales123");
        pst = conn.prepareStatement("select * from user_table inner join subscriber on " +
                "user_table.email= subscriber.email where user_table.email = ? and password = ?;");
        pst.setString(1, email);
        pst.setString(2, password);
        rs = pst.executeQuery();
        if (rs.next()) {
            Subscriber subscriber = new Subscriber();
            subscriber.setSubscriberID(rs.getInt(1));
            subscriber.setFirstName(rs.getString(2));
            subscriber.setSubscriberID(rs.getInt(3));
            subscriber.setPreferedGenre(rs.getString(4));
            subscriber.setEmail(rs.getString(5));
            subscriber.setPassword(rs.getString(6));

            return subscriber;
        } else {
            System.out.println("Subscriber Does Not Exist");
        }    return null;
    }


    public Admin authentication(String email, String password) throws SQLException, NamingException {

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/redfox"
                    ,"root", "Whales123");






            pst = conn.prepareStatement("select * from user_table inner join admin " +
                    "on user_table.email= admin.email \n" +
                    "where user_table.email = ? and password = ?;");
            pst.setString(1, email);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if (rs.next()) {

                Admin admin = new Admin();
                admin.setEmail(rs.getString(1));
                admin.setPassword(rs.getString(2));
                admin.setFirstName(rs.getString(3));
                admin.setLastname(rs.getString(4));


                return admin;
            } else {

                System.out.println("Admin Does Not Exist");
            }
            return null;

        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            this.closeSQL(conn, pst, (ResultSet) null);
        }
        return null;

    }

    public static void main(String[]args) throws SQLException, NamingException {
        Admin admin = new Admin();
        UserDao dao= new UserDao();


        admin = dao.authentication("admin@gmail.com", "12345");
        System.out.println(admin.getEmail());



        }


    }










