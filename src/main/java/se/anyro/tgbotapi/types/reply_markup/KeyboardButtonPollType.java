package se.anyro.tgbotapi.types.reply_markup;

/**
 * @see <a href="https://core.telegram.org/bots/api#keyboardbuttonpolltype">Official documentation of
 *      KeyboardButtonPollType</a>
 */
public class KeyboardButtonPollType {
    public String type;

    public KeyboardButtonPollType(String type) {
        this.type = type;
    }
}
