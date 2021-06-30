@test
  Feature: Voxsmart technical test

    @task-1
    Scenario: Test the total currency records on the homepage
      Given I navigate to CoinMarketHomePage page
      And I successfully landed on CoinMarketHomePage page
      When the title of page reads "Today's Cryptocurrency Prices by Market Cap"
      Then make sure there are 100 records on the page

    @task-3
    Scenario: Compare different Rankings on currencies
      Given I navigate to CoinMarketHomePage page
      And I successfully landed on CoinMarketHomePage page
      When I move mouse over cryptocurrencies link
      Then make sure dropdown_menu_crypto is visible
      When I click the crypto_menu_FiatRanking link
      Then I successfully landed on FiatsCompanyRankings page
      And the title of page reads "Bitcoin Compared To The Largest Fiat Currencies In The World by Market Cap"

      When I move mouse over cryptocurrencies link
      Then make sure dropdown_menu_crypto is visible
      When I click the crypto_menu_recentlyAdded link
      Then I successfully landed on RecentlyAddedCurrencies page
      And the title of page reads "New Cryptocurrencies"