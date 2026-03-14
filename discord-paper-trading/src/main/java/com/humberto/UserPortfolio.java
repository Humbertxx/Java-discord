package com.humberto;
import java.io.IOException;
import java.math.BigDecimal;
import yahoofinance.YahooFinance;
import yahoofinance.Stock;

public class UserPortfolio {
    private String stockSymbol;

    public void setStock(String company){
        this.stockSymbol = company;
    }

    public Stock getStock(){
        for (int i = 0; i < 3; i++) {
            try {
                return YahooFinance.get(stockSymbol);
            } catch (IOException e) {
                if (e.getMessage() != null && e.getMessage().contains("429")) {
                    try { Thread.sleep(1500L * (i + 1)); } catch (InterruptedException ignored) {}
                    continue;
                }
                System.err.println("Error fetching stock data: " + e.getMessage());
                return null;
            }
        }
        return null;
    }

    public void prices() throws IllegalArgumentException{
        Stock stock = getStock();
         if (stock == null){
            System.out.println("could not fetch stock"); // change with discord path
            return;
        }

        BigDecimal price = stock.getQuote().getPrice();
        BigDecimal change = stock.getQuote().getChangeInPercent();
        BigDecimal peg = stock.getStats().getPeg();
        BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
        
        System.out.println("Symbol: " + stock.getSymbol());
        System.out.println("Price: " + price);
        System.out.println("Change %: " + change);
        System.out.println("PEG: " + peg);
        System.out.println("Dividend Yield %: " + dividend);
    }
}
