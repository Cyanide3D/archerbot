package ru.archer.modal;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.internal.requests.Route;
import ru.archer.Colors;
import ru.archer.Roles;

import java.awt.*;
import java.util.List;

public class TeamRegistrationModal implements ModalHandler {
    @Override
    public void handle(ModalInteractionEvent event) {
        event.deferReply().setEphemeral(true).queue();
        String teamName = event.getValue("team_name").getAsString();
        Category category = event.getGuild().createCategory(teamName + " AREA").complete();
        TextChannel inviteTextChannel = category.createTextChannel(teamName + "-admin")
                .addPermissionOverride(event.getMember(), List.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_HISTORY), List.of())
                .addPermissionOverride(event.getGuild().getPublicRole(), List.of(), List.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_HISTORY, Permission.MESSAGE_SEND))
                .complete();

        TextChannel textChannel = category.createTextChannel(teamName + "-chat")
                .addPermissionOverride(event.getMember(), List.of(Permission.MESSAGE_MANAGE), List.of())
                .addPermissionOverride(event.getGuild().getPublicRole(), List.of(), List.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_HISTORY, Permission.MESSAGE_SEND))
                .complete();

        VoiceChannel voiceChannel = category.createVoiceChannel("\uD83D\uDD0A " + teamName)
                .addPermissionOverride(event.getMember(), List.of(Permission.VOICE_MOVE_OTHERS), List.of())
                .addPermissionOverride(event.getGuild().getPublicRole(), List.of(), List.of(Permission.VIEW_CHANNEL))
                .complete();

        Role role = event.getGuild().createRole().setName("* " + teamName + " *").setHoisted(true).setColor(Color.red).complete();
        event.getGuild().modifyRolePositions().selectPosition(role).moveTo(7).queue();
        event.getGuild().addRoleToMember(event.getMember(), role).queue();
        event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById(Roles.getCapitanRoleId())).queue();

        textChannel.getManager().putPermissionOverride(role, List.of(Permission.MESSAGE_HISTORY, Permission.MESSAGE_SEND, Permission.VIEW_CHANNEL), List.of()).queue();
        voiceChannel.getManager().putPermissionOverride(role, List.of(Permission.VOICE_CONNECT, Permission.VIEW_CHANNEL), List.of()).queue();

        inviteTextChannel.sendMessageEmbeds(new EmbedBuilder()
                .setTitle("<:SDLLOGOsmall:1089265210528907425> TO CHOOSE THE COLOR OF YOUR TEAM USE ** /TEAMCOLOR** <:SDLLOGOsmall:1089265210528907425>")
                .setDescription("** Available colors: **")
                .setColor(Color.GREEN)
                .addField("", "INDIANRED\n" +
                        "RED\n" +
                        "CRIMSON\n" +
                        "DEEPPINK\n" +
                        "HOTPINK\n" +
                        "PALEVIOLET\n" +
                        "ORANGERED\n" +
                        "ORANGE", true)
                .addField("", "KHAKI\n" +
                        "INDIGO\n" +
                        "TEAL\n" +
                        "STEELBLUE\n" +
                        "NAVY\n" +
                        "CHOCOLATE\n" +
                        "AZURE\n" +
                        "MISTYROSE", true)
                .addField("", "WHITE\n" +
                        "BLACK\n" +
                        "LIMEGREEN\n" +
                        "GREENYELLOW\n" +
                        "DARKGREEN\n" +
                        "BROWN\n" +
                        "DARKSLATEGRAY\n" +
                        "GRAY", true)
                .build()).queue();

        event.getHook().sendMessage("Successfully created the team! Check your new category in the channel list!").queue();
    }

    @Override
    public String id() {
        return "team_registration";
    }
}
