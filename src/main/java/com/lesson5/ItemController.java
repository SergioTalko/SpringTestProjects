package com.lesson5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

public class ItemController {
    private ItemService itemService;

    @Autowired
    public ItemController(ItemService serviceItem) {
        this.itemService = serviceItem;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/item/save", produces = "text-plain")
    public @ResponseBody
    String saveItem(HttpServletRequest request) {

     //   itemService.save(item);
        return "ok";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/item/update", produces = "text-plain")
    public @ResponseBody
    String updateItem(HttpServletRequest request) {

      //  itemService.update(item);
        return "ok";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/item/delete", produces = "text-plain")
    public @ResponseBody
    String deleteItem(@RequestParam("id") String id) {
        itemService.delete(Long.parseLong(id));
        return "ok";
    }



}
