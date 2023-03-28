package ru.archer.button;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

public class TeamRegistrationButton implements ButtonHandler {

    @Override
    public void handle(ButtonInteractionEvent event) {
        event.replyModal(Modal.create("team_registration", "Team registration")
                .addActionRows(ActionRow.of(TextInput.create("team_name", "Team name", TextInputStyle.SHORT).setRequired(true).build()))
//                .addActionRows(ActionRow.of(TextInput.create("team_role_color", "Role color", TextInputStyle.SHORT).setRequired(false).build()))
                .build()).queue();
    }

    @Override
    public String id() {
        return "team_registration";
    }


}
