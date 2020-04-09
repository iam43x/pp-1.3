package DAO;

import model.User;
import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {

    public UserJdbcDAO() {
    }


    public void createTable() throws SQLException {
        Statement stmt = DBHelper.getConnection().createStatement();
        stmt.execute("create table if not exists user_db (id bigint auto_increment," +
                " firstName varchar(256),lastName varchar(256),primary key(id))");
        stmt.close();
    }

    @Override
    public List<User> getAllUsers() throws SQLException {

        List<User> res = new ArrayList<>();

        Statement stmt = DBHelper.getConnection().createStatement();
        stmt.execute("select*from user_db");
        ResultSet resultSet = stmt.getResultSet();
        while (resultSet.next()) {
            res.add(new User(resultSet.getLong(1), resultSet.getString(2)
                    , resultSet.getString(3)));
        }
        stmt.close();
        return res;
    }

    @Override
    public void addUser(User user) throws SQLException {
        PreparedStatement pstmt = DBHelper.getConnection().
                prepareStatement("insert into user_db (firstName, lastName) value ((?),(?))");
        pstmt.setString(1, user.getFirstName());
        pstmt.setString(2, user.getLastName());
        pstmt.execute();
        pstmt.close();
    }

    @Override
    public void updateUser(String firstName, String lastName, Long id) throws SQLException {
        PreparedStatement pstmt = DBHelper.getConnection().
                prepareStatement("Update user_db set firstName=(?), lastName=(?) where id=(?)");
        pstmt.setString(1, firstName);
        pstmt.setString(2, lastName);
        pstmt.setLong(3, id);
        pstmt.execute();
        pstmt.close();
    }

    @Override
    public void deleteUser(Long id) throws SQLException {
        PreparedStatement pstmt = DBHelper.getConnection().
                prepareStatement("delete from user_db where id=(?)");
        pstmt.setLong(1, id);
        pstmt.execute();
        pstmt.close();
    }

    @Override
    public User getUserById(Long id) throws SQLException {
        PreparedStatement pstmt = DBHelper.getConnection().
                prepareStatement("select*from user_db where id=(?)");
        pstmt.setLong(1, id);
        ResultSet resultSet = pstmt.executeQuery();
        resultSet.next();
        String firstName = resultSet.getString(2);
        String lastName = resultSet.getString(3);
        pstmt.close();
        return new User(id, firstName, lastName);
    }
}
