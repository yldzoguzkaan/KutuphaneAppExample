package Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Hibernate.HibernateUtil;
import Model.User;


public class UserDao {
	public List < User > getUsers() {
		Session session = null;
		try {
        	session = HibernateUtil.getSessionFactory().openSession();
            
        }catch (Exception e) {
        	e.printStackTrace();
		}
        return session.createQuery("from User", User.class).list();
    }
	public void saveUser(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
        	// start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(user);
            // commit transaction
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
