package ru.archer;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import ru.archer.listener.MainListener;

public class Main {

    public static void main(String[] args) {
        JDABuilder JDA = JDABuilder
                .createDefault("MTA5MDM2OTUxMjEzOTkyNzY2Mw.G6dohK.mBMXKNo4pN1FxBr6n8Yw6LeKRp_t_23GR31k-8")
                .enableIntents(GatewayIntent.GUILD_EMOJIS_AND_STICKERS)
                .addEventListeners(new MainListener());
        JDA.build();
    }

}
