package Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Hibernate.HibernateUtil;
import Model.Record;

public class RecordDao {
	public List < Record > getRecords() {
		Session session = null;
		try {
        	session = HibernateUtil.getSessionFactory().openSession();
            
        }catch (Exception e) {
        	e.printStackTrace();
		}
        return session.createQuery("from Record", Record.class).list();
    }
	public void saveRecord(Record record) {
        Transaction transaction = null;
        Session session = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
        	// start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(record);
            // commit transaction
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
	public void updateRecord(Record record) {
        Transaction transaction = null;
        Session session = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
        	// start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(record);
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
