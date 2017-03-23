package me.hoaiviet.webdriver.base.common.core.helper;

import me.hoaiviet.webdriver.base.common.core.configuration.Configuration;
import me.hoaiviet.webdriver.base.common.core.configuration.Environment;

/**
 * Created by hoaiviet on 12/7/16.
 * UrlHelper
 */
public class UrlHelper {

    private static Environment env = Configuration.getEnvironment();

    public static String getUrl() {
        return "http://" + env.hqDomain;
    }

    public static String getUrlForSC() {
        return "";
    }
}
