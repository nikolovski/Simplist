package com.n00b5.simplist.data;

import com.n00b5.simplist.api.ebay.EbayItem;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Project: simplist
 *
 * @author d4k1d23
 * @date 1/12/17
 */
public class EbayDAO {


    private SessionFactory sessionFactory;

    @Transactional(isolation = Isolation.READ_COMMITTED,
    propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void createEbayItem(EbayItem item){
        sessionFactory.getCurrentSession().save(item);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteItem(EbayItem ebayItem){
        sessionFactory.getCurrentSession().delete(ebayItem);
    }


    @Transactional
    public void deleteItemById(String listingId){
        EbayItem ebayItem = (EbayItem) sessionFactory.getCurrentSession().get(EbayItem.class,listingId);
        sessionFactory.getCurrentSession().delete(ebayItem);
    }

    @Transactional
    public EbayItem getItemById(String id){
        return (EbayItem) sessionFactory.getCurrentSession().get(EbayItem.class,id);
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
