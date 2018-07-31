
public class ItemController {
   ItemService itemService = new ItemService();

    public Item save(Item item) {
        return itemService.save(item);
    }

    public Item update(Item item){
        return itemService.update(item);
    }

    public void delete(Long id){
        itemService.delete(id);
    }

    public Item findById(Long id){
        return itemService.findById(id);
    }
}
