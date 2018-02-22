package se.anyro.tgbotapi.types.inline;

import se.anyro.tgbotapi.types.reply_markup.InlineKeyboardMarkup;

/**
 * @see <a href="https://core.telegram.org/bots/api#inlinequeryresultcachedaudio">Official documentation of
 *      InlineQueryResultCachedAudio</a>
 */
public class InlineQueryResultCachedAudio extends InlineQueryResult {
    public String type = "audio";
    public String id;
    public String audio_file_id;
    public String caption;
    public String parse_mode;
    public InlineKeyboardMarkup reply_markup;
    public InputMessageContent input_message_content;

    public InlineQueryResultCachedAudio(String id, String fileId) {
        this.id = id;
        this.audio_file_id = fileId;
    }
}