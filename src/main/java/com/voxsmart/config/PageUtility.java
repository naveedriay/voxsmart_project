package com.voxsmart.config;

import com.voxsmart.page_objects.AbstractPageObject;
import com.voxsmart.page_objects.CoinMarketCapHomepage;
import com.voxsmart.page_objects.FiatRankings;
import org.springframework.stereotype.Component;

@Component
public class PageUtility {

    public AbstractPageObject getRelativePageObject(String page_name) {
        AbstractPageObject parentObject = null;

        if (page_name.equals("CoinMarketHomePage"))
            parentObject = new CoinMarketCapHomepage();
        else
            if(page_name.equals("FiatRankings"))
            parentObject = new FiatRankings();

        return parentObject;
    }
}
