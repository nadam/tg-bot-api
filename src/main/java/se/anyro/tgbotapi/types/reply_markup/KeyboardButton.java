package se.anyro.tgbotapi.types.reply_markup;

/**
 * Button for reply keyboard.
 * 
 * @see <a href="https://core.telegram.org/bots/api#keyboardbutton">Official documentation of KeyboardButton</a>
 */
public class KeyboardButton {

    public String text;
    public boolean request_contact;
    public boolean request_location;

    public KeyboardButton(String text) {
        this.text = text;
    }
}