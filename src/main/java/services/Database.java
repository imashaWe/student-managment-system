package services;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private Connection connection;
    private Statement statement;

    public Database() {
        String connectionUrl ="jdbc:mysql://localhost/java_sms?" +
                "user=root&password=";

        try {
            this.connection = DriverManager.getConnection(connectionUrl);
            System.out.println("Database connect success!");
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewStu(String fname,String lname,String nic,String phone) {

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO student(fname,lname,nic,phone) VALUES(?,?,?,?)");
            statement.setString(1,fname);
            statement.setString(2,lname);
            statement.setString(3,nic);
            statement.setString(4,phone);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void edit(int id,String fname,String lname,String nic,String phone) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE student SET fname = ?,lname = ?,nic = ?,phone = ? WHERE id = ?");
            statement.setString(1,fname);
            statement.setString(2,lname);
            statement.setString(3,nic);
            statement.setString(4,phone);
            statement.setInt(5,id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
            try {
                PreparedStatement statement = connection.prepareStatement("UPDATE student SET is_delete = 1 WHERE id = ?");
                statement.setInt(1,id);
                statement.execute();

            }catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public ArrayList<Object[]> getAllStudent(String q) {
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        try {
            Statement statement = connection.createStatement();
             String sql = "SELECT * FROM student WHERE is_delete = 0";

            if (!q.isEmpty()) {
                sql += " AND fname LIKE '%"+q+"%'";
                sql += " OR lname LIKE '%"+q+"%'";
                sql += " OR nic LIKE '%"+q+"%'";
                sql += " OR phone LIKE '%"+q+"%'";
            }

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Object[] row = {
                        resultSet.getString("id"),
                        resultSet.getString("fname"),
                        resultSet.getString("lname"),
                        resultSet.getString("nic"),
                        resultSet.getString("phone"),
                };
                data.add(row);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  data;
    }


}
