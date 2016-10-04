package se.anyro.tgbotapi.types.inline;

import se.anyro.tgbotapi.types.reply_markup.InlineKeyboardMarkup;

/**
 * @see <a href="https://core.telegram.org/bots/api#inlinequeryresultcachedvoice">Official documentation of
 *      InlineQueryResultCachedVoice</a>
 */
public class InlineQueryResultCachedVoice extends InlineQueryResult {
    public String type = "voice";
    public String id;
    public String voice_file_id;
    public String title;
    public String caption;
    public InlineKeyboardMarkup reply_markup;
    public InputMessageContent input_message_content;

    public InlineQueryResultCachedVoice(String id, String fileId, String title) {
        this.id = id;
        this.voice_file_id = fileId;
        this.title = title;
    }
}