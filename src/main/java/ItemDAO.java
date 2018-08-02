import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

import javax.transaction.Transactional;

public class ItemDAO {
    private static SessionFactory sessionFactory;
    private static final String DELETE_ITEM_BY_ID = "DELETE FROM ITEM WHERE ID = ?";


    public Item save(Item item) throws InternalServerError {
        Transaction tr = null;
        try(Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();
            session.save(item);
            tr.commit();
        }catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());
            if (tr != null)
                tr.rollback();
            throw new InternalServerError(e.getMessage());
        }
        System.out.println("Save is done");
        return item;
    }


    public Item update(Item item) throws InternalServerError {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()){
            tr = session.getTransaction();
            tr.begin();
            session.update(item);
            tr.commit();
        }catch (HibernateException e) {
            System.err.println("Update is failed");
            System.err.println(e.getMessage());
            if (tr != null)
                tr.rollback();
            throw new InternalServerError(e.getMessage());
        }
        System.out.println("Update is done");
        return item;
    }


    public void delete(long id) throws InternalServerError {
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
            throw new InternalServerError(e.getMessage());
        }
    }


    public Item findById(Class c, long id) throws InternalServerError {
        try(Session session = createSessionFactory().openSession()) {
            return (session.get(Item.class, id));
        }catch (HibernateException e) {
            System.err.println(c.getName()+" with such id \"" + id + "\" does not exist.");
            System.err.println(e.getMessage());
            throw new InternalServerError(e.getMessage());
        }
    }

    public static SessionFactory createSessionFactory(){
        if (sessionFactory == null) {
            return new Configuration().configure().buildSessionFactory();}
        return sessionFactory;
    }

}
