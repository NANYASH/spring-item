package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(method = RequestMethod.POST, value = "/save", produces = "text/plain")
    @ResponseBody
    public String save(Item item) throws InternalServerError {
        String message;
        try {
            message = "Item :"+itemService.save(item)+" is saved.";
            return message;
        }catch (InternalServerError e){
            return "InternalServerError";
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/delete", produces = "text/plain")
    @ResponseBody
    public String update(Item item) throws InternalServerError, BadRequestException {
        String message;
        try {
            message = "Item :"+itemService.update(item).toString()+" is udated.";
            return message;
        }catch (BadRequestException | InternalServerError e){
            return e.getMessage();
        }

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", produces = "text/plain")
    @ResponseBody
    public String delete(Long id) throws InternalServerError, BadRequestException {
        String message;
        try {
            itemService.delete(id);
            message = "Item with id :"+id+" is deleted.";
            return message;
        }catch (BadRequestException | InternalServerError e){
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findById",produces = "text/plain")
    @ResponseBody
    public String findById(@RequestParam Long id)  {
        String message;
        try {
            message = itemService.findById(id).toString();
            return message;
        }catch (BadRequestException | InternalServerError e){
            return e.getMessage();
        }
    }


}
