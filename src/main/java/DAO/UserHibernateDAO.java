package DAO;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDAO {

    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = session.beginTransaction();
        List<User> res=session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return res;
    }

    @Override
    public void addUser(User user){
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

    @Override
    public void deleteUser(Long id) {
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from User where id=:id").setParameter("id",id).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void updateUser(String name, Long id) {
        Transaction transaction = session.beginTransaction();
        session.createQuery("update from User u set u.name=:name where id=:id").setParameter("id",id).
                setParameter("name",name).executeUpdate();
        transaction.commit();
        session.close();
    }
}
