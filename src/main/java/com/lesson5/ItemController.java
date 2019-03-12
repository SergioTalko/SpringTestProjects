package com.lesson5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    private ItemDAO itemDAO;

    @Autowired
    public ItemController(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save", produces = "application/json")
    public ResponseEntity<Item> saveItem(@RequestBody Item item) {
        try{
            itemDAO.save(item);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("Cant save item with id " + item.getId() + " .Please try again later");
            return new ResponseEntity<>(item,HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update", produces = "application/json")
    public ResponseEntity<Item> updateItem(@RequestBody Item item) {
        try{
        itemDAO.update(item);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("Cant update item with id " + item.getId() + " .Please try again later");
            return new ResponseEntity<>(item,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", produces = "application/json")
    public ResponseEntity<Item> deleteItem(@RequestParam("id") String id) {

        try {
            itemDAO.delete(Long.parseLong(id));
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("Cant delete item with id \" + id + \" .Please try again later");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAll", produces = "application/json")
    public List<Item> getAll() {
        return itemDAO.findAll();
    }

}
