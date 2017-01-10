package com.n00b5.simplist.data;

<<<<<<< HEAD
import com.n00b5.simplist.api.etsy.EtsyItem;
=======
>>>>>>> master
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
<<<<<<< HEAD
 * Created by Louis on 1/7/2017.
 */

public class DAO {


    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {this.sessionFactory = sessionFactory;}

    @Transactional()
    public EtsyItem etsyGetById(String id) {
        System.out.println("IN DAO GET BY ID");
        EtsyItem etsyItem = (EtsyItem) sessionFactory.getCurrentSession().load(EtsyItem.class,id);
        System.out.println("ITEM IN DAO -> " + etsyItem);
        return etsyItem;
    }
=======
 * Created by Shehar on 1/7/2017.
 */
public class DAO {

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {this.sessionFactory = sessionFactory;}


>>>>>>> master

    @Transactional(	isolation= Isolation.READ_COMMITTED,
            propagation= Propagation.REQUIRED,
            rollbackFor=Exception.class)
<<<<<<< HEAD
    public void etsyAddItem(EtsyItem etsyItem) {
        System.out.println("Saving this to the db -> " + etsyItem.toString());
        sessionFactory.getCurrentSession().save(etsyItem);
        System.out.println("Added to db");
    }

    @Transactional()
    public void etsyDeleteItem(String listing_id) {

        System.out.println("IN DAO DELETE");

        EtsyItem etsyItem = (EtsyItem) sessionFactory.getCurrentSession().load(EtsyItem.class,listing_id);
        sessionFactory.getCurrentSession().delete(etsyItem);

        System.out.println("deleted from db");

    }

    @Transactional()
    public void etsyUpdateItem(EtsyItem updatedEtsyItem, String listing_id) {
        System.out.println("IN DAO UPDATE");
        EtsyItem dbEtsyItem = (EtsyItem) sessionFactory.getCurrentSession().load(EtsyItem.class,listing_id);
        System.out.println("DB ITEM IS -> " + dbEtsyItem);

        dbEtsyItem.setTitle(updatedEtsyItem.getTitle());
        dbEtsyItem.setDescription(updatedEtsyItem.getDescription());
        dbEtsyItem.setQuantity(updatedEtsyItem.getQuantity());

        System.out.println("ABOUT TO UPDATE THE DB");
        sessionFactory.getCurrentSession().update(dbEtsyItem);
        System.out.println("ADDED TO THE DB");
    }
}
=======
    public void insert(Object obj){
        sessionFactory.getCurrentSession().save(obj);
    }

}
>>>>>>> master
