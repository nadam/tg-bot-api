package se.anyro.tgbotapi.types.inline;

import se.anyro.tgbotapi.types.reply_markup.InlineKeyboardMarkup;

/**
 * @see <a href="https://core.telegram.org/bots/api#inlinequeryresultcachedsticker">Official documentation of
 *      InlineQueryResultCachedSticker</a>
 */
public class InlineQueryResultCachedSticker extends InlineQueryResult {
    public String type = "gif";
    public String id;
    public String sticker_file_id;
    public InlineKeyboardMarkup reply_markup;
    public InputMessageContent input_message_content;

    public InlineQueryResultCachedSticker(String id, String fileId) {
        this.id = id;
        this.sticker_file_id = fileId;
    }
}