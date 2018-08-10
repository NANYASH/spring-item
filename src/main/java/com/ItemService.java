package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemDAO itemDAO;

    public Item save(Item item) throws InternalServerError {
        return itemDAO.save(item);
    }

    public Item update(Item item) throws InternalServerError, BadRequestException {
        return itemDAO.update(item);
    }

    public void delete(long id) throws InternalServerError, BadRequestException {
        itemDAO.delete(id);
    }

    public Item findById(long id) throws InternalServerError, BadRequestException {
       return itemDAO.findById(Item.class,id);
    }


}
