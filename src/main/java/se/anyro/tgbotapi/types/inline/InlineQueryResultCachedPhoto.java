package se.anyro.tgbotapi.types.inline;

import se.anyro.tgbotapi.types.reply_markup.InlineKeyboardMarkup;

/**
 * @see <a href="https://core.telegram.org/bots/api#inlinequeryresultcachedphoto">Official documentation of
 *      InlineQueryResultCachedPhoto</a>
 */
public class InlineQueryResultCachedPhoto extends InlineQueryResult {
    public String type = "photo";
    public String id;
    public String photo_file_id;
    public String title;
    public String description;
    public String caption;
    public InlineKeyboardMarkup reply_markup;
    public InputMessageContent input_message_content;

    public InlineQueryResultCachedPhoto(String id, String fileId) {
        this.id = id;
        this.photo_file_id = fileId;
    }
}
