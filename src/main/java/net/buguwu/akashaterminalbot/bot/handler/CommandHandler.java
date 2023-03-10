package net.buguwu.akashaterminalbot.bot.handler;

import lombok.extern.slf4j.Slf4j;
import net.buguwu.akashaterminalbot.bot.SpringWebhookCommandBot;
import net.buguwu.akashaterminalbot.bot.command.AboutCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.helpCommand.HelpCommand;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
public class CommandHandler extends SpringWebhookCommandBot {
    @Autowired
    private Environment environment;

    public CommandHandler(DefaultBotOptions options, SetWebhook setWebhook) {
        super(options, setWebhook);
        log.info("Registering commands...");
        HelpCommand helpCommand = new HelpCommand();
        this.register(new AboutCommand("about","关于"));
        this.register(helpCommand);
        log.info("Done.");
    }

    public String getBotUsername() {
        return this.environment.getProperty("AkashaTerminalBot.bot.username");
    }

    public String getBotToken() {
        return this.environment.getProperty("AkashaTerminalBot.bot.token");
    }

    public String getBotPath() {
        return this.environment.getProperty("AkashaTerminalBot.bot.token") + "/webhook";
    }

    @Override
    public void processNonCommandUpdate(Update update) {
    }
}
