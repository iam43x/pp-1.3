package service;

import DAO.UserDAO;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService userService;

    private UserDAO userDAO;

    private UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public static UserService getInstance() throws SQLException {

        return userService==null?new UserService(new UserDAO(UserDAO.getConnection())):userService;
    }

    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    public void addUser(User user) throws SQLException {
        userDAO.addUser(user);
    }

    public void deleteUser(Long id) throws SQLException {
        userDAO.deleteUser(id);
    }

    public void updateUser(String name,Long id) throws SQLException {
        userDAO.updateUser(name,id);
    }
}
