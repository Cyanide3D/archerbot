package ru.archer.command;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import ru.archer.Colors;
import ru.archer.Roles;

import java.awt.*;
import java.util.List;

public class SetRoleColorCommand implements Command {
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        String color = event.getOption("color").getAsString();
        try {
            Colors c = Colors.valueOf(color);
            Member member = event.getMember();
            List<Role> roles = member.getRoles();
            Role teamRole = roles.stream().filter(r -> r.getName().startsWith("* ")).findFirst().orElseThrow();
            Role capitanRole = roles.stream().filter(r -> r.getId().equals(Roles.getCapitanRoleId())).findFirst().orElseThrow();
            teamRole.getManager().setColor(new Color(c.getR(), c.getG(), c.getB())).queue();
            event.reply("Successfully changed the color!").setEphemeral(true).queue();
        } catch (Exception e) {
            event.reply("Something went wrong...").setEphemeral(true).queue();
        }
    }

    @Override
    public CommandData getCommand() {
        return Commands.slash("teamcolor","Choose the color of your team").addOption(OptionType.STRING, "color", "Color name", true, true);
    }
}
