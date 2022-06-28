package webdriver.common.template;

import webdriver.common.core.helper.UrlHelper;

public class QCPracticeTestTemplate extends CoreTestTemplate {

    private String url;

    protected void loadFirstPage() {
        prepareURLs();
        driver.get(url);
    }

    protected void prepareURLs() {
        url = UrlHelper.getUrl();
    }
}
