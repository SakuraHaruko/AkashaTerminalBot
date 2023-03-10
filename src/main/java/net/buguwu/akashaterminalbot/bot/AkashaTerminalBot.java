package net.buguwu.akashaterminalbot.bot;

import lombok.extern.slf4j.Slf4j;
import net.buguwu.akashaterminalbot.bot.handler.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
@Slf4j
public class AkashaTerminalBot {
    private final TelegramBotsApi telegramBotsApi;
    @Value(value="${AkashaTerminalBot.bot.webhook-host}")
    private String webhookHost;

    @Autowired
    private Environment environment;

    public AkashaTerminalBot() throws TelegramApiException {
         telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
    }

    public static String getAppName() {
        return "AkashaTerminalBot";
    }

    public static String getAppVersion() {
        return "0.0.1 (020)";
    }

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url("https://" + this.webhookHost).build();
    }

    @Bean
    public CommandHandler commandHandler() throws TelegramApiException {
        CommandHandler commandHandler = new CommandHandler(new DefaultBotOptions(), this.setWebhookInstance());
        log.info("SetWebHook from {} {}",AkashaTerminalBot.getAppName(),"https://" + this.webhookHost + "/" + this.environment.getProperty("AkashaTerminalBot.bot.token") + "/webhook");
        this.telegramBotsApi.registerBot(commandHandler, commandHandler.getSetWebhook());
        return commandHandler;
    }

}
