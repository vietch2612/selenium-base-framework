package me.hoaiviet.webdriver.base.common.core.configuration;

import lombok.Getter;

/**
 * Created by hoaiviet on 12/6/16.
 * Environment
 */
public enum Environment {

    UAT("uat-hq-frontend.seven-system.com", "uat"),
    TEST("qa-hq-frontend.seven-system.com", "test"),
    MAPPING("mapping-hq-frontend.seven-system.com", "mapping"),
    DEV("dev-hq-frontend.seven-system.com", "dev");

    @Getter
    public final String hqDomain;

    @Getter
    public final String type;

    Environment(String hqDomain, String type) {
        this.hqDomain = hqDomain;
        this.type = type;
    }
}
