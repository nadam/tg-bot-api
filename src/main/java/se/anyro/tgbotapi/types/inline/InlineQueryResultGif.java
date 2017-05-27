package se.anyro.tgbotapi.types.inline;

import se.anyro.tgbotapi.types.reply_markup.InlineKeyboardMarkup;

/**
 * @see <a href="https://core.telegram.org/bots/api#inlinequeryresultgif">Official documentation of
 *      InlineQueryResultGif</a>
 */
public class InlineQueryResultGif extends InlineQueryResult {
    public String type = "gif";
    public String id;
    public String gif_url;
    public int gif_width;
    public int gif_height;
    public int gif_duration;
    public String thumb_url;
    public String title;
    public String caption;
    public InlineKeyboardMarkup reply_markup;
    public InputMessageContent input_message_content;

    public InlineQueryResultGif(String id, String gifUrl, String thumbUrl) {
        this.id = id;
        this.gif_url = gifUrl;
        this.thumb_url = thumbUrl;
    }
}
