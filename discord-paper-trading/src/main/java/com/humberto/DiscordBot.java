package com.humberto;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class DiscordBot {
   // public static ConcurrentHashMap<Long, UserPortfolio> portfolios = new ConcurrentHashMap<>();
    public static void main(String[] args) throws Exception {
        JDA jda = JDABuilder.createDefault("YOUR_BOT_TOKEN")
            .addEventListeners(new TradingListener())
            .build();

    }
}
