package se.anyro.tgbotapi.types;

/**
 * @see <a href="https://core.telegram.org/bots/api#botcommand">Official documentation of BotCommand</a>
 */
public class BotCommand {
    public String command;
    public String description;

    public BotCommand(String command, String description) {
        this.command = command;
        this.description = description;
    }
}
