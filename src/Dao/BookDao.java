package Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Hibernate.HibernateUtil;
import Model.Book;

public class BookDao {
	public List < Book > getBooks() {
		Session session = null;
		try {
        	session = HibernateUtil.getSessionFactory().openSession();
            
        }catch (Exception e) {
        	e.printStackTrace();
		}
        return session.createQuery("from Book", Book.class).list();
    }
	public void saveBook(Book book) {
        Transaction transaction = null;
        Session session = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
        	// start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(book);
            // commit transaction
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
	public void updateBook(Book book) {
        Transaction transaction = null;
        Session session = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
        	// start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(book);
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
