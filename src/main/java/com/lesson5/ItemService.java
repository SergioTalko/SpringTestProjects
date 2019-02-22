package com.lesson5;

import org.springframework.beans.factory.annotation.Autowired;

public class ItemService {


    private final ItemDAO itemDAO;

    @Autowired
    public ItemService(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    public Item save(Item item) {
        try {
            return itemDAO.save(item);
        } catch (Exception e) {
            System.out.println("Item with id: " + item.getId() + "is not saved");
            e.printStackTrace();
            return null;
        }
    }

    public Item update(Item item) {
        try {
            return itemDAO.update(item);
        } catch (Exception e) {
            System.out.println("Item with id: " + item.getId() + "is not updated");
            e.printStackTrace();
            return null;
        }
    }

    public void delete(long id) {
        try {
            itemDAO.delete(id);
        } catch (Exception e) {
            System.out.println("Item with id: " + id + "is not deleted");
            e.printStackTrace();
        }
    }
}
