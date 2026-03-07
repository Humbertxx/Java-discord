package com.humberto;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class DiscordBot extends ListenerAdapter{
    private JDA jda;
    private final String guildId = "792948550295879700"; // Replace with your actual Guild ID
    
    // set jda (api for discord) and starts bot command applications
    public void setJDA(JDA jda){
        this.jda = jda;
        try {
            echoingCommand(); 
        } catch (IllegalArgumentException e) {
            System.err.println("Failed to initialize echoing command: " + e.getMessage());
        } 
    }
    // getter of guild ID
    public String getGuild(){
        return guildId;
    }

    public void echoingCommand() throws IllegalArgumentException{
        Guild guild = jda.getGuildById(getGuild());
        if (guild == null){
            throw new IllegalArgumentException("not valid guild: null"); 
        } 

        guild.updateCommands().addCommands(
            Commands.slash("echo", "Repeats messages back to you.")
                .addOption(OptionType.STRING, "message", "The message to repeat.")
                .addOption(OptionType.INTEGER, "times", "The number of times to repeat the message.")
                .addOption(OptionType.BOOLEAN, "ephemeral", "Whether or not the message should be sent as an ephemeral message."),
        
            Commands.slash("animal", "Finds a random animal")
                .addOptions(
                    new OptionData(OptionType.STRING, "type", "The type of animal to find")
                    .addChoice("Bird", "bird")
                    .addChoice("Big Cat", "bigcat")
                    .addChoice("Canine", "canine")
                    .addChoice("Fish", "fish")
            )).queue();
    }
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("echo")) {
          event.reply(event.getOption("message").getAsString()).queue(); // reply immediately
        }
    }
  
}