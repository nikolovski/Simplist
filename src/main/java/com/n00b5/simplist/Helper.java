package com.n00b5.simplist;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Project: Simplist
 *
 * @author d4k1d23
 * @date 1/10/17
 */
public interface Helper {
    ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
}
