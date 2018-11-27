package com.lesson2part2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.net.ConnectException;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;


    @RequestMapping(method = RequestMethod.POST, value = "/addItem", produces = "text/plain")
    @ResponseBody
    public String addItem(HttpServletRequest req) {

        String response;
        try {
            Item item = parseJSONToObject(req);
            itemService.addItem(item);
            response = "Item " + item.getName() + " was successfully saved";
        } catch (ConnectException e) {
            e.printStackTrace();
            response = e.getMessage();

        }
        return response;
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/updateItem", produces = "text/plain")
    @ResponseBody
    public String updateItem(HttpServletRequest req) {
        String response;
        try {
            Item item = parseJSONToObject(req);
            itemService.update(item);
            response = "Item " + item.getName() + " was successfully updated";
        } catch (BadRequestException | ConnectException e) {
            response = e.getMessage();
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteItem", produces = "text/plain")
    @ResponseBody
    public String deleteItem(@RequestParam Long id) {
        String response;
        try {
            Item item = findItemById(id);
            itemService.update(item);
            response = "Item " + item.getName() + " was successfully deleted";
        } catch (BadRequestException | ConnectException e) {
            response = e.getMessage();
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getItemById", produces = "text/plain")
    @ResponseBody
    public String getItemById(@RequestParam Long id) {
        String response;
        try {
            response = itemService.getItemById(id).toString();
            return response;
        } catch (BadRequestException | ConnectException e) {
            response = e.getMessage();
        }
        return response;
    }

    private Item findItemById(Long id) throws BadRequestException, ConnectException {

        return itemService.getItemById(id);
    }


    private Item parseJSONToObject(HttpServletRequest req) {

        ObjectMapper mapper = new ObjectMapper();
        Item item = null;
        try (InputStream inputStream = req.getInputStream()) {
            item = mapper.readValue(inputStream, Item.class);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return item;
    }
}



