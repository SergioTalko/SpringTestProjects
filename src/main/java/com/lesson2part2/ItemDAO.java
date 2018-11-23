package com.lesson2part2;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.net.ConnectException;

@Repository
public class ItemDAO {
    // private final String DELETE_ITEM = "DELETE FROM ITEM WHERE Item_ID = ?";

    private static SessionFactory sessionFactory;


    public Item addItem(Item item) throws ConnectException {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();
            session.save(item);
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Cant save item with id " + item.getId());
            System.err.println(e.getMessage());
            if (tr != null)
                tr.rollback();
            throw new ConnectException("There is some connection problem.Please try again later");
        }
        System.out.println("Item with id " + item.getId() + " successfully saved");
        return item;
    }

    public Item updateItem(Item item) throws ConnectException, BadRequestException {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            if (getItemById(item.getId()) != null) {
                tr = session.getTransaction();
                tr.begin();
                session.update(item);
                tr.commit();
            } else throw new BadRequestException("Cant find item in DB with name " + item.getName());
        } catch (HibernateException e) {
            System.err.println("Cant update item with name " + item.getName());
            System.err.println(e.getMessage());
            if (tr != null)
                tr.rollback();
            throw new ConnectException(e.getMessage());
        }
        System.out.println("Item with name " + item.getName() + " successfully updated");
        return item;
    }

    public void deleteItem(long id) throws ConnectException, BadRequestException {
        Transaction tr;
        try (Session session = createSessionFactory().openSession()) {
            if (getItemById(id) != null) {
                tr = session.getTransaction();
                tr.begin();
                session.delete(getItemById(id));
                tr.commit();
            } else throw new BadRequestException("Cant find item in DB with id " + id);
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            throw new ConnectException(e.getMessage());
        }
    }


    public Item getItemById(long id) throws ConnectException, BadRequestException {
        Item item;
        try (Session session = createSessionFactory().openSession()) {
            item = (session.get(Item.class, id));
            if (item != null) return item;
            throw new BadRequestException("There is no item with id " + item.getId());
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            throw new ConnectException(e.getMessage());
        }
    }


    private static SessionFactory createSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }

}
