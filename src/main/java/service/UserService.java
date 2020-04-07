package service;

import DAO.UserHibernateDAO;
import DAO.UserJdbcDAO;
import model.User;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService userService;

    private SessionFactory sessionFactory;

    private UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService(DBHelper.getSessionFactory());
        }
        return userService;
    }

    public List<User> getAllUsers() throws SQLException {
        return new UserHibernateDAO(sessionFactory.openSession()).getAllUsers();
    }

    public void addUser(User user) throws SQLException {
        new UserHibernateDAO(sessionFactory.openSession()).addUser(user);
    }

    public void deleteUser(Long id) throws SQLException {
        new UserHibernateDAO(sessionFactory.openSession()).deleteUser(id);
    }

    public void updateUser(String name,Long id) throws SQLException {
        new UserHibernateDAO(sessionFactory.openSession()).updateUser(name,id);
    }
}
