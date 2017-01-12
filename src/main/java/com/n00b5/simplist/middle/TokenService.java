package com.n00b5.simplist.middle;

import com.n00b5.simplist.api.ebay.EbayToken;
import com.n00b5.simplist.api.etsy.EtsyToken;
import com.n00b5.simplist.data.Facade;

/**
 * Project: simplist
 *
 * @author d4k1d23
 * @date 1/12/17
 */
public class TokenService {

    private Facade facade;

    public void setFacade(Facade facade) {
        this.facade = facade;
    }

    public Facade getFacade() {
        return facade;
    }

    public void insertEbayToken(EbayToken token){
        facade.insertEbayToken(token);
    }
    public void insertEtsyToken(EtsyToken token){
        facade.insertEtsyToken(token);
    }
}
