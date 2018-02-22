package se.anyro.tgbotapi.types.inline;

import se.anyro.tgbotapi.types.reply_markup.InlineKeyboardMarkup;

/**
 * @see <a href="https://core.telegram.org/bots/api#inlinequeryresultcachedmpeg4gif">Official documentation of
 *      InlineQueryResultCachedMpeg4Gif</a>
 */
public class InlineQueryResultCachedMpeg4Gif extends InlineQueryResult {
    public String type = "mpeg4_gif";
    public String id;
    public String mpeg4_file_id;
    public String title;
    public String caption;
    public String parse_mode;
    public InlineKeyboardMarkup reply_markup;
    public InputMessageContent input_message_content;

    public InlineQueryResultCachedMpeg4Gif(String id, String fileId) {
        this.id = id;
        this.mpeg4_file_id = fileId;
    }
}
