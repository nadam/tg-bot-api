package se.anyro.tgbotapi.types.reply_markup;
import se.anyro.tgbotapi.types.WebAppInfo;

/**
 * Button for reply keyboard.
 * 
 * @see <a href="https://core.telegram.org/bots/api#keyboardbutton">Official documentation of KeyboardButton</a>
 */
public class KeyboardButton {

    public String text;
    public Boolean request_contact;
    public Boolean request_location;
    public KeyboardButtonPollType request_poll;
    public WebAppInfo web_app;

    public KeyboardButton(String text) {
        this.text = text;
    }

    public static KeyboardButton textButton(String text) {
        return new KeyboardButton(text);
    }

    public static KeyboardButton contactButton(String text) {
        KeyboardButton button = new KeyboardButton(text);
        button.request_contact = true;
        return button;
    }

    public static KeyboardButton locationButton(String text) {
        KeyboardButton button = new KeyboardButton(text);
        button.request_location = true;
        return button;
    }

    public static KeyboardButton pollButton(String text, KeyboardButtonPollType pollType) {
        KeyboardButton button = new KeyboardButton(text);
        button.request_poll = pollType;
        return button;
    }

    public static KeyboardButton webAppButton(String text, String url) {
        KeyboardButton button = new KeyboardButton(text);
        button.web_app = new WebAppInfo(url);
        return button;
    }
}
