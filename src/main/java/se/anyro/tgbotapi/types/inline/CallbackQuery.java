package se.anyro.tgbotapi.types.inline;

import se.anyro.tgbotapi.types.Message;
import se.anyro.tgbotapi.types.User;

/**
 * @see <a href="https://core.telegram.org/bots/api#callbackquery">Official documentation of CallbackQuery</a>
 */
public class CallbackQuery {
    public String id;
    public User from;
    public Message message;
    public String inline_message_id;
    public String chat_instance;
    public String data;
    public String game_short_name;

    public boolean isInline() {
        return inline_message_id != null;
    }
}
