package DAO;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {
    private Connection connection;

    private UserJdbcDAO() throws SQLException {
    }

    public UserJdbcDAO(Connection connection) throws SQLException {
        this.connection = connection;
    }


    public static Connection getConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();
            url.append("jdbc:mysql://").
                    append("localhost:").
                    append("3306/").
                    append("preproject?").
                    append("user=web3&").
                    append("password=m8MoKaFmlLqD");
            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (InstantiationException | SQLException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists user_db (id bigint auto_increment, name varchar(256),primary key(id))");
        stmt.close();
    }

    public List<User> getAllUsers() throws SQLException {

        List<User> res = new ArrayList<>();

        Statement stmt = connection.createStatement();
        stmt.execute("select*from user_db");
        ResultSet resultSet = stmt.getResultSet();
        while (resultSet.next()) {
            res.add(new User(resultSet.getLong(1), resultSet.getString(2)));
        }
        return res;
    }

    public void addUser(User user) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("insert into user_db set name=(?)");
        pstmt.setString(1, user.getName());
        pstmt.execute();
        pstmt.close();
    }

    public void updateUser(String name, Long id) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("Update user_db set name=(?) where id=(?)");
        pstmt.setString(1, name);
        pstmt.setLong(2, id);
        pstmt.execute();
        pstmt.close();
    }

    public void deleteUser(Long id) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("delete from user_db where id=(?)");
        pstmt.setLong(1, id);
        pstmt.execute();
        pstmt.close();
    }

}
