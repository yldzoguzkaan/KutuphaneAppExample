package Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Hibernate.HibernateUtil;
import Model.Time;

public class TimeDao {
	public List < Time > getTimes() {
		Session session = null;
		try {
        	session = HibernateUtil.getSessionFactory().openSession();
            
        }catch (Exception e) {
        	e.printStackTrace();
		}
        return session.createQuery("from Time", Time.class).list();
    }
	public Time getTime() {
		List<Time> tl = getTimes();
		Time result = tl.get(0);
		return result;
    }
	public int updateTime(Time time) {
        Transaction transaction = null;
        Session session = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
        	// start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(time);
            // commit transaction
            transaction.commit();
            return 100;
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return 150;
        }
    }
}
