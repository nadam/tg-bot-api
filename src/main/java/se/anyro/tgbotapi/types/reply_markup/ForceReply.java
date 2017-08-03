package se.anyro.tgbotapi.types.reply_markup;

/**
 * @see <a href="https://core.telegram.org/bots/api#forcereply">Official documentation of ForceReply</a>
 */
public class ForceReply extends ReplyMarkup {
    public boolean force_reply = true;
    public Boolean selective;

    public ForceReply() {
    }

    public ForceReply(boolean selective) {
        this.selective = selective;
    }
}
