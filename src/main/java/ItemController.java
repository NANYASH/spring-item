
public class ItemController {
   ItemService itemService = new ItemService();

    public Item save(Item item) throws InternalServerError {
        return itemService.save(item);
    }

    public Item update(Item item) throws InternalServerError {
        return itemService.update(item);
    }

    public void delete(Long id) throws InternalServerError {
        itemService.delete(id);
    }

    public Item findById(Long id) throws InternalServerError {
        return itemService.findById(id);
    }
}
