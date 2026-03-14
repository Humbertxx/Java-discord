package com.humberto;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Main {
    public static void main(String[] args) throws InterruptedException {
       /*  final String token = "TOKEN";
        DiscordBot bot = new DiscordBot();

        JDA jda = JDABuilder.createDefault(token).addEventListeners(bot).build(); 
        jda.awaitReady();

        bot.setJDA(jda);*/
        String ticker = "AAPL";
        UserPortfolio stock = new UserPortfolio();
        stock.setStock(ticker);
        
        stock.prices();
    }
}
