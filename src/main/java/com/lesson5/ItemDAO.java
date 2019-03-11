package com.lesson5;


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
        try {
            em.persist(item);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cant save item with id " + item.getId() + " .Please try again later");
        }
        return item;
    }

    public Item update(Item item) {
        try {
            em.merge(item);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cant update item with id " + item.getId() + " .Please try again later");
        }
        return item;
    }

    public void delete(long id) {
        try {
            em.remove(findById(id));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cant delete item with id " + id + " .Please try again later");
        }

    }

    public Item findById(long id) {
        Item item = null;
        try {
            item = em.find(Item.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cant find item with id " + id + " .Please try again later");
        }
        return item;
    }


    public List<Item> findAll() {
        List<Item> items = em.createQuery("Select a From Item a",
                Item.class).getResultList();
        return items;
    }
}
