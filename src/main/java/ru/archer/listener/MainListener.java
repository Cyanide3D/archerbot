package ru.archer.listener;

import net.dv8tion.jda.api.events.emoji.EmojiAddedEvent;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEmojiEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ru.archer.Colors;
import ru.archer.button.ButtonHandler;
import ru.archer.button.TeamRegistrationButton;
import ru.archer.command.AdminCommand;
import ru.archer.command.Command;
import ru.archer.command.SetRoleColorCommand;
import ru.archer.modal.ModalHandler;
import ru.archer.modal.TeamRegistrationModal;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainListener extends ListenerAdapter {

    private List<Command> commands = List.of(
            new AdminCommand(),
            new SetRoleColorCommand()
    );

    private List<ButtonHandler> buttons = List.of(
            new TeamRegistrationButton()
    );

    private List<ModalHandler> modals = List.of(
            new TeamRegistrationModal()
    );

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        for (Command command : commands) {
            if (command.getCommand().getName().equals(event.getName())) {
                command.handle(event);
                return;
            }
        }
    }

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        for (ModalHandler modal : modals) {
            if (event.getModalId().equals(modal.id())) {
                modal.handle(event);
                return;
            }
        }
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        for (ButtonHandler button : buttons) {
            if (event.getComponentId().equals(button.id())) {
                button.handle(event);
                return;
            }
        }
    }

    @Override
    public void onReady(ReadyEvent event) {
        event.getJDA().updateCommands().addCommands(commands.stream().map(Command::getCommand).collect(Collectors.toList())).queue();
    }

    @Override
    public void onEmojiAdded(EmojiAddedEvent event) {
        System.out.println(event.getEmoji().getAsReactionCode());
        System.out.println("qweqweqwe");
    }

    @Override
    public void onCommandAutoCompleteInteraction(CommandAutoCompleteInteractionEvent event) {
        if (event.getName().equals("teamcolor") && event.getFocusedOption().getName().equals("color")) {
            List<net.dv8tion.jda.api.interactions.commands.Command.Choice> choices = Arrays.stream(Colors.values())
                    .map(Enum::name)
                    .filter(word -> word.toLowerCase().startsWith(event.getFocusedOption().getValue().toLowerCase()))
                    .map(word -> new net.dv8tion.jda.api.interactions.commands.Command.Choice(word, word))
                    .collect(Collectors.toList());

            event.replyChoices(choices).queue();
        }
    }
}
