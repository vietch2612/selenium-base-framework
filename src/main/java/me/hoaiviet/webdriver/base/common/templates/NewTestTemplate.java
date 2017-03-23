package me.hoaiviet.webdriver.base.common.templates;

import me.hoaiviet.webdriver.base.common.core.helper.UrlHelper;

/**
 * Created by hoaiviet on 12/7/16.
 * NewTestTemplate
 */
public class NewTestTemplate extends CoreTestTemplate {

    private String hqUrl;

    @Override
    protected void loadFirstPage() {
        prepareURLs();
        driver.get(hqUrl);
    }

    @Override
    protected void prepareURLs() {
        hqUrl = UrlHelper.getUrl();
    }
}
