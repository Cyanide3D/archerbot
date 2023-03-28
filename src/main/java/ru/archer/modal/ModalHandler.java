package ru.archer.modal;

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;

public interface ModalHandler {

    void handle(ModalInteractionEvent event);
    String id();

}
