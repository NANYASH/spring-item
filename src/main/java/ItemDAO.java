import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import javax.transaction.Transactional;

public class ItemDAO extends GenericDAO<Item> {
    private static final String DELETE_ITEM_BY_ID = "DELETE FROM ITEM WHERE ID = ?";

    @Override
    public Item save(Item o) {
        return super.save(o);
    }

    @Override
    public Item update(Item o) {
        return super.update(o);
    }

    @Override
    public void delete(long id) {
        Transaction transaction;
        try( Session session = createSessionFactory().openSession()) {
            NativeQuery query = session.createNativeQuery(DELETE_ITEM_BY_ID);
            transaction = session.getTransaction();
            query.addEntity(Item.class);
            query.setParameter(1,id);
            transaction.begin();
            query.executeUpdate();
            transaction.commit();
        }catch (HibernateException e){
            System.err.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public Item findById(Class c, long id) {
        return super.findById(c, id);
    }
}
