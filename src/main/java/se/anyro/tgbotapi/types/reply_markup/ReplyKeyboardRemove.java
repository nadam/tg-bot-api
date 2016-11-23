package se.anyro.tgbotapi.types.reply_markup;

/**
 * @see <a href="https://core.telegram.org/bots/api#replykeyboardhide">Official documentation of ReplyKeyboardHide</a>
 */
public class ReplyKeyboardRemove extends ReplyMarkup {
    public boolean remove_keyboard = true;
    public boolean selective;

    public ReplyKeyboardRemove() {
    }

    public ReplyKeyboardRemove(boolean selective) {
        this.selective = selective;
    }
}
