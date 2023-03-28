package ru.archer.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;

public class AdminCommand implements Command {
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        event.getChannel().asTextChannel().sendMessageEmbeds(new EmbedBuilder()
                        .addField("<:SDLLOGOsmall:1089265210528907425> TEAM CREATION: <:SDLLOGOsmall:1089265210528907425>", "Please select **Team Registration** and follow the process in a new window. \n\n" +
                                "**\uD83D\uDC49** This will create a new role with the name of your team, assign you as a Captain of that team and create a text + voice channels accessible only by members of your team. From there on out, You will be in control of who is allowed to join your team, as well as remove any existing members. ", false)
                        .addField("","",false)
                        .addField("<:SDLLOGOsmall:1089265210528907425> **JOIN AN EXISTING TEAM:** <:SDLLOGOsmall:1089265210528907425>","Please select **Join a team** and follow the process in a new window. \n\n" +
                                "**\uD83D\uDC49** Choose this option if you would like to request joining a pre-existing Team. Note that only the Team Captain will be notified of the join request and only they can accept/deny the request.", false)
                        .setColor(Color.GREEN)
                        .setThumbnail("https://cdn.discordapp.com/attachments/1088894710787084471/1090383239782350878/SDLLogo3small.png")
                        .build())
                .addActionRow(
                        Button.primary("team_registration","Team registration"),
                        Button.primary("team_join", "Join team")
                ).queue();
    }

    @Override
    public CommandData getCommand() {
        return Commands.slash("test", "Only admin command").setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR));
    }
}
