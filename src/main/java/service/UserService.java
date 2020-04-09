package service;

import DAO.UserHibernateDAO;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static final UserService userService;
    private static final UserHibernateDAO dao;

    private UserService() {
    }

    static {
        userService = new UserService();
        dao=new UserHibernateDAO();
    }

    public static UserService getInstance() {
        return userService;
    }


    public List<User> getAllUsers() throws SQLException {
        return dao.getAllUsers();
    }

    public void addUser(User user) throws SQLException {
        dao.addUser(user);
    }

    public void deleteUser(Long id) throws SQLException {
        dao.deleteUser(id);
    }

    public void updateUser(String firstName, String lastName, Long id) throws SQLException {
        dao.updateUser(firstName, lastName, id);
    }

    public User getUserById(Long id) throws SQLException {
        return dao.getUserById(id);
    }
}
