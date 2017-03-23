package me.hoaiviet.webdriver.base.common.core.configuration;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriverException;
import org.testng.SkipException;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hoaiviet on 12/6/16.
 * Configuration
 */
public class Configuration {

    private static final String DEFAULT_CONFIG_FILE = "config_default.yml";
    private static Map<String, String> defaultConfig;
    private static final Map<String, String> testConfig = new HashMap<>();

    @Getter(lazy = true)
    private static final String hqDomain = getEnvironment().hqDomain;

    private static Map<String, String> readConfiguration() {
        if (defaultConfig == null) {
            Yaml yaml = new Yaml();

            try {
                //noinspection unchecked
                defaultConfig = (Map<String, String>) yaml
                        .load(new FileInputStream(new File(DEFAULT_CONFIG_FILE)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new SkipException("Can't find config file.");
            }
        }
        return defaultConfig;
    }

    private static String getPropertyFromFile(String propertyName) {
        return "null".equals(String.valueOf(readConfiguration().get(propertyName))) ? null
                : String.valueOf(readConfiguration().get(propertyName));
    }

    private static String getProp(String propertyName) {
        if (testConfig.get(propertyName) == null) {
            return System.getProperty(propertyName) != null ?
                    System.getProperty(propertyName) : getPropertyFromFile(propertyName);
        } else {
            return testConfig.get(propertyName);
        }
    }

    public static String getBrowser() {
        return getProp("browser");
    }

    private static String getEnv() {
        return getProp("env");
    }

    public static String[] getExtension() {
        String extensions = getProp("extensions");

        if (StringUtils.isEmpty(extensions)) {
            return new String[]{};
        }

        ArrayList<String> res = new ArrayList<>();
        for (String ext : extensions.replace("[", "").replace("]", "").split(",")) {
            res.add(ext.trim());
        }

        return res.toArray(new String[res.size()]);
    }

    public static Environment getEnvironment() {
        return getEnvironment(getEnv());
    }

    private static Environment getEnvironment(String env) {
        if (env.contains("uat")) {
            return Environment.UAT;
        } else if (env.contains("dev")) {
            return Environment.DEV;
        } else if (env.contains("test") || env.contains("qa")) {
            return Environment.TEST;
        } else if (env.contains("mapping")) {
            return Environment.MAPPING;
        }
        return Environment.TEST;
    }

    public static void setTestValue(String key, String value) {
        testConfig.put(key, value);
    }

    public static void clearCustomTestProperties() {
        testConfig.clear();
    }

    public static Dimension getBrowserSize() {
        String size = getProp("browserSize");

        if (StringUtils.isNotBlank(size) || "maximised".equals(size) || size.split("x").length == 2) {
            if ("maximised".equals(size)) {
                return null;
            } else {
                return new Dimension(Integer.valueOf(size.split("x")[0]),
                        Integer.valueOf(size.split("x")[1]));
            }
        } else {
            throw new WebDriverException("browser size: " + size + " is not a proper value");
        }
    }
}
