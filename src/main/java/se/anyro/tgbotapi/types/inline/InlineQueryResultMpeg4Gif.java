package se.anyro.tgbotapi.types.inline;

import se.anyro.tgbotapi.types.reply_markup.InlineKeyboardMarkup;

/**
 * @see <a href="https://core.telegram.org/bots/api#inlinequeryresultmpeg4gif">Official documentation of
 *      InlineQueryResultMpeg4Gif</a>
 */
public class InlineQueryResultMpeg4Gif extends InlineQueryResult {
    public String type = "mpeg4_gif";
    public String id;
    public String mpeg4_url;
    public int mpeg4_width;
    public int mpeg4_height;
    public int mpeg4_duration;
    public String thumb_url;
    public String title;
    public String caption;
    public String parse_mode;
    public InlineKeyboardMarkup reply_markup;
    public InputMessageContent input_message_content;

    public InlineQueryResultMpeg4Gif(String id, String mpeg4_url, String thumb_url) {
        this.id = id;
        this.mpeg4_url = mpeg4_url;
        this.thumb_url = thumb_url;
    }
}
