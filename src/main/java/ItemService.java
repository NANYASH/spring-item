
public class ItemService {

    ItemDAO itemDAO = new ItemDAO();

    public Item save(Item item) {
        return itemDAO.save(item);
    }

    public Item update(Item item){
        return itemDAO.update(item);
    }

    public void delete(long id){
        itemDAO.delete(id);
    }

    public Item findById(long id){
       return itemDAO.findById(Item.class,id);
    }
}
