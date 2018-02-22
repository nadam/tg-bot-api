package se.anyro.tgbotapi.types.inline;

import se.anyro.tgbotapi.types.reply_markup.InlineKeyboardMarkup;

/**
 * @see <a href="https://core.telegram.org/bots/api#inlinequeryresultphoto">Official documentation of
 *      InlineQueryResultPhoto</a>
 */
public class InlineQueryResultPhoto extends InlineQueryResult {
    public String type = "photo";
    public String id;
    public String photo_url;
    public String thumb_url;
    public int photo_width;
    public int photo_height;
    public String title;
    public String description;
    public String caption;
    public String parse_mode;
    public InlineKeyboardMarkup reply_markup;
    public InputMessageContent input_message_content;

    public InlineQueryResultPhoto(String id, String photoUrl, String thumbUrl) {
        this.id = id;
        this.photo_url = photoUrl;
        this.thumb_url = thumbUrl;
    }
}