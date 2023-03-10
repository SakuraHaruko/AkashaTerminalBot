package net.buguwu.akashaterminalbot.controller;

import lombok.extern.slf4j.Slf4j;
import net.buguwu.akashaterminalbot.bot.handler.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@Slf4j
public class WebHookController {
    @Autowired
    public CommandHandler commandHandler;

    @PostMapping(value={"/webhook"})
    public void getUpdate(@RequestBody Update update) {
        log.info("some update recieved {}",update.toString());
        this.commandHandler.onWebhookUpdateReceived(update);
    }
}
