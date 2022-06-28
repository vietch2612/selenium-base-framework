package webdriver.common.core.configuration;

import lombok.Getter;

/**
 * Created by hoaiviet on 5/18/17.
 * Environment
 */
public enum Environment {
    UAT("", "uat"),
    TEST_1("", "test1"),
    MAPPING_1("", "mapping1"),
    DEVELOPMENT_1("", "dev1");

    @Getter
    public final String url;

    @Getter
    public final String type;

    Environment(String url, String type) {
        this.url = url;
        this.type = type;
    }
}
