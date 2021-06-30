package com.voxsmart.page_objects;

import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

public class FiatsCompanyRankings extends  AbstractPageObject {

    public String pageTitle  = "h1";
    public String goToHomepage = ".cmc-logo-link";
    public String toHeaderItems= ".cmc-header-desktop nav ul li a";

    public String cryptocurrencies = ".cmc-header-desktop  nav > ul > li:nth-child(1) > a > div";
    public String all_fiatCurrencies_list = ".cmc-table tbody tr";
    public String first_currency_item = ".cmc-table tbody tr:nth-child(1)";

    public String dropdown_menu_crypto = ".cmc-header-desktop  nav > ul > li:nth-child(1) > a > div[aria-expanded=true]";
    public String crypto_menu_ranking        = "#tippy-2 > div > div.tippy-content > div > div > div:nth-child(1) > div:nth-child(1)";
    public String crypto_menu_recentlyAdded  = "#tippy-2 > div > div.tippy-content > div > div > div:nth-child(1) > div:nth-child(2)";
    public String crypto_menu_legalCountries = "#tippy-2 > div > div.tippy-content > div > div > div:nth-child(1) > div:nth-child(3)";
    public String crypto_menu_globalCharts   = "#tippy-2 > div > div.tippy-content > div > div > div:nth-child(1) > div:nth-child(4)";
    public String crypto_menu_FiatRanking    = "#tippy-2 > div > div.tippy-content > div > div > div:nth-child(1) > div:nth-child(5)";

    public FiatsCompanyRankings(){
        CURRENT_URL = "https://coinmarketcap.com/fiat-currencies/";
    }

    public void pageShownWithElements() {
        assertTrue(find(By.cssSelector(pageTitle)).isDisplayed());
        assertTrue(find(By.cssSelector(goToHomepage)).isDisplayed());
        assertTrue(find(By.cssSelector(toHeaderItems)).isDisplayed());
        assertTrue(find(By.cssSelector(all_fiatCurrencies_list)).isDisplayed());
    }

    public String getCssElementPath(String element_name ) throws  NoSuchFieldException, IllegalAccessException {
        return FiatsCompanyRankings.class.getField(element_name).get(this).toString();
    }
}
