package se.anyro.tgbotapi.types.inline;

import se.anyro.tgbotapi.types.reply_markup.InlineKeyboardMarkup;

/**
 * @see <a href="https://core.telegram.org/bots/api#inlinequeryresultvenue">Official documentation of
 *      InlineQueryResultVenue</a>
 */
public class InlineQueryResultVenue extends InlineQueryResult {
    public String type = "venue";
    public String id;
    public float latitude;
    public float longitude;
    public String title;
    public String address;
    public String foursquare_id;
    public String foursquare_type;
    public InlineKeyboardMarkup reply_markup;
    public InputMessageContent input_message_content;
    public String thumb_url;
    public int thumb_width;
    public int thumb_height;

    public InlineQueryResultVenue(String id, float latitude, float longitude, String title, String address) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.address = address;
    }
}
