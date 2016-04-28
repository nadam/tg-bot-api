package se.anyro.tgbotapi.types.reply_markup;

/**
 * @see <a href="https://core.telegram.org/bots/api#replykeyboardhide">Official documentation of ReplyKeyboardHide</a>
 */
public class ReplyKeyboardHide extends ReplyMarkup {
    public boolean hide_keyboard = true;
    public boolean selective;

    public ReplyKeyboardHide() {
    }

    public ReplyKeyboardHide(boolean selective) {
        this.selective = selective;
    }
}
