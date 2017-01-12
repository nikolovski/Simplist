package com.n00b5.simplist.data;

import com.n00b5.simplist.api.ebay.EbayToken;
import com.n00b5.simplist.api.etsy.EtsyToken;
import com.n00b5.simplist.beans.User;
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
public class TokensDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public void insertEbayToken(EbayToken token) {
        sessionFactory.getCurrentSession().save(token);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public void insertEtsyToken(EtsyToken token) {
        sessionFactory.getCurrentSession().save(token);
    }
}
