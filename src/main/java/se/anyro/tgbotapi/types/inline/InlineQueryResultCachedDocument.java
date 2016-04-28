package se.anyro.tgbotapi.types.inline;

import se.anyro.tgbotapi.types.reply_markup.InlineKeyboardMarkup;

/**
 * @see <a href="https://core.telegram.org/bots/api#inlinequeryresultcacheddocument">Official documentation of
 *      InlineQueryResultCachedDocument</a>
 */
public class InlineQueryResultCachedDocument extends InlineQueryResult {
    public String type = "document";
    public String id;
    public String title;
    public String document_file_id;
    public String description;
    public String caption;
    public InlineKeyboardMarkup reply_markup;
    public InputMessageContent input_message_content;

    public InlineQueryResultCachedDocument(String id, String fileId, String title) {
        this.id = id;
        this.document_file_id = fileId;
        this.title = title;
    }
}
