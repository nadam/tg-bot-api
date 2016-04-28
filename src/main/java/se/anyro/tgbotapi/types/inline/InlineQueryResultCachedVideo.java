package se.anyro.tgbotapi.types.inline;

import se.anyro.tgbotapi.types.reply_markup.InlineKeyboardMarkup;

/**
 * @see <a href="https://core.telegram.org/bots/api#inlinequeryresultcachedvideo">Official documentation of
 *      InlineQueryResultCachedVideo</a>
 */
public class InlineQueryResultCachedVideo extends InlineQueryResult {
    public String type = "video";
    public String id;
    public String video_file_id;
    public String title;
    public String description;
    public String caption;
    public InlineKeyboardMarkup reply_markup;
    public InputMessageContent input_message_content;

    public InlineQueryResultCachedVideo(String id, String fileId, String title) {
        this.id = id;
        this.video_file_id = fileId;
        this.title = title;
    }
}
