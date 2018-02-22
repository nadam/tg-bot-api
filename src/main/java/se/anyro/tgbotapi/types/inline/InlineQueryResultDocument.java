package se.anyro.tgbotapi.types.inline;

import se.anyro.tgbotapi.types.reply_markup.InlineKeyboardMarkup;

/**
 * @see <a href="https://core.telegram.org/bots/api#inlinequeryresultdocument">Official documentation of
 *      InlineQueryResultDocument</a>
 */
public class InlineQueryResultDocument extends InlineQueryResult {
    public String type = "document";
    public String id;
    public String title;
    public String caption;
    public String parse_mode;
    public String document_url;
    public String mime_type;
    public String description;
    public InlineKeyboardMarkup reply_markup;
    public InputMessageContent input_message_content;
    public String thumb_url;
    public int thumb_width;
    public int thumb_height;

    public static final String MIME_PDF = "application/pdf";
    public static final String MIME_ZIP = "application/zip";

    public InlineQueryResultDocument(String id, String documentUrl, String title, String mimeType) {
        this.id = id;
        this.document_url = documentUrl;
        this.title = title;
        this.mime_type = mimeType;
    }
}
