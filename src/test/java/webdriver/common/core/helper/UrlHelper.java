package webdriver.common.core.helper;

import webdriver.common.core.configuration.Configuration;
import webdriver.common.core.configuration.Environment;

/**
 * Created by hoaiviet on 5/18/17.
 * UrlHelper
 */
public class UrlHelper {

    private static Environment env = Configuration.getEnvironment();

    public static String getUrl() {
        return "https://www.2359.co/";
    }
}
