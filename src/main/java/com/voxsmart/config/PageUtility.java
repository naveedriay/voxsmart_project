package com.voxsmart.config;

import com.voxsmart.page_objects.AbstractPageObject;
import com.voxsmart.page_objects.CoinMarketCapHomepage;
import com.voxsmart.page_objects.FiatsCompanyRankings;
import com.voxsmart.page_objects.RecentlyAddedCurrencies;
import org.springframework.stereotype.Component;

@Component
public class PageUtility {

    public AbstractPageObject getRelativePageObject(String page_name) {
        AbstractPageObject parentObject = null;

        switch (page_name){
            case "CoinMarketHomePage":
                parentObject = new CoinMarketCapHomepage();
                break;
            case "FiatsCompanyRankings":
                parentObject = new FiatsCompanyRankings();
                break;
            case "RecentlyAddedCurrencies":
                parentObject = new RecentlyAddedCurrencies();
                break;
        }

        return parentObject;
    }
}
