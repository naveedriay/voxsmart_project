package com.voxsmart.page_objects;

import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

public class FiatRankings  extends  AbstractPageObject {

    public String pageTitle  = "h1";
    public String goToHomepage = ".cmc-logo-link";
    public String toHeaderItems= ".cmc-header-desktop nav ul li a";


    public String all_fiatCurrencies_list = ".cmc-table tbody tr";
    public String first_currency_item = ".cmc-table tbody tr:nth-child(1)";

    public FiatRankings(){
        CURRENT_URL = "https://coinmarketcap.com/fiat-currencies/";
    }

    public void pageShownWithElements() {
        assertTrue(find(By.cssSelector(pageTitle)).isDisplayed());
        assertTrue(find(By.cssSelector(goToHomepage)).isDisplayed());
        assertTrue(find(By.cssSelector(toHeaderItems)).isDisplayed());
        assertTrue(find(By.cssSelector(all_fiatCurrencies_list)).isDisplayed());
    }

    public String getCssElementPath(String element_name ) throws  NoSuchFieldException, IllegalAccessException {
        return FiatRankings.class.getField(element_name).get(this).toString();
    }
}
