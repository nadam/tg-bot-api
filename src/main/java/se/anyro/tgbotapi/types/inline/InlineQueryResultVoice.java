package se.anyro.tgbotapi.types.inline;

import se.anyro.tgbotapi.types.reply_markup.InlineKeyboardMarkup;

/**
 * @see <a href="https://core.telegram.org/bots/api#inlinequeryresultvoice">Official documentation of
 *      InlineQueryResultVoice</a>
 */
public class InlineQueryResultVoice extends InlineQueryResult {
    public String type = "voice";
    public String id;
    public String voice_url;
    public String title;
    public String caption;
    public String parse_mode;
    public int voice_duration;
    public InlineKeyboardMarkup reply_markup;
    public InputMessageContent input_message_content;

    public InlineQueryResultVoice(String id, String voiceUrl, String title) {
        this.id = id;
        this.voice_url = voiceUrl;
        this.title = title;
    }
}