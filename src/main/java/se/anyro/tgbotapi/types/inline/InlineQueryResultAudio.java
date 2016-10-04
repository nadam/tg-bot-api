package se.anyro.tgbotapi.types.inline;

import se.anyro.tgbotapi.types.reply_markup.InlineKeyboardMarkup;

/**
 * @see <a href="https://core.telegram.org/bots/api#inlinequeryresultaudio">Official documentation of
 *      InlineQueryResultAudio</a>
 */
public class InlineQueryResultAudio extends InlineQueryResult {
    public String type = "audio";
    public String id;
    public String audio_url;
    public String title;
    public String caption;
    public String performer;
    public int audio_duration;
    public InlineKeyboardMarkup reply_markup;
    public InputMessageContent input_message_content;

    public InlineQueryResultAudio(String id, String audioUrl, String title) {
        this.id = id;
        this.audio_url = audioUrl;
        this.title = title;
    }
}