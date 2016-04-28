package se.anyro.tgbotapi.types.inline;

import se.anyro.tgbotapi.types.reply_markup.InlineKeyboardMarkup;

/**
 * @see <a href="https://core.telegram.org/bots/api#inlinequeryresultlocation">Official documentation of
 *      InlineQueryResultLocation</a>
 */
public class InlineQueryResultLocation extends InlineQueryResult {
    public String type = "location";
    public String id;
    public float latitude;
    public float longitude;
    public String title;
    public InlineKeyboardMarkup reply_markup;
    public InputMessageContent input_message_content;
    public String thumb_url;
    public int thumb_width;
    public int thumb_height;

    public InlineQueryResultLocation(String id, float latitude, float longitude, String title) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
    }
}
