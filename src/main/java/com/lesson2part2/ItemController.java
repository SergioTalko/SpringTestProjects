package com.lesson2part2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;


    @RequestMapping(method = RequestMethod.POST, value = "/addItem", produces = "text/plain")
    @ResponseBody
    public String save(HttpServletRequest req) throws ConnectException {

        String response = null;
        try {
            Item item = parseJSONToObject(req);
            itemService.addItem(item);
            response = "Item " + item.getName() + " was successfully saved";
        } catch (ConnectException e) {
            e.printStackTrace();
            return e.getMessage();

        } catch (IOException e) {
            e.printStackTrace();
            response = "Cant save item. Try again later ";
        }

        return response;
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



