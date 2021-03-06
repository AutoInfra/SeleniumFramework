package com.search.DuckDuck;

import base.base_redefined;
import com.duckduckgo.pages.SearchPage;
import com.epam.healenium.SelfHealingDriver;
import javafx.scene.shape.MoveTo;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.retry;

import java.util.concurrent.TimeUnit;

public class SearchTest extends base_redefined {
    //public WebDriver driver - Disabling this in favour of Healenium
/*     public WebDriver webDriver;
    public SelfHealingDriver driver; //healenium webdriver*/


    public WebDriver driver;

    private static final Logger LOGGER = LogManager.getLogger(SearchTest.class);

    @BeforeTest
    @SneakyThrows
    public void InitializeDriver()
    {
/*        webDriver=SetupDriver();
        //creating a delegate, this holds the instance of selenium webdriver
        driver = SelfHealingDriver.create(webDriver);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1200, 800));*/

        driver=SetupDriver();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1200, 800));

    }

    @SneakyThrows
    @Test(description = "DuckDuckSearch" , retryAnalyzer= retry.class)
    @Parameters({"keywordToSearch"})
    public void searchInDuckDuckGo(String keywordfromTestNG) {
        SearchPage SP = new SearchPage(driver);
        SP.goTo();
        SP.doSearch(keywordfromTestNG);
        SP.goToVideos();
        int size = SP.printResult();
        Assert.assertTrue(size <100 );

    }

}
