package com.lesson2part2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.ConnectException;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemDAO itemDAO;

    public Item addItem(Item item) throws ConnectException {
        return itemDAO.addItem(item);
    }

    public Item update(Item item) throws ConnectException, BadRequestException {
        return itemDAO.updateItem(item);
    }

    public void delete(long id) throws ConnectException, BadRequestException {
        itemDAO.deleteItem(id);
    }

    public Item getItemById(long id) throws ConnectException, BadRequestException {
        return itemDAO.getItemById(id);
    }


}
