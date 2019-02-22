package com.lesson5;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        return em.find(Item.class, id);


    }
}
