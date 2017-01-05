package com.n00b5.simplist;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Project: Simplist
 *
 * @author d4k1d23
 * @date 1/4/17
 */
public abstract class BeansContainer {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
