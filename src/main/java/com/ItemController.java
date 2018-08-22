package com;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;
    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(method = RequestMethod.POST, value = "/save", produces = "text/plain")
    @ResponseBody
    public String save(HttpServletRequest req) throws InternalServerError {
        String message;
        try {
            message = "Item :"+itemService.save(mapToItem(req))+" is saved.";
            return message;
        }catch (InternalServerError e){
            e.printStackTrace();
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return new InternalServerError("Something wrong").getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update", produces = "text/plain")
    @ResponseBody
    public String update(HttpServletRequest req) throws InternalServerError, BadRequestException{
        String message;
        try {
            message = "Item :"+itemService.update(mapToItem(req)).toString()+" is updated.";
            return message;
        }catch (BadRequestException | InternalServerError e){
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return new InternalServerError("Something wrong").getMessage();
        }

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", produces = "text/plain")
    @ResponseBody
    public String delete(@RequestParam Long id) throws InternalServerError, BadRequestException {
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

    private Item mapToItem(HttpServletRequest req) throws IOException {
        JsonParser jsonParser = mapper.getFactory().createParser(req.getInputStream());
        Item item = mapper.readValue(jsonParser, Item.class);
        System.out.println(item);
        return item;
    }



}
