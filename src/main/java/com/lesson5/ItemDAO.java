package com.lesson5;


import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class ItemDAO {

    @PersistenceContext
    EntityManager em;


    public Item save(Item item) {
        em.persist(item);
        return item;
    }

    public Item update(Item item) {
        em.merge(item);
        return item;
    }

    public void delete(long id) {
        em.remove(findById(id));
    }

    public Item findById(long id) {
        Item item = null;
        item = em.find(Item.class, id);
        return item;
    }


    public List<Item> findAll() {
        List<Item> items = em.createQuery("Select a From Item a",
                Item.class).getResultList();
        return items;
    }
}
