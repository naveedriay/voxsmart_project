package com.voxsmart.page_objects;

import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

public class CoinMarketCapHomepage extends  AbstractPageObject {


    public String pageTitle  = "h1";
    public String goToHomepage = ".cmc-logo-link";
    public String toHeaderItems= ".cmc-header-desktop nav ul li a"; //.cmc-header-desktop  nav > ul > li:nth-child(1) > a > div

    public String cryptocurrencies = ".cmc-header-desktop  nav > ul > li:nth-child(1) > a > div";
    public String exchanges = ".cmc-header-desktop  nav > ul > li:nth-child(2) > a > div";
    public String NFT       = ".cmc-header-desktop  nav > ul > li:nth-child(3) > a > div";
    public String portfolio = ".cmc-header-desktop  nav > ul > li:nth-child(4) > a > div";
    public String watchList = ".cmc-header-desktop  nav > ul > li:nth-child(5) > a > div";
    public String calendar  = ".cmc-header-desktop  nav > ul > li:nth-child(6) > a > div";
    public String products  = ".cmc-header-desktop  nav > ul > li:nth-child(7) > div";
    public String learn     = ".cmc-header-desktop  nav > ul > li:nth-child(8) > div";

    public String dropdown_menu_crypto = ".cmc-header-desktop  nav > ul > li:nth-child(1) > a > div[aria-expanded=true]";
    public String crypto_menu_ranking        = "#tippy-2 > div > div.tippy-content > div > div > div:nth-child(1) > div:nth-child(1)";
    public String crypto_menu_globalCharts   = "#tippy-2 > div > div.tippy-content > div > div > div:nth-child(1) > div:nth-child(2)";
    public String crypto_menu_FiatRanking    = "#tippy-2 > div > div.tippy-content > div > div > div:nth-child(1) > div:nth-child(3)";
    public String crypto_menu_CompanyRanking = "#tippy-2 > div > div.tippy-content > div > div > div:nth-child(1) > div:nth-child(4)";


    public String all_currencies_list = ".cmc-table tbody tr";
    public String first_currency_item = ".cmc-table tbody tr:nth-child(1)";
    public String result_shown_text   = ".main-content > div.sc-57oli2-0.dEqHl.cmc-body-wrapper > div > div > div.sc-16r8icm-0.sc-4r7b5t-0.eYVrH > p";

    public CoinMarketCapHomepage(){
        CURRENT_URL = "https://coinmarketcap.com/";
    }

    public void pageShownWithElements() {
        assertTrue(find(By.cssSelector(pageTitle)).isDisplayed());
        assertTrue(find(By.cssSelector(goToHomepage)).isDisplayed());
        assertTrue(find(By.cssSelector(toHeaderItems)).isDisplayed());
    }

    public String getCssElementPath(String element_name ) throws  NoSuchFieldException, IllegalAccessException {
        return CoinMarketCapHomepage.class.getField(element_name).get(this).toString();
    }

}