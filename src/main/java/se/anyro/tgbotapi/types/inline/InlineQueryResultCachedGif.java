package se.anyro.tgbotapi.types.inline;

import se.anyro.tgbotapi.types.reply_markup.InlineKeyboardMarkup;

/**
 * @see <a href="https://core.telegram.org/bots/api#inlinequeryresultcachedgif">Official documentation of
 *      InlineQueryResultCachedGif</a>
 */
public class InlineQueryResultCachedGif extends InlineQueryResult {
    public String type = "gif";
    public String id;
    public String gif_file_id;
    public String title;
    public String caption;
    public InlineKeyboardMarkup reply_markup;
    public InputMessageContent input_message_content;

    public InlineQueryResultCachedGif(String id, String fileId) {
        this.id = id;
        this.gif_file_id = fileId;
    }
}
